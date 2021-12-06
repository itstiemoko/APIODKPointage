package com.apiodkpointage.apiodkpointage.promotions.repositories;

import com.apiodkpointage.apiodkpointage.promotions.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
