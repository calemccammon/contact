package contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	private static final String DBNAME = System.getProperty("RDS_DB_NAME");
	private static final String USERNAME = System.getProperty("RDS_USERNAME");
	private static final String PASSWORD = System.getProperty("RDS_PASSWORD");
	private static final String HOSTNAME = System.getProperty("RDS_HOSTNAME");
	private static final String PORT = System.getProperty("RDS_PORT");
	private static final String JDBC_URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME + "?user=" + USERNAME + "&password=" + PASSWORD;
	private Connection conn = null;
	
	public ConnectionManager() {
		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			this.conn = DriverManager.getConnection(JDBC_URL);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		} catch (SQLException ex) {
		    // Handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	public void createTable() throws SQLException {
	    String sqlCreate = "CREATE TABLE IF NOT EXISTS contacts " + 
	        	"(id INTEGER NOT NULL AUTO_INCREMENT, " +
	        	"firstName VARCHAR(255), " +
	        	"lastName VARCHAR(255), " +
	        	"street VARCHAR(255), " +
	        	"city VARCHAR(255), " +
	        	"state VARCHAR(255), " +
	        	"zip VARCHAR(255), " +
	        	"phone VARCHAR(255), " +
	        	"email VARCHAR(255), " +
	        	"PRIMARY KEY (id))";
	    Statement stmt = this.conn.createStatement();
	    stmt.execute(sqlCreate);
	    stmt.close();
	}
}

