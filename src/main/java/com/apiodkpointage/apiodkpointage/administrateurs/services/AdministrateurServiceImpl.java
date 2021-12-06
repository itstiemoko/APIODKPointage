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


        if (admintel.isPresent() || adminemail.isPresent() || adminlogin.isPresent()){
            throw new IllegalStateException("cet email ou telephone ou login existe dèja ");

        }

        administrateurRepository.save(administrateur);
        logServiceImp.addLogAdmin(superAdmin, "Ajouter de l'Admin "+ administrateur.getPrenom()+ " "+administrateur.getNom());

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

        administrateur.setEtat(Etat.DESACTIVER);
        logServiceImp.addLogAdmin(superAdmin, "Suppression de l'Admin "+administrateur.getPrenom()+" "+administrateur.getNom()+" par "+superAdmin.getPrenom()+" "+superAdmin.getNom());

        return "Vous avez supprimé l'admin "+administrateur.getPrenom()+" "+administrateur.getNom();
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

        logServiceImp.addLogAdmin(superAdmin, "Modification de l'Admin "+ administrateur.getPrenom()+ " "+administrateur.getNom());
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
        logServiceImp.addLogAdmin(administrateurExistant, "A modifier son mot de passe");
        return "Mot de passe modifié avec succès !";
    }

    @Override
    public Administrateur connexion(String login, String password) {
        Optional<Administrateur> optionalAdministrateur = administrateurRepository.findByLoginAndMotDePass(login, password);

        if(optionalAdministrateur.isEmpty())
        {
            return null;
        }

        if(optionalAdministrateur.get().getEtat() == Etat.DESACTIVER)
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
        logServiceImp.addLogAdmin(superAdmin,  "Modification du profile de l'admin "+administrateurExistant.getPrenom()+" "+administrateurExistant.getNom());

        return "Profile mise à jour avec succès !";
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
        logServiceImp.addLogAdmin(superAdmin, "Le profile "+profile.getLibelle()+" ajouté par le SUPERADMIN avec l'id "+idSuperAdmin);
        return "Profile "+profile.getLibelle()+" ajouté avec succès !";
    }

    @Override
    public String deleteProfile(Long id, Long idSuperAdmin) {
        Administrateur superAdmin= administrateurRepository.findById(idSuperAdmin).get();
        profileRepository.deleteById(id);
        logServiceImp.addLogAdmin(superAdmin,  "Le profile avec l'id "+id+" supprimé par "+superAdmin.getPrenom());
        return "Le profile avec l'id "+id+" supprimé avec succès !";
    }
}
