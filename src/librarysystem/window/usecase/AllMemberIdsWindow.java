package librarysystem.window.usecase;

import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;

import javax.swing.*;
import java.awt.*;


public class AllMemberIdsWindow extends JFrame implements LibWindow {
    public static final AllMemberIdsWindow INSTANCE = new AllMemberIdsWindow();
    private boolean isInitialized = false;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private TextArea textArea;

    private AllMemberIdsWindow() {
    }

    public void init() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        defineTopPanel();
        defineMiddlePanel();
        defineLowerPanel();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(lowerPanel, BorderLayout.SOUTH);
        getContentPane().add(mainPanel);
        isInitialized = true;
    }

    public void defineTopPanel() {
        topPanel = new JPanel();
        JLabel AllIDsLabel = new JLabel("All Member IDs");
        Util.adjustLabelFont(AllIDsLabel, Util.DARK_BLUE, true);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(AllIDsLabel);
    }

    public void defineMiddlePanel() {
        middlePanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
        middlePanel.setLayout(fl);
        textArea = new TextArea(8, 20);
        middlePanel.add(textArea);

    }

    public void defineLowerPanel() {
        lowerPanel = new JPanel();
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
        lowerPanel.setLayout(fl);
        JButton backButton = new JButton("<== Back to Main");
        addBackButtonListener(backButton);
        lowerPanel.add(backButton);
    }

    public void setData(String data) {
        textArea.setText(data);
    }

    private void addBackButtonListener(JButton butn) {
        butn.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
            Util.centerFrameOnDesktop(LibrarySystem.INSTANCE);
        });
    }

    @Override
    public boolean isInitialized() {

        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;

    }

}

