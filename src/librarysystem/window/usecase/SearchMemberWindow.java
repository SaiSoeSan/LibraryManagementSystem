package librarysystem.window.usecase;

import components.backend.Librarian;
import components.backend.Member;
import librarysystem.LibWindow;
import librarysystem.window.AdminWindow;

import javax.swing.*;
import java.awt.*;

public class SearchMemberWindow extends JFrame implements LibWindow {
    public static final SearchMemberWindow INSTANCE = new SearchMemberWindow();
    private boolean isInitialized = false;


    private JTextField memberIdField;

    private Librarian librarian;

    private SearchMemberWindow() {}

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
        JPanel formPanel = new JPanel(new FlowLayout());

        memberIdField = new JTextField(10);

        formPanel.add(new JLabel("Memberr Id"));
        formPanel.add(memberIdField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        searchButton.addActionListener(evt -> {
            String id = memberIdField.getText();
            // TODO
            Member response = librarian.getMemberById(id);
            JOptionPane.showMessageDialog(SearchMemberWindow.this, "Member returned: " + response);
            // Add logic to search book by ISBN
        });

        backButton.addActionListener(evt -> {
            AdminWindow.INSTANCE.setVisible(true);
            SearchMemberWindow.INSTANCE.setVisible(false);
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
