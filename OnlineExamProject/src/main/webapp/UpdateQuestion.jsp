<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update </title>
    <link rel="stylesheet" href="css/stylesheetinsertQ.css">
</head>
<body>
<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title"> Update Questions </h2>
                    <%
		try {
			response.setContentType("text/html");
			
			/* Object obj = session.getAttribute("qs");
			int qs = Integer.parseInt(obj.toString());
			out.println("qno is "+qs); */
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examdb", "root", "root");
			int qs = 6;
			//String sub="javaexam";
			String sub = (String)request.getAttribute("subject");
			//System.out.println("subject in updateQ page is " + sub);
			//out.println("subj is "+sub);
			PreparedStatement preparedStatement = connection.prepareStatement("select * from " + sub + " where qno = ?");
			preparedStatement.setInt(1, qs);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String question = resultSet.getString(2);
				String opt1 = resultSet.getString(3);
				String opt2 = resultSet.getString(4);
				String opt3 = resultSet.getString(5);
				String crtopt = resultSet.getString(6); 
				//System.out.println(question + " " + opt1 + " "+ opt2 + " " + opt3 );
			
			
		%>
                    <form action="UpdateQServlet" method="post">
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="question" class="label"> Enter the question </label>
                                <input class="input--style-4" type="text" name="question" value="<%=question %>" required/>
                            </div>        
                        </div>
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="question" class="label"> Enter the first option </label>
                                <input class="input--style-4" type="text" name="option1" value="<%=opt1 %>" required/>
                            </div>        
                        </div>
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="question" class="label"> Enter the second option </label>
                                <input class="input--style-4" type="text" name="option2" value="<%=opt2 %>" required/>
                            </div>        
                        </div>
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="question" class="label"> Enter the third option </label>
                                <input class="input--style-4" type="text" name="option3" value="<%=opt3 %>" required/>
                            </div>        
                        </div>
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="question" class="label"> Select the correct answer </label>
                                    <select name="optionans" class="input01" " required>
                                        <option disabled="disabled" selected="selected">Choose option </option>
                                        <option value="option1">option1</option>
                                        <option value="option2">option2</option>
                                        <option value="option3">option3</option>
                                    </select>
                                    <div class="select-dropdown"></div>
                        </div>
                        <div class="row row-space">
                            <div class="input-group">
                                <label for="subject" class="label"> Select the subject name </label>
                                    <select name="subject" class="input01" required>
                                        <option disabled="disabled" selected="selected">Choose option </option>
                                        <option value="javaexam">java</option>
                                        <option value="htmlexam">html</option>
                                        <option value="cssexam">css</option>
                                    </select>
                                    <div class="select-dropdown"></div>  
                                    
                        </div>
                        <div class="row row-space">
                            <div class="p-t-15">
                                <button class="btn btn--radius-2 btn--blue" type="submit">Update</button>
                            </div>
                        </div>
                        <%
                        }
                            

                        	connection.close();
                        } catch (ClassNotFoundException e) {
                        	e.printStackTrace();
                        } catch (SQLException e) {
                        	e.printStackTrace();
                        }
                        %>   
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>