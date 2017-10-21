package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;

public class RightSide {
	
	private ScrollPane ScrollPane = new ScrollPane();
	private TableView<Book> booksView = new TableView<Book>();
	private TableView<Author> auhtorView = new TableView<Author>();
	private TableView<Loan> loansView = new TableView<Loan>();
	private TableView<Borrower> borrowView = new TableView<Borrower>();
	private TableView<Library> branchView = new TableView<Library>();
	private TableView<Publisher> pubView = new TableView<Publisher>();
	private VBox right = new VBox();
	
	public RightSide(){
		initTableViews();
		initScrollPane();
		right.getChildren().addAll(ScrollPane);
		right.setPadding(new Insets(10,10,10,10));
		GridPane.setConstraints(right, 1, 0);
	}
	
	private void initTableViews() {
		bookColCreate();
		authorColCreate();
		loansColCreate();
		borrowColCreate();
		libraryColCreate();
		publisherColCreate();
	}

	@SuppressWarnings("unchecked")
	private void bookColCreate() {
		TableColumn<Book, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(125.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<Book, String>("BookID"));
		
		TableColumn<Book, String> TitleCol = new TableColumn<>("Title");
		TitleCol.setPrefWidth(125.0d); 
		TitleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		
		TableColumn<Book, String> PubCol = new TableColumn<>("PublisherName");
		PubCol.setPrefWidth(150.0d); 
		PubCol.setCellValueFactory(new PropertyValueFactory<Book, String>("PublisherName"));
		
		booksView.getColumns().addAll(IDCol, TitleCol, PubCol);
	}
	
	public void switchToBooks(){
		ScrollPane.setContent(booksView);
	}
	
	public void clearBooks(){
		booksView = new TableView<Book>();
		bookColCreate();
	}
	
	public void addToBooks(int BookID, String Title, String Publisher){
		ObservableList<Book> Row = FXCollections.observableArrayList();
		Row.add(new Book(BookID, Title, Publisher));
		booksView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void authorColCreate() {
		TableColumn<Author, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(125.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<Author, String>("BookID"));
		
		TableColumn<Author, String> ALNCol = new TableColumn<>("AuthorLastName");
		ALNCol.setPrefWidth(125.0d); 
		ALNCol.setCellValueFactory(new PropertyValueFactory<Author, String>("AuthorLastName"));
		
		TableColumn<Author, String> AFNCol = new TableColumn<>("AuthorFirstName");
		AFNCol.setPrefWidth(150.0d); 
		AFNCol.setCellValueFactory(new PropertyValueFactory<Author, String>("AuthorFirstName"));
		
		auhtorView.getColumns().addAll(IDCol, ALNCol, AFNCol);
	}
	
	public void switchToAuthor(){
		ScrollPane.setContent(auhtorView);
	}
	
	public void clearAuthor(){
		auhtorView = new TableView<Author>();
		authorColCreate();
	}
	
	public void addToAuthor(int BookID, String AuthorLName, String AuthorFName){
		ObservableList<Author> Row = FXCollections.observableArrayList();
		Row.add(new Author(BookID, AuthorLName, AuthorFName));
		auhtorView.setItems(Row);
	}

	@SuppressWarnings("unchecked")
	private void loansColCreate() {
		TableColumn<Loan, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(50.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("BookID"));
		
		TableColumn<Loan, String> BIDCol = new TableColumn<>("BranchID");
		BIDCol.setPrefWidth(60.0d); 
		BIDCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("BranchID"));
		
		TableColumn<Loan, String> CNoCol = new TableColumn<>("CardNo");
		CNoCol.setPrefWidth(50.0d); 
		CNoCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("CardNo"));
		
