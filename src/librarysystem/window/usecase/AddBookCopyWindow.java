package librarysystem.window.usecase;

import components.backend.Administrator;
import librarysystem.LibWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookCopyWindow extends JFrame implements LibWindow {
    public static final AddBookCopyWindow INSTANCE = new AddBookCopyWindow();
    private boolean isInitialized = false;

    private Administrator administrator;

    private JTextField isbnField;
//    private JTextField numCopiesField;

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

    public void init(Administrator administrator) {
        this.administrator = administrator;
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Using GridBagLayout for formPanel to center and justify fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        isbnField = new JTextField(20);
//        numCopiesField = new JTextField(20);

        // ISBN Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("ISBN: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(isbnField, gbc);

//        // Number of Copies Label and Field
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.anchor = GridBagConstraints.EAST;
//        formPanel.add(new JLabel("Number of Copies: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
//        formPanel.add(numCopiesField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton submitButton = new JButton("Add a copy");
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
        setSize(800, 400); // Increased screen size
        Util.centerFrameOnDesktop(this);
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String isbn = isbnField.getText();

            // Add copies of the book to the system
            try {
                 administrator.addCopyOfBook(isbn);
                clearFields();
                JOptionPane.showMessageDialog(AddBookCopyWindow.this, "Copies added successfully." );
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
//        numCopiesField.setText("");
    }
}
