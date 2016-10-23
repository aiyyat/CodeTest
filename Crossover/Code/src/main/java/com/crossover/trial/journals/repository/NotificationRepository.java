package com.crossover.trial.journals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.trial.journals.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	Notification findTopByOrderByLastTriggerDesc();
}
