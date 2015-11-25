package com.jpmc.problem2;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.problem2.domain.Direction;
import com.jpmc.problem2.domain.Operation;
import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.PositionIdentifier;
import com.jpmc.problem2.domain.Trade;

/**
 * 
 * @author achuth
 *
 */
public class JPMCProblem2Test {
	PositionAggregator aggregator;

	@Before
	public void setup() {
		aggregator = new PositionAggregator();
	}

	/**
	 * I could have written all my test cases this way, but using the
	 * formTrade() and checkForPosition() is neater, closer to 'Domain', apart
	 * from being easily understandable and aesthetically pleasing.
	 */
	@Test
	public void testXYZ() {
		Trade t1 = new Trade(1234, 1);
		t1.setAccountNumber("ACC-1234");
		t1.setSecurityId("XYZ");
		t1.setDirection(Direction.BUY);
		t1.setOperation(Operation.NEW);
		t1.setQuantity(100);

		Trade t2 = new Trade(1234, 2);
		t2.setAccountNumber("ACC-1234");
		t2.setSecurityId("XYZ");
		t2.setDirection(Direction.BUY);
		t2.setOperation(Operation.AMEND);
		t2.setQuantity(150);

		aggregator.addTrade(t1);
		aggregator.addTrade(t2);
		Position position = aggregator.getContainer().getPositionsList().get(0);
		TestCase.assertEquals("[1234]", position.getTrades().toString());
		TestCase.assertEquals(new Integer("150"), position.getQuantity());
		TestCase.assertEquals(
				"PositionIdentifier [accountNumber=ACC-1234, instrument=XYZ]",
				position.getId().toString());
	}

