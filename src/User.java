public class User {
    private String id;
    private String password;
    private UserRole role;

    public User(String id, String password, UserRole role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }
}
