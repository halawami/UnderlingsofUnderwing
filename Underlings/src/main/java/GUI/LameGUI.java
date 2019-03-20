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
		int xOffset = 25, yOffset = 25;
		int gap = 10;
		int width = 20;
		int height = 20;

		g.setColor(Color.WHITE);
		g.fillRect(xOffset + (width + gap) * col, yOffset + (height + gap) * row, width, height);

		frame.getContentPane().getGraphics().drawImage(img, 0, 0, null);
	}

	@Override
	public void displayPlayer(int playerNumber, Player player) {

	}

	@Override
	public void displayHandler(int playerNumber, Handler handler) {

	}

	@Override
	public void displayBackground() {
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
	}

}
