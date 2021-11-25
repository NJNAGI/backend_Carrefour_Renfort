package com.carrefour.renfortapp.models;

import javax.persistence.*;

@Entity
public class Pointage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String mois;
    private String annee;
    private String heuretravail;
    private String etat;
    private String moispaiement;
    private String coutreel;
    private String anneepaiement;
    private String date_locage;
    private String motif_blocage;
    private String recommander;
    private String tempsattente;
    private String conformiteprofile;
    private float TotalCout;

    @ManyToOne
    @JoinColumn(name="id_interimaire")
    private Interimaire interimaire;

    @ManyToOne
    @JoinColumn(name="id_demande")
    private Demande demande;

    public Interimaire getInterimaire() {
        return interimaire;
    }

    public void setInterimaire(Interimaire interimaire) {
        this.interimaire = interimaire;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public long getId() {
        return id;
    }

    public String getMois() {
        return mois;
    }

    public String getAnnee() {
        return annee;
    }

    public String getHeuretravail() {
        return heuretravail;
    }

    public String getEtat() {
        return etat;
    }

    public String getMoispaiement() {
        return moispaiement;
    }

    public String getCoutreel() {
        return coutreel;
    }

    public String getAnneepaiement() {
        return anneepaiement;
    }

    public String getDate_locage() {
        return date_locage;
    }

    public String getMotif_blocage() {
        return motif_blocage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setHeuretravail(String heuretravail) {
        this.heuretravail = heuretravail;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setMoispaiement(String moispaiement) {
        this.moispaiement = moispaiement;
    }

    public void setCoutreel(String coutreel) {
        this.coutreel = coutreel;
    }

    public void setAnneepaiement(String anneepaiement) {
        this.anneepaiement = anneepaiement;
    }

    public void setDate_locage(String date_locage) {
        this.date_locage = date_locage;
    }

    public void setMotif_blocage(String motif_blocage) {
        this.motif_blocage = motif_blocage;
    }

    public String getRecommander() {
        return recommander;
    }

    public void setRecommander(String recommander) {
        this.recommander = recommander;
    }

    public String getTempsattente() {
        return tempsattente;
    }

    public void setTempsattente(String tempsattente) {
        this.tempsattente = tempsattente;
    }

    public String getConformiteprofile() {
        return conformiteprofile;
    }

    public void setConformiteprofile(String conformiteprofile) {
        this.conformiteprofile = conformiteprofile;
    }


    public float getTotalCout() {
        return TotalCout;
    }

    public void setTotalCout(float totalCout) {
        TotalCout = totalCout;
    }
}
