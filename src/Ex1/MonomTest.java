package Ex1;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testF() {
		String[] strings = { "0X", "5x^2", "3", "5.99998x", "12.5x^4" };
		double[] actual = { 0, 5, 3, 17.99994, 3200 };
		for (int i = 0; i < strings.length; i++) {
			Monom m = new Monom(strings[i]);
			// the value that we send to the f function is i (from the for)
			if (m.f(i) != actual[i]) {
				throw new RuntimeException("expected: " + actual[i] + " actual: " + m.f(i));
			}
		}
	}

	@Test
	void testIsZero() {
		String[] strings = { "0", "0.0X", "0x^3", "0.0000x^3" };
		for (int i = 0; i < strings.length; i++) {
			Monom m = new Monom(strings[i]);
			// the value that we send to the f function is i (from the for)
			if (!m.isZero()) {
				throw new RuntimeException("The monom :" + m + " should have been equal to zero");
			}
		}
		// checking if adding the same monom one positive and one negative
		// gives the value 0 - for isZero check.
		Monom m1 = new Monom("5x^3");
		Monom m2 = new Monom("-5x^3");
		m1.add(m2);
		if (!m1.isZero()) {
			throw new RuntimeException("The monom should have been equal to zero");
		}

	}

	@Test
	void testMonomString() {
		// in this function we will check that after we do toString
		// we can still use the string we get as initializing another monom
		String[] m1 = { "12.12x^2", "0.25x^2", "6x^3", "0", "14.0x", "0.0x^0" };
		for (int i = 0; i < m1.length; i++) {
			Monom expected = new Monom(m1[i]);
			Monom actual = new Monom(expected.toString());
			assertEquals(actual, expected);
		}

	}

	@Test
	void testAdd() {

		String[] m1 = { "5x", "0.25x^2", "6x^3", "123.2x" };
		String[] m2 = { "x", "0.25x^2", "1.23x^3", "-123.2x" };
		String[] afterAdd = { "6.0X", "0.5x^2", "7.23x^3", "0X" };
		for (int i = 0; i < m2.length; i++) {
			Monom expected = new Monom(afterAdd[i]);
			Monom actual = new Monom(m1[i]);
			actual.add(new Monom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test
	void testMultipy() {
		Monom m1 = new Monom("10x^3");
		Monom m2 = new Monom("2x");
		m1.multipy(m2);
		Monom m3 = new Monom("20.0x^4");
		assertEquals(m1, m3);

	}

	@Test
	void testIsEqual() {
		String[] arr1 = { "12.0x", "123.10x^1", "5x^5", "0" };
		String[] arr2 = { "12x^1", "123.10x", "5.0x^5", "0.0" };
		for (int j = 0; j < arr2.length; j++) {
			assertEquals(new Monom(arr1[j]), new Monom(arr2[j]));
		}
	}

	@Test
	void testInitFromString() {
		// this test is excatly like the test of the constructor of the monom
		// who gets a string as an initializer
		String[] arr1 = { "12.0x", "123.10x^1", "5x^5", "0", "12x", "36.003x", "8.9999x^8", "71.12x^90", "3.12",
				"0.0001x", "x", "-x", "-1" };
		for (int i = 0; i < arr1.length; i++) {

			try {
				Monom m = new Monom(arr1[i]);
				function func = m.initFromString(m.toString());
			} catch (Exception e) {
				throw new RuntimeException("all monoms are legal, should not throw an exception");
			}
		}
	}

	@Test
	void testCopy() {
		String[] strings = { "0X", "5x^2", "3", "5.99998x", "12.5x^4" };
		for (int i = 0; i < strings.length; i++) {
			Monom m1 = new Monom(strings[i]);
			Monom m2 = (Monom) m1.copy();
			assertEquals(m1, m2);
		}
	}
}
