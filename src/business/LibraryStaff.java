package business;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.HashMap;

public abstract class LibraryStaff {

    public static Auth currentAuth = null;


    public static Auth login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if(!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }

        String passwordFound = map.get(id).getPassword();
        if(!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        return currentAuth = map.get(id).getAuthorization();

    }


    public String searchBookByIsbn(String isbn) {
        return "General way of searching for a book";
    }


    public abstract String addNewLibraryMember(LibraryMember libraryMember);

//    public abstract String editLibraryMemberInfo();

    public abstract String AddNewBook(Book book);

    public abstract String addCopyOfBook(String isbn, int copies);



    public abstract String checkOutBook(String memberId, String isbn);


    public abstract boolean checkIfBookExist();


    public abstract boolean checkBookAvailability();

    public abstract String PrintMemberCheckOutRecord(String memberId);


    public abstract String getMemberById(String memberId);



}
