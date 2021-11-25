package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UniteOrganisationnelle {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String code;
    @OneToMany(mappedBy = "unite")
    private Collection<User> users;

    @ManyToOne
    @JoinColumn(name="etablissement_id")
    private Etablisement etablisement;






    @JsonIgnore
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }




    public Etablisement getEtablisement() {
        return etablisement;
    }

    public void setEtablisement(Etablisement etablisement) {
        this.etablisement = etablisement;
    }





}
