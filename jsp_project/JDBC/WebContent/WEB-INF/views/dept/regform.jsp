<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서관리 프로그램</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css">
<style>
	#content {
		padding : 20px;
	}
	
	#content>hr {
		border:0px;
		border-top: 1px solid #aaa;
		margin: 10px 0;
	}
	
	
	
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/frame/header.jsp" %>

	<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
	
	<div id="content">
	
		
		<h3>부서 정보 등록</h3>
		<hr>
		
		<form method="post">
			<table>
				<tr>
					<td>부서번호</td>
					<td><input type="text" name="deptno"> </td>
				</tr>
				<tr>
					<td>부서이름</td>
					<td><input type="text" name="dname"> </td>
				</tr>
				<tr>
					<td>부서위치</td>
					<td><input type="text" name="loc"> </td>
				</tr>
				
				<tr>
					<td></td>
					<td><input type="submit" value="등록"> <input type="reset"> </td>
				</tr>
			</table>
		</form>
		
		
		
		
		
		
		
		
	</div>

</body>
</html>