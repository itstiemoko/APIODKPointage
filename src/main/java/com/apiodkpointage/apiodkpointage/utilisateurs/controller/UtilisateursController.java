package com.apiodkpointage.apiodkpointage.utilisateurs.controller;


import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.pointages.service.PointageServiceImpl;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import com.apiodkpointage.apiodkpointage.utilisateurs.service.UtilisateursServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateurs/")
public  class UtilisateursController {
    @Autowired
    UtilisateursServiceImpl utilisateursService;

    @PostMapping("ajout_utilisateur/{idAdmin}")
    String ajout_utilisateur(@RequestBody Utilisateur utilisateur, @PathVariable("idAdmin") Long idAdmin){
        return utilisateursService.ajout_utilisateurs(utilisateur, idAdmin);
    }
    @GetMapping("afficher_list_utilisateur")
    List<Utilisateur> afficher_utilisateur(){
        return utilisateursService.afficher_utilisateur();
    }

    @GetMapping("genre_utilisateur/{genre}")
    List<Utilisateur> UtilisateurByGenre(@PathVariable("genre") String genre){
        return utilisateursService.UtilisateurByGenre(genre);
    }

    @GetMapping("afficher_by_id/{id}")
    Utilisateur afficher_by_id(@PathVariable("id") Long id){
        return utilisateursService.afficher_par_id(id);
    }

    @PutMapping("modifier_utilisateur/{id}&{idAdmin}")
    Utilisateur modifier_utilisateur(@RequestBody Utilisateur utilisateur, @PathVariable("id") Long id, @PathVariable("idAdmin") Long idAdmin){
        return utilisateursService.modifier_utilisateur(utilisateur, id, idAdmin);
    }

    @DeleteMapping("supprimerUser/{id}&{idAdmin}")
    public void deleteUser(@PathVariable("id") Long id, @PathVariable("idAdmin") Long idAdmin)
    {
        utilisateursService.supprimer_utilisateur(id, idAdmin);
    }
    @DeleteMapping("restoreUtilisateur/{id}&{idAdmin}")
    public void restoreUtilisateur(@PathVariable("id") Long id, @PathVariable("idAdmin") Long idAdmin)
    {
        utilisateursService.restoreUtilisateur(id, idAdmin);
    }
    @PutMapping("/modifierMotDePass/{id}")
    public String modifierAdminPassword(@PathVariable("id") Long id, @RequestParam String nouveauPassword)
    {
        return utilisateursService.modifierPassword(id, nouveauPassword);
    }
    @GetMapping("login")
    public Utilisateur connexion (@RequestParam String login, @RequestParam String password){
        return utilisateursService.login(login, password);
    }

}
