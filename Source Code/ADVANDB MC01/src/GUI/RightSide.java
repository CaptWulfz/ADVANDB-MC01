package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RightSide {
	
	private ScrollPane ScrollPane = new ScrollPane();
	private TableView results = new TableView();
	private VBox right = new VBox();
	
	@SuppressWarnings("static-access")
	public RightSide(){
		initScrollPane();
		right.getChildren().addAll(ScrollPane);
		right.setPadding(new Insets(10,10,10,10));
		GridPane.setConstraints(right, 1, 0);
	}
	
	private void initScrollPane() {
		ScrollPane.setContent(results);
	}

	public VBox getRightSide(){
		return right;
	}
}
