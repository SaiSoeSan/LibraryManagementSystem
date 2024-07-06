import components.backend.User;

public class TestMemberCheckoutRecord {
    public static void main(String[] args) {
//        Address address = new Address("123 Main St", "Springfield", "IL", "62704");
//
//        // Create users
//        User librarian = new User("lib1", "libPass", "Bob", "Johnson", "098-765-4321", address);
//
//        // Create roles and assign to users;=
//        Librarian libRole = new Librarian(librarian);
//        librarian.addRole("Librarian", libRole);
//
//        try {
//            DataAccess.saveUser(librarian);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        User loggedInUser = User.login("admin1", "adminPass");
        if (loggedInUser != null) {
            System.out.println("Login successful!");

            // Determine role
            if (loggedInUser.getRole("Librarian") != null) {
                System.out.println("User is a Librarian");
//                Librarian librarianRole = (Librarian) loggedInUser.getRole("Librarian");
//                System.out.println(librarianRole.printCheckoutRecord("4324234"));
            } else {
                System.out.println("Login failed.");
            }
        }
    }
}
