package com.apiodkpointage.apiodkpointage.utilisateurs.service;

import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;

import java.util.List;

public interface UtilisateursServices {

    public String ajout_utilisateurs(Utilisateur utilisateur, Long idAdmin);
    public Utilisateur modifier_utilisateur(Utilisateur utilisateur, Long id, Long idAmin);
    public void supprimer_utilisateur(Long id, Long idAdmin);
    public void restoreUtilisateur(Long id, Long idAdmin);

    List<Utilisateur> afficher_utilisateur();
    public Utilisateur afficher_par_id(Long id);
    String modifierPassword(Long id, String nouveauPassword);
    Utilisateur login (String login, String password);
    Utilisateur findByUtilisateurAndLogin(String login);
    List<Utilisateur> UtilisateurByGenre(String genre);

}
