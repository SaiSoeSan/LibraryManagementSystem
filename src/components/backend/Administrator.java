package components.backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Administrator extends UserRole implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    public Administrator(User user) {
        super(user);
    }

    public void addNewMember(String memberId, String firstName, String lastName, String street,
                             String city, String state, String zip, String phoneNumber) {

        // save updated members list to the file
        try {
            Address address = new Address(street, city, state, zip);

            DataAccess.saveMember(new Member(firstName, lastName, phoneNumber, address, memberId));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New member added successfully!");


    }


    public Book searchBookByIsbn(String isbn) {
        return null;
    }


    public void addNewBook(String isbn, String title, List<Author> authors, int maxCheckoutLength,
                           int numberOfCopies) {

        System.out.println("adding book....");

        try {
            Book book = new Book(isbn, title, maxCheckoutLength, authors, numberOfCopies);
            DataAccess.saveBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("New Book added successfully!");
    }

    public void addCopyOfBook(String isbn) throws IOException {
        Book book = User.searchBookByIsbn(isbn);
        book.createNewCopy();
        book.saveBook();
        System.out.println("New copy book added successfully!");

    }

//    public void getMembers() {
//        // Deserialize existing members
//        List<Member> members;
//        try {
//            members = DataAccess.getMembersList();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // Display the list of members
//        System.out.println("List of Members:");
//        for (Member member : members) {
//            System.out.println(member.getMemberId());
//        }
//    }

    public void editMember() {
        System.out.println("Editing a member.");
    }

    public void addNewBok() {
        System.out.println("Adding a book.");
    }


    public String getRoleName() {
        return "Administrator";
    }

}
