package com.carrefour.renfortapp.dao;


import com.carrefour.renfortapp.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface budgetRepository extends JpaRepository<Budget,Long> {
}