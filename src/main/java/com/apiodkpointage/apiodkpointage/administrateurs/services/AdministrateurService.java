package com.apiodkpointage.apiodkpointage.administrateurs.services;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.Profile;

import java.util.List;

public interface AdministrateurService {

    String ajouterAdmin(Administrateur administrateur, Long idSuperAdmin);
    List<Administrateur> afficherListAdmin();
    String supprimerAdmin(Long id, Long idSuperAdmin);
    String restoreAdmin(Long id, Long idSupperAdmin);
    Administrateur modifierAdmin(Administrateur administrateur, Long id, Long idSuperAdmin );
    Administrateur afficherAdminById(Long id);
    String modifierPassword(Long id, String nouveauPassword);
    Administrateur connexion(String login, String password);
    String mettreAJourProfile(Long id, Profile profile, Long idSuperAdmin);
    List<Profile> listProfile();
    Profile getProfileById(Long id);
    String addProfile(Profile profile, Long idSuperAdmin);
    String deleteProfile(Long id, Long idSuperAdmin);
}
