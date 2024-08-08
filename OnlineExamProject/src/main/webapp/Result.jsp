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
<title>Result Page</title>
<link rel="stylesheet" href="css/stylestartexam.css">
</head>
<body>
<div class="main"  style="background-image: url('images/bookimage1.png');">
		<div class="container" >
			<div class="start-exam text-center">
				<h1>
					<b> Result </b>
				</h1>
				<div>
					<p class="Result">
						<% String m = (String)request.getAttribute("marks"); %>
						Your test score is <%=m %>
					</p>
				</div>
				<div>
					<a href="Index.html"><button class="btn btn--radius-2 btn--blue" type="button">  Exit </button></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>