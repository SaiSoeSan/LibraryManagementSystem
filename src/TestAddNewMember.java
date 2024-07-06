import components.backend.*;

import java.io.IOException;

public class TestAddNewMember {
    public static void main(String[] args) {
        Address address = Address.CreateNewAddress("123 Main St", "Springfield", "IL", "62704");

        // Create users
        User admin = new User("admin1", "adminPass", "Alice", "Smith", "123-456-7890", address);

        // Create roles and assign to users
        Administrator adminRole = new Administrator(admin);

        admin.addRole("Administrator", adminRole);


        // Create users
        User librarian = new User("lib1", "libPass", "John", "Wall", "123-456-7890", address);

        // Create roles and assign to users
        Librarian librarianRole = new Librarian(librarian);

        librarian.addRole("Librarian", librarianRole);


        // Create users
        User superAdmin = new User("superAdmin", "superPass", "Samar", "Samar", "123-456-7890", address);

        // Create roles and assign to users

        superAdmin.addRole("Librarian", librarianRole);
        superAdmin.addRole("Administrator", adminRole);


        try {
            DataAccess.saveUser(admin);
            DataAccess.saveUser(librarian);
            DataAccess.saveUser(superAdmin);
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

                String memberId = "4324234";
                String firstName = "Sai";
                String lastName = "Soe";
                String street = "street 1";
                String city = "Fairfield";
                String state = "Iowa";
                String zip = "24234234";
                String phoneNumber = "4823856";

                administrator.addNewMember(memberId, firstName, lastName, street, city, state, zip, phoneNumber);

            } else {
                System.out.println("Login failed.");
            }
        }
    }
}
