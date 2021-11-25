package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.models.H2;
import com.carrefour.renfortapp.dao.h2Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/h2")
public class H2Controller {
    @Autowired
    private h2Repository ih2;

    @GetMapping("/all")
    public List<H2> getAllh2() {
        return ih2.findAll();
    }

    @PostMapping("/save")
    public H2 saveh2(@RequestBody H2 h) {
        return ih2.save(h);
    }

    @PutMapping("/update/{Id}")
    public H2 update(@RequestBody H2 h, @PathVariable Long Id) {
        h.setId(Id);
        return ih2.saveAndFlush(h);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteh2(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            ih2.delete(Id);
            message.put("etat","h2 deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","h2 not deleted");
            return message;
        }
    }

}