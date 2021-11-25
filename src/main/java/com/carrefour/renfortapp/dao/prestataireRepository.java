package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface prestataireRepository extends JpaRepository<Prestataire,Long> {
}