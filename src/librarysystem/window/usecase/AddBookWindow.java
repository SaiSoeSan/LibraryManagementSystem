package librarysystem.window.usecase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import business.Address;
import business.Author;
import business.Book;
import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

public class AddBookWindow extends JFrame implements StaffWindow {
    public static final AddBookWindow INSTANCE = new AddBookWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField authorsField;  // This will be a comma-separated list of authors
    private JTextField maxCheckoutLengthField;
    private JTextField numCopiesField;

    private AddBookWindow() {}

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
        titleField = new JTextField(10);
        authorsField = new JTextField(10);
        maxCheckoutLengthField = new JTextField(10);
        numCopiesField = new JTextField(10);

        formPanel.add(new JLabel("ISBN"));
        formPanel.add(isbnField);
        formPanel.add(new JLabel("Title"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Authors (comma-separated)"));
        formPanel.add(authorsField);
        formPanel.add(new JLabel("Max Checkout Length"));
        formPanel.add(maxCheckoutLengthField);
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
        setSize(500, 300);
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String authors = authorsField.getText();
            String maxCheckoutLength = maxCheckoutLengthField.getText();
            String numCopies = numCopiesField.getText();

            // Convert maxCheckoutLength and numCopies to integers
            int maxCheckoutLengthInt = Integer.parseInt(maxCheckoutLength);
            int numCopiesInt = Integer.parseInt(numCopies);

            try {
                // TODO
//                         // Create a new Book object and add it to the system
            Book book = new Book(isbn, title, maxCheckoutLengthInt, List.of(new Author("", "", "", new Address("", "","",""), "")));
           String response = libraryStaff.AddNewBook(book);
                clearFields();
                JOptionPane.showMessageDialog(AddBookWindow.this, "New book added successfully." + response);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(AddBookWindow.this, "Error: " + e.getMessage());
            }

            clearFields();
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AdminWindow.INSTANCE.setVisible(true);
            AddBookWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookWindow.INSTANCE);
        }
    }

    private void clearFields() {
        isbnField.setText("");
        titleField.setText("");
        authorsField.setText("");
        maxCheckoutLengthField.setText("");
        numCopiesField.setText("");
    }
}

