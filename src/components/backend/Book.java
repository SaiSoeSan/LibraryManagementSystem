package components.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;
//
    private String title;
    private String isbn;
    private int maxCheckoutLength;
    private List<BookCopy> bookCopyList;
    private List<Author> authors;

    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numCopies) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = authors;
        this.bookCopyList = new ArrayList<>();
        for (int i = 0; i < numCopies; i++) {
            bookCopyList.add(new BookCopy(isbn + "-" + i, true));
        }
    }

    public String getIsbn() {
        return isbn;
    }

//    public boolean isAvailable() {
//        return availability;
//    }

//    public void setAvailability(boolean availability) {
//        this.availability = availability;
//    }

//    public String getUniqueNumber() {
//        return uniqueNumber;
//    }

//    @Override
//    public String toString() {
//        return "BookCopy [uniqueNumber=" + uniqueNumber + ", availability=" + availability + "]";
//    }
}
