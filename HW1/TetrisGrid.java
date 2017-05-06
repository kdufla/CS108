//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	private boolean[][] grid;
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid=grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		for(int j=0;j<grid[0].length;j++){
			if(checkRow(j)){
				clear(j);
				j--;
			}
		}
	}
	
	private void clear(int r){
		for(int i=0;i<grid.length;i++){
			for(int j=r;j<grid[i].length-1;j++){
				grid[i][j]=grid[i][j+1];
			}
		}
		add();
	}
	
	private boolean checkRow(int r){
		for(int j=0;j<grid.length;j++)if(!grid[j][r])return false;
		return true;
	}
	
	
	
	private void add(){for(int j=0;j<grid.length;j++)grid[j][grid[0].length-1]=false;}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	public boolean[][] getGrid(){return grid;}
	
}
