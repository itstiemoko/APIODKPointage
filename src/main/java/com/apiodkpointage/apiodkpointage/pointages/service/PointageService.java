package com.apiodkpointage.apiodkpointage.pointages.service;

import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;

import java.time.LocalDate;
import java.util.List;

public interface PointageService {

    public String ajoutPointage(Pointage pointage);
    public Pointage afficherPointageById(Long id);
    public List<Pointage> afficherPointByUser(Utilisateur utilisateur);
    public List<Pointage> listPointage();
    Pointage modifierPointage(Pointage pointage, Long id);

    //Les pointages par date
    List<Pointage> getPointageList(LocalDate localDate);

    //Les pointages par semaine
    List<Pointage> getPointageList(int year, int month, int day);

    //Les pointages par mois
    List<Pointage> getPointageList(int year, int month);

    //Les pointages entre deux intervalle
    List<Pointage> getPointageListBetwenDate(LocalDate min, LocalDate max);

    //Pointage par date et par utilisateur
    List<Pointage> getPointageByDateAndUser(LocalDate localDate, Utilisateur login);
}
