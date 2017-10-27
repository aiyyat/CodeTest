package com.technicalyorker.telepathy;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

/*-
 *
 * @author achuth
 *			            A
 *         B                  C
 *    D         E          F     G
 * H     I   J     K    L     M
 */
public class LinkingNodeProblemTest {

	private static final Node h = new Node("H");
	private static final Node i = new Node("I");
	private static final Node j = new Node("J");
	private static final Node k = new Node("K");
	private static final Node l = new Node("L");
	private static final Node m = new Node("M");
	private static final Node d = new Node("D", new Node[] { h, i });
	private static final Node e = new Node("E", new Node[] { j, k });
	private static final Node f = new Node("F", new Node[] { l, m });
	private static final Node g = new Node("G");
	private static final Node b = new Node("B", new Node[] { d, e });
	private static final Node c = new Node("C", new Node[] { f, g });
	private static final Node a = new Node("A", new Node[] { b, c });
	private static final LinkingNodeProblem link = new LinkingNodeProblem();

	@BeforeClass
	public static void initiate() {
		link.perform(a);
	}

	@Test
	public void assertLevel1() {
		TestCase.assertNull(a.getRight());
	}

	@Test
	public void assertLevel2() {
		TestCase.assertEquals(b.getRight().getName(), c.getName());
		TestCase.assertNull(c.getRight());
	}

	@Test
	public void assertLevel3() {
		TestCase.assertEquals(d.getRight().getName(), e.getName());
		TestCase.assertEquals(e.getRight().getName(), f.getName());
		TestCase.assertEquals(f.getRight().getName(), g.getName());
		TestCase.assertNull(g.getRight());
	}

	@Test
	public void assertLevel4() {
		TestCase.assertEquals(h.getRight().getName(), i.getName());
		TestCase.assertEquals(i.getRight().getName(), j.getName());
		TestCase.assertEquals(j.getRight().getName(), k.getName());
		TestCase.assertEquals(k.getRight().getName(), l.getName());
		TestCase.assertEquals(l.getRight().getName(), m.getName());
		TestCase.assertNull(m.getRight());
	}
}
