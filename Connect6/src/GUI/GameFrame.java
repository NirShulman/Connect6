package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import BoardLogic.Board;
import BoardLogic.Player;


public class GameFrame extends JFrame {
	
	JPanel pane;
	BufferedImage icon;
	public GameFrame() {
		
	
		try {
		
			icon = ImageIO.read(new File("Images/icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	
		
	
		Player.calcAllMasks();
         pane = new gamePanel();
       
        this.setIconImage(icon);
        this.setTitle("Java Connect6 Game");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(pane);
		
		Color bg = new Color(242,176,109,255);
		pane.setBackground(bg);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

}
