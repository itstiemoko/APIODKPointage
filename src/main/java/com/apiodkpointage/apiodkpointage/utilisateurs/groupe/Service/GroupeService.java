package com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Service;

import com.apiodkpointage.apiodkpointage.utilisateurs.groupe.Groupe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupeService {
  Groupe ajoutGroupe(Groupe groupe);
  List<Groupe> afficherGroupe();
  String modifierGroupe(Groupe groupe , Long id);
  Groupe afficherGroupeById(Long id);
  String supprimerGroupe(Groupe groupe ,Long id);
}
