package components.backend;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;

    // Constructor, getters, and setters
    public Person(String firstName, String lastName, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }


    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
