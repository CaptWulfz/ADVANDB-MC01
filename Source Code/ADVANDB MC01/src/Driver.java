import javafx.application.Application;
import javafx.stage.Stage;
import util.*;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.*;

public class Driver extends Application{
	
	public static void main(String[] args) {
		//launch(args);
		
		//testing
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<?>> result;
		int input;
		String query;
		do {
			System.out.print("\nTest ViewDB Queries (1-6; 0 to exit): ");
			input = sc.nextInt();
			result = Service.viewDBQ(input);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					for (int j = 0; j < result.get(i).size(); j++)
						System.out.print(result.get(i).get(j) + "\t");
					System.out.println("");
				}
			}
		} while(input != 0);
		
		do {
			System.out.print("\nTest Preset Queries (1-8; 0 to exit): ");
			input = sc.nextInt();
			result = Service.presetQ(input);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					for (int j = 0; j < result.get(i).size(); j++)
						System.out.print(result.get(i).get(j) + "\t");
					System.out.println("");
				}
			}
		} while(input != 0);
		
		query = sc.nextLine();
		do {
			System.out.print("\nTest New Query (string; \"quit\" to exit): ");
			query = sc.nextLine();
			result = Service.newQ(query);
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					for (int j = 0; j < result.get(i).size(); j++)
						System.out.print(result.get(i).get(j) + "\t");
					System.out.println("");
				}
			}
		} while(!query.equalsIgnoreCase("quit"));
		
		sc.close();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		RightSide right = new RightSide();
		LeftSide left = new LeftSide(right);
		StageMakerView view = new StageMakerView(primaryStage, left, right);
	}
	
}
