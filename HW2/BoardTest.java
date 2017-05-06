package tetris;

import static org.junit.Assert.*;

import org.junit.*;

public class BoardTest {
	Board b;
	private Piece pyr1, pyr2, pyr3, pyr4, pyrF;
    private Piece s11, s12, s1F;
    private Piece s21, s22, s2F;
    private Piece l11, l12, l13, l14, l1F;
    private Piece l21, l22, l23, l24,  l2F;
    private Piece stick1, stick2, stickF;
    private Piece square1, squareF;;

	// This shows how to build things in setUp() to re-use
	// across tests.
	
	// In this case, setUp() makes shapes,
	// and also a 3X6 board, with pyr placed at the bottom,
	// ready to be used by tests.
	@Before
	public void setUp() throws Exception {
		b = new Board(3, 6);
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		pyrF = pyr4.computeNextRotation();

		s11 = new Piece(Piece.S1_STR);
		s12 = s11.computeNextRotation();
		s1F = s12.computeNextRotation();

		s21 = new Piece(Piece.S2_STR);
		s22 = s21.computeNextRotation();
		s2F = s22.computeNextRotation();

		l11 = new Piece(Piece.L1_STR);
		l12 = l11.computeNextRotation();
		l13 = l12.computeNextRotation();
		l14 = l13.computeNextRotation();
		l1F = l14.computeNextRotation();

		l21 = new Piece(Piece.L2_STR);
		l22 = l21.computeNextRotation();
		l23 = l22.computeNextRotation();
		l24 = l23.computeNextRotation();
		l2F = l24.computeNextRotation();

		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();
		stickF = stick2.computeNextRotation();

		square1 = new Piece(Piece.SQUARE_STR);
		squareF = square1.computeNextRotation();
		
		b.place(pyr1, 0, 0);
	}
	
	// Check the basic width/height/max after the one placement
	@Test
	public void testSample1() {
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(2, b.getColumnHeight(1));
		assertEquals(2, b.getMaxHeight());
		assertEquals(3, b.getRowWidth(0));
		assertEquals(1, b.getRowWidth(1));
		assertEquals(0, b.getRowWidth(2));
	}
	
	// Place sRotated into the board, then check some measures
	@Test
	public void testSample2() {
		b.commit();
		int result = b.place(s12, 1, 1);
		assertEquals(Board.PLACE_OK, result);
		assertEquals(1, b.getColumnHeight(0));
		assertEquals(4, b.getColumnHeight(1));
		assertEquals(3, b.getColumnHeight(2));
		assertEquals(4, b.getMaxHeight());
	}
	
	@Test
	public void testPlace(){
		b.commit();
		assertEquals(Board.PLACE_OK, b.place(s11, 0, b.dropHeight(s11, 0)));
		b.undo();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(pyr1, 0, b.dropHeight(pyr1, 0)));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(pyr1, -1, 0));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(pyr1, 0, -1));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(pyr1, 1, 3));
		b.undo();
		assertEquals(Board.PLACE_BAD, b.place(pyr1, 0, 0));
		b.undo();
		assertEquals(Board.PLACE_OK, b.place(stick1, 0, b.dropHeight(stick1, 0)));
		b.undo();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(pyr3, 0, b.dropHeight(pyr3, 0)));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(square1, 18, 0));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(l23, 2, 1));
		b.undo();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(pyr1, 1, 3));
		b.undo();
		assertEquals(Board.PLACE_BAD, b.place(l11, 0, 1));
		b.undo();
	}
	
	@Test
	public void testClear(){
		b.commit();
		assertEquals(1,b.clearRows());
		b.commit();
		assertEquals(Board.PLACE_OK, b.place(stick1, 0, b.dropHeight(stick1, 0)));
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(squareF, 1, b.dropHeight(squareF, 1)));
		b.commit();
		assertEquals(2, b.clearRows());
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(s12, 1, b.dropHeight(s12, 1)));
		b.commit();
		assertEquals(Board.PLACE_OK, b.place(pyr4, 0, b.dropHeight(pyr4, 0)));
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(l13, 1, b.dropHeight(l13, 1)));
		b.commit();
		assertEquals(5, b.clearRows());
		b.commit();
		assertEquals(Board.PLACE_OK, b.place(stick1, 0, b.dropHeight(stick1, 0)));
		b.commit();
		assertEquals(Board.PLACE_OK, b.place(stick1, 2, b.dropHeight(stick1, 2)));
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(l13, 0, b.dropHeight(l13, 0)));
		b.commit();
		assertEquals(2, b.clearRows());
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(l22, 0, b.dropHeight(l22, 0)));
		b.commit();
		System.out.println(b.toString());
		assertEquals(2, b.clearRows());
		b.commit();
		assertEquals(Board.PLACE_OUT_BOUNDS, b.place(stick2, 0, b.dropHeight(stick2, 0)));
		b.undo();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(l23, 1, b.dropHeight(l23, 1)));
		b.commit();
		assertEquals(Board.PLACE_ROW_FILLED, b.place(l14, 0, b.dropHeight(l14, 0)));
		b.commit();
		assertEquals(4, b.clearRows());
	}
	
	@Test
	public void testDimensons(){
		assertEquals(3, b.getWidth());
		assertEquals(6, b.getHeight());
		assertEquals(-1, b.getColumnHeight(16));
		assertEquals(-1, b.getColumnHeight(-4));
		assertEquals(-1, b.getRowWidth(17));
		assertEquals(-1, b.getRowWidth(-3));
		assertEquals(true, b.getGrid(0, 0));
		assertEquals(false, b.getGrid(0, 1));
		assertEquals(true, b.getGrid(-1, 0));
		assertEquals(true, b.getGrid(0, -1));
		assertEquals(true, b.getGrid(17, 0));
		assertEquals(true, b.getGrid(0, 17));
	}
	
	 @Test(expected = RuntimeException.class)
	 public void testPlaceExc(){
		 b.place(s11, 0, b.dropHeight(s11, 0));
	 }
	 
	 @Test
	 public void testUndo(){
		 //boolean[][] gridOld=b.get
	 }
	
}
