import components.backend.Address;
import components.backend.Administrator;
import components.backend.DataAccess;
import components.backend.User;

import java.io.IOException;
import java.util.ArrayList;

public class TestAddBook {
    public static void main(String[] args) {
        Address address = Address.CreateNewAddress("123 Main St", "Springfield", "IL", "62704");

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

                String isnb = "22222";
                String title = "Book 1";
                int maxCheckoutLen = 6;
                int countOfAuthors = 2;
                int numberOfCopies = 2;

                administrator.addNewBook(isnb, title, new ArrayList<>(), maxCheckoutLen, numberOfCopies);

            } else {
                System.out.println("Login failed.");
            }
        }
    }
}
