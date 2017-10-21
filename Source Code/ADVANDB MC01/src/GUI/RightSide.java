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
import sqlTables.*;

public class RightSide {
	
	private ScrollPane ScrollPane = new ScrollPane();
	private TableView<book> booksView = new TableView<book>();
	private TableView<book_authors> auhtorView = new TableView<book_authors>();
	private TableView<book_loans> loansView = new TableView<book_loans>();
	private TableView<borrower> borrowView = new TableView<borrower>();
	private TableView<library_branch> branchView = new TableView<library_branch>();
	private TableView<publisher> pubView = new TableView<publisher>();
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
		TableColumn<book, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(125.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<book, String>("BookID"));
		
		TableColumn<book, String> TitleCol = new TableColumn<>("Title");
		TitleCol.setPrefWidth(125.0d); 
		TitleCol.setCellValueFactory(new PropertyValueFactory<book, String>("Title"));
		
		TableColumn<book, String> PubCol = new TableColumn<>("PublisherName");
		PubCol.setPrefWidth(150.0d); 
		PubCol.setCellValueFactory(new PropertyValueFactory<book, String>("PublisherName"));
		
		booksView.getColumns().addAll(IDCol, TitleCol, PubCol);
	}
	
	public void switchToBooks(){
		ScrollPane.setContent(booksView);
	}
	
	public void clearBooks(){
		booksView = new TableView<book>();
		bookColCreate();
	}
	
	public void addToBooks(int BookID, String Title, String Publisher){
		ObservableList<book> Row = FXCollections.observableArrayList();
		Row.add(new book(BookID, Title, Publisher));
		booksView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void authorColCreate() {
		TableColumn<book_authors, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(125.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<book_authors, String>("BookID"));
		
		TableColumn<book_authors, String> ALNCol = new TableColumn<>("AuthorLastName");
		ALNCol.setPrefWidth(125.0d); 
		ALNCol.setCellValueFactory(new PropertyValueFactory<book_authors, String>("AuthorLastName"));
		
		TableColumn<book_authors, String> AFNCol = new TableColumn<>("AuthorFirstName");
		AFNCol.setPrefWidth(150.0d); 
		AFNCol.setCellValueFactory(new PropertyValueFactory<book_authors, String>("AuthorFirstName"));
		
		auhtorView.getColumns().addAll(IDCol, ALNCol, AFNCol);
	}
	
	public void switchToAuthor(){
		ScrollPane.setContent(auhtorView);
	}
	
	public void clearAuthor(){
		auhtorView = new TableView<book_authors>();
		authorColCreate();
	}
	
	public void addToAuthor(int BookID, String AuthorLName, String AuthorFName){
		ObservableList<book_authors> Row = FXCollections.observableArrayList();
		Row.add(new book_authors(BookID, AuthorLName, AuthorFName));
		auhtorView.setItems(Row);
	}

	@SuppressWarnings("unchecked")
	private void loansColCreate() {
		TableColumn<book_loans, String> IDCol = new TableColumn<>("BookID");
		IDCol.setPrefWidth(50.0d); 
		IDCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("BookID"));
		
		TableColumn<book_loans, String> BIDCol = new TableColumn<>("BranchID");
		BIDCol.setPrefWidth(60.0d); 
		BIDCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("BranchID"));
		
		TableColumn<book_loans, String> CNoCol = new TableColumn<>("CardNo");
		CNoCol.setPrefWidth(50.0d); 
		CNoCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("CardNo"));
		
		TableColumn<book_loans, String> DOCol = new TableColumn<>("DateOut");
		DOCol.setPrefWidth(75.0d); 
		DOCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("DateOut"));
		
		TableColumn<book_loans, String> DDCol = new TableColumn<>("DueDate");
		DDCol.setPrefWidth(75.0d); 
		DDCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("DueDate"));
		
		TableColumn<book_loans, String> DRCol = new TableColumn<>("DateReturned");
		DRCol.setPrefWidth(100.0d); 
		DRCol.setCellValueFactory(new PropertyValueFactory<book_loans, String>("DateReturned"));
		
		loansView.getColumns().addAll(IDCol, BIDCol, CNoCol, DOCol, DDCol, DRCol);
	}
	
	public void switchToLoans(){
		ScrollPane.setContent(loansView);
	}
	
	public void clearLoans(){
		loansView = new TableView<book_loans>();
		loansColCreate();
	}
	
	public void addToLoans(int BookID, int BranchID, int CardNo, String DateOut, String DueDate, String DateReturned){
		ObservableList<book_loans> Row = FXCollections.observableArrayList();
		Row.add(new book_loans(BookID, BranchID, CardNo, DateOut, DueDate, DateReturned));
		loansView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void borrowColCreate() {
		TableColumn<borrower, String> CNoCol = new TableColumn<>("CardNo");
		CNoCol.setPrefWidth(50.0d); 
		CNoCol.setCellValueFactory(new PropertyValueFactory<borrower, String>("CardNo"));
		
		TableColumn<borrower, String> BLNCol = new TableColumn<>("BorrowerLName");
		BLNCol.setPrefWidth(100.0d); 
		BLNCol.setCellValueFactory(new PropertyValueFactory<borrower, String>("BorrowerLName"));
		
		TableColumn<borrower, String> BFNCol = new TableColumn<>("BorrowerFName");
		BFNCol.setPrefWidth(100.0d); 
		BFNCol.setCellValueFactory(new PropertyValueFactory<borrower, String>("BorrowerFName"));
		
		TableColumn<borrower, String> AdCol = new TableColumn<>("Address");
		AdCol.setPrefWidth(75.0d); 
		AdCol.setCellValueFactory(new PropertyValueFactory<borrower, String>("Address"));
		
		TableColumn<borrower, String> PhnCol = new TableColumn<>("Phone");
		PhnCol.setPrefWidth(75.0d); 
		PhnCol.setCellValueFactory(new PropertyValueFactory<borrower, String>("Phone"));
		
		borrowView.getColumns().addAll(CNoCol, BLNCol, BFNCol, AdCol, PhnCol);
	}
	
	public void switchToBorrow(){
		ScrollPane.setContent(borrowView);
	}
	
	public void clearBorrow(){
		borrowView = new TableView<borrower>();
		borrowColCreate();
	}
	
	public void addToBorrow(int CardNo, String BorrowLN, String BorrowFN, String Address, String Phone){
		ObservableList<borrower> Row = FXCollections.observableArrayList();
		Row.add(new borrower(CardNo, BorrowLN, BorrowFN, Address, Phone));
		borrowView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void libraryColCreate() {
		TableColumn<library_branch, String> BIDCol = new TableColumn<>("BranchID");
		BIDCol.setPrefWidth(125.0d); 
		BIDCol.setCellValueFactory(new PropertyValueFactory<library_branch, String>("BranchID"));
		
		TableColumn<library_branch, String> BCol = new TableColumn<>("BranchName");
		BCol.setPrefWidth(125.0d); 
		BCol.setCellValueFactory(new PropertyValueFactory<library_branch, String>("BranchName"));
		
		TableColumn<library_branch, String> BACol = new TableColumn<>("BranchAddress");
		BACol.setPrefWidth(150.0d); 
		BACol.setCellValueFactory(new PropertyValueFactory<library_branch, String>("BranchAddress"));
		
		branchView.getColumns().addAll(BIDCol, BCol, BACol);	
	}
	
	public void switchToLibrary(){
		ScrollPane.setContent(branchView);
	}
	
	public void clearLibrary(){
		branchView = new TableView<library_branch>();
		libraryColCreate();
	}
	
	public void addToLibrary(int BranchID, String BranchName, String BranchAddress){
		ObservableList<library_branch> Row = FXCollections.observableArrayList();
		Row.add(new library_branch(BranchID, BranchName, BranchAddress));
		branchView.setItems(Row);
	}
	
	@SuppressWarnings("unchecked")
	private void publisherColCreate() {
		TableColumn<publisher, String> PNCol = new TableColumn<>("PublisherName");
		PNCol.setPrefWidth(125.0d); 
		PNCol.setCellValueFactory(new PropertyValueFactory<publisher, String>("PublisherName"));
		
		TableColumn<publisher, String> AdCol = new TableColumn<>("Address");
		AdCol.setPrefWidth(125.0d); 
		AdCol.setCellValueFactory(new PropertyValueFactory<publisher, String>("Address"));
		
		TableColumn<publisher, String> PhnCol = new TableColumn<>("Phone");
		PhnCol.setPrefWidth(150.0d); 
		PhnCol.setCellValueFactory(new PropertyValueFactory<publisher, String>("Phone"));
		
		pubView.getColumns().addAll(PNCol, AdCol, PhnCol);
	}
	
	public void switchToPublisher(){
		ScrollPane.setContent(pubView);
	}
	
	public void clearPublisher(){
		pubView = new TableView<publisher>();
		publisherColCreate();
	}
	
	public void addToLibrary(String  PublisherName, String Address, int Phone){
		ObservableList<publisher> Row = FXCollections.observableArrayList();
		Row.add(new publisher(PublisherName, Address, Phone));
		pubView.setItems(Row);
	}

	private void initScrollPane() {
		ScrollPane.setContent(booksView);
	}

	public VBox getRightSide(){
		return right;
	}
}
