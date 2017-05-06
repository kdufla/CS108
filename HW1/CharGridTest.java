// Test cases for CharGrid -- a few basic tests are provided.
package test;

import static org.junit.Assert.*;
import org.junit.Test;

import assign1.CharGrid;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
		assertEquals(1, cg.charArea(' '));
	}
	
	
	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}

	@Test
	public void plusTest1(){
		char[][] grid = new char[][] {
			{'c', 'a', ' '},
			{'b', ' ', 'b'},
			{' ', ' ', 'a'}
		};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(0, cg.countPlus());
	
	}

	@Test
	public void plusTest2(){
		char[][] grid = new char[][] {
			{'c', 'a', ' ', 'b', 'a'},
			{'b', 'Z', 'b', 'b','b'},
			{'b', ' ', 'c', 'b','g'}
		};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(1, cg.countPlus());
	
	}

	@Test
	public void plusTest3(){
		char[][] grid = new char[][] {
			{'c', 'a', ' ', 'b', 'a'},
			{'b', 'Z', 'b', 'b','b'},
			{'b', ' ', 'c', 'b','g'},
			{'c', 'a', ' ', 'b', 'a'},
			{'b', ' ', 'b', 'b','b'},
			{' ', ' ', ' ', 'b','g'},
			{'c', ' ', ' ', 'b', 'a'},
			{'b', 'Z', 'b', 'b','b'},
			{'b', ' ', 'c', 'b','g'},
		};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(1, cg.countPlus());
	
	}

	@Test
	public void plusTest4(){
		char[][] grid = new char[][] {
			{'w', 'g', 'a', 'v', 's', 's'},
			{'v', 'v', 'a', 'a', 'o', 'r'},
			{'a', 'a', 'a', 'a', 'a', 'p'},
			{' ', 'v', 'a', 's', 'w', 'f'},
			{'v', 'v', 'a', 'w', 'w', 'w'},
			{' ', 'v', 'h', 's', 'w', ' '}
		};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(2, cg.countPlus());
	
	}

	@Test
	public void plusTest5(){
		char[][] grid = new char[][] {
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
			{'s', 's', 's', 's', 's', 's', 's'},
		};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(1, cg.countPlus());
	
	}

	@Test
	public void plusTest6(){
		char[][] grid = new char[][] {{' '}};
	
	CharGrid cg = new CharGrid(grid);
	
	assertEquals(0, cg.countPlus());
	
	}
	
	@Test
	public void plusTest7(){
		char[][] grid = { //
		        "  p      ".toCharArray(), //
		        "  p    x ".toCharArray(), //
		        "ppppp xxx".toCharArray(), //
		        "  p  y x ".toCharArray(), //
		        "  p yyy  ".toCharArray(), //
		        "zzzzzyzzz".toCharArray(), //
		        "  xx y   ".toCharArray() };
		CharGrid cg = new CharGrid(grid);
		assertEquals(2, cg.countPlus());
	
	}

}