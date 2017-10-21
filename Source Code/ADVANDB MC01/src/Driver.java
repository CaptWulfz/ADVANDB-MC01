import javafx.application.Application;
import javafx.stage.Stage;
import GUI.*;
import querymanager.*;

public class Driver extends Application{
	
	public static void main(String[] args) {
		launch(args);
		
		QueryManager qm = new QueryManager();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RightSide right = new RightSide();
		LeftSide left = new LeftSide(right);
		StageMakerView view = new StageMakerView(primaryStage, left, right);
	}
}
