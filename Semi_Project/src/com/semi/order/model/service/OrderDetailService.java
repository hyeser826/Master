package com.semi.order.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import com.semi.cart.model.vo.Cart;
import com.semi.common.JDBCTemplate;
import com.semi.order.model.dao.OrderDetailDao;
import com.semi.order.model.vo.OrderDetail;

public class OrderDetailService {

	public ArrayList<OrderDetail> selectOrderDetailList(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<OrderDetail> odList = new OrderDetailDao().selectOrderDetailList(conn,orderNo);
		JDBCTemplate.close(conn);
		
		
		return odList;
	}

	public ArrayList<OrderDetail> selectOrderDetail(int ono) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<OrderDetail> odList = new OrderDetailDao().selectOrderDetail(conn, ono);
		
		JDBCTemplate.close(conn);
		
		return odList;
	}

	public int chStatusCancel(int detailOno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().chStatusCancel(conn,detailOno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int chStatusConfirm(int detailOno) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().chStatusConfirm(conn,detailOno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int insertOrderDetail(int orderNo, ArrayList<Cart> buyList) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().insertOrderDetail(conn, orderNo,buyList);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	//비회원 주문시 주문번호 받기
	public int insertBOrderDetail(int orderNo, Date eventDate, int pno, int price, int amount, String strName) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().insertBOrderDetail(conn, orderNo, eventDate, pno, price, amount, strName);

		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	//회원 주문시 주문번호 받기
	public int insertMOrderDetail(int orderNo, Date eventDate, int pno, int price, int amount, String strName) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().insertMOrderDetail(conn, orderNo, eventDate, pno, price, amount, strName);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int chStatus(int detailOno, String detailOnoText) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OrderDetailDao().chStatus(conn,detailOno,detailOnoText);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	 

}
