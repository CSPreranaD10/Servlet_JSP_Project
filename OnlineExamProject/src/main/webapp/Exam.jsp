<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.RandomNumGenerate"%>
<%@page import="java.util.Random"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam</title>
<link rel="stylesheet" href="css/stylestartexam.css">
</head>
<body>
	<%
RandomNumGenerate obj = new RandomNumGenerate();
int num = obj.checkQNo();


try {
	response.setContentType("text/html");

	//getting attribute from session
	String subname = (String)session.getAttribute("subname");
	
%>
	<div class="main"
		style="background-image: url('images/bookimage1.png');">
		<div class="container">
			<div class="start-exam">
				<form action="ExamServlet" method="post" class="start-form">
					<div class="text-center">
						<h2>
							<b><%=subname %> Questions</b>
						</h2>
					</div>
					<br>
					<div>
						<%!static int qsno = 1;%>
						<%
					// Register the Driver Class
					Class.forName("com.mysql.cj.jdbc.Driver");

					// Create connection
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");

							// Create statement
							PreparedStatement preparedStatement = connection.prepareStatement("select * from "+subname+" where qno =?");
							preparedStatement.setInt(1, num);

							// Execute queries
							ResultSet resultSet = preparedStatement.executeQuery();
							if (resultSet.next()) {
					%>
						<label for="question" class="form-label"> Q<%=qsno%>) <%=resultSet.getString(2)%>
						</label>

					</div>
					<div class="form-check">
						<label class="form-check-label" for="Firstoption"> <input
							class="form-check-input" type="radio" name="answer" id="chk1"
							value="option1"> <%=resultSet.getString(3)%>
						</label>
					</div>
					<div class="form-check">
						<label class="form-check-label" for="Secondoption"> <input
							class="form-check-input text-center" type="radio" name="answer"
							id="chk2" value="option2"> <%=resultSet.getString(4)%>
						</label>
					</div>
					<div class="form-check">
						<label class="form-check-label" for="Thirdoption"> <input
							class="form-check-input" type="radio" name="answer" id="chk2"
							value="option3"> <%=resultSet.getString(5)%>
						</label>
					</div>
					<div class="display-hidden">
						<% String a = resultSet.getString(6); %>
						<input type="hidden" name="canswer" id="chk2" value="<%=a%>">
					</div>
					<div class="container-start-form-btn">
						<% if(qsno != 10) {%>
						<button type="submit" class="start-from-btn">Next</button>
						<%} else { %>
						<button type="submit" class="start-from-btn">Submit</button>
						<%} %>
					</div>

				</form>
			</div>
		</div>
		<%
			}
			qsno++;
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	%>
	</div>
</body>
</html>