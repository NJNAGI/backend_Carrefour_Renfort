package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Societe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code_societe;  //interfaçage avec la base oracle des  données personnel !
    private String nom;

    @OneToMany(mappedBy = "societe")
    private Collection<Etablisement> etablisements;

    @OneToMany(mappedBy = "societe")
    private Collection<Prestataire> prestataires;

    @OneToMany(mappedBy = "societe")
    private Collection<User> users;

    @OneToMany(mappedBy = "societe")
    private Collection<Demande> demandes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode_societe() {
        return code_societe;
    }

    public void setCode_societe(String code_societe) {
        this.code_societe = code_societe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    @JsonIgnore
    public Collection<Etablisement> getEtablisements() {
        return etablisements;
    }

    public void setEtablisements(Collection<Etablisement> etablisements) {
        this.etablisements = etablisements;
    }

    @JsonIgnore
    public Collection<Prestataire> getPrestataires() {
        return prestataires;
    }

    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setPrestataires(Collection<Prestataire> prestataires) {
        this.prestataires = prestataires;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void setDemandes(Collection<Demande> demandes) {
        this.demandes = demandes;
    }

    @JsonIgnore
    public Collection<Demande> getDemandes() {
        return demandes;
    }
}
