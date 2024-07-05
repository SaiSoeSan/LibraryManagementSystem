package librarysystem.window;


import business.AbstractServiceFactory;
import business.LibraryStaff;
import librarysystem.window.usecase.CheckoutBookWindow;
import librarysystem.LibWindow;
import librarysystem.LibrarySystem;
import librarysystem.window.usecase.PrintCheckoutRecordWindow;
import librarysystem.Util;
import librarysystem.window.usecase.SearchMemberWindow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LibrarianWindow extends JFrame implements LibWindow {
    public static final LibrarianWindow INSTANCE = new LibrarianWindow();
    private boolean isInitialized = false;
    private LibraryStaff libraryStaff;

    private LibrarianWindow() {}

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void init() {
        libraryStaff = AbstractServiceFactory.getLibraryService();
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Librarian Functions");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        labelPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton checkoutBookButton = new JButton("Checkout Book");
        JButton printCheckoutRecordButton = new JButton("Print Checkout Record");
        JButton searchMemberButton = new JButton("Search Member");
        JButton backButton = new JButton("Back to Main");

        checkoutBookButton.addActionListener(new CheckoutBookButtonListener());
        printCheckoutRecordButton.addActionListener(new PrintCheckoutRecordButtonListener());
        searchMemberButton.addActionListener(new SearchMemberButtonListener());
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(checkoutBookButton);
        buttonPanel.add(printCheckoutRecordButton);
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
            CheckoutBookWindow.INSTANCE.init();
            CheckoutBookWindow.INSTANCE.setVisible(true);
            LibrarianWindow.INSTANCE.setVisible(false);
        }
    }

    class PrintCheckoutRecordButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            PrintCheckoutRecordWindow.INSTANCE.init();
            PrintCheckoutRecordWindow.INSTANCE.setVisible(true);
            LibrarianWindow.INSTANCE.setVisible(false);
        }
    }


    class SearchMemberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            SearchMemberWindow.INSTANCE.init();
            SearchMemberWindow.INSTANCE.setVisible(true);
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

