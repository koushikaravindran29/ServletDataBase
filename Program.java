package Basics;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class Program extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		   
		      // JDBC driver name and database URL
		       String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		       String DB_URL="jdbc:mysql://127.0.0.1:3306/Studentdataaccess";

		      //  Database credentials
		      String USER = "root";
		      String PASS = "root@1234";
		      
		      int ID=Integer.parseInt(request.getParameter("ID"));
		      int AGE=Integer.parseInt(request.getParameter("AGE"));
		      String FNAME=request.getParameter("FNAME");
		      String LNAME=request.getParameter("LNAME");
		         
		      // Set response content type
		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();     
		      try {
		    	    // Register JDBC driver
		    	    Class.forName(JDBC_DRIVER);  
		    	    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);  
		    	    
		    	    // Execute SQL query
		    	    PreparedStatement stmt = con.prepareStatement("INSERT INTO Detailofs (ID, FNAME, LNAME, AGE) VALUES (?, ?, ?, ?)");
		    	    stmt.setInt(1, ID);
		    	    stmt.setString(2, FNAME);
		    	    stmt.setString(3, LNAME);
		    	    stmt.setInt(4, AGE);
		    	    
		    	    int code = stmt.executeUpdate();  
		    	    out.print("<h1>Updated Successfully</h1>");
		    	    
		    	    // Close resources
		    	    stmt.close();
		    	    con.close();
		    	} catch (Exception e) {
		    	    e.printStackTrace();
		    	   // out.print("<h1>Error occurred: " + e.getMessage() + "</h1>");
		    	}
	}
}