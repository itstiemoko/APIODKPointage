package com.apiodkpointage.apiodkpointage.utilisateurs.groupe.controller;

import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Groupe;
import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Service.GroupeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Groupe")
@CrossOrigin
public class GroupeController {
  @Autowired
  GroupeServiceImp groupeServiceImp;
  @PostMapping("/ajouterGroupe")
  public Groupe ajoutGroupe(@RequestBody Groupe groupe){
      return groupeServiceImp.ajoutGroupe(groupe);
  }
  @GetMapping("/afficherGroupe")
  public List<Groupe> afficherGroupe(){
    return groupeServiceImp.afficherGroupe();
  }
  @PutMapping("/modifierGroupe/{id}")
  public String modifierGroupe(@RequestBody Groupe groupe ,@PathVariable Long id){
    return groupeServiceImp.modifierGroupe(groupe ,id);
  }
  @GetMapping("/afficherById/{id}")
  public Groupe afficherGroupeById( @PathVariable Long id){
    return groupeServiceImp.afficherGroupeById(id);
  }


}
