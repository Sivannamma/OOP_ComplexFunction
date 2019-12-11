package Ex1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ComplexFunctionTest {

	@Test
	void testF() {
		String[] arr = { "2x+5x^4-30.12x^7+7", "12x+24.003x^2+23.3x^9", "24x-x^3-7", "-10-3x-34.5x-12x^5" };
		double[] ansF = { 14, 118.606, 66, -6077 };
		for (int i = 0; i < arr.length; i++) {
			Polynom left = new Polynom(arr[i]);
			Polynom right = new Polynom(arr[i]);
			function func = new ComplexFunction(left, right, "Plus");
			if (func.f(i) != ansF[i])
				fail("excepted: " + func.f(i) + " actual: " + ansF[i]);
		}
	}

	@Test
	void testInitFromString() {
		String[] expected = { "plus(2x+5x^4,-30.12x^7+7)", "times(12x+24.003x^2,23.3x^9)", "divid(24x-x^3,-7)",
				"Max(-10-3x-34.5x,-12x^5)" };
		String[] arr = { "2x+5x^4", "-30.12x^7+7", "12x+24.003x^2", "+23.3x^9", "24x-x^3", "-7", "-10-3x-34.5x",
				"-12x^5" };
		String[] op = { "plus", "times", "divid", "max" };
		int k = 0;
		for (int i = 0; i < arr.length; i += 2) {
			Polynom left = new Polynom(arr[i]);
			Polynom right = new Polynom(arr[i + 1]);
			function func = new ComplexFunction(left, right, op[k]);
			function func2 = new ComplexFunction(left, right, op[k]);
			func2.initFromString(expected[k]);
			assertEquals(func, func2);
			k++;

		}

	}

	@Test
	void testCopy() {
		String[] arr = { "2x+5x^4-30.12x^7+7", "12x+24.003x^2+23.3x^9", "24x-x^3-7", "-10-3x-34.5x-12x^5" };
		for (int i = 0; i < arr.length - 1; i++) {
			Polynom left = new Polynom(arr[i]);
			Polynom right = new Polynom(arr[i + 1]);
			function func = new ComplexFunction(left, right, "Plus");
			function copy = func.copy();
			assertEquals(func, copy);
		}

	}

	@Test
	void testPlus() {
		String[] arr = { "2x", "2x" };
		String[] expected = { "4x", "0" };
		for (int i = 0; i < 2; i++) {
			Polynom left = new Polynom(arr[0]);
			Polynom right = new Polynom(arr[1]);
			function func = new ComplexFunction(left, right, "Plus");
			Polynom left1 = new Polynom(expected[0]);
			Polynom right1 = new Polynom(expected[1]);
			function expectedFunc = new ComplexFunction(left1, right1, "Plus");
			assertEquals(func, expectedFunc);
		}
	}

	@Test
	void testMul() {
		String[] str = { "2x^7", "4x+2x^2+3+3x^3", "12.5x+0.5", "3x^7+2.222X", "15", "3.88888X^7+12X" };
		String[] OP = { "plus", "divid", "times", "max", "min" };
		String[] expected = { "Times(Plus(+2.0X^7,+3.0X^0+4.0X^1+2.0X^2+3.0X^3),+2.0X^7)",
				"Times(Divid(+3.0X^0+4.0X^1+2.0X^2+3.0X^3,+0.5X^0+12.5X^1),+3.0X^0+4.0X^1+2.0X^2+3.0X^3)",
				"Times(Times(+0.5X^0+12.5X^1,+2.222X^1+3.0X^7),+0.5X^0+12.5X^1)",
				"Times(Max(+2.222X^1+3.0X^7,+15.0X^0),+2.222X^1+3.0X^7)",
				"Times(Min(+15.0X^0,+12.0X^1+3.88888X^7),+15.0X^0)" };
		for (int i = 0; i < str.length - 1; i++) {
			Polynom left = new Polynom(str[i]);
			Polynom right = new Polynom(str[i + 1]);
			ComplexFunction func = new ComplexFunction(left, right, OP[i]);
			func.mul(left);
			assertEquals(func, expected[i]);
		}
	}

	@Test
	void testDiv() {
		String[] str = { "2x^7+3.0x^3-5-67.12x^7", "4x+2x^2+3+3x^3+0", "12.5x+0.5-0.0001x^3", "3x^7+2.222X", "15",
				"3.88888X^7+12X" };
		String[] OP = { "plus", "divid", "times", "max", "min" };
		String[] expected = {
				"Divid(Plus(-5.0X^0+3.0X^3-65.12X^7,+3.0X^0+4.0X^1+2.0X^2+3.0X^3),-5.0X^0+3.0X^3-65.12X^7)",
				"Divid(Divid(+3.0X^0+4.0X^1+2.0X^2+3.0X^3,+0.5X^0+12.5X^1-1.0X^3),+3.0X^0+4.0X^1+2.0X^2+3.0X^3)",
				"Divid(Times(+0.5X^0+12.5X^1-1.0X^3,+2.222X^1+3.0X^7),+0.5X^0+12.5X^1-0.01X^3)",
				"Divid(Max(+2.222X^1+3.0X^7,+15.0X^0),+2.222X^1+3.0X^7)",
				"Divid(Min(+15.0X^0,+12.0X^1+3.88888X^7),+15.0X^0)" };
		for (int i = 0; i < str.length - 1; i++) {
			Polynom left = new Polynom(str[i]);
			Polynom right = new Polynom(str[i + 1]);
			ComplexFunction func = new ComplexFunction(left, right, OP[i]);
			func.div(left);
			assertEquals(func, expected[i]);
		}
	}

	@Test
	void testMax() {
		String[] str = { "x+2.2x^5", "1-3x-19x^2", "1.1x^7-2", "-5.6666x+2.1", "15", "3.88888X^7+12X" };
		String[] OP = { "plus", "divid", "times", "max", "min" };
		String[] expected = { "Max(Plus(+1.0X^1+2.2X^5,+1.0X^0-3.0X^1-19.0X^2),+1.0X^1+2.2X^5)",
				"Max(Divid(+1.0X^0-3.0X^1-19.0X^2,-2.0X^0+2.0X^7),+1.0X^0-3.0X^1-19.0X^2)",
				"Max(Times(-2.0X^0+2.0X^7,+3.0X^0-5.6666X^1),-2.0X^0+2.0X^7)",
				"Max(Max(+3.0X^0-5.6666X^1,+15.0X^0),+3.0X^0-5.6666X^1)",
				"Max(Min(+15.0X^0,+12.0X^1+3.88888X^7),+15.0X^0)" };
		for (int i = 0; i < str.length - 1; i++) {
			Polynom left = new Polynom(str[i]);
			Polynom right = new Polynom(str[i + 1]);
			ComplexFunction func = new ComplexFunction(left, right, OP[i]);
			func.max(left);
			assertEquals(func, expected[i]);
		}
	}

	@Test
	void testMin() {
		String[] str = { "x+3", "0.5x-15x^5", "12x-12.0", "102.5x^1+5", "15+x", "-1" };
		String[] OP = { "plus", "divid", "times", "max", "min" };
		String[] expected = { "Min(Plus(+3.0X^0+1.0X^1,+0.5X^1-15.0X^5),+3.0X^0+1.0X^1)",
				"Min(Divid(+0.5X^1-15.0X^5,-12.0X^0+12.0X^1),+0.5X^1-15.0X^5)",
				"Min(Times(-12.0X^0+12.0X^1,+5.0X^0+102.5X^1),-12.0X^0+12.0X^1)",
				"Min(Max(+5.0X^0+102.5X^1,+15.0X^0+1.0X^1),+5.0X^0+102.5X^1)",
				"Min(Min(+15.0X^0+1.0X^1,-1.0X^0),+15.0X^0+1.0X^1)" };
		for (int i = 0; i < str.length - 1; i++) {
			Polynom left = new Polynom(str[i]);
			Polynom right = new Polynom(str[i + 1]);
			ComplexFunction func = new ComplexFunction(left, right, OP[i]);
			func.min(left);
			assertEquals(func, expected[i]);
		}
	}

	@Test
	void testComp() {
		Polynom left = new Polynom("x+3");
		Polynom right = new Polynom("x+2");
		ComplexFunction func1 = new ComplexFunction(left, left, "times");
		ComplexFunction func2 = new ComplexFunction(right, right, "divid");
		func1.comp(func2);
		assertEquals(func1, "Comp(Times(+3.0X^0+1.0X^1,+3.0X^0+1.0X^1),Divid(+2.0X^0+1.0X^1,+2.0X^0+1.0X^1))");

	}

	@Test
	void testLeft() {
		Polynom left = new Polynom("x^2+6x-9");
		Polynom right = new Polynom("4x^3-8.5");
		ComplexFunction func1 = new ComplexFunction(left, right, "min");
		assertEquals(func1.left(), left);
		assertEquals(func1.right(), right);

	}

	@Test
	void testRight() {
		Polynom left = new Polynom("x+1");
		Polynom right = new Polynom("x");
		ComplexFunction func1 = new ComplexFunction(left, left, "times");
		ComplexFunction func2 = new ComplexFunction(right, func1, "plus");
		assertEquals(func2.right(), func1);
	}

	@Test
	void testGetOp() {
		Polynom left = new Polynom("x^2+6x-9");
		Polynom right = new Polynom("4x^3-8.5");
		ComplexFunction func1 = new ComplexFunction(left, right, "min");
		assertEquals(func1.getOp(), Operation.Min);
	}

}
