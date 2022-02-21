package in.amazon.utils;

import org.testng.annotations.DataProvider;

import CommonLibs.utils.DataBaseDriver;

public class TestDataSourceDB {
	
	DataBaseDriver dbDriver;
	
	@DataProvider
	public Object[][] getDataForSearchProduct() throws Exception {
		
		dbDriver = new DataBaseDriver();
		
		String server = "localhost";
		
		int portNumber = 3306;
		
		String database = "Test";
		
		String username = "root";
				
		String password = "admin@1234";
		
		String query = "Select * FROM customer";
		
		dbDriver.opneConnection(server, database, portNumber, username, password);
		
		Object[][] data =  dbDriver.executeSelectQuery(query);
		
		return data;
		
	}

}
