package librarysystem.window.usecase;

import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberWindow extends JFrame implements StaffWindow {
    public static final AddMemberWindow INSTANCE = new AddMemberWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;

    private JTextField memberIdField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;

    private AddMemberWindow() {}

    @Override
    public void init() {}

    public boolean isInitialized() {
        return isInitialized;
    }

    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void init(LibraryStaff libraryStaff) {
        this.libraryStaff = libraryStaff;
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        memberIdField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        streetField = new JTextField(20);
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zipField = new JTextField(20);
        phoneField = new JTextField(20);

        addFormRow(formPanel, gbc, "Member ID", memberIdField);
        addFormRow(formPanel, gbc, "First Name", firstNameField);
        addFormRow(formPanel, gbc, "Last Name", lastNameField);
        addFormRow(formPanel, gbc, "Street", streetField);
        addFormRow(formPanel, gbc, "City", cityField);
        addFormRow(formPanel, gbc, "State", stateField);
        addFormRow(formPanel, gbc, "Zip", zipField);
        addFormRow(formPanel, gbc, "Phone", phoneField);

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
        setSize(800, 600);  // Increased window size
        setLocationRelativeTo(null);  // Center the window on the screen
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String label, JTextField textField) {
        gbc.gridx = 0;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(textField, gbc);
        gbc.gridy++;
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String memberId = memberIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();
            String phone = phoneField.getText();

            try {
                String response = libraryStaff.addNewLibraryMember(memberId, firstName, lastName, phone, street, city, state, zip);
                clearFields();
                JOptionPane.showMessageDialog(AddMemberWindow.this, "New Member Added Successfully " + response);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(AddMemberWindow.this, "Error: " + e.getMessage());
            }

            clearFields();
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            AdminWindow.INSTANCE.setVisible(true);
            AddMemberWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(AddMemberWindow.INSTANCE);
        }
    }

    private void clearFields() {
        memberIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        stateField.setText("");
        zipField.setText("");
        phoneField.setText("");
    }
}
