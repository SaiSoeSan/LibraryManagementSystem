import components.backend.*;

import java.io.IOException;

public class TestGetMemberCheckoutRecord {
    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Springfield", "IL", "62704");

        // Create users
        User admin = new User("admin1", "adminPass", "Alice", "Smith", "123-456-7890", address);
        User librarian = new User("lib1", "libPass", "Bob", "Johnson", "098-765-4321", address);

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

        User loggedInUser = User.login("admin1", "adminPass");
        if (loggedInUser != null) {
            System.out.println("Login successful!");
            Librarian librarianRole;
            Administrator administrator;
            // Determine role
            if (loggedInUser.getRole("Librarian") != null) {
                System.out.println("User is a Librarian");
                librarianRole = (Librarian) loggedInUser.getRole("Librarian");
            }
            if (loggedInUser.getRole("Administrator") != null) {
                System.out.println("User is a Administrator");
                 administrator = (Administrator) loggedInUser.getRole("Administrator");

            } else {
                System.out.println("Login failed.");
            }

            // Add new Member with checkout entries

        }
    }
}
