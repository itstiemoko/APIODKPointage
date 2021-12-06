package com.apiodkpointage.apiodkpointage.pointages.Repository;

import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PointageRepository extends JpaRepository<Pointage,Long> {

    List<Pointage> findPointagesByDate(LocalDate localDate);
    List<Pointage> getPointageByDateGreaterThanEqualAndDateLessThanEqual(LocalDate min, LocalDate max);
    List<Pointage> findByUtilisateur(Utilisateur utilisateur);
    List<Pointage> findPointagesByDateAndUtilisateur(LocalDate localDate, Utilisateur login);
    Optional<Pointage> findByDateAndUtilisateur(LocalDate date, Utilisateur utilisateur);
}
