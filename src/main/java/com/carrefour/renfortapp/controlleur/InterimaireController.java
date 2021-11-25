package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.interimaireRepository;
import com.carrefour.renfortapp.dao.prestataireRepository;

import com.carrefour.renfortapp.models.Interimaire;
import com.carrefour.renfortapp.models.Prestataire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/interimaire")
public class InterimaireController {

    @Autowired
    private interimaireRepository iinterimaire;
    @Autowired
    private prestataireRepository iprestataire;

    @GetMapping("/all")
    public List<Interimaire> getAllinterimaire() {
        return iinterimaire.findAll();
    }

    @PostMapping("/save")
    public Interimaire saveinterimaire(@RequestBody Interimaire i) {
        return iinterimaire.save(i);
    }

    @PostMapping("/saveheure/{idinterimaire}")
    public Interimaire saveinterimaire1(@PathVariable Long idinterimaire,@RequestBody String  heure) {
        Interimaire i=iinterimaire.findOne(idinterimaire);
        i.setHeuretravail(heure);
        return iinterimaire.save(i);
    }
    @PostMapping("/saveinterimmaire/{idprestataire}")
    public Interimaire saveinterimaire11(@PathVariable Long idprestataire,@RequestBody Interimaire  i) {
        Prestataire p=iprestataire.findOne(idprestataire);
        i.setPrestataire(p);
        return iinterimaire.save(i);
    }

    @PutMapping("/update/{Id}")
    public Interimaire update(@RequestBody Interimaire i, @PathVariable Long Id) {
        i.setId(Id);
        return iinterimaire.saveAndFlush(i);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteinterimaire(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            iinterimaire.delete(Id);
            message.put("etat","interimaire deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","interimaire not deleted");
            return message;
        }
    }

}
