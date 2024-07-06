package business;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


	public static List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		ArrayList<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	public static List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		ArrayList<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}


    public String searchBookByIsbn(String isbn) {
        return "General way of searching for a book";
    }


    public abstract String addNewLibraryMember(String memberId,String firstName, String lastName, String phone, String street, String city, String state, String zip);

//    public abstract String editLibraryMemberInfo();

    public abstract String AddNewBook(Book book);

    public abstract String addCopyOfBook(String isbn, int copies);



    public abstract CheckOutRecord checkOutBook(String memberId, String isbn);


    public abstract boolean checkIfBookExist();


    public abstract boolean checkBookAvailability();

    public abstract String PrintMemberCheckOutRecord(String memberId);


    public abstract String getMemberById(String memberId);



}
