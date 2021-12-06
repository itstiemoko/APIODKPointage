package com.apiodkpointage.apiodkpointage.promotions.controllers;


import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.promotions.services.PromotionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/promotion/")
public class PromotionController {

    @Autowired
    PromotionServiceImp promotionServiceImp;

    @GetMapping("afficherpromotion")
    List<Promotion> afficher(){
        return promotionServiceImp.afficherListePromotion();
    }

    @PostMapping("ajouter/{idAdmin}")
    String ajouter(@RequestBody Promotion promotion,@PathVariable Long idAdmin){
        return promotionServiceImp.ajouterPromotion(promotion,idAdmin);
    }

    @PutMapping("modifier/{id}&{idAdmin}")
    Promotion modification(@RequestBody Promotion promotion, @PathVariable Long id,@PathVariable Long idAdmin){
        return promotionServiceImp.modifierPromotion(promotion, id,idAdmin);
    }
    @GetMapping("afficherparid/{id}")
    Promotion afficherparid(@PathVariable("id") Long id){
        return promotionServiceImp.afficherParId(id);
    }

}
