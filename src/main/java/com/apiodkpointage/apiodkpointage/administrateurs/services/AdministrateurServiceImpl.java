package com.apiodkpointage.apiodkpointage.administrateurs.services;

import com.apiodkpointage.apiodkpointage.Etat;
import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.Profile;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;

import com.apiodkpointage.apiodkpointage.administrateurs.repositories.ProfileRepository;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
    @Autowired
    AdministrateurRepository administrateurRepository;

    @Autowired
    LogServiceImp logServiceImp;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public String ajouterAdmin(Administrateur administrateur, Long idSuperAdmin) {
        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();

        Optional<Administrateur> admintel= administrateurRepository.findByTelephone(administrateur.getTelephone());
        Optional<Administrateur> adminemail= administrateurRepository.findByEmail(administrateur.getEmail());
        Optional<Administrateur> adminlogin= administrateurRepository.findByLogin(administrateur.getLogin());


        if (adminemail.isPresent()){
            return "email";
        }
        if (admintel.isPresent()){
            return "telephone";
        }
        if(adminlogin.isPresent()){
            return "login";
        }

        administrateurRepository.save(administrateur);
        logServiceImp.addLogAdmin(superAdmin, superAdmin.getPrenom()+ " "+superAdmin.getNom()+ " a ajouté l'administrateur "+ administrateur.getPrenom()+ " "+administrateur.getNom());

        return "Admin "+administrateur.getPrenom()+" "+administrateur.getNom()+" ajouté avec succès";
    }

    @Override
    public List<Administrateur> afficherListAdmin() {
        return administrateurRepository.findAll();
    }

    @Override
    public String supprimerAdmin(Long id, Long idSuperAdmin) {
        //administrateurRepository.deleteById(id);

        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();
        Administrateur administrateur = administrateurRepository.findById(id).get();

        administrateur.setSupprimer(true);
        administrateur.setEtat(Etat.DESACTIVER);
        logServiceImp.addLogAdmin(superAdmin, "Suppression de l'Admin "+administrateur.getPrenom()+" "+administrateur.getNom()+" par "+superAdmin.getPrenom()+" "+superAdmin.getNom());

        return "Vous avez supprimez un administrateur "+administrateur.getPrenom()+" "+administrateur.getNom();
    }
    @Override
    public  String restoreAdmin(Long id, Long idSuperAdmin){
        Administrateur superAdmin = administrateurRepository.findById(idSuperAdmin).get();
        Administrateur administrateur = administrateurRepository.findById(id).get();

        administrateur.setSupprimer(false);
        administrateur.setEtat(Etat.ACTIVER);
        logServiceImp.addLogAdmin(superAdmin, "Restauration de l'admin" + administrateur.getPrenom() + " " + administrateur.getNom() + "par" +superAdmin.getPrenom() + " " + superAdmin.getNom());
        return "Vous avez supprimé l'admin " + administrateur.getPrenom() + " " + administrateur.getNom();
    }

    @Override
    public Administrateur modifierAdmin(Administrateur administrateur, Long id, Long idSuperAdmin) {
        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();
        Administrateur adminExistant = administrateurRepository.findById(id).get();

        adminExistant.setNom(administrateur.getNom());
        adminExistant.setPrenom(administrateur.getPrenom());
        adminExistant.setGenre(administrateur.getGenre());
        adminExistant.setAdresse(administrateur.getAdresse());
        adminExistant.setTelephone(administrateur.getTelephone());
        adminExistant.setEmail(administrateur.getEmail());
        adminExistant.setLogin(administrateur.getLogin());
        adminExistant.setMotDePass(administrateur.getMotDePass());
        adminExistant.setDateModification();
        adminExistant.setEtat(administrateur.getEtat());

        logServiceImp.addLogAdmin(superAdmin, superAdmin.getPrenom()+ " " +superAdmin.getNom()+ " a modifié l'Administrateur "+ administrateur.getPrenom()+ " "+administrateur.getNom());
        return administrateurRepository.save(adminExistant);
    }

    @Override
    public Administrateur afficherAdminById(Long id) {
        return administrateurRepository.findById(id).get();
    }

    @Override
    @Transactional
    public String modifierPassword(Long id, String nouveauPassword) {
        Administrateur administrateurExistant = administrateurRepository.findById(id).get();
        administrateurExistant.setMotDePass(nouveauPassword);
        logServiceImp.addLogAdmin(administrateurExistant, administrateurExistant.getPrenom()+ " " +administrateurExistant.getNom()+ " a modifié son mot de passe");
        return "Mot de passe modifié avec succès !";
    }

    @Override
    public Administrateur connexion(String login, String password) {
        Optional<Administrateur> optionalAdministrateur = administrateurRepository.findByLoginAndMotDePass(login, password);

        if(optionalAdministrateur.isEmpty())
        {
            return null;
        }

        if(optionalAdministrateur.get().getSupprimer())
        {
            throw new IllegalStateException("Votre compte administrateur est désactivé !");
        }

        logServiceImp.addLogAdmin(optionalAdministrateur.get(), optionalAdministrateur.get().getPrenom()+" "+optionalAdministrateur.get().getNom()+" s'est connecté");
        return optionalAdministrateur.get();
    }

    @Override
    @Transactional
    public String mettreAJourProfile(Long id, Profile profile, Long idSuperAdmin) {
        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();
        Administrateur administrateurExistant = administrateurRepository.findById(id).get();
        administrateurExistant.setProfile(profile);
        logServiceImp.addLogAdmin(superAdmin,  superAdmin.getPrenom()+ " "+superAdmin.getNom()+ " a modifié le profile de l'administrateur "+administrateurExistant.getPrenom()+" "+administrateurExistant.getNom());

        return "Mise en jour du profile effectué avec succès !";
    }

    @Override
    public List<Profile> listProfile() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElseThrow();
    }

    @Override
    public String addProfile(Profile profile, Long idSuperAdmin) {
        Administrateur superAdmin = administrateurRepository.findById(idSuperAdmin).get();
        profileRepository.save(profile);
        logServiceImp.addLogAdmin(superAdmin, "Le profile "+profile.getLibelle()+" a été ajouté par le SUPERADMIN "+ superAdmin.getPrenom()+ " "+superAdmin.getNom());
        return "Profile "+profile.getLibelle()+" profile ajouté avec succès !";
    }

    @Override
    public String deleteProfile(Long id, Long idSuperAdmin) {
        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();
        Profile profile= profileRepository.findById(id).get();
        profileRepository.deleteById(id);
        logServiceImp.addLogAdmin(superAdmin,  "Le profile "+profile.getLibelle()+ " a été supprimé par "+superAdmin.getPrenom()+ " "+superAdmin.getNom());
        return "Le profile "+profile.getLibelle()+ " supprimé avec succès !";
    }
}
