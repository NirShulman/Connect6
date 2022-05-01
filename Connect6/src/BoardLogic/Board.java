package BoardLogic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.util.LinkedList;
import java.util.List;

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
	public static List<BoardPoint> blackMoves;
	List<BoardPoint> whiteMoves;
	List<BoardPoint> curList;
	
	boolean AIgame;

	public Board(boolean AI) {
		this.AIgame = AI;
		blackMoves = new LinkedList<BoardPoint>();
		whiteMoves = new LinkedList<BoardPoint>();
		playerB = new Player(new BigInteger("0"), "Images/Black.png");
		curPlayer = playerB;
		curList = blackMoves;
		combinedBoard = new BigInteger("0");
		if (AI)
			playerW = new ComputerPlayer(new BigInteger("0"), "Images/White.png");
		else
			playerW = new Player(new BigInteger("0"), "Images/White.png");
	}

	public void draw(Graphics graphics) {
		playerB.draw(graphics);
		playerW.draw(graphics);
		

	}

	public void Click(Point point) {

		int col = (point.x - Dimensions.boardXStart) / Dimensions.CellSize;

		int row = (int) Math.round((double) (point.y - Dimensions.boardYStart) / Dimensions.CellSize);

		boolean validMove = curPlayer.Move(row, col,curList);

		if (validMove) {

			checkWin(curPlayer);
			checkDraw(playerB, playerW);
			piecesToPut--;
			if(AIgame)
				AIafterClick();
			else
				normalSwitch();
				
		}

	}
	public void AIafterClick()
	{
		if (piecesToPut == 0)
		{
			playerW.Move(0, 0,whiteMoves);
			playerW.Move(0, 0 ,whiteMoves);
			checkWin(curPlayer);
			checkDraw(playerB, playerW);
			piecesToPut=2;
		}
	}
	public void normalSwitch()
	{
		if (piecesToPut == 0) {

			piecesToPut = 2;
			if (curPlayer == playerB) {
				curPlayer = playerW;
				curList = whiteMoves;
			} else {
				curPlayer = playerB;
				curList = blackMoves;
			}
		}

	}
	public boolean checkWin(Player p) {
		if (p.didWin()) {
			over = true;

			if (p == playerB)
				winMessage = "Black Won";
			else
				winMessage = "White Won";
			return true;

		}
		return false;

	}

	public boolean checkDraw(Player p, Player p2) {
		BigInteger fullBoard = new BigInteger(Dimensions.fullBoardHex, 16);
		BigInteger temp = new BigInteger("0", 2);
		temp = temp.or(p.getPieces()).or(p2.getPieces());
		if (temp.equals(fullBoard)) {
			winMessage = "Draw!";
			return true;
		}
		return false;
	}

}
