package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int qsno = 1;
	static int count = 0;
	
    public ExamServlet() {
        super();
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("ExamServlet begins");

		response.setContentType("text/html");
		// getting parameter from Exam.jsp page
		String candans = request.getParameter("answer");
		String cortans = request.getParameter("canswer");
		System.out.println("Attributes are \ncandidate answer : " + candans + "\nCorrect answer " + cortans);

		if (cortans.equals(candans)) {
			System.out.println("Marks is " + count);
			count++;
			System.out.println("qsno is : " + qsno);
		}
		if (qsno != 10) {
			qsno++;
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Exam.jsp");
			System.out.println("ExamServlet exits. Forwarded to Exam.jsp ");
			requestDispatcher.forward(request, response);
		} else {
			System.out.println("Code in else part");
			String marks = String.valueOf(count * 5) ;

			request.setAttribute("marks", marks);
			RequestDispatcher requestDispatcher =
					 request.getRequestDispatcher("Result.jsp");
					 requestDispatcher.include(request, response);
			System.out.println("ExamServlet exits");
		}

	}

}
