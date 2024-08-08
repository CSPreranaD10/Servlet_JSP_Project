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


public class DeleteSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteSubjectServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentLoginServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from DeleteSubject.jsp page
		String subj = request.getParameter("subject");
		

		System.out.println("From DeleteSubject page /subject : " + subj );

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DeleteSubjectServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("DeleteSubjectServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection
					.prepareStatement("drop table " + subj);

			PreparedStatement preparedStatement1 = connection
					.prepareStatement("delete from subject where subname =?");
			preparedStatement1.setString(1, subj);
			
			// Execute queries
			preparedStatement.executeUpdate();
			preparedStatement1.executeUpdate();
			writer.println("Question Paper deleted ");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminMain.html");
					System.out.println("DeleteSubjectServlet exits. Forwarded to AdminMain.html");
					requestDispatcher.include(request, response);
			
			connection.close();
			System.out.println("DeleteSubjectServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
