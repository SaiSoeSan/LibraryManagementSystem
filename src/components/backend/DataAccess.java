package components.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataAccess {

    private static final String MEMBER_FILE_PATH = "src/storage/members.ser";
    private static final String USER_FILE_PATH = "src/storage/users.ser";
    private static final String BOOK_FILE_PATH = "src/storage/books.ser";

    private static HashMap<String, Member> memberMap;
    private static HashMap<String, Book> bookMap;

    static {
        memberMap = readHashMapFromFile(MEMBER_FILE_PATH);
        bookMap = readHashMapFromFile(BOOK_FILE_PATH);
    }

    // Deserialize to an object from the specified file
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        }
    }

    // Read HashMap from file
    @SuppressWarnings("unchecked")
    private static <T> HashMap<String, T> readHashMapFromFile(String filePath) {
        HashMap<String, T> map = new HashMap<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                map = (HashMap<String, T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    // Get members list
    public static List<Member> getMembersList() {
        return new ArrayList<>(memberMap.values());
    }

    // Read member from HashMap
    public static Member readMember(String memberId) {
        return memberMap.get(memberId);
    }

    // Save member to file
    public static void saveMember(Member member) throws IOException {
        memberMap.put(member.getMemberId(), member);
        serializeHashMap(memberMap, MEMBER_FILE_PATH);
    }

    // Read book from HashMap
    public static Book readBook(String ISBN) {
        return bookMap.get(ISBN);
    }

    // Save book to file
    public static void saveBook(Book book) throws IOException {
        bookMap.put(book.getIsbn(), book);
        serializeHashMap(bookMap, BOOK_FILE_PATH);
    }

    // Serialize HashMap to file
    private static <T> void serializeHashMap(HashMap<String, T> map, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
        }
    }
}
