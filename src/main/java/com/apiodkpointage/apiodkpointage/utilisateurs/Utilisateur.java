package com.apiodkpointage.apiodkpointage.utilisateurs;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.log.Log;
import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.utilisateurs.apprenants.Apprenant;
import com.apiodkpointage.apiodkpointage.utilisateurs.formateurs.Formateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeUser", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,property = "Type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(name = "FORMATEUR", value = Formateur.class),
                @JsonSubTypes.Type(name = "APPRENANT", value = Apprenant.class)
        }
)
public abstract class Utilisateur
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
    private String adresse;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false, unique = true)
    private int telephone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String motDePass;

    @Column(nullable = false)
    private Etat etat;


    private boolean supprimer = false;
    @Column(nullable = false)
    private String imageURL;

    @ManyToMany(mappedBy = "utilisateurs")
    private List<Administrateur> administrateurs;

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur")
    private List<Pointage> pointages;

    private LocalDate dateCreation = LocalDate.now();

    private LocalDate dateModification;

    @JsonIgnore
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER)
    private List<Log> log= new ArrayList<>();


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


    //Constructeur par défaut
    public Utilisateur() {
    }


    public Utilisateur(String nom, String prenom, String adresse, int telephone, String email, String login, String motDePass, Etat etat, boolean supprimer, String imageURL) {


        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.login = login;
        this.motDePass = motDePass;
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

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public  boolean getSupprimer(){
        return supprimer;
    }
    public void setSupprimer(boolean supprimer){
        this.supprimer = supprimer;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<Pointage> getPointages() {
        return pointages;
    }

    public void setPointages(List<Pointage> pointages) {
        this.pointages = pointages;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public List<Log> getLog() {
        return log;
    }

    public void setLog(List<Log> log) {
        this.log = log;
    }
}
