package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.*;
import com.carrefour.renfortapp.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/demands")

public class DemandeController {


    @Autowired
    private DemmandeRepository demmandeRepository;
    @Autowired
    private prestataireRepository prestatairerepository;

    @Autowired
    private IUser iuser;

    @Autowired
    private EtablisementRepository ietab;

    @Autowired
    private SocieteRepository isoc;



    // public static int getDaysCount(Date begin, Date end) {
    //   Calendar start = org.apache.commons.lang.time.DateUtils.toCalendar(begin);
    // start.set(Calendar.MILLISECOND, 0);
    // start.set(Calendar.SECOND, 0);
    // start.set(Calendar.MINUTE, 0);
    // start.set(Calendar.HOUR_OF_DAY, 0);

    //Calendar finish = org.apache.commons.lang.time.DateUtils.toCalendar(end);
    // finish.set(Calendar.MILLISECOND, 999);
    // finish.set(Calendar.SECOND, 59);
    // finish.set(Calendar.MINUTE, 59);
    // finish.set(Calendar.HOUR_OF_DAY, 23);

    // long delta = finish.getTimeInMillis() - start.getTimeInMillis();
    // return (int) Math.ceil(delta / (1000.0 * 60 * 60 * 24));
    // }







    @PostMapping("/add/{idUser}/{idEtab}/{codeSoc}")
    public Demande AddDemande(@RequestBody Demande demande, @PathVariable Long idUser, @PathVariable Long idEtab, @PathVariable Long codeSoc) {
        User us= iuser.findOne(idUser);
        demande.setUser(us);
        Etablisement e= ietab.findOne(idEtab);
        demande.setEtab(e);
        Societe s=isoc.findOne(codeSoc);
        demande.setSociete(s);
        //  demande.setDate_creation(new Date());


        return demmandeRepository.save(demande);
    }
    @GetMapping("/send")
    public void senddemande(){

    }

    @GetMapping("/all")
    public List<Demande> GetAll() {
        return demmandeRepository.findAll();
    }

    @GetMapping ("/GetOne/{id}")
    public Demande getOneDemande(@PathVariable Long id){

        return demmandeRepository.findOne(id);
    }
    @PutMapping("/update")
    public Demande Updatedemande(@RequestBody Demande demande) {
        return demmandeRepository.saveAndFlush(demande); //Ajout et modification
    }


    @PutMapping("/donneravisH1/{id}")
    public Demande Avisdemande(@RequestBody String avis,@PathVariable Long id) {
        Demande us= demmandeRepository.findOne(id);
        us.setAvisH1(avis);
        return demmandeRepository.saveAndFlush(us); //Ajout et modification
    }

    @PutMapping("/donneravisH2/{id}")
    public Demande Avisdemande2(@RequestBody String avis,@PathVariable Long id) {
        Demande us= demmandeRepository.findOne(id);
        us.setAvisH2(avis);
        return demmandeRepository.saveAndFlush(us); //Ajout et modification
    }
    @DeleteMapping("/delete/{id}")
    public HashMap deleteDemande(@PathVariable Long id){

        HashMap<String, String> hashMap = new HashMap<>();
        try{
            demmandeRepository.delete(id);
            hashMap.put("state","Ok");
            return  hashMap;
        }catch(Exception e){
            hashMap.put("state","No");
            return hashMap;

        }
    }
    //

//    ///pathVariable
//    @GetMapping ("/one2/{id}")
//    public Optional<Demande> getOneCat2(@PathVariable Long id){
//        return demmandeRepository.findById(id);
//    }

//    @GetMapping ("/one3")
//    public Optional<Demande> getOneCat3(Long id){
//        return demmandeRepository.findById(id);
//    }


//    @PostMapping("/add")
//    public Demande AddCategorie(@RequestBody Demande categorie) {
//        return categorieRepository.save(categorie);
//    }
//

//
//    @DeleteMapping ("/delete/{id}")
//    public Response DeleteCategorie(@PathVariable  Long id){
//        Response res =new Response();
//        try {
//            categorieRepository.deleteById(id);
//            res.setState("ok");
//            return res;
//        }
//        catch (Exception e){
//            res.setState("fail");
//            return  res;
//        }
//    }
    ////
//@GetMapping ("/nbj")
//public float getnbjr() {
//    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    //  float res=0;
    // try {
    //     Date dateAvant = sdf.parse("01/03/2021");
    //     Date dateApres = sdf.parse("01/07/2021");
    //    long diff = dateApres.getTime() - dateAvant.getTime();
    //    res = (diff / (1000*60*60*24));
    //   System.out.println("Nombre de jours entre les deux dates est: "+res);
    //  } catch (Exception e) {
    //     e.printStackTrace();
    //  }

