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
    @Override
    public String toString() {
        return "CheckoutEntry{checkoutDate= " + checkoutDate + ", dueDate= " + dueDate + ", bookCopy= " + bookCopy + "{'}'}";
    }
}
