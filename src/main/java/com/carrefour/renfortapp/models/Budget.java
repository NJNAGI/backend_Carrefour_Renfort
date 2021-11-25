package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String annee;
    private String perimetre;
    private String montant;

    @OneToOne(mappedBy="budget")
    private Etablisement etablisement;

    @JsonIgnore
    public Etablisement getEtablisement() {
        return etablisement;
    }

    public void setEtablisement(Etablisement etablisement) {
        this.etablisement = etablisement;
    }

    public long getId() {
        return id;
    }

    public String getAnnee() {
        return annee;
    }

    public String getPerimetre() {
        return perimetre;
    }

    public String getMontant() {
        return montant;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setPerimetre(String perimetre) {
        this.perimetre = perimetre;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
}
