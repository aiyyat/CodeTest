package com.crossover.trial.journals.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Util {

	public static LocalDateTime to(Date lastTrigger) {
		return LocalDateTime.ofInstant(lastTrigger.toInstant(), ZoneId.systemDefault());
	}

	public static Date to(LocalDateTime lastTrigger) {
		return Date.from(lastTrigger.atZone(ZoneId.systemDefault()).toInstant());
	}
}
