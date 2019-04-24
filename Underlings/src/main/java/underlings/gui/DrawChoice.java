package underlings.gui;

public enum DrawChoice implements Choice {
    BLUE, RED, GREEN, PURPLE, ORANGE, YELLOW, BLACK, WHITE, RANDOM, COOL, WARM;


    @Override
    public String toString(){
        return this.name() + " Element";
    }
}
