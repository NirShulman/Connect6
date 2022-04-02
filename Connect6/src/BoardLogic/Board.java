package BoardLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;

import BoardLogic.Dimensions;
import javax.swing.RepaintManager;

import BoardLogic.Dimensions;
import GUI.GameFrame;
import GUI.gamePanel;

public class Board {
	
	int piecesToPut = 1;// how Many pieces player can put this round

	public boolean over = false;// if game is over

	public String winMessage = null;// message when game is over according to player who won,or draw

	public static BigInteger combinedBoard;
	private Player playerB, playerW;
	private Player curPlayer;

	public Board() {
		playerW = new Player(new BigInteger("0"), "Images/White.png"); // 1010 1010 0 0101 0101 0 1010 1010
		playerB = new Player(new BigInteger("0"), "Images/Black.png");
		curPlayer = playerB;
		combinedBoard = new BigInteger("0");
	}

	public void draw(Graphics graphics) {
		playerB.draw(graphics);
		playerW.draw(graphics);
		AttributedCharacterIterator x;
		
		
		

	}

	public void Click(Point point) {

		int col = (point.x - Dimensions.boardXStart) / Dimensions.CellSize;

		int row = (int) Math.round((double) (point.y - Dimensions.boardYStart) / Dimensions.CellSize);
	
		boolean validMove = curPlayer.Move(row, col);
		
		if (validMove) {

			checkWin(curPlayer);
						
			piecesToPut--;
			if (piecesToPut == 0) {

				piecesToPut = 2;
				if (curPlayer==playerB)
					curPlayer = playerW;
				else
					curPlayer = playerB;
			}
		}

	}

	public boolean checkWin(Player p) {
		if (p.didWin()) {
			over=true;
			
			if(p==playerB)
				winMessage= "Black Won";
			else
				winMessage = "White Won";
			return true;
			
		}
		return false;
		
	}

	public boolean checkDraw(Player p, Player p2) {
		BigInteger fullBoard = new BigInteger(Dimensions.fullBoardHex, 16);
		BigInteger temp = new BigInteger("0", 2);
		temp = temp.or(p.pieces).or(p2.pieces);
		if (temp.equals(fullBoard))
			return true;
		return false;
	}

}
