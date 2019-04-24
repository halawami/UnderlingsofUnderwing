package underlings.handler;

public class HandlerFactory {
	
	public Handler createHandler(){
		return createHandler(HandlerState.READY_ROOM);
	}
	
	public Handler createHandler(HandlerState state){
		return new Handler(state);
	}
}
