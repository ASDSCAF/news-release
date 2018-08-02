package org.jbit.news.dao;

/*
 * 工具类
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
//	private static String driver;
//	private static String url;
//	private static String user;
//	private static String password;
//	private Connection conn = null;
	
    public Connection conn;

	public BaseDao(Connection conn) {
		this.conn = conn;
	}
//
//	static {
//		init();
//	}
//
//	public static void init() {
//		Properties params = new Properties();
//		String configFile = "database.properties";
//		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(
//				configFile);
//		try {
//			params.load(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		driver = params.getProperty("driver");
//		url = params.getProperty("url");
//		user = params.getProperty("user");
//		password = params.getProperty("password");
//
//	}

	// 开启数据库连接
//	public Connection getConnection() {
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//
//	}

	// 关闭数据库连接
//	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	/*
	 * 增删改操作
	 */
	public int executerUpdate(String preparedSql, Object... param) throws SQLException{
		PreparedStatement pstmt = null;
		int num = 0;
//		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(preparedSql);
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public ResultSet executerQuery(String preparedSql, Object... param) throws SQLException{
		PreparedStatement pstmt = null;
//		conn = getConnection();
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(preparedSql);
			for (int i = 0; i < param.length; i++) {
				pstmt.setObject(i + 1, param[i]);
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
