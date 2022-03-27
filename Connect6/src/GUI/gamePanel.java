package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import BoardLogic.Board;
import BoardLogic.Dimensions;

public class gamePanel extends JPanel {
	BufferedImage image, cursor;
	Board board;
	Point curPoint = new Point();
	int xCursor, yCursor;
	// public static int boardXStart =
	// (Board.CellSize*((Board.N)/2)-((2*Board.CellSize)/3));
	JLabel jlabel ;
	
public void addWinTextAndDisablePanel(Graphics g)
{
	String text = new String("Win!");
	Font font = new Font("Calibri",Font.ITALIC, 96);
	g.setColor(new Color(196, 30, 58));
	FontMetrics metrics = g.getFontMetrics(font);
	int x = Dimensions.imageOfBoardX+(Dimensions.boardWidth - metrics.stringWidth(text))/2;
	int y = (Dimensions.boardWidth - metrics.stringWidth(text))/2;
	g.setFont(font);
	g.drawString(text, (Dimensions.screenSize.width-metrics.stringWidth(text))/2, (Dimensions.screenSize.height-metrics.getHeight())/2);
}
	public void paint(Graphics g) {
		
		super.paint(g);
	
		
		g.drawImage(image, Dimensions.imageOfBoardX, 0, Dimensions.boardWidth, Dimensions.boardWidth, null);
		g.drawImage(cursor, xCursor-Dimensions.CellSize/4, yCursor + Dimensions.CellSize/8, Dimensions.CellSize / 2, Dimensions.CellSize / 2, null);
		board.draw(g);
		addWinTextAndDisablePanel(g);
		/*for(MouseListener mL :this.getMouseListeners())
		{
			
			this.removeMouseListener(mL);
		}*///disabling clicks
		
		
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
		WinText win = new WinText();
		
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
					xCursor = (xCursor /Dimensions.CellSize + adjuster) *Dimensions.CellSize;
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
	
		 
		   
		    JLabel label1 = new JLabel();
		    label1.setText("WIN!!!!");
		    label1.setBounds(0, 0, 200, 50);
		    
		    setVisible(true);
	}

}
