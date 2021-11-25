package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.AffectationInterimaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface affectationRepository extends JpaRepository<AffectationInterimaire,Long> {
}
