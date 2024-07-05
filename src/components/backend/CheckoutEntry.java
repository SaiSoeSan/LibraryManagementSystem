package components.backend;

import java.time.LocalDate;

public class CheckoutEntry {
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
