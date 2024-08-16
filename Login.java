package Basics;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
     // JDBC driver name and database URL
     String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
     String DB_URL = "jdbc:mysql://127.0.0.1:3306/login";

    //  Database credentials
    String USER = "root";
    String PASS = "root@1234";
    
    String USERNAME = request.getParameter("USERNAME");
    int PASSWORD = Integer.parseInt(request.getParameter("PASSWORD"));

    // Set response content type
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();     
    try {
          // Register JDBC driver
          Class.forName(JDBC_DRIVER);  
          Connection con = DriverManager.getConnection(DB_URL, USER, PASS);  
          
          // Prepare SQL query
          PreparedStatement stmt = con.prepareStatement("INSERT INTO details (USERNAME, PASSWORD) VALUES (?, ?)");
          stmt.setString(1, USERNAME);
          stmt.setInt(2, PASSWORD);
          
          // Execute SQL query
          int code = stmt.executeUpdate();  
          out.print("<h1>Updated Successfully</h1>");
          
          // Close resources
          stmt.close();
          con.close();
      } catch (Exception e) {
          e.printStackTrace();
          out.print("<h1>Error occurred: " + e.getMessage() + "</h1>");
      }
  }
}