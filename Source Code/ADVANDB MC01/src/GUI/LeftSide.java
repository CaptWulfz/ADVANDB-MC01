package GUI;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Service;

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
	private String[] opList;
	private RightSide rightSide;
	
	public LeftSide(RightSide rightSide, String[] opList, String[] queryList) {
		//QueryChoices.getItems().add("Empty");
		QueryChoices.getItems().addAll(queryList);
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
		this.opList = opList;
		this.rightSide = rightSide;
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
			//TODO
			Service.executePQuery(QueryChoices.getSelectionModel().getSelectedIndex() + 1);
			this.rightSide.CreateTableViews(Service.getCOLEX(), Service.getROWEX());
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
		optimizeScroll.setMinHeight(190);
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
	public void addNewOpt(String name){
		name = name + "-";
		Button button = new Button(name);
		button.setAlignment(Pos.CENTER);
		button.setStyle("-fx-focus-color: transparent; "
							+ "-fx-base: grey; "
				            + "-fx-background-radius: 0%;"
				            + "-fx-font-weight: bold");
		button.setPadding(new Insets(5));
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e ->  {
			buttonBox.getChildren().remove(button);
		});
		buttonBox.getChildren().addAll(button);
	}
	

	private ChoiceBox<String> OptimizeChoices = new ChoiceBox<String>();
	private ScrollPane descScroll = new ScrollPane();
	private Label desc = new Label("Description");
	
	public void OptimizeBox() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); 
		window.setTitle("OPTIMIZE");
		window.setMinWidth(250);
		window.setResizable(false);
		
		if (!OptimizeChoices.getItems().isEmpty())
			OptimizeChoices.getItems().clear();
		
		OptimizeChoices.getItems().addAll(opList);
		
		OptimizeChoices.setMaxWidth(Double.MAX_VALUE);
		OptimizeChoices.getSelectionModel().select(0);
		
		Label label = new Label();
		label.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		label.setText("TYPE");
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> window.close());
		cancelButton.setMaxWidth(Double.MAX_VALUE);
		
		Button okButton = new Button("Ok");
		okButton.setOnAction(e -> {
				addNewOpt(OptimizeChoices.getValue());
				window.close();
			});
		okButton.setMaxWidth(Double.MAX_VALUE);
		
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(cancelButton, okButton);
		
		VBox descBox = new VBox();
		descBox.getChildren().addAll(desc);
		descScroll.setContent(descBox);
		descScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, OptimizeChoices, descScroll,  buttons);
		layout.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void switchTablesBox(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Choose Table To Display");
		window.setMinWidth(250);
		window.setResizable(false);
		
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll();
		layout.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public void addToQueryChoices(String choice){
		QueryChoices.getItems().add(choice);
	}
	
	public VBox getLeftSide(){
		return left;
	}

}
