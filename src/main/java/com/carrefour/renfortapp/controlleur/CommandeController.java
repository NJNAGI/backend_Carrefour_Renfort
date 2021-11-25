package com.carrefour.renfortapp.controlleur;


import com.carrefour.renfortapp.dao.DemmandeRepository;
import com.carrefour.renfortapp.dao.commandeRepository;
import com.carrefour.renfortapp.dao.prestataireRepository;
import com.carrefour.renfortapp.models.Commande;
import com.carrefour.renfortapp.models.Demande;
import com.carrefour.renfortapp.models.Prestataire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/commande")

public class CommandeController {

    @Autowired
    private commandeRepository icommande;
    @Autowired
    private DemmandeRepository idemande;
    @Autowired
    private prestataireRepository iprestataire;

    @GetMapping("/all")
    public List<Commande> getAllcommande() {
        return icommande.findAll();
    }

    @PostMapping("/save/{iddemande}/{idprest}")
    public Commande savecommande(@RequestBody Commande c,@PathVariable Long iddemande,@PathVariable Long idprest) {
        Demande d= idemande.findOne(iddemande);
        c.setDemande(d);
        Prestataire p=iprestataire.findOne(idprest);
        c.setPrestataire(p);
        return icommande.save(c);
    }

    @GetMapping ("/GetOne/{id}")
    public Commande getOnecommande(@PathVariable Long id){

        return icommande.findOne(id);
    }
    @PutMapping("/update/{Id}")
    public Commande update(@RequestBody Commande c, @PathVariable Long Id) {
        c.setId(Id);
        return icommande.saveAndFlush(c);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteCommande(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            icommande.delete(Id);
            message.put("etat","commande deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","commande not deleted");
            return message;
        }
    }
    @PutMapping("/traiter/{id}")
    public Commande traiter(@RequestBody long x,@PathVariable long id){
        Commande c= icommande.findOne( id );
        c.setSommeheure(x);
        return icommande.saveAndFlush(c);
    }



    @PutMapping("/updatecout/{Id}/{idprestataire}")
    public Commande updatecout( @PathVariable Long Id,@PathVariable Long idprestataire) {

        Prestataire prest= iprestataire.findOne(idprestataire);
        Commande c= icommande.findOne(Id);

        int result = Integer.parseInt(prest.getCout().getCout());
        int result2 = icommande.sumQuantities();
        System.out.println("CommandeController.updatecout" + result2);
        int result3 = result*result2;
        System.out.println("multipleeeeeee" + result3);
        c.setMoyencout(result3);
        return icommande.save(c);
    }
}
