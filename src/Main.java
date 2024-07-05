import components.backend.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        // Create address
//        Address address = new Address("123 Main St", "Springfield", "IL", "62704");
//
//        // Create users
//        User admin = new User("admin1", "adminPass", "Alice", "Smith", "123-456-7890", address);
//        User librarian = new User("lib1", "libPass", "Bob", "Johnson", "098-765-4321", address);
//
//        // Create roles and assign to users
//        Administrator adminRole = new Administrator(admin);
//        Librarian libRole = new Librarian(librarian);
//
//        admin.addRole("Administrator", adminRole);
//        librarian.addRole("Librarian", libRole);
//
//        // Serialize the users to a file
//        Map<String, User> userMap = new HashMap<>();
//        userMap.put(admin.getId(), admin);
//        userMap.put(librarian.getId(), librarian);
//
//        try {
//            SerializationUtil.serialize(userMap, "users.ser");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Login
        User loggedInUser = User.login("admin1", "adminPass");

        if (loggedInUser != null) {
            System.out.println("Login successful!");

            // Determine role
            if (loggedInUser.getRole("Librarian") != null) {
                System.out.println("User is a Librarian");
                Librarian librarian = (Librarian) loggedInUser.getRole("Librarian");
            }
            if (loggedInUser.getRole("Administrator") != null) {
                Administrator administrator = (Administrator) loggedInUser.getRole("Administrator");
//                administrator.addNewMember("12321","Samar","Akkila","Street 2","Fairfield","Iowa","123456","2480234234");

                administrator.getMembers();
            }

//            administrator.getMembers();

            //add book to library system
//            administrator.addNewBook();

        } else {
            System.out.println("Login failed.");
        }
    }
}