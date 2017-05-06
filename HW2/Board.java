// Board.java
package tetris;

import java.util.Arrays;

/**
 * CS108 Tetris Board. Represents a Tetris board -- essentially a 2-d grid of
 * booleans. Supports tetris pieces and row clearing. Has an "undo" feature that
 * allows clients to add and remove pieces efficiently. Does not do any drawing
 * or have any idea of pixels. Instead, just represents the abstract 2-d board.
 */
public class Board {
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;
	private int[] widths;
	private int[] heights;

	// backup
	private boolean[][] gridB;
	private int[] widthsB;
	private int[] heightsB;

	// Here a few trivial methods are provided:

	/**
	 * Creates an empty board of the given width and height measured in blocks.
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		gridB = new boolean[width][height];
		committed = true;

		this.widths = new int[height];
		this.heights = new int[width];
		this.widthsB = new int[height];
		this.heightsB = new int[width];

	}

	/**
	 * Returns the width of the board in blocks.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the board in blocks.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the max column height present in the board. For an empty board
	 * this is 0.
	 */
	public int getMaxHeight() {
		int max = 0;
		for (int i = 0; i < this.heights.length; i++)
			if (this.heights[i] > max)
				max = this.heights[i];
		return max;
	}

	/**
	 * Checks the board for internal consistency -- used for debugging.
	 */
	public void sanityCheck() {
		if (DEBUG) {
			int[] newWidths = new int[grid[0].length];
			int[] newHeights = new int[grid.length];

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j]) {
						if (j >= newHeights[i])
							newHeights[i] = j + 1;
						newWidths[j]++;
					}
				}
			}
			assert (Arrays.equals(newWidths, widths));
			assert (Arrays.equals(newHeights, heights));
		}
	}

	/**
	 * Given a piece and an x, returns the y value where the piece would come to
	 * rest if it were dropped straight down at that x.
	 * 
	 * <p>
	 * Implementation: use the skirt and the col heights to compute this fast --
	 * O(skirt length).
	 */
	public int dropHeight(Piece piece, int x) {
		int[] off = new int[piece.getSkirt().length];
		for (int i = 0; i < piece.getSkirt().length; i++) {
			try {
				// how far piece can fall on individual line
				off[i] = this.height + piece.getSkirt()[i] - this.heights[i + x];
			} catch (ArrayIndexOutOfBoundsException e) {
				return -1;
			}
		}

		int ret = 0, min = this.height + 1;
		for (int i = 0; i < piece.getSkirt().length; i++) {
			if (min > off[i]) {
				min = off[i];
				ret = i + x;
			}
		}
		/*
		 * return height of of column which will stop falling piece -
		 * piece.getSkirt()[ret - x], because 0 0 of piece might be lower than
		 * block which stopped piece. for example second rotation of l2 can be
		 * stopped by its 1 2 block.
		 */
		return this.heights[ret] - piece.getSkirt()[ret - x];
	}

	/**
	 * Returns the height of the given column -- i.e. the y value of the highest
	 * block + 1. The height is 0 if the column contains no blocks.
	 */
	public int getColumnHeight(int x) {
		try {
			return this.heights[x];
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * Returns the number of filled blocks in the given row.
	 */
	public int getRowWidth(int y) {
		try {
			return this.widths[y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}

	/**
	 * Returns true if the given block is filled in the board. Blocks outside of
	 * the valid width/height area always return true.
	 */
	public boolean getGrid(int x, int y) {
		try {
			return this.grid[x][y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	/*
	 * check if x and y are outside of this.grid
	 *
	 * private boolean outside(int x, int y) { if (x >= 0 && x < width && y >= 0
	 * && y < height) return false; return true; }
	 */

	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;

	/**
	 * Attempts to add the body of a piece to the board. Copies the piece blocks
	 * into the board grid. Returns PLACE_OK for a regular placement, or
	 * PLACE_ROW_FILLED for a regular placement that causes at least one row to
	 * be filled.
	 * 
	 * <p>
	 * Error cases: A placement may fail in two ways. First, if part of the
	 * piece may falls out of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 * Or the placement may collide with existing blocks in the grid in which
	 * case PLACE_BAD is returned. In both error cases, the board may be left in
	 * an invalid state. The client can use undo(), to recover the valid,
	 * pre-place state.
	 */
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed)
			throw new RuntimeException("place commit problem");

		int result = PLACE_OK;
		committed = false;
		backup();
		int dx, dy;
		TPoint[] points = piece.getBody();

		for (TPoint p : points) {
			dx = x + p.x;
			dy = y + p.y;

			try {
				// check for ArrayIndexOutOfBoundsException
				if (this.grid[x][y]);
				if(this.grid[dx][dy]);
			} catch (ArrayIndexOutOfBoundsException e) {
				return PLACE_OUT_BOUNDS;
			}
			// grid is filled
			if (this.grid[dx][dy])
				return PLACE_BAD;

			this.grid[dx][dy] = true;
			// if new high
			if (this.heights[dx] < dy + 1)
				this.heights[dx] = dy + 1;
			
			this.widths[dy]++;
			// if filled
			if (this.width == this.widths[dy])
				result = PLACE_ROW_FILLED;

		}
		sanityCheck();
		return result;
	}

	/**
	 * Deletes rows that are filled all the way across, moving things above
	 * down. Returns the number of rows cleared.
	 */
	public int clearRows() {
		// if(committed)backup();
		committed = false;
		int rowsCleared = 0;
		for (int j = 0; j < grid[0].length; j++) {
			//if filled
			if (widths[j] == width) {
				clear(j);
				j--;
				rowsCleared++;
			}
		}
		sanityCheck();
		return rowsCleared;
	}
	
	//clear specific row and update board 
	private void clear(int r) {
		for (int i = 0; i < grid.length; i++) {
			heights[i] = 0;
			for (int j = 0; j < grid[i].length - 1; j++) {
				if (j >= r) {
					grid[i][j] = grid[i][j + 1];
					if (i == 0)
						widths[j] = widths[j + 1];
				}
				if (grid[i][j])
					heights[i] = j + 1;

			}
		}
		add();
	}

	/*
	 * private boolean checkRow(int r) { for (int j = 0; j < grid.length; j++)
	 * if (!grid[j][r]) return false; return true; }
	 */

	//add new row on top
	private void add() {
		this.widths[grid[0].length - 1] = 0;
		for (int j = 0; j < grid.length; j++)
			grid[j][grid[0].length - 1] = false;
	}

	/**
	 * Reverts the board to its state before up to one place and one
	 * clearRows(); If the conditions for undo() are not met, such as calling
	 * undo() twice in a row, then the second undo() does nothing. See the
	 * overview docs.
	 */
	public void undo() {
		if (!committed)
			restore();
		committed = true;
	}

	/**
	 * Puts the board in the committed state.
	 */
	public void commit() {
		committed = true;
	}

	//backup condition of board by copying it in backup arrays 
	private void backup() {
		for (int i = 0; i < gridB.length; i++)
			System.arraycopy(grid[i], 0, gridB[i], 0, grid[i].length);
		System.arraycopy(widths, 0, widthsB, 0, widths.length);
		System.arraycopy(heights, 0, heightsB, 0, heights.length);
	}

	//restore condition of board from backup arrays
	private void restore() {
		for (int i = 0; i < grid.length; i++)
			System.arraycopy(gridB[i], 0, grid[i], 0, grid[i].length);
		System.arraycopy(widthsB, 0, widths, 0, widths.length);
		System.arraycopy(heightsB, 0, heights, 0, heights.length);
	}

	/*
	 * Renders the board state as a big String, suitable for printing. This is
	 * the sort of print-obj-state utility that can help see complex state
	 * change over time. (provided debugging utility)
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height - 1; y >= 0; y--) {
			buff.append('|');
			for (int x = 0; x < width; x++) {
				if (getGrid(x, y))
					buff.append('+');
				else
					buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x = 0; x < width + 2; x++)
			buff.append('-');
		return (buff.toString());
	}
}
