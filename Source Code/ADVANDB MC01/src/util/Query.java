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
		//result(0) = colLabels, result(1+) = rows
		//colTypes must be compared against java.sql.Types
		ArrayList<ArrayList<?>> result = new ArrayList<ArrayList<?>>();
		ArrayList<String> colLabels = new ArrayList<String>();
		ArrayList<Integer> colTypes = new ArrayList<Integer>();
		
		//get rs and rsmd
		ResultSet rs = Database.getInstance().query(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		
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
		Database.getInstance().queryClose();
		
		return result;
	}
}
