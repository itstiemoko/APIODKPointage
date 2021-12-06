package com.apiodkpointage.apiodkpointage.administrateurs.repositories;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long>
{
    Optional<Administrateur> findByLoginAndMotDePass(String login, String password);

    Optional<Administrateur> findByTelephone(int telephone);
    Optional<Administrateur> findByEmail(String email);
    Optional<Administrateur> findByLogin(String login);


}
