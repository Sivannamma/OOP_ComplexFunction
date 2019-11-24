package myMath;

public class PolynomTest {
	public static void main(String[] args) {

		Polynom p1 = new Polynom("x^2+20X");
		System.out.println(p1.root(0, 115, 0.0001));
//
//		test1();
//		test2();
	}

	public static void test1() {
		Polynom p1 = new Polynom("X+1");
		Polynom p2 = new Polynom("12X^2-20X+12");
		String[] monoms = { "1", "X", "X^2", "0.5X^2" };
		// for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		System.out.println("is this?" + p1);
		double aa = p1.area(0, 1, 0.0001);
		System.out.println(aa);
		p1.substract(p1);
		System.out.println(p1);
	}

	public static void test2() {
		Polynom p1 = new Polynom(), p2 = new Polynom();
		String[] monoms1 = { "2", "-X", "-3.2X^2", "4", "-1.5X^2" };
		String[] monoms2 = { "5", "1.7X", "3.2X^2", "-3", "-1.5X^2" };
		for (int i = 0; i < monoms1.length; i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for (int i = 0; i < monoms2.length; i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		p1.add(p2);
		System.out.println("p1+p2: " + p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: " + p1);
		String s1 = p1.toString();
//		Polynom_able pp1 = Polynom.parse(s1);
//		System.out.println("from string: " + pp1);
	}
}
