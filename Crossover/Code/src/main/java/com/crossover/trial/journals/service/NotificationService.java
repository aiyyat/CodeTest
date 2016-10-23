package com.crossover.trial.journals.service;

import java.util.List;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.MailMessage;

public interface NotificationService {
	void notifyNewJournal(Journal journal);

	void scheduledNotification();

	void notify(List<MailMessage> messages);
}
