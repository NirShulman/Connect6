package BoardLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.math.BigInteger;

import javax.swing.RepaintManager;

import GUI.GameFrame;
import GUI.gamePanel;

public class Board {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int N = 19;// width and height
	public static final String fullBoardHex = "1FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
	public static int CellSize =  (int)((screenSize.getHeight()/N)-(screenSize.getHeight()/N/9));
	boolean turn = true;// true = black ,false =white
	int piecesToPut = 1;//how Many pieces player can put this round
	int count =0;
	boolean firstTurn = true;
	public  boolean over = false;//if game is over
	
	public String winMessage = null;//message when game is over according to player who won,or draw
	
	BigInteger combinedBoard;
	Player playerB, playerW;
	

	public Board() {
		playerW = new Player(new BigInteger("0"), "Images/White.png"); // 1010 1010 0 0101 0101 0 1010 1010
		playerB = new Player(new BigInteger("0"), "Images/Black.png");
		combinedBoard = new BigInteger("0");
	}

	public void draw(Graphics graphics) {
		playerB.draw(graphics);
		playerW.draw(graphics);
	}


	public boolean Click(Point point) {
	

		int col = (point.x-gamePanel.boardXStart)/CellSize ;
		
		int row = (int) Math.round((double)(point.y-gamePanel.boardYStart)/CellSize);
		System.out.println("row:"+row+" Col:"+col);
	
		
		
	
		

	return	playerW.Move(row, col, combinedBoard);
		
	}
	
	public boolean checkWin(Player p) {
		if (p.didWin()) {
		
			if(turn==true)
				winMessage= "Black Won";
			else
				winMessage = "White Won";
			System.out.println(winMessage);
			return true;
			
		}
		return false;
	}
	public boolean checkDraw(Player p ,Player p2)
	{
		BigInteger fullBoard = new BigInteger(fullBoardHex,16);
		BigInteger temp = new BigInteger("0",2);
		temp = temp.or(p.pieces).or(p2.pieces);
		if(temp.equals(fullBoard))
			return true;
		return false;
	}

	
}
