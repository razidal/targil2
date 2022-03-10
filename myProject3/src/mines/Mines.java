package mines;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Mines {//class for the minesweeper game

	private int height,width,numMines;
	private boolean showAll;
	private Game game[][];
	private Random rand = new Random();
	public Mines(int height, int width, int numMines) {//a constructor the initialize the game 
		this.height=height;
		this.width=width;
		this.numMines=numMines;
		this.showAll=false;
		game=new Game[height][width];
		int count = 0;
		for (int i=0;i<height;i++) {//starting a new game 
			for (int j=0;j<width;j++) {
				game[i][j]=new Game(i,j);
			}
		}
		while(count<this.numMines) {
			int x=rand.nextInt(height);//a getting a random places to put mines in the game
			int y=rand.nextInt(width);
			if (!game[x][y].mine) {//if there's isn't a mines the game created
				game[x][y].mine=true;//putting on mines 
				count++;
			}
		}
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				game[i][j].Neighbours();
			}
		}
	}
	public boolean addMine(int i, int j) {//adding mines method 
		if (game[i][j].mine) {//if there's a mine in i,j place on board 
			return false;
		}
		game[i][j].mine = true;//replacing existing mine 
		return true;
	}
	public boolean open(int i, int j) {//opens a place on board as long as it's not a mine returns true
		if (game[i][j].mine) {
			return false;
		}
		game[i][j].open = true;
		if (game[i][j].numOfMines()==0) {
			Set<Game> neighbours=game[i][j].neighbor;
			for (Game g:neighbours) {//checking the neighbors if they are to be opened
				if(!g.open) {
					if(!open(g.x,g.y)) {
						return true;
					}
				}
			}
		}
		return true;
	}
	public void toggleFlag(int x, int y) {//setting a flag in x,y place
		game[x][y].flag=!game[x][y].flag;
	}
	public boolean isDone() {//a method that returns true if all the place on board that aren't mines are opened
		for (int i=0;i<height;i++) {
			for (int j=0;j<width; j++) {
				if (!game[i][j].mine && !game[i][j].open) {//are not mines and aren't opened 
					return false;
				}
			}
		}
		return true;
	}
	public String get(int i, int j) { //displaying board as a string
		return game[i][j].toString();
	}
	public void setShowAll(boolean showAll) {//shows the opened places
		this.showAll = showAll;
	}
	public String toString() {//method to build a string out of the board
		StringBuilder str = new StringBuilder();
		boolean flag = false;
		for (int i=0;i<height;i++) {
			for (int j=0;j<width; j++) {
				String s=game[i][j].toString();
				if (!s.equals("")) {
					str.append(s);
					flag = true;
				}
			}
			if (flag) {
				str.append("\n");
			}
			flag = false;
		}
		return str.toString();

	}
	public class Game { //inner class 
		private boolean open,flag,mine;
		private int x, y;
		private Set<Game> neighbor;
		public Game(int x, int y) {
			this.x = x;
			this.y = y;
			open = false;
			mine = false;
			flag = false;
			neighbor = new HashSet<>();
		}
		public boolean getOpen() {//flag for opened places
			return open;
		}
		public boolean getMine() {//flag for mines
			return mine;
		}
		public boolean getFlag() {//flag for flags (in the game)
			return flag;
		}
		public int numOfMines() {//method to count the number of mines on board
			Iterator<Game> iter=neighbor.iterator();
			int count = 0;
			while (iter.hasNext()) {
				if (iter.next().mine) {
					count++;
				}
			}
			return count;
		}
		public void Neighbours() {//method to get neighbors of opened place
			for (int i=x-1;i<=x+1;i++) {
				for (int j=y-1;j<=y+1;j++) {
					if (i==x && j==y) {
						continue;
					}
					if (getPoint(i, j)) {
						neighbor.add(game[i][j]);
					}
				}
			}
		}
		public boolean getPoint(int i, int j) { //method to get place that isn't off the borders of the game
			return (i>=0 && j>=0 && i<height && j<width);
		}
		public String toString() {//setting displays for the game to show player his actions
			if (open || showAll) {
				if (mine) {//if it's a mine show X
					return "X";
				} else {
					if (numOfMines() == 0) {
						return " ";//if there's no mines
					}
					return "" +numOfMines();
				}
			} else 
				if (flag) {				
					return "F";//if player put flag show F
				}
				else {
					return ".";//unopened place
				}
		}
	}
}
