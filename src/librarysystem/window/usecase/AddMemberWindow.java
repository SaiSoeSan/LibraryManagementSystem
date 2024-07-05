package librarysystem.window.usecase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import business.LibraryStaff;
import business.LibraryMember;
import business.Address;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.AdminWindow;

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

        memberIdField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        streetField = new JTextField(10);
        cityField = new JTextField(10);
        stateField = new JTextField(10);
        zipField = new JTextField(10);
        phoneField = new JTextField(10);

        formPanel.add(new JLabel("Member ID"));
        formPanel.add(memberIdField);
        formPanel.add(new JLabel("First Name"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Street"));
        formPanel.add(streetField);
        formPanel.add(new JLabel("City"));
        formPanel.add(cityField);
        formPanel.add(new JLabel("State"));
        formPanel.add(stateField);
        formPanel.add(new JLabel("Zip"));
        formPanel.add(zipField);
        formPanel.add(new JLabel("Phone"));
        formPanel.add(phoneField);

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
            String memberId = memberIdField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();
            String phone = phoneField.getText();

            Address address = new Address(street, city, state, zip);
            LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, address);


            try {
               String response = libraryStaff.addNewLibraryMember(member);
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

