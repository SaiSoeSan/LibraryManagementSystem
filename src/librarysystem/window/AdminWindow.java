package librarysystem.window;

import components.backend.Administrator;
import components.backend.User;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;
import librarysystem.window.usecase.AddBookCopyWindow;
import librarysystem.window.usecase.AddBookWindow;
import librarysystem.window.usecase.AddMemberWindow;
import librarysystem.window.usecase.SearchBookWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow extends JFrame implements LibWindow {
    public static final AdminWindow INSTANCE = new AdminWindow();
    private boolean isInitialized = false;

    private Administrator administrator;

    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    private JButton addMemberButton;
    private JButton editMemberButton;
    private JButton addBookButton;
    private JButton addBookCopyButton;
    private JButton searchBookButton;

    private AdminWindow() {

    }


    @Override
    public void init() {

    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    public void init(User user) {
        administrator = new Administrator(user);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        defineTopPanel();
        defineMiddlePanel();
        defineBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(500, 400);
    }

    private void defineTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("<= Back to Main");
        backButton.addActionListener(evt -> {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        });
        topPanel.add(backButton);
    }

    private void defineMiddlePanel() {
        middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        addMemberButton = new JButton("Add New Member");
        addMemberButton.setPreferredSize(new Dimension(150, 40));
        addMemberButton.addActionListener(new AddMemberListener());

//        editMemberButton = new JButton("Edit Member Info");
//        editMemberButton.setPreferredSize(new Dimension(150, 40));
//        editMemberButton.addActionListener(new EditMemberListener());

        addBookButton = new JButton("Add New Book");
        addBookButton.setPreferredSize(new Dimension(150, 40));
        addBookButton.addActionListener(new AddBookListener());

        addBookCopyButton = new JButton("Add Book Copy");
        addBookCopyButton.setPreferredSize(new Dimension(150, 40));
        addBookCopyButton.addActionListener(new AddBookCopyListener());

        searchBookButton = new JButton("Search Book by ISBN");
        searchBookButton.setPreferredSize(new Dimension(150, 40));
        searchBookButton.addActionListener(new SearchBookListener());

        middlePanel.add(addMemberButton);
//        middlePanel.add(editMemberButton);
        middlePanel.add(addBookButton);
        middlePanel.add(addBookCopyButton);
        middlePanel.add(searchBookButton);
    }

    private void defineBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    class AddMemberListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AddMemberWindow.INSTANCE.init(administrator);
            AddMemberWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
        }
    }

//    class EditMemberListener implements ActionListener {
//        public void actionPerformed(ActionEvent evt) {
//            EditMemberWindow.INSTANCE.init(libraryService);
//            EditMemberWindow.INSTANCE.setVisible(true);
//            AdminWindow.INSTANCE.setVisible(false);
//            Util.centerFrameOnDesktop(EditMemberWindow.INSTANCE);
//        }
//    }

    class AddBookListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AddBookWindow.INSTANCE.init(administrator);
            AddBookWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookWindow.INSTANCE);

        }
    }

    class AddBookCopyListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AddBookCopyWindow.INSTANCE.init(administrator);
            AddBookCopyWindow.INSTANCE.setVisible(true);
//            AddBookCopyWindow.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);

        }
    }

    class SearchBookListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            SearchBookWindow.INSTANCE.init(administrator);
            SearchBookWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(SearchBookWindow.INSTANCE);

        }
    }
}
