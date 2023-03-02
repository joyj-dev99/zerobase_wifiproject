<!-- 와이파이 정보 가져오기 실행화면 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="wifi.JsonOfWifi"
	import="wifiDto.rowItem"
	import="Jdbc.JdbcConnect"
	import="java.util.Arrays"
	import="java.util.List"
	import="com.google.gson.Gson"
	import="com.google.gson.JsonParser" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
	
</head>
<body>
	
<% 

//api 이용해서 json 데이터 불러오기 

JsonOfWifi jsonofwifi = new JsonOfWifi();
JsonParser parser = new JsonParser();
JdbcConnect jdbcConnect = new JdbcConnect();
Gson gson = new Gson();


int totalCnt = jsonofwifi.TotalCntChecker(); // 전체 wifi 갯수 구하기 
int cnt = 0;

//파싱 1000개씩 끊어서!


for (int i = 1; i < totalCnt / 1000; i++) {
	String res = jsonofwifi.LoadWifiData(i * 1000 - 999, i * 1000);
	
	rowItem[] array = gson.fromJson(res, rowItem[].class);
    List<rowItem> list = Arrays.asList(array);
    
  //db상에 해당 데이터가 있는지 체크 
  //있으면 업데이트 
  //없으면 인서트 

	for (rowItem wifi : list) {
		if (jdbcConnect.Checker(wifi)) {
			jdbcConnect.DbUpdate(wifi);
			cnt++;
		} else {
			jdbcConnect.DbInsert(wifi);
			cnt++;
		}
	}
}

String res = jsonofwifi.LoadWifiData(((int) totalCnt / 1000) * 1000 + 1 , totalCnt);
 

rowItem[] array = gson.fromJson(res, rowItem[].class);
List<rowItem> list = Arrays.asList(array);

for (rowItem wifi : list) {

 	if (jdbcConnect.Checker(wifi) == true) {
		jdbcConnect.DbUpdate(wifi);
		cnt++;
	} else {
		jdbcConnect.DbInsert(wifi);
		cnt++;
	}
}

%>
	
	<div style="text-align:center">
		<h1> <% out.print(cnt); %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
		<a href = "http://localhost:8080/wifi-project/index.jsp">홈으로 가기</a>
	</div>

</body>
</html>