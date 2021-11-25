package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.dao.EtablisementRepository;
import com.carrefour.renfortapp.dao.Unite_OrgRepository;
import com.carrefour.renfortapp.models.Etablisement;
import com.carrefour.renfortapp.models.UniteOrganisationnelle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/users/uniteOrg")
public class UniteOrgController {

    @Autowired
    EtablisementRepository     etablisementRepository;
    @Autowired
    Unite_OrgRepository        unite_orgRepository;

    @PostMapping("/add/{id_etablis}")
    public UniteOrganisationnelle addUOrg(@RequestBody UniteOrganisationnelle uniteOrganisationnelle, @PathVariable Long id_etablis) {
        // souscategorie.setCategorie(categorieRepository.getOne(idc));
        //  uniteOrganisationnelle.setEtablisement(etablisementRepository.getOne(id_etablis));
        Etablisement e= etablisementRepository.findOne(id_etablis);
        uniteOrganisationnelle.setEtablisement(e);
        return unite_orgRepository.save(uniteOrganisationnelle);
    }
    @GetMapping("/all")
    public List<UniteOrganisationnelle> GetAllUniteOrg(){
        return  unite_orgRepository.findAll();
    }

    @PutMapping("/update/{Id}")
    public UniteOrganisationnelle update(@RequestBody UniteOrganisationnelle u, @PathVariable Long Id) {
        u.setId(Id);
        return unite_orgRepository.saveAndFlush(u);
    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteunite(@PathVariable(value = "Id") Long Id) {
        HashMap message= new HashMap();
        try{
            unite_orgRepository.delete(Id);
            message.put("etat","unite organisationnelle deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","unite organisationnelle not deleted");
            return message;
        }
    }


}