package components.backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Administrator extends UserRole implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    public Administrator(User user) {
        super(user);
    }

    public void addNewMember(String memberId, String firstName, String lastName, String street,
                          String city, String state, String zip, String phoneNumber) {

        // Get Map of members from the file
        Map<String,Member> memberMap;
        try {
            memberMap = (Map<String, Member>) DataAccess.getMembersList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        if(memberMap == null){
            memberMap = new HashMap<>();
        };

        if (!memberMap.containsKey(memberId)) {
            // Create Address object
            Address address = new Address(street, city, state, zip);
            // Create a new Member
            Member newMember = new Member(firstName, lastName, phoneNumber,address,memberId);
            memberMap.put(memberId,newMember);
        } else {
            System.out.println("Member with memberId " + memberId + " already exists.");
            return;
        }

        // save updated members list to the file
        try {
            DataAccess.saveMembersList(memberMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("New member added successfully!");
    }


    public void addNewBook(String isbn, String title, int countOfAuthors, int maxCheckoutLength,
                             int numberOfCopies) {

        System.out.println("adding book....");

        List<Author> authors = new ArrayList<>();

        //create author based on how many authors does the frontend pass me
        for(int i = 0; i < countOfAuthors; i++) {
            Author author = new Author("Author" + i);
            authors.add(author);
        }

        Map<String, Book> bookMap = null;

        try {
            bookMap = (Map<String, Book>) DataAccess.getBookList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(bookMap == null){
            bookMap = new HashMap<>();
        }

        if (!bookMap.containsKey(isbn)) {
            Book book = new Book(isbn,title,maxCheckoutLength,authors,numberOfCopies);
            bookMap.put(isbn,book);
        } else {
            System.out.println("Book with ISBN " + isbn + " already exists.");
            return;
        }

        try {
            DataAccess.saveBooks(bookMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("New Book added successfully!");
    }

    public void addCopyOfBook(String isbn){
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
