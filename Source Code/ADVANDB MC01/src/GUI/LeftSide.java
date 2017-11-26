package GUI;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Database;
import util.Query;
import util.Service;
import GUI.StageMakerView;

public class LeftSide {
	
	private ChoiceBox<String> QueryChoices = new ChoiceBox<String>();
	private Button QueryCreate = new Button("CREATE");
	private Button generate = new Button("GENERATE"); 
	private Button addNewPlus = new Button("ADD NEW +");
	private Label query = new Label("QUERY");
	private Label optimize = new Label("OPTIMIZE");
	private ScrollPane optimizeScroll = new ScrollPane();
	private String toBeCreated = new String(); 
	private VBox buttonBox = new VBox();
	private VBox left = new VBox();
	
	private String[] qList;
	private RightSide rightSide;
	private StageMakerView view;
	
	public LeftSide(RightSide rightSide, String[] qList) {
		QueryChoices.getItems().addAll(qList);
		QueryChoices.getSelectionModel().select(0);
		QueryChoices.setMaxWidth(100);
		initButtons();
		initScrollPane();
		initLabels();
		left.getChildren().addAll(query, QueryChoices, QueryCreate, optimize, optimizeScroll, generate);
		GridPane.setConstraints(left, 0, 0);
		left.setPadding(new Insets(10,10,10,10));
		left.setMaxHeight(Double.MAX_VALUE);
		left.setSpacing(10);
		this.rightSide = rightSide;
		this.qList = qList;
	}

	private void initButtons() {
		//ViewDB button
		
		//QueryCreate button
		QueryCreate.setAlignment(Pos.CENTER);
		QueryCreate.setStyle("-fx-focus-color: transparent; "
				            + "-fx-base: Red; "
				            + "-fx-background-radius: 0%;"
				            + "-fx-font-weight: bold");
		QueryCreate.setPadding(new Insets(5));
		QueryCreate.setMaxWidth(Double.MAX_VALUE);
		QueryCreate.setOnAction(e ->  {
			toBeCreated = QueryChoices.getValue();
		});
		
		generate.setAlignment(Pos.CENTER);
		generate.setStyle("-fx-focus-color: transparent; "
			            + "-fx-base: Red; "
			            + "-fx-background-radius: 0%;"
			            + "-fx-font-weight: bold");
		generate.setPadding(new Insets(5));
		generate.setMaxWidth(Double.MAX_VALUE);
		generate.setOnAction(e ->  {
			Service.executePQuery(QueryChoices.getSelectionModel().getSelectedIndex() + 1);
			this.rightSide.CreateTableViews(Service.getCOLEX(), Service.getROWEX());
			//XXX FINISH THIS BITCH
			this.rightSide.changeTime(Service.getTime());
			System.out.println(Service.getTime());
		});
		
		addNewPlus.setAlignment(Pos.CENTER);
		addNewPlus.setStyle("-fx-focus-color: transparent; "
						+ "-fx-base: grey; "
			            + "-fx-background-radius: 0%;"
			            + "-fx-font-weight: bold");
		addNewPlus.setPadding(new Insets(5));
		addNewPlus.setMaxWidth(Double.MAX_VALUE);
		addNewPlus.setOnAction(e ->  {
			OptimizeBox();
		});
	}
	
	private void initScrollPane() {
		optimizeScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		optimizeScroll.setMinHeight(195);
		optimizeScroll.setMaxWidth(125);
		optimizeScroll.setMinWidth(125);
		buttonBox.getChildren().addAll(addNewPlus);
		optimizeScroll.setContent(buttonBox);
	}
	
