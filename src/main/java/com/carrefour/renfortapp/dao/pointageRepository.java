package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pointageRepository extends JpaRepository<Pointage,Long> {
}
