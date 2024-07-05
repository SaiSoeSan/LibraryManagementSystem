import components.backend.Address;
import components.backend.Author;
import components.backend.Book;
import components.backend.Exception.BookNotFoundException;
import components.backend.Exception.MemberNotFoundException;
import components.backend.Member;
import components.backend.*;

import java.util.ArrayList;
import java.util.List;

public class TestCheckoutBook {
    public static void main(String[] args) {
        try {
            Address address = new Address("Street 1", "Fairfield", "IA", "52556;");
            Member member = new Member("Abdul", "Moiz", "641222222", address, "1");
            DataAccess.saveMember(member);

            Author author = new Author("James", "Clear", "64199", address, "lets make a difference");
            List<Author> authors = new ArrayList<Author>();
            authors.add(author);
            Book book = new Book("1100452", "Atomic Habits", 10, authors, 5);
            DataAccess.saveBook(book);

            //Member not found
//            CheckoutEntry checkoutEntry = Member.checkoutBook("10", "1");

            //Book not found
//           CheckoutEntry checkoutEntry = Member.checkoutBook("1", "1");

            //Return the checkout entry
            CheckoutEntry checkoutEntry = Member.checkoutBook("1", "1100452");


            System.out.println(checkoutEntry);

        } catch (MemberNotFoundException | BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
