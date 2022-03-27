package BoardLogic;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Dimensions {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int N = 19;// width and height
	public static final String fullBoardHex = "1FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
	public static int CellSize = (int) ((screenSize.getHeight() / N) - (screenSize.getHeight() / N / 9));

	public static int boardYStart = (int) (CellSize * 1);
	public static int boardYEND = (int) (1.015 * (CellSize *N));
	public static int boardWidth = (int) (CellSize * (N + 1));
	public static int imageOfBoardX = (int) (screenSize.getWidth() / 4.5);//where the entire picture of board is drawn in gamePanel

	public static int boardXStart = (int) (imageOfBoardX + CellSize*1.25);
	public static int boardXEND = boardXStart + (N - 1) * CellSize;

}
