package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CreateTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CreateTestServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("CreateTestServlet begins");
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// Creating a session of admin
		HttpSession session = request.getSession();

		// getting parameter from CreateTest.html page
		String sname = request.getParameter("Subject");
		String sid = request.getParameter("sid");
		String scourse = request.getParameter("scourse");
		
		System.out.println("From CreateTest page /nsubject : "+ sname+"\nsubject id : " +sid + "\n subject course : " + scourse);
		try {
			//getting attribute from session
			String name = (String) session.getAttribute("aname");
			
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("CreateTestServlet : Driver class registered");

						// Create connection
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
						System.out.println("CreateTestServlet : Connection created");

						// Create statement
						Statement statement = connection.createStatement();
					String sql = "CREATE TABLE "+sname +
			                "(qno int primary key auto_increment, " +
			                " question varchar(100), " + 
			                " option1 varchar(100), " + 
			                " option2 varchar(100), " +
			                " option3 varchar(100), " +  
			                " correctans varchar(10) )"; 
					
					PreparedStatement preparedStatement =
				 			  connection.prepareStatement("insert into subject values(?,?,?,?)");
				 			  preparedStatement.setString(1, sid);
				 			  preparedStatement.setString(2, name);
				 			  preparedStatement.setString(3, scourse);
				 			  preparedStatement.setString(4, sname);
				 			  
				 				// Execute queries
			       statement.executeUpdate(sql);
		 			  preparedStatement.executeUpdate();
			       
		 			 writer.print(sname + "Test Created");
		 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("InsertQuestions.jsp");
		 			System.out.println("CreateTestServlet exits. Forwarded to InsertQuestions.jsp ");
		 			requestDispatcher.include(request, response);
		 			connection.close();
		 			System.out.println("CreateTestServlet : Connection closed");
			       
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
