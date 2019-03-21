package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Game.Card;
import Game.Handler;
import Player.Player;

public class LameGUI implements GUI {
	private final int WIDTH = 1066, HEIGHT = 950;
	private JFrame frame;
	private Image img;
	private Graphics g;

	private Image background;

	public LameGUI() {
		frame = new JFrame("Lame Underlings");

		frame.setSize(WIDTH + 6, HEIGHT + 35);
		frame.getContentPane().setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = img.getGraphics();

		getImages();

		frame.setVisible(true);
		frame.getGraphics().setColor(Color.BLUE);
		frame.getGraphics().fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void getImages() {
		try {
			background = ImageIO.read(new File("Graphics\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int promptPlayerCount() {
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [2,6]"));
	}

	@Override
	public void displayCard(int row, int col, Card card) {
		// 1313 1228
		// 1823 1588
		// 1896 1648
		int xOffset = 1313, yOffset = 1228;
		int xGap = 73, yGap = 60;
		int width = 510, height = 360;
		double ratio = 1066/4099.0;

		g.setColor(new Color((int)(Math.random()*255*255*255)));
		g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
	}

	@Override
	public void displayPlayer(int playerNumber, Player player) {
		// 8 7
		// 1363 730
		// 1372 2921
		
		int xOffset = 8, yOffset = 7;
		int xGap = 9, yGap = 2191;
		int width = 1355, height = 723;
		double ratio = 1066/4099.0;
		
		int row = playerNumber/3;
		int col = playerNumber%3;

		g.setColor(new Color((int)(Math.random()*255*255*255)));
		g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
	}

	@Override
	public void displayHandler(int playerNumber, Handler handler) {
		int xOffset = 8, yOffset = 7;
		int xGap = 9, yGap = 2191;
		int width = 1355, height = 723;
		double ratio = 1066/4099.0;
		
		int row = playerNumber/3;
		int col = playerNumber%3;

		g.setColor(new Color((int)(Math.random()*255*255*255)));
		g.drawString(handler.getState().toString(), (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(Math.random()*50+ratio*(height/2 + yOffset + (height + yGap) * row)));
		
	}

	@Override
	public void displayBackground() {
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
	}

	@Override
	public void update() {
		frame.getContentPane().getGraphics().drawImage(img, 0, 0, null);
	}

}
