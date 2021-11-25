package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.SocieteRepository;
import com.carrefour.renfortapp.models.Societe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/societes")
public class SocieteController {



    @Autowired
    SocieteRepository societeRepository;
    @PostMapping("/add")
    public Societe addSociete(@RequestBody Societe  societe){
        return societeRepository.save(societe);
    }
    @GetMapping("/all")
    public List<Societe> getall(){
        return  societeRepository.findAll();
    }
    @PutMapping("/update/{Id}")
    public Societe update(@RequestBody Societe s, @PathVariable Long Id) {
        s.setId(Id);
        return societeRepository.saveAndFlush(s);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deletesociete(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            societeRepository.delete(Id);
            message.put("etat","societe deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","societe not deleted");
            return message;
        }
    }



}
