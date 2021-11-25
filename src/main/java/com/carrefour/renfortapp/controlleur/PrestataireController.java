package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.SocieteRepository;
import com.carrefour.renfortapp.dao.coutRepository;
import com.carrefour.renfortapp.dao.prestataireRepository;
import com.carrefour.renfortapp.models.Couthoraire;
import com.carrefour.renfortapp.models.Prestataire;
import com.carrefour.renfortapp.models.Societe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/prestataire")
public class PrestataireController {
    @Autowired
    private prestataireRepository iprestataire;
    @Autowired
    private coutRepository icout;
    @Autowired
    private SocieteRepository isoc;
    @GetMapping("/all")
    public List<Prestataire> getAllprestataire() {
        return iprestataire.findAll();
    }

    @PostMapping("/save1/{idcout}/{idsoc}")
    public Prestataire saveprestataire1(@RequestBody Prestataire p,@PathVariable Long idcout,@PathVariable Long idsoc) {
        Couthoraire  c =icout.findOne(idcout);
        p.setCout(c);
        Societe s =isoc.findOne(idsoc);
        p.setSociete(s);
        return iprestataire.save(p);
    }

    @PostMapping("/save")
    public Prestataire saveprestataire(@RequestBody Prestataire p) {
        return iprestataire.save(p);
    }

    @PutMapping("/update/{Id}")
    public Prestataire update(@RequestBody Prestataire p, @PathVariable Long Id) {
        p.setId(Id);
        return iprestataire.saveAndFlush(p);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteprestataire(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            iprestataire.delete(Id);
            message.put("etat","prestataire deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","prestataire not deleted");
            return message;
        }
    }

}