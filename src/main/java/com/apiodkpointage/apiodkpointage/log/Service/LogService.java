package com.apiodkpointage.apiodkpointage.log.Service;

import com.apiodkpointage.apiodkpointage.log.Log;

import java.time.LocalDate;
import java.util.List;

public interface LogService {
    Log ajoutLOg(Log log);
    List<Log>  afficherLog();
    Log afficherLgBById(Long id);
    //List<Log> afficherByActeur(String acteur, Long id);

    //Les pointages par date
    List<Log> getLogByDate(LocalDate localDate);

    //Les pointages par date
    List<Log> getLogByMonth(int year, int month);

    //Les pointages par date
    List<Log> getLogBetweenInterval(LocalDate min, LocalDate max);




}
