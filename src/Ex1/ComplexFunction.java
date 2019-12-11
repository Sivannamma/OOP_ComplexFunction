package Ex1;

import java.util.ArrayList;
import java.util.Arrays;

public class ComplexFunction implements complex_function {
	private function left = null;
	private function right = null;
	private Operation op = Operation.None;

	public ComplexFunction(function left, function right, Operation op) {
		this.op = op;
		if (left == null) // left cannot be null
			throw new RuntimeException("function cannot be null");
		this.left = left.copy();
		if (right != null)
			this.right = right.copy();
	}

	public ComplexFunction(function left) {
		if (left == null)
			throw new RuntimeException("function cannot be null");
		this.left = left.copy();
		// operation will be set to none because theres no operation to do between only
		// one function
		this.op = Operation.None;
	}

	public ComplexFunction(String str) {

		if (str.contentEquals("")) // if the str is empty throws exception
			throw new RuntimeException("ilegal input");

		function func = initFromString(str);
		if (func instanceof Polynom) { // if its one polynom it means only one side
			this.op = Operation.None; // no operation because one side of the function
			this.left = func.copy();
		} else if (func instanceof ComplexFunction) {
			this.op = ((ComplexFunction) func).getOp();
			this.left = ((ComplexFunction) func).left;
			this.right = ((ComplexFunction) func).right;
		} else
			throw new RuntimeException();

	}

	public ComplexFunction(function left, function right, String op) {

		if (left == null) { // left side cannot be null - throwing exception.
			throw new RuntimeException("functions cannot be null");
		}
		// therefore after checking it isnt null, we can initialize left
		// we are doing copy because its an object and not primitive type
		// needs deeper initialize.
		this.left = left.copy();

		// right side can be null, if its null it stays like that, otherwise we
		// initialize
		// it , just like we did with the left side.
		if (right != null)
			this.right = right.copy();

		// checking for the operation :
		// Plus, Times, Divid, Max, Min, Comp , None, Error
		switch (op.toUpperCase()) { // because we get it like a string.
		case "NONE": {
			if (this.right != null) // can be none if and only if right side function is null.
				throw new RuntimeException("None cannot be exicuted with two functions");
			this.op = Operation.None;
			break;
		}
		case "PLUS": {
			this.op = Operation.Plus;
			break;
		}
		case "TIMES": {
			this.op = Operation.Times;
			break;
		}
		case "DIVID": {
			this.op = Operation.Divid;
			break;
		}
		case "MAX": {
			this.op = Operation.Max;
			break;
		}
		case "MIN": {
			this.op = Operation.Min;
			break;
		}
		case "COMP": {
			this.op = Operation.Comp;
			break;
		}
		default: { // Error
			throw new RuntimeException("Operation cannot be initialize to such input");
		}
		}
	}

	@Override
	public double f(double x) {
		switch (this.op) {
		case None: {
			throw new RuntimeException("Operation cannot be null");
		}
		case Plus: {
			return this.left.f(x) + this.right.f(x);
		}
		case Times: {
			return this.left.f(x) * this.right.f(x);
		}
		case Divid: {
			return this.left.f(x) / this.right.f(x);
		}
		case Max: {
			if (this.left.f(x) > this.right.f(x))
				return this.left.f(x); // left is the max
			else
				return this.right.f(x);
		}
		case Min: {
			if (this.left.f(x) < this.right.f(x))
				return this.left.f(x); // left is the min
			else
				return this.right.f(x);
		}
		case Comp: {
			if (this.right != null)
				return this.left.f(right.f(x));
			return this.left.f(x); // because there isnt right side
		}
		default: { // Error
			throw new RuntimeException("Operation cannot be initialize to such input");
		}
		}

	}

