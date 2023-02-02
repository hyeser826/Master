package com.semi.refund.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.order.model.vo.OrderDetail;
import com.semi.refund.model.vo.Refund;

public class RefundDao {
	
	private Properties prop = new Properties();
	
	public RefundDao() {
		try {
			prop.loadFromXML(new FileInputStream(RefundDao.class.getResource("/db/sql/refund-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//환불 요청 폼에 띄워줄 상품 조회 메소드
	public OrderDetail selectRefundProduct(Connection conn, int orderDetailNo) {
		OrderDetail od = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectRefundProduct");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderDetailNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				od = new OrderDetail();
				od.setOrderDetailNo(rset.getInt("ORDER_DETAIL_NO"));
				od.setTitleImg(rset.getString("TITLEIMG"));
				od.setProductName(rset.getString("PRODUCT_NAME"));
				od.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				od.setProductCount(rset.getInt("PRODUCT_COUNT"));
				od.setStrName(rset.getString("STR_NAME"));
				od.setOrderNo(rset.getInt("ORDER_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return od;
	}
	//환불 요청 데이터 삽입 메소드
	public int insertRefund(Connection conn, Refund r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertRefund");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getOrderDetailNo());
			pstmt.setString(2, r.getRefundReason());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
		
}
