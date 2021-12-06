package com.apiodkpointage.apiodkpointage.administrateurs;

import com.apiodkpointage.apiodkpointage.Etat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile
{
    //Déclaration des variables de classe.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Libelle libelle;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "profile")
    private List<Administrateur> administrateurs;

    public List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<Administrateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    //Constructeur par défaut
    public Profile() {
    }

    public Profile(Libelle libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    /*____________________Setters et Getters_____________________________*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libelle getLibelle() {
        return libelle;
    }

    public void setLibelle(Libelle libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
