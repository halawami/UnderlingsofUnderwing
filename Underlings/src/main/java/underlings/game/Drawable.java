package underlings.game;

import java.awt.Image;
import java.awt.Toolkit;

public abstract class Drawable {
	
	public Image card_front;
	public Image card_back;

	public Drawable(String imageName) {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		
		this.card_front = defaultToolkit.getImage(imageName + "_front.png");
		this.card_back = defaultToolkit.getImage(imageName + "_back.png");
	}
	
}
