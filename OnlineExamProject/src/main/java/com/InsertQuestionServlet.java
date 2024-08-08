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

public class InsertQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertQuestionServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("InsertQuestionServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from InsertQuestions.html page
		String subject = request.getParameter("subject");
		String question = request.getParameter("question");
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String optionans = request.getParameter("optionans");
		System.out.println("From InsertQuestions page : \n subject : " + subject + "\n question : " + question
				+ "\n option1 : " + option1 + "\n option2 : " + option2 + "\n option3 : " + option3 + "\n corrctans : "
				+ optionans);

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("CreateTestServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("CreateTestServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection.prepareStatement(
					"insert into " + subject + "(question, option1, option2, option3, correctans) values(?,?,?,?,?)");
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, option1);
			preparedStatement.setString(3, option2);
			preparedStatement.setString(4, option3);
			preparedStatement.setString(5, optionans);

			// Execute queries
			preparedStatement.executeUpdate();

			writer.println("Question inserted Successfully");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminMain.html");
			System.out.println("InsertQuestionServlet exits. Forwarded to AdminMain.html ");
			requestDispatcher.include(request, response);
			connection.close();
 			System.out.println("InsertQuestionServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
