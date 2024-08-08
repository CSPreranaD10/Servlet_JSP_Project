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

public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	System.out.println("StudentLoginServlet begins");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// Creating a session of user
		HttpSession session = request.getSession();

		// getting parameter from StudentLogin.html page
		String username = request.getParameter("susername");
		String password = request.getParameter("spassword");

		System.out.println("From StudentLogin page /nUsername : " + username + " /nPassword : " + password);

		try {
			// Register the Driver Class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("StudentLoginServlet : Driver class registered");

			// Create connection
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			System.out.println("StudentLoginServlet : Connection created");

			// Create statement
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from candidates where susername = ?");
			preparedStatement.setString(1, username);

			// Execute queries
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String pass = resultSet.getString("spassword");
				String rollno = resultSet.getString("studentid");
				String sname = resultSet.getString("sname");
				String program = resultSet.getString("sprogram");

				// store value in session
				session.setAttribute("rollno", rollno);
				session.setAttribute("sname", sname);
				session.setAttribute("program", program);
				System.out.println("Attributes in session : \nrollno : " + rollno + "\nstudent name : " + sname
						+ "\nprogram : " + program);

				if (password.equals(pass)) {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("BeforeExam.jsp");
					System.out.println("StudentLoginServlet exits. Forwarded to BeforeExam.jsp ");
					requestDispatcher.forward(request, response);
				} else {
					writer.print("Invalid username or password");
					System.out.println("Wrong username or password");

					RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("StudentLogin.html ");
					requestDispatcher1.include(request, response);
				}
			}
			connection.close();
			System.out.println("StudentLoginServlet : Connection closed");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

}
