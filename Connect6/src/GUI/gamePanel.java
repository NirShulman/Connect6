package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.tools.Tool;

import BoardLogic.Board;
import BoardLogic.Dimensions;

public class gamePanel extends JPanel {
	Image image, cursor, winImage;

	Board board;
	Point curPoint = new Point();
	int xCursor, yCursor;
	// public static int boardXStart =
	// (Board.CellSize*((Board.N)/2)-((2*Board.CellSize)/3));
	JLabel jlabel;

	public boolean hasWon() {
		return board.over;
	}

	public void addWinTextAndDisablePanel(Graphics g) {

		for (MouseMotionListener mmL : this.getMouseMotionListeners())
			this.removeMouseMotionListener(mmL);
		for (MouseListener mL : this.getMouseListeners())
			this.removeMouseListener(mL);

		Graphics2D g2d = (Graphics2D) g;

		int xForImage = (this.getWidth() - winImage.getWidth(null)) / 2;
		int yForImage = (this.getHeight() - winImage.getHeight(null)) / 2;
		g2d.drawImage(winImage, 0, 0, this.getWidth(), this.getHeight(), null);

		String text = new String(board.winMessage);
		Font font = new Font("Calibri", Font.ITALIC, 96);
		g.setColor(Color.WHITE);
		FontMetrics metrics = g.getFontMetrics(font);
		int x = (this.getWidth() - metrics.stringWidth(text)) / 2;
		int y = (this.getHeight() - metrics.getHeight()) / 2;
		g.setFont(font);
		g.drawString(text, x, y);

	}

	public void paint(Graphics g) {

		super.paint(g);
		if (!hasWon()) {
			g.drawImage(image, Dimensions.imageOfBoardX, 0, Dimensions.boardWidth, Dimensions.boardWidth, null);
			g.drawImage(cursor, xCursor - Dimensions.CellSize / 4, yCursor + Dimensions.CellSize / 8,
					Dimensions.CellSize / 2, Dimensions.CellSize / 2, null);
			board.draw(g);
		} else {
			addWinTextAndDisablePanel(g);
			repaint();
		}

	}

	public gamePanel() {

		board = new Board(true);
		try {

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			winImage = toolkit.getImage("Images/winImage.gif");

			image = toolkit.getImage("Images/goboard.png");

			cursor = toolkit.getImage("Images/green.png");
		} catch (Exception e) {
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

				if (yCursor >= Dimensions.boardYStart && yCursor <= Dimensions.boardYEND) {
					yCursor = (yCursor / Dimensions.CellSize) * Dimensions.CellSize;
				} else if (yCursor < Dimensions.boardYStart) {
					yCursor = Dimensions.boardYStart;
				} else {
					yCursor = Dimensions.boardYEND;
				}

				// 813

				if (xCursor >= Dimensions.boardXStart && xCursor <= Dimensions.boardXEND) {
					adjuster = xCursor % Dimensions.CellSize / (Dimensions.CellSize / 2);
					xCursor = (xCursor / Dimensions.CellSize + adjuster) * Dimensions.CellSize;
				} else if (xCursor <= Dimensions.boardXStart) {
					xCursor = Dimensions.boardXStart;
				} else {
					xCursor = Dimensions.boardXEND;
				}

				curPoint.setLocation(xCursor, yCursor);
				repaint();

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		setVisible(true);
	}

}
