package com.carrefour.renfortapp.controlleur;


import com.carrefour.renfortapp.dao.coutRepository;
import com.carrefour.renfortapp.models.Couthoraire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/cout")
public class CoutController {

    @Autowired
    private coutRepository icout;

    @GetMapping("/all")
    public List<Couthoraire> getAllcout() {
        return icout.findAll();
    }

    @PostMapping("/save")
    public Couthoraire savecout(@RequestBody Couthoraire c) {
        return icout.save(c);
    }

    @PutMapping("/update/{Id}")
    public Couthoraire update(@RequestBody Couthoraire c, @PathVariable Long Id) {
        c.setId(Id);
        return icout.saveAndFlush(c);
    }



    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deletecout(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            icout.delete(Id);
            message.put("etat","cout deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","cout not deleted");
            return message;
        }
    }

}

