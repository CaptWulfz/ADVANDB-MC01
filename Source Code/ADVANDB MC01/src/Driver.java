import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import util.*;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.*;

public class Driver extends Application{
	
	private static String[] optimizationList = {"Index", "View", "Temp. Table", "Join Tables"};
	private static String[] queryList = 
		{"List all the books published by Huffington Post",
		 "List all publishers located in Los Angeles",
		 "List all books written/authored by Stonefeather",
		 "Count the total number of book that were on loan from each branch from 01/01/2006 to 31/12/2011",
		 "List all books that were borrowed more than once from the Silver Branch",
		 "List all borrowers who borrowed from the Silver Branch",
		 "List all book loans that were returned Exactly on the due date",
		 "List all publishers where their authored books are available in the branches located in New York"};
	
	public static void main(String[] args) {
		launch(args);
		
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
		LeftSide left = new LeftSide(right, optimizationList, queryList);
		StageMakerView view = new StageMakerView(primaryStage, left, right);
		
		/*
		ArrayList<String> COLEX = new ArrayList<String>();
        ArrayList<ObservableList<String>> ROWEX = new ArrayList<ObservableList<String>>();

        //COLLUM NAMES
        COLEX.add("NIGGA");
        COLEX.add("FAGGOT");
        COLEX.add("Makunouchi");
        COLEX.add("SENDO TAKESHI");
        //ROWS
        ObservableList<String> rowList1 = FXCollections.observableArrayList(); 
        rowList1.addAll("fucker", "muthat", "bitch", "Mashiba");
        ROWEX.add(rowList1);
        ObservableList<String> rowList2 = FXCollections.observableArrayList(); 
        rowList2.addAll("nuigger", "nuggets", "McDo ROckyRoad", "Sawamara");
        ROWEX.add(rowList2);
        ObservableList<String> rowList3 = FXCollections.observableArrayList(); 
        rowList3.addAll("nuigger", "nuggets", "McDo ROckyRoad", "AOKI");
        ROWEX.add(rowList3);
       // right.CreateTableViews(COLEX, ROWEX);
        */
	}
	
}
