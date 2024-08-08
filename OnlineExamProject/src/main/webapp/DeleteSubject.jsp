<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Subject</title>
<link rel="stylesheet" href="css/stylesheetinsertQ.css">
</head>
<body>

<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title"> Delete Questions Paper</h2>
					<form action="DeleteSubjectServlet" method="post">
						<fieldset>
							<div class="row row-space">
								<div class="input-group">
									<label for="subject" class="label"> Select the
										subject name
									</label>
									<select name="subject" class="input01" required>
										<option disabled="disabled" selected="selected"> Choose subject </option>
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
									<button class="btn btn--radius-2 btn--blue" type="submit">Delete</button>
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