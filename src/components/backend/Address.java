package components.backend;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;
    private String street;
    private String city;
    private String state;
    private String zip;

    // Constructor, getters, and setters
    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
