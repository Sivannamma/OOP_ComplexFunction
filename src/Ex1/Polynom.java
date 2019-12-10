package Ex1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

import Ex1.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	private HashMap<Integer, Double> map;
	private HashMap<Integer, Double> mapNew;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		map = new HashMap<>();
		map.put(0, 0.0);

	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x",
	 * "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	private boolean ifToRoundAbove(String str) {
		// to reach the dot
		int j = 0;
		for (j = 0; j < str.length() && str.charAt(j) != '.'; j++)
			;
		// to count if theres '99' -> than we round
		for (int i = j + 1; i < str.length() - 1; i++) {
			if (str.charAt(i) == '9' && str.charAt(i + 1) == '9') {
				return true;
			}
		}
		return false;
	}

	public Polynom(String s) {
		if (s == "") {
			throw new RuntimeException("empty polynom exception");
		}
		s = s.toUpperCase(); // in case we got x in the polynom, we check for X
		String temp = "";
		map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '+' && i != 0 || (s.charAt(i) == '-' && i != 0)) {
				Monom m = new Monom(temp);
				if (map.containsKey(m.get_power())) // checking if exsist
					map.put(m.get_power(), map.get(m.get_power()) + m.get_coefficient());
				else
					map.put(m.get_power(), m.get_coefficient());
				temp = "";
			}
			if (s.charAt(i) == ' ')
				continue;
			temp += s.charAt(i);
		}

		Monom m = new Monom(temp); // adding the last one or if its just 1 monom
		if (map.containsKey(m.get_power())) // checking if exsist
			map.put(m.get_power(), map.get(m.get_power()) + m.get_coefficient());
		else
			map.put(m.get_power(), m.get_coefficient());
	}

	@Override
	public double f(double x) {
		Iterator<Monom> iter = this.iteretor(); // iterator of this object
		double ans = 0;

		while (iter.hasNext()) { // going over the map
			ans += iter.next().f(x); // suming
		}
		return (ifToRoundAbove(ans + "")) ? round(ans, 2) : ans;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor(); // iterator of p1
		while (iter.hasNext()) { // going over the object
			this.add(iter.next()); // sending each monom to the add function
		}
	}

	@Override
	public void add(Monom m1) {

		if (this.map.containsKey(m1.get_power())) // if the power exsists- adding to it
		{
			this.map.put(m1.get_power(), m1.get_coefficient() + map.get(m1.get_power()).doubleValue());

		} else { // else- adding as a new variable
			this.map.put(m1.get_power(), m1.get_coefficient());

		}

	}

	private void substract(Monom m1) {
		if (this.map.containsKey(m1.get_power())) // if the power of the monom exsits in the polynom
		{
			this.map.put(m1.get_power(), map.get(m1.get_power()).doubleValue() - m1.get_coefficient());

		} else {
			this.map.put(m1.get_power(), m1.get_coefficient() * -1);
		}
	}

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor(); // iterator of p1
		while (iter.hasNext()) { // going over the object
			this.substract(iter.next()); // sending each monom to be substructed
		}
	}

	public HashMap<Integer, Double> getMap() { // getter
		return map;
	}

	public void setMap(HashMap<Integer, Double> map) { // setter
		this.map = map;
	}

	@Override
	public void multiply(Polynom_able p1) {
		mapNew = new HashMap<>(); // making a new hash
		Iterator<Monom> iter = p1.iteretor(); // iterator of p1
		while (iter.hasNext()) { // going over the object
			multiply(iter.next()); // sending each monom to be multiplied
		}
		map = mapNew; // change the pointer to the new hash.
	}

	@Override
	public boolean equals(Object p1) {
		if (p1 instanceof Polynom) {
			Polynom_able polynom = ((Polynom) p1).copy();
			Iterator<Monom> iterOther = polynom.iteretor(); // iterator of p1
			Iterator<Monom> iterThis = this.iteretor();
			if (polynom.isZero() && this.isZero())
				return true;
			while (iterOther.hasNext() && iterThis.hasNext()) {
				// using a function in monom class
				if (!iterThis.next().equals(iterOther.next())) // checking if the coefficent isnt equals to 0
					return false;

			}
			return iterOther.next() == null && iterThis.next() == null; // confirming we went over all the monoms
		}
		return false;
	}

	@Override
	public boolean isZero() {
		Iterator<Monom> iter = this.iteretor(); // iterator of this map
		while (iter.hasNext()) {
			if (iter.next().get_coefficient() != 0) // checking if the coefficent isnt equals to 0
				return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if (f(x0) * f(x1) > 0)
			throw new RuntimeException();

		// to know which one
		if (f(x0) > f(x1)) {
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}

		double x2 = (x0 + x1) / 2;
		while (Math.abs(f(x2)) > eps) {
			if (f(x2) < 0)
				x0 = x2;
			else
				x1 = x2;
			x2 = (x0 + x1) / 2;
		}

		return f(x2);
	}

	@Override
	public Polynom_able copy() {
		String str = this.toString();
		Polynom_able other = new Polynom(str);
		return other;
	}

	@Override
	public Polynom_able derivative() {
		String der = "";
		Iterator<Monom> iter = this.iteretor(); // iterator of this map
		while (iter.hasNext()) { // going ober this map
			der += iter.next().derivative(); // putting into a string
		}
		Polynom_able ans = new Polynom(der);
		return ans;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double height;
		double areaSum = 0;
		for (double i = x0; i < x1; i += eps) {
			height = this.f(i);
			if (height < 0)
				throw new RuntimeException("negative area exception"); // we got a value under axis x
			areaSum += height * eps;
		}
		return areaSum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		Iterator<Monom> iter = new MyMonomIterator(this);
		return iter;
	}

	@Override
	public void multiply(Monom m1) {

		for (Map.Entry<Integer, Double> entry : map.entrySet()) { // itearte through the old hash
			if (mapNew.containsKey(entry.getKey() + m1.get_power())) {
				mapNew.put(entry.getKey() + m1.get_power(),
						entry.getValue() * m1.get_coefficient() + mapNew.get(entry.getKey() + m1.get_power()));
			}

			else
				mapNew.put(entry.getKey() + m1.get_power(), m1.get_coefficient() * entry.getValue()); // setting the new
																										// key and value

		}

	}

	public String toString() {
		String str = "";
		Iterator<Monom> iterC = this.iteretor(); // iterator for the coefficent
		while (iterC.hasNext()) {
			str += iterC.next().toString();
		}
		return str;
	}

	@Override
	public function initFromString(String s) {
		function func = new Polynom(s); // creating a new function from Polynom constructor
		return func;
	}

}