package com.crossover.trial.journals.service;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.MailMessage;
import com.crossover.trial.journals.model.User;

public interface NotificationService {
	void emailNotification(MailMessage build);

	void scheduledNotification();

	void notifyNewJournal(Journal journal);
}
