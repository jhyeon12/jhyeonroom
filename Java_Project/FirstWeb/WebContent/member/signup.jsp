<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원 가입 데이터</h1>
	<table>
		<tr>
			<td>아이디</td>
			<td>
				<%
					String uid = request.getParameter("userid");
					out.println(uid);
				%>
			</td>
		</tr>		
		<tr>
			<td>비밀번호</td>
			<td><%= request.getParameter("pw") %></td>
		</tr>		
		<tr>
			<td>이름</td>
			<td><%= request.getParameter("username") %></td>
		</tr>		
		<tr>
			<td>자기소개</td>
			<td><%= request.getParameter("comment") %></td>
		</tr>		
		<tr>
			<td>성별</td>
			<td><%= request.getParameter("gender") %></td>
		</tr>		
		<tr>
			<td>관심사</td>
			<td>
				<% 
					String[] interests = request.getParameterValues("interest");
				
					if(interests != null) {
						for(int i=0; i<interests.length; i++){
							out.println("<div>"+interests[i]+"</div>");
						}
					}
				%>
			</td>
		</tr>		
		<tr>
			<td>태어난 년도</td>
			<td><%= request.getParameter("byear") %></td>
		</tr>
	</table>
</body>
</html>