	@Override
	public function initFromString(String s) {
		int i = 0;
		String op = "";
		// checking if we come to a situation with a polynom that
		// can be initialized, with no "(" or ")" :
		if (s.indexOf('(') == -1 && s.indexOf(')') == -1) {
			function func = new Polynom(s);
			return func;
		}
		// searching for the operation
		while (i < s.length() && s.charAt(i) != '(') { // searching the appearance of (
			op += s.charAt(i); // concat the operation from the string
			i++;
		}

		// if the operation is None we need to initialize only one side
		// of the function - because None cant be between two functions
		if (op.toUpperCase().contentEquals("NONE")) {
			function left = initFromString(s.substring((i + 1), s.indexOf(')')));
			function setFunc = new ComplexFunction(left);
			return setFunc;
		} else { // it means the operation is not None
			String temp = s.substring(i + 1, s.length() - 1);
			int split = indexOfSplit(temp);
			if (split == -1) { // in case theres no operation, it means two functions sided
				function left = initFromString(temp.substring(0, temp.indexOf(',')));
				function right = initFromString(temp.substring(temp.indexOf(',') + 1));
				function setFunc = new ComplexFunction(left, right, op);
				return setFunc;
			} else { // split it by the function
				function left = initFromString(temp.substring(0, split - 1));
				function right = initFromString(temp.substring(split));
				function setFunc = new ComplexFunction(left, right, op);
				return setFunc;
			}

		}
	}

	private int indexOfSplit(String str) {
		int count = 0;
		int index = str.indexOf('(');
		if (index == -1)
			return -1;
		count++;
		index++;
		while (index < str.length()) {
			// we count how many ( and ), by the times its 0 we know the two functions
			// can be splited
			if (str.charAt(index) == '(')
				count++;

			if (str.charAt(index) == ')')
				count--;
			index++;
			if (index == str.length())
				return str.indexOf(',');
			if (count == 0) // means we can return the index of split
				return index + 1;
		}
		return -2;
	}

	@Override
	public function copy() {
		function func = new ComplexFunction(this.left, this.right, this.op + "");
		return func;
	}

	@Override
	public void plus(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Plus;
		this.right = f1.copy();
	}

	@Override
	public void mul(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Times;
		this.right = f1.copy();
	}

	@Override
	public void div(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Divid;
		this.right = f1.copy();

	}

	@Override
	public void max(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Max;
		this.right = f1.copy();

	}

	@Override
	public void min(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Min;
		this.right = f1.copy();

	}

	@Override
	public void comp(function f1) {
		function thisNew = new ComplexFunction(this.left, this.right, this.op.toString());
		this.left = thisNew.copy();
		this.op = Operation.Comp;
		this.right = f1.copy();

	}

	@Override
	public function left() {
		return left; // returns left function
	}

	@Override
	public function right() {
		return right; // returns right function
	}

	@Override
	public Operation getOp() {
		return this.op; // returning our oparetion
	}

	public String toString() {
		String ans = "";
		switch (this.op) {
		case None: {
			ans += "None";
			break;
		}
		case Plus: {
			ans += "Plus";
			break;
		}
		case Times: {
			ans += "Times";
			break;
		}
		case Divid: {
			ans += "Divid";
			break;
		}
		case Max: {
			ans += "Max";
			break;
		}
		case Min: {
			ans += "Min";
			break;
		}
		case Comp: {
			ans += "Comp";
			break;
		}
		}
		ans += '(';
		if (left != null) {
			ans += this.left.toString();
			if (this.op == Operation.None)
				ans += ')';
			else
				ans += ",";
		}
		if (right != null) {
			ans += this.right.toString();
			ans += ')';
		}
		return ans;
	}

	@Override
	public boolean equals(Object other) {
		double range = -25;
		final double jumpingRange = 0.01;
		if (other instanceof ComplexFunction) {
			complex_function otherO = (ComplexFunction) (other);
			while (range <= 25) {
				if (Math.abs(this.f(range) - otherO.f(range)) > 0.01) { // once they represents dif value- not the same
					return false;
				}
				range += jumpingRange; // checking in epsilon steps
			}
		}
		return true;
	}
}
