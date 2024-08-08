<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Question</title>
<link rel="stylesheet" href="css/stylesheetshowQ.css">
</head>
<body>
	<div>
		<div class="wrapper">
			<form action="#" method="post">
				<fieldset>
					<div class="row row-space">
						<div class="input-group">
							<label for="subject" class="label"> Select the subject
								name </label> <select name="subject" class="input01" required>
								<option disabled="disabled" selected="selected">Choose
									subject</option>
								<% 
										try { 
											Class.forName("com.mysql.cj.jdbc.Driver"); 
											Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root" , "root" );
											PreparedStatement preparedStatement;
											preparedStatement=connection.prepareStatement("select * from subject"); 
											ResultSet resultSet=preparedStatement.executeQuery(); 
											while(resultSet.next()) {
												String sub=resultSet.getString("subname");
										%>
								<option value="<%=sub %>">
									<%=sub %>
								</option>
								<% } 
											connection.close(); 
										} catch (SQLException e) { 
											e.printStackTrace(); 
										} catch (ClassNotFoundException e) { 
											e.printStackTrace(); 
										} %>
							</select>

						</div>
					</div>
					<div class="row row-space">
						<div class="p-t-15">
							<button class="btn btn--radius-2 btn--blue" type="submit">Show</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<%
		try {
			response.setContentType("text/html");
			int qsno = 1;
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			String sub = (String) request.getParameter("subject");
			
			
		%>


		<h4 class="text-center mb-4">
			<%= sub %>
			Question Paper
		</h4>
		<div>
			<a href="AdminMain.html">
				<button class="btn btn--radius-2 btn--blue" type="button">
					Back</button>
			</a>
		</div>
		<div class="table">
			<div class="row header">
				<div class="cell">Q no</div>
				<div class="cell">Question</div>
				<div class="cell">Option 1</div>
				<div class="cell">Option 2</div>
				<div class="cell">Option 3</div>
				<div class="cell">Correct Option</div>
				<div class="cell"></div>
			</div>
			<%
					PreparedStatement preparedStatement = connection.prepareStatement("select * from " + sub);
					//preparedStatement.setInt(1, qsno);
					ResultSet resultSet = preparedStatement.executeQuery();
					while(resultSet.next()) {
				%>
			<div class="row">
				<div class="cell" data-title="Q no">
					<%=qsno%>
					<%session.setAttribute("qs", qsno); %>
				</div>
				<div class="cell" data-title="Question">
					<%=resultSet.getString(2)%>
				</div>
				<div class="cell" data-title="Option 1">
					<%=resultSet.getString(3)%>
				</div>
				<div class="cell" data-title="Option 2">
					<%=resultSet.getString(4)%>
				</div>
				<div class="cell" data-title="Option 3">
					<%=resultSet.getString(5)%>
				</div>
				<div class="cell" data-title="Correct Option">
					<%=resultSet.getString(6)%>
				</div>
				<div class="cell" data-title="Correct Option">
					<a href="UpdateQuestion.jsp?qsno=<%=qsno%>">Update</a> <a
						href="DeleteQuestionServlet?qsno=<%=qsno%> ">Delete</a>
				</div>
			</div>
			<% qsno++;
					}connection.close();
				} catch (ClassNotFoundException e) {
						e.printStackTrace();
				} catch (SQLException e) {
						e.printStackTrace();
				}
				%>

		</div>

	</div>
	</div>

</body>
</html>