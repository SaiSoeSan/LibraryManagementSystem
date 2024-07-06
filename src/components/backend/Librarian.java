package components.backend;

import components.backend.Exception.BookNotFoundException;
import components.backend.Exception.MemberNotFoundException;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Librarian extends UserRole implements Serializable {
    private static final long serialVersionUID = 1022965883958618544L;

    public Librarian(User user) {
        super(user);
    }

    public CheckoutEntry checkOutBook(String memberId, String isbn) throws MemberNotFoundException, BookNotFoundException, IOException {
        return Member.checkoutBook(memberId, isbn);
    }

    public Book searchBookByISBN(String isbn) {
        return DataAccess.readBook(isbn);
    }

    @Override
    public String getRoleName() {
        return "Librarian";
    }


    public Member getMemberById(String id) {
       return DataAccess.readMember(id);
    }

    public List<CheckoutEntry> PrintMemberCheckOutRecord(String memberId) {
        return null;
    }
}
