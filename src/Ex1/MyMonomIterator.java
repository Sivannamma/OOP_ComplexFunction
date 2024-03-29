package Ex1;

import java.util.Iterator;

public class MyMonomIterator implements Iterator<Monom> {
	private Polynom _p;
	private Iterator<Integer> _keys;

	// keySet is a set of all the keys that in the map that we owe.
	// keySet has it own iterator interface.

	public MyMonomIterator(Polynom p) {
		_p = p;
		_keys = p.getMap().keySet().iterator();
	}

	@Override
	public boolean hasNext() {
		return _keys.hasNext();
	}
	
	public boolean hasPrev(Polynom p) {
		if(p.getMap().keySet().hashCode()>0)
			return true;
		return false;
	}

	@Override
	public Monom next() {
		Monom ans = null;
		if (hasNext()) {
			int p = _keys.next(); // power
			double c = _p.getMap().get(p); // coefficent
			ans = new Monom(c, p);
		}
		return ans;
	}
	
	public Monom prev() {
		Monom ans = null;
		if (hasNext()) {
			int p = _keys.next(); // power
			double c = _p.getMap().get(p); // coefficent
			ans = new Monom(c, p);
		}
		return ans;
	}
	

}
