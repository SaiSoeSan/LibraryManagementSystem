import java.util.List;

public class Book {
    private String title;
    private String isbn;
    private List<Author> authors;
    private int maxCheckoutLength;
    private boolean availability;
    private List<BookCopy> copies;

    public Book(String title, String isbn, List<Author> authors, int maxCheckoutLength, boolean availability, List<BookCopy> copies) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.maxCheckoutLength = maxCheckoutLength;
        this.availability = availability;
        this.copies = copies;
    }
}
