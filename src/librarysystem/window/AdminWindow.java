package librarysystem.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import business.AbstractServiceFactory;
import business.LibraryStaff;
import librarysystem.window.usecase.AddBookCopyWindow;
import librarysystem.window.usecase.AddBookWindow;
import librarysystem.window.usecase.AddMemberWindow;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.window.usecase.SearchBookWindow;
import librarysystem.Util;

public class AdminWindow extends JFrame implements LibWindow {
    public static final AdminWindow INSTANCE = new AdminWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;

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


    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }


    public void init() {
        libraryStaff = AbstractServiceFactory.getAdminLibraryService();
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
            AddMemberWindow.INSTANCE.init(libraryStaff);
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
            AddBookWindow.INSTANCE.init(libraryStaff);
            AddBookWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookWindow.INSTANCE);

        }
    }

    class AddBookCopyListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AddBookCopyWindow.INSTANCE.init(libraryStaff);
            AddBookCopyWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);

        }
    }

    class SearchBookListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            SearchBookWindow.INSTANCE.init(libraryStaff);
            SearchBookWindow.INSTANCE.setVisible(true);
            AdminWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(SearchBookWindow.INSTANCE);

        }
    }
}
