package components.backend;
import java.io.Serializable;

public abstract class UserRole implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    protected User user;

    public UserRole(User user) {
        this.user = user;
    }

    public abstract String getRoleName();
}
