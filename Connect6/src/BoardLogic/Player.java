package BoardLogic;

import java.awt.Graphics;
import java.math.BigInteger;

public interface Player {
	void draw(Graphics graphics);
	boolean Move(int row, int col);
	boolean didWin();
	BigInteger getPieces();
	void setPieces(BigInteger pieces);
	
}
