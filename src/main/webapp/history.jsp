<!-- 위치 히스토리 목록 기능 화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Jdbc.JdbcConnect"
	import="history.History"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
		table {
				width: 100%;		
		}
			
		th, td {
			border:solid 1px #000;
			text-align:center;
		}
</style>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<p><a href="http://localhost:8080/wifi-project/index.jsp">홈</a> | 
	<a href="http://localhost:8080/wifi-project/history.jsp">위치 히스토리 목록</a> | 
	<a href="http://localhost:8080/wifi-project/load-wifi.jsp">Open API 와이파이 정보 가져오기</a></p>
	
	<table>
		<thead>
			<tr> 
				<td>ID</td>
				<td>X</td>
				<td>Y</td>
				<td>조회일자</td>
				<td>비고</td>
			</tr>
		</thead>
		<tbody>
<% 
		JdbcConnect jdbcConnect= new JdbcConnect();
    	List<History> historyList = jdbcConnect.loadHistory();

    	for (History history : historyList) {
    		out.write("<tr>");
    		out.write("<td>" + history.getHistoryId() + "</td>");
    		out.write("<td>" + history.getHistoryLat() + "</td>");
    		out.write("<td>" + history.getHistoryLnt() + "</td>");
    		out.write("<td>" + history.getHistoryDt() + "</td>");
   %>
    		<td> <button type="button">삭제</button> </td>
    <%
    		out.write("</tr>");
    	}
		%>
		
		</tbody>
	
	</table>
	

</body>
</html>