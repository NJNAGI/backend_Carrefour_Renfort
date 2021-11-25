package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.h1Repository;
import com.carrefour.renfortapp.models.H1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/h1")
public class H1Controller {
    @Autowired
    private h1Repository ih1;

    @GetMapping("/all")
    public List<H1> getAllh1() {
        return ih1.findAll();
    }

    @PostMapping("/save")
    public H1 saveh1(@RequestBody H1 h) {
        return ih1.save(h);
    }

    @PutMapping("/update/{Id}")
    public H1 update(@RequestBody H1 h, @PathVariable Long Id) {
        h.setId(Id);
        return ih1.saveAndFlush(h);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteh1(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            ih1.delete(Id);
            message.put("etat","h1 deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","h1 not deleted");
            return message;
        }
    }

}