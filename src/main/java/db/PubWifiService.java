package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PubWifiService {
	public List<PubWifi> list() {
		List<PubWifi> pubWifiList = new ArrayList();

		// 5개
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스

		String url = "jdbc:mariadb://192.168.0.2:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";

		// 1.드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2.connection 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3.statement 객체 생성

			// 4.query 실행
			String sql = " select x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1 "
					+ " x_swifi_adres2, x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby "
					+ " x_swifi_svc_se, x_swifi_cmcwr, x_swifi_cnstc_year, x_swifi_inout_door "
					+ " x_swifi_remars3, lat, lnt, work_dttm " + " from wifi " + " order by distance ,x_swifi_mgr_no ";
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			// 4-1.쿼리를 한건씩 읽어옴
			// 5.결과 수행
			while (rs.next()) {

				String distance = rs.getString("distance");
				String mgnNo = rs.getString("x_swifi_mgr_no");
				String wrdofc = rs.getString("x_swifi_wrdofc");
				String mainNm = rs.getString("x_swifi_main_nm");
				String address1 = rs.getString("x_swifi_adres1");
				String address2 = rs.getString("x_swifi_adres2");
				String instlFloor = rs.getString("x_swifi_instl_floor");
				String instlTy = rs.getString("x_swifi_instl_ty");
				String instlMby = rs.getString("x_swifi_instl_mby");
				String svcSe = rs.getString("x_swifi_svc_se");
				String cmcwr = rs.getString("x_swifi_cmcwr");
				String cnstcYear = rs.getString("x_swifi_cnstc_year");
				String inoutDoor = rs.getString("x_swifi_inout_door");
				String remars3 = rs.getString("x_swifi_remars3");
				String lat = rs.getString("lat");
				String lnt = rs.getString("lnt");
				String workDttm = rs.getString("work_dttm");

				PubWifi pubWifi = new PubWifi();

				pubWifi.setDistance(distance);
				pubWifi.setMgnNo(mgnNo);
				pubWifi.setWrdofc(wrdofc);
				pubWifi.setMainNm(mainNm);
				pubWifi.setAddress1(address1);
				pubWifi.setAddress2(address2);
				pubWifi.setInstlFloor(instlFloor);
				pubWifi.setInstlTy(instlTy);
				pubWifi.setInstlMby(instlMby);
				pubWifi.setSvcSe(svcSe);
				pubWifi.setCmcwr(cmcwr);
				pubWifi.setCnstcYear(cnstcYear);
				pubWifi.setInoutDoor(inoutDoor);
				pubWifi.setRemars3(remars3);
				pubWifi.setLat(lat);
				pubWifi.setLnt(lnt);
				pubWifi.setWorkDttm(workDttm);

				pubWifiList.add(pubWifi);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 6.객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
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

		return pubWifiList;
	}

	/**
	 * 기록 등록
	 * 
	 */
	public int register(List<PubWifi> pubList) {
		// 5개
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스

		String url = "jdbc:mariadb://192.168.0.2:3306/testdb1";
		String dbUserId = "testuser1";
		String dbPassword = "zerobase";

		// 1.드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2.connection 객체 생성
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int res = 0;
		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			// 3.statement 객체 생성

			// 4.query 실행
			String sql = " insert into wifi " + " values (?, ?, ?, ? " + " ?,?,?,?,?,?,?,?,?,?,?,?); ";
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < pubList.size(); i++) {

				PubWifi wifi = pubList.get(i);

				preparedStatement.setString(1, wifi.getMgnNo());
				preparedStatement.setString(2, wifi.getWrdofc());
				preparedStatement.setString(3, wifi.getMainNm());
				preparedStatement.setString(4, wifi.getAddress1());
				preparedStatement.setString(5, wifi.getAddress2());
				preparedStatement.setString(6, wifi.getInstlFloor());
				preparedStatement.setString(7, wifi.getInstlTy());
				preparedStatement.setString(8, wifi.getInstlMby());
				preparedStatement.setString(9, wifi.getSvcSe());
				preparedStatement.setString(10, wifi.getCmcwr());
				preparedStatement.setString(11, wifi.getCnstcYear());
				preparedStatement.setString(12, wifi.getInoutDoor());
				preparedStatement.setString(13, wifi.getRemars3());
				preparedStatement.setString(14, wifi.getLat());
				preparedStatement.setString(15, wifi.getLnt());
				preparedStatement.setString(16, wifi.getWorkDttm());

				preparedStatement.addBatch();
			}
			int [] result = preparedStatement.executeBatch();
			
			int affected = preparedStatement.executeUpdate();
			// 4-1.쿼리를 한건씩 읽어옴
			// 5.결과 수행
			if (affected > 0) {
				System.out.println("저장 성공!");
			} else {
				System.out.println("저장 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 6.객체 연결 해제(close)
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
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
		return res;
	}
}
