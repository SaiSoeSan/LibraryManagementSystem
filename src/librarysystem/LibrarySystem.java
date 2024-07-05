package librarysystem;

import librarysystem.window.usecase.AllMemberIdsWindow;
import librarysystem.window.usecase.LoginWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;


public class LibrarySystem extends JFrame implements LibWindow {
    public final static LibrarySystem INSTANCE = new LibrarySystem();
    JPanel mainPanel;
    JMenuBar menuBar;
    JMenu options;
    JMenuItem login, allBookIds, allMemberIds;
    String pathToImage;
    JLayeredPane layeredPane = new JLayeredPane();
    private boolean isInitialized = false;

    private static LibWindow[] allWindows = {
            LibrarySystem.INSTANCE,
            LoginWindow.INSTANCE,
            AllMemberIdsWindow.INSTANCE,
//            AllBookIdsWindow.INSTANCE // TODO add other instances
    };

    public static void hideAllWindows() {
        for (LibWindow frame : allWindows) {
            frame.setVisible(false);
        }
    }

    private LibrarySystem() {
    }

    public void init() {
        formatContentPane();
        setPathToImage();
        insertSplashImage();
        createMenus();
        //pack();
        setSize(660, 500);
        isInitialized = true;
    }

    private void formatContentPane() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 1));
        getContentPane().add(mainPanel);
    }

    private void setPathToImage() {
        String currDirectory = System.getProperty("user.dir");

        pathToImage = currDirectory + File.separator + "librarysystem" + File.separator + "library.jpg";
    }

    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);

        // Create a layered pane
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Create the background label and add it to the layered pane
        JLabel backgroundLabel = new JLabel(image);
        backgroundLabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

    }

    private void createMenus() {

        // Create the login panel and add it to the layered pane
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setOpaque(false);
        loginPanel.setBounds(250, 200, 300, 200); // adjust the size and position as needed

        layeredPane.add(loginPanel, JLayeredPane.PALETTE_LAYER);

        // Add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, 800, 600); // adjust the size and position as needed

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton loginMenuButton = new JButton("Login");
        JButton allBookIdsButton = new JButton("All Book IDs");
        JButton allMemberIdsButton = new JButton("All Member IDs");

        buttonPanel.add(loginMenuButton, gbc);
        gbc.gridy++;
        buttonPanel.add(allBookIdsButton, gbc);
        gbc.gridy++;
        buttonPanel.add(allMemberIdsButton, gbc);

        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        mainPanel.add(layeredPane, BorderLayout.CENTER);
        add(mainPanel);

        loginMenuButton.addActionListener(new LoginListener());
//        allBookIdsButton.addActionListener(new AllBookIdsListener());
//        allMemberIdsButton.addActionListener(new AllMemberIdsListener());
    }


//    private void insertSplashImage() {
//        ImageIcon image = new ImageIcon(pathToImage);
//		mainPanel.add(new JLabel(image));
//    }
//    private void createMenus() {
//    	menuBar = new JMenuBar();
//		menuBar.setBorder(BorderFactory.createRaisedBevelBorder());
//		login = new JMenuItem("Login");
//		login.addActionListener(new LoginListener());
//		addMenuItems();
//		setJMenuBar(menuBar);
//    }
//
//    private void addMenuItems() {
//       options = new JMenu("Options");
// 	   menuBar.add(options);
// 	   login = new JMenuItem("Login");
// 	   login.addActionListener(new LoginListener());
// 	   allBookIds = new JMenuItem("All Book Ids");
// 	   allBookIds.addActionListener(new AllBookIdsListener());
// 	   allMemberIds = new JMenuItem("All Member Ids");
// 	   allMemberIds.addActionListener(new AllMemberIdsListener());
// 	   options.add(login);
// 	   options.add(allBookIds);
// 	   options.add(allMemberIds);
//    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();
            LoginWindow.INSTANCE.init();
            Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
            LoginWindow.INSTANCE.setVisible(true);

        }

    }

//    class AllBookIdsListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            LibrarySystem.hideAllWindows();
//            AllBookIdsWindow.INSTANCE.init();
//
//            List<String> ids = ci.allBookIds();
//            Collections.sort(ids);
//            StringBuilder sb = new StringBuilder();
//            for (String s : ids) {
//                sb.append(s + "\n");
//            }
//            System.out.println(sb.toString());
//            AllBookIdsWindow.INSTANCE.setData(sb.toString());
//            AllBookIdsWindow.INSTANCE.pack();
//            //AllBookIdsWindow.INSTANCE.setSize(660,500);
//            Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
//            AllBookIdsWindow.INSTANCE.setVisible(true);
//
//        }
//
//    }

//    class AllMemberIdsListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            LibrarySystem.hideAllWindows();
//            AllMemberIdsWindow.INSTANCE.init();
//            AllMemberIdsWindow.INSTANCE.pack();
//            AllMemberIdsWindow.INSTANCE.setVisible(true);
//
//
//            LibrarySystem.hideAllWindows();
//            AllBookIdsWindow.INSTANCE.init();
//
//            List<String> ids = ci.allMemberIds();
//            Collections.sort(ids);
//            StringBuilder sb = new StringBuilder();
//            for (String s : ids) {
//                sb.append(s + "\n");
//            }
//            System.out.println(sb.toString());
//            AllMemberIdsWindow.INSTANCE.setData(sb.toString());
//            AllMemberIdsWindow.INSTANCE.pack();
//            //AllMemberIdsWindow.INSTANCE.setSize(660,500);
//            Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
//            AllMemberIdsWindow.INSTANCE.setVisible(true);
//
//
//        }
//
//    }




    @Override
    public boolean isInitialized() {
        return isInitialized;
    }


    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;

    }

}
