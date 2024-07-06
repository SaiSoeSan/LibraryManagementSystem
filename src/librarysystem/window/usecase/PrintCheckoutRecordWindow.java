package librarysystem.window.usecase;

import business.CheckOutRecord;
import business.LibraryStaff;
import librarysystem.StaffWindow;
import librarysystem.Util;
import librarysystem.window.LibrarianWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        setSize(600, 400);
    }

    class PrintButtonListener implements ActionListener {
        private JTextField memberIdField;

        PrintButtonListener(JTextField memberIdField) {
            this.memberIdField = memberIdField;
        }

        public void actionPerformed(ActionEvent evt) {
            String memberId = memberIdField.getText();

            try {
//                List<CheckOutRecord> checkoutRecords = libraryStaff.PrintMemberCheckOutRecord(memberId);

                // TODO
                List<CheckOutRecord> checkoutRecords = List.of(new CheckOutRecord());

                if (!checkoutRecords.isEmpty()) {
                    displayCheckoutRecords(checkoutRecords);
                    memberIdField.setText("");
                } else {
                    JOptionPane.showMessageDialog(PrintCheckoutRecordWindow.this, "Member not found or no checkout record.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(PrintCheckoutRecordWindow.this, "Error: " + e.getMessage());
            }
        }


        private void displayCheckoutRecords(List<CheckOutRecord> records) {
            String[] columnNames = {"ISBN", "Title", "Copy Number", "Checkout Date", "Due Date"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are read-only
                }
            };

            for (CheckOutRecord record : records) {
                Object[] rowData = {
                        "record.getBookCopy().getBook().getIsbn()",
                        "record.getBookCopy().getBook().getTitle()",
                        "record.getBookCopy().getCopyNum()",
                        "record.getCheckoutDate()",
                        "record.getDueDate()"
                };
                tableModel.addRow(rowData);
            }

            JTable checkoutTable = new JTable(tableModel);
            checkoutTable.setFillsViewportHeight(true);

            JScrollPane scrollPane = new JScrollPane(checkoutTable);
            scrollPane.setPreferredSize(new Dimension(600, 300));

            JOptionPane.showMessageDialog(PrintCheckoutRecordWindow.this, scrollPane, "Checkout Record", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            LibrarianWindow.INSTANCE.setVisible(true);
            PrintCheckoutRecordWindow.INSTANCE.setVisible(false);
        }
    }
}
