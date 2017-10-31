package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Query {
	private static Query instance = new Query();
	
	public Query() {
		
	}
	
	public static Query getInstance(){
		return instance;
	}
	
	public ArrayList<ArrayList<?>> getRS(String query) throws SQLException{
		//result(0) = execTime, result(1) = colLabels, result(2+) = rows
		//colTypes must be compared against java.sql.Types
		
		String setProfileQuery = "set profiling = 1;";
		String profileQuery = "show profiles;";
		
		Database.getInstance().executeQuery(setProfileQuery);
		
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		ArrayList<String> colLabels = new ArrayList<String>();
		ArrayList<String> execTime = new ArrayList<String>();
		ArrayList<Integer> colTypes = new ArrayList<Integer>();
		
		//get rs, rsmd, and exec time
		long start = System.nanoTime();
		ResultSet rs = Database.getInstance().query(query);
		long end = System.nanoTime();
		ResultSetMetaData rsmd = rs.getMetaData();
		//execTime.add(end - start);
		result.add(null);
		
		//get column names
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
			colLabels.add(rsmd.getColumnLabel(i));
		result.add(colLabels);
		
		//get column types, do not add to result
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
			colTypes.add(rsmd.getColumnType(i));
		
		//get rows
		while(rs.next()){
			ArrayList<Object> row = new ArrayList<Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (colTypes.get(i - 1) == Types.VARCHAR)
					row.add(rs.getString(i));
				else
					row.add(rs.getInt(i));
			}
			result.add(row);
		}
		
		//Get profile time
		rs = Database.getInstance().query(profileQuery);
		rsmd = rs.getMetaData();
		// Initialize the first value in the execTime Array List
		execTime.add(null);
		while(rs.next()) {
				String time = String.format("%f", rs.getDouble(2));
				//System.out.println(rs.getString(3));
				
				//Use .set to replace the first value each time
				execTime.set(0, time);
		}
		result.set(0, execTime);
		
		Database.getInstance().queryClose();
		
		return result;
	}
	
	public void executeQuery(String query) {
		Database.getInstance().executeQuery(query);
	}
}
