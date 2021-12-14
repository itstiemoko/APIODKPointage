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




    public Formateur(String nom, String prenom, String adresse, int telephone, String email, String login, String motDePass, Etat etat, Long id, boolean supprimer, String imageURL) {
        super(nom, prenom, adresse, telephone, email, login, motDePass, etat,supprimer, imageURL);
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
