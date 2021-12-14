package com.apiodkpointage.apiodkpointage.pointages.Controller;


import com.apiodkpointage.apiodkpointage.pointages.Pointage;
import com.apiodkpointage.apiodkpointage.pointages.service.PointageServiceImpl;
import com.apiodkpointage.apiodkpointage.utilisateurs.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pointage/")
@CrossOrigin
public class PointageController {
    @Autowired
    PointageServiceImpl pointageservice;

    @PostMapping("ajoutPointage")
    String ajout(@RequestBody Pointage pointage){
        return pointageservice.ajoutPointage(pointage);
    }

    @GetMapping("afficherPointage/{id}")
    Pointage afficheById(@PathVariable Long id){
        return pointageservice.afficherPointageById(id);
    }

    @PostMapping("affichePointageByUser")
    List<Pointage> afficheUser(@RequestBody Utilisateur utilisateur){
        return pointageservice.afficherPointByUser(utilisateur);
    }

    @GetMapping("listPointage")
    List<Pointage> listePointage(){
        return pointageservice.listPointage();
    }

    //Poinatage par jour
    @GetMapping("pointageByDay/date={date}")
        List<Pointage> pointageByDay(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate localdate){
       return pointageservice.getPointageList(localdate);
    }

    //Poinatage par semaine
    @GetMapping("pointageByWeek/week={year}-{month}-{day}")
    List<Pointage> pointageByWeek(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
        return pointageservice.getPointageList(year, month, day);
    }

    //Poinatage par mois
    @GetMapping("pointageByMonth/month={year}-{month}")
    List<Pointage> pointageByMonth(@PathVariable("year") int year, @PathVariable("month") int month){
        return pointageservice.getPointageList(year, month);
    }

    @GetMapping("pointageByInterval/{debut}&{fin}")
    public List<Pointage> getPresenceListBetween(
            @PathVariable("debut") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate debut,
            @PathVariable("fin") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fin){
        return pointageservice.getPointageListBetwenDate(debut, fin);
    }

}
