package Ex1;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testMonomMonom() {
		// fail("Not yet implemented");
	}

	@Test
	void testF() {
		// fail("Not yet implemented");
	}

	@Test
	void testIsZero() {
		// fail("Not yet implemented");
	}

	@Test
	void testMonomString() {
		// fail("Not yet implemented");
	}

	@Test
	void testAdd() {
//		Monom m1=new Monom("15.12x^12");
//		Monom m2=new Monom("0.88x^12");
//		m1.add(m2);
//		Monom m3= new Monom("16x^12");
//		assertEquals(m1,m3);
	}

	@Test
	void testMultipy() {
		Monom m1=new Monom("10x^3");
		Monom m2=new Monom("2x");
		m1.multipy(m2);
		Monom m3= new Monom("20.0x^4");
		assertEquals(m1,m3);
		
	}

	@Test
	void testIsEqual() {
//		String[] arr1 = { "12.0x", "123.10x^1", "5x^5" };
//		String[] arr2 = { "12x", "123.10x", "5.5x^5" };
//		int i = 0;
//		while (i < arr1.length) {
//			assertEquals(new Monom(arr1[i]), new Monom(arr2[i]));
//			i++;
//		}
	}

	@Test
	void testInitFromString() {
		// fail("Not yet implemented");
	}

	@Test
	void testCopy() {
		// fail("Not yet implemented");
	}

}