	private void initLabels(){
		query.setFont(Font.font("Arial", FontWeight.BLACK, 17));
		query.setPadding(new Insets(5));
		query.setAlignment(Pos.CENTER);
		query.setMaxWidth(Double.MAX_VALUE);
		
		optimize.setFont(Font.font("Arial", FontWeight.BLACK, 17));
		optimize.setPadding(new Insets(5));
		optimize.setAlignment(Pos.CENTER);
		optimize.setMaxWidth(Double.MAX_VALUE);
	}
	public void addNewOpt(String indexName, String table){
		String name = indexName + " ON " + table;
		Button button = new Button(name);
		button.setAlignment(Pos.CENTER);
		button.setStyle("-fx-focus-color: transparent; "
							+ "-fx-base: grey; "
				            + "-fx-background-radius: 0%;"
				            + "-fx-font-weight: bold");
		button.setPadding(new Insets(5));
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e ->  {
			//enter shit here
			buttonBox.getChildren().remove(button);
			Service.dropIndex(indexName, table);
		});
		buttonBox.getChildren().addAll(button);
	}
	
	private ScrollPane descScroll = new ScrollPane();
	private String choice;
	private Button indexButton = new Button("Index");
	private Button viewButton = new Button("View");
	private Button cancelButton = new Button("Cancel");
	private Button okButton = new Button("Ok");
	//XXX private TextField ViewQueryField = new TextField();
	private TextField IndexQueryField = new TextField();
	
	public void OptimizeBox() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); 
		window.setTitle("OPTIMIZE");
		window.setMinWidth(285);
		window.setResizable(false);
		
		//XXX ViewQueryField.setMaxWidth(Double.MAX_VALUE);
		
		Label label = new Label();
		label.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		label.setText("TYPE");
		
		initOptButtons();
		descScroll.setContent(new Label("            Choose an Option"));
		
		VBox VIButtons = new VBox();
		VIButtons.setAlignment(Pos.CENTER);
		VIButtons.getChildren().addAll(indexButton, viewButton);
		
		cancelButton.setOnAction(e -> window.close());
		okButton.setOnAction(e -> {
			String indexName = IndexQueryField.getText(),
				   table = TableBox.getValue();
			if (!IndexQueryField.getText().equals("")) {
					Service.createIndex(IndexQueryField.getText(), TableBox.getValue(), columnBox.getValue());
					addNewOpt(indexName, table);
					IndexQueryField.clear();
			}
			window.close();
		});
		
		HBox endButtons = new HBox();
		endButtons.setAlignment(Pos.CENTER);
		endButtons.getChildren().addAll(cancelButton, okButton);
		endButtons.setPadding(new Insets(10,10,10,10));
		
		descScroll.setMinHeight(120);
		descScroll.setMinWidth(210);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, VIButtons, descScroll,  endButtons);
		layout.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private HBox descViewH = new HBox();
	private VBox descViewV = new VBox();
	private ChoiceBox<String> TableBox = new ChoiceBox<String>();
	private ChoiceBox<String> columnBox = new ChoiceBox<String>();
	//XXX
	private Button ViewButton = new Button("ADD VIEWS");
	
	private void initOptButtons(){
		
		if (!TableBox.getItems().isEmpty())
			TableBox.getItems().clear();
		
		//XXX
		ViewButton.setMaxWidth(Double.MAX_VALUE);
		ViewButton.setOnAction(e -> {
			if(ViewButton.getText() == "ADD VIEWS"){
				ViewButton.setText("DISCARD VIEWS");
				Service.executeViewQ();
			}
			else {
				ViewButton.setText("ADD VIEWS");
				Service.executeDropQ();
			}
		});
		
		TableBox.getItems().addAll("book", "book_authors", "book_loans", "borrower", "library_branch", "publisher");
		TableBox.setMaxWidth(Double.MAX_VALUE);
		columnBox.setMaxWidth(Double.MAX_VALUE);
		Button indexName = new Button("Index Name");
		indexName.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: grey; "
    			+ "-fx-font-weight: bold");
		indexName.setOnAction(e -> {
			choice = "Index : Index Name";
			descViewV.getChildren().clear();
			descViewV.getChildren().addAll(descViewH, IndexQueryField);
			descScroll.setContent(descViewV);
			//TextBox
		});
		Button table = new Button("Table");
		table.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: grey; "
    			+ "-fx-font-weight: bold"); 
		table.setOnAction(e -> {
			
			choice = "Index : Table";
			descViewV.getChildren().clear();
			descViewV.getChildren().addAll(descViewH, TableBox);
			descScroll.setContent(descViewV);
			//XXX
		});
		Button column  = new Button("Column ");
		column.setStyle("-fx-focus-color: transparent; "
				+ "-fx-base: grey; "
    			+ "-fx-font-weight: bold");
		column.setOnAction(e -> {
			ArrayList<String> columns = new ArrayList<String>();
			
			choice = "Index : Column";
			descViewV.getChildren().clear();
			descViewV.getChildren().addAll(descViewH, columnBox);
			descScroll.setContent(descViewV);
			
			int tableIndex = TableBox.getSelectionModel().getSelectedIndex();
			
			if (tableIndex == 0) {
				columns.add("BookID");
				columns.add("Title");
				columns.add("PublisherName");
			} else if (tableIndex == 1) {
				columns.add("BookID");
				columns.add("AuthorLastName");
				columns.add("AuthorFirstName");
			} else if (tableIndex == 2) {
				columns.add("BookID");
				columns.add("BranchID");
				columns.add("CardNo");
				columns.add("DateOut");
				columns.add("DueDate");
				columns.add("DateReturned");
			} else if (tableIndex == 3) {
				columns.add("CardNo");
				columns.add("BorrowerLName");
				columns.add("BorrowerFName");
				columns.add("Address");
				columns.add("Phone");
			} else if (tableIndex == 4) {
				columns.add("BranchID");
				columns.add("BranchName");
				columns.add("BranchAddress");
			} else if (tableIndex == 5) {
				columns.add("PublisherName");
				columns.add("Address");
				columns.add("Phone");
			}
			
			if (!columnBox.getItems().isEmpty())
				columnBox.getItems().clear();
			
			columnBox.getItems().addAll(columns);
			//XXX
		});
		
		//XXX Label labelEQ = new Label();
		//XXX labelEQ.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		//XXX labelEQ.setText("Enter Query:");
		
		descViewH.setPadding(new Insets(10,10,10,10));
		if (descViewH.getChildren().isEmpty())
			descViewH.getChildren().addAll(indexName, table, column);
		
		indexButton.setPadding(new Insets(5));
		indexButton.setMaxWidth(Double.MAX_VALUE);
		indexButton.setOnAction(e -> {
				choice = "Index";
				descScroll.setContent(descViewH);
			});
		indexButton.setStyle("-fx-focus-color: transparent; "
	            			+ "-fx-background-radius: 0%;"
	            			+ "-fx-font-weight: bold");
		
		viewButton.setPadding(new Insets(5));
		viewButton.setMaxWidth(Double.MAX_VALUE);
		viewButton.setOnAction(e -> {
				choice = "View";
				descViewV.getChildren().clear();
				//XXX descViewV.getChildren().addAll(labelEQ, ViewQueryField);
				//XXX
				descViewV.getChildren().addAll(ViewButton);
				descScroll.setContent(descViewV);
			});
		viewButton.setStyle("-fx-focus-color: transparent; "
    					  + "-fx-background-radius: 0%;"
    					  + "-fx-font-weight: bold");
		
		cancelButton.setMaxWidth(Double.MAX_VALUE);
		
		okButton.setMaxWidth(Double.MAX_VALUE);
		okButton.setOnAction( e -> {
			//TODO FINISH THE TEXTBOX SHIT
			
		});
	}
	
	public void addToQueryChoices(String choice){
		QueryChoices.getItems().add(choice);
	}
	
	public VBox getLeftSide(){
		return left;
	}

}
