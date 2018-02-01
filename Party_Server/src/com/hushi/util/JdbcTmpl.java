package com.hushi.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTmpl {
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private PreparedStatement ps;
	private ResultSet rs;
	private Connection conn;
	private Statement st;

	static {

		try {
			// 1.ͨ����ǰ��ķ�����������ļ�
			InputStream in = JdbcTmpl.class.getClassLoader()
					.getResourceAsStream("db.properties");

			// 2.����Properties��Ķ���
			Properties pro = new Properties();
			pro.load(in);

			// 3.ͨ��keyֵ��������ļ��е�valueֵ
			URL = pro.getProperty("url").trim();
			USERNAME = pro.getProperty("username").trim();
			PASSWORD = pro.getProperty("password").trim();
			DRIVER = pro.getProperty("driver").trim();

			Class.forName(DRIVER);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void getPreparedStatement(String sql) {
		getConnection();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getStatement() {
		try {
			getConnection();
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������ѯ
	 */
	
		public ResultSet find(String sql, Object[] params) {
			
			try {
				getPreparedStatement(sql);
				this.bind(params);
				
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
			
		}

	/**
	 * ��ѯ����
	 */
	public ResultSet findAll(String sql) {
		getStatement();
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ����
	 */
	public boolean insert(String sql, Object[] params) {
		int flag = 0;
		try {
			getPreparedStatement(sql);
			this.bind(params);
			flag = ps.executeUpdate();
			if (flag != 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally {
			myCommit();
		}
		return false;
	}

	/**
	 * �޸�
	 */
	
	
	
	public boolean update(String sql) {
		int flag = 0;
		try {
			getPreparedStatement(sql);
			flag = ps.executeUpdate();
			if (flag != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally {
			myCommit();
		}
		return false;
	}
	public boolean update(String sql, Object[] params) {
		int flag = 0;
		try {
			getPreparedStatement(sql);
			this.bind(params);
			flag = ps.executeUpdate();
			if (flag != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally {
			myCommit();
		}
		return false;
	}

	/**
	 * ɾ��
	 */
	public boolean delete(String sql, Object[] params) {
		int flag = 0;

		try {
			getPreparedStatement(sql);
			this.bind(params);
			flag = ps.executeUpdate();
			if (flag != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.myRollback();
		} finally {
			myCommit();
		}
		return false;
	}

	
	

	/**
	 * �ع�
	 */
	public void myRollback() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ύ
	 */
	public void myCommit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * �ر���Դ
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * /**
	 * �󶨲���
	 */
	
	public void bind(Object[] params) {
		try {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
