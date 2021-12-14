package com.apiodkpointage.apiodkpointage.pointages;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Pointage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @Column(nullable = false)
    private LocalTime heureArrivee = LocalTime.now();

    private LocalTime heureDepart;

    public List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<Administrateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "pointages")
    public List<Administrateur> administrateurs;

    @ManyToOne
    public Utilisateur utilisateur;

    public Pointage() {
    }

    public Pointage(LocalTime heureArrivee, LocalTime heureDepart) {
        this.heureArrivee = heureArrivee;
        this.heureDepart = heureDepart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }


}
