package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Etablisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablisementRepository extends JpaRepository<Etablisement,Long> {
}
