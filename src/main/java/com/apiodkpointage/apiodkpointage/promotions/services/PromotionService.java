package com.apiodkpointage.apiodkpointage.promotions.services;

import com.apiodkpointage.apiodkpointage.promotions.Promotion;

import java.util.List;

public interface PromotionService  {
    public String ajouterPromotion(Promotion promotion,Long idAdmin);
    public Promotion modifierPromotion(Promotion promotion, Long id,Long idAdmin);
    List<Promotion> afficherListePromotion();
    public Promotion afficherParId(Long id);

}
