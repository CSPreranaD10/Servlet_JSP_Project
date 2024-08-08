package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteQuestionServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("UpdateQServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from UpdateQ.jsp page
		
		String subj = "javaexam";

		int qs = 7;
		System.out.println("qs is "  + qs);
		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DeleteQuestionServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("DeleteQuestionServlet : Connection created");

			
			// Create statement
			PreparedStatement preparedStatement1 = connection.prepareStatement(
					"delete from " + subj + " where qno =?");
			
			preparedStatement1.setInt(1, qs);
			// Execute queries
			preparedStatement1.executeUpdate();
			writer.println("Question Deleted");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("HtmlQuestions.jsp");
			System.out.println("DeleteQuestionServlet exits. Forwarded to HtmlQuestions.jsp ");
			requestDispatcher.include(request, response);
			connection.close();
			System.out.println("DeleteQuestionServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
