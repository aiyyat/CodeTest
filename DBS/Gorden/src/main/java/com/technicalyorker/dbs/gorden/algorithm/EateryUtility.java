package com.technicalyorker.dbs.gorden.algorithm;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.technicalyorker.dbs.gorden.exception.InvalidInputFormatException;

/**
 * EateryUtility is a Spring Component Managed Bean and implements KnapSack
 * Algorithm to find the max satisfaction for a variety of dishes.
 * 
 * @author achuth
 *
 */
@Component
public class EateryUtility {
	private static final Logger logger = LoggerFactory.getLogger(EateryUtility.class);

	public Integer perform(InputStream is) {
		// Total allowable time in seconds!
		Integer t = null;
		// Total number of Items on Menu
		Integer n = null;
		// Time required to eat an item
		Integer y[] = null;
		// Degree of Satisfaction
		Integer x[] = null;
		try (Scanner s = new Scanner(is)) {
			t = s.nextInt();
			n = s.nextInt();
			y = new Integer[n];
			x = new Integer[n];
			for (Integer i = 0; i < n; i++) {
				x[i] = s.nextInt();
				y[i] = s.nextInt();
			}
		} catch (Throwable e) {
			throw new InvalidInputFormatException(e);
		}
		return perform(t, y, x, n);
	}

	// Gordan's best satisfaction/performance on the act of eating
	public Integer perform(Integer timeLimit, Integer eatingTimes[], Integer degreeOfSatisfactions[],
			Integer menuItemCount) {
		if (menuItemCount != eatingTimes.length || menuItemCount != degreeOfSatisfactions.length) {
			throw new InvalidInputFormatException();
		}
		Integer i, w;
		Integer satisfactionMatrix[][] = new Integer[menuItemCount + 1][timeLimit + 1];
		for (i = 0; i <= menuItemCount; i++) {
			for (w = 0; w <= timeLimit; w++) {
				if (i == 0 || w == 0)
					satisfactionMatrix[i][w] = 0;
				else if (eatingTimes[i - 1] <= w)
					satisfactionMatrix[i][w] = Math.max(
							degreeOfSatisfactions[i - 1] + satisfactionMatrix[i - 1][w - eatingTimes[i - 1]],
							satisfactionMatrix[i - 1][w]);
				else
					satisfactionMatrix[i][w] = satisfactionMatrix[i - 1][w];
			}
		}
		String text = String.format(
				"\nInput: \n\tTime Limit: %s\n\tEating Times: %s\n\tDegree Of Satisfaction: %s\n\tMenu Item Count:%s\nOutput: \n\tMax Sat. Degree:%s\n",
				timeLimit, Arrays.toString(eatingTimes), Arrays.toString(degreeOfSatisfactions), menuItemCount,
				satisfactionMatrix[menuItemCount][timeLimit]);
		logger.debug(text);
		return satisfactionMatrix[menuItemCount][timeLimit];
	}
}
