package components.backend;

import java.io.Serializable;
import java.util.List;

public class Librarian extends UserRole implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    public Librarian(User user) {
        super(user);
    }

    public void checkOutBook() {
        System.out.println(user.getFirstName() + " is checking out a book...");
    }

    public List<CheckoutEntry> printCheckoutRecord(String memberId) {

        Member member = DataAccess.readMember(memberId);
        return member.getCheckoutEntries();
    }

    public void searchBookByISBN(String isbn ) {

    }
    @Override
    public String getRoleName() {
        return "Librarian";
    }

}
