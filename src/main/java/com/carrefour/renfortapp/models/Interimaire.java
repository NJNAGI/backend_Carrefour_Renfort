package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Interimaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String cin;
    private String nom;
    private String prenom;
    private String date_blocage;
    private String motif_blocage;
    private String heuretravail;

    @OneToMany(mappedBy ="interimaire")
    private Collection<Pointage> pointages;

    @OneToMany(mappedBy = "interimaire")
    private Collection<AffectationInterimaire> affectations;


    @ManyToOne
    @JoinColumn(name="code_prestataire")
    private Prestataire prestataire;



    public long getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    @JsonIgnore
    public Collection<Pointage> getPointages() {
        return pointages;
    }

    public void setPointages(Collection<Pointage> pointages) {
        this.pointages = pointages;
    }
    @JsonIgnore
    public Collection<AffectationInterimaire> getAffectations() {
        return affectations;
    }

    public void setAffectations(Collection<AffectationInterimaire> affectations) {
        this.affectations = affectations;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    public String getPrenom() {
        return prenom;
    }


    public String getDate_blocage() {
        return date_blocage;
    }

    public String getMotif_blocage() {
        return motif_blocage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_blocage(String date_blocage) {
        this.date_blocage = date_blocage;
    }

    public void setMotif_blocage(String motif_blocage) {
        this.motif_blocage = motif_blocage;
    }

    public String getHeuretravail() {
        return heuretravail;
    }

    public void setHeuretravail(String heuretravail) {
        this.heuretravail = heuretravail;
    }
}
