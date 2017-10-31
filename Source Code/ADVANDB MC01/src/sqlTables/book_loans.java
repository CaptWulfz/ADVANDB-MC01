package sqlTables;

public class book_loans {
	private int BookID;
	private int BranchID;
	private int CardNo;
	private String DateOut;
	private String DueDate;
	private String DateReturned;
	
	public book_loans(int bookID, int branchID, int cardNo, String dateOut, String dueDate, String dateReturned) {
		BookID = bookID;
		BranchID = branchID;
		CardNo = cardNo;
		DateOut = dateOut;
		DueDate = dueDate;
		DateReturned = dateReturned;
	}

	public int getBookID() {
		return BookID;
	}

	public int getBranchID() {
		return BranchID;
	}

	public int getCardNo() {
		return CardNo;
	}

	public String getDateOut() {
		return DateOut;
	}

	public String getDueDate() {
		return DueDate;
	}

	public String getDateReturned() {
		return DateReturned;
	}
	
	
}
