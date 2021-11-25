package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.EtablisementRepository;
import com.carrefour.renfortapp.dao.budgetRepository;
import com.carrefour.renfortapp.models.Budget;
import com.carrefour.renfortapp.models.Etablisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/budget")
public class BudgetController {

    @Autowired
    private budgetRepository ibudget;
    @Autowired
    private EtablisementRepository ietab;


    @GetMapping("/all")
    public List<Budget> getAllbudget() {
        return ibudget.findAll();
    }

    @PostMapping("/save/{idEtab}")
    public Budget savebudget(@RequestBody Budget b,@PathVariable Long idEtab) {
        Etablisement e= ietab.findOne(idEtab);
        b.setEtablisement(e);
        return ibudget.save(b);
    }

    @PostMapping("/save")
    public Budget savebudget1(@RequestBody Budget b) {
        return ibudget.save(b);
    }

    @PutMapping("/update/{Id}")
    public Budget update(@RequestBody Budget b, @PathVariable Long Id) {
        b.setId(Id);
        return ibudget.saveAndFlush(b);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteBudget(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            ibudget.delete(Id);
            message.put("etat","budget deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","budget not deleted");
            return message;
        }
    }
}
