package com.crossover.trial.journals.service.helpers;

import com.crossover.trial.journals.builders.MailMessage;

public interface NotificationService {

	void emailNotification(MailMessage build);

	void scheduledNotification();

}
