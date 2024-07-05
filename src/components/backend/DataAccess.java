package components.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    private static final String MEMBER_FILE_PATH = "src/storage/members.ser";
    private static final String USER_FILE_PATH = "src/storage/users.ser";
    private static final String BOOK_FILE_PATH = "src/storage/books.ser";

    // Serialize the given object to the specified file
    public static void serialize(Object obj, String fileName) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(USER_FILE_PATH);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(obj);
        }
    }

    // Deserialize to an object from the specified file
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(USER_FILE_PATH);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        }
    }

    // Serialize the given object to the specified file
    public static void saveMembersList(List<Member> members) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBER_FILE_PATH))) {
            oos.writeObject(members);
        }
    }


    //get members from file
    public static List<Member> getMembersList() throws IOException, ClassNotFoundException {
        File file = new File(MEMBER_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Member>) ois.readObject();
        }
    }

    //get books from file
    public static List<Book> getBookList() throws IOException, ClassNotFoundException {
        File file = new File(BOOK_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Book>) ois.readObject();
        }
    }

    // Method to get a book by ISBN
    public static Book getBookByISBN(String isbn) throws IOException, ClassNotFoundException {
        List<Book> allBooks = getBookList();

        for (Book book : allBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book; // Return the first book found with the given ISBN
            }
        }

        return null; // Return null if no book with the given ISBN is found
    }


    // Serialize the given object to the specified file
    public static void saveBook(Book book) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE_PATH))) {
            oos.writeObject(book);
        }
    }



}
