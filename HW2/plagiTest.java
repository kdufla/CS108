package tetris;
import junit.framework.TestCase;


public class plagiTest extends TestCase {
	Board b;
	Piece pyr1, pyr2, pyr3, pyr4, s, sRotated;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	
	protected void setUp() throws Exception {
		b = new Board(3, 6);
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		
		b.place(pyr1, 0, 0);
	}
	
	// Check the basic width/height/max after the one placement
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(1, b.getColumnHeight(2));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
	// Place sRotated into the board, then check some measures
	public void testSample2() {
		b.commit();
		int result = b.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	// Makre  more tests, by putting together longer series of 
	// place, clearRows, undo, place ... checking a few col/row/max
	// numbers that the board looks right after the operations.
	public void testPlacingStatus() {
		b.commit();
		assertEquals(Board.PLACE_BAD, b.place(sRotated, 0, 0));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(sRotated, 2, 2));
		b.undo();
		assertEquals(Board.PLACE_BAD, b.place(sRotated, 0, 0));

	}
	public void testPlacingStatusMore() {
		b.commit();
		b.place(sRotated, 1, 1);
		b.commit();
		int reuslt = b.place(new Piece(Piece.STICK_STR), 0, 1);
		//System.out.println(b.toString());
		assertEquals(Board.PLACE_ROW_FILLED, reuslt);
	}
	
	public void testClearRows() {
		b.commit();
		b.place(sRotated, 1, 1);
		b.commit();
		b.place(new Piece(Piece.STICK_STR), 0, 1);
		//System.out.println(b.toString());
		assertEquals(3, b.clearRows());
		//System.out.println(b.toString());
		assertEquals(0, b.clearRows());
	}
	
	public void testDropHeight() {
		//System.out.println(b.toString());
		assertEquals(1, b.dropHeight(new Piece(Piece.STICK_STR), 0));
		assertEquals(2, b.dropHeight(new Piece(Piece.STICK_STR), 1));
		
		assertEquals(2, b.dropHeight(new Piece(Piece.PYRAMID_STR), 0));
		Piece l2 = new Piece(Piece.L2_STR);
		//b.place(l2.computeNextRotation(), 0, 1);
		//System.out.println(b.toString());
		assertEquals(1, b.dropHeight(l2.computeNextRotation(), 0));
	}
	
	public void testUndo() {
		//b.commit();
		// place/clear row/undo
		//  \_______________|
	}
	
	public void testUndoMore() {
		//b.commit();
		// place/undo
		//  \_____|
	}
}