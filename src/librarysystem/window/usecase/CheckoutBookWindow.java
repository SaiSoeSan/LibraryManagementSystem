package librarysystem.window.usecase;

import business.CheckOutRecord;
import components.backend.Librarian;
import librarysystem.LibWindow;
import librarysystem.Util;
import librarysystem.window.LibrarianWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutBookWindow extends JFrame implements LibWindow {
    public static final CheckoutBookWindow INSTANCE = new CheckoutBookWindow();
    private boolean isInitialized = false;

    private Librarian librarian;

    private JTextField memberIdField;
    private JTextField isbnField;
    private JTable checkoutTable;
    private DefaultTableModel tableModel;

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

    public void init(Librarian librarian) {
        this.librarian = librarian;


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(800, 600)); // Set preferred size

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Checkout Book");
        Util.adjustLabelFont(label, Util.DARK_BLUE, true);
        labelPanel.add(label);

        JPanel formPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel memberIdLabel = new JLabel("Member ID:");
        formPanel.add(memberIdLabel, gbc);

        memberIdField = new JTextField(10);
        gbc.gridx++;
        formPanel.add(memberIdField, gbc);

        JLabel isbnLabel = new JLabel("ISBN:");
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(isbnLabel, gbc);

        isbnField = new JTextField(10);
        gbc.gridx++;
        formPanel.add(isbnField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");

        checkoutButton.addActionListener(new CheckoutButtonListener());
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);

        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(formPanel, BorderLayout.CENTER);
        inputButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
        mainPanel.add(inputButtonPanel, BorderLayout.WEST); // Adjusted to WEST for justification

        tableModel = new DefaultTableModel(new String[]{"ISBN", "Title", "Copy Number", "Checkout Date", "Due Date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        checkoutTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(checkoutTable);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER); // Centered the table

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();

        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(centerX, centerY);

        setSize(800, 600); // Set size after positioning
    }

    class CheckoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String memberId = memberIdField.getText();
            String isbn = isbnField.getText();

            try {
                // CheckOutRecord response = libraryStaff.checkOutBook(memberId, isbn);
                CheckOutRecord response = new CheckOutRecord();
                if (response != null) {
                    updateCheckoutTable(response);
                    clearFields();
                    JOptionPane.showMessageDialog(CheckoutBookWindow.this, "Book successfully checked out.");
                } else {
                    JOptionPane.showMessageDialog(CheckoutBookWindow.this, "Checkout failed. Please check the Member ID and ISBN.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(CheckoutBookWindow.this, "Error: " + e.getMessage());
            }
        }
    }

    class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            LibrarianWindow.INSTANCE.setVisible(true);
            CheckoutBookWindow.INSTANCE.setVisible(false);
            Util.centerFrameOnDesktop(LibrarianWindow.INSTANCE); // Center the LibrarianWindow on the desktop
        }
    }

    private void clearFields() {
        memberIdField.setText("");
        isbnField.setText("");
    }

    private void updateCheckoutTable(CheckOutRecord entry) {
        tableModel.addRow(new Object[]{
                "Isbn",
                "Title",
                "entry.getBookCopy().getCopyNum()",
                "entry.getCheckoutDate()",
                "entry.getDueDate()"
        });
    }
}
