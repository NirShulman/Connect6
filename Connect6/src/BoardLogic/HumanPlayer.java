package BoardLogic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GUI.GameFrame;
import GUI.gamePanel;

public class HumanPlayer implements Player {
	

	BigInteger pieces;

	BufferedImage image;

	public BigInteger getPieces() {
		return pieces;
	}

	public void setPieces(BigInteger pieces) {
		this.pieces = pieces;
	}

	public HumanPlayer(BigInteger pieces, String fileName) {
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

	
	public boolean Move(int row, int col) {
		return playerFunctions.Move(pieces, row, col);
	}

	@Override
	public boolean didWin() {
		return playerFunctions.didWin(pieces);
	}


}
