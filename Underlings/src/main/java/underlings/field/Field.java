package underlings.field;

import java.util.List;

import underlings.gui.DrawChoice;
import underlings.handler.Handler;

public class Field {

	private FieldSpace white;
	
	private List<FieldSpace> field;

	public Field(FieldSpaceFactory fieldSpaceFactory) {
		this.white = fieldSpaceFactory.createFieldSpace(DrawChoice.WHITE);
		this.field = fieldSpaceFactory.createFieldArray();
	}

	public void addHandler(int fieldPosition, Handler handler) {
		this.field.get(fieldPosition).addHandler(handler);
		
	}

	public void addHandlerWhitespace(Handler handler) {
		this.white.addHandler(handler);
	}
	
	
	
}

