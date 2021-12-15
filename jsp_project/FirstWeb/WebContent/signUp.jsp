<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
	<%
		request.setCharacterEncoding("utf-8");
	%>
	
	<h1>ID : <%= request.getParameter("userid") %></h1>
	<h1>Password : <%= request.getParameter("pw") %></h1>
	<h1>Name : <%= request.getParameter("userName") %></h1>
	<h1>Comment : <%= request.getParameter("comment") %></h1>
	<h1>Gender : <%= request.getParameter("gender") %></h1>
	<%
	
		String[] interest = request.getParameterValues("interest");
		if(interest == null) {
			out.println("<h1>Interest : 관심사가 없습니다.</h1>");
		} else {
			for(int i=0; i<interest.length; i++) {
				out.println("<h1>Interest : " + interest[i] + "</h1>");
			}
		}
	%>
	
	<h1>Byear : <%= request.getParameter("byear") %></h1>
	
	
	
	
	
	
	
</body>
</html>