package com.carrefour.renfortapp.models;

import javax.persistence.*;

@Entity
public class Couthoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long   id;
    private String cout;

    @OneToOne(mappedBy = "cout")
    private Prestataire pres;

    public long getId() {
        return id;
    }

    public String getCout() {
        return cout;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }
}