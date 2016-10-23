package com.crossover.trial.journals.service;

import static com.crossover.trial.journals.constants.ApplicationConstants.FIRST_TIME_POLL_MESSAGE;
import static com.crossover.trial.journals.constants.ApplicationConstants.NO_JOURNALS_ADDED;
import static com.crossover.trial.journals.constants.ApplicationConstants.POLLING_INTERVAL_IN_SECONDS;
import static com.crossover.trial.journals.constants.ApplicationConstants.SCHUDLED_EMAIL_BODY_CONTENT;
import static com.crossover.trial.journals.constants.ApplicationConstants.SCHUDLED_MESSAGE_COMMON;
import static com.crossover.trial.journals.constants.ApplicationConstants.SCHUDLED_NEW_JOURNAL_TITLE;
import static com.crossover.trial.journals.constants.ApplicationConstants.SUBSCRIBED_EMAIL_NOTIFICATION_BODY;
import static com.crossover.trial.journals.constants.ApplicationConstants.SUBSCRIBED_EMAIL_NOTIFICATION_SUBJECT;
import static com.crossover.trial.journals.constants.ApplicationConstants.TRIGGER_INTERVAL_IN_SECONDS;
import static com.crossover.trial.journals.model.NotificationStatus.BEGIN_POLLING;
import static com.crossover.trial.journals.model.NotificationStatus.ERROR;
import static com.crossover.trial.journals.model.NotificationStatus.NO_NEW_JOURNALS;
import static com.crossover.trial.journals.model.NotificationStatus.SENT;
import static com.crossover.trial.journals.utility.TemporalUtil.format;
import static com.crossover.trial.journals.utility.TemporalUtil.toDate;
import static com.crossover.trial.journals.utility.TemporalUtil.toLocalDateTime;
import static java.lang.String.format;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.MailMessage;
import com.crossover.trial.journals.model.Notification;
import com.crossover.trial.journals.model.Subscription;
import com.crossover.trial.journals.repository.JournalRepository;
import com.crossover.trial.journals.repository.NotificationRepository;
import com.crossover.trial.journals.repository.SubscriptionRepository;
import com.crossover.trial.journals.repository.UserRepository;
import com.crossover.trial.journals.service.helpers.EmailService;

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
		notify(poll(TRIGGER_INTERVAL_IN_SECONDS, notificationRepository.findTopByOrderByLastTriggerDesc()));
	}

	@Override
	public List<MailMessage> poll(Long timeInSeconds, final Notification prevNote) {
		List<MailMessage> messages = new ArrayList<>();
		final Notification newNote = new Notification();
		final LocalDateTime now = LocalDateTime.now();
		newNote.setLastTrigger(toDate(now));
		newNote.setNotificationSent(BEGIN_POLLING);
		final StringBuffer s = new StringBuffer(FIRST_TIME_POLL_MESSAGE);
		try {
			if (prevNote != null) {
				if (now.isBefore(toLocalDateTime(prevNote.getLastTrigger()).plusSeconds(timeInSeconds))) {
					return null;
				}
				// Needed a mutable version of Integer here to mark final
				final AtomicInteger journalCount = new AtomicInteger(0);
				s.setLength(0);
				journalRepository.findBypublishDateAfter(prevNote.getLastTrigger()).forEach(j -> {
					s.append(format(SCHUDLED_NEW_JOURNAL_TITLE, journalCount.incrementAndGet(), j.getName(),
							j.getPublisher().getName(), format(j.getPublishDate()), j.getCategory().getName()));
				});
				if (0 < journalCount.get()) {
					userRepository.findAll().forEach(k -> {
						String msg = format(SCHUDLED_MESSAGE_COMMON, format(prevNote.getLastTrigger()));
						messages.add(MailMessage.builder().to(k.getEmailId()).subject(msg + "!")
								.body(format(SCHUDLED_EMAIL_BODY_CONTENT, k.getLoginName(), msg, s.toString()))
								.build());
						log.info("Scheduled Email Triggered to: " + k.getLoginName() + " at " + now);
					});
					newNote.setNotificationSent(SENT);
				} else {
					s.append(NO_JOURNALS_ADDED);
					newNote.setNotificationSent(NO_NEW_JOURNALS);
				}
			}
		} catch (Exception e) {
			s.setLength(0);
			s.append(e.getMessage());
			newNote.setNotificationSent(ERROR);
			log.error(e, e);
		}
		// Do not Send Email the first time around else users mailbox will
		// be flooded with emails, every time the notification table undergoes
		// housekeeping
		notificationRepository.save(newNote);
		return messages;
	}

	@Override
	public void notifyNewJournal(Journal journal) {
		notify(fetchMailMessagesForSubscribers(journal));
	}

	@Override
	public List<MailMessage> fetchMailMessagesForSubscribers(Journal journal) {
		List<MailMessage> messages = new ArrayList<>();
		List<Subscription> subscriptions = subscriptionRepository.findByCategory(journal.getCategory());
		subscriptions.stream().forEach(s -> {
			String body = format(SUBSCRIBED_EMAIL_NOTIFICATION_BODY, s.getUser().getLoginName(), journal.getName(),
					journal.getPublisher().getName(), format(journal.getPublishDate()), s.getCategory().getName());
			messages.add(MailMessage.builder().to(s.getUser().getEmailId())
					.subject(format(SUBSCRIBED_EMAIL_NOTIFICATION_SUBJECT, journal.getName())).body(body).build());
		});
		return messages;
	}

	private void notify(List<MailMessage> messages) {
		if (null != messages) {
			messages.forEach(m -> emailService.sendMessage(m));
		}
	}
}
