package contact;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("jdbc_insert") !=null) {
			String firstName = request.getParameter("inputFirstName");
			String lastName = request.getParameter("inputLastName");
			String street = request.getParameter("inputStreet");
			String city = request.getParameter("inputCity");
			String state = request.getParameter("inputState");
			String zip = request.getParameter("inputZip");
			String phone = request.getParameter("inputPhone");
			String email = request.getParameter("inputEmail");
			Contact newContact = new Contact(firstName, lastName, street, city, state, zip, phone, email);
	        Connection connect = null;
	        PreparedStatement preparedStatement = null;
	        try {
	        	response.sendRedirect("index.jsp#modalSuccess");
		        ConnectionManager connectionManager = new ConnectionManager();
	        	connect = connectionManager.getConnection();
	        	connectionManager.createTable();
		        preparedStatement = connect.prepareStatement("INSERT INTO contacts " + "(firstName, lastName, street, city, state, zip, phone, email) VALUES" + 
		        		"(?,?,?,?,?,?,?,?)");
		        preparedStatement.setString(1, newContact.getFirstName());	
		        preparedStatement.setString(2, newContact.getLastName());
		        preparedStatement.setString(3, newContact.getStreet());
		        preparedStatement.setString(4, newContact.getCity());
		        preparedStatement.setString(5, newContact.getState());
		        preparedStatement.setString(6, newContact.getZip());
		        preparedStatement.setString(7, newContact.getPhone());
		        preparedStatement.setString(8, newContact.getEmail());
		        preparedStatement.executeUpdate();
		        preparedStatement.close();
		        connect.close();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: " + e.getSQLState());
			    System.out.println("VendorError: " + e.getErrorCode());
				e.printStackTrace();
			}
		}
	}

}
