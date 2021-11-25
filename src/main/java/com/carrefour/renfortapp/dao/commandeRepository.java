package com.carrefour.renfortapp.dao;


import com.carrefour.renfortapp.models.Commande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface commandeRepository extends JpaRepository<Commande,Long> {
    @Query(value = "SELECT SUM(heuretheorique) FROM Commande")
    public int sumQuantities();

}
