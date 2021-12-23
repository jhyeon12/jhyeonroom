<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>signUp JSP</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
	%>

	<h1>
		ID :
		<%=request.getParameter("id")%></h1>
	<h1>
		PW :
		<%=request.getParameter("pw")%></h1>
	<h1>
		UserName :
		<%=request.getParameter("userName")%></h1>
	<h1>
		Comment :
		<%=request.getParameter("comment")%></h1>
	<h1>
		Gender :
		<%=request.getParameter("gender")%></h1>

	<%
		String[] interests = request.getParameterValues("interest");
		if (interests != null) {
			for (int i = 0; i < interests.length; i++) {
				out.println("관심사 : " + interests[i] + " <br>");
			}
		} else {
			out.println("관심사 : 선택된 관심사가 없습니다. <br>");
		}
	%>

	<h1>
		byear :
		<%=request.getParameter("byear")%></h1>
</body>
</html>