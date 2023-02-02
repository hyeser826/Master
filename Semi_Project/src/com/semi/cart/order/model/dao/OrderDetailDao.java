package com.semi.cart.order.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.cart.model.vo.Cart;
import com.semi.common.JDBCTemplate;
import com.semi.cart.order.model.vo.Order;
import com.semi.cart.order.model.vo.OrderDetail;
import com.semi.product.model.dao.ProductDao;

public class OrderDetailDao {
	private Properties prop = new Properties();
	
	public OrderDetailDao() {
		try {
			prop.loadFromXML(new FileInputStream(OrderDetailDao.class.getResource("/db/sql/order-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<OrderDetail> selectOrderDetailList(Connection conn, int orderNo) {
		// select문
		ArrayList<OrderDetail> odList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderDetailList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				odList.add(new OrderDetail(
										   rset.getString("PRODUCT_NAME"),
										   rset.getInt("PRODUCT_PRICE"),
										   rset.getInt("PRODUCT_COUNT"),
										   rset.getString("ORDER_STATUS"),
										   rset.getString("TITLEIMG")
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
		
		return odList;
	}

	public ArrayList<OrderDetail> selectOrderDetail(Connection conn, int ono) {
		// select문
		ArrayList<OrderDetail> odList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ono);
			
			rset = pstmt.executeQuery();
		
			while(rset.next()) {
				odList.add(new OrderDetail (rset.getInt("ORDER_DETAIL_NO")
										,rset.getInt("PRODUCT_NO")
										,rset.getInt("ORDER_NO")
										,rset.getInt("PRODUCT_COUNT")
										,rset.getInt("PRODUCT_PRICE")
										,rset.getString("ORDER_STATUS")
										,rset.getDate("EXPDATE")
										,rset.getString("TITLEIMG")
										,rset.getString("PRODUCT_NAME")
										,rset.getInt("MASTER_NO")
										,rset.getString("DELIVERY_INVOICE_NO")
										,rset.getString("DELIVERY_PARCEL")
										,rset.getDate("DELIVERY_SHIP_DATE")
							));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return odList;
	}

	public int chStatusCancel(Connection conn, int detailOno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("chStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "주문취소");
			pstmt.setInt(2, detailOno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int chStatusConfirm(Connection conn, int detailOno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("chStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "구매확정");
			pstmt.setInt(2, detailOno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//주문서의 생성
	public int insertOrderDetail(Connection conn,int orderNo, ArrayList<Cart> buyList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertWithOrderNo");
		
		try {
			
			for(Cart c : buyList) {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getCartNo());
			pstmt.setInt(2, orderNo);
			pstmt.setInt(3, c.getCartNo());
			pstmt.setInt(4, (c.getTotalPrice()/c.getAmount()));
			pstmt.setInt(5, c.getCartNo());
			
			result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertBOrderDetail(Connection conn, int orderNo, Date eventDate, int pno, int price, int amount,
			String strName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = null;
		
		if(eventDate==null) {
			sql = prop.getProperty("insertBOrderDetail");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, amount);
				pstmt.setInt(4, price);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		
		}else{
		
			sql = prop.getProperty("eventBOrderDetail");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, amount);
				pstmt.setInt(4, price);
				pstmt.setDate(5, eventDate);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		}
		return result;
	}

	public int insertMOrderDetail(Connection conn, int orderNo, Date eventDate, int pno, int price, int amount,
			String strName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = null;
		
		if(eventDate==null) {
			sql = prop.getProperty("insertMOrderDetail");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, amount);
				pstmt.setInt(4, price);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		
		}else{
		
			sql = prop.getProperty("eventMOrderDetail");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, amount);
				pstmt.setInt(4, price);
				pstmt.setDate(5, eventDate);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
		}
		return result;
	}



}
