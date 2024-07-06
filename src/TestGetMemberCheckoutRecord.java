import components.backend.*;
import components.backend.Exception.BookNotFoundException;
import components.backend.Exception.MemberNotFoundException;

import java.io.IOException;
import java.util.List;

public class TestGetMemberCheckoutRecord {
    public static void main(String[] args) throws BookNotFoundException, IOException, MemberNotFoundException, ClassNotFoundException {
        Address address = new Address("123 Main St", "Springfield", "IL", "62704");

        // Create users
        User admin = new User("admin", "adminPass", "Alice", "Smith", "123-456-7890", address);
        User librarian = new User("librarian", "librarianPass", "Bob", "Johnson", "098-765-4321", address);

        // Create roles and assign to users
        Administrator adminRole = new Administrator(admin);
        Librarian libRole = new Librarian(librarian);

        admin.addRole("Administrator", adminRole);
        librarian.addRole("Librarian", libRole);

        try {
            DataAccess.saveUser(admin);
            DataAccess.saveUser(librarian);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User loggedInUser = User.login("admin", "adminPass");
        User loggedInLibrarian = User.login("librarian", "librarianPass");
        if (loggedInUser != null) {
            System.out.println("Login successful for administrator.");
            Administrator administrator;
            // Determine role

            if (loggedInUser.getRole("Administrator") != null) {
                System.out.println("User is a Administrator");
                administrator = (Administrator) loggedInUser.getRole("Administrator");
                // Add new Member with checkout entries
                administrator.addNewMember("100", "Abdul", "Moiz", "1000 N 4th Street", "Fairfield", "IA", "52554", "641233333");
                administrator.addNewMember("101", "Abdul", "Moiz", "1000 N 4th Street", "Fairfield", "IA", "52554", "641233333");
                administrator.addNewBook("5001", "Atomic Habits", 1, 10, 10);
                administrator.addNewBook("5002", "Rich dad poor dad", 1, 10, 10);

                Member.checkoutBook("100", "5001");
                Member.checkoutBook("100", "5002");
                Member.checkoutBook("101", "5001");
            }
             else {
                System.out.println("Login failed.");
            }
        }

        if (loggedInLibrarian != null) {
            Librarian librarianRole;
            if (loggedInLibrarian.getRole("Librarian") != null) {
                System.out.println("User is a Librarian");
                librarianRole = (Librarian) loggedInLibrarian.getRole("Librarian");
                List<CheckoutEntry> entries = librarianRole.getMemberCheckoutRecord("100");
                for (CheckoutEntry entry : entries) {
                    System.out.println(entry);
                }
            } else {
                System.out.println("Login failed.");
            }
        }

    }
}
