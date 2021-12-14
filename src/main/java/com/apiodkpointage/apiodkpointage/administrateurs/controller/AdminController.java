package com.apiodkpointage.apiodkpointage.administrateurs.controller;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.Profile;
import com.apiodkpointage.apiodkpointage.administrateurs.services.AdministrateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdministrateurServiceImpl administrateurServiceImpl;

    @PostMapping("/ajout_admin/{idSuperAdmin}")
    String addAdmin(@RequestBody Administrateur administrateur, @PathVariable Long idSuperAdmin){
        return administrateurServiceImpl.ajouterAdmin(administrateur, idSuperAdmin);
    }

    @GetMapping("/afficher_List_admin")
    List<Administrateur> afficherListAdmin(){
        return administrateurServiceImpl.afficherListAdmin();
    }

    @GetMapping("/afficherAdminById/{id}")
    Administrateur afficherAdminById(@PathVariable("id") Long id){
        return administrateurServiceImpl.afficherAdminById(id);
    }

    @DeleteMapping("/supprimerAdmin/{id}&{idSuperAdmin}")
    public String supprimerAdmin(@PathVariable("id") Long id, @PathVariable("idSuperAdmin") Long idSuperAdmin){
        return administrateurServiceImpl.supprimerAdmin(id, idSuperAdmin);
    }

    @DeleteMapping("/restoreAdmin/{id}&{idSuperAdmin}")
    public  String restoreAdmin(@PathVariable("id") Long id, @PathVariable("idSuperAdmin") Long idSuperAdmin){
        return administrateurServiceImpl.restoreAdmin(id, idSuperAdmin);
    }

    @PutMapping("/modifierAdmin/{id}&{idSuperAdmin}")
    public Administrateur modifierAdmin(@RequestBody Administrateur administrateur, @PathVariable("id") Long id, @PathVariable("idSuperAdmin") Long idSuperAdmin)
    {
        return administrateurServiceImpl.modifierAdmin(administrateur, id, idSuperAdmin);
    }

    @PutMapping("/modifierMotDePassAdmin/{id}")
    public String modifierAdminPassword(@PathVariable("id") Long id, @RequestParam String nouveauPassword)
    {
        return administrateurServiceImpl.modifierPassword(id, nouveauPassword);
    }

    @GetMapping("/authentificationAdmin")
    public Administrateur connexion(@RequestParam String login, @RequestParam String password)
    {
        return administrateurServiceImpl.connexion(login, password);
    }

    @PutMapping("/updateProfile/{id}&{idSuperAdmin}")
    public String updateProfile(@PathVariable("id") Long id, @RequestBody Profile profile, @PathVariable("idSuperAdmin") Long idSuperAdmin){
        return administrateurServiceImpl.mettreAJourProfile(id, profile, idSuperAdmin);
    }

    @GetMapping("/listProfile")
    public List<Profile> listProfile()
    {
        return administrateurServiceImpl.listProfile();
    }

    @GetMapping("/getProfileById/{id}")
    public Profile getProfileById(@PathVariable Long id)
    {
        return administrateurServiceImpl.getProfileById(id);
    }

    @PostMapping("/addProfile/{idSuperAdmin}")
    public String addProfile(@RequestBody Profile profile, @PathVariable Long idSuperAdmin)
    {
        return administrateurServiceImpl.addProfile(profile, idSuperAdmin);
    }

    @DeleteMapping("/deleteProfile/{id}&{idSuperAdmin}")
    public String deleteProfile(@PathVariable Long id, @PathVariable Long idSuperAdmin)
    {
        return administrateurServiceImpl.deleteProfile(id, idSuperAdmin);
    }

}
