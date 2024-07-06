package librarysystem.window.usecase;//package librarysystem.window.usecase;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//
//import business.Address;
//import business.LibraryService;
//import business.LibraryMember;
//import librarysystem.LibWindow2;
//import librarysystem.window.AdminWindow;
//
//public class EditMemberWindow extends JFrame implements LibWindow2 {
//    public static final EditMemberWindow INSTANCE = new EditMemberWindow();
//    private boolean isInitialized = false;
//
//    private LibraryService libraryService;
//
//    private JTextField memberIdField;
//    private JTextField firstNameField;
//    private JTextField lastNameField;
//    private JTextField streetField;
//    private JTextField cityField;
//    private JTextField stateField;
//    private JTextField zipField;
//    private JTextField phoneField;
//
//    private EditMemberWindow() {}
//
//    public boolean isInitialized() {
//        return isInitialized;
//    }
//
//    public void isInitialized(boolean val) {
//        isInitialized = val;
//    }
//
//    public void init(LibraryService libraryService) {
//        this.libraryService = libraryService;
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        JPanel formPanel = new JPanel(new FlowLayout());
//
//        memberIdField = new JTextField(10);
//        firstNameField = new JTextField(10);
//        lastNameField = new JTextField(10);
//        streetField = new JTextField(10);
//        cityField = new JTextField(10);
//        stateField = new JTextField(10);
//        zipField = new JTextField(10);
//        phoneField = new JTextField(10);
//
//        formPanel.add(new JLabel("Member ID"));
//        formPanel.add(memberIdField);
//        formPanel.add(new JLabel("First Name"));
//        formPanel.add(firstNameField);
//        formPanel.add(new JLabel("Last Name"));
//        formPanel.add(lastNameField);
//        formPanel.add(new JLabel("Street"));
//        formPanel.add(streetField);
//        formPanel.add(new JLabel("City"));
//        formPanel.add(cityField);
//        formPanel.add(new JLabel("State"));
//        formPanel.add(stateField);
//        formPanel.add(new JLabel("Zip"));
//        formPanel.add(zipField);
//        formPanel.add(new JLabel("Phone"));
//        formPanel.add(phoneField);
//
//        JPanel buttonPanel = new JPanel(new FlowLayout());
//        JButton submitButton = new JButton("Submit");
//        JButton backButton = new JButton("Back");
//
//        submitButton.addActionListener(new SubmitButtonListener());
//
//        backButton.addActionListener(evt -> {
//            AdminWindow.INSTANCE.setVisible(true);
//            EditMemberWindow.INSTANCE.setVisible(false);
//        });
//
//        buttonPanel.add(submitButton);
//        buttonPanel.add(backButton);
//
//        mainPanel.add(formPanel, BorderLayout.CENTER);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        getContentPane().add(mainPanel);
//        isInitialized(true);
//        pack();
//        setSize(500, 300);
//    }
//
//
//
//
//    class SubmitButtonListener implements ActionListener {
//        public void actionPerformed(ActionEvent evt) {
//            String memberId = memberIdField.getText();
//            String firstName = firstNameField.getText();
//            String lastName = lastNameField.getText();
//            String street = streetField.getText();
//            String city = cityField.getText();
//            String state = stateField.getText();
//            String zip = zipField.getText();
//            String phone = phoneField.getText();
//
//            Address address = new Address(street, city, state, zip);
//            LibraryMember member = new LibraryMember(memberId, firstName, lastName, phone, address);
//
//
//            try {
////            ci.addLibraryMember(member);
//                clearFields();
//                JOptionPane.showMessageDialog(EditMemberWindow.this, "Member information edited Successfully");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(EditMemberWindow.this, "Error: " + e.getMessage());
//            }
//
//
//
//            clearFields();
//        }
//    }
//
//
//
//    private void clearFields() {
//        memberIdField.setText("");
//        firstNameField.setText("");
//        lastNameField.setText("");
//        streetField.setText("");
//        cityField.setText("");
//        stateField.setText("");
//        zipField.setText("");
//        phoneField.setText("");
//    }
//
//}
