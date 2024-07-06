package business;

public class Librarian extends LibraryStaff {


    @Override
    public String addNewLibraryMember(String memberId,String firstName, String lastName, String phone, String street, String city, String state, String zip) {
        return "Librarian dont do this action";
    }


    @Override
    public String AddNewBook(Book book) {
        return "Librarian dont do this action";
    }

    @Override
    public String addCopyOfBook(String isbn, int copies) {
        return "Librarian dont do this action";
    }

    @Override
    public CheckOutRecord checkOutBook(String memberId, String isbn) {
        return null;
    }

    @Override
    public boolean checkIfBookExist() {
        return false;
    }

    @Override
    public boolean checkBookAvailability() {
        return false;
    }

    @Override
    public String PrintMemberCheckOutRecord(String memberId) {
        return "printMemberCheckOutRecord by Librarian";
    }

    @Override
    public String getMemberById(String memberId) {
        return "getMemberById by Librarian";
    }
}
