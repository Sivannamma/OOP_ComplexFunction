package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

//	@Test
//	void testComplexFunction() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testF() {
//		Polynom p1 = new Polynom("7+7x+5x^2");
//		Polynom p2 = new Polynom("7x+5x^2");
//		Polynom p3 = new Polynom("2x+3x^2");
//		ComplexFunction func1 = new ComplexFunction(p2, p3, "Times");
//		ComplexFunction func2 = new ComplexFunction(p1, func1, "plus");
//		System.out.println(func2.f(1));
//	}

	@Test
	void testInitFromString() {
		Polynom p1 = new Polynom("5");
		Polynom p2 = new Polynom("10X");
		function f1 = new ComplexFunction(p1, p2, "plus");
		f1 = f1.initFromString("plus(Times(2x,2x),2)");
		System.out.println(f1.toString());
		System.out.println(f1.f(1));
		// times(mul(plus(2x,2x),5),10)
<<<<<<< HEAD
		// plus(Times(2x,2x),plus(2,2x))
		// Times(2x,plus(2x,2x))2x))
=======
// plus(Times(2x,2x),plus(2,2x))
//Times(2x,plus(2x,2x))2x))
>>>>>>> 01d1cd5c54b97323e172d07cb23da82f8aebd6b7
	}

//	@Test
//	void testCopy() {
//
//	}
//
//	@Test
//	void testPlus() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMul() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDiv() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMax() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testComp() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testLeft() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRight() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetOp() {
//		fail("Not yet implemented");
//	}

}
