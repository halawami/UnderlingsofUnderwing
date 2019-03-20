package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.Card;
import Game.Handler;
import Player.Player;

public class LameGUI implements GUI {
	private final int WIDTH = 400, HEIGHT = 400;
	private JFrame frame;
	private Image img;
	private Graphics g;
	
	public LameGUI() {
		frame = new JFrame("Lame Underlings");
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = img.getGraphics();
		
		frame.setVisible(true);
		frame.getGraphics().setColor(Color.BLUE);
		frame.getGraphics().fillRect(0, 0, WIDTH, HEIGHT);
	}

	public int promptPlayerCount() {
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [2,6]"));
	}

	@Override
	public void displayCard(int row, int col, Card card) {
		int offset = 25;
		int size = 50;
		
		g.setColor(Color.WHITE);
		g.fillRect(offset + (offset + size)*col , offset + (offset + size)*row, size, size);
		
		System.out.println("("+row+","+col+")\t"+card.toString());
		frame.getGraphics().drawImage(img, 0, 0, null);
	}

	@Override
	public void displayPlayer(int playerNumber, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHandler(int playerNumber, Handler handler) {
		// TODO Auto-generated method stub
		
	}

}
