package com.crossover.trial.journals.constants;

public class ApplicationConstants {
	// In reality it will be about 30*60*1000
	public static final long POLLING_INTERVAL_IN_SECONDS = 5 * 1000L;
	// In reality it will be 24*60
	public static final long TRIGGER_INTERVAL_IN_SECONDS = 240;
	public static final String SCHUDLED_NEW_JOURNAL_TITLE = "\n\n\n%d) Name: %s\nBy: %s\nOn: %s\nCategory: %s";
	public static final String SCHUDLED_EMAIL_BODY_CONTENT = "Dear %s,\n\n\n  %s\n%s \n\n\n Regards, \nTeam Medical Journal!";
	public static final String SCHUDLED_MESSAGE_COMMON = "Journels Added Since %s";
	public static final String SUBSCRIBED_EMAIL_NOTIFICATION_BODY = "Dear %s, \n\n A New Journel was added: \nName: %s\nBy: %s\nOn: %s\nCategory: %s\n\nRegards,\nTeam Medical Journal.";
	public static final String SUBSCRIBED_EMAIL_NOTIFICATION_SUBJECT = "Medical Jounel: New Journel Added!! %s";
	public static final String NO_JOURNALS_ADDED = "No New Journals got added";
	public static final String FIRST_TIME_POLL_MESSAGE = "Poller Started!";
}
