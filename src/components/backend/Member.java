package components.backend;

import java.io.Serial;
import java.io.Serializable;

public class Member extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1022965883958618544L;

    private String memberId;
    public Member(String firstName, String lastName, String phoneNumber, Address address,String memberId) {
        super(firstName, lastName, phoneNumber, address);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
