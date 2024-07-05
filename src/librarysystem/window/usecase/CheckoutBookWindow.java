package librarysystem.window.usecase;

import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.LibrarianWindow;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class CheckoutBookWindow extends JFrame implements StaffWindow {
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;

    private CheckoutBookWindow() {
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

    public void init(LibraryStaff libraryStaff) {
        this.libraryStaff = libraryStaff;
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Checkout Book");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        labelPanel.add(label);

        JPanel formPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField(10);
        inputPanel.add(memberIdLabel);
        inputPanel.add(memberIdField);

        JLabel isbnLabel = new JLabel("ISBN:");
        JTextField isbnField = new JTextField(10);
        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");

        checkoutButton.addActionListener(new CheckoutButtonListener(memberIdField, isbnField));
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);

        formPanel.add(inputPanel, BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(400, 200);
    }

    class CheckoutButtonListener implements ActionListener {
        private JTextField memberIdField;
        private JTextField isbnField;

        CheckoutButtonListener(JTextField memberIdField, JTextField isbnField) {
            this.memberIdField = memberIdField;
            this.isbnField = isbnField;
        }

        public void actionPerformed(ActionEvent evt) {
            String memberId = memberIdField.getText();
            String isbn = isbnField.getText();

            // Perform checkout logic here
//            boolean success = LibrarySystem.INSTANCE.checkoutBook(memberId, isbn);
            String response = libraryStaff.checkOutBook(memberId, isbn);
            boolean success = true;
            if (success) {
                JOptionPane.showMessageDialog(CheckoutBookWindow.this, "Book checked out successfully. " + response);
                memberIdField.setText("");
                isbnField.setText("");
            } else {
                JOptionPane.showMessageDialog(CheckoutBookWindow.this, "Failed to check out book.");
            }
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            LibrarianWindow.INSTANCE.setVisible(true);
            CheckoutBookWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(LibrarianWindow.INSTANCE);
        }
    }
}

