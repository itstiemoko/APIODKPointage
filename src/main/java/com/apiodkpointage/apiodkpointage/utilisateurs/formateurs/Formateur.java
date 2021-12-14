package com.apiodkpointage.apiodkpointage.utilisateurs.formateurs;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.utilisateurs.Type;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;

import javax.persistence.*;

@Entity
@DiscriminatorValue("FORMATEUR")
public class Formateur extends Utilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Formateur() {
    }

<<<<<<< HEAD
    public Formateur(String nom, String prenom, String adresse, int telephone, String email, String login, String motDePass, Etat etat, Long id) {
        super(nom, prenom, adresse, telephone, email, login, motDePass, etat, false);
=======
    public Formateur(String nom, String prenom, String adresse, int telephone, String email, String login, String motDePass, Etat etat, Long id, String imageURL) {
        super(nom, prenom, adresse, telephone, email, login, motDePass, etat, imageURL);
>>>>>>> e7fc39ee8da76abc8c14163571d0953d514d2f33
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
