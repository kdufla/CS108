package test;

import static org.junit.Assert.*;
import org.junit.Test;

import assign1.TetrisGrid;

import java.util.*;

public class TetrisGridTest {
	
	// Provided simple clearRows() test
	// width 2, height 3 grid
	@Test
	public void testClear1() {
		boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, false, false},
			{false, true, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear2() {
		boolean[][] before =
		{	
			{true, true, false, },
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{false, false, false},
			{true, false, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear3() {
		boolean[][] before =
		{	
			{false, false, false, },
			{false, false, false, }
		};
		
		boolean[][] after =
		{	
			{false, false, false, },
			{false, false, false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear4() {
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, }
		};
		
		boolean[][] after =
		{	
			{false, false, false, },
			{false, false, false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear5() {
		boolean[][] before =
		{	
			{true, true, true,true, true, true,true, true, true,false, false, false,true,true },
			{true, true, true,true, true, true,true, true, true,false, false, false,false,true },
		};
		
		boolean[][] after =
		{	
			{false, false, false,true,false,false, false,false,false,false,false,false,false,false},
			{false, false, false,false,false,false, false,false,false,false,false,false,false,false},
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear6() {
		boolean[][] before =
		{	
			{true, true, true,true, true, true,true, true, true,false, false, false,true,true },
			{true, true, true,true, true, true,true, true, true,false, false, false,false,true },
			{true, true, true,true, true, true,true, true, true,false, false, false,true,true },
			{true, true, true,true, true, true,true, true, true,false, false, false,false,true }
		};
		
		boolean[][] after =
		{	
			{false, false, false,true,false,false, false,false,false,false,false,false,false,false},
			{false, false, false,false,false,false, false,false,false,false,false,false,false,false},
			{false, false, false,true,false,false, false,false,false,false,false,false,false,false},
			{false, false, false,false,false,false, false,false,false,false,false,false,false,false},
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	@Test
	public void testClear7() {
		//System.out.println("blaaa");
		boolean[][] before =
		{	
			{true}
		};
		
		boolean[][] after =
		{	
			{false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClear8() {
		boolean[][] before =
		{	
			{false, true, false, },
			{true, false, true, }
		};
		//System.out.println("blaa");
		boolean[][] after =
		{	
			{false, true, false, },
			{true, false, true, }
		};
		//System.out.println("blaa");
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
}
