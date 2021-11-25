package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DemmandeRepository extends JpaRepository<Demande,Long> {

    @Query("select d from  Demande d where d.regimeHoraire like :x ")
    Demande findByRegimeHoraire(@Param("x") String x);

}
