package com.apiodkpointage.apiodkpointage.utilisateurs.repository;

import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateursRepository extends JpaRepository <Utilisateur, Long > {
    Optional<Utilisateur> findByLoginAndMotDePass(String login, String password);
    Utilisateur findUtilisateurByLogin(String login);
    List<Utilisateur>  findUtilisateurByGenre(String genre);
    Optional<Utilisateur> findByTelephone(int telephone);
    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByLogin(String login);
}
