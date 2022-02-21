package CommonLibs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class DataBaseDriver {
	
	private Connection connection; 
	
	public void opneConnection(String server, String database, int portNumber, String username, String password ) throws Exception {
		
		String connectionString = String.format("jdbc:mysql://%s:%d/%s", server, portNumber, database);
		
		connection = DriverManager.getConnection(connectionString);  //opening a connection from DriverManager class
		
	}
	
	public void closeConnection() throws Exception {
		
		connection.close();    //closing a connection
	}
	
	public Object[][] executeSelectQuery(String query) throws Exception	{ 
		
		Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet resultSet = stmt.executeQuery(query);
		
		resultSet.last();
		
		Object[][] data;
		
		int rowCount = resultSet.getRow();
		int cellCount = resultSet.getMetaData().getColumnCount();
		
		data = new Object [rowCount][cellCount];
		
		resultSet.first();
		
		for(int row = 1; row <= rowCount; row++) {
			for (int cell =1; cell <= cellCount; cell ++) {
				data[row-1][cell-1] = resultSet.getString(cell);
			}
			resultSet.next();
		}
		
		return data;
	}
}





