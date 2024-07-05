package librarysystem.window.usecase;

import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookCopyWindow extends JFrame implements StaffWindow {
    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;


    private JTextField isbnField;
    private JTextField numCopiesField;

    private AddBookCopyWindow() {}

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
        JPanel formPanel = new JPanel(new FlowLayout());

        isbnField = new JTextField(10);
        numCopiesField = new JTextField(10);

        formPanel.add(new JLabel("ISBN"));
        formPanel.add(isbnField);
        formPanel.add(new JLabel("Number of Copies"));
        formPanel.add(numCopiesField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(new SubmitButtonListener());
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(400, 200);
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String isbn = isbnField.getText();
            String numCopies = numCopiesField.getText();

            // Convert numCopies to integer
            int numCopiesInt = Integer.parseInt(numCopies);

            // Add copies of the book to the system
            try {
                String response = libraryStaff.addCopyOfBook(isbn, numCopiesInt);
                clearFields();
                JOptionPane.showMessageDialog(AddBookCopyWindow.this, "Copies added successfully." + response);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(AddBookCopyWindow.this, "Error: " + e.getMessage());
            }
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AdminWindow.INSTANCE.setVisible(true);
            AddBookCopyWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookCopyWindow.INSTANCE);
        }
    }

    private void clearFields() {
        isbnField.setText("");
        numCopiesField.setText("");
    }
}
