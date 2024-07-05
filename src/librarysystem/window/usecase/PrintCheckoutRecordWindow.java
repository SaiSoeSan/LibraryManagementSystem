package librarysystem.window.usecase;


import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.LibrarianWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintCheckoutRecordWindow extends JFrame implements StaffWindow {
    public static final PrintCheckoutRecordWindow INSTANCE = new PrintCheckoutRecordWindow();
    private boolean isInitialized = false;

    private LibraryStaff libraryStaff;

    private PrintCheckoutRecordWindow() {}

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
        JLabel label = new JLabel("Print Checkout Record");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        labelPanel.add(label);

        JPanel formPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel memberIdLabel = new JLabel("Member ID:");
        JTextField memberIdField = new JTextField(10);
        inputPanel.add(memberIdLabel);
        inputPanel.add(memberIdField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printButton = new JButton("Print Record");
        JButton backButton = new JButton("Back");

        printButton.addActionListener(new PrintButtonListener(memberIdField));
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(printButton);
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

    class PrintButtonListener implements ActionListener {
        private JTextField memberIdField;

        PrintButtonListener(JTextField memberIdField) {
            this.memberIdField = memberIdField;
        }

        public void actionPerformed(ActionEvent evt) {
            String memberId = memberIdField.getText();

            // Perform printing logic here
//            String checkoutRecord = LibrarySystem.INSTANCE.printCheckoutRecord(memberId);
            String checkoutRecord = libraryStaff.PrintMemberCheckOutRecord(memberId); // TODO
//            String checkoutRecord = "success"; // TODO

            if (checkoutRecord != null) {
                JTextArea textArea = new JTextArea(checkoutRecord);
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 300));
                JOptionPane.showMessageDialog(PrintCheckoutRecordWindow.this, scrollPane, "Checkout Record", JOptionPane.INFORMATION_MESSAGE);
                memberIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(PrintCheckoutRecordWindow.this, "Member not found or no checkout record.");
            }
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            LibrarianWindow.INSTANCE.setVisible(true);
            PrintCheckoutRecordWindow.INSTANCE.setVisible(false);
        }
    }
}

