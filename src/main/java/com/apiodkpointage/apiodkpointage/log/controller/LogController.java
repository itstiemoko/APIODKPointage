package com.apiodkpointage.apiodkpointage.log.controller;

import com.apiodkpointage.apiodkpointage.log.Log;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/log")
public class LogController {
    @Autowired
    LogServiceImp logServiceImp;
    @PostMapping("/ajoutLog")
    Log ajoutLOg(@RequestBody Log log){
        return logServiceImp.ajoutLOg(log);
    }

    @GetMapping("/listeLog")
    List<Log> afficherLog(){
        return logServiceImp.afficherLog();
    }

    @GetMapping("/afficherLogById/{id}")
    Log afficherLogBById(@PathVariable Long id){

        return logServiceImp.afficherLgBById(id);
    }

    /**@GetMapping("/afficherParActeur/{id}&{acteur}")
    List<Log> afficherByActeur(@PathVariable String acteur, @PathVariable Long id){
        return logServiceImp.afficherByActeur(acteur, id);
    }**/

    //Log par jour
    @GetMapping("logByDay/date={date}")
    List<Log> pointageByDay(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate localdate){
        return logServiceImp.getLogByDate(localdate);
    }

    //Log par mois
    @GetMapping("logByMonth/month={year}-{month}")
    List<Log> pointageByMonth(@PathVariable("year") int year, @PathVariable("month") int month){
        return logServiceImp.getLogByMonth(year, month);
    }

    //Log par interval
    @GetMapping("logByInterval/{debut}&{fin}")
    public List<Log> getPresenceListBetween(
            @PathVariable("debut") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate debut,
            @PathVariable("fin") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fin){
        return logServiceImp.getLogBetweenInterval(debut, fin);
    }
}
