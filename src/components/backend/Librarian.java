package components.backend;

import business.CheckOutRecord;

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

    public void searchBookByISBN(String isbn ) {

    }
    @Override
    public String getRoleName() {
        return "Librarian";
    }

    public String getMemberById(String id) {
        return null;
    }

    public List<CheckOutRecord> PrintMemberCheckOutRecord(String memberId) {
    return null;
    }
}
