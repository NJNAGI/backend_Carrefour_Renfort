package com.carrefour.renfortapp.Controlleur;

import com.carrefour.renfortapp.dao.DemmandeRepository;
import com.carrefour.renfortapp.dao.NotificationRepository;
import com.carrefour.renfortapp.models.Demande;
import com.carrefour.renfortapp.models.Notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class NotificationController {
    @Autowired
    private NotificationRepository inotification;

    @Autowired
    private DemmandeRepository idemande;



    @PostMapping("/notification/{iddemande}")
    public Notification notification(@PathVariable Long iddemande,@RequestBody Notification notif) {
        Demande d= idemande.findOne(iddemande);
        notif.setDemande(d);
        return inotification.saveAndFlush(notif);
    }
    @GetMapping("/notification/all")
    public List<Notification> getAllnotification() {
        return inotification.findAll();
    }
}
