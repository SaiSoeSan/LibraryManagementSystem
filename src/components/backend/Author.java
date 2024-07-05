package components.backend;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;


    private String bio;

    public Author(String firstName, String lastName, String phoneNumber, Address address, String bio) {
        super(firstName, lastName, phoneNumber, address);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String toString() {
        return "Author [bio=" + bio + "]";
    }
}
