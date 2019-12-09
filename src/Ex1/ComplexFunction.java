package Ex1;

import java.util.ArrayList;
import java.util.Arrays;

public class ComplexFunction implements complex_function {
	private function left = null;
	private function right = null;
	private Operation op = Operation.None;

	public ComplexFunction(function left) {
		if (left == null)
			throw new RuntimeException("function cannot be null");
		this.left = left.copy();
		// operation will be set to none because theres no operation to do between only
		// one function
		this.op = Operation.None;
	}

	public ComplexFunction(function left, function right, String op) {

		if (left == null) {
			throw new RuntimeException("functions cannot be null");
		}
		// because they arent null we can initialize them:
		// we treat the functions as nodes, therefore we deep copy, otherwise we change
		// pointers
		this.left = left.copy();
		this.right = right.copy();

		// checking for the operation :
		// Plus, Times, Divid, Max, Min, Comp , None, Error
		switch (op.toUpperCase()) {
		case "NONE": {
			if (this.right != null)
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
		case "DIVIDE": {
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
				return this.left.f(x); // left is the min
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
			function left = initFromString(s.substring(i + 1, s.lastIndexOf(',')));
			function setFunc = new ComplexFunction(left);
			return setFunc;
		} else { // it means the operation is not None
			String temp = s.substring(i + 1);
			function left = initFromString(temp.substring(0, temp.indexOf(',')));
			function right = initFromString(temp.substring(temp.indexOf(',') + 1, temp.lastIndexOf(')')));
			function setFunc = new ComplexFunction(left, right, op);
			return setFunc;

		}
	}

	@Override
	public function copy() {
		function func = new ComplexFunction(this.left, this.right, this.op + "");
		return func;
	}

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub

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
			ans += ",";
		}
		if (right != null) {
			ans += this.right.toString();
			ans += ')';
		}
		return ans;
	}

}
