package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

public class HistoryService {
	public List<History> list() {
		List<History> historyList = new ArrayList();

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
			String sql = " select site_id, x_col, y_col, search_dt " + " from member " 
			+ " order by site_id ";
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			// 4-1.쿼리를 한건씩 읽어옴
			// 5.결과 수행
			while (rs.next()) {
				Integer siteId = rs.getInt("site_id");
				String xCol = rs.getString("x_col");
				String yCol = rs.getString("y_col");
				Date searchDt = rs.getDate("search_dt");

				History history= new History();
				history.setSiteId(siteId);
				history.setXCol(xCol);
				history.setYCol(yCol);
				history.setSearchDt(searchDt);;

				historyList.add(history);

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

		return historyList;
	}

	

	/**
	 * 기록 등록
	 * 
	 */
	public void register(History history) {
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
			String sql = " insert into history (site_id, x_col, y_col, search_dt) " + " values (?, ?, ?, ?); ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, history.getSiteId());
			preparedStatement.setString(2, history.getXCol());
			preparedStatement.setString(3, history.getYCol());
			preparedStatement.setDate(4, history.getSearchDt());

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
	}

	/**
	 * 기록 삭제 함수
	 */
	public void delHistory(History history) {
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
			String sql = " delete from history " + " where siteId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, history.getSiteId());

			int affected = preparedStatement.executeUpdate();
			// 4-1.쿼리를 한건씩 읽어옴
			// 5.결과 수행
			if (affected > 0) {
				System.out.println("삭제 성공!");
			} else {
				System.out.println("삭제 실패!");
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
	}
}
