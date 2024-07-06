package components.backend;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntry implements Serializable {
    @Serial
    private static final long serialVersionUID = 1022965883958618544L;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BookCopy bookCopy;
    public CheckoutEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
    }


    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    @Override
    public String toString() {
        return "CheckoutEntry{checkoutDate= " + checkoutDate + ", dueDate= " + dueDate + ", bookCopy= " + bookCopy + "{'}'}";
    }
}
