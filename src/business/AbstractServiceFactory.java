package business;


public class AbstractServiceFactory {


	public static LibraryStaff getLibraryService() {
		return new Librarian();
	}

	public static LibraryStaff getAdminLibraryService() {
		return new administrator();
	}


	
}
