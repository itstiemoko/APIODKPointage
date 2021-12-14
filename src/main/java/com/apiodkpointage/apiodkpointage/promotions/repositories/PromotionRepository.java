package com.apiodkpointage.apiodkpointage.promotions.repositories;

import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long>
{
    Optional<Promotion> findByNom(String nom);
}
