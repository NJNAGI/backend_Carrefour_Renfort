package com.carrefour.renfortapp.models;


import javax.persistence.*;

@Entity
public class AffectationInterimaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String titre;
    private String date_affectation;
    private String date_fin_mission;
    private String heure;
    private String status;

    @ManyToOne
    @JoinColumn(name="interimaire")
    private Interimaire interimaire;

    @ManyToOne
    @JoinColumn(name="ref_demande")
    private Demande d;

    public Interimaire getInterimaire() {
        return interimaire;
    }

    public void setInterimaire(Interimaire interimaire) {
        this.interimaire = interimaire;
    }

    public Demande getD() {
        return d;
    }

    public void setD(Demande d) {
        this.d = d;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate_affectation() {
        return date_affectation;
    }

    public String getDate_fin_mission() {
        return date_fin_mission;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }



    public void setDate_affectation(String date_affectation) {
        this.date_affectation = date_affectation;
    }

    public void setDate_fin_mission(String date_fin_mission) {
        this.date_fin_mission = date_fin_mission;
    }
}
