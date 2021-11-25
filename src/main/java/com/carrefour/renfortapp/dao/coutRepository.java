package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Couthoraire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface coutRepository extends JpaRepository<Couthoraire,Long> {
}
