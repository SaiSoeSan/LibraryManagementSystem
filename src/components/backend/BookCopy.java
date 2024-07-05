package components.backend;

import java.io.Serializable;

public class BookCopy implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    private String uniqueNumber;
    private boolean availability;
    public BookCopy(String uniqueNumber, boolean availability){

    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    @Override
    public String toString() {
        return "BookCopy [uniqueNumber=" + uniqueNumber + ", availability=" + availability + "]";
    }
}
