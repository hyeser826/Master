package com.semi.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	

		public static Connection getConnection() {
			
			Properties prop = new Properties(); //Map 계열 컬렉션 (key-value)
			
			//읽어들이고자 하는 driver.properties 파일 물리적인 경로
			//FileInputStream("드라이버.properties파일 경로");
			//실질적으로 배포되는 폴더는 WebContent이기 때문에 src/db/driver/driver.properties를 읽어도 의미가 없다.
			//실질적으로 배포되는 폴더안에 있는 classes폴더에 있는것을 읽어줘야한다.
			String filePath = JDBCTemplate.class.getResource("/db/driver/Driver.properties").getPath();
			//물리적인 경로 얻기 JDBCTemplate.class : 실질적인 클래스파일 경로
			
			try {
				prop.load(new FileInputStream(filePath));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB경로 이상!");
			}
			
			Connection conn = null;
			
			try {
				//1.jdbc 드라이버 등록
				Class.forName(prop.getProperty("driver"));
				
				//2.DB와 접속된 Connection 객체 생성 (url,username,password)
				conn = DriverManager.getConnection(prop.getProperty("url")
												 ,prop.getProperty("username")
												 ,prop.getProperty("password"));
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
		}
	
		public static void commit(Connection conn) {
			
			try {
				if(conn!=null&& !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void rollback(Connection conn) {
			
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		public static void close(Connection conn) {
			try {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		public static void close(Statement stmt) {
			try {
				if(stmt!=null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public static void close(ResultSet rset) {
			try {
				if(rset!=null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
