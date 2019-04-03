package underlings.game;

import underlings.card.Family;
import underlings.card.Temperature;

public class Card {
    public String name;


    public String filePath;
    public int points;
    public Temperature temperature;
    public Family family;


    public String getName() {
        return this.name;
    }
    public String getFilePath() {
        return filePath;
    }

    public int getPoints() {
        return points;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Family getFamily() {
        return family;
    }
}
