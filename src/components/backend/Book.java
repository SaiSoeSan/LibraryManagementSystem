package components.backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable, Comparable<Book> {
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

    public BookCopy getBookCopy() {
        if(!bookCopyList.isEmpty()) {
            return bookCopyList.getFirst();
        }
        else return null;
    }
    public void saveBook() throws IOException {
        DataAccess.saveBook(this);;
    }
    public void createNewCopy() {
        String uniqueNumber = BookCopy.generateRandomString(5);
        bookCopyList.add(new BookCopy(uniqueNumber, true));
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


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        return o.isbn.compareTo(this.isbn);
    }
}