	@Test
	public void testQED() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("5678|1|QED|200|BUY|ACC-2345|NEW"));
		builder.addTrade(formTrade("5678|2|QED|0|BUY|ACC-2345|CANCEL"));
		checkForPosition(builder, "ACC-2345|QED|0|5678");
	}

	@Test
	public void testRET() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("2233|1|RET|100|SELL|ACC-3456|NEW"));
		builder.addTrade(formTrade("2233|2|RET|400|SELL|ACC-3456|AMEND"));
		builder.addTrade(formTrade("2233|3|RET|0|SELL|ACC-3456|CANCEL"));
		checkForPosition(builder, "ACC-3456|RET|0|2233");
	}

	@Test
	public void testYUI() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("8896|1|YUI|300|BUY|ACC-4567|NEW"));
		builder.addTrade(formTrade("6638|1|YUI|100|SELL|ACC-4567|NEW"));
		checkForPosition(builder, "ACC-4567|YUI|200|8896,6638");
	}

	@Test
	public void testHJK() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("6363|1|HJK|200|BUY|ACC-5678|NEW"));
		builder.addTrade(formTrade("7666|1|HJK|200|BUY|ACC-5678|NEW"));
		builder.addTrade(formTrade("6363|2|HJK|100|BUY|ACC-5678|AMEND"));
		builder.addTrade(formTrade("7666|2|HJK|50|SELL|ACC-5678|AMEND"));
		checkForPosition(builder, "ACC-5678|HJK|50|6363,7666");
	}

	@Test
	public void testFVBnGBN() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("8686|1|FVB|100|BUY|ACC-6789|NEW"));
		builder.addTrade(formTrade("8686|2|GBN|100|BUY|ACC-6789|AMEND"));
		builder.addTrade(formTrade("9654|1|FVB|200|BUY|ACC-6789|NEW"));
		checkForPosition(builder, "ACC-6789|GBN|100|8686");
		checkForPosition(builder, "ACC-6789|FVB|200|9654,8686");
	}

	@Test
	public void testJKL() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("1025|1|JKL|100|BUY|ACC-7789|NEW"));
		builder.addTrade(formTrade("1036|1|JKL|100|BUY|ACC-7789|NEW"));
		builder.addTrade(formTrade("1025|2|JKL|100|SELL|ACC-8877|AMEND"));
		checkForPosition(builder, "ACC-7789|JKL|100|1036,1025");
		checkForPosition(builder, "ACC-8877|JKL|-100|1025");
	}

	@Test
	public void testKLOHJK() {
		PositionAggregator builder = new PositionAggregator();
		builder.addTrade(formTrade("1122|1|KLO|100|BUY|ACC-9045|NEW"));
		builder.addTrade(formTrade("1122|2|HJK|100|SELL|ACC-9045|AMEND"));
		builder.addTrade(formTrade("1122|3|KLO|100|SELL|ACC-9045|AMEND"));
		builder.addTrade(formTrade("1144|1|KLO|300|BUY|ACC-9045|NEW"));
		builder.addTrade(formTrade("1144|2|KLO|400|BUY|ACC-9045|AMEND"));
		builder.addTrade(formTrade("1155|1|KLO|600|SELL|ACC-9045|NEW"));
		builder.addTrade(formTrade("1155|2|KLO|0|BUY|ACC-9045|CANCEL"));

		checkForPosition(builder, "ACC-9045|KLO|300|1122,1144,1155");
		checkForPosition(builder, "ACC-9045|HJK|0|1122");
	}

	@Test
	public void problem2Test() {
		aggregator.addTrade(formTrade("1234|1|XYZ|100|BUY|ACC-1234|NEW"));
		aggregator.addTrade(formTrade("1234|2|XYZ|150|BUY|ACC-1234|AMEND"));
		aggregator.addTrade(formTrade("5678|1|QED|200|BUY|ACC-2345|NEW"));
		aggregator.addTrade(formTrade("5678|2|QED|0|BUY|ACC-2345|CANCEL"));
		aggregator.addTrade(formTrade("2233|1|RET|100|SELL|ACC-3456|NEW"));
		aggregator.addTrade(formTrade("2233|2|RET|400|SELL|ACC-3456|AMEND"));
		aggregator.addTrade(formTrade("2233|3|RET|0|SELL|ACC-3456|CANCEL"));
		aggregator.addTrade(formTrade("8896|1|YUI|300|BUY|ACC-4567|NEW"));
		aggregator.addTrade(formTrade("6638|1|YUI|100|SELL|ACC-4567|NEW"));
		aggregator.addTrade(formTrade("6363|1|HJK|200|BUY|ACC-5678|NEW"));
		aggregator.addTrade(formTrade("7666|1|HJK|200|BUY|ACC-5678|NEW"));
		aggregator.addTrade(formTrade("6363|2|HJK|100|BUY|ACC-5678|AMEND"));
		aggregator.addTrade(formTrade("7666|2|HJK|50|SELL|ACC-5678|AMEND"));
		aggregator.addTrade(formTrade("8686|1|FVB|100|BUY|ACC-6789|NEW"));
		aggregator.addTrade(formTrade("8686|2|GBN|100|BUY|ACC-6789|AMEND"));
		aggregator.addTrade(formTrade("9654|1|FVB|200|BUY|ACC-6789|NEW"));
		aggregator.addTrade(formTrade("1025|1|JKL|100|BUY|ACC-7789|NEW"));
		aggregator.addTrade(formTrade("1036|1|JKL|100|BUY|ACC-7789|NEW"));
		aggregator.addTrade(formTrade("1025|2|JKL|100|SELL|ACC-8877|AMEND"));
		aggregator.addTrade(formTrade("1122|1|KLO|100|BUY|ACC-9045|NEW"));
		aggregator.addTrade(formTrade("1122|2|HJK|100|SELL|ACC-9045|AMEND"));
		aggregator.addTrade(formTrade("1122|3|KLO|100|SELL|ACC-9045|AMEND"));
		aggregator.addTrade(formTrade("1144|1|KLO|300|BUY|ACC-9045|NEW"));
		aggregator.addTrade(formTrade("1144|2|KLO|400|BUY|ACC-9045|AMEND"));
		aggregator.addTrade(formTrade("1155|1|KLO|600|SELL|ACC-9045|NEW"));
		aggregator.addTrade(formTrade("1155|2|KLO|0|BUY|ACC-9045|CANCEL"));

		checkForPosition(aggregator, "ACC-1234|XYZ|150|1234");
		checkForPosition(aggregator, "ACC-2345|QED|0|5678");
		checkForPosition(aggregator, "ACC-3456|RET|0|2233");
		checkForPosition(aggregator, "ACC-4567|YUI|200|8896,6638");
		checkForPosition(aggregator, "ACC-5678|HJK|50|6363,7666");
		checkForPosition(aggregator, "ACC-6789|GBN|100|8686");
		checkForPosition(aggregator, "ACC-6789|FVB|200|9654,8686");
		checkForPosition(aggregator, "ACC-7789|JKL|100|1036,1025");
		checkForPosition(aggregator, "ACC-8877|JKL|-100|1025");
	}

	private void checkForPosition(PositionAggregator builder, String string) {
		String[] split = string.split("\\|");
		PositionIdentifier id = new PositionIdentifier(split[0], split[1]);
		Position position = builder.getContainer().getPositionById(id);
		TestCase.assertEquals(position.getQuantity().toString(), split[2]);
		String[] trades = split[3].split(",");
		TestCase.assertEquals(trades.length, position.getTrades().size());
		for (String trade : trades) {
			TestCase.assertTrue(position.getTrades().contains(
					new Integer(trade)));
		}
	}

	private Trade formTrade(String string) {
		String[] split = string.split("\\|");
		Trade t = new Trade(new Integer(split[0]), new Integer(split[1]));
		t.setSecurityId(split[2]);
		t.setQuantity(new Integer(split[3]));
		t.setDirection(Direction.valueOf(split[4]));
		t.setAccountNumber(split[5]);
		t.setOperation(Operation.valueOf(split[6]));
		return t;
	}
}
