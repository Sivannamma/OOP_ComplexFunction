package Ex1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Functions_GUI implements functions {
	// we chose to use array list because as a collection it owns alot of useful
	// function that are helpful to check the basic stuff that we need in this
	// function.
	ArrayList<function> arr_func;

	public Functions_GUI() { // default constructor
		this.arr_func = new ArrayList<function>(); // initializing array list
	}

	@Override
	public boolean add(function e) {
		return arr_func.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		arr_func.clear();
	}

	@Override
	public boolean contains(Object o) {
		return arr_func.contains(o); // array list has its own contain function, we use to
										// to check if o object is contain
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return arr_func.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return arr_func.size() == 0; // if the size equals to zero, means the list is empty
	}

	@Override
	public Iterator<function> iterator() {
		return arr_func.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return arr_func.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return arr_func.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return arr_func.retainAll(c);
	}

	@Override
	public int size() {
		return arr_func.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
