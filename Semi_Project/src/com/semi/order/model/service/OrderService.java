package com.semi.order.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.order.model.dao.OrderDao;
import com.semi.order.model.vo.Order;
import com.semi.order.model.vo.OrderDetail;

public class OrderService {

	public ArrayList<Order> selectOrderList(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Order> list = new OrderDao().selectOrderList(conn,memNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public Order selectOrder(int ono) {
		Connection conn = JDBCTemplate.getConnection();
		
		Order o = new OrderDao().selectOrder(conn,ono);
		
		JDBCTemplate.close(conn);
		return o;
	}

	
	//구매하기로 바로받은 주문 주문서 만들기
	public int insertNewOrder(String memId, String reciverName, String reciverPhone, String orderAddress, int totalDel) {

		Connection conn = JDBCTemplate.getConnection();
		
		int orderNo = new OrderDao().insertNewMOrder(conn,memId,reciverName,reciverPhone,orderAddress,totalDel);

		if(orderNo>0) {//주문서 입력되었음
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		
		return orderNo;
	}

	//구매하기로 바로받은 비회원주문 주문서 만들기
	public int insertNewBOrder(int bmemNo, String reciverName, String reciverPhone, String orderAddress, int delFee) {
		Connection conn = JDBCTemplate.getConnection();
		
		int orderNo = new OrderDao().insertNewBOrder(conn,bmemNo,reciverName,reciverPhone,orderAddress,delFee);
		
		if(orderNo>0) {//주문서 입력되었음
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return orderNo;
	}

}
