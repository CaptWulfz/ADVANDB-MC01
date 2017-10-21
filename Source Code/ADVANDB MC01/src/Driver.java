import javafx.application.Application;
import javafx.stage.Stage;
import util.*;

import java.sql.SQLException;
import java.util.ArrayList;

import GUI.*;

public class Driver extends Application{
	
	public static void main(String[] args) {
		//launch(args);
		
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		try {
			result = Query.getRS("select * from book;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).size(); j++) {
				if (j == 0)
					System.out.print(result.get(i).get(j) + "\t");
				else
					System.out.print(result.get(i).get(j) + "\t\t\t\t\t");
			}
				
			System.out.println();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RightSide right = new RightSide();
		LeftSide left = new LeftSide(right);
		StageMakerView view = new StageMakerView(primaryStage, left, right);
	}
}
