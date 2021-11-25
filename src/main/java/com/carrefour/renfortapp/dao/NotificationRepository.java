package com.carrefour.renfortapp.dao;


import com.carrefour.renfortapp.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification,Long> {
}

