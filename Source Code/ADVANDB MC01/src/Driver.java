import javafx.application.Application;
import javafx.stage.Stage;
import util.*;
import GUI.*;

public class Driver extends Application{
	
	public static void main(String[] args) {
		launch(args);
		
		Query qm = new Query();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RightSide right = new RightSide();
		LeftSide left = new LeftSide(right);
		StageMakerView view = new StageMakerView(primaryStage, left, right);
	}
}
