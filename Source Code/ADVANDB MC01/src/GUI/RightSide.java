package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RightSide {
	
	private ScrollPane ScrollPane = new ScrollPane();
	private TableView<ObservableList<Object>> Table = new TableView<>();; 
	private VBox right = new VBox();

	private Label time = new Label("Time: 0 sec");
	
	public RightSide(){
		initScrollPane();
		right.getChildren().addAll(ScrollPane, time);
		right.setPadding(new Insets(10,10,10,10));
		GridPane.setConstraints(right, 1, 0);
		
		time.setPadding(new Insets(10,10,10,10));
		time.setFont(Font.font("Arial", FontWeight.BLACK, 15));
	}
	
	public void CreateTableViews(ArrayList<String> arrayList, ArrayList<ObservableList<Object>> Rows) {
		Table = new TableView<>();
		//COL
		for (int i = 0; i < arrayList.size(); i++) {
		    int finalIdx = i;
		    TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(arrayList.get(i));
		    if(arrayList.size()<5)
		    	column.setPrefWidth(800/arrayList.size()); 
		    else column.setPrefWidth(200); 
		    column.setCellValueFactory(param ->new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));
		    Table.getColumns().add(column);
		}
		//ROW
		for (int i = 0; i < Rows.size(); i++) 
			Table.getItems().add(FXCollections.observableArrayList(Rows.get(i)));
		ScrollPane.setContent(Table);
	}

	private void initScrollPane() {
		ScrollPane.setContent(Table);
		ScrollPane.setMaxWidth(800);
		ScrollPane.setMinWidth(800);
	}

	public VBox getRightSide(){
		return right;
	}
	
	public void changeTime(String text){
		time.setText("Time: " + text + " secs");
	}
	
	public String getTime() {
		return time.getText();
	}
}
