package underlings.gui;

public class GUI {
	public PromptHandler promptHandler;
	public Display display;
	
	public GUI(PromptHandler promptHandler, Display display) {
		this.promptHandler = promptHandler;
		this.display = display;
	}
}