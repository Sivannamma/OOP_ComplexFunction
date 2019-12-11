package Ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;

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
		return arr_func.addAll(c);
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
		return arr_func.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return arr_func.toArray(a);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		FileReader file_reader = new FileReader(file);
		BufferedReader read_from = new BufferedReader(file_reader);
		String lines = read_from.readLine();
		while (lines != null) { // as long as we have lines to read to the file
			ComplexFunction func = new ComplexFunction(lines);
			arr_func.add(func);
			lines = read_from.readLine();
		}
		read_from.close();
	}

	@Override
	public void saveToFile(String file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		Iterator<function> itr = arr_func.iterator();
		while (itr.hasNext()) { // as long as we have more functions to read
			writer.write(itr.next().toString());
			writer.newLine(); // new line
		}
		writer.close();
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height); // setting the board
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setXscale(ry.get_min(), ry.get_max());

	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub

	}

}
