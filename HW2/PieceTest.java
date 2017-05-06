package tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4, pyrF;
    private Piece s11, s12, s1F;
    private Piece s21, s22, s2F;
    private Piece l11, l12, l13, l14, l1F;
    private Piece l21, l22, l23, l24,  l2F;
    private Piece stick1, stick2, stickF;
    private Piece square1, squareF;

	@Before
	public void setUp() throws Exception {
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
	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSampleSize() {
		// Check size of pyr piece
		
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());

		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());

		assertEquals(3, pyr3.getWidth());
		assertEquals(2, pyr3.getHeight());

		assertEquals(2, pyr4.getWidth());
		assertEquals(3, pyr4.getHeight());

		assertEquals(3, pyrF.getWidth());
		assertEquals(2, pyrF.getHeight());

		assertEquals(3, s11.getWidth());
		assertEquals(2, s11.getHeight());

		assertEquals(2, s12.getWidth());
		assertEquals(3, s12.getHeight());

		assertEquals(3, s1F.getWidth());
		assertEquals(2, s1F.getHeight());

		assertEquals(3, s21.getWidth());
		assertEquals(2, s21.getHeight());

		assertEquals(2, s22.getWidth());
		assertEquals(3, s22.getHeight());

		assertEquals(3, s2F.getWidth());
		assertEquals(2, s2F.getHeight());

		assertEquals(2, l11.getWidth());
		assertEquals(3, l11.getHeight());

		assertEquals(3, l12.getWidth());
		assertEquals(2, l12.getHeight());

		assertEquals(2, l13.getWidth());
		assertEquals(3, l13.getHeight());

		assertEquals(3, l14.getWidth());
		assertEquals(2, l14.getHeight());

		assertEquals(2, l1F.getWidth());
		assertEquals(3, l1F.getHeight());

		assertEquals(2, l21.getWidth());
		assertEquals(3, l21.getHeight());

		assertEquals(3, l22.getWidth());
		assertEquals(2, l22.getHeight());

		assertEquals(2, l23.getWidth());
		assertEquals(3, l23.getHeight());

		assertEquals(3, l24.getWidth());
		assertEquals(2, l24.getHeight());

		assertEquals(2, l2F.getWidth());
		assertEquals(3, l2F.getHeight());

		assertEquals(1, stick1.getWidth());
		assertEquals(4, stick1.getHeight());

		assertEquals(4, stick2.getWidth());
		assertEquals(1, stick2.getHeight());

		assertEquals(1, stickF.getWidth());
		assertEquals(4, stickF.getHeight());

		assertEquals(2, square1.getWidth());
		assertEquals(2, square1.getHeight());

		assertEquals(2, squareF.getWidth());
		assertEquals(2, squareF.getHeight());
	}
	
	
	// Test the skirt returned by a few pieces
	@Test
	public void testSampleSkirt() {
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, pyr2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, pyr4.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyrF.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s11.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, s12.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s1F.getSkirt()));

		assertTrue(Arrays.equals(new int[] {1, 0, 0}, s21.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, s22.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 0}, s2F.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, l11.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, l12.getSkirt()));
		assertTrue(Arrays.equals(new int[] {2, 0}, l13.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1, 1}, l14.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, l1F.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, l21.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 1, 0}, l22.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 2}, l23.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, l24.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, l2F.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0}, stick1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, stick2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0}, stickF.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0}, square1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, squareF.getSkirt()));
		
		//assertTrue(Arrays.equals(Piece.parsePoints(Piece.PYRAMID_STR), pyr1.getBody()));
		
	}

    @Test
    public void testEquals(){
    	assertTrue(pyr1.equals(pyrF));
    	assertTrue(s11.equals(s1F));
    	assertTrue(s21.equals(s2F));
    	assertTrue(l11.equals(l1F));
    	assertTrue(l21.equals(l2F));
    	assertTrue(stick1.equals(stickF));
    	assertTrue(square1.equals(squareF));
        assertFalse(l11.equals(s12));
        assertFalse(l22.equals(s22));
        assertFalse(s22.equals(l11));
        assertFalse(square1.equals(pyr3));
    }
    
    @Test
    public void testRotation(){
		assertTrue(pyr2.equals(new Piece("1 0	1 1	 1 2  0 1")));
		assertTrue(pyr3.equals(new Piece("0 1	1 1	 2 1  1 0")));
		assertTrue(pyr4.equals(new Piece("0 0	0 1	 0 2  1 1")));
		assertTrue(pyrF.equals(new Piece("0 0	1 0	 2 0  1 1")));

		assertTrue(s12.equals(new Piece("0 1	0 2	 1 1  1 0")));
		assertTrue(s1F.equals(new Piece("0 0	1 0	 1 1  2 1")));

		assertTrue(s22.equals(new Piece("0 0	0 1	 1 1  1 2")));
		assertTrue(s2F.equals(new Piece("0 1	1 1	 1 0  2 0")));

		assertTrue(l12.equals(new Piece("2 1	0 0	 1 0  2 0")));
		assertTrue(l13.equals(new Piece("1 0	1 1	 1 2  0 2")));
		assertTrue(l14.equals(new Piece("0 1	1 1	 2 1  0 0")));
		assertTrue(l1F.equals(new Piece("0 0	0 1	 0 2  1 0")));

		assertTrue(l22.equals(new Piece("0 1	1 1	 2 1  2 0")));
		assertTrue(l23.equals(new Piece("0 0	0 1	 0 2  1 2")));
		assertTrue(l24.equals(new Piece("0 0	0 1	 1 0  2 0")));
		assertTrue(l2F.equals(new Piece("0 0	1 1	 1 0  1 2")));

		assertTrue(stick2.equals(new Piece("1 0	2 0	 3 0  0 0")));
		assertTrue(stickF.equals(new Piece("0 1	0 0	 0 2  0 3")));

		assertTrue(squareF.equals(new Piece("0 0	1 1	 0 1  1 0")));
		
	}
	
    @Test
    public void testGetPieces(){
        Piece[] pieces =  Piece.getPieces();
        
        assertTrue(pieces[Piece.L1].fastRotation().equals(l12));
        assertTrue(pieces[Piece.S1].fastRotation().equals(s12));
        assertFalse(pieces[Piece.S1].fastRotation().equals(s11));
        assertFalse(pieces[Piece.S1].equals(s12));
        assertTrue(pieces[Piece.L1].equals(l11));
        assertTrue(pieces[Piece.S1].equals(s11));
        assertTrue(pieces[Piece.L2].equals(l21));
        assertTrue(pieces[Piece.SQUARE].equals(square1));
        assertTrue(pieces[Piece.PYRAMID].equals(pyr1));
    }
    
    @Test(expected = RuntimeException.class)
    public void coverParse(){
    	Piece p = new Piece("gaTi, rato ar mipasuxe es rogor unda meqna rom gkiTxe? gavige pasuxi.");
    }
}
