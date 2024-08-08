package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("AdminLoginServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// Creating a session of admin
		HttpSession session = request.getSession();

		// getting parameter from AdminLogin.html page
		String username = request.getParameter("ausername");
		String password = request.getParameter("apassword");

		System.out.println("From AdminLogin page \nUsername : " + username + " \nPassword : " + password);

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("AdminLoginServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("AdminLoginServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from admin where ausername = ?");
			preparedStatement.setString(1, username);

			// Execute queries
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String pass = resultSet.getString("apassword");
				String aname = resultSet.getString("aname");

				// store value in session
				session.setAttribute("aname", aname);

				if (password.equals(pass)) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminMain.html");
					System.out.println("AdminLoginServlet exits. Forwarded to AdminMain.html ");
					requestDispatcher.forward(request, response);
				} else {
					writer.print("Invalid username or password");
					System.out.println("Invalid username or password");
					RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("AdminLogin.html");
					System.out.println("AdminLoginServlet exits. Forwarded to AdminLogin.html ");
					requestDispatcher1.include(request, response);
				}
			}
			connection.close();
			System.out.println("AdminLoginServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

}
