package com.kazurayam.katalon.keyword

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
public class MethodParametersTest {
	
	private MethodParameters sig1
	private MethodParameters sig2
	private MethodParameters sig3
	private MethodParameters sig4
	
	@Before
	public void setup() {
		sig1 = new MethodParameters(Arrays.asList(String.class, Integer.class))
		sig2 = new MethodParameters(Arrays.asList(String.class, Integer.class))
		sig3 = new MethodParameters(Arrays.asList(Double.class))
		sig4 = new MethodParameters(Arrays.asList())
	}
	
	@Test
	public void testEquals() {
		assertTrue(sig1.equals(sig2))
	}
	
	@Test
	public void testNotEqual() {
		assertTrue(!sig1.equals(sig3))
	}
	
	@Test
	public void testHashCode() {
		assertEquals(sig1.hashCode(), sig2.hashCode())
		assertNotEquals(sig1.hashCode(), sig3.hashCode())
	}
	
	@Test
	public void testToString() {
		println sig1.toString()
		assertEquals("(java.lang.String, java.lang.Integer)", sig1.toString())
		assertEquals("(java.lang.Double)", sig3.toString())
		assertEquals("()", sig4.toString())
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, sig1.compareTo(sig2))
		assertNotEquals(0, sig1.compareTo(sig3))
		assertTrue(sig1.compareTo(sig4) < 0)
	}
}
