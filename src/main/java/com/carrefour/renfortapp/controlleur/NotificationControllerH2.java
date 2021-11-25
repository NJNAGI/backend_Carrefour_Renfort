package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.DemmandeRepository;
import com.carrefour.renfortapp.dao.NotificationH2Repository;
import com.carrefour.renfortapp.models.Demande;
import com.carrefour.renfortapp.models.NotificationH2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class NotificationControllerH2 {

    @Autowired
    private NotificationH2Repository inotif;

    @Autowired
    private DemmandeRepository idemande;



    @PostMapping("/notificationh2/{iddemande}")
    public NotificationH2 notification(@PathVariable Long iddemande, @RequestBody NotificationH2 notif) {
        Demande d= idemande.findOne(iddemande);
        notif.setDemande(d);
        return inotif.saveAndFlush(notif);
    }
    @GetMapping("/notificationh2/all")
    public List<NotificationH2> getAllnotification() {
        return inotif.findAll();
    }
}
