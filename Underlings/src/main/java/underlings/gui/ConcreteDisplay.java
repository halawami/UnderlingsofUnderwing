package underlings.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.handler.Handler;
import underlings.handler.WildHandler;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ConcreteDisplay implements Display {

    private static final int WIDTH = 1266;
    private static final int HEIGHT = 950;
    private static final int OFFSET_X = 6;
    private static final int OFFSET_Y = 35;
    private JFrame frame;
    private Image img;
    private Graphics gr;

    private Image background;

    private Map<ElementColor, Color> colorMap;

    public ConcreteDisplay() {
        this.colorMap = new HashMap<ElementColor, Color>();
        this.colorMap.put(ElementColor.WHITE, Color.WHITE);
        this.colorMap.put(ElementColor.BLACK, Color.BLACK);
        this.colorMap.put(ElementColor.RED, Color.RED);
        this.colorMap.put(ElementColor.BLUE, Color.BLUE);
        this.colorMap.put(ElementColor.YELLOW, Color.YELLOW);
        this.colorMap.put(ElementColor.PURPLE, new Color(255, 0, 255));
        this.colorMap.put(ElementColor.ORANGE, Color.ORANGE);
        this.colorMap.put(ElementColor.GREEN, Color.GREEN);

        this.frame = new JFrame(LocaleWrap.get("game_title"));

        this.frame.setSize(WIDTH + OFFSET_X, HEIGHT + OFFSET_Y);
        this.frame.getContentPane().setSize(WIDTH, HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);

        this.img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.gr = this.img.getGraphics();

        this.getImages();

        this.frame.setVisible(true);
        this.frame.getGraphics().setColor(Color.BLUE);
        this.frame.getGraphics().fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void getImages() {
        try {
            this.background = ImageIO.read(new File(LocaleWrap.get("background")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayCard(int row, int col, Card card) {
        if (card.handler == WildHandler.getInstance()) {
            this.displayDragon(row, col, card);
        } else {
            this.displayEgg(row, col, card);
        }
    }

    private void displayEgg(int row, int col, Card card) {
        // 1313 1228
        // 1823 1588
        // 1896 1648
        int offsetX = 1313;
        int offsetY = 1228;
        int gapX = 73;
        int gapY = 60;
        int width = 510;
        int height = 360;
        double ratio = 1066 / 4099.0;

        this.gr.setColor(Color.LIGHT_GRAY);
        this.gr.fillRect((int) (ratio * (offsetX + (width + gapX) * col)),
                (int) (ratio * (offsetY + (height + gapY) * row)), (int) (ratio * width), (int) (ratio * height));

        this.gr.setColor(Color.BLACK);
        this.gr.drawString(card.name, (int) (ratio * (30 + offsetX + (width + gapX) * col)),
                (int) (-25 + ratio * (height / 2 + offsetY + (height + gapY) * row)));
        this.gr.drawString(card.points + "", (int) (ratio * (30 + offsetX + (width + gapX) * col)) + 118,
                (int) (-25 + ratio * (height / 2 + offsetY + (height + gapY) * row)));

        int spaceNum = 0;
        for (ElementSpace space : card.elementSpaces) {
            int x = (int) ((ratio * (40 + offsetX + (width + gapX) * col)) + 255 * ratio * (spaceNum % 2));
            int y = (int) (spaceNum / 2 * 15 - 5 + ratio * (height / 2 + offsetY + (height + gapY) * row));

            this.gr.setColor(this.colorMap.get(space.color));
            this.gr.fillRect(x - 5, y - 5, 5, 5);

            this.gr.setColor(Color.BLACK);
            String s = "";
            if (space.elements.isEmpty()) {
                s = LocaleWrap.get("empty");
            }
            for (int i = 0; i < space.elements.size(); i++) {
                String elementName = space.getElementColors().get(i).name();
                s += "" + elementName.charAt(0) + elementName.charAt(elementName.length() - 1) + " ";
            }

            this.gr.drawString(s, x, y);

            spaceNum++;
        }
    }

    private void displayDragon(int row, int col, Card card) {
        // 1313 1228
        // 1823 1588
        // 1896 1648
        int offsetX = 1313;
        int offsetY = 1228;
        int gapX = 73;
        int gapY = 60;
        int width = 510;
        int height = 360;
        double ratio = 1066 / 4099.0;

        this.gr.setColor(Color.LIGHT_GRAY);
        this.gr.fillRect((int) (ratio * (offsetX + (width + gapX) * col)),
                (int) (ratio * (offsetY + (height + gapY) * row)), (int) (ratio * width), (int) (ratio * height));

        this.gr.setColor(Color.BLACK);
        this.gr.drawString(card.name, (int) (ratio * (30 + offsetX + (width + gapX) * col)),
                (int) (-25 + ratio * (height / 2 + offsetY + (height + gapY) * row)));

        this.gr.drawString(LocaleWrap.get("hatched_wild"), (int) (ratio * (30 + offsetX + (width + gapX) * col)),
                (int) (-5 + ratio * (height / 2 + offsetY + (height + gapY) * row)));
    }

    @Override
    public void displayPlayer(int playerNumber, Player player) {
        // 8 7
        // 1363 730
        // 1372 2921
        int offsetX = 8;
        int offsetY = 7;
        int gapX = 9;
        int gapY = 2191;
        int width = 1355;
        int height = 723;
        double ratio = 1066 / 4099.0;

        int row = playerNumber / 3;
        int col = playerNumber % 3;

        this.gr.setColor(Color.LIGHT_GRAY);
        this.gr.fillRect((int) (ratio * (offsetX + (width + gapX) * col)),
                (int) (ratio * (offsetY + (height + gapY) * row)), (int) (ratio * width), (int) (ratio * height));

        String elements = "";
        for (Element e : player.getElements()) {
            elements += e.getColor() + " ";
        }
        this.gr.setColor(Color.BLACK);
        this.gr.drawString(MessageFormat.format(LocaleWrap.get("player_number"), (playerNumber + 1)),
                (int) (ratio * (offsetX + (width + gapX) * col)) + 5,
                (int) (ratio * (offsetY + (height + gapY) * row)) + 15);
        this.gr.drawString(elements, (int) (ratio * (30 + offsetX + (width + gapX) * col)),
                (int) (-25 + ratio * (height / 2 + offsetY + (height + gapY) * row)));
    }

    @Override
    public void displayHandlers(int playerNumber, List<Handler> handlers) {
        int offsetX = 8;
        int offsetY = 7;
        int gapX = 9;
        int gapY = 2191;
        int width = 1355;
        int height = 723;
        double ratio = 1066 / 4099.0;

        for (int i = 0; i < handlers.size(); i++) {
            int row = playerNumber / 3;
            int col = playerNumber % 3;

            this.gr.setColor(Color.BLACK);
            this.gr.drawString(handlers.get(i).toString(), (int) (ratio * (30 + offsetX + (width + gapX) * col)),
                    (int) (i * 25 + ratio * (height / 2 + offsetY + (height + gapY) * row)));
        }
    }

    @Override
    public void displayBackground() {
        this.gr.drawImage(this.background, 0, 0, WIDTH - 200, HEIGHT, null);
    }

    @Override
    public void update() {
        this.frame.getContentPane().getGraphics().drawImage(this.img, 0, 0, null);
    }

    @Override
    public void displayStats(ElementBag elementBag, int roundsLeft, int currentPhase, int leadTurn) {

        this.gr.setColor(Color.LIGHT_GRAY);
        this.gr.fillRect(WIDTH - 200, 0, 200, HEIGHT);

        this.gr.setColor(Color.BLACK);

        StringBuilder stats = new StringBuilder();
        stats.append("Elements Remaining\n");
        stats.append("Red: " + elementBag.getNumberRemaining(ElementColor.RED) + "\n");
        stats.append("Blue: " + elementBag.getNumberRemaining(ElementColor.BLUE) + "\n");
        stats.append("Yellow: " + elementBag.getNumberRemaining(ElementColor.YELLOW) + "\n");
        stats.append("Green: " + elementBag.getNumberRemaining(ElementColor.GREEN) + "\n");
        stats.append("Orange: " + elementBag.getNumberRemaining(ElementColor.ORANGE) + "\n");
        stats.append("Purple: " + elementBag.getNumberRemaining(ElementColor.PURPLE) + "\n");
        stats.append("Black: " + elementBag.getNumberRemaining(ElementColor.BLACK) + "\n");
        stats.append("White: " + elementBag.getNumberRemaining(ElementColor.WHITE) + "\n\n");
        stats.append("Rounds Left: " + roundsLeft + "\n");
        stats.append("Phase: " + currentPhase + "\n");
        stats.append("Turn Leader: " + leadTurn + "\n");

        int y = 15;

        for (String item : stats.toString().split("\n")) {
            this.gr.drawString(item, WIDTH - 200, y);
            y += 15;
        }

    }

}
