package Ex1;

public class PolynomTestEvgeny {
	static int fails = 0;

	public static void main(String[] args) {
	
		Monom m = new Monom("-.012x^1");
		System.out.println(m);
//		try {
//			test1();
//		} catch (Exception e) {
//			System.out.println("test1 exception");
//			fails = fails + 3;
//		}
//		try {
//			test2();
//		} catch (Exception e) {
//			System.out.println("test2 exception");
//			fails = fails + 3;
//		}
//		try {
//			test3();
//		} catch (Exception e) {
//			System.out.println("test3 exception");
//			fails = fails + 3;
//		}
//		try {
//			test4();
//		} catch (Exception e) {
//			System.out.println("test4 exception");
//			fails = fails + 3;
//		}
//		try {
//			test5();
//		} catch (Exception e) {
//			System.out.println("test5 exception");
//			fails = fails + 3;
//		}
//		try {
//			test6();
//		} catch (Exception e) {
//			System.out.println("test6 exception");
//			fails = fails + 3;
//		}
//		try {
//			test7();
//		} catch (Exception e) {
//			System.out.println("test7 exception");
//			fails = fails + 3;
//		}
//		System.out.println("num of fails: " + fails);
	}

	public static boolean compDouble(double a, double b) {
		double diff = a - b;
		if (a == b || (diff < 0.01 && diff > -0.01))
			return true;
		return false;
	}

	public static void test1() throws Exception {
		System.out.println("******  Test1:  substruct and is zero******");
		Polynom_able p1 = new Polynom();
		String[] monoms = { "2", "-x", "-3.2x^2", "4", "-1.5x^2" };
		for (int i = 0; i < monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}

		p1.substract(p1);
		if (!p1.isZero()) {
			fails++;
			System.out.println("failed is zero");
		}

	}

	public static void test2() throws Exception {
		System.out.println("******  Test2: add creat from self string******");
		Polynom_able p1 = new Polynom(), p2 = new Polynom();
		String[] monoms1 = { "2", "-x", "-3.2x^2", "4", "-1.5x^2" };
		String[] monoms2 = { "5", "1.7x", "3.2x^2", "-3", "-1.5x^2" };
		for (int i = 0; i < monoms1.length; i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for (int i = 0; i < monoms2.length; i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		// System.out.println("p1: "+p1);
		// System.out.println("p2: "+p2);
		if (!p1.equals(new Polynom(p1.toString()))) {
			fails++;
			System.out.println("self check from string failed");
		}
		if (!p1.equals(new Polynom("-4.7x^2-1.0x+6.0"))) {
			fails++;
			System.out.println("failed to creat poly with spases");
		}

	}

	public static void test3() throws Exception {// test for area and root.
		System.out.println("******  Test3 area and root:  ******");
		String[][] polynoms = { { "3x^2", "-6x^3", "9x", "-2" }, { "x", "5x", "0", "-5" }, { "4x^6", "-5x^5", "1" } };
		double[][] res = { { 0, 0.2135 }, { 0, 0.83334 }, { 2.404, 0.9999 } };
		for (int i = 0; i < polynoms.length; i++) {
			Polynom p1 = new Polynom();
			for (int j = 0; j < polynoms[i].length; j++) {
				Monom temp = new Monom(polynoms[i][j]);
				p1.add(temp);
			}
			if (!compDouble(p1.area(-1, 0, 0.0001), res[i][0])) {
				fails++;
				System.out.println("area missmatch");
			}
			if (!compDouble(p1.root(0, 1, 0.0001), res[i][1])) {
				fails++;
				System.out.println(" root missmatch");
			}

		}
	}

	public static void test4() throws Exception {// test for add and substract.
		System.out.println("******  Test4: add and substract ******");
		String[][] polynoms = { { "3x^2", "-6x^3", "9x", "-2" }, { "x", "5x", "-5" },
				{ "-6.0x^3", "3.0x^2", "15.0x", "-7.0" } };
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		for (int i = 0; i < polynoms[0].length; i++) {
			Monom temp = new Monom(polynoms[0][i]);
			p1.add(temp);
		}
		for (int i = 0; i < polynoms[1].length; i++) {
			Monom temp = new Monom(polynoms[1][i]);
			p2.add(temp);
		}
		for (int i = 0; i < polynoms[2].length; i++) {
			Monom temp = new Monom(polynoms[2][i]);
			p3.add(temp);
		}
		Polynom add = new Polynom();
		Polynom substract = new Polynom();
		add = (Polynom) p1.copy();
		substract = (Polynom) p1.copy();
		add.add(p2);
		substract.substract(p2);
		if (!add.equals(p3)) {
			fails++;
			System.out.println("fail add");
			System.out.println(add);
			System.out.println(p3);
		}
	}

	public static void test5() throws Exception {// test for multiply.
		System.out.println("******  Test5: multiply ******");
		String[][] polynoms = { { "3x^2", "-6x^3", "9x", "-2" }, { "x", "3x^3" },
				{ "-18.0x^6", "9.0x^5", "21.0x^4", "-3.0x^3", "9.0x^2", "-2.0x" } };
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		for (int i = 0; i < polynoms[0].length; i++) {
			Monom temp = new Monom(polynoms[0][i]);
			p1.add(temp);
		}
		for (int i = 0; i < polynoms[1].length; i++) {
			Monom temp = new Monom(polynoms[1][i]);
			p2.add(temp);
		}

		for (int i = 0; i < polynoms[2].length; i++) {
			Monom temp = new Monom(polynoms[2][i]);
			p3.add(temp);
		}
		Polynom multiply = new Polynom();
		multiply = (Polynom) p1.copy();
		multiply.multiply(p2);

		if (!multiply.equals(p3)) {
			fails++;
			System.out.println("fail mult");
		}

	}

	public static void test6() throws Exception {// test for equals.
		System.out.println("******  Test6 equals:  ******");
		String[][] polynoms = { { "3x^2", "-6x^3", "9x", "-2" }, { "x", "5x", "0", "-5" },
				{ "3.00000001x^2", "-5.9999999999x^3", "9.000000001x", "-2" } };
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		Polynom p3 = new Polynom();
		for (int i = 0; i < polynoms[0].length; i++) {
			Monom temp = new Monom(polynoms[0][i]);
			p1.add(temp);
		}
		for (int i = 0; i < polynoms[1].length; i++) {
			Monom temp = new Monom(polynoms[1][i]);
			p2.add(temp);
		}

		for (int i = 0; i < polynoms[2].length; i++) {
			Monom temp = new Monom(polynoms[2][i]);
			p3.add(temp);
		}

		if (p1.equals(p2)) {
			fails++;
			System.out.println("fail equals");
		}

		if (!p1.equals(p3)) {
			fails++;
			System.out.println("fail equals no numeric checks");
		}

	}

	public static void test7() throws Exception { // test deep copy
		// System.out.println("****** Test7 : Copy and creat from string with space
		// ******");
		// Polynom_able p1 = new Polynom("-4.7x^2-1.0x+6.0");
		// Polynom_able p2 = p1.copy();
//		if (!p1.equals(p2)) {
//			fails++;
//			System.out.println("fail copy");
//		}
		// p2.add(new Monom("x^2"));
//		if (p1.equals(p2)) {
//			fails++;
//			System.out.println("fail deep copy");
//		}
	}

}