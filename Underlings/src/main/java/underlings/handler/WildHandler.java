package underlings.handler;

public class WildHandler extends Handler {

    private static WildHandler instance;

    private WildHandler() {
        super(HandlerState.CARD);
    }

    public static WildHandler getInstance() {
        if (instance == null) {
            instance = new WildHandler();
        }
        return instance;
    }

}
