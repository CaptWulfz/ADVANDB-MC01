package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {
	private static Query instance = new Query();
	
	public Query() {
		
	}
	
	public static Query getInstance(){
		return instance;
	}
	
	public ArrayList<ArrayList<?>> getRS(String query) throws SQLException{
		//result (0) = colLabels, result (1) = colTypes, result(2+) = rows
		//colTypes must be compared against java.sql.Types
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		ArrayList<String> colLabels = new ArrayList<String>();
		ArrayList<Object> colTypes = new ArrayList<Object>();
		
		//get rs and rsmd
		ResultSet rs = Database.getInstance().query(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		//get column names
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
			colLabels.add(rsmd.getColumnLabel(i));
		result.add(colLabels);
		
		//get column types
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
			colTypes.add(rsmd.getColumnType(i));
		result.add(colTypes);
		
		
		
		return result;
	}
}
