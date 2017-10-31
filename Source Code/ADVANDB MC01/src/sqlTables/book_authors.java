package sqlTables;

public class book_authors {
	private int BookID;
	private String AuthorLastName;
	private String AuthorFirstName;
	
	public book_authors(int bookID, String authorLastName, String authorFirstName) {
		BookID = bookID;
		AuthorLastName = authorLastName;
		AuthorFirstName = authorFirstName;
	}

	public int getBookID() {
		return BookID;
	}

	public String getAuthorLastName() {
		return AuthorLastName;
	}

	public String getAuthorFirstName() {
		return AuthorFirstName;
	}
}