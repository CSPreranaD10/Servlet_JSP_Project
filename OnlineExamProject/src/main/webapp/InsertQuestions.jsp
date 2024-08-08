<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DatabaseMetaData" %>
<%@page import="java.sql.ResultSetMetaData" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Question</title>
	<link rel="stylesheet" href="css/stylesheetinsertQ.css">
</head>
<body>
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title"> Insert Questions </h2>
					<form action="InsertQuestionServlet" method="post">
						<fieldset>
							<div class="row row-space">
								<div class="input-group">
									<label for="question" class="label"> Enter the
										question </label>
									<input class="input--style-4" type="text" name="question" required />
								</div>
							</div>
							<div class="row row-space">
								<div class="input-group">
									<label for="question" class="label"> Enter the first
										option </label>
									<input class="input--style-4" type="text" name="option1" required />
								</div>
							</div>
							<div class="row row-space">
								<div class="input-group">
									<label for="question" class="label"> Enter the
										second option </label>
									<input class="input--style-4" type="text" name="option2" required />
								</div>
							</div>
							<div class="row row-space">
								<div class="input-group">
									<label for="question" class="label"> Enter the third option </label>
									<input class="input--style-4" type="text" name="option3" required />
								</div>
							</div>
							<div class="row row-space">
								<div class="input-group">
									<label for="question" class="label"> Select the
										correct answer </label>
									<select name="optionans" class="input01" required>
										<option disabled="disabled" selected="selected">
											Choose option </option>
										<option value="option1">option1</option>
										<option value="option2">option2</option>
										<option value="option3">option3</option>
									</select>
									<div class="select-dropdown"></div>
								</div>
							</div>
							<div class="row row-space">
								<div class="input-group">
									<label for="subject" class="label"> Select the
										subject name
									</label>
									<select name="subject" class="input01" required>
										<option disabled="disabled" selected="selected"> Choose option </option>
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
									<div class="select-dropdown"></div>
								</div>
							</div>
							<div class="row row-space">
								<div class="p-t-15">
									<button class="btn btn--radius-2 btn--blue" type="submit">Insert</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>