    // return  res;
//}

    @GetMapping("/nb")
    public long daysBetween() {

        //24-May-2017, change this to your desired Start Date
        LocalDate dateBefore = LocalDate.of(2021, Month.MAY, 24);
        //29-July-2017, change this to your desired End Date
        LocalDate dateAfter = LocalDate.of(2021, Month.JULY, 24);
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        System.out.println(noOfDaysBetween);
        return noOfDaysBetween;

    }
    @PutMapping("/nb/{iddemande}")
    public Demande daysBetween1(@PathVariable Long iddemande) {
        Demande us= demmandeRepository.findOne(iddemande);

        System.out.println("erre"+us);
        LocalDate dateBefore =   LocalDate.parse(us.getDebutDeLaMission());
        LocalDate dateAfter = LocalDate.parse(us.getFinDeLaMission());


        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore,dateAfter);
        System.out.println(noOfDaysBetween);
        // us.setNbrejrepos((double) noOfDaysBetween);
        float result = (float) noOfDaysBetween/ 7;

        us.setNbrejrepos((long)(result*us.getEffectifdemande()));
        long nbre_jours_repos=(long)(result*us.getEffectifdemande());
        long nbre_jours_theorique= (noOfDaysBetween * us.getEffectifdemande()) - nbre_jours_repos;
        us.setNbrejtheorique(nbre_jours_theorique);

        //  us.setNbrehtheorique( nbre_jours_theorique *2);

        System.out.println("DemandeController.daysBetween" + us.getRegimeHoraire());
        System.out.println("equal" + us.getRegimeHoraire().equals("48h".toString()));
        if(us.getRegimeHoraire().toString().equals("48h".toString())){
            us.setNbrehtheorique( nbre_jours_theorique *8);
            return demmandeRepository.saveAndFlush(us);

        }
        else {
            us.setNbrehtheorique( nbre_jours_theorique *5);
        }

        return demmandeRepository.saveAndFlush(us);
    }
    @PutMapping("/nbj/{iddemande}/{idprestataire}")
    public Demande daysBetween(@PathVariable Long iddemande,@PathVariable Long idprestataire) {
        Demande us= demmandeRepository.findOne(iddemande);
        Prestataire p= prestatairerepository.findOne(idprestataire);

        System.out.println("erre"+us);
        LocalDate dateBefore =   LocalDate.parse(us.getDebutDeLaMission());
        LocalDate dateAfter = LocalDate.parse(us.getFinDeLaMission());


        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore,dateAfter);
        System.out.println(noOfDaysBetween);
        // us.setNbrejrepos((double) noOfDaysBetween);
        float result = (float) noOfDaysBetween/ 7;

        us.setNbrejrepos((long)(result*us.getEffectifdemande()));
        long nbre_jours_repos=(long)(result*us.getEffectifdemande());
        long nbre_jours_theorique= (noOfDaysBetween * us.getEffectifdemande()) - nbre_jours_repos;
        us.setNbrejtheorique(nbre_jours_theorique);

        int result1 = Integer.parseInt(p.getCout().getCout());
        //  us.setNbrehtheorique( nbre_jours_theorique *2);

        System.out.println("DemandeController.daysBetween" + us.getRegimeHoraire());
        System.out.println("equal" + us.getRegimeHoraire().equals("48h".toString()));
        if(us.getRegimeHoraire().toString().equals("48h".toString())){
            us.setNbrehtheorique( nbre_jours_theorique *8);
            us.setDepensetheorique(result1*nbre_jours_theorique*8);
            return demmandeRepository.saveAndFlush(us);

        }
        else {
            us.setNbrehtheorique( nbre_jours_theorique *3);
            us.setDepensetheorique(result1*nbre_jours_theorique*3);
        }

        return demmandeRepository.saveAndFlush(us);
    }
    // @PutMapping("/n/{id}")
    //  public Demande n(@RequestBody long x,@PathVariable Long id) {
    //   Demande us= demmandeRepository.findOne(id);
    //   us.setNbrejrepos(x);
    //   return demmandeRepository.saveAndFlush(us); //Ajout et modification
    // }
}
