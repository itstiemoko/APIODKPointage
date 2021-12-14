package com.apiodkpointage.apiodkpointage.utilisateurs.service;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.pointages.service.PointageServiceImpl;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.repository.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateursServiceImpl implements UtilisateursServices{
    @Autowired
    UtilisateursRepository utilisateursRepository;

    @Autowired
    AdministrateurRepository administrateurRepository;

    @Override
    public List<Utilisateur> UtilisateurByGenre(String genre) {
        return  utilisateursRepository.findUtilisateurByGenre(genre);
    }

    @Autowired
    LogServiceImp logServiceImp;

    @Autowired
    PointageServiceImpl pointageServiceImpl;


    @Override
    public String ajout_utilisateurs(Utilisateur utilisateur, Long idAdmin) {
        Administrateur administrateur = administrateurRepository.findById(idAdmin).get();
        Optional<Utilisateur> usertel = utilisateursRepository.findByTelephone(utilisateur.getTelephone());
        Optional<Utilisateur> userlogin = utilisateursRepository.findByLogin(utilisateur.getLogin());
        Optional<Utilisateur> useremail = utilisateursRepository.findByEmail(utilisateur.getEmail());

        if (useremail.isPresent()){
            return "email";
        }
        if (usertel.isPresent()){
            return "telephone";
        }
        if(userlogin.isPresent()){
            return "login";
        }

        utilisateursRepository.save(utilisateur);
        logServiceImp.addLogAdmin(administrateur, administrateur.getPrenom()+ " "+administrateur.getNom()+ " a ajouté l'utilisateur "+ utilisateur.getPrenom()+" "+ utilisateur.getNom());

        return "Utilisateur "+utilisateur.getPrenom()+" "+utilisateur.getNom()+" ajouté avec succès";
    }

    @Override
    public Utilisateur modifier_utilisateur(Utilisateur utilisateur, Long id, Long idAdmin) {
        Administrateur administrateur=administrateurRepository.findById(idAdmin).get();
       Utilisateur modifier = utilisateursRepository.findById(id).get();
       modifier.setNom(utilisateur.getNom());
       modifier.setPrenom(utilisateur.getPrenom());
       modifier.setAdresse(utilisateur.getAdresse());
       modifier.setEmail(utilisateur.getEmail());
       modifier.setEtat(utilisateur.getEtat());
       modifier.setLogin(utilisateur.getLogin());
       modifier.setMotDePass(utilisateur.getMotDePass());
       modifier.setTelephone(utilisateur.getTelephone());
       modifier.setDateModification();
       logServiceImp.addLogAdmin(administrateur, administrateur.getPrenom()+ " "+administrateur.getNom()+ " a modifié l'utilisateur "+ utilisateur.getPrenom()+" "+ utilisateur.getNom());

        return utilisateursRepository.save(modifier);
    }

    @Override
    public void supprimer_utilisateur(Long id, Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        Utilisateur utilisateur=utilisateursRepository.findById(id).get();
        //utilisateursRepository.deleteById(id);
        utilisateur.setSupprimer(true);
        utilisateur.setEtat(Etat.DESACTIVER);
        logServiceImp.addLogAdmin(administrateur, "Suppression de l'utilisateur "+utilisateur.getNom()+ " "+utilisateur.getPrenom() +" par administrateur  "+ administrateur.getNom()+ " "+ administrateur.getPrenom() );
    }
    @Override
    public void restoreUtilisateur(Long id, Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        Utilisateur utilisateur=utilisateursRepository.findById(id).get();
        utilisateur.setSupprimer(false);
        utilisateur.setEtat(Etat.ACTIVER);
        logServiceImp.addLogAdmin(administrateur, "Restauration de l'utilisateur "+utilisateur.getNom()+ " "+utilisateur.getPrenom() +" par Admin  "+ administrateur.getNom()+ " "+ administrateur.getPrenom() );
    }

    @Override
    public List<Utilisateur> afficher_utilisateur() {
        return utilisateursRepository.findAll();
    }

    @Override
    public Utilisateur afficher_par_id(Long id) {
        return utilisateursRepository.findById(id).get();
    }

    @Override
    public String modifierPassword(Long id, String nouveauPassword) {
        Utilisateur utilisateurExistant = utilisateursRepository.findById(id).get();
        utilisateurExistant.setMotDePass(nouveauPassword);
        logServiceImp.addLog(utilisateurExistant, utilisateurExistant.getPrenom()+ " "+utilisateurExistant.getNom()+ " a modifié son mot de passe ");

        return "Mot de passe modifié avec succès !";
    }

    @Override
    public Utilisateur login(String login, String password) {
        Optional <Utilisateur> connexion= utilisateursRepository.findByLoginAndMotDePass(login, password);

        if(connexion.isEmpty())
        {
            return null;
        }

        if (connexion.get().getEtat() == Etat.DESACTIVER || connexion.get().getSupprimer()){
            throw new IllegalStateException("Votre compte utilisateur est désactivé");
        }

        Pointage pointage = new Pointage();
        pointage.setUtilisateur(connexion.get());
        pointageServiceImpl.ajoutPointage(pointage);
        logServiceImp.addLog(connexion.get(), "Connexion de l'utilisateur "+connexion.get().getPrenom()+" "+connexion.get().getNom());
        return connexion.get();
    }

    @Override
    public Utilisateur findByUtilisateurAndLogin(String login) {
        return utilisateursRepository.findUtilisateurByLogin(login);
    }


}
