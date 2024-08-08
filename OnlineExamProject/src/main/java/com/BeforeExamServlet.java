package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BeforeExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BeforeExamServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("BeforeExamServlet begins");

		response.setContentType("text/html");

		// Creating a session of user
		HttpSession session = request.getSession();

		// getting parameter from BeforeExam.jsp page
		String subname = request.getParameter("tname");
		session.setAttribute("subname", subname);
		System.out.println("Attributes in session : subject name : " + subname);

		System.out.println("BeforeExamServlet exits. Forwarded to Exam.jsp ");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("Exam.jsp");
		requestDispatcher.forward(request, response);

	}

}
