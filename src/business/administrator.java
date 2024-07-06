package business;

public class administrator extends LibraryStaff {


    @Override
    public String addNewLibraryMember(String memberId,String firstName, String lastName, String phone, String street, String city, String state, String zip) {
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
        return "PrintMemberCheckOutRecord by Admin Library Member";
    }

    @Override
    public String getMemberById(String memberId) {
        return "getMemberById by Admin Library Member";
    }
}
