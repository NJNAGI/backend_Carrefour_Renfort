package com.carrefour.renfortapp.models;

import javax.persistence.*;
@Entity
public class NotificationH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;

    @OneToOne
    @JoinColumn(name="ref_demande")
    private Demande demande;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
