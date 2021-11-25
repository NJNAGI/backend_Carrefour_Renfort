package com.carrefour.renfortapp.models;

import javax.persistence.Entity;

@Entity
public class H1 extends User{
    private String cin;

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
}

