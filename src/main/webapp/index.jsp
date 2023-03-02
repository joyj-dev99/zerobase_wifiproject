<!-- 홈화면 -->

<%@page import="java.util.ArrayList"%>
<%@page import="wifi.loadNearWifi20"%>
<%@page import="wifiDto.rowItem"%>
<%@page import="java.util.List"%>
<%@page import="Jdbc.JdbcConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<script src = "whereAmI.js"></script>
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
	<h1>와이파이 정보 구하기</h1>	
	<p>
		<a href="http://localhost:8080/wifi-project/index.jsp">홈</a> | 
		<a href="http://localhost:8080/wifi-project/history.jsp">위치 히스토리 목록</a> | 
		<a href="http://localhost:8080/wifi-project/load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
	</p>
	
	
		<Form method="get" action="index.jsp"> 
			<p>
		    	LAT: <input type ="text" id = "targetLat" name = "targetLat" /> , 
				LNT: <input type ="text" id = "targetLnt" name = "targetLnt" /> 
		    	<button type="button" onclick ="whereAmI()">내 위치 가져오기</button>
	    		<input type="submit" value="근처 WIFI 정보 보기"> 
   			</p>
    	</Form>
   
    	
    	<table>
    		<thead>
    			<tr>
    				<th>거리(Km)</th>
    				<th>관리번호</th>
    				<th>자치구</th>
    				<th>와이파이명</th>
    				<th>도로명주소</th>
    				<th>상세주소</th>
    				<th>설치위치(층)</th>
    				<th>설치유형</th>
    				<th>설치기관</th>
    				<th>서비스구분</th>
    				<th>망종류</th>
    				<th>설치년도</th>
    				<th>실내외구분</th>
    				<th>WIFI접속환경</th>
    				<th>X좌표</th>
    				<th>Y좌표</th>
    				<th>작업일자</th>
    			</tr>
    		</thead>
    		<tbody>
    		
    			
 
		    <% 
		   
			   if (request.getParameter("targetLat") != null && request.getParameter("targetLnt") != null) {
		    		Double nowLat = Double.parseDouble(request.getParameter("targetLat"));
		      		Double nowLnt = Double.parseDouble(request.getParameter("targetLnt"));
		      		
		      		loadNearWifi20 loadWifi = new loadNearWifi20();
		        	List<rowItem> wifiList = loadWifi.load(nowLat, nowLnt); // 현재 위도,경도 넘기면 가장 가까운 20개 보여
	
			    	for (rowItem wifi : wifiList) {
			    		out.write("<tr>");
			    		String result = String.format("%.4f", wifi.getDistance());
			    		out.write("<td>" + result + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_MGR_NO() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_WRDOFC() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_MAIN_NM() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_ADRES1() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_ADRES2() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_INSTL_FLOOR() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_INSTL_TY() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_INSTL_MBY() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_SVC_SE() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_CMCWR() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_CNSTC_YEAR() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_INOUT_DOOR() + "</td>");
			    		out.write("<td>" + wifi.getX_SWIFI_REMARS3() + "</td>");
			    		out.write("<td>" + wifi.getLAT() + "</td>");
			    		out.write("<td>" + wifi.getLNT() + "</td>");
			    		out.write("<td>" + wifi.getWORK_DTTM() + "</td>");
			    		out.write("</tr>");
			    		
			    	}
		        	
			    	loadWifi.inserHistory(nowLat, nowLnt);
		        	
			    } else {
	    	 	%>
					<tr>
				   		<td colspan="17"> 위치 정보를 입력한 후에 조회해 주세요.</td>
				   	</tr>				   
			   <%
			    	
			    }
		 %>
    		
    		</tbody>
    	</table>
    	
	
</body>
</html>