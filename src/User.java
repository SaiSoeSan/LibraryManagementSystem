import java.util.List;

public class User {
    private String id;
    private String password;
    private List<Role> roles;

    public User(String id, String password, List<Role> roles) {
        this.id = id;
        this.password = password;
        this.roles = roles;
    }
}
