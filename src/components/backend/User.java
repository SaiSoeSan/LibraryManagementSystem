package components.backend;

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
        return this.id.equals(id) && this.password.equals(password);
    }

    public static User login(String id, String password) {
        User user = DataAccess.readUser(id);
        if (user != null && user.verifyCredentials(id, password)) {
            return user;
        }
        return null;
    }

    // Getters and setters
    public String getId() { return id; }
}