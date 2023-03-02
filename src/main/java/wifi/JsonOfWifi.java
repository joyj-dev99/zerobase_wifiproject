package wifi;

/*
 * Open API를 이용해 json 파일 가져오기 */


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonOfWifi {
	
	@SuppressWarnings("deprecation")
	public String LoadWifiData(int x, int y) throws IOException { // api 이용해서 	불러온 json 데이터 parsing 하 
	
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/666c495970736f723131305667524443/json/TbPublicWifiInfo"); /*URL*/
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(x),"UTF-8")); /*요청시작위치 */
		urlBuilder.append("/" + URLEncoder.encode(Integer.toString(y),"UTF-8")); /*요청종료위치 */
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		String res = sb.toString();
		JsonParser paser = new JsonParser();
		JsonObject obj = (JsonObject) paser.parse(res); 
		JsonObject parse_TbPublicWifiInfo = (JsonObject) obj.get("TbPublicWifiInfo");
		String parse_row = parse_TbPublicWifiInfo.get("row").toString();

		return parse_row;
		
		
	}
	
	
	//totalCnt 구하기 
	@SuppressWarnings("deprecation")
	public int TotalCntChecker() throws IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/666c495970736f723131305667524443/json/TbPublicWifiInfo/1/1"); /*URL*/
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		String res = sb.toString();
		
		JsonParser paser = new JsonParser();
		JsonObject obj = (JsonObject) paser.parse(res); 
		JsonObject parse_TbPublicWifiInfo = (JsonObject) obj.get("TbPublicWifiInfo");
	    
		int totalCnt = Integer.valueOf(parse_TbPublicWifiInfo.get("list_total_count").toString());
	
		return totalCnt;
	}

	
}