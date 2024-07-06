package librarysystem.window.usecase;

import business.BookCopy;
import components.backend.Librarian;
import librarysystem.LibWindow;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchDueBookWindow extends JFrame implements LibWindow {
    public static final SearchDueBookWindow INSTANCE = new SearchDueBookWindow();
    private boolean isInitialized = false;

    private JTextField isbnField;
    private Librarian Liberian;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    private SearchDueBookWindow() {}

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
        this.Liberian = librarian;
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new FlowLayout());

        isbnField = new JTextField(10);

        formPanel.add(new JLabel("ISBN"));
        formPanel.add(isbnField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        searchButton.addActionListener(evt -> searchBookCopies());

        backButton.addActionListener(evt -> {
            AdminWindow.INSTANCE.setVisible(true);
            SearchDueBookWindow.INSTANCE.setVisible(false);
        });

        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(new String[]{"ISBN", "Title", "Copy Number", "Checked Out By", "Due Date"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are read-only
            }
        };
        resultTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(600, 400);

    }

    private void searchBookCopies() {
        String isbn = isbnField.getText();
        try {
            //TODO
//            ArrayList<BookCopy> bookCopies = libraryStaff.searchBookByIsbn(isbn);
            List<BookCopy> bookCopies = List.of(new BookCopy()); // Replace with actual search logic
            displayBookCopies(bookCopies);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(SearchDueBookWindow.this, "Error: " + e.getMessage());
        }
    }

    private void displayBookCopies(List<BookCopy> bookCopies) {
        tableModel.setRowCount(0); // Clear existing data

        if (bookCopies != null && !bookCopies.isEmpty()) {
            for (BookCopy copy : bookCopies) {
                String checkedOutBy = "";
                String dueDate = "";

                tableModel.addRow(new Object[]{
                        "copy.getBook().getIsbn()",
                        "copy.getBook().getTitle()",
                        "copy.getCopyNum()",
                        "checkedOutBy",
                        "dueDate"
                });
            }
        } else {
            JOptionPane.showMessageDialog(SearchDueBookWindow.this, "No book copies found for the given ISBN.");
        }
    }
}
