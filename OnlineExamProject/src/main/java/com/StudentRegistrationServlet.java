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

public class StudentRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public StudentRegistrationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentRegistrationServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from StudentRegistration.html page
		String name = request.getParameter("sname");
		String rollno = request.getParameter("srollno");
		String email = request.getParameter("semail");
		String username = request.getParameter("susername");
		String password = request.getParameter("spassword");
		String dob = request.getParameter("sbdate");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String mnum = request.getParameter("smnumber");
		String program = request.getParameter("course");

		System.out.println("From StudentRegistration page : \n name : " + name + "\nrollno : " + rollno + "\nemail : "
				+ email + "\nusername : " + username);
		System.out.println("\npassword : " + password + "\nDOB : " + dob + "\nState : " + state + "\nCity : " + city
				+ "\n Mobile : " + mnum + "\nProgram :" + program);

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("StudentRegistrationServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("StudentRegistrationServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into candidates values(?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, rollno);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, username);
			preparedStatement.setString(5, password);
			preparedStatement.setString(6, dob);
			preparedStatement.setString(7, state);
			preparedStatement.setString(8, city);
			preparedStatement.setString(9, mnum);
			preparedStatement.setString(10, program);

			// Execute queries
			preparedStatement.executeUpdate();

			writer.println("<b> Registered Successfully </b>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("StudentLogin.html");
			System.out.println("StudentRegistrationServlet exits. Forwarded to StudentLogin.html ");
			requestDispatcher.include(request, response);
			connection.close();
			System.out.println("StudentRegistrationServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
