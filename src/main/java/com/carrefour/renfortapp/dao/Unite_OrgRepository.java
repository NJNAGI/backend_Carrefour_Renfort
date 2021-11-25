package com.carrefour.renfortapp.dao;

import com.carrefour.renfortapp.models.UniteOrganisationnelle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Unite_OrgRepository extends JpaRepository<UniteOrganisationnelle,Long> {
}

