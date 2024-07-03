public class User {
    private String id;
    private String password;
    private RoleEnum role;

    public User(String id, String password, RoleEnum role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }
}
