package BoardLogic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ComputerPlayer implements Player {

	BigInteger pieces;

	BufferedImage image;

	public BigInteger getPieces() {
		return pieces;
	}

	public void setPieces(BigInteger pieces) {
		this.pieces = pieces;
	}

	public ComputerPlayer(BigInteger pieces, String fileName) {
		this.pieces = pieces;

		try {
			image = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics graphics) {

		playerFunctions.draw(pieces, image, graphics);

	}
	
	public List<BigInteger> getPossibleMoves()
	{
		List<BigInteger>list = new ArrayList<BigInteger>();
		BigInteger mask = new BigInteger("1");
		for (int i = 0; i <= Dimensions.N * Dimensions.N; i++, mask = mask.shiftLeft(1)) {
			if (i > 0 && i % 19 == 0)
				mask = mask.shiftLeft(1);
			if(playerFunctions.isEmpty(mask))
				list.add(mask);
		}
		return list;
	}
	public BigInteger getBestMove()
	{
		BigInteger bestMove = null;
		int max_grade= Integer.MIN_VALUE;
		List<BigInteger>moves = getPossibleMoves();
		
		for(BigInteger move : moves)
		{
			
		}
		return null;
	}
	
	@Override
	public boolean Move(int row, int col) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean didWin() {
		return playerFunctions.didWin(pieces);
	}
}
