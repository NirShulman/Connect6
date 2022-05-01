package BoardLogic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
enum lpattern{
	
}
}
public class ComputerPlayer extends Player {
	static final int dontCheck = -40;

	public ComputerPlayer(BigInteger pieces, String fileName) {
		super(pieces, fileName);
	}

	public boolean isOpponentStone(BigInteger mask) {
		if (super.isEmpty(mask) == false && mask.and(super.pieces).equals(mask) == false)
			return true;
		return false;

	}

	public List<BoardPoint> getPossibleMoves() {
		List<BoardPoint> list = new ArrayList<BoardPoint>();
		BigInteger mask = new BigInteger("1");
		for (int i = 0; i <= Dimensions.N * Dimensions.N; i++, mask = mask.shiftLeft(1)) {
			if (i > 0 && i % 19 == 0)
				mask = mask.shiftLeft(1);
			if (super.isEmpty(mask))
				list.add(new BoardPoint(i / Dimensions.N, i % Dimensions.N));
		}
		return list;
	}

	public boolean outOfBorder(BoardPoint p) {
		if (p.getRow() > 18 || p.getRow() < 0 || p.getCol() > 18 || p.getCol() < 0)
			return true;
		return false;
	}

	public boolean isOutOfBorder(int row, int col, int rowMover, int colMover) {
		int colCheck, rowCheck;
		if (rowMover != dontCheck && colMover != dontCheck) {
			rowCheck = rowMover;
			colCheck = colMover;
		} else if (rowMover != dontCheck) {
			rowCheck = rowMover;
			colCheck = col;
		} else {
			rowCheck = row;
			colCheck = colMover;
		}

		if (rowCheck < 0 || rowCheck > 18 || colCheck < 0 || colCheck > 18)
			return true;
		return false;
	}

	public List<BoardPoint> getPointsForDefence() {
		List<BoardPoint> availableMoves = new ArrayList<BoardPoint>();
		LinkedList<BoardPoint> list = (LinkedList<BoardPoint>) Board.blackMoves;
		BoardPoint lastMove = list.getLast();
		BoardPoint lastMove2;
		if (list.size() > 1) {
			lastMove2 = list.get(list.size() - 2);
			availableMoves.addAll(getPoint5x5Area(lastMove2));
		}
		availableMoves.addAll(getPoint5x5Area(lastMove));

		return availableMoves;
	}

	public List<BoardPoint> getPoint5x5Area(BoardPoint p) {
		List<BoardPoint> EmptyPointslist = new ArrayList<BoardPoint>();
		for (int i = -2; i <= 2; i++) {
			for (int j = -2; j <= 2; j++) {
				BoardPoint newPoint = new BoardPoint(p.getRow() + i, p.getCol() + j);
				BigInteger move = new BigInteger("1");
				move = move.shiftLeft(newPoint.getRow() * (Dimensions.N + 1) + newPoint.getCol());
				if (super.isEmpty(move) == true)
					if (outOfBorder(newPoint) == false)
						EmptyPointslist.add(newPoint);
			}
		}
		return EmptyPointslist;
	}

	public BigInteger getBlackBoard() {
		return Board.combinedBoard.xor(pieces);
	}

	public BigInteger getDefenderBoard(BigInteger AttackerBoard) {
		return Board.combinedBoard.xor(AttackerBoard);
	}

	public void ThreatSpaceSearch(BigInteger AttackerBoard) {

	}
	public static int MostSignificantBitUsingLog(BigInteger i)
	{
		BigInteger zero = new BigInteger("0");
	    int bit;
	    if (i.equals(zero))
	        bit = -1;
	    else
	        bit =BigInteger.Log

	    return bit;
	}

	public int defensiveSlideWindow(BigInteger pattern) {
		BigInteger window = new BigInteger("111111");
		pattern.max(window)
		int leastSag = pattern.and(Board.combinedBoard).getLowestSetBit();// index of least sagnificant bit
		int amountToShift = 
		if (leastSag >= 6) {
			window.shiftLeft(leastSag);
		}
		return 0;
	}

