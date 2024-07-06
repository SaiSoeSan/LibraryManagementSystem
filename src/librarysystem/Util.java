package librarysystem;

import javax.swing.*;
import java.awt.*;

public class Util {
	public static final Color DARK_BLUE = Color.BLUE.darker();
	public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
	public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19); //dark green
	public static final Color LINK_AVAILABLE = DARK_BLUE;
	public static final Color LINK_NOT_AVAILABLE = Color.gray;
	//rgb(18, 75, 14)
	
	public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), (f.getSize()-2));
    }
	
	public static void adjustLabelFont(JLabel label, Color color, boolean bigger) {
		if(bigger) {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()+2));
			label.setFont(f);
		} else {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()-2));
			label.setFont(f);
		}
		label.setForeground(color);
		
	}

	

	
	public static boolean isNumeric(String s) {
		if(s==null) return false;
		try {
			Integer.parseInt(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
}
