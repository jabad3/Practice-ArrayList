package root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <code>MyArrayList</code> is a data-structure I created to mimic the java.util.ArrayList<T> class. It behaves in 
 * the same manner, and allows for generic types.
 * @author Joseph Abad
 * @param <T>
 */
public class MyArrayList<T> implements Collection<T>, List<T>{
	private int capacity;
	private int size;
	private T[] array;
	
	///////////////////////////////////////////////////////////
	//Constructors
	///////////////////////////////////////////////////////////
	public MyArrayList() {
//		this.capacity = 10;
//		this.size = 0;
//		array = (T[]) new Object[this.capacity];
		this(10);
	}
	public MyArrayList(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		array = (T[]) new Object[this.capacity];
	}
	
	
	
	///////////////////////////////////////////////////////////
	//Standard Methods
	///////////////////////////////////////////////////////////
	@Override
	public boolean add(T e) {
		//0. No safety checks
		//1. Check to see if resize is needed
		resize();
		//2. Find the index at which to add
		int index = this.size;
		//3. Add the object
		this.array[index] = e;
		this.size++;
		return true;
	}
	@Override
	public void add(int index, T element) {
		//0. Safety checks
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException();
		}
		//1. Check to see if resize is needed
		resize();	//takes care of range issues
		
		T temp;
		//2. Make space for new object
		if(size>=index) {
			forwardShifter(index);
			this.array[index] = element;
		} else {
			this.array[index] = element;
		}
		this.size++;
	}
	private void resize() {
		if(this.size==this.capacity) {
			System.out.println("resize");
			this.capacity*=2;
			array = Arrays.copyOf(array, this.capacity);
			return;
		}
		else return;
	}
	private void forwardShifter(int gapIndex) {
		int tailIndex = this.size;
		for(;tailIndex>gapIndex;tailIndex--) {
			array[tailIndex]=array[tailIndex-1];
		}
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	///////////////////////////////////////////////////////////
	//Special Methods
	///////////////////////////////////////////////////////////
	public void ensureCapacity(int minCapacity) {
		
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ListIterator<T> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}	
	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	///////////////////////////////////////////////////////////
	//Common Methods
	///////////////////////////////////////////////////////////
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	@Override
	public int size() {
		return this.size;
	}
	@Override
	public void clear() {
		for(T t : array) {
			t=null;
		}
	}
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);	}
	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	
	///////////////////////////////////////////////////////////
	//Overrides
	///////////////////////////////////////////////////////////
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
	/**
	 * @see java.lang.Object#equals()
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	//Testing
	private T[] getArray() {return this.array;}
	private int getCapacity() {return this.capacity;}
//	private T[] getArray() {return array;}
//	private T[] getArray() {return array;}

}
