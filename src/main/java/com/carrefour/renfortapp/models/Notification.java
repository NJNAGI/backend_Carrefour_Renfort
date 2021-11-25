package com.carrefour.renfortapp.models;
import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String readable;
    private String avisH1;
    private String avisH2;
    @ManyToOne
    @JoinColumn(name="ref_demande")
    private Demande demande;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
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

    public void setId(long id) {
        this.id = id;
    }

    public String getAvisH1() {
        return avisH1;
    }

    public void setAvisH1(String avisH1) {
        this.avisH1 = avisH1;
    }

    public String getAvisH2() {
        return avisH2;
    }

    public void setAvisH2(String avisH2) {
        this.avisH2 = avisH2;
    }
}

