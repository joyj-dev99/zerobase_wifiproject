package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wifiDto.rowItem;
import history.History;

public class JdbcConnect {
	
	
	//db에 이미 존재하는지 확인. 
	public boolean Checker(rowItem wifi) {
		
		boolean result = false;
	
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
			
			String sql = 	" SELECT * " + 
							" FROM WIFI " +
							" WHERE WIFI_MGR_NO = ? ";
			preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, wifi.getX_SWIFI_MGR_NO());
			

			rs = preparedstatement.executeQuery();
			
			if (rs.next()) {
				result = true;
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
		
		return result;
		
	}
	
	//새로운 wifi 추가 
	public void DbInsert(rowItem wifi) {
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
			
			String sql = " INSERT into WIFI (WIFI_MGR_NO, WIFI_WRDOFC, WIFI_MAIN_NM, WIFI_ADRES1, WIFI_ADRES2, WIFI_INSTL_FLOOR, WIFI_INSTL_TY, WIFI_INSTL_MBY, WIFI_SVC_SE, WIFI_CMCWR, WIFI_CNSTC_YEAR, WIFI_INOUT_DOOR, WIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
					" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
					;
			
			preparedstatement = connection.prepareStatement(sql);
			
			preparedstatement.setString(1, wifi.getX_SWIFI_MGR_NO());
			preparedstatement.setString(2, wifi.getX_SWIFI_WRDOFC());
			preparedstatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
			preparedstatement.setString(4, wifi.getX_SWIFI_ADRES1());
			preparedstatement.setString(5, wifi.getX_SWIFI_ADRES2());
			preparedstatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			preparedstatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
			preparedstatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			preparedstatement.setString(9, wifi.getX_SWIFI_SVC_SE());
			preparedstatement.setString(10, wifi.getX_SWIFI_CMCWR());
			preparedstatement.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			preparedstatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			preparedstatement.setString(13, wifi.getX_SWIFI_REMARS3());
			preparedstatement.setDouble(14, Double.parseDouble(wifi.getLAT()));
			preparedstatement.setDouble(15, Double.parseDouble(wifi.getLNT()));
			preparedstatement.setString(16, wifi.getWORK_DTTM());
			
			
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
	
	// wifi 정보 업데이트 
	public void DbUpdate(rowItem wifi) {
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
			
			String sql = " UPDATE WIFI "
					+ " set WIFI_WRDOFC = ? "
					+ "	, WIFI_MAIN_NM = ? "
					+ "	, WIFI_ADRES1 = ? "
					+ "	, WIFI_ADRES2 = ? "
					+ "	, WIFI_INSTL_FLOOR = ? "
					+ "	, WIFI_INSTL_TY = ? "
					+ "	, WIFI_INSTL_MBY = ? "
					+ "	, WIFI_SVC_SE = ? "
					+ "	, WIFI_CMCWR = ? "
					+ "	, WIFI_CNSTC_YEAR = ? "
					+ "	, WIFI_INOUT_DOOR = ? "
					+ "	, WIFI_REMARS3 = ? "
					+ "	, LAT = ? "
					+ "	, LNT = ? "
					+ "	, WORK_DTTM = ? "
					+ " WHERE WIFI_MGR_NO = ? " ;
			
			preparedstatement = connection.prepareStatement(sql);
			
			preparedstatement.setString(1, wifi.getX_SWIFI_WRDOFC());
			preparedstatement.setString(2, wifi.getX_SWIFI_MAIN_NM());
			preparedstatement.setString(3, wifi.getX_SWIFI_ADRES1());
			preparedstatement.setString(4, wifi.getX_SWIFI_ADRES2());
			preparedstatement.setString(5, wifi.getX_SWIFI_INSTL_FLOOR());
			preparedstatement.setString(6, wifi.getX_SWIFI_INSTL_TY());
			preparedstatement.setString(7, wifi.getX_SWIFI_INSTL_MBY());
			preparedstatement.setString(8, wifi.getX_SWIFI_SVC_SE());
			preparedstatement.setString(9, wifi.getX_SWIFI_CMCWR());
			preparedstatement.setString(10, wifi.getX_SWIFI_CNSTC_YEAR());
			preparedstatement.setString(11, wifi.getX_SWIFI_INOUT_DOOR());
			preparedstatement.setString(12, wifi.getX_SWIFI_REMARS3());
			preparedstatement.setDouble(13, Double.parseDouble(wifi.getLAT()));
			preparedstatement.setDouble(14, Double.parseDouble(wifi.getLNT()));
			preparedstatement.setString(15, wifi.getWORK_DTTM());
			preparedstatement.setString(16, wifi.getX_SWIFI_MGR_NO());
			
			
			int affected = preparedstatement.executeUpdate();
			
			if (affected > 0) {
				System.out.println(" 수정 성공 ");
			} else {
				System.out.println(" 수정 실패 ");
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

	// 히스토리 불러오기
	public List<History> loadHistory() {
		
		List<History> historyList = new ArrayList<>();
		
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
			
			String sql = " SELECT HISTORY_ID, LAT, LNT, HISTORY_DT "
							+ "FROM HISTORY ";
			preparedstatement = connection.prepareStatement(sql);
			
			rs = preparedstatement.executeQuery();
			
			while (rs.next()) {
				
				History history = new History();
				history.setHistoryId(rs.getInt("HISTORY_ID"));
				history.setHistoryLat(rs.getDouble("LAT"));
				history.setHistoryLnt(rs.getDouble("LNT"));
				history.setHistoryDt(rs.getDate("HISTORY_DT").toString());
				
				historyList.add(history);
				
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
		
		return historyList;
		
	}
	
}
