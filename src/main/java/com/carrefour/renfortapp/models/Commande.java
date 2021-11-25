package com.carrefour.renfortapp.models;


import javax.persistence.*;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String mois;
    private String annee;
    private int heuretheorique;
    private String heurereel;
    private String depensetheorique;
    private String depensereel;
    private long sommeheure;
    private float moyencout;

    @ManyToOne
    @JoinColumn(name="code_prestataire")
    private Prestataire prestataire;

    @OneToOne
    @JoinColumn(name="ref_demande")
    private Demande demande;



    public long getId() {
        return id;
    }

    public String getMois() {
        return mois;
    }

    public String getAnnee() {
        return annee;
    }

    public int getHeuretheorique() {
        return heuretheorique;
    }

    public String getHeurereel() {
        return heurereel;
    }

    public String getDepensetheorique() {
        return depensetheorique;
    }

    public String getDepensereel() {
        return depensereel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public float getMoyencout() {
        return moyencout;
    }

    public void setMoyencout(float moyencout) {
        this.moyencout = moyencout;
    }

    public long getSommeheure() {
        return sommeheure;
    }

    public void setSommeheure(long sommeheure) {
        this.sommeheure = sommeheure;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setHeuretheorique(int heuretheorique) {
        this.heuretheorique = heuretheorique;
    }

    public void setHeurereel(String heurereel) {
        this.heurereel = heurereel;
    }

    public void setDepensetheorique(String depensetheorique) {
        this.depensetheorique = depensetheorique;
    }

    public void setDepensereel(String depensereel) {
        this.depensereel = depensereel;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
}
