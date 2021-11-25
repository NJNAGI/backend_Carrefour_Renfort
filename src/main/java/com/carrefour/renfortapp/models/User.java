package com.carrefour.renfortapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable, UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String firstName;
    private String lastName;
    private String image;
    private String matricule;
    private String poste;
    private String niveau_hierarchique;
    private String email;
    private String PasswordResetToken;

    @ManyToOne
    @JoinColumn(name="id_etablisement")
    private Etablisement etab;

    @ManyToOne
    @JoinColumn(name="id_unite")
    private UniteOrganisationnelle unite;


    @ManyToOne
    @JoinColumn(name="code_societe")
    private Societe societe;


    @OneToMany(mappedBy = "user")
    private Collection<Demande> demandes;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPoste() {
        return poste;
    }

    public void setEtab(Etablisement etab) {
        this.etab = etab;
    }

    public void setUnite(UniteOrganisationnelle unite) {
        this.unite = unite;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public void setDemandes(Collection<Demande> demandes) {
        this.demandes = demandes;
    }
    public Etablisement getEtab() {
        return etab;
    }

    public UniteOrganisationnelle getUnite() {
        return unite;
    }

    public Societe getSociete() {
        return societe;
    }
    @JsonIgnore
    public Collection<Demande> getDemandes() {
        return demandes;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

//    public String getSociete() {
//        return societe;
//    }
//
//    public void setSociete(String societe) {
//        this.societe = societe;
//    }

//    public Etablisement getEtablisement() {
//        return etablisement;
//    }
//
//    public void setEtablisement(Etablisement etablisement) {
//        this.etablisement = etablisement;
//    }

//    public String getUnite_organisationnelle() {
//        return unite_organisationnelle;
//    }
//
//    public void setUnite_organisationnelle(String unite_organisationnelle) {
//        this.unite_organisationnelle = unite_organisationnelle;
//    }

    public String getNiveau_hierarchique() {
        return niveau_hierarchique;
    }

    public void setNiveau_hierarchique(String niveau_hierarchique) {
        this.niveau_hierarchique = niveau_hierarchique;
    }

    @Column(name = "username", unique = true)
    private String username;

    //@JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;


    @ManyToOne
    private Authority authorities;

    @Column(name = "enabled")
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public String getPasswordResetToken() {
        return PasswordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        PasswordResetToken = passwordResetToken;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Authority> auth = new ArrayList<Authority>();
        auth.add(authorities);
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) { this.lastPasswordResetDate = lastPasswordResetDate; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Authority authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

