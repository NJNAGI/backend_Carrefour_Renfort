package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.DemmandeRepository;
import com.carrefour.renfortapp.dao.affectationRepository;
import com.carrefour.renfortapp.dao.interimaireRepository;
import com.carrefour.renfortapp.models.AffectationInterimaire;
import com.carrefour.renfortapp.models.Demande;
import com.carrefour.renfortapp.models.Interimaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/affectation")

public class AffectationController {
    @Autowired
    private affectationRepository iaffectation;

    @Autowired
    private DemmandeRepository idemande;

    @Autowired
    private interimaireRepository iinterimaire;

    @GetMapping("/all")
    public List<AffectationInterimaire> getAllaffectation() {
        return iaffectation.findAll();
    }

    @PostMapping("/save/{iddemande}/{idinterimaire}")
    public AffectationInterimaire saveaffectation(@RequestBody AffectationInterimaire a,@PathVariable Long iddemande,@PathVariable Long idinterimaire) {
        Demande d= idemande.findOne(iddemande);
        a.setD(d);
        Interimaire i=iinterimaire.findOne(idinterimaire);
        a.setInterimaire(i);
        return iaffectation.save(a);
    }

    @PutMapping("/update/{Id}")
    public AffectationInterimaire update(@RequestBody AffectationInterimaire a, @PathVariable Long Id) {
        a.setId(Id);
        return iaffectation.saveAndFlush(a);
    }
    @PutMapping("/traiter/{Id}")
    public AffectationInterimaire traiter(@PathVariable Long Id){
        AffectationInterimaire p= iaffectation.findOne( Id );
        p.setStatus( "Valider" );
        // p.setMoispaiement(p.getMoispaiement());
        return iaffectation.saveAndFlush(p);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteAffectation(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            iaffectation.delete(Id);
            message.put("etat","affectation deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","affectation not deleted");
            return message;
        }
    }

}