		TableColumn<Loan, String> DOCol = new TableColumn<>("DateOut");
		DOCol.setPrefWidth(75.0d); 
		DOCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("DateOut"));
		
		TableColumn<Loan, String> DDCol = new TableColumn<>("DueDate");
		DDCol.setPrefWidth(75.0d); 
		DDCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("DueDate"));
		
		TableColumn<Loan, String> DRCol = new TableColumn<>("DateReturned");
		DRCol.setPrefWidth(100.0d); 
		DRCol.setCellValueFactory(new PropertyValueFactory<Loan, String>("DateReturned"));
		
		loansView.getColumns().addAll(IDCol, BIDCol, CNoCol, DOCol, DDCol, DRCol);
	}
	
	public void switchToLoans(){
		ScrollPane.setContent(loansView);
	}
	
	public void clearLoans(){
		loansView = new TableView<Loan>();
		loansColCreate();
	}
	
	public void addToLoans(int BookID, int BranchID, int CardNo, String DateOut, String DueDate, String DateReturned){
		ObservableList<Loan> Row = FXCollections.observableArrayList();
		Row.add(new Loan(BookID, BranchID, CardNo, DateOut, DueDate, DateReturned));
		loansView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void borrowColCreate() {
		TableColumn<Borrower, String> CNoCol = new TableColumn<>("CardNo");
		CNoCol.setPrefWidth(50.0d); 
		CNoCol.setCellValueFactory(new PropertyValueFactory<Borrower, String>("CardNo"));
		
		TableColumn<Borrower, String> BLNCol = new TableColumn<>("BorrowerLName");
		BLNCol.setPrefWidth(100.0d); 
		BLNCol.setCellValueFactory(new PropertyValueFactory<Borrower, String>("BorrowerLName"));
		
		TableColumn<Borrower, String> BFNCol = new TableColumn<>("BorrowerFName");
		BFNCol.setPrefWidth(100.0d); 
		BFNCol.setCellValueFactory(new PropertyValueFactory<Borrower, String>("BorrowerFName"));
		
		TableColumn<Borrower, String> AdCol = new TableColumn<>("Address");
		AdCol.setPrefWidth(75.0d); 
		AdCol.setCellValueFactory(new PropertyValueFactory<Borrower, String>("Address"));
		
		TableColumn<Borrower, String> PhnCol = new TableColumn<>("Phone");
		PhnCol.setPrefWidth(75.0d); 
		PhnCol.setCellValueFactory(new PropertyValueFactory<Borrower, String>("Phone"));
		
		borrowView.getColumns().addAll(CNoCol, BLNCol, BFNCol, AdCol, PhnCol);
	}
	
	public void switchToBorrow(){
		ScrollPane.setContent(borrowView);
	}
	
	public void clearBorrow(){
		borrowView = new TableView<Borrower>();
		borrowColCreate();
	}
	
	public void addToBorrow(int CardNo, String BorrowLN, String BorrowFN, String Address, String Phone){
		ObservableList<Borrower> Row = FXCollections.observableArrayList();
		Row.add(new Borrower(CardNo, BorrowLN, BorrowFN, Address, Phone));
		borrowView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void libraryColCreate() {
		TableColumn<Library, String> BIDCol = new TableColumn<>("BranchID");
		BIDCol.setPrefWidth(125.0d); 
		BIDCol.setCellValueFactory(new PropertyValueFactory<Library, String>("BranchID"));
		
		TableColumn<Library, String> BCol = new TableColumn<>("BranchName");
		BCol.setPrefWidth(125.0d); 
		BCol.setCellValueFactory(new PropertyValueFactory<Library, String>("BranchName"));
		
		TableColumn<Library, String> BACol = new TableColumn<>("BranchAddress");
		BACol.setPrefWidth(150.0d); 
		BACol.setCellValueFactory(new PropertyValueFactory<Library, String>("BranchAddress"));
		
		branchView.getColumns().addAll(BIDCol, BCol, BACol);	
	}
	
	public void switchToLibrary(){
		ScrollPane.setContent(branchView);
	}
	
	public void clearLibrary(){
		branchView = new TableView<Library>();
		libraryColCreate();
	}
	
	public void addToLibrary(int BranchID, String BranchName, String BranchAddress){
		ObservableList<Library> Row = FXCollections.observableArrayList();
		Row.add(new Library(BranchID, BranchName, BranchAddress));
		branchView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void publisherColCreate() {
		TableColumn<Publisher, String> PNCol = new TableColumn<>("PublisherName");
		PNCol.setPrefWidth(125.0d); 
		PNCol.setCellValueFactory(new PropertyValueFactory<Publisher, String>("PublisherName"));
		
		TableColumn<Publisher, String> AdCol = new TableColumn<>("Address");
		AdCol.setPrefWidth(125.0d); 
		AdCol.setCellValueFactory(new PropertyValueFactory<Publisher, String>("Address"));
		
		TableColumn<Publisher, String> PhnCol = new TableColumn<>("Phone");
		PhnCol.setPrefWidth(150.0d); 
		PhnCol.setCellValueFactory(new PropertyValueFactory<Publisher, String>("Phone"));
		
		pubView.getColumns().addAll(PNCol, AdCol, PhnCol);
	}
	
	public void switchToPublisher(){
		ScrollPane.setContent(pubView);
	}
	
	public void clearPublisher(){
		pubView = new TableView<Publisher>();
		publisherColCreate();
	}
	
	public void addToLibrary(String  PublisherName, String Address, int Phone){
		ObservableList<Publisher> Row = FXCollections.observableArrayList();
		Row.add(new Publisher(PublisherName, Address, Phone));
		pubView.setItems(Row);
	}

	private void initScrollPane() {
		ScrollPane.setContent(booksView);
	}

	public VBox getRightSide(){
		return right;
	}
}
