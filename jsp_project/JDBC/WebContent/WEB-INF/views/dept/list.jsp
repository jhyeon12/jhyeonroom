<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
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
	
	#content>table {
		border: 0px;
		border-collapse: collapse;
		
		width : 700px;
	}
	
	#content>table td, #content>table th {
		 border: 1px solid #aaa;
		 text-align: center;
	} 
	
	
	
	
	
</style>
<script>

function deleteDept(deptno){
	
	if(confirm('삭제하시겠습니까?')){
		// 삭제 처리할수 있는 url 이동
		// http://localhost:8080/jdbc/dept/delete.do?deptno=9998
		//alert(deptno + " 삭제");
		location.href='delete.do?deptno='+deptno;
	}
	
}

</script>

</head>
<body>

	<%@ include file="/WEB-INF/views/frame/header.jsp" %>

	<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
	
	<div id="content">
		 <h3>부서 리스트</h3>
		<hr>
		
		<table>
			<tr>
				<th>부서번호</th>
				<th>부서이름</th>
				<th>부서위치</th>
				<th>관리</th>
			</tr>
			
			<c:if test="${not empty deptList}">
			
			<c:forEach items="${deptList}" var="dept">
			<tr>
				<td>${dept.deptno}</td>
				<td>${dept.dname}</td>
				<td>${dept.loc}</td>
				<td> 
					<a href="edit.do?deptno=${dept.deptno}">수정</a> 
						| 
					삭제 
				</td>	
			</tr>
			</c:forEach>
			
			</c:if>
			
			<c:if test="${empty deptList}">
			<tr>
				<td colspan="4">등록된 부서정보가 없습니다.</td>
			</tr>
			</c:if>
			
			
			
			
			
			
		</table>
	</div>

</body>
</html>