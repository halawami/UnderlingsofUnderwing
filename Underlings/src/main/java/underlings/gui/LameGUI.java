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

import underlings.card.Card;
import underlings.element.Element;
import underlings.handler.Handler;
import underlings.player.Player;

public class LameGUI implements Display {
	private static final int WIDTH = 1066, HEIGHT = 950;
	private static final int OFFSET_X = 6, OFFSET_Y = 35;
	private JFrame frame;
	private Image img;
	private Graphics g;

	private Image background;

	public LameGUI() {
		this.frame = new JFrame("Lame Underlings");

		this.frame.setSize(WIDTH + OFFSET_X, HEIGHT + OFFSET_Y);
		this.frame.getContentPane().setSize(WIDTH, HEIGHT);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);

		this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		this.g = this.img.getGraphics();

		this.getImages();

		this.frame.setVisible(true);
		this.frame.getGraphics().setColor(Color.BLUE);
		this.frame.getGraphics().fillRect(0, 0, WIDTH, HEIGHT);
	}

	private void getImages() {
		try {
			this.background = ImageIO.read(new File("Graphics\\background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		this.g.setColor(Color.LIGHT_GRAY);
		this.g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
		
		this.g.setColor(Color.BLACK);
		this.g.drawString(card.name, (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(-25+ratio*(height/2 + yOffset + (height + yGap) * row)));
		this.g.drawString(card.elementSpaces[0].toString(), (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(-5+ratio*(height/2 + yOffset + (height + yGap) * row)));
		this.g.drawString(card.elementSpaces[1].toString(), (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(15+ratio*(height/2 + yOffset + (height + yGap) * row)));
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

		this.g.setColor(Color.LIGHT_GRAY);
		this.g.fillRect((int)(ratio*(xOffset + (width + xGap) * col)), (int)(ratio*(yOffset + (height + yGap) * row)), (int)(ratio*width), (int)(ratio*height));
		
		String elements = "";
		for(Element e : player.getElements()) {
			elements += e.getColor() + " ";
		}
		this.g.setColor(Color.BLACK);
		this.g.drawString(elements, (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(-25+ratio*(height/2 + yOffset + (height + yGap) * row)));
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

			this.g.setColor(Color.BLACK);
			this.g.drawString(handlers.get(i).toString(), (int)(ratio*(30 + xOffset + (width + xGap) * col)), (int)(i*25+ratio*(height/2 + yOffset + (height + yGap) * row)));
		}
	}

	@Override
	public void displayBackground() {
		this.g.drawImage(this.background, 0, 0, WIDTH, HEIGHT, null);
	}

	@Override
	public void update() {
		this.frame.getContentPane().getGraphics().drawImage(this.img, 0, 0, null);
	}

}
