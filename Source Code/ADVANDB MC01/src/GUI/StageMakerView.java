package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StageMakerView {
	
	protected StackPane MainPane = new StackPane();
	protected Scene MainScene;
	protected GridPane grid = new GridPane();

	public StageMakerView(Stage primaryStage, LeftSide left, RightSide right) throws Exception{
		primaryStage.setTitle("MCO1");
		primaryStage.setResizable(false);
		
		grid.setPrefSize(575, 350); 
		grid.getChildren().addAll(left.getLeftSide(), right.getRightSide());
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(8);
		
		MainPane.getChildren().addAll(grid);
		MainPane.setAlignment(Pos.CENTER);
		MainScene = new Scene (MainPane, 575, 350);
		
		primaryStage.setScene(MainScene);
		primaryStage.show();
	}
}
