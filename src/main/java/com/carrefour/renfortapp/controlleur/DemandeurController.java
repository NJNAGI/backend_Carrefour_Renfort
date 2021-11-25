package com.carrefour.renfortapp.controlleur;


import com.carrefour.renfortapp.dao.demandeurRepository;
import com.carrefour.renfortapp.models.Demandeur;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/demandeur")
public class DemandeurController {
    @Autowired
    private demandeurRepository idemande;

    @GetMapping("/all")
    public List<Demandeur> getAlldemandeur() {
        return idemande.findAll();
    }

    @PostMapping("/save")
    public Demandeur savedemandeur(@RequestBody Demandeur d) {
        return idemande.save(d);
    }

    @PutMapping("/update/{Id}")
    public Demandeur update(@RequestBody Demandeur d, @PathVariable Long Id) {
        d.setId(Id);
        return idemande.saveAndFlush(d);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deletedemandeur(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            idemande.delete(Id);
            message.put("etat","demandeur deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","demandeur not deleted");
            return message;
        }
    }

}
