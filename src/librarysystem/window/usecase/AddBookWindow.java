package librarysystem.window.usecase;

import components.backend.Address;
import components.backend.Administrator;
import components.backend.Author;
import librarysystem.LibWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow extends JFrame implements LibWindow {
    public static final AddBookWindow INSTANCE = new AddBookWindow();
    private boolean isInitialized = false;

    private Administrator administrator;

    private JTextField isbnField;
    private JTextField titleField;
    private JTextField maxCheckoutLengthField;
    private JTextField numCopiesField;

    private JPanel authorPanel;
    private List<AuthorFieldSet> authorFieldSets;

    private AddBookWindow() {}

    @Override
    public void init() {}

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void init(Administrator administrator) {
        this.administrator = administrator;
        authorFieldSets = new ArrayList<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        isbnField = new JTextField(10);
        titleField = new JTextField(10);
        maxCheckoutLengthField = new JTextField(10);
        numCopiesField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ISBN"), gbc);
        gbc.gridx = 1;
        formPanel.add(isbnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Title"), gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Max Checkout Length"), gbc);
        gbc.gridx = 1;
        formPanel.add(maxCheckoutLengthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Number of Copies"), gbc);
        gbc.gridx = 1;
        formPanel.add(numCopiesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton addAuthorButton = new JButton("Add Author");
        addAuthorButton.addActionListener(new AddAuthorButtonListener());
        formPanel.add(addAuthorButton, gbc);

        authorPanel = new JPanel();
        authorPanel.setLayout(new BoxLayout(authorPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(authorPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        submitButton.addActionListener(new SubmitButtonListener());
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        isInitialized(true);
        setSize(600, 400);
        setLocationRelativeTo(null);  // Center the window
    }

    private class AddAuthorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AuthorFieldSet authorFieldSet = new AuthorFieldSet(authorFieldSets.size() + 1);
            authorFieldSets.add(authorFieldSet);
            authorPanel.add(authorFieldSet.getPanel());
            authorPanel.revalidate();
            authorPanel.repaint();
        }
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String isbn = isbnField.getText();
            String title = titleField.getText();
            String maxCheckoutLength = maxCheckoutLengthField.getText();
            String numCopies = numCopiesField.getText();

            int maxCheckoutLengthInt = 5;
            int numCopiesInt = 1;
            try{
                 maxCheckoutLengthInt = Integer.parseInt(maxCheckoutLength);
                 numCopiesInt = Integer.parseInt(numCopies);
            } catch (NumberFormatException e) {
            }


            List<Author> authors = new ArrayList<>();
            for (AuthorFieldSet authorFieldSet : authorFieldSets) {
                String firstName = authorFieldSet.firstNameField.getText();
                String lastName = authorFieldSet.lastNameField.getText();
                String street = authorFieldSet.streetField.getText();
                String city = authorFieldSet.cityField.getText();
                String state = authorFieldSet.stateField.getText();
                String zip = authorFieldSet.zipField.getText();
                String phone = authorFieldSet.phoneField.getText();
                String credentials = authorFieldSet.credentialsField.getText();
                String bio = authorFieldSet.bioField.getText();

                Address address = new Address(street, city, state, zip);

                Author author = new Author(firstName, lastName, phone, address, bio, credentials);
                authors.add(author);
            }

            try {
                administrator.addNewBook(isbn, title, authors, maxCheckoutLengthInt, numCopiesInt);
                clearFields();
                JOptionPane.showMessageDialog(AddBookWindow.this, "New book added successfully. " );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(AddBookWindow.this, "Error: " + e.getMessage());
            }
        }
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AdminWindow.INSTANCE.setVisible(true);
            AddBookWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddBookWindow.INSTANCE);
        }
    }

    private void clearFields() {
        isbnField.setText("");
        titleField.setText("");
        maxCheckoutLengthField.setText("");
        numCopiesField.setText("");
        authorFieldSets.clear();
        authorPanel.removeAll();
        authorPanel.revalidate();
        authorPanel.repaint();
    }

    private static class AuthorFieldSet {
        private JTextField firstNameField;
        private JTextField lastNameField;
        private JTextField streetField;
        private JTextField cityField;
        private JTextField stateField;
        private JTextField zipField;
        private JTextField phoneField;
        private JTextField credentialsField;
        private JTextField bioField;

        private JPanel panel;

        public AuthorFieldSet(int authorNumber) {
            panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            panel.setBorder(BorderFactory.createTitledBorder("Author " + authorNumber));

            firstNameField = new JTextField(10);
            lastNameField = new JTextField(10);
            streetField = new JTextField(10);
            cityField = new JTextField(10);
            stateField = new JTextField(10);
            zipField = new JTextField(10);
            phoneField = new JTextField(10);
            credentialsField = new JTextField(10);
            bioField = new JTextField(10);

            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("First Name"), gbc);
            gbc.gridx = 1;
            panel.add(firstNameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Last Name"), gbc);
            gbc.gridx = 1;
            panel.add(lastNameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Street"), gbc);
            gbc.gridx = 1;
            panel.add(streetField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("City"), gbc);
            gbc.gridx = 1;
            panel.add(cityField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("State"), gbc);
            gbc.gridx = 1;
            panel.add(stateField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(new JLabel("Zip"), gbc);
            gbc.gridx = 1;
            panel.add(zipField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            panel.add(new JLabel("Phone"), gbc);
            gbc.gridx = 1;
            panel.add(phoneField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            panel.add(new JLabel("Credentials"), gbc);
            gbc.gridx = 1;
            panel.add(credentialsField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            panel.add(new JLabel("Bio"), gbc);
            gbc.gridx = 1;
            panel.add(bioField, gbc);
        }

        public JPanel getPanel() {
            return panel;
        }
    }
}
