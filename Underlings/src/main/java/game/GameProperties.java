package game;

public enum GameProperties {
    TWO_PLAYERS, THREE_PLAYERS, FOUR_TO_SIX_PLAYERS;

    static {
        TWO_PLAYERS.haveRoundsOf(15).hatchingGroundOfWidth(3).andHeight(2).andMaxHandlers(4);
        THREE_PLAYERS.haveRoundsOf(13).hatchingGroundOfWidth(4).andHeight(3).andMaxHandlers(5);
        FOUR_TO_SIX_PLAYERS.haveRoundsOf(12).hatchingGroundOfWidth(4).andHeight(4).andMaxHandlers(6);
    }

    public int numberOfRounds;
    public int hatchingGroundWidth;
    public int hatchingGroundHeight;
    public int maxHandlers;

    private GameProperties haveRoundsOf(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
        return this;
    }

    private GameProperties hatchingGroundOfWidth(int width) {
        this.hatchingGroundWidth = width;
        return this;
    }

    private GameProperties andHeight(int height) {
        this.hatchingGroundHeight = height;
        return this;
    }

    private void andMaxHandlers(int maxHandlers) {
        this.maxHandlers = maxHandlers;
    }

    public static GameProperties getPropertiesOf(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 2:
                return TWO_PLAYERS;
            case 3:
                return THREE_PLAYERS;
            default:
                return FOUR_TO_SIX_PLAYERS;
        }
    }


}
