package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface demandeurRepository extends JpaRepository<Demandeur,Long>{
}
