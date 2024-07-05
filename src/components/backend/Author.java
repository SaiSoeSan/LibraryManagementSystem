package components.backend;

import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;


    private String bio;

    public Author(String bio) {
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
