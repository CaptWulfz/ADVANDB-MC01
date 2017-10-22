package GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RightSide {
	
	private ScrollPane ScrollPane = new ScrollPane();
	private TableView<ObservableList<Object>> Table = new TableView<>();; 
	private VBox right = new VBox();
	
	public RightSide(){
		initScrollPane();
		right.getChildren().addAll(ScrollPane);
		right.setPadding(new Insets(10,10,10,10));
		GridPane.setConstraints(right, 1, 0);
	}
	
	public void CreateTableViews(ArrayList<String> ColList, ArrayList<ObservableList<Object>> Rows) {
		Table = new TableView<>();
		//COL
		for (int i = 0; i < ColList.size(); i++) {
		    int finalIdx = i;
		    TableColumn<ObservableList<Object>, Object> column = new TableColumn<>(ColList.get(i));
		    if(ColList.size()<5)
		    	column.setPrefWidth(850/ColList.size()); 
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
		ScrollPane.setMaxWidth(825);
		ScrollPane.setMinWidth(825);
	}

	public VBox getRightSide(){
		return right;
	}
}
