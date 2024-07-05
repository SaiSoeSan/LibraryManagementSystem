import components.backend.Address;
import components.backend.Administrator;
import components.backend.DataAccess;
import components.backend.User;

import java.io.IOException;

public class TestAddBookCopy {
    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Springfield", "IL", "62704");

        // Create users
        User admin = new User("admin1", "adminPass", "Alice", "Smith", "123-456-7890", address);

        // Create roles and assign to users
        Administrator adminRole = new Administrator(admin);

        admin.addRole("Administrator", adminRole);

        try {
            DataAccess.saveUser(admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User loggedInUser = User.login("admin1", "adminPass");
        if (loggedInUser != null) {
            System.out.println("Login successful!");


            if (loggedInUser.getRole("Administrator") != null) {
                System.out.println("User is a Administrator");
                Administrator administrator = (Administrator) loggedInUser.getRole("Administrator");
                try {
                    administrator.addCopyOfBook("22222");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("Login failed.");
            }
        }
    }
}
