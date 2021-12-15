<%@page import="util.CookieBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 로그아웃 : LOGIN, ID 쿠키 를 삭제
	
	CookieBox cookieBox = new CookieBox(request);

	// 유효시간이 0인 쿠키를 생성 응답 처리 !!
	response.addCookie(CookieBox.createCookie("LOGIN", "", 0, "/"));
	response.addCookie(CookieBox.createCookie("ID", "", 0, "/"));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>로그아웃 되었습니다.</h1>
	<h3><a href="/first/cookie/member/mypage.jsp">mypage</a></h3>
</body>
</html>