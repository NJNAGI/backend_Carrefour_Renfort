package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.DemmandeRepository;
import com.carrefour.renfortapp.dao.*;
import com.carrefour.renfortapp.models.GRH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/grh")
public class GRHController {
    @Autowired
    private grhReposiroty igrh;

    @Autowired
    private DemmandeRepository idemande;



    @GetMapping("/all")
    public List<GRH> getAllgrh() {
        return igrh.findAll();
    }

    @PostMapping("/save")
    public GRH saveh2(@RequestBody GRH h) {
        return igrh.save(h);
    }

    @PutMapping("/update/{Id}")
    public GRH update(@RequestBody GRH h, @PathVariable Long Id) {
        h.setId(Id);
        return igrh.saveAndFlush(h);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deletegrh(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            igrh.delete(Id);
            message.put("etat","grh deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","grh not deleted");
            return message;
        }
    }

}