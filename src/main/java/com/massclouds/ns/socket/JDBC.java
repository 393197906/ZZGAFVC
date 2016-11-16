package com.massclouds.ns.socket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ctc.wstx.dtd.DFAState;

import redis.clients.jedis.Jedis;

public class JDBC {
	private String type = "mysql";
	private String url = "jdbc:mysql://localhost/face";
	private String name = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "root";

	private Connection conn = null;
	private PreparedStatement pst = null;

	// 构造连接
	public JDBC(String type, String ip, String db, String username,
			String password) {
		this.url = "jdbc:mysql://" + ip + "/" + db;
		this.user = username;
		this.password = password;
		try {
			Class.forName(name);// 指定连接类型
			this.conn = DriverManager.getConnection(this.url, this.user,
					this.password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 构造连接
	public JDBC() {
		try {
			Class.forName(name);// 指定连接类型
			this.conn = DriverManager.getConnection(this.url, this.user,
					this.password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.conn.close();
//			this.pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询表
	public String sqlSelect(String sql)  {
		ResultSet result = null;
		String str = null;
		try {
			Statement state = this.conn.createStatement();
			result = state.executeQuery(sql);
			str = this.resultSetToJson(result); // json 格式化
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	// 更新表
	public int sqlUpdate(String sql) throws SQLException {
		Statement state = this.conn.createStatement();
		int statu = state.executeUpdate(sql);
		return statu;
	}

	// 数据转json
	private String resultSetToJson(ResultSet rs) throws SQLException,
			JSONException {
		// json数组
		JSONArray array = new JSONArray();
		// 获取列数
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();

		// 遍历ResultSet中的每条数据
		while (rs.next()) {
			JSONObject jsonObj = new JSONObject();
			// 遍历每一列
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = rs.getString(columnName);
				jsonObj.put(columnName, value);
			}
			array.put(jsonObj);
		}
		if (array.length() > 1) { // 判断长度
			return array.toString();
		}
		return array.toString().substring(1, array.toString().length() - 1);
	}

	// main
	public static void main(String[] args) {
		
		JDBC jd = new JDBC();
		String string = jd.sqlSelect("select * from face_camera");
		System.out.println(string);
		jd.close();
		
	}

}
