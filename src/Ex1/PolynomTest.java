package Ex1;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class PolynomTest {

	@Test
	void add() {
		String[] m1 = { "0x+x", "5x+1", "0.25x^2+6.12x^5", "1", "123.2x+0.42x^4" };
		String[] m2 = { "5.99x+20x^3", "x^2+0", "2", "x", "-123.2x" };
		String[] afterAdd = { "6.99x+20x^3", "5x+x^2+1", "0.25x^2+6.12x^5+2", "x+1", "0X+0.42x^4" };
		for (int i = 0; i < m2.length; i++) {
			Polynom expected = new Polynom(afterAdd[i]);
			Polynom actual = new Polynom(m1[i]);
			actual.add(new Polynom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test
	void testPolynomString() {
		String[] strings = { "0", "5x+12.359x^5", "+12x^4+67.22X", "-1+2x+3x^2" };
		String[] expected = { "+0.0X^0", "+5.0X^1+12.359X^5", "+67.22X^1+12.0X^4", "-1.0X^0+2.0X^1+3.0X^2" };
		for (int i = 0; i < strings.length; i++) {
			Polynom actual = new Polynom(strings[i]);
			assertEquals(actual.toString(), expected[i]);
		}
	}

	@Test
	void testF() {
		String[] strings = { "0", "2x+2x^2-2x+34.12x^5", "12.23+45X^3-19x^5", "12+3x-17x^5", "120-12x-0x^5" };
		double[] actual = { 0, 36.12, -235.77, -4110, 72 };
		for (int i = 0; i < strings.length; i++) {
			Polynom p = new Polynom(strings[i]);
			// the value that we send to the f function is i (from the for)
			if (p.f(i) != actual[i]) {
				throw new RuntimeException("expected: " + actual[i] + " actual: " + p.f(i));
			}
		}
	}

	@Test
	void testAddMonom() {
		String[] m1 = { "0x", "5x", "0.25x^2", "6x^3", "123.2x" };
		String[] m2 = { "5.99x", "x^2", "0.25x^2", "x", "-123.2x" };
		String[] afterAdd = { "5.99x", "5x+x^2", "0.5x^2", "6x^3+x", "0X" };
		for (int i = 0; i < m2.length; i++) {
			Polynom expected = new Polynom(afterAdd[i]);
			Polynom actual = new Polynom(m1[i]);
			actual.add(new Monom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test
	void testSubstract() {

		Polynom p1 = new Polynom("-123.2x+0.42x^4");
		Polynom p2 = new Polynom("-123x");
		Polynom p3 = new Polynom("-0.2x+0.42x^4");
		p1.substract(p2);
		assertEquals(p1, p3);

		String[] m1 = { "12x^4+5", "5x+1", "5.999999x^12+3x-0.012x^1-5", "1", "-123.2x+0.42x^4" };
		String[] m2 = { "12x^4+5", "x^2+0", "2", "x", "-123x" };
		String[] afterSub = { "0", "5x+1-1.0x^2", "5.999999x^12+3x-0.012x^1-7", "1-x", "-0.2x+0.42x^4" };
		for (int i = 0; i < m2.length; i++) {
			Polynom expected = new Polynom(afterSub[i]);
			Polynom actual = new Polynom(m1[i]);
			actual.substract(new Polynom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test
	void testMultiplyPolynom_able() {
		String[] m1 = { "x+2", "x+6", "0.5x+1", "1", "-123.2x+0.42x^4" };
		String[] m2 = { "x+2", "x+4", "5x", "-x", "0" };
		String[] afterMul = { "x^2+4x+4.0", "10x+x^2+24", "5x+2.5x^2", "-x", "0x^1+0x^4" };
		for (int i = 0; i < m2.length; i++) {
			Polynom expected = new Polynom(afterMul[i]);
			Polynom actual = new Polynom(m1[i]);
			actual.multiply(new Polynom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test
	void testEqualsObject() {
		String[] q1 = { "18x^3+0", "5x+1", "0.12x^4", "1", "12x^5", "-12", "56.12x^5" };
		String[] q2 = { "18.0x^3+0x", "4x+x+0.5+0.5", "0.12x^4", "1", "12.0x^5", "-12.0", "56.0x^5+0.12x^5" };
		for (int i = 0; i < q1.length; i++) {
			Polynom expected = new Polynom(q1[i]);
			Polynom actual = new Polynom(q2[i]);
			assertEquals(actual, expected);
		}
	}

	@Test
	void testIsZero() {
		String[] m = { "12x-12x+0x", "3x^3- x^3 +5-5-2.0x^3", "0", "0.00000x^4" };
		for (int i = 0; i < m.length; i++) {
			Polynom actual = new Polynom(m[i]);
			assertEquals(actual.isZero(), true);
		}
	}

	@Test
	void testRoot() {
	}

	@Test
	void testCopy() {
		String[] strings = { "6.99x+20x^3+5x+2+5X^3", "5x+x^2+1+12x^3-102x^87", "0.25x^2+6.12x^5+2", "x+1+x^2-5x^3",
				"0X+0.42x^4" };
		for (int i = 0; i < strings.length; i++) {
			Polynom p1 = new Polynom(strings[i]);
			Polynom p2 = (Polynom) p1.copy();
			assertEquals(p2, p1);
		}
	}

	@Test
	void testDerivative() {
		String[] m1 = { "3x+5x^2+4", "x^2+12.2x^3", "0.12x+3x^3", "1" };
		String[] afterDer = { "3+10x", "2.0x+36.6x^2", "0.12+9x^2", "0" };
		for (int i = 0; i < m1.length; i++) {
			Polynom expected = new Polynom(afterDer[i]);
			Polynom actual = new Polynom(m1[i]);
			assertEquals(actual.derivative(), expected);
		}
	}

	@Test
	void testMultiplyMonom() {
		String[] m1 = { "x+2", "x+6", "0.5x+1", "1", "-123.2x+0.42x^4" };
		String[] m2 = { "1", "2", "5", "-4", "0" };
		String[] afterMul = { "x+2", "2.0x+12.0", "5+2.5x^1", "-4", "0x^1+0x^4" };
		for (int i = 0; i < m2.length; i++) {
			Polynom expected = new Polynom(afterMul[i]);
			Polynom actual = new Polynom(m1[i]);
			actual.multiply(new Polynom(m2[i]));
			assertEquals(actual, expected);
		}
	}

	@Test // ******** to add
	void testInitFromString() {

	}

}
