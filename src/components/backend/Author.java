package components.backend;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;


    private String bio;
    private String credentials;

    public Author(String firstName, String lastName, String phoneNumber, Address address, String bio, String credentials ) {
        super(firstName, lastName, phoneNumber, address);
        this.bio = bio;
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Author [bio=" + bio + "]";
    }
}
