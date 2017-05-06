// TabooTest.java
// Taboo class tests -- nothing provided.
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

import assign1.Taboo;

public class TabooTest {

	@Test
	public void testTaboo1() {
		List<Character> rules = Arrays.asList(new Character[] { '4', '8', '5', null, '6', '3', '2' });
		Taboo<Character> taboo = new Taboo<>(rules);
		List<Character> ls;

		// t1
		Character[] bla=new Character[] { '4', '8', '5', '6', '3', '2' };
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Character[] { '4', '5', '6', '2' }), ls);

		// t2
		bla=new Character[] { '0', '7', '3', '6', '7', '3' };
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Character[] { '0', '7', '3', '6', '7', '3' }), ls);

		// t3
		bla=new Character[] {};
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Character[] {}), ls);
	}
	

	@Test
	public void testTaboo2() {
		List<Integer> rules = Arrays.asList(new Integer[] { 4, 8, 5, null, 6, 3, 2 });
		Taboo<Integer> taboo = new Taboo<>(rules);
		List<Integer> ls;

		// t1
		Integer[] bla=new Integer[] { 4, 8, 5, 6, 3, 2 };
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Integer[] { 4, 5, 6, 2 }), ls);

		// t2
		bla=new Integer[] { 0, 7, 3, 6, 7, 3 };
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Integer[] { 0, 7, 3, 6, 7, 3 }), ls);

		// t3
		bla=new Integer[] {};
		ls = new ArrayList<>(Arrays.asList(bla));
		taboo.reduce(ls);
		assertEquals(Arrays.asList(new Integer[] {}), ls);
	}

	
}
