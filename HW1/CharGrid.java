// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int minX = 0;
		int maxX=0;
		int minY = 0;
		int maxY=0;
		boolean br=false;
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				if(grid[i][j]==ch){
					minX=i;
					minY=j;
					br=true;
					break;
				}
			}
			if(br)break;
		}
		for(int i=minX;i<grid.length;i++){
			for(int j=minY;j<grid[i].length;j++){
				if(grid[i][j]==ch){
					maxX=i;
					maxY=j;
				}
			}
		}
		return (maxX-minX+1)*(maxY-minY+1);
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int count=0;
		for(int i=1;i<grid.length-1;i++){
			for(int j=1;j<grid[i].length-1;j++){
				System.out.println(count);
				if(isPlus(i,j))count++;
				System.out.println(count);
			}
		}
		return count;
	}
	
	private boolean isPlus(int x, int y){
		int[] ar = new int[4];
		for(int i=0;i<4;i++)ar[i]=getArm(x,y,i);
		if(ar[0]>1&&ar[0]==ar[1]&&ar[2]==ar[3]&&ar[3]==ar[0])return true;
		return false;
	}
	
	/*
	 * get arm lenght. orient: 0=up; 1=right; 2=down; 3=left
	 */
	private int getArm(int x,int y,int orient){
		char ch=grid[x][y];
		System.out.println(ch);
		//System.out.println(x);
		//System.out.println(y);
		int xp=1,yp=1;
		switch(orient){
		case 0:xp=0;
		yp=-1;
		break;
		case 1:xp=1;
		yp=0;
		break;
		case 2:xp=0;
		yp=1;
		break;
		case 3:xp=-1;
		yp=0;
		break;
		}
		int res=0;
		while(nOut(x,y)&&grid[x][y]==ch){
			System.out.println(nOut(x,y));
			System.out.println(grid[x][y]);
			res++;
			x+=xp;
			y+=yp;
		}
		//System.out.println(grid[x][y]);
		System.out.println(res);
		return res;
	}
	
	private boolean nOut(int x, int y){
		if(x>=0&&x<=grid.length-1&&y>=0&&y<=grid[x].length-1)return true;
		return false;
	}
	
}
