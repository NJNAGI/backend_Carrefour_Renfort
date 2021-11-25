package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.models.*;
import com.carrefour.renfortapp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/pointage")
public class PointageController {
    @Autowired
    private pointageRepository ipointage;
    @Autowired
    private DemmandeRepository idemande;
    @Autowired
    private interimaireRepository iinterimaire;
    @Autowired
    private coutRepository icout;
    @Autowired
    private prestataireRepository iprestataire;



    @GetMapping("/all")
    public List<Pointage> getAllpointage() {
        return ipointage.findAll();
    }

    @PostMapping("/save/{iddemande}/{idinterimaire}")
    public Pointage savepointage(@RequestBody Pointage p,@PathVariable Long iddemande,@PathVariable Long idinterimaire) {
        Demande d= idemande.findOne(iddemande);
        p.setDemande(d);
        Interimaire i= iinterimaire.findOne(idinterimaire);
        p.setInterimaire(i);
        return ipointage.save(p);
    }
    @PostMapping("/saveheure/{idinterimaire}")
    public Interimaire saveinterimaire1(@PathVariable Long idinterimaire,@RequestBody String  heure) {
        Interimaire i=iinterimaire.findOne(idinterimaire);
        i.setHeuretravail(heure);
        return iinterimaire.save(i);
    }
    @PutMapping("/update/{Id}")
    public Pointage update(@RequestBody Pointage p, @PathVariable Long Id) {
        p.setId(Id);
        return ipointage.saveAndFlush(p);
    }

    @PutMapping("/updatecout/{Id}/{idprestataire}")
    public Pointage updatecout( @PathVariable Long Id,@PathVariable Long idprestataire) {

        Prestataire prest= iprestataire.findOne(idprestataire);
        Pointage p= ipointage.findOne(Id);
        int result = Integer.parseInt(prest.getCout().getCout());
        int result2 = Integer.parseInt(p.getHeuretravail());
        int result3 = result*result2;
        p.setTotalCout(result3);
        return ipointage.save(p);
    }

    @PutMapping("/traiter/{Id}")
    public Pointage traiter(@PathVariable Long Id){
        Pointage p= ipointage.findOne( Id );
        p.setEtat( "Valider" );
        p.setMoispaiement(p.getMoispaiement());
        return ipointage.saveAndFlush(p);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deletepointage(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            ipointage.delete(Id);
            message.put("etat","pointage deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","pointage not deleted");
            return message;
        }
    }

}

