package assign3;

import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */
public class Sudoku {
	// Provided grid data for main/testing
	// The instance variable strategy is up to you.
	
	// Provided easy 1 6 grid
	// (can paste this text into the GUI too)
	public static final int[][] easyGrid = Sudoku.stringsToGrid(
	"1 6 4 0 0 0 0 0 2",
	"2 0 0 4 0 3 9 1 0",
	"0 0 5 0 8 0 4 0 7",
	"0 9 0 0 0 6 5 0 0",
	"5 0 0 1 0 2 0 0 8",
	"0 0 8 9 0 0 0 3 0",
	"8 0 9 0 4 0 2 0 0",
	"0 7 3 5 0 9 0 0 1",
	"4 0 0 0 0 0 6 7 9");
	
	
	public static final int[][] myg = Sudoku.stringsToGrid(
			"0 3 0 0 0 1 5 0 0",
			"0 0 0 5 0 0 0 8 4",
			"0 0 5 0 0 7 0 6 0", 
			"0 0 0 0 0 0 0 0 0", 
			"0 8 0 2 0 0 0 7 0", 
			"0 0 0 8 5 0 0 0 9", 
			"0 0 3 0 9 4 0 0 7",
			"0 0 4 0 0 0 0 0 8", 
			"5 0 6 0 1 0 0 0 0");
	
	
	// Provided medium 5 3 grid
	public static final int[][] mediumGrid = Sudoku.stringsToGrid(
	 "530070000",
	 "600195000",
	 "098000060",
	 "800060003",
	 "400803001",
	 "700020006",
	 "060000280",
	 "000419005",
	 "000080079");
	
	// Provided hard 3 7 grid
	// 1 solution this way, 6 solutions if the 7 is changed to 0
	public static final int[][] hardGrid = Sudoku.stringsToGrid(
	"3 7 0 0 0 0 0 8 0",
	"0 0 1 0 9 3 0 0 0",
	"0 4 0 7 8 0 0 0 3",
	"0 9 3 8 0 0 0 1 2",
	"0 0 0 0 4 0 0 0 0",
	"5 2 0 0 0 6 7 9 0",
	"6 0 0 0 2 1 0 4 0",
	"0 0 0 5 3 0 9 0 0",
	"0 3 0 0 0 0 0 5 1");
	
	
	public static final int SIZE = 9;  // size of the whole 9x9 puzzle
	public static final int PART = 3;  // size of each 3x3 part
	public static final int MAX_SOLUTIONS = 100;
	public static final int START =0;
	
	public int[][] grid;
	private ArrayList<Spot> spots;
	private long startTime;
	//private int solCount=0;
	private ArrayList<String> solutions;
	
	public class Spot implements Comparable {

		int row;
		int column;
		int unused;

		public Spot(int r, int c) {
			row = r;
			column = c;
			unused = unused().size();
		}

		// get hashset of available numbers
		public HashSet<Integer> unused() {
			HashSet<Integer> used = used();
			HashSet<Integer> unused = new HashSet<Integer>();
			for (int i = 0; i <= SIZE; i++)
				if (!used.contains(i))
					unused.add(i);
			return unused;
		}

		// get hashset of not available numbers
		private HashSet<Integer> used() {
			HashSet<Integer> filledNeighbours = new HashSet<Integer>();
			addRow(filledNeighbours);
			addCol(filledNeighbours);
			addBlock(filledNeighbours);
			return filledNeighbours;
		}

		// get used numbers in block
		private void addBlock(HashSet<Integer> filledNeighbours) {
			int baseR=(row / PART) * PART;
			int baseC=(column / PART) * PART;
			for (int i = 0; i < PART; i++)
				for (int j = 0; j < PART; j++)
					filledNeighbours.add(grid[baseR + i][baseC + j]);
		}

		//get used numbers in column
		private void addCol(HashSet<Integer> filledNeighbours) {
			for (int i = 0; i < SIZE; i++){
				filledNeighbours.add(grid[i][column]);
			}
		}

		//get used numbers in row
		private void addRow(HashSet<Integer> filledNeighbours) {
			for (int i = 0; i < SIZE; i++)
				filledNeighbours.add(grid[row][i]);
		}

		// set value v to spot
		public void set(int v) {
			grid[row][column] = v;
		}
		
		/*
		public int get(){
			return grid[row][column];
		}*/

		@Override
		public int compareTo(Object arg0) {
			return Integer.compare(this.unused, ((Spot) arg0).unused);
		}
		
		@Override
		public String toString() {
			return "row=" + row + ", column=" + column + ", unused="+ unused+", currVal="+grid[row][column];
		}
	}	
	
	
	// Provided various static utility methods to
	// convert data formats to int[][] grid.
	
