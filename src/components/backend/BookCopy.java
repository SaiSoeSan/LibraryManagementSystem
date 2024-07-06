package components.backend;

import java.io.Serializable;
import java.util.Random;

public class BookCopy implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    private String uniqueNumber;
    private boolean availability;
    public BookCopy(String uniqueNumber, boolean availability){
            this.uniqueNumber = uniqueNumber;
            this.availability = availability;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "BookCopy [uniqueNumber=" + uniqueNumber + ", availability=" + availability + "]";
    }
}
