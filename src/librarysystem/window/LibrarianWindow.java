package librarysystem.window;


import components.backend.Librarian;
import components.backend.User;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.Util;
import librarysystem.window.usecase.CheckoutBookWindow;
import librarysystem.window.usecase.PrintCheckoutRecordWindow;
import librarysystem.window.usecase.SearchDueBookWindow;
import librarysystem.window.usecase.SearchMemberWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibrarianWindow extends JFrame implements LibWindow {
    public static final LibrarianWindow INSTANCE = new LibrarianWindow();
    private boolean isInitialized = false;
    private Librarian librarian;

    private LibrarianWindow() {

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
        librarian = new Librarian(user);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Librarian Functions");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        labelPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton checkoutBookButton = new JButton("Checkout Book");
        JButton printCheckoutRecordButton = new JButton("Print Checkout Record");
        JButton searchMemberButton = new JButton("Search Member");
        JButton searchBookButton = new JButton("Search Book By ISBN");
        JButton backButton = new JButton("Back to Main");

        checkoutBookButton.addActionListener(new CheckoutBookButtonListener());
        printCheckoutRecordButton.addActionListener(new PrintCheckoutRecordButtonListener());
        searchMemberButton.addActionListener(new SearchMemberButtonListener());
        searchBookButton.addActionListener(new SearchBookButtonListener());
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(checkoutBookButton);
        buttonPanel.add(printCheckoutRecordButton);
        buttonPanel.add(searchMemberButton);
        buttonPanel.add(searchBookButton);
        buttonPanel.add(backButton);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(400, 200);
    }

    class CheckoutBookButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            CheckoutBookWindow.INSTANCE.init(librarian);
            CheckoutBookWindow.INSTANCE.setVisible(true);
//            CheckoutBookWindow.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            LibrarianWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(CheckoutBookWindow.INSTANCE);
        }
    }



    class PrintCheckoutRecordButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PrintCheckoutRecordWindow.INSTANCE.init(librarian);
            PrintCheckoutRecordWindow.INSTANCE.setVisible(true);
            LibrarianWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(PrintCheckoutRecordWindow.INSTANCE);
        }
    }


    class SearchMemberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            SearchMemberWindow.INSTANCE.init(librarian);
            SearchMemberWindow.INSTANCE.setVisible(true);
            LibrarianWindow.INSTANCE.setVisible(false);
        }
    }

    class SearchBookButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            SearchDueBookWindow.INSTANCE.init(librarian);
            SearchDueBookWindow.INSTANCE.setVisible(true);
            LibrarianWindow.INSTANCE.setVisible(false);
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            LibrarySystem.hideAllWindows();
            LibrarySystem.INSTANCE.setVisible(true);
        }
    }
}

