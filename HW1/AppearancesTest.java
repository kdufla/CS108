package test;

import static org.junit.Assert.*;
import org.junit.Test;

import assign1.Appearances;

import java.util.*;

public class AppearancesTest {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}
	
	@Test
	public void testSameCount1() {
		Appearances ap=new Appearances();
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));
	}
	
	@Test
	public void testSameCount2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}
	
	@Test
	public void testSameCount3() {
		Appearances ap=new Appearances();
		List<String> a = stringToList("");
		List<String> b = stringToList("");
		assertEquals(0, Appearances.sameCount(a, b));
	}
	
	@Test
	public void testSameCount4() {
		Appearances ap=new Appearances();
		List<String> a = stringToList("");
		List<String> b = stringToList("cbba");
		assertEquals(0, Appearances.sameCount(a, b));
	}
	
	@Test
	public void testSameCount5() {
		Appearances ap=new Appearances();
		List<String> a = stringToList("a");
		List<String> b = stringToList("a");
		assertEquals(1, Appearances.sameCount(a, b));
	}
	
	@Test
	public void testSameCount6() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList();
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}
	
	@Test
	public void testSameCount7() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList();
		assertEquals(0, Appearances.sameCount(a, Arrays.asList()));
	}
}
