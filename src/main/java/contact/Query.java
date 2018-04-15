package contact;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
import java.sql.Connection;


public class Query extends HttpServlet {
	
	private String query = null;
	private String search = null;
	private ResultSet resultSet;
	private Statement setupStatement = null;
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

    public Query() {}

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ConnectionManager newConnection = new ConnectionManager();
		Connection conn = newConnection.getConnection();
    	if (request.getParameter("jdbc_query") !=null) {
			try {
				setupStatement = conn.createStatement();
				query = "SELECT * FROM contacts WHERE firstName LIKE " + "\'" + search + "%\'" +
						" OR lastName LIKE " + "\'" + search + "%\'" +
						" OR street LIKE " + "\'" + search + "%\'" +
						" OR city LIKE " + "\'" + search + "%\'" +
						" OR state LIKE " + "\'" + search + "%\'" +
						" OR zip LIKE " + "\'" + search + "%\'" +
						" OR phone LIKE " + "\'" + search + "%\'" +
						" OR email LIKE " + "\'" + search + "%\'" +
						" ORDER BY lastName";
				resultSet = setupStatement.executeQuery(query);
				search = request.getParameter("search");
				if(search == "" || search == null || !resultSet.next()) {
					response.sendRedirect("index.jsp#modalNoRecords");
				} else {
					response.setContentType("text/html");
					out = response.getWriter();
					if (request.getParameter("jdbc_query") !=null) {
						out.println("<link rel='stylesheet' href='css/bootstrap.min.css' />");
						out.println("<table class='table table-hover'>");
						out.println("<tr><th>Name</th><th>Address</th><th>Phone Number</th><th>Email</th></tr>");
						while (resultSet.next()) {
							String firstName = resultSet.getString("firstName");
							firstName = highlight(firstName);
							String lastName = resultSet.getString("lastName");
							lastName = highlight(lastName);
							String fullName = lastName + ", " + firstName;
							String street = resultSet.getString("street");
							street = highlight(street);
							String city = resultSet.getString("city");
							city = highlight(city);
							String state = resultSet.getString("state");
							state = highlight(state);
							String zip = resultSet.getString("zip");
							zip = highlight(zip);
							String address = street + "\n" + city + "\n" + state + ", " + zip;
							String phone = resultSet.getString("phone");
							phone = highlight(phone);
							String email = resultSet.getString("email");
							email = highlight(email);
							out.println("<tr><td>" + fullName + "</td><td>" + address + "</td><td>" + phone + "</td><td>" + email + "</td></tr>");
						}
						out.println("</table>");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
	            try { 
	            	setupStatement.close(); 
	            } catch (SQLException e) { 
	            	e.printStackTrace(out); 
	            	System.out.println("SQLException: " + e.getMessage());
	    		    System.out.println("SQLState: " + e.getSQLState());
	    		    System.out.println("VendorError: " + e.getErrorCode());
	            }
	            try { 
	            	conn.close(); 
	            } catch (SQLException e) { 
	            	e.printStackTrace(out); 
	            	System.out.println("SQLException: " + e.getMessage());
	    		    System.out.println("SQLState: " + e.getSQLState());
	    		    System.out.println("VendorError: " + e.getErrorCode());
	            }
	        }
		}
    }
    
    public String highlight(String input) {
    	String inputLower = input.toLowerCase();
    	String searchLower = search.toLowerCase();
    	if(inputLower.contains(searchLower)) {
    		input = "<mark>" + input + "</mark>";
    	}
    	return input;
    }
		
}


