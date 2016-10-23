package com.crossover.trial.journals.service;

import java.util.List;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.MailMessage;
import com.crossover.trial.journals.model.Notification;

public interface NotificationService {
	void notifyNewJournal(Journal journal);

	void scheduledNotification();

	List<MailMessage> poll(Long timeInSeconds, final Notification prevNote);

	List<MailMessage> fetchMailMessagesForSubscribers(Journal journal);
}
