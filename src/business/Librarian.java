package business;

public class Librarian extends LibraryStaff {


    @Override
    public String addNewLibraryMember(LibraryMember libraryMember) {
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
    public String checkOutBook(String memberId, String isbn) {
        return "checkout book by library";
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
