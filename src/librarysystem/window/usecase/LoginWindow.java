package librarysystem.window.usecase;

import components.backend.User;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;
import librarysystem.window.AdminWindow;
import librarysystem.window.LibrarianWindow;

import javax.swing.*;
import java.awt.*;


public class LoginWindow extends JFrame implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();
	private boolean isInitialized = false;

	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;

	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;

	public boolean isInitialized() {
		return isInitialized;
	}

	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	private LoginWindow() {
	}

	public void init() {
		mainPanel = new JPanel();
		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);

		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);

		isInitialized(true);
		pack();
		setSize(400, 300); // Set initial size before centering

		// Center the window on the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
		int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
		setLocation(centerX, centerY);
	}

	private void defineUpperHalf() {
		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.add(topPanel, BorderLayout.NORTH);
		upperHalf.add(middlePanel, BorderLayout.CENTER);
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.HORIZONTAL);
		middleHalf.add(s, BorderLayout.SOUTH);
	}

	private void defineLowerHalf() {
		lowerHalf = new JPanel();
		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton backButton = new JButton("<= Back to Main");
		addBackButtonListener(backButton);
		lowerHalf.add(backButton);
	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		JPanel intPanel = new JPanel(new BorderLayout());
		intPanel.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.NORTH);
		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(intPanel);
	}

	private void defineMiddlePanel() {
		middlePanel = new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}

	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		loginButton = new JButton("Login");
		addLoginButtonListener(loginButton);
		lowerPanel.add(loginButton);
	}

	private void defineLeftTextPanel() {
		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		username = new JTextField(10);
		JLabel label = new JLabel("Username");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(username);
		bottomText.add(label);

		leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BorderLayout());
		leftTextPanel.add(topText, BorderLayout.NORTH);
		leftTextPanel.add(bottomText, BorderLayout.CENTER);
	}

	private void defineRightTextPanel() {
		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

		password = new JPasswordField(10);
		JLabel label = new JLabel("Password");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(password);
		bottomText.add(label);

		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BorderLayout());
		rightTextPanel.add(topText, BorderLayout.NORTH);
		rightTextPanel.add(bottomText, BorderLayout.CENTER);
	}

	private void addBackButtonListener(JButton button) {
		button.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

	private void addLoginButtonListener(JButton button) {
		button.addActionListener(evt -> {
			String userId = username.getText();
			String pass = new String(password.getPassword()); // Use getPassword() and convert to string

			System.out.println("About to login");
            User loggedInUser = User.login(userId, pass);


			if (loggedInUser != null) {
				System.out.println("Login successful!");

				if (loggedInUser.getRole("Librarian") != null && loggedInUser.getRole("Administrator") != null ) {
					String[] options = {"Admin", "Librarian"};
					int choice = JOptionPane.showOptionDialog(
							this,
							"Welcome " + loggedInUser.getFirstName() + ". Choose role to view.",
							"Role Selection",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE,
							null,
							options,
							options[0]
					);
					switch (choice) {
						case 0 -> showAdminWindow(loggedInUser);
						case 1 -> showLibrarianWindow(loggedInUser);
					}
					return;
				}
				if (loggedInUser.getRole("Librarian") != null) {
					System.out.println("User is a Librarian");
					showLibrarianWindow(loggedInUser);
				}
				if (loggedInUser.getRole("Administrator") != null) {
					System.out.println("User is a Administrator");
					showAdminWindow(loggedInUser);

				}
				else {
					System.out.println("Login failed. Invalid Role");
				}
			}

			else {
				System.out.println("Login failed. Invalid credential");
			}
		});
	}

	private void showAdminWindow(User loggedInUser) {
		LibrarySystem.hideAllWindows();
		AdminWindow.INSTANCE.init(loggedInUser);
		Util.centerFrameOnDesktop(AdminWindow.INSTANCE);
		AdminWindow.INSTANCE.setVisible(true);
	}

	private void showLibrarianWindow(User loggedInUser) {
		LibrarySystem.hideAllWindows();
		LibrarianWindow.INSTANCE.init(loggedInUser);
		Util.centerFrameOnDesktop(LibrarianWindow.INSTANCE);
		LibrarianWindow.INSTANCE.setVisible(true);
	}

	private void clearFields() {
		username.setText("");
		password.setText("");
	}
}
