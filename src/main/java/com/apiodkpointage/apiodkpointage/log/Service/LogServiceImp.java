package com.apiodkpointage.apiodkpointage.log.Service;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.log.Log;
import com.apiodkpointage.apiodkpointage.log.Repository.LogRepository;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogServiceImp implements LogService{
    @Autowired
    LogRepository logRepository;

    @Override
    public Log ajoutLOg(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> afficherLog() {
        return logRepository.findAll();
    }

    @Override
    public Log afficherLgBById(Long id) {
        return logRepository.findById(id).get();
    }

    /**@Override
    public List<Log> afficherByActeur(String acteur, Long id) {

        return logRepository.findByTypeActeurAndIdActeur(acteur,id);
    }**/

    @Override
    public List<Log> getLogByDate(LocalDate localDate) {
        return logRepository.findLogByDate(localDate);
    }

    @Override
    public List<Log> getLogByMonth(int year, int month) {
        LocalDate initial = LocalDate.of(year, month, 1);
        LocalDate start = initial.withDayOfMonth(1);
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        return logRepository.getLogByDateGreaterThanEqualAndDateLessThanEqual(start, end);
    }

    @Override
    public List<Log> getLogBetweenInterval(LocalDate min, LocalDate max) {
        return logRepository.getLogByDateGreaterThanEqualAndDateLessThanEqual(min, max);
    }

    public void addLog(Utilisateur user, String action)
    {
        Log log = new Log();
        log.setUtilisateur(user);
        log.setAction(action);
        logRepository.save(log);
    }

    public void addLogAdmin(Administrateur administrateur, String action)
    {
        Log log = new Log();
        log.setAdministrateur(administrateur);
        log.setAction(action);
        logRepository.save(log);
    }
}
