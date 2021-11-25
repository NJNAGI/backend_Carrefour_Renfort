package com.carrefour.renfortapp.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Demande  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reference;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String date_creation ;
    private String poste;
    private String demandeur;
    private long effectifdemande;
    private String profil;
    private String sexe;
    private String natureDedemande;
    private String motif;
    private String natureDeMission;
    private String regimeHoraire; //48H
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String DebutDeLaMission;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String FinDeLaMission;
    private String personneAcontacter;
    private String heureDebut;
    private String avisH1;
    private String avisH2;
    private long nbrejrepos;
    private long nbrejtheorique;
    private long nbrehtheorique;
    private long depensetheorique;

    //private String Status;
    // private String avis_hierarchie; //Accord ou nn
    //private String etat; /// En cours, Acceptée (H1)

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;


    @ManyToOne
    @JoinColumn(name="code_societe")
    private Societe societe;


    @ManyToOne
    @JoinColumn(name="id_etab")
    private Etablisement etab;

    @OneToOne(mappedBy = "demande")
    private Commande commande;


    @OneToMany(mappedBy = "demande")
    private Collection<Notification> notifications;

    @OneToOne(mappedBy = "demande")
    private NotificationH2 notificationH2;

    @OneToMany(mappedBy = "demande")
    private Collection<Pointage> pointage;

    @OneToMany(mappedBy="d")
    private Collection<AffectationInterimaire> affectations;

    @JsonIgnore
    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }

    @JsonIgnore
    public Collection<Pointage> getPointage() {
        return pointage;
    }

    public void setPointage(Collection<Pointage> pointage) {
        this.pointage = pointage;
    }

    @JsonIgnore
    public Collection<AffectationInterimaire> getAffectations() {
        return affectations;
    }

    public void setAffectations(Collection<AffectationInterimaire> affectations) {
        this.affectations = affectations;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public long getNbrehtheorique() {
        return nbrehtheorique;
    }

    public void setNbrehtheorique(long nbrehtheorique) {
        this.nbrehtheorique = nbrehtheorique;
    }

    public long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getPoste() {
        return poste;
    }

    public long getEffectifdemande() {
        return effectifdemande;
    }

    public long getNbrejtheorique() {
        return nbrejtheorique;
    }

    public void setNbrejtheorique(long nbrejtheorique) {
        this.nbrejtheorique = nbrejtheorique;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public String getProfil() {
        return profil;
    }

    public String getSexe() {
        return sexe;
    }

    public String getNatureDedemande() {
        return natureDedemande;
    }

    public String getMotif() {
        return motif;
    }



    public String getNatureDeMission() {
        return natureDeMission;
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

    public String getRegimeHoraire() {
        return regimeHoraire;
    }

    public String getDebutDeLaMission() {
        return DebutDeLaMission;
    }

    public String getFinDeLaMission() {
        return FinDeLaMission;
    }

    public String getPersonneAcontacter() {
        return personneAcontacter;
    }

    public String getHeureDebut() {
        return heureDebut;
    }
    @JsonIgnore
    public NotificationH2 getNotificationH2() {
        return notificationH2;
    }

    public void setNotificationH2(NotificationH2 notificationH2) {
        this.notificationH2 = notificationH2;
    }

    public User getUser() {
        return user;
    }

    public Societe getSociete() {
        return societe;
    }

    public Etablisement getEtab() {
        return etab;
    }
    @JsonIgnore
    public Commande getCommande() {
        return commande;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setEffectifdemande(long effectifdemande) {
        this.effectifdemande = effectifdemande;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNatureDedemande(String natureDedemande) {
        this.natureDedemande = natureDedemande;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public long getNbrejrepos() {
        return nbrejrepos;
    }

    public void setNbrejrepos(long nbrejrepos) {
        this.nbrejrepos = nbrejrepos;
    }

    public void setNatureDeMission(String natureDeMission) {
        this.natureDeMission = natureDeMission;
    }

    public void setRegimeHoraire(String regimeHoraire) {
        this.regimeHoraire = regimeHoraire;
    }

    public void setDebutDeLaMission(String debutDeLaMission) {
        DebutDeLaMission = debutDeLaMission;
    }

    public void setFinDeLaMission(String finDeLaMission) {
        FinDeLaMission = finDeLaMission;
    }

    public void setPersonneAcontacter(String personneAcontacter) {
        this.personneAcontacter = personneAcontacter;
    }

    public long getDepensetheorique() {
        return depensetheorique;
    }

    public void setDepensetheorique(long depensetheorique) {
        this.depensetheorique = depensetheorique;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }



    public void setUser(User user) {
        this.user = user;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public void setEtab(Etablisement etab) {
        this.etab = etab;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }






}
