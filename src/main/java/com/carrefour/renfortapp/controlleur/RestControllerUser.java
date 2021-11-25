package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.common.DeviceProvider;
import com.carrefour.renfortapp.dao.*;
import com.carrefour.renfortapp.models.*;
import com.carrefour.renfortapp.security.TokenHelper;
import com.carrefour.renfortapp.security.auth.JwtAuthenticationRequest;
import com.carrefour.renfortapp.utils.EmailService;
import com.carrefour.renfortapp.utils.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users")
public class RestControllerUser {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    TokenHelper tokenHelper;
    @Autowired
    private StorageService storage;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DeviceProvider deviceProvider;

    @Autowired
    private IUser iUser;

    @Autowired
    private IAuthority iAuthority;

    @Autowired
    private EtablisementRepository ietab;

    @Autowired
    private SocieteRepository isoc;

    @Autowired
    private Unite_OrgRepository iunite;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/")
    public void home(Device device) {
        if (device.isMobile()) {
            logger.info("Hello mobile user!");
        } else if (device.isTablet()) {
            logger.info("Hello tablet user!");
        } else {
            logger.info("Hello desktop user!");
        }
    }

    @RequestMapping("/register/{idUnite}/{idEtab}/{codeSoc}")

    public ResponseEntity<?> register(User user,@PathVariable Long idUnite,@PathVariable Long idEtab ,@PathVariable Long codeSoc)
    {
        Authority authority=new Authority();
        authority.setName("USER");
        iAuthority.save(authority);

        user.setEnabled(true);
        user.setAuthorities(authority);
        user.setPassword(hash(user.getPassword()));
        // storage.store(file);
        // user.setImage(file.getOriginalFilename());
        user.setMatricule(user.getMatricule());

        user.setPoste(user.getPoste());

        Etablisement e= ietab.findOne(idEtab);
        user.setEtab(e);
        Societe s= isoc.findOne(codeSoc);
        user.setSociete(s);

        UniteOrganisationnelle u= iunite.findOne(idUnite);
        user.setUnite(u);


        iUser.save(user);

        return ResponseEntity.ok(new UserTokenState(null, 0, user));
    }




    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response, Device device
    ) throws AuthenticationException, IOException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        User user = (User) authentication.getPrincipal();

        String jws = tokenHelper.generateToken(user.getUsername(), device);
        int expiresIn = tokenHelper.getExpiredIn(device);
        // Add cookie to response
        response.addCookie(createAuthCookie(jws, expiresIn));
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn, user));
    }

    @GetMapping("/all")
    public List<User> getalluser() {
        return iUser.findAll();
    }
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response, Principal principal) {

        String authToken = tokenHelper.getToken(request);
        Device device = deviceProvider.getCurrentDevice(request);
        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken, device);
            int expiresIn = tokenHelper.getExpiredIn(device);
            //  User user = (User) authentication.getPrincipal();
            // Add cookie to response
            response.addCookie(createAuthCookie(refreshedToken, expiresIn));
            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, null));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    private Cookie createAuthCookie(String jwt, int expiresIn) {
        Cookie authCookie = new Cookie(tokenHelper.AUTH_COOKIE, (jwt));
        authCookie.setPath("/");
        authCookie.setHttpOnly(true);
        authCookie.setMaxAge(expiresIn);
        return authCookie;
    }

    String hash(String password) {


        String hashedPassword = null;
        int i = 0;
        while (i < 5) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(password);
            i++;
        }

        return hashedPassword;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public String sendMail(@RequestBody Mail mail) {
        System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
        mail.setFrom("essidnissaf@gmail.com");
        mail.setTo(mail.getTo());
        mail.setSubject(mail.getSubject());
        mail.setContent(mail.getContent());
        emailService.sendSimpleMessage(mail);
        return "ok";
    }

    @PostMapping("/forgetpassword")
    public HashMap<String, String> resetPassword(String email, Device device) {
        HashMap message = new HashMap();
        User userexisting = iUser.findUserByEmail(email);
        if (userexisting == null) {
            message.put("user", "user not found");
            return message;
        }
        //String token1 = tokenHelper.generateToken(userexisting, device);
        //String token=token1.substring(0,20);
        UUID token = UUID.randomUUID();
        userexisting.setPasswordResetToken(token.toString());
        userexisting.setId(userexisting.getId());
        Mail mail = new Mail();
        mail.setContent("votre nouveau token est " + "http://localhost:8084/users/savePassword?token=");
        mail.setFrom("jlassinagi@gmail.com");
        mail.setTo(userexisting.getEmail());
        mail.setSubject("Reset password");
        emailService.sendSimpleMessage(mail);
        iUser.saveAndFlush(userexisting);
        message.put("user", "user found and email is send");

        return message;

    }

    @PostMapping("/savePassword/{resetLink}")
    public HashMap<String,String> savePassword(@PathVariable String resetLink,String newPassword) {

        User userexisting = iUser.findUserByResetLink(resetLink);
        HashMap message = new HashMap();

        if (userexisting != null) {
            userexisting.setId(userexisting.getId());
            userexisting.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userexisting.setEnabled(true);
            userexisting.setPasswordResetToken(null);
            iUser.save(userexisting);
            message.put("resetpassword", "proccesed");
            return message;

        } else {
            message.put("resetpassword", "failed");
            return message;

        }



    }
}