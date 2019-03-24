package underlings.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import underlings.game.Card;
import underlings.game.Handler;
import underlings.player.Player;

public class LameGUI implements GUI {
	private final int WIDTH = 1066, HEIGHT = 950;
	private JFrame frame;
	private Image img;
	private Graphics g;

	private Image background;

	public LameGUI() {
		this.frame = new JFrame("Lame Underlings");

		this.frame.setSize(this.WIDTH + 6, this.HEIGHT + 35);
		this.frame.getContentPane().setSize(this.WIDTH, this.HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);

		this.img = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		this.g = this.img.getGraphics();

		this.getImages();

		this.frame.setVisible(true);
		this.frame.getGraphics().setColor(Color.BLUE);
		this.frame.getGraphics().fillRect(0, 0, this.WIDTH, this.HEIGHT);
	}

	private void getImages() {
		try {
			this.background = ImageIO.read(new File("Graphics\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int promptPlayerCount() {
		return Integer.parseInt(JOptionPane.showInputDialog("How many players? [2,6]"));
	}

	/**
	 * 
	 * prompts for each phase
	 * 
	 */

	@Override
	public void displayCard(int row, int col, Card card) {
		// 1313 1228
		// 1823 1588
		// 1896 1648
		int xOffset = 1313, yOffset = 1228;
		int xGap = 73, yGap = 60;
		int width = 510, height = 360;
		double ratio = 1066/4099.0;

		this.g.setColor(new Color((int)(Math.random()*255*255*255)));
		this.g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
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

		this.g.setColor(new Color((int)(Math.random()*255*255*255)));
		this.g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
	}

	@Override
	public void displayHandlers(int playerNumber, List<Handler> handlers) {
		int xOffset = 8, yOffset = 7;
		int xGap = 9, yGap = 2191;
		int width = 1355, height = 723;
		double ratio = 1066/4099.0;

		for(int i = 0; i < handlers.size(); i++) {
			int row = playerNumber/3;
			int col = playerNumber%3;

			this.g.setColor(new Color((int)(Math.random()*255*255*255)));
			this.g.drawString(handlers.get(i).getState().toString(), (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(i*25+ratio*(height/2 + yOffset + (height + yGap) * row)));
		}
	}

	@Override
	public void displayBackground() {
		this.g.drawImage(this.background, 0, 0, this.WIDTH, this.HEIGHT, null);
	}

	@Override
	public void update() {
		this.frame.getContentPane().getGraphics().drawImage(this.img, 0, 0, null);
	}

}
