package librarysystem;

import librarysystem.window.usecase.LoginWindow;

import java.awt.*;



public class Main {

	public static void main(String[] args) {
	      EventQueue.invokeLater(() -> 
	         {
	            LibrarySystem.INSTANCE.setTitle("Sample Library Application");

				 LibrarySystem.INSTANCE.init();
				 LibrarySystem.INSTANCE.setVisible(true);
				 Util.centerFrameOnDesktop(LibrarySystem.INSTANCE);
	         });
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
