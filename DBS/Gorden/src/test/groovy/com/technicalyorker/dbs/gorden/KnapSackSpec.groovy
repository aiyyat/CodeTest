package com.technicalyorker.dbs.gorden
import spock.lang.*
import com.technicalyorker.dbs.gorden.algorithm.*;

class KnapSackEaterySpec extends Specification{
	def "the most ordinary of scenarios"() {
		print("calling test!")
		when: "KnapSackEatery is initialized"
		EateryUtility k = new EateryUtility();
		then: "Calculate the greatest Satisfaction and Compare it with result"
		e == k.perform(a,b,c,d)
		where: "predefined values are"
		a | b 						  | c 						  | d ||e
		50| [10, 20, 30] as Integer[] |[60, 100, 120] as Integer[]| 3 || 220
		25| [10, 20, 30] as Integer[] |[60, 100, 120] as Integer[]| 3 || 100
	}
}
