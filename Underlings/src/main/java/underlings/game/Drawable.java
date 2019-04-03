package underlings.game;

import java.awt.Image;
import java.awt.Toolkit;

public abstract class Drawable {
	
	public Image getImage(String imageName) {
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		
		return defaultToolkit.getImage(getImageFile());
	}
	
	protected abstract String getImageFile();
	
}
