<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test page</h1>
	<table>
		<tr>
			<th>deptno</th>
			<th>dname</th>
			<th>loc</th>
		</tr>
		<%
		java.util.ArrayList list =(java.util.ArrayList)request.getAttribute("list");
		for(int i=0; i<list.size(); i++){
			com.bit.model.entity.DeptVo bean=(com.bit.model.entity.DeptVo)list.get(i);
		%>
		<tr>
			<td><%=bean.getDeptno() %></td>
			<td><%=bean.getDname() %></td>
			<td><%=bean.getLoc() %></td>
		</tr>
		<%} %>
	</table>
</body>
</html>








