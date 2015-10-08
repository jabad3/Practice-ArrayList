package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import root.MyArrayList;
import tests.Node;



public class MyArrayListTests {
	static MyArrayList arraylist;
	static ArrayList whatShouldArrayListContainNow;
	static Node node1;
	static Node node2;
	static Node node3;
	static Node node4;
	static Node node5;
	static Method getCapacity;
	static Method getArray;
	static Method resize;

	@BeforeClass
	public static void prepareForTests() {
		arraylist = new MyArrayList();
		whatShouldArrayListContainNow = new ArrayList();
		node1 = new Node(1, "Joseph");
		node2 = new Node(2, "Jose");
		node3 = new Node(3, "Vicki");
		node4 = new Node(4, "Luis");
		node5 = new Node(5, "Ariel");
		
		//Initialize Private Methods
		try {getCapacity =	MyArrayList.class.getDeclaredMethod("getCapacity");
			 getCapacity.setAccessible(true);
		} catch(Exception e) {System.out.println("getCapacity exception!");}
		try {getArray = 	MyArrayList.class.getDeclaredMethod("getArray");
			 getArray.setAccessible(true);
		} catch(Exception e) {System.out.println("getArray exception!");}
		try {resize = 		MyArrayList.class.getDeclaredMethod("resize");
			 resize.setAccessible(true);
		} catch(Exception e) {System.out.println("resize exception!");}
	}
	@Before
	public void beforeEach() {
		arraylist = new MyArrayList();
		whatShouldArrayListContainNow.clear();
	}
	@Test
	public void testInitial1() {
		//Caes: arraylist initialization
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));
	}
	@Test
	public void testInitial2() {
		//Caes: arraylist size initialization
		arraylist = new MyArrayList(20);
		assertTrue(testParameters(20, 0, whatShouldArrayListContainNow));
		System.out.println("s");
		resize();
		assertTrue(testParameters(40, 0, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd1() {
		//Case: invalid entries
		Object x = null;
		arraylist.add(null);
		arraylist.add(x);
		whatShouldArrayListContainNow.add(null);
		whatShouldArrayListContainNow.add(null);
		assertTrue(testParameters(10, 2, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd2() {
		//Case: simple add
		arraylist.add(node1);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd3() {
		//Case: duplicate entries
		arraylist.add(node1);
		arraylist.add(node1);		
		arraylist.add(node1);
		arraylist.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 4, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd4() {
		//Case: many entries, one duplicate
		arraylist.add(node1);
		arraylist.add(node1);		
		arraylist.add(node2);
		arraylist.add(node3);
		arraylist.add(node4);		
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node3);
		whatShouldArrayListContainNow.add(node4);
		assertTrue(testParameters(10, 5, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd5() {
		//Case: inserting over existing entries (push entries to the right).
		arraylist.add(0, node1);
		arraylist.add(0, node2);
		arraylist.add(0, node3);
		whatShouldArrayListContainNow.add(node3);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd6() {
		//Case: Keep testing inserting over indexes. Test the edges. Test the resizes.
		arraylist.add(0, node1);
		arraylist.add(0, node2);
		arraylist.add(0, node3);
		arraylist.add(0, node4);
		arraylist.add(0, node5);
		arraylist.add(0, node1);
		arraylist.add(0, node2);
		arraylist.add(0, node3);
		arraylist.add(0, node4);
		arraylist.add(0, node5);
		whatShouldArrayListContainNow.add(node5);
		whatShouldArrayListContainNow.add(node4);
		whatShouldArrayListContainNow.add(node3);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node5);
		whatShouldArrayListContainNow.add(node4);
		whatShouldArrayListContainNow.add(node3);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 10, whatShouldArrayListContainNow));
		arraylist.add(0, node1);
		whatShouldArrayListContainNow.add(0, node1);
		assertTrue(testParameters(20, 11, whatShouldArrayListContainNow));
	}
	@Test
	public void testAdd7() {
		//Case: Test adding at weird indexes.
		assertTrue(false);
	}
	@Test
	public void testClear1() {	
		//Case: clearing a arraylist (size is 0, capacity doesnt change)
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node3);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));
		
		arraylist.clear();
		whatShouldArrayListContainNow.clear();
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testClear2() {	
		//Case: clearing a arraylist after resize
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		resize();
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node3);
		assertTrue(testParameters(20, 3, whatShouldArrayListContainNow));
		
		arraylist.clear();
		whatShouldArrayListContainNow.clear();
		assertTrue(testParameters(20, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGetSize1() {
		//Case: empty array
		assertEquals(0, arraylist.size());
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGetSize2() {
		//Case: non empty array
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node2);
		whatShouldArrayListContainNow.add(node3);
		assertEquals(3, arraylist.size());
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGetSize3() {
		//Case: after manipulating array
		arraylist.add(node1);
		arraylist.remove(node2);
		arraylist.add(node3);
		arraylist.remove(node1);
		whatShouldArrayListContainNow.add(node3);
		assertEquals(1, arraylist.size());
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));	
	}
	@Test
	public void testResize1() {
		//Case: simple resize
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
		resize();
		assertTrue(testParameters(20, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testResize2() {
		resize();
		resize();
		assertTrue(testParameters(40, 0, whatShouldArrayListContainNow));	
		resize();
		assertTrue(testParameters(80, 0, whatShouldArrayListContainNow));	
		resize();
		assertTrue(testParameters(160, 0, whatShouldArrayListContainNow));	
		resize();
		assertTrue(testParameters(320, 0, whatShouldArrayListContainNow));	
	}
	
	@Test
	public void testResize3() {
		//Case: resize only when needed
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		arraylist.add(0, node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 10, whatShouldArrayListContainNow));
		arraylist.add(0, node1);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(20, 12, whatShouldArrayListContainNow));
	}
	@Test
	public void testGet1() {
		//Case: invalid gets
		assertEquals(null, arraylist.get((Integer)null));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGet2() {
		//Case: non existent keys
		assertEquals(null, arraylist.get(0));
		assertEquals(null, arraylist.get(1));
		assertEquals(null, arraylist.get(2));
		assertEquals(null, arraylist.get(3));
		assertEquals(null, arraylist.get(4));
		assertEquals(null, arraylist.get(5));
		assertEquals(null, arraylist.get(6));
		assertEquals(null, arraylist.get(7));
		assertEquals(null, arraylist.get(8));
		assertEquals(null, arraylist.get(9));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGet3() {
		//Case: simple get
		arraylist.add(0, node1);
		whatShouldArrayListContainNow.add(node1);
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));	
		assertEquals(node1, arraylist.get(0));
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));
	}
	@Test
	public void testGet4() {
		//Case: multiple gets
		arraylist.add(0, node1);
		arraylist.add(3, node2);
		arraylist.add(6, node3);
		whatShouldArrayListContainNow.add(0, node1);
		whatShouldArrayListContainNow.add(3, node2);
		whatShouldArrayListContainNow.add(6, node3);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
		
		assertEquals(node1, arraylist.get(0));
		assertEquals(node2, arraylist.get(3));
		assertEquals(node3, arraylist.get(6));
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGet5() {
		//Case: getting after removing
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node1.getId(), node1);
		whatShouldArrayListContainNow.add(node2.getId(), node2);
		whatShouldArrayListContainNow.add(node3.getId(), node3);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
		
		arraylist.remove(node1.getId());
		arraylist.remove(node2.getId());
		arraylist.remove(node3.getId());
		whatShouldArrayListContainNow.clear();
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	

		assertEquals(null, arraylist.get(0));
		assertEquals(null, arraylist.get(1));
		assertEquals(null, arraylist.get(2));
		assertEquals(null, arraylist.get(3));
		assertEquals(null, arraylist.get(4));
		assertEquals(null, arraylist.get(5));
		assertEquals(null, arraylist.get(6));
		assertEquals(null, arraylist.get(7));
		assertEquals(null, arraylist.get(8));
		assertEquals(null, arraylist.get(9));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testGet7() {
		//Case: getting keys after over writing an add
		arraylist.add(node1.getId(), node1);
		whatShouldArrayListContainNow.add(node1.getId(), node1);
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));	
		assertEquals(node1, arraylist.get(node1.getId()));

		arraylist.add(node1.getId(), node2);
		whatShouldArrayListContainNow.clear();
		whatShouldArrayListContainNow.add(node1.getId(), node2);
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));	
		assertEquals(node2, arraylist.get(node1.getId()));
	}
	@Test
	public void testRemove1() {
		//Case: invalid remove
		assertEquals(null, arraylist.remove(null));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testRemove2() {
		//Case: removing non existent keys
		assertEquals(null, arraylist.remove("1"));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testRemove3() {
		//Case: removing at invalid indexes
		assertEquals(null, arraylist.remove(1000));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testRemove4() {
		//Case: removing at empty indexes
		assertEquals(null, arraylist.remove(2));
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
	}
	@Test
	public void testRemove5() {
		//Case: simple removes
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		arraylist.add(node4.getId(), node4);
		whatShouldArrayListContainNow.add(node2.getId(), node2);
		whatShouldArrayListContainNow.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node4.getId(), node4);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
		
		Node x = (Node) arraylist.remove(node2.getId());
		Node y = (Node) arraylist.remove(node3.getId());
		Node z = (Node) arraylist.remove(node4.getId());
		whatShouldArrayListContainNow.clear();
		assertTrue(testParameters(10, 0, whatShouldArrayListContainNow));	
		assertEquals(node2, x);
		assertEquals(node3, y);
		assertEquals(node4, z);
		
		x = (Node) arraylist.get(node1.getId());
		y = (Node) arraylist.get(node2.getId());
		z = (Node) arraylist.get(node3.getId());
		assertEquals("", null, x);
		assertEquals("", null, y);
		assertEquals("", null, z);
	}
	@Test
	public void testRemove6() {
		//Case: remove manipulation
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node1.getId(), node1);
		whatShouldArrayListContainNow.add(node2.getId(), node2);
		whatShouldArrayListContainNow.add(node3.getId(), node3);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));	
		
		Node x = (Node) arraylist.remove(node1.getId());
		Node y = (Node) arraylist.remove(node3.getId());
		whatShouldArrayListContainNow.remove(node1);
		whatShouldArrayListContainNow.remove(node3);		
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));	
		assertEquals(node1, x);
		assertEquals(node3, y);
		
		x = (Node) arraylist.get(node1.getId());
		y = (Node) arraylist.get(node2.getId());
		Node z = (Node) arraylist.get(node3.getId());
		assertEquals("", null, x);
		assertEquals("", node2, y);
		assertEquals("", null, z);
		
		x = (Node) arraylist.remove(node1.getId());
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));
		assertEquals("", null, x);
	}
	@Test
	public void testContains1() {
		//Case: manipulation
		arraylist.add(node2.getId(), node2);
		arraylist.add(node3.getId(), node3);
		arraylist.add(node4.getId(), node4);
		whatShouldArrayListContainNow.add(node2.getId(), node2);
		whatShouldArrayListContainNow.add(node3.getId(), node3);
		whatShouldArrayListContainNow.add(node4.getId(), node4);
		assertTrue(testParameters(10, 3, whatShouldArrayListContainNow));
	
		assertEquals(false, arraylist.contains(node1));
		assertEquals(true,  arraylist.contains(node2));
		assertEquals(true,  arraylist.contains(node3));
		
		arraylist.remove(node1.getId());
		arraylist.remove(node2.getId());
		arraylist.remove(node3.getId());
		whatShouldArrayListContainNow.remove(node2);
		whatShouldArrayListContainNow.remove(node3);		
		assertTrue(testParameters(10, 1, whatShouldArrayListContainNow));

		assertEquals("", false, arraylist.contains(node1));
		assertEquals("", false, arraylist.contains(node2));
		assertEquals("", false, arraylist.contains(node3));
	}
	@Test
	public void testSetMethodsAgainstRealArrayList1() {
		//Case: create a real java.Hasharraylist and compare my set methods to it
		ArrayList real = new ArrayList();
		real.add(node1.getId(), node1);
		real.add(node2.getId(), node2);
		arraylist.add(node1.getId(), node1);
		arraylist.add(node2.getId(), node2);			
		//System.out.println("    struct: " + table);
		//System.out.println("  mystruct: " + arraylist);
		//System.out.println("  entryset: " + table.entrySet());
		//System.out.println("myentryset: " + arraylist.entrySet());
		//System.out.println("    keyset: " + table.keySet());
		//System.out.println("  mykeyset: " + arraylist.keySet());
		//System.out.println("    values: " + table.values());
		//System.out.println("  myvalues: " + arraylist.values());
		assertEquals(real.toArray(), arraylist.toArray());
		assertEquals(real.toArray(), arraylist.toArray());
		//assertEquals(((Collection)table.values()), arraylist.values());		
	}
	@Test
	public void testAddAll1() {	
		//Case: simple putAll
		ArrayList<Node> table = new ArrayList<Node>();
		table.add(node1.getId(), node1);
		table.add(node2.getId(), node2);
		arraylist.addAll(table);
		assertEquals("", table.toArray(), arraylist.toArray());
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//F O R     T E S T I N G     U S E     O N L Y
	public void resize() {
		try {resize.invoke(arraylist);} catch(Exception e){};
	}
	public int getCapacity() {
		int i = 1000000;
		try {i = (int) getCapacity.invoke(arraylist);} catch(Exception e) {};
		return i;
	}
	public Object[] getArray() {
		Object[] i = null;
		try {i = (Object[]) getArray.invoke(arraylist);} catch(Exception e) {};
		return i;
	}
	public boolean testParameters(int testCapacity, int testSize, ArrayList content) {
		//Testing Capacity
		assertEquals(testCapacity, getArray().length);
		assertEquals(testCapacity, getCapacity());
		
		//Testing Size
		assertEquals(testSize, arraylist.size());
		assertEquals(testSize, testNumberOfEntries());
		
		//Testing Content
//		ArrayList temp = testContent();
//		assertEquals(content, temp);
		assertArrayEquals(content.toArray(), arraylist.toArray());
		
		//Testing Load Factor
		assertTrue(testCapacity());
		return true;		
	}
	public boolean testCapacity() {
		return (arraylist.size() <= getCapacity());
	}
	public int testNumberOfEntries() {
//		int i = 0;
//		System.out.println("a:"+Arrays.toString(arraylist.toArray()));
//		for(Object o : arraylist.toArray()) {
//			System.out.println(o);
//			if(o!=null) {
//				i++;
//			}
//		}
//		System.out.println("i"+ i);
//		return i;
		return arraylist.toArray().length;
	}
//	public ArrayList testContent() {
//		ArrayList temp = new ArrayList();
//		for(int i = 0; i<getArray().length; i++) {
//			temp.add(getArray()[i]);
//		}
//		return temp;
//	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
