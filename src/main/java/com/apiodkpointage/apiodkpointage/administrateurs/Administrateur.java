package com.apiodkpointage.apiodkpointage.administrateurs;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.log.Log;
import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Administrateur
{
    //Déclaration des variables de classe.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false, unique = true)
    private int telephone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String motDePass;

    @Column(nullable = false)
    private String imageURL;

    private LocalDate dateCreation = LocalDate.now();
    private LocalDate dateModification;

    @Column(nullable = false)
    private Etat etat;

    private Boolean supprimer = false;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Profile profile;

    @ManyToMany
    @JsonIgnore
    private List<Utilisateur> utilisateurs;

    @ManyToMany
    @JsonIgnore
    private List<Pointage> pointages;


    @ManyToMany(mappedBy = "administrateurs")
    @JsonIgnore
   private List<Promotion> promotions;

    @JsonIgnore
    @OneToMany(mappedBy = "administrateur", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Log> logs= new ArrayList<>();

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    //Constructeur par défaut
    public Administrateur(){}


    public Administrateur(String nom, String prenom, String adresse, String genre, int telephone, String email, String login, String motDePass, LocalDate dateCreation, LocalDate dateModification, Etat etat, boolean supprimer, String imageURL) {

        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.genre = genre;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.motDePass = motDePass;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.etat = etat;
        this.supprimer = supprimer;
        this.imageURL = imageURL;
    }

    /*____________________Setters et Getters_____________________________*/
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification() {
        this.dateModification = LocalDate.now();
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    public boolean getSupprimer(){
        return supprimer;
    }
    public void setSupprimer(boolean supprimer){
        this.supprimer = supprimer;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Pointage> getPointages() {
        return pointages;
    }

    public void setPointages(List<Pointage> pointages) {
        this.pointages = pointages;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
