package com.apiodkpointage.apiodkpointage.utilisateurs.groupe;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.utilisateurs.apprenants.Apprenant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Groupe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nom;
  private Etat etat;
  private int nombrePersonne;

  @JsonIgnore
  @OneToMany(mappedBy = "groupe")
  private List<Apprenant> apprenant;

  private LocalDate dateCreation = LocalDate.now();
  private LocalDate dateModification;

  public Long getId() {
    return id;
  }

  public int getNombrePersonne() {
    return nombrePersonne;
  }

  public void setNombrePersonne(int nombrePersonne) {
    this.nombrePersonne = nombrePersonne;
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

  public Etat getEtat() {
    return etat;
  }

  public void setEtat(Etat etat) {
    this.etat = etat;
  }

  public List<Apprenant> getApprenant() {
    return apprenant;
  }

  public void setApprenant(List<Apprenant> apprenant) {
    this.apprenant = apprenant;
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

  public void setDateModification(LocalDate dateModification) {
    this.dateModification = dateModification;
  }

  public Groupe(String nom, Etat etat, LocalDate dateCreation , int nombrePersonne) {
    this.nom = nom;
    this.etat = etat;
    this.dateCreation = dateCreation;
    this.nombrePersonne = nombrePersonne;
  }
}
