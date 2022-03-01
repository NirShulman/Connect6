package GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import BoardLogic.Board;

public class gamePanel extends JPanel {
	BufferedImage image, cursor;
	Board board;
	Point curPoint = new Point();
	int xCursor, yCursor;
	// public static int boardXStart =
	// (Board.CellSize*((Board.N)/2)-((2*Board.CellSize)/3));

	public static int boardYStart = (int) (Board.CellSize * (1.15));
	int boardYEND = (int) (1.015 * (Board.CellSize * Board.N));
	static int boardWidth = (int) (Board.CellSize * (Board.N + 1));
	static int imageOfBoardX = (int) (Board.screenSize.getWidth() / 4.5);

	public static int boardXStart = (int) (imageOfBoardX + 70);
	int boardXEND = boardXStart + (Board.N - 1) * board.CellSize;

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, imageOfBoardX, 0, boardWidth, boardWidth, null);
		g.drawImage(cursor, xCursor-Board.CellSize/4, yCursor + Board.CellSize/8, Board.CellSize / 2, Board.CellSize / 2, null);
		board.draw(g);
	}

	/*
	 * @Override protected void paintComponent(Graphics g) {
	 * super.paintComponent(g);
	 * 
	 * 
	 * g.drawImage(image, ((Board.N-5)/2)*(Board.CellSize), 0,
	 * (Board.N+1)*Board.CellSize, (Board.N+1) * Board.CellSize,null);
	 * g.drawImage(cursor,xCursor-9,yCursor-7,25,25,null);
	 * 
	 * board.Paint(g); if(board.over==true) this.setVisible(false); }
	 */
	public gamePanel() {
		board = new Board();
		try {
			image = ImageIO.read(new File("Images/goboard.png"));
			cursor = ImageIO.read(new File("Images/green.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				board.Click(curPoint);
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

		});
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {// set pos for green cursor

				int adjuster;
				xCursor = (int) (e.getPoint().getX());
				yCursor = (int) (e.getPoint().getY());

			
				if (yCursor >= boardYStart && yCursor <= boardYEND) {
					yCursor = (yCursor / Board.CellSize) * Board.CellSize;
				} else if (yCursor < boardYStart) {
					yCursor = boardYStart;
				} else {
					yCursor = boardYEND;
				}

				// 813

				if (xCursor >= boardXStart && xCursor <= boardXEND) {
					adjuster = xCursor % Board.CellSize / (Board.CellSize / 2);
					xCursor = (xCursor / Board.CellSize + adjuster) * Board.CellSize;
				} else if (xCursor <= boardXStart) {
					xCursor = boardXStart;
				} else {
					xCursor = boardXEND;
				}

				
				curPoint.setLocation(xCursor, yCursor);
				repaint();

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

}
