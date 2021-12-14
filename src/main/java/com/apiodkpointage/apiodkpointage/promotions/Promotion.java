package com.apiodkpointage.apiodkpointage.promotions;


import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.apprenants.Apprenant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
public class Promotion
{
    //Déclaration des variables de classe.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private int annee;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false)
    private int totalApprenants;

    @Column(nullable = false)
    private int nombreFemmes;

    @Column(nullable = false)
    private int nombreHommes;

    @Column(nullable = false)
    private LocalTime horaireDebutJournee;


    private LocalTime horaireFinJournee;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion")
    private List<Apprenant> apprenants;

    @ManyToMany
    private List<Administrateur> administrateurs;

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<Apprenant> apprenants) {
        this.apprenants = apprenants;
    }

    public Promotion() {
    }

    public Promotion(String nom, int annee, LocalDate dateDebut, LocalDate dateFin, int nombreFemmes, int nombreHommes, LocalTime horaireDebutJournee, LocalTime horaireFinJournee) {
        this.nom = nom;
        this.annee = annee;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombreFemmes = nombreFemmes;
        this.nombreHommes = nombreHommes;
        this.horaireDebutJournee = horaireDebutJournee;
        this.horaireFinJournee = horaireFinJournee;
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

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getTotalApprenants() {
        return totalApprenants;
    }

    public void setTotalApprenants(int totalApprenants) {
        this.totalApprenants = totalApprenants;
    }

    public int getNombreFemmes() {
        return nombreFemmes;
    }

    public void setNombreFemmes(int nombreFemmes) {
        this.nombreFemmes = nombreFemmes;
    }

    public int getNombreHommes() {
        return nombreHommes;
    }

    public void setNombreHommes(int nombreHommes) {
        this.nombreHommes = nombreHommes;
    }

    public LocalTime getHoraireDebutJournee() {
        return horaireDebutJournee;
    }

    public void setHoraireDebutJournee(LocalTime horaireDebutJournee) {
        this.horaireDebutJournee = horaireDebutJournee;
    }

    public LocalTime getHoraireFinJournee() {
        return horaireFinJournee;
    }

    public void setHoraireFinJournee(LocalTime horaireFinJournee) {
        this.horaireFinJournee = horaireFinJournee;
    }
}
