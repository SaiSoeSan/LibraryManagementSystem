package components.backend;

import components.backend.Exception.BookNotFoundException;
import components.backend.Exception.MemberNotFoundException;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Member extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1022965883958618544L;
    private String memberId;
    private static List<CheckoutEntry> checkoutEntries;

    public Member(String firstName, String lastName, String phoneNumber, Address address, String memberId) {
        super(firstName, lastName, phoneNumber, address);
        this.memberId = memberId;
        this.checkoutEntries = new ArrayList<CheckoutEntry>();
    }

    public String getMemberId() {
        return memberId;
    }

    public static CheckoutEntry checkoutBook(String memberId, String ISBN) throws MemberNotFoundException, BookNotFoundException, IOException, ClassNotFoundException {
        Member member = DataAccess.readMember(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member not found."); // can throw exception here
        }
        Book book = DataAccess.readBook(ISBN);
        if (book == null) {
            throw new BookNotFoundException("Book not found."); // can throw exception here
        }
        BookCopy bookCopy = book.getBookCopy();
        CheckoutEntry checkoutEntry = new CheckoutEntry(LocalDate.now(), LocalDate.now(), bookCopy);
        checkoutEntries.add(checkoutEntry);
        DataAccess.saveMember(member);
        book.saveBook();

        return checkoutEntry;
    }
}
