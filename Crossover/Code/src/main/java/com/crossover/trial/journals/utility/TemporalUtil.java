package com.crossover.trial.journals.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TemporalUtil {
	private static final SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static LocalDateTime toLocalDateTime(Date lastTrigger) {
		return LocalDateTime.ofInstant(lastTrigger.toInstant(), ZoneId.systemDefault());
	}

	public static Date toDate(LocalDateTime lastTrigger) {
		return Date.from(lastTrigger.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String asString(Date date) {
		return s.format(date);
	}
}
