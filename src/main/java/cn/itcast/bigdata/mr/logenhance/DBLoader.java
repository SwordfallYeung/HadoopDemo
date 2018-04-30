package cn.itcast.bigdata.mr.logenhance;

import java.sql.*;
import java.util.Map;

/**
 * @author y15079
 * @create 2018-03-23 23:22
 * @desc
 **/
public class DBLoader {

	public static void dbLoader(Map<String, String> ruleMap) throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/urldb?characterEncoding=UTF-8", "root", "admin");
			st = conn.createStatement();
			res = st.executeQuery("select url,content from url_rule");
			while (res.next()){
				ruleMap.put(res.getString(1), res.getString(2));
			}
		} finally {
			try {
				if (res != null)
					res.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
