package util;

import java.util.*;

public class QueryManager {
	
	private Database db;
	private String[] optimizationList = {"Index", "View", "Temp. Table", "Join Tables"};
	//private ArrayList<String> optimizations;
	public static QueryManager qm = new QueryManager();
	
	public QueryManager() {
		db = Database.getInstance();
	}
	
	public void addOptimization(String type) {
		
		if (type.equalsIgnoreCase("Index")) {
			
			
		} else if (type.equalsIgnoreCase("View")) {
			
			
		} else if (type.equalsIgnoreCase("Temp. Table")) {
			
			
		} else if (type.equalsIgnoreCase("Join Tables")) {
			
			
		}
		
	}
	
	public void dropOptimization(String type) {
		
		
	}
	
	public String[] getOptimizationList() {
		return this.optimizationList;
	}
	
	public static QueryManager getInstance() {
		return qm;
	}
	
}
