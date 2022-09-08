package com.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Driver;

public class JdbcUtils {
/*
	// �������ݿ���û���
	private final String USERNAME = "root";
	// �������ݿ������
	private final String PASSWORD = "123456";
	// �������ݿ��������Ϣ
	private final String DRIVER = "com.mysql.jdbc.Driver";
	// ����������ݿ�ĵ�ַ
	private final String URL = "jdbc:mysql://localhost:3306/mydb";
*/
	// �������ݿ��������Ϣ
	private final String DRIVER = "org.sqlite.JDBC";
	// ����������ݿ�ĵ�ַ
	private final String URL = "jdbc:sqlite:/Users/xinxingwu/mydb.db";
	// ����������ݿ������
	private Connection connection;
	// ����sql����ִ�ж���
	private PreparedStatement pstmt;
	// �����ѯ���صĽ������
	private ResultSet resultSet;
	
	// ʵ��������Ĺ���
	private Statement stmt;

	public JdbcUtils() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(DRIVER);
			System.out.println("ע�������ɹ�����");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ע������ʧ�ܣ���");
		}

	}
	// ���������ݿ������
	public Connection getConnection() {
		try {
			// �����MySQL����� connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			 connection = DriverManager.getConnection(URL);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Connection exception !");
		}
		return connection;

	}
	
	
	
	/** ʵ��������ɾ��
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteByBatch(String[] sql) throws SQLException{
		boolean flag = false;
		stmt = connection.createStatement();
		if (sql!=null) { //�ж������Ƿ�Ϊ�գ�������length���жϣ�������ܻᱨ��ָ���쳣��
			
			for(int i = 0 ; i<sql.length ; i++){
				stmt.addBatch(sql[i]);
			}
			
			int[] count = stmt.executeBatch();
			if (count!=null) {
				flag = true;
			}
			
		}	
		return flag;		
	}

	/**
	 * ��ɶ����ݿ�������ɾ�����޸ĵĲ���
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;// ��ʾ���û�ִ������ɾ�����޸ĵĲ���Ӱ�������
		int index = 1; // ��ʾ ռλ�� ����1��ʼ
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i)); // ���ռλ��
			}
		}

		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;

	}

	/**
	 * ��ѯ���ص�����¼
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public  Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery(); // ���ز�ѯ���

		ResultSetMetaData metaData = pstmt.getMetaData(); // ��ȡ ����У�һ�������еĽ��
		int cols_len = metaData.getColumnCount(); // ����е�����

		while (resultSet.next()) {
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); // ��õ�i�е��ֶ�����
				Object col_value = resultSet.getObject(col_name);// ���� ��i�е�����ֵ
				if (col_value == null) {
					col_value = "";
				}
				map.put(col_name, col_value);
			}

		}

		return map;
	}

	/**
	 * ��ѯ���ض�����¼
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		pstmt = connection.prepareStatement(sql);
		int index = 1; // ��ʾռλ��
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery(); // ���ز�ѯ�������
		ResultSetMetaData metaData = resultSet.getMetaData(); // ����еĽ��

		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			int cols_len = metaData.getColumnCount(); // ��ȡ�ܵ�����
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); // ��ȡ�� i�е��ֶ�����
																	// ,�м����1��ʼ
				Object col_value = resultSet.getObject(col_name); // ��ȡ��i�е�����ֵ
				if (col_value == null) {
					col_value = "";
				}

				map.put(col_name, col_value);
			}
			list.add(map);
		}

		return list;

	}

	/**
	 * ��ѯ���ص���JavaBean(ʹ��java�������)
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1; // ռλ��
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i)); // ���ռλ��
			}
		}
		resultSet = pstmt.executeQuery(); // ��ȡ��ѯ���

		ResultSetMetaData metaData = resultSet.getMetaData(); // ��ȡ�е���Ϣ
		int cols_len = metaData.getColumnCount(); // ��ȡ�ܵ�����
		while (resultSet.next()) {
			// ͨ��������ƴ���ʵ��
			resultObject = cls.newInstance(); // java�������
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); // ��ȡ��i�е�����
				Object col_value = resultSet.getObject(col_name); // ��ȡ��i�е�ֵ
				if (col_value == null) {
					col_value = "";
				}
				Field field = cls.getDeclaredField(col_name);
				field.setAccessible(true);// �� JavaBean�ķ��� privateȨ��
				field.set(resultObject, col_value);
			}

		}

		return resultObject;
	}

	/** ��ѯ���ض��JavaBean(ͨ��java�������)
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1; //ռλ��
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery(); // ���ز�ѯ�������

		ResultSetMetaData metaData = resultSet.getMetaData(); // �����е���Ϣ
		int cols_len = metaData.getColumnCount(); // ��������ܵ�����
		while (resultSet.next()) {
			// ͨ��������ƴ���һ��javaʵ��
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String col_name = metaData.getColumnName(i + 1); // ��õ�i�е�����
				Object col_value = resultSet.getObject(col_name); // ��õ�i�е�����
				if (col_value == null) {
					col_value = "";
				}
				Field field = cls.getDeclaredField(col_name);
				field.setAccessible(true); // ��JavaBean�ķ���privateȨ��
				field.set(resultObject, col_value);
			}
			list.add(resultObject);

		}

		return list;
	}
	
	/**�ر����ݿ����
	 * @throws SQLException
	 */
	public void releaseConn(){
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		if(stmt!=null){
			
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	
}