	/**
	 * Returns a 2-d grid parsed from strings, one string per row.
	 * The "..." is a Java 5 feature that essentially
	 * makes "rows" a String[] array.
	 * (provided utility)
	 * @param rows array of row strings
	 * @return grid
	 */
	public static int[][] stringsToGrid(String... rows) {
		int[][] result = new int[rows.length][];
		for (int row = 0; row<rows.length; row++) {
			result[row] = stringToInts(rows[row]);
		}
		return result;
	}
	
	
	/**
	 * Given a single string containing 81 numbers, returns a 9x9 grid.
	 * Skips all the non-numbers in the text.
	 * (provided utility)
	 * @param text string of 81 numbers
	 * @return grid
	 */
	public static int[][] textToGrid(String text) {
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE) {
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		}
		
		int[][] result = new int[SIZE][SIZE];
		int count = 0;
		for (int row = 0; row<SIZE; row++) {
			for (int col=0; col<SIZE; col++) {
				result[row][col] = nums[count];
				count++;
			}
		}
		return result;
	}
	
	
	/**
	 * Given a string containing digits, like "1 23 4",
	 * returns an int[] of those digits {1 2 3 4}.
	 * (provided utility)
	 * @param string string containing ints
	 * @return array of ints
	 */
	public static int[] stringToInts(String string) {
		int[] a = new int[string.length()];
		int found = 0;
		for (int i=0; i<string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				a[found] = Integer.parseInt(string.substring(i, i+1));
				found++;
			}
		}
		int[] result = new int[found];
		System.arraycopy(a, 0, result, 0, found);
		return result;
	}


	// Provided -- the deliverable main().
	// You can edit to do easier cases, but turn in
	// solving hardGrid.
	public static void main(String[] args) {
		Sudoku sudoku;
		sudoku = new Sudoku(myg);
		
		System.out.println(sudoku); // print the raw problem
		int count = sudoku.solve();
		System.out.println("solutions:" + count);
		System.out.println("elapsed:" + sudoku.getElapsed() + "ms");
		System.out.println(sudoku.getSolutionText());
	}

	/**
	 * Sets up based on the given ints.
	 */
	public Sudoku(int[][] ints) {
		grid=new int[SIZE][SIZE]; // sudoku board
		spots= new ArrayList<Spot>(); // empty spots
		solutions=new ArrayList<String>(); // list of possible solutions
		for (int i = 0; i < SIZE; i++){
			System.arraycopy(ints[i], 0, grid[i], 0, SIZE);
		}
		for (int i = 0; i < SIZE; i++){
			for(int j=0;j<SIZE;j++)
				if(grid[i][j]==0)
					spots.add(new Spot(i,j));
		}
		Collections.sort(spots);
		startTime = System.currentTimeMillis();
		
	}
	
	/**
	 * Sets up based on the given string.
	 */
	public Sudoku(String s){
		this(textToGrid(s));
	}
	
	@Override
	public String toString() {
		return toString(grid);
	}
	
	// make string of current sudoku state. spots separated with spaces
	public String toString(int[][] grid) {
		StringBuilder sb= new StringBuilder();
        if (grid != null)
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++){
                	sb.append(grid[i][j]);
                	sb.append(" ");
                	/* useful for debug
                	if(i%PART==PART-1&&j%PART==PART-1)
                		sb.append("_|_");
                	else if(j%PART==PART-1)
                		sb.append(" | ");
                	else if(i%PART==PART-1)
                		sb.append("___");
                	else
                		sb.append("   ");
                	*/
                }
                sb.append('\n');
            }
        return sb.toString();
    }
	
	/**
	 * Solves the puzzle, invoking the underlying recursive search.
	 */
	public int solve() {
		recursiveSolve(START);
		return solutions.size();
	}
	
	
	private void recursiveSolve(int n){
		if (n > spots.size()-1) {
			//solCount++;
			solutions.add(this.toString());
			return;
		}
		
		HashSet<Integer> unuseds = spots.get(n).unused();
		if (unuseds.size() == 0 && n != (spots.size()-1))
			return;
		
		for(Integer num : unuseds){
			Spot curSpot=spots.get(n);
			curSpot.set(num);
			recursiveSolve(n+1);
			curSpot.set(0);
			if(solutions.size()>=MAX_SOLUTIONS)break;
		}
	}
	
	public String getSolutionText() {
		return solutions.get(START);
	}
	
	public long getElapsed() {
		return System.currentTimeMillis() - startTime;
	}

}
