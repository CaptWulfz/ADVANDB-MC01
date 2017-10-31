package sqlTables;

public class book {
	private int BookID;
	private String Title;
	private String PublisherName;
	
	public book(int bookID, String title, String publisherName) {
		BookID = bookID;
		Title = title;
		PublisherName = publisherName;
	}

	public int getBookID() {
		return BookID;
	}

	public String getTitle() {
		return Title;
	}

	public String getPublisherName() {
		return PublisherName;
	}
	
	
}
