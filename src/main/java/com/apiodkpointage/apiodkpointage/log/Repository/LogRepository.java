package com.apiodkpointage.apiodkpointage.log.Repository;

import com.apiodkpointage.apiodkpointage.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends JpaRepository <Log, Long>{
    //List<Log> findByTypeActeurAndIdActeur(String acteur, Long id);
    List<Log> findLogByDate(LocalDate localDate);
    List<Log> getLogByDateGreaterThanEqualAndDateLessThanEqual(LocalDate min, LocalDate max);
}
