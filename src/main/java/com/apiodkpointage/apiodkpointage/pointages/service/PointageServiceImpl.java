package com.apiodkpointage.apiodkpointage.pointages.service;

import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.pointages.Repository.PointageRepository;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

@Service
public class PointageServiceImpl implements PointageService {


    @Autowired
    PointageRepository pointageRepository;

    @Override
    @Transactional
    public String ajoutPointage(Pointage pointage) {
        Optional<Pointage> pointageExistant = pointageRepository.findByDateAndUtilisateur(pointage.getDate(), pointage.getUtilisateur());

        if(pointageExistant.isEmpty())
        {
            pointageRepository.save(pointage);
        }

        else {
            pointageExistant.get().setHeureDepart(LocalTime.now());
        }
        return "Pointage effectué avec succès";
    }


    @Override
    public Pointage afficherPointageById(Long id) {
        return pointageRepository.findById(id).get();
    }

    @Override
    public List<Pointage> afficherPointByUser(Utilisateur utilisateur) {
        return pointageRepository.findByUtilisateur(utilisateur);
    }

    @Override
    public List<Pointage> listPointage() {
        return pointageRepository.findAll();
    }

    @Override
    public Pointage modifierPointage(Pointage pointage, Long id) {
        Pointage modification = pointageRepository.findById(id).get();
        modification.setHeureDepart(pointage.getHeureDepart());
        System.out.println("ok");
        return pointageRepository.save(modification);
    }

    //Liste de pointages
    @Override
    public List<Pointage> getPointageList(LocalDate localDate) {
        return pointageRepository.findPointagesByDate(localDate);
    }

    //Poitage par semaine
    @Override
    public List<Pointage> getPointageList(int year, int month, int day) {
        LocalDate week = LocalDate.of(year, month, day);
        LocalDate monday = week.with(previousOrSame(DayOfWeek.MONDAY));
        LocalDate friday = week.with(nextOrSame(DayOfWeek.FRIDAY));
        return pointageRepository.getPointageByDateGreaterThanEqualAndDateLessThanEqual(monday, friday);


    }

    //Pointage du mois
    @Override
    public List<Pointage> getPointageList(int year, int month) {
        LocalDate initial = LocalDate.of(year, month, 1);
        LocalDate start = initial.withDayOfMonth(1);
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        return pointageRepository.getPointageByDateGreaterThanEqualAndDateLessThanEqual(start, end);
    }

    //Pointage entre deux dates
    @Override
    public List<Pointage> getPointageListBetwenDate(LocalDate min, LocalDate max) {
        return pointageRepository.getPointageByDateGreaterThanEqualAndDateLessThanEqual(min, max);
    }

    //Pointage par utilisateur et par date
    @Override
    public List<Pointage> getPointageByDateAndUser(LocalDate localDate, Utilisateur utilisateur) {
        return pointageRepository.findPointagesByDateAndUtilisateur(localDate, utilisateur);
    }
}
