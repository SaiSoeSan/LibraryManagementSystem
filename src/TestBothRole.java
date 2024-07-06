package storage;

import components.backend.*;

import java.io.IOException;

public class TestBothRole {
    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Springfield", "IL", "62704");

        // Create users
        User admin = new User("admin1", "adminPass", "Alice", "Smith", "123-456-7890", address);
        User librarian = new User("lib1", "libPass", "Bob", "Johnson", "098-765-4321", address);

        // Create roles and assign to users
        Administrator adminRole = new Administrator(admin);
        Librarian libRole = new Librarian(librarian);

        admin.addRole("Administrator", adminRole);
        admin.addRole("Librarian",libRole);
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


            if(loggedInUser.getRole("Librarian") != null && loggedInUser.getRole("Administrator") != null){
                System.out.println("Both Admin and Librarian");
                //if user is a librarianRole, use librarianRole.method()
                Librarian librarianRole = (Librarian) loggedInUser.getRole("Librarian");
                //if user is a administrator, use administratorRole.method()
                Administrator administratorRole = (Administrator) loggedInUser.getRole("Administrator");
            }else if (loggedInUser.getRole("Librarian") != null) {
                Librarian librarianRole = (Librarian) loggedInUser.getRole("Librarian");
            }else if (loggedInUser.getRole("Administrator") != null) {
                Administrator administratorRole = (Administrator) loggedInUser.getRole("Administrator");
            }else{
                System.out.println("Login fail");
            }
        }
    }
}
