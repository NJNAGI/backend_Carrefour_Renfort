package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.EtablisementRepository;
import com.carrefour.renfortapp.dao.SocieteRepository;
import com.carrefour.renfortapp.dao.budgetRepository;
import com.carrefour.renfortapp.models.Budget;
import com.carrefour.renfortapp.models.Etablisement;
import com.carrefour.renfortapp.models.Societe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value ="/users/etablisements")
public class EtablisementController {


    @Autowired
    EtablisementRepository   etablisementRepository;
    @Autowired
    SocieteRepository        societeRepository;
    @Autowired
    budgetRepository       budgetRepository;

    @PostMapping("/add/{code_societe}/{budget}")
    public Etablisement AddEtablisement(@RequestBody Etablisement etab, @PathVariable long code_societe,@PathVariable Long budget) {

        Societe s=societeRepository.findOne(code_societe);
        etab.setSociete(s);

        Budget b= budgetRepository.findOne(budget);
        etab.setBudget(b);
        return etablisementRepository.save(etab);

    }
    @PostMapping("/add1/{code_societe}")
    public Etablisement AddEtablisement1(@RequestBody Etablisement etab, @PathVariable long code_societe) {

        Societe s=societeRepository.findOne(code_societe);
        etab.setSociete(s);
        return etablisementRepository.save(etab);

    }
    @GetMapping("/all")
    public List<Etablisement> GetAllEtablisement(){
        return  etablisementRepository.findAll();

    }

    @PostMapping("/save")
    public Etablisement saveetab(@RequestBody Etablisement e) {
        return etablisementRepository.save(e);
    }

    @PutMapping("/update/{Id}")
    public Etablisement update(@RequestBody Etablisement e, @PathVariable Long Id) {
        e.setId(Id);
        return etablisementRepository.saveAndFlush(e);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteetab(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            etablisementRepository.delete(Id);
            message.put("etat","etablissement deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","etablissement not deleted");
            return message;
        }
    }



}