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

public class UpdateQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateQServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateQServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from UpdateQ.jsp page
		String qsn = request.getParameter("question");
		String op1 = request.getParameter("option1");
		String op2 = request.getParameter("option2");
		String op3 = request.getParameter("option3");
		String opc = request.getParameter("optionans");
		String subj = request.getParameter("subject");

		System.out.println(qsn + " " + op1 + " "+ op2 + " " + op3 + " " + opc + " " + subj);
		int qs = 4;
		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("StudentRegistrationServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("StudentRegistrationServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement1 = connection.prepareStatement(
					"update " + subj + " set question =? , option1=?, option2=?, option3=? , correctans=? where qno =?");
			preparedStatement1.setString(1, qsn);
			preparedStatement1.setString(2, op1);
			preparedStatement1.setString(3, op2);
			preparedStatement1.setString(4, op3);
			preparedStatement1.setString(5, opc);
			preparedStatement1.setInt(6, qs);
			// Execute queries
			preparedStatement1.executeUpdate();
			writer.println("Updated Successfully");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("HtmlQuestions.jsp");
			System.out.println("UpdateQServlet exits. Forwarded to HtmlQuestions.jsp ");
			requestDispatcher.include(request, response);
			connection.close();
			System.out.println("UpdateQServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
