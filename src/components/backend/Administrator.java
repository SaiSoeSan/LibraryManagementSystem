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
        // Create Address object
        Address address = new Address(street, city, state, zip);
        // Create a new Member
        Member newMember = new Member(firstName, lastName, phoneNumber,address,memberId);

        // Get List of members from the file
        List<Member> members;
        try {
            members = DataAccess.getMembersList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Add the new member to the list
        members.add(newMember);

        // save updated members list to the file
        try {
            DataAccess.saveMembersList(members);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New member added successfully!");
    }


    public void addBook(String isbn, String title, List<Author> authors, int maxCheckoutLen,
                             int numCopies) {

        // Create a new Author
        Author Author1 = new Author("this is a bio for author 1");

        // Add Author to Author list
        authors.add(Author1);

        // Create a new Book
        Book book = new Book(isbn,title,maxCheckoutLen ,authors,numCopies);

        // save new book  to the file
        try {
            DataAccess.saveBook(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New Book added successfully!");
    }

    public void getMembers() {
        // Deserialize existing members
        List<Member> members;
        try {
            members = DataAccess.getMembersList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Display the list of members
        System.out.println("List of Members:");
        for (Member member : members) {
            System.out.println(member.getMemberId());
        }
    }

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
