<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Before Exam</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="css/stylestartexam.css">

</head>
<body>
<%
        try {

        	String program = (String)session.getAttribute("program");
        	// Register the Driver Class
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		// Create connection
    		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
    		
    		// Create statement
        	PreparedStatement preparedStatement = connection
    			.prepareStatement("select * from subject where sprogram = ?");
        	preparedStatement.setString(1, program);
        		
    		// Execute queries
        	
    		String name = (String)session.getAttribute("sname");
    		String rollno = (String)session.getAttribute("rollno");
    %>
	<div class="main" style="background-image: url('images/bookimage1.png');">
		<div class="container">
			<div class="start-exam">

				<form action="BeforeExamServlet" method="post" class="start-form">
					<span class="start-form-title"> 
						<b>Exam</b>
					</span> <br>
					<div>
						<p>
							Instructions : Attempt all questions. 
							<br> 1 Question = 5 marks 
							<br> Total Marks : 50 <br>
						</p>
					</div>
					<div class="wrap-input01 validate-input" data-validate="Enter full name">
						<input type="text" name="sname" id="txtname" class="input01" value="<%=name %>" required>
					</div>

					<div class="wrap-input01 validate-input" data-validate="Enter Roll no">
						<input type="number" name="srollno" id="txtrollno" class="input01" value="<%=rollno %>" required>
					</div>

					<div class="wrap-input01 validate-input" data-validate="Select Subject">

						<select name="tname" id="tname" class="input01" placeholder="Subject">
							<option> Select Subject </option>
							<%
							ResultSet resultSet = preparedStatement.executeQuery();
								while(resultSet.next()) {
    								String sub = resultSet.getString("subname"); 
							%>
							<option value="<%=sub %>"> <%=sub %> </option>
							<%} %>
						</select>
                    </div>
                   
                    <div class="container-start-form-btn">
                        <button type="submit" class="start-from-btn"> Begin Test </button>
                    </div>
                </form>
            </div>
             <%
        	
        connection.close();
        } catch (SQLException e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    %>
        </div>
    </div>

</body>
</html>