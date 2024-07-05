import java.time.LocalDate;

public class BookCopy {
    private static int copyCounter = 1;
    private String copyNumber;
    private boolean availability;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    public BookCopy(String copyNumber, boolean availability, LocalDate checkoutDate, LocalDate dueDate) {
        this.copyNumber = copyNumber;
        this.availability = availability;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }
}
