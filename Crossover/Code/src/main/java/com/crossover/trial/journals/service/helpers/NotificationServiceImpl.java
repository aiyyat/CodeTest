package com.crossover.trial.journals.service.helpers;

import static com.crossover.trial.journals.constants.ApplicationConstants.POLLING_INTERVAL_IN_SECONDS;
import static com.crossover.trial.journals.constants.ApplicationConstants.TRIGGER_INTERVAL_IN_SECONDS;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crossover.trial.journals.builders.MailMessage;
import com.crossover.trial.journals.model.Notification;
import com.crossover.trial.journals.repository.JournalRepository;
import com.crossover.trial.journals.repository.NotificationRepository;
import com.crossover.trial.journals.repository.UserRepository;
import com.crossover.trial.journals.util.Util;

@Service
public class NotificationServiceImpl implements NotificationService {
	private final static Logger log = Logger.getLogger(NotificationServiceImpl.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private UserRepository userRepository;

	@Scheduled(fixedRate = POLLING_INTERVAL_IN_SECONDS)
	@Override
	public synchronized void scheduledNotification() {
		Notification sb = notificationRepository.findTopByOrderByLastTriggerDesc();
		LocalDateTime now = LocalDateTime.now();
		final StringBuffer s = new StringBuffer("First Trigger!");
		if (sb != null) {
			if (now.isBefore(Util.to(sb.getLastTrigger()).plusSeconds(TRIGGER_INTERVAL_IN_SECONDS))) {
				return;
			}
			s.setLength(0);
			final AtomicInteger journalCount = new AtomicInteger(0);
			journalRepository.findBypublishDateAfter(sb.getLastTrigger()).forEach(j -> {
				s.append(String.format("\n\n\n%d) Name: %s\nBy: %s\nOn: %s\nCategory: %s",
						journalCount.incrementAndGet(), j.getName(), j.getPublisher().getName(), j.getPublishDate(),
						j.getCategory().getName()));
			});
			if (0 < journalCount.get()) {
				userRepository.findAll().forEach(k -> {
					String text = "Journel's Added Since " + sb.getLastTrigger().toString();
					emailNotification(MailMessage.builder().to(k.getEmailId()).subject(text + "!")
							.body(String.format("Dear %s,\n\n\n  %s\n%s \n\n\n Regards, \nTeam Medical Journal!",
									k.getLoginName(), text, s.toString()))
							.build());
					log.info("Scheduled Email Triggered to: " + k.getLoginName() + " at " + now);
				});
			}
		}
		// Else users mailbox will be flooded with emails everytime the
		// notification table undergoes housekeeping
		Notification notification = new Notification();
		notification.setMessage(s.toString());
		notification.setLastTrigger(new Date());
		notificationRepository.save(notification);
	}

	@Override
	public void emailNotification(MailMessage message) {
		emailService.sendMessage(message);
	}
}
