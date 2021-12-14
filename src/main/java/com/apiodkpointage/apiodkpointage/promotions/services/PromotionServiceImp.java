package com.apiodkpointage.apiodkpointage.promotions.services;

import com.apiodkpointage.apiodkpointage.administrateurs.Administrateur;
import com.apiodkpointage.apiodkpointage.administrateurs.repositories.AdministrateurRepository;
import com.apiodkpointage.apiodkpointage.log.Service.LogServiceImp;
import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import com.apiodkpointage.apiodkpointage.promotions.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImp implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    LogServiceImp logServiceImp;
    @Autowired
    AdministrateurRepository administrateurRepository;

    @Override
    public  String  ajouterPromotion(Promotion promotion,Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        int dateDebut = promotion.getDateDebut().getYear();
        promotion.setAnnee(dateDebut);
        int totalApprenant = promotion.getNombreFemmes()+ promotion.getNombreHommes();
        promotion.setTotalApprenants(totalApprenant);

        Optional<Promotion> isPromotionNameExist = promotionRepository.findByNom(promotion.getNom());

        if(isPromotionNameExist.isPresent())
        {
            return "nom";
        }


        logServiceImp.addLogAdmin(administrateur,"L'administrateur "+ administrateur.getPrenom()+ " "+administrateur.getNom()+ " a ajouté la promotion " +promotion.getNom());
        promotionRepository.save(promotion);
        return " Promotion ajoutée avec succès";
    }

    @Override
    public Promotion modifierPromotion(Promotion promotion, Long id,Long idAdmin) {
        Administrateur administrateur= administrateurRepository.findById(idAdmin).get();
        Promotion modification = promotionRepository.findById(id).get();
        modification.setNom(promotion.getNom());
        modification.setAnnee(promotion.getAnnee());
        modification.setDateDebut(promotion.getDateDebut());
        modification.setDateFin(promotion.getDateFin());
        modification.setApprenants(promotion.getApprenants());
        modification.setTotalApprenants(promotion.getTotalApprenants());
        modification.setNombreFemmes(promotion.getNombreFemmes());
        modification.setNombreHommes(promotion.getNombreHommes());
        modification.setHoraireDebutJournee(promotion.getHoraireDebutJournee());
        modification.setHoraireFinJournee(promotion.getHoraireFinJournee());
        logServiceImp.addLogAdmin(administrateur,"L'administrateur " +administrateur.getPrenom()+ " "+administrateur.getNom() + " a modifié la promotion " + promotion.getNom());
        return promotionRepository.save(modification);

    }

    @Override
    public List<Promotion> afficherListePromotion() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion afficherParId(Long id) {
        return promotionRepository.findById(id).get();
    }
}
