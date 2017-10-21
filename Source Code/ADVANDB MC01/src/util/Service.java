package util;

import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
	public static String[]
		viewDBQs = { //ViewDB Queries
			"SELECT * FROM book;",
			"SELECT * FROM book_authors;",
			"SELECT * FROM book_loans;",
			"SELECT * FROM borrower;",
			"SELECT * FROM library_branch;",
			"SELECT * FROM publisher;",
		},
			
		presetQs = { //Preset Queries
			//1 table
			"SELECT Title, PublisherName FROM book WHERE PublisherName = \"Huffington Post\" ORDER BY Title;",
			
			"SELECT PublisherName, Address FROM publisher WHERE Address = \"Los Angeles\" order by 1 asc;",
			
			//2 tables
			"SELECT b.BookID, b.Title, b.PublisherName, ba.AuthorLastName, ba.AuthorFirstName"
			+ " FROM book b, book_authors ba "
			+ " WHERE b.BookID IN (SELECT BookID FROM book_authors WHERE AuthorLastName = \"Stonefeather\") AND b.BookID = ba.BookID"
			+ " ORDER BY b.Title ASC;",
			
			"SELECT lb.BranchName, COUNT(bl.BookID) as 'NoBooksLoaned'"
			+ " FROM library_branch lb, book_loans bl"
			+ " WHERE lb.BranchID = bl.BranchID AND bl.DateOut BETWEEN \"01/01/2006\" AND \"31/12/2011\""
			+ " GROUP BY bl.BranchID"
			+ " ORDER BY BranchName ASC;",
			
			//3 tables
			"SELECT b.Title"
			+ " FROM book b, book_loans bl, library_branch lb"
			+ " WHERE b.BookID = bl.BookID AND bl.BranchID = lb.BranchID AND lb.BranchName = 'Silver'"
			+ " GROUP BY b.BookID"
			+ " HAVING COUNT(b.BookID) > 1",
			
			"SELECT DISTINCT br.BorrowerLName, br.CardNo"
			+ " FROM booksdb.borrower br, booksdb.library_branch lb, booksdb.book_loans bl"
			+ " WHERE br.CardNo = bl.CardNo AND bl.BranchID = lb.BranchID AND lb.BranchName = 'Silver'",
			
			//4 tables
			"SELECT br.BorrowerLName, br.BorrowerFName, b.BookID, b.Title, ba.AuthorLastName, ba.AuthorFirstName, bl.DueDate, bl.DateReturned"
			+ " FROM book b, book_authors ba, borrower br, book_loans bl"
			+ " WHERE bl.DueDate = bl.DateReturned AND b.BookID = \"%\" + bl.BookID AND b.bookID = ba.BookID AND ba.BookID = \"%\" + bl.BookID AND bl.CardNo = br.CardNo"
			+ " ORDER BY BorrowerLName ASC, Title ASC;",
			
			"SELECT DISTINCT p.PublisherName, lb.BranchAddress"
			+ " FROM publisher p, library_branch lb, book_loans bl, book b"
			+ " WHERE lb.BranchAddress = 'New York' AND bl.BookID = b.BookID AND bl.BranchID = lb.BranchID AND b.PublisherName = p.PublisherName;"
		};
	
	//choose from viewDB queries (1-6), 1-based index
	public static ArrayList<ArrayList<?>> viewDBQ(int n) {
		if (n < 1 || n > 6)
			return null;
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		try {
			result = Query.getInstance().getRS(viewDBQs[n - 1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//choose from preset queries (1-8), 1-based index
	public static ArrayList<ArrayList<?>> presetQ(int n) {
		if (n < 1 || n > 8)
			return null;
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		try {
			result = Query.getInstance().getRS(presetQs[n - 1]);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//new queries
	public static ArrayList<ArrayList<?>> newQ(String query) {
		if (query.equalsIgnoreCase("quit"))
			return null;
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		try {
			result = Query.getInstance().getRS(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
