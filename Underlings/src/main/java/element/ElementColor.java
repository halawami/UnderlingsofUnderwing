package element;

public enum ElementColor {
    BLUE, RED, GREEN, YELLOW, ORANGE, PURPLE, WHITE, BLACK, NULL;

    @Override
    public String toString() {
        return this.name();
    }

}
