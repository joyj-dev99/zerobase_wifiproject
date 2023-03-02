package wifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wifiDto.rowItem;

public class loadNearWifi20 {
	public List<rowItem> load(double nowLat, double nowLnt) {
		
		List<rowItem> wifiList = new ArrayList<>();
	
		String url = "jdbc:mariadb://52.79.204.48:3306/WIFI";
		String dbUserId = "wifiproject";
		String dbPassword = "wifiproject123";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " SELECT w.* "
							+ "	,(6371*acos(cos(radians(?))*cos(radians(w.LAT))*cos(radians(w.LNT)-radians(?))+sin(radians(?))*sin(radians(w.LAT)))) AS distance "
							+ " FROM WIFI w"
							+ " order by distance "
							+ " limit 20 ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setDouble(1, nowLat);
			preparedstatement.setDouble(2, nowLnt);
			preparedstatement.setDouble(3, nowLat);
			

			rs = preparedstatement.executeQuery();
			
			while (rs.next()) {
				
				rowItem wifi = new rowItem();
				wifi.setDistance(rs.getDouble("distance"));
				wifi.setX_SWIFI_MGR_NO(rs.getString("WIFI_MGR_NO"));
				wifi.setX_SWIFI_WRDOFC(rs.getString("WIFI_WRDOFC"));
				wifi.setX_SWIFI_MAIN_NM(rs.getString("WIFI_MAIN_NM"));
				wifi.setX_SWIFI_ADRES1(rs.getString("WIFI_ADRES1"));
				wifi.setX_SWIFI_ADRES2(rs.getString("WIFI_ADRES2"));
				wifi.setX_SWIFI_INSTL_FLOOR(rs.getString("WIFI_INSTL_FLOOR"));
				wifi.setX_SWIFI_INSTL_TY(rs.getString("WIFI_INSTL_TY"));
				wifi.setX_SWIFI_INSTL_MBY(rs.getString("WIFI_INSTL_MBY"));
				wifi.setX_SWIFI_SVC_SE(rs.getString("WIFI_SVC_SE"));
				wifi.setX_SWIFI_CMCWR(rs.getString("WIFI_CMCWR"));
				wifi.setX_SWIFI_CNSTC_YEAR(rs.getString("WIFI_CNSTC_YEAR"));
				wifi.setX_SWIFI_INOUT_DOOR(rs.getString("WIFI_INOUT_DOOR"));
				wifi.setX_SWIFI_REMARS3(rs.getString("WIFI_REMARS3"));
				wifi.setLAT(String.valueOf(rs.getDouble("LAT")));
				wifi.setLNT(String.valueOf(rs.getDouble("LNT")));
				wifi.setWORK_DTTM(rs.getString("WORK_DTTM"));
				
				wifiList.add(wifi);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
			
			
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				} 	
			} catch (SQLException e) {
					e.printStackTrace();
				}
			
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				} 
			} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return wifiList;
		
	}

	public void inserHistory(double nowLat, double nowLnt) {
		String url = "jdbc:mariadb://52.79.204.48:3306/WIFI";
		String dbUserId = "wifiproject";
		String dbPassword = "wifiproject123";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			
			String sql = " INSERT into HISTORY (LAT, LNT, HISTORY_DT) "
					+ " values (?, ? ,now()) " ;
			
			preparedstatement = connection.prepareStatement(sql);
			
			preparedstatement.setDouble(1, nowLat);
			preparedstatement.setDouble(2, nowLnt);
			
			
			int affected = preparedstatement.executeUpdate();
			
			if (affected > 0) {
				System.out.println(" 저장 성공 ");
			} else {
				System.out.println(" 저장 실패 ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
			
			
			try {
				if (preparedstatement != null && !preparedstatement.isClosed()) {
					preparedstatement.close();
				} 	
			} catch (SQLException e) {
					e.printStackTrace();
				}
			
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				} 
			} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

}
