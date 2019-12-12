package Ex1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Iterator;
import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;

/**
 * Note: minor changes (thanks to Elad!!) The use of "get" was replaced by
 * iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from
 * the main: 0) java.awt.Color[r=0,g=0,b=255] f(x)= plus(-1.0x^4 +2.4x^2
 * +3.1,+0.1x^5 -1.2999999999999998x +5.0) 1) java.awt.Color[r=0,g=255,b=255]
 * f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0) 2)
 * java.awt.Color[r=255,g=0,b=255] f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5
 * -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1) 3)
 * java.awt.Color[r=255,g=200,b=0] f(x)= -1.0x^4 +2.4x^2 +3.1 4)
 * java.awt.Color[r=255,g=0,b=0] f(x)= +0.1x^5 -1.2999999999999998x +5.0 5)
 * java.awt.Color[r=0,g=255,b=0] f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2
 * +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x
 * +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5
 * -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2
 * +3.1),+0.1x^5 -1.2999999999999998x +5.0) 6) java.awt.Color[r=255,g=175,b=175]
 * f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x
 * +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x
 * -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x
 * +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5
 * -1.2999999999999998x +5.0)
 * 
 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {
	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = { "x +3", "x -2", "x -4" };
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for (int i = 1; i < s3.length; i++) {
			cf3.mul(new Polynom(s3[i]));
		}

		ComplexFunction cf = new ComplexFunction(p1, p2, Operation.Plus);
		ComplexFunction cf4 = new ComplexFunction(new Polynom("x +1"), cf3, "DIVId");
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while (iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);
		return ans;
	}

	public static void main(String[] a) throws IOException {
		functions data = FunctionsFactory(); // checking if the data created is working with the outside function
		data.saveToFile("func.txt");
	}

	@Test
	void testFunctions_GUI() {
		function f1 = new ComplexFunction("Plus(Times(2x,2x),2x)");
		function f2 = new ComplexFunction("Divid(max(2x+54,2x),Plus(54x+2x^5,6))");
		function f3 = new ComplexFunction("min(plus(2x,2x),0)");
		Functions_GUI gui = new Functions_GUI();
		gui.arr_func.add(f1);
		gui.arr_func.add(f2);
		gui.arr_func.add(f3);
		gui.remove(f1);
		gui.remove(f2);
		System.out.println("after Removing " + gui.arr_func.toString());
		System.out.println("does f2 contains?(should be false cuz we removed it ), ans : " + gui.arr_func.contains(f2));
		gui.arr_func.clear();
		System.out.println("after clearing " + gui.arr_func.toString());
	}

	@Test
	void testInitFromFile() throws IOException {
		Functions_GUI gui = new Functions_GUI();
		gui.initFromFile("func.txt");
		System.out.println(gui.arr_func.toString()); // it shows that we did inserts function into the list
	}

	@Test
	void testSaveToFile() throws IOException {
		function f1 = new ComplexFunction("Plus(Times(2x,2x),2x)");
		function f2 = new ComplexFunction("Divid(max(2x+54,2x),Plus(54x+2x^5,6))");
		function f3 = new ComplexFunction("min(plus(2x,2x),0)");
		Functions_GUI gui = new Functions_GUI();
		gui.arr_func.add(f1);
		gui.arr_func.add(f2);
		gui.arr_func.add(f3);
		gui.saveToFile("func.txt");
	}

	@Test
	void testDrawFunctions() throws IOException {
		function f1 = new ComplexFunction("Plus(Times(2x,2x),2x)");
		function f2 = new ComplexFunction("Divid(max(2x+54,2x),Plus(54x+2x^5,6))");
		function f3 = new ComplexFunction("min(plus(2x,2x),0)");
		Functions_GUI gui = new Functions_GUI();
		gui.arr_func.add(f1);
		gui.arr_func.add(f2);
		gui.arr_func.add(f3);
		Range x = new Range(-5, 25);
		Range y = new Range(10, -10);
		gui.drawFunctions(800, 600, x, y, 100);

	}

	@Test
	void ReadingFromJasonFileTest() throws IOException {
		String fileFunction = "func.txt";
		String fileJson = "GUI_params.txt";
		Functions_GUI gui = new Functions_GUI();
		gui.initFromFile(fileFunction);
		gui.drawFunctions(fileJson); // the other function
	}
}
