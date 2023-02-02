package com.semi.order.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.JY.pom.model.vo.Delivery;
import com.semi.common.JDBCTemplate;
import com.semi.order.model.vo.Order;


public class OrderDao {
	private Properties prop = new Properties();
		
	public OrderDao() {
		try {
			prop.loadFromXML(new FileInputStream(OrderDao.class.getResource("/db/sql/orderr-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Order> selectOrderList(Connection conn, int memNo) {
		//select문
		
		ArrayList<Order> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();

			while(rset.next()) {
				list.add(new Order(rset.getInt("ORDER_NO"),
								   rset.getInt("MEM_NO"),
								   rset.getString("PAY_NAME"),
								   rset.getString("ORDER_ADDRESS"),
								   rset.getString("RECIVER_NAME"),
								   rset.getString("RECIVER_PHONE"),
								   rset.getDate("ORDER_DATE"),
								   rset.getInt("TOTAL_DEL")
								  )
				);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public Order selectOrder(Connection conn, int ono) {
		// select문
		
		Order o = new Order();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				o = new Order (rset.getInt("ORDER_NO")
								,rset.getInt("MEM_NO")
								,rset.getString("MEM_NAME")
								,rset.getString("PAY_NAME")
								,rset.getString("ORDER_ADDRESS")
								,rset.getString("RECIVER_NAME")
								,rset.getString("RECIVER_PHONE")
								,rset.getDate("ORDER_DATE")
								,rset.getInt("TOTAL_DEL"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return o;
	}

	

	public int insertNewOrder(Connection conn, String memId, String reciverName, String reciverPhone,
			String orderAddress, int totalDel) {

		PreparedStatement pstmt = null;
		int result =0;
		String sql = prop.getProperty("insertNewOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, orderAddress );
			pstmt.setString(3, reciverName);
			pstmt.setString(4, reciverPhone);
			pstmt.setInt(5, totalDel);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int selectCurOrderNo(Connection conn, String memId) {
		int orderNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCurOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				orderNo = rset.getInt("ORDER_NO");				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return orderNo;
	}

	public int insertNewMOrder(Connection conn, String memId, String reciverName, String reciverPhone,
			String orderAddress, int delFee) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		int orderNo = 0;
		String sql = prop.getProperty("insertNewMOrder");
		String sql2 = prop.getProperty("selectCurOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, orderAddress);
			pstmt.setString(3, reciverName);
			pstmt.setString(4, reciverPhone);
			pstmt.setInt(5, delFee);
			
			result = pstmt.executeUpdate();
			
			if(result>0) {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, memId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					orderNo = rset.getInt("ORDER_NO");
				}
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return result;
	}
	
	public int insertNewBOrder(Connection conn, int bmemNo, String reciverName, String reciverPhone,
			String orderAddress, int delFee) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result =0;
		int orderNo = 0;
		String sql = prop.getProperty("insertNewBOrder");
		String sql2 = prop.getProperty("bCurOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderAddress);
			pstmt.setString(2, reciverName);
			pstmt.setString(3, reciverPhone);
			pstmt.setInt(5, delFee);
			
			result = pstmt.executeUpdate();
			
			if(result>0) {
				pstmt = conn.prepareStatement(sql2);
				
				pstmt.setString(1, reciverName);
				pstmt.setString(2, reciverPhone);
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					orderNo = rset.getInt("ORDER_NO");
				}
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}

		return result;
	}
	
	public Delivery selectDelivery(Connection conn, int ono) {
		// select문
		
		Delivery d = new Delivery();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectDelivery");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			
			rset = pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return d;
	}
	
	
}
