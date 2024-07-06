package librarysystem.window.usecase;

import components.backend.Administrator;
import components.backend.Book;
import librarysystem.LibWindow;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;

public class SearchBookWindow extends JFrame implements LibWindow {
    public static final SearchBookWindow INSTANCE = new SearchBookWindow();
    private boolean isInitialized = false;


    private JTextField isbnField;

    private Administrator administrator;

    private SearchBookWindow() {}

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
        JPanel formPanel = new JPanel(new FlowLayout());

        isbnField = new JTextField(10);

        formPanel.add(new JLabel("ISBN"));
        formPanel.add(isbnField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        searchButton.addActionListener(evt -> {
            String isbn = isbnField.getText();
            Book response = administrator.searchBookByIsbn(isbn);
            JOptionPane.showMessageDialog(SearchBookWindow.this, "Book returned: \n Title:" + response.getTitle());
            // Add logic to search book by ISBN
        });

        backButton.addActionListener(evt -> {
            AdminWindow.INSTANCE.setVisible(true);
            SearchBookWindow.INSTANCE.setVisible(false);
        });

        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        isInitialized(true);
        pack();
        setSize(400, 200);
    }
}
