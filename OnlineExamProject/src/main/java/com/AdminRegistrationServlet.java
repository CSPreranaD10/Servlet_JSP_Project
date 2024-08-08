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

public class AdminRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminRegistrationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminRegistrationServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// getting parameter from AdminRegistration.html page
		String name = request.getParameter("aname");
		String email = request.getParameter("aemail");
		String username = request.getParameter("ausername");
		String password = request.getParameter("apassword");
		String role = request.getParameter("role");

		System.out.println("From AdminRegistration page \nname : " + name + "\n Email : " + email + "\n username : "
				+ username + "\n password : " + password + "\n role :" + role);

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("AdminRegistrationServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("AdminRegistrationServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection.prepareStatement("insert into admin values(?,?,?,?,?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, role);

			// Execute queries
			preparedStatement.executeUpdate();

			writer.println("<b>Registered Successfully </b>");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminLogin.html");
			System.out.println("AdminRegistrationServlet exits. Forwarded to AdminLogin.html ");
			requestDispatcher.include(request, response);

			connection.close();
			System.out.println("AdminRegistrationServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
