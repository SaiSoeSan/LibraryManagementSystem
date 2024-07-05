package business;

public class administrator extends LibraryStaff {


    @Override
    public String addNewLibraryMember(LibraryMember libraryMember) {
        return "addNewLibraryMember by Admin Library Member";
    }


    @Override
    public String AddNewBook(Book book) {
        return "AddNewBook by Admin Library Member";
    }

    @Override
    public String addCopyOfBook(String isbn, int copies) {
        return "AddCopyOfBook by Admin Library Member";
    }

    @Override
    public String checkOutBook(String memberId, String isbn) {
        return "CheckOutBook by Admin Library Member";
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
        return "PrintMemberCheckOutRecord by Admin Library Member";
    }

    @Override
    public String getMemberById(String memberId) {
        return "getMemberById by Admin Library Member";
    }
}
