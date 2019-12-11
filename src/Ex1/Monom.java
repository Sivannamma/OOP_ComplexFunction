
package Ex1;

import java.util.Comparator;

import javax.management.RuntimeErrorException;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 * 
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * (Math.pow(x, this.get_power()));
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() == 0.0;
	}

	// ***************** add your code below **********************
	public Monom(String s) {
		if (s == "")
			throw new RuntimeException("empty monom exception");

		s = s.toUpperCase(); // in case we got x in the monom, we check for X

		if (!isLegalInput(s)) // if the input is ilegal
			throw new RuntimeException("ilegal monom input");

		String num = "";
		boolean isDot = false; // if s contains a dot
		boolean isMinusOrPlus = (s.charAt(0) == '-' || s.charAt(0) == '+') ? true : false; // if the monom starts with -
																							// or +
		int i = (isMinusOrPlus) ? 1 : 0; // indicates where to start iterate on the string

		while (i < s.length()) {
			if (s.charAt(i) == ' ') {
				i++;
				continue;
			}
			num += s.charAt(i);
			if (s.charAt(i) == '.') {
				if (isMinusOrPlus)
					this._coefficient += StringToInt(num.substring(0, i - 1));
				else
					this._coefficient += StringToInt(num.substring(0, i));
				num = "";
				isDot = true;
			}
			if (s.charAt(i) == 'X') {
				if (isDot)
					this._coefficient += StringToIntAfterDot(num.substring(0, num.length() - 1));
				else {
					if (num.length() > 1)
						this._coefficient += StringToInt(num.substring(0, num.length() - 1));
					else
						this._coefficient += StringToInt("X");
				}

				switch (s.length() - i) { // depends on the result
											// we will know if the string contains X or X^... or without X at all.
				case 0:
					this._power = 0; // if there is not X in the monom
					break;
				case 1:
					this._power = 1; // if the monom contains only X
					break;
				default: // if the mononm contains X and power (^).
					i++;
					num = "";
					break;
				}
			}
			i++;

		}
		if (!(s.contains("X"))) {
			if (isDot)
				this._coefficient += StringToIntAfterDot(num);
			else
				this._coefficient += StringToInt(num);
		}

		if (isMinusOrPlus && s.charAt(0) == '-') // checks if the monom is negative we multiple by -1
			this._coefficient *= -1;
		if (s.contains("^")) // if the monom contains ^ we will add it to the power
			this._power = Integer.parseInt(num);

	}

	private int StringToInt(String str) {
		if (str == "X")
			return 1;
		return Integer.parseInt(str);

	}

	private double StringToIntAfterDot(String str) {
		int zeroCount = 0;
		int i = 0;
		if (str == "X")
			return 1;

		while (i < str.length() && str.charAt(i) == '0') { // checks how many zeros we have after the dot and before a
															// num that is not 0
			zeroCount++;
			i++;
		}
		str = inputSupport(str);
		double num = Integer.parseInt(str);
		while (num > 1)
			num = num / 10;

		while (zeroCount > 0) {
			num = num / 10;
			zeroCount--;
		}
		return num;

	}

	private String inputSupport(String str) {
		int toDivide = str.length() - 9;
		if (str.length() > 9)
			return str.substring(0, str.length() - toDivide);
		return str;
	}

	private boolean isLegalInput(String s) {
		boolean isLegal = true; // setting the flag to know if the input is legal
								// if its gonna change- we know there was wrong input
		int i = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0; // indicates where to start iterate on the string

		// checks the first char at the string
		if (s.charAt(0) != '+' && s.charAt(0) != 'X' && s.charAt(0) != '-' && !(s.charAt(0) >= 48 && s.charAt(0) <= 58))
			isLegal = false;

		if (s.contains("X")) {
			if (s.length() == 1)
				return true;
			if (s.length() == 2 && (s.contains("-") || s.contains("+")))
				return true;

			int indOfX = s.indexOf('X');

			if (indOfX != s.length() - 1 && s.charAt(indOfX + 1) != '^')
				isLegal = false;

			if (!isLegalNumber(s.substring(i, indOfX), true)) // checking the number before x
				isLegal = false;
			if (s.contains("^") && !isLegalNumber(s.substring(indOfX + 2, s.length()), false)) // checking the number
																								// after x
				isLegal = false;
		}

		return isLegal;
	}

	public boolean isLegalNumber(String str, boolean isFirst) {
		if (isFirst) { // needs to check for a double number
			if (str.contentEquals(""))
				return true;
			if (str.length() != 1 && str.charAt(0) == '.')
				return false;
			try {
				Double.parseDouble(str);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

		else { // needs to check for an integer number
			try {
				Integer.parseInt(str);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

	public void add(Monom m) {
		if (m._power != this._power) // if the pow isnt the same, we cant add them
			throw new RuntimeException(); // throwing exception
		else
			this._coefficient += m._coefficient;

	}

	public void multipy(Monom d) {
		this._power += d._power; // adding the pow
		this._coefficient *= d._coefficient; // multiple the coefficent

	}

	public String toString() {
		String ans = (_coefficient >= 0) ? "+" : "";
		ans += "" + _coefficient + "X^" + _power + "";
		return ans;
	}
	// you may (always) add other methods.

	// ****************** Private Methods and Data *****************

	public void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	@Override
	public boolean equals(Object m1) {
		if (m1 instanceof Monom) { // checking if m1 is instance of monom - than cast
			Monom monom = (Monom) m1;
			if (this.isZero() && monom.isZero()) // if the coefficent is zero - they r equal
				return true;
			this._coefficient = Polynom.round(this._coefficient, 2);
			monom._coefficient = Polynom.round(monom._coefficient, 2);
			return this._coefficient == monom._coefficient && this._power == monom._power;
		} else
			return false;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;

	@Override
	public function initFromString(String s) {
		// creating a new function from monom constructor
		function m = new Monom(s);
		return m;
	}

	@Override
	public function copy() {
		String str = this.toString();
		Monom other = new Monom(str);
		return other;

	}

}
