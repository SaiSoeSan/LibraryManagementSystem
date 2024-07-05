package dataaccess;

import business.Book;
import business.LibraryMember;

import java.util.HashMap;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String, User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveNewMember(LibraryMember member);
}