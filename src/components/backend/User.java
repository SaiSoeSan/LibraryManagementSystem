package components.backend;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User extends Person implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    private String id;
    private String password;
    private Map<String, UserRole> roles;

    public User(String id, String password, String firstName, String lastName, String phoneNumber, Address address) {
        super(firstName, lastName, phoneNumber, address);
        this.id = id;
        this.password = password;
        this.roles = new HashMap<>();
    }

    public void addRole(String roleName, UserRole role) {
        roles.put(roleName, role);
    }

    public UserRole getRole(String roleName) {
        return roles.get(roleName);
    }

    public boolean verifyCredentials(String id, String password) {
        System.out.println("passed from front end " + id);
        System.out.println("passed from frontend " + password);
        System.out.println("this" + this.password);
        System.out.println("this " + this.id);
        return this.id.equals(id) && this.password.equals(password);
    }

    public static User login(String id, String password) {
        Map<String, User> userMap = null;
        try {
            userMap = (Map<String, User>) DataAccess.deserialize("users.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (userMap != null) {
            User user = userMap.get(id);
            if (user != null && user.verifyCredentials(id, password)) {
                return user;
            }

        }
        return null;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}