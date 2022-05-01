package BoardLogic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player {
	
	BigInteger pieces;

	BufferedImage image;

	public static ArrayList<BigInteger> allMasks = new ArrayList<BigInteger>();
	public static ArrayList<BigInteger>horizontalMasks = new ArrayList<BigInteger>();
	public static ArrayList<BigInteger>verticalMask = new ArrayList<BigInteger>();
	public static ArrayList<BigInteger>DiagonalMasks1 = new ArrayList<BigInteger>();
	public static ArrayList<BigInteger>DiagonalMask2 = new ArrayList<BigInteger>();


	private static int maxHorizontalAndVerticalRuns = 13 * 19, maxNumOfShiftsForHorizontal = 13,
			maxNumOfShiftsForDiagonal = 13, maxDiagonalRuns = 13 * 13;

	public Player(BigInteger pieces, String fileName) {
		this.pieces = pieces;

		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void draw(Graphics graphics) {

		BigInteger mask = new BigInteger("1");
		BigInteger temp = new BigInteger("0");
		for (int i = 0; i <= Dimensions.N * Dimensions.N; i++, mask = mask.shiftLeft(1)) {
			if (i > 0 && i % 19 == 0)
				mask = mask.shiftLeft(1);
			if (pieces.and(mask).compareTo(temp) != 0) {
				graphics.drawImage(image,
						Dimensions.boardXStart + (i % Dimensions.N) * Dimensions.CellSize - (Dimensions.CellSize / 3),
						Dimensions.boardYStart + (i / Dimensions.N) * Dimensions.CellSize, Dimensions.CellSize,
						Dimensions.CellSize, null);
			}
		}

	}
	
	public static void calcAllMasks() {
		BigInteger checkHorizontalMask = new BigInteger("3F", 16);// 24
		BigInteger checkVerticalMask = new BigInteger("10000100001000010000100001", 16);// 24
		BigInteger checkDiagonalMask1 = new BigInteger("200001000008000040000200001", 16);
		BigInteger checkDiagonalMask2 = new BigInteger("10000200004000080001000020", 16);

		allMasks.clear();
		int diver = 14;
		for (int diagRun = 1; diagRun < 15 * 14; diagRun++) {
			allMasks.add(checkDiagonalMask1);
			allMasks.add(checkDiagonalMask2);
			if (diagRun % 15 != 0) {

				checkDiagonalMask1 = checkDiagonalMask1.shiftLeft(1);
				checkDiagonalMask2 = checkDiagonalMask2.shiftLeft(1);
			} else {
				checkDiagonalMask1 = checkDiagonalMask1.shiftLeft(6);
				checkDiagonalMask2 = checkDiagonalMask2.shiftLeft(6);
			}

		}
	
		for (int rowRun = 0; rowRun < 20; rowRun++) {
			for (int colRun = 0; colRun <= 14; colRun++) {
				allMasks.add(checkHorizontalMask);
				allMasks.add(checkVerticalMask);
				if (colRun == 14)
					checkHorizontalMask = checkHorizontalMask.shiftLeft(6);
				else {
					checkHorizontalMask = checkHorizontalMask.shiftLeft(1);
				}
				checkVerticalMask = checkVerticalMask.shiftLeft(1);
			}

		}

	}

	public  boolean didWin() {

		for (BigInteger mask : allMasks) {
			if (pieces.and(mask).equals(mask)) {
				System.out.println("Win ayy");
				return true;
			}
		}
		return false;
	}
	
	public  boolean Move(int row, int col,List<BoardPoint>moveList) {
		BigInteger mask = new BigInteger("1");
		mask = mask.shiftLeft(row * (Dimensions.N + 1) + col);
		if (isEmpty(mask) == true) {
			pieces = pieces.or(mask);
			Board.combinedBoard = Board.combinedBoard.or(mask);
			moveList.add(new BoardPoint(row,col));
			return true;
		} else
			return false;

	}
	public  boolean isEmpty(BigInteger mask) {
		if (Board.combinedBoard.and(mask).equals(mask))
			return false;
		return true;
	}
	public BigInteger getPieces() {
		// TODO Auto-generated method stub
		return this.pieces;
	}
}
