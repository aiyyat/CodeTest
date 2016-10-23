package com.crossover.trial.journals.service;

import static com.crossover.trial.journals.constants.ApplicationConstants.FIRST_TIME_POLL_MESSAGE;
import static com.crossover.trial.journals.constants.ApplicationConstants.POLLING_INTERVAL_IN_SECONDS;
import static com.crossover.trial.journals.constants.ApplicationConstants.SCH_EMAIL_BODY_CONTENT;
import static com.crossover.trial.journals.constants.ApplicationConstants.SCH_NEW_JOURNAL_TITLE;
import static com.crossover.trial.journals.constants.ApplicationConstants.SUB_EMAIL_NOTIFICATION_BODY;
import static com.crossover.trial.journals.constants.ApplicationConstants.SUB_EMAIL_NOTIFICATION_SUBJECT;
import static com.crossover.trial.journals.constants.ApplicationConstants.TRIGGER_INTERVAL_IN_SECONDS;
import static com.crossover.trial.journals.utility.TemporalUtil.format;
import static com.crossover.trial.journals.utility.TemporalUtil.toDate;
import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crossover.trial.journals.constants.ApplicationConstants;
import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.MailMessage;
import com.crossover.trial.journals.model.Notification;
import com.crossover.trial.journals.model.Subscription;
import com.crossover.trial.journals.repository.JournalRepository;
import com.crossover.trial.journals.repository.NotificationRepository;
import com.crossover.trial.journals.repository.SubscriptionRepository;
import com.crossover.trial.journals.repository.UserRepository;
import com.crossover.trial.journals.service.helpers.EmailService;
import com.crossover.trial.journals.utility.TemporalUtil;

/**
 * Handles Scheduled User Notification
 * 
 * @author achuth
 *
 */
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
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	@Scheduled(fixedRate = POLLING_INTERVAL_IN_SECONDS)
	public void scheduledNotification() {
		final Notification prevNot = notificationRepository.findTopByOrderByLastTriggerDesc();
		final LocalDateTime now = LocalDateTime.now();
		final StringBuffer s = new StringBuffer(FIRST_TIME_POLL_MESSAGE);
		if (prevNot != null) {
			if (now.isBefore(
					TemporalUtil.toLocalDateTime(prevNot.getLastTrigger()).plusSeconds(TRIGGER_INTERVAL_IN_SECONDS))) {
				return;
			}
			// Needed a mutable version of Integer here to mark final
			final AtomicInteger journalCount = new AtomicInteger(0);
			s.setLength(0);
			journalRepository.findBypublishDateAfter(prevNot.getLastTrigger()).forEach(j -> {
				s.append(format(SCH_NEW_JOURNAL_TITLE, journalCount.incrementAndGet(), j.getName(),
						j.getPublisher().getName(), format(j.getPublishDate()), j.getCategory().getName()));
			});
			if (0 < journalCount.get()) {
				userRepository.findAll().forEach(k -> {
					String text = "Journel's Added Since " + prevNot.getLastTrigger().toString();
					emailNotification(MailMessage.builder().to(k.getEmailId()).subject(text + "!")
							.body(format(SCH_EMAIL_BODY_CONTENT, k.getLoginName(), text, s.toString())).build());
					log.info("Scheduled Email Triggered to: " + k.getLoginName() + " at " + now);
				});
			} else {
				s.append(ApplicationConstants.NO_JOURNALS_ADDED);
			}
		}
		// Do not Send Email the first time around else users mailbox will
		// be flooded with emails, every time the notification table undergoes
		// housekeeping
		Notification newNot = new Notification();
		newNot.setMessage(s.toString());
		newNot.setLastTrigger(toDate(now));
		notificationRepository.save(newNot);
	}

	@Override
	public void notifyNewJournal(Journal journal) {
		List<Subscription> subscriptions = subscriptionRepository.findByCategory(journal.getCategory());
		subscriptions.stream().forEach(s -> {
			String body = format(SUB_EMAIL_NOTIFICATION_BODY, s.getUser().getLoginName(), journal.getName(),
					journal.getPublisher().getName(), format(journal.getPublishDate()), s.getCategory().getName());
			emailNotification(MailMessage.builder().to(s.getUser().getEmailId())
					.subject(format(SUB_EMAIL_NOTIFICATION_SUBJECT, journal.getName())).body(body).build());
		});
	}

	@Override
	public void emailNotification(MailMessage message) {
		emailService.sendMessage(message);
	}
}
