package com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Service;

import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Groupe;
import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class GroupeServiceImp implements GroupeService{
  @Autowired
  GroupeRepository groupeRepository;
  @Override
  public Groupe ajoutGroupe(Groupe groupe) {
    return groupeRepository.save(groupe) ;
  }

  @Override
  public List<Groupe> afficherGroupe() {
    return groupeRepository.findAll();
  }

  @Override
  @Transactional
  public String modifierGroupe(Groupe groupe, Long id) {
     Groupe modifier=  groupeRepository.findById(id).get();
     modifier.setNom(groupe.getNom());
     modifier.setEtat(groupe.getEtat());
     modifier.setDateModification(groupe.getDateModification());
     modifier.setNombrePersonne(groupe.getNombrePersonne());
    return "Groupe Modifier" ;
  }

  @Override
  public Groupe afficherGroupeById(Long id) {
    return groupeRepository.findById(id).get();
  }

  @Override
  public String supprimerGroupe(Groupe groupe ,Long id) {

    return null;
  }
}
