package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Prestataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String code_prestataire;
    private String siege_social;
    private Number tel;
    private String email;
    private String Fax;
    private String date_signature;
    private String fin_convention;
    private String site;


    @ManyToOne
    @JoinColumn(name="code_societe")
    private Societe societe;

    @OneToMany(mappedBy = "prestataire")
    private Collection<Commande> commandes;

    //@OneToOne(mappedBy = "pres")
    //private Couthoraire cout;

    @OneToOne
    @JoinColumn(name="id_cout")
    private Couthoraire cout;

    @OneToMany(mappedBy = "prestataire")
    private Collection<Interimaire> interimaires;


    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
    @JsonIgnore
    public Collection<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Collection<Commande> commandes) {
        this.commandes = commandes;
    }

    public Couthoraire getCout() {
        return cout;
    }

    public void setCout(Couthoraire cout) {
        this.cout = cout;
    }
    @JsonIgnore
    public Collection<Interimaire> getInterimaires() {
        return interimaires;
    }

    public void setInterimaires(Collection<Interimaire> interimaires) {
        this.interimaires = interimaires;
    }

    public long getId() {
        return id;
    }

    public String getCode_prestataire() {
        return code_prestataire;
    }


    public String getSiege_social() {
        return siege_social;
    }

    public Number getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return Fax;
    }

    public String getDate_signature() {
        return date_signature;
    }

    public String getFin_convention() {
        return fin_convention;
    }

    public String getSite() {
        return site;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCode_prestataire(String code_prestataire) {
        this.code_prestataire = code_prestataire;
    }

    public void setSiege_social(String siege_social) {
        this.siege_social = siege_social;
    }

    public void setTel(Number tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public void setDate_signature(String date_signature) {
        this.date_signature = date_signature;
    }

    public void setFin_convention(String fin_convention) {
        this.fin_convention = fin_convention;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