	public int SlidingWindow(BigInteger pattern,BigInteger AttackerBoard)
	{
		BigInteger marking = new BigInteger("0");
		BigInteger windowRes;
		int threatNum=0;
		for(BigInteger window : allMasks)
		{
			if(window.and(attacker).equals(window))
			{
				threatNum=Integer.MAX_VALUE;
				break;
			}
			windowRes = window.and(Board.combinedBoard)
			else if(window.and(Board.combinedBoard).bitCount()>=4)
			{
				threatNum++;
			}
				
		}
		return threatNum;
		
		
		
	}

	public BoardPoint defend()// defensive strategy
	{
		List<BoardPoint> points = getPointsForDefence();
		double max_score = Double.MIN_VALUE;
		double point_score;
		BoardPoint BestBlockPoint = null;
		for (BoardPoint p : points) {
			System.out.println("row:" + p.getRow() + " Col:" + p.getCol());
			point_score = hMoveEvaluation(p.getRow(), p.getCol());
			if (point_score > max_score) {
				max_score = point_score;
				BestBlockPoint = p;
			}

		}
		System.out.println("----------------------");

		return BestBlockPoint;
	}

	public boolean Move(int row, int col, List<BoardPoint> moveList) {
		BoardPoint bestMove = defend();
		return super.Move(bestMove.row, bestMove.col, moveList);

	}

	public double hMoveEvaluation(int row, int col)// half move evaluation
	{
		double Eval = 0;
		int directionsWithNoEnemy = 4;

		int eDirectional;
		double emptyW = 2;// weight of emptyPos
		double ownStoneW = Math.pow(2, 12);// weight of own stone
		BigInteger mask = new BigInteger("1");
		boolean hasOpponentStone;// has an opponent stone in the direction
		mask = mask.shiftLeft(row * (Dimensions.N + 1) + col);

		for (int i = 0; i < 4; i++)// 4 directions to check
		{
			hasOpponentStone = false;
			eDirectional = 1;
			int dir = 1;
			int dir1 = dir;
			int colMover = dontCheck;
			int rowMover = dontCheck;
			switch (i) {
			case (0):
				colMover = col;
				break;
			case (1):
				rowMover = row;
				break;
			case (2):
				colMover = col;
				rowMover = row;
				break;
			case (3):
				colMover = col;
				rowMover = row;
				dir1 = -dir1;
				break;

			}
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < 5; k++) {
					if (rowMover != dontCheck) {
						rowMover += dir;
						mask.shiftLeft(rowMover * (Dimensions.N + 1));
					} else {
						mask.shiftLeft(row * (Dimensions.N + 1));
					}
					if (colMover != dontCheck) {
						colMover += dir1;
						mask.shiftLeft(colMover);
					} else {
						mask.shiftLeft(col);
					}
					if (isOutOfBorder(row, col, rowMover, colMover)) {
						break;
					} else if (isEmpty(mask)) {
						eDirectional *= emptyW;
					} else if (mask.and(pieces).equals(mask)) // is white stone
					{

						if (hasOpponentStone = false) {
							directionsWithNoEnemy--;
							hasOpponentStone = true;
						}
						break;
					} else {

						ownStoneW /= 2;// as more own stones appear,weight decreases
						eDirectional *= ownStoneW;
					}

					mask = new BigInteger("1");

				}

				if (rowMover != dontCheck)
					rowMover = row;
				if (colMover != dontCheck)
					colMover = col;
				dir = -dir;
				dir1 = -dir1;
				ownStoneW = Math.pow(emptyW, 12);

			}
			Eval += eDirectional;
		}
		double degree = 1.0;

		switch (directionsWithNoEnemy)// degree for evaluation based on how many directions are clear of opponent
										// stones
		{
		case (4):
			degree = 1.000007265;
			break;
		case (3):
			degree = 1.00000363725;
			break;
		case (2):
			degree = 1.00000181862;
			break;
		case (1):
			degree = 1.0;
			break;
		}
		Eval *= degree;
		return Eval;

	}

	public BigInteger getBestMove() {
		BoardPoint bestMove = null;
		double max_grade = Double.MIN_VALUE;
		List<BoardPoint> moves = getPossibleMoves();
		for (BoardPoint move : moves) {
			double moveEval = hMoveEvaluation(move.getRow(), move.getCol());
			if (moveEval > max_grade) {
				max_grade = moveEval;
				bestMove = move;
			}
		}
		BigInteger best = new BigInteger("1");
		best = best.shiftLeft(bestMove.getRow() * (Dimensions.N + 1) + bestMove.getCol());
		return best;
	}

}
