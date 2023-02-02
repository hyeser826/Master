package com.semi.refund.model.service;

import java.sql.Connection;

import com.semi.common.JDBCTemplate;
import com.semi.order.model.dao.OrderDetailDao;
import com.semi.order.model.vo.OrderDetail;
import com.semi.refund.model.dao.RefundDao;
import com.semi.refund.model.vo.Refund;

public class RefundService {
	//환불 요청 폼에 띄워줄 상품 조회 메소드
	public OrderDetail selectRefundProduct(int orderDetailNo) {
		Connection conn = JDBCTemplate.getConnection();
		OrderDetail od = new RefundDao().selectRefundProduct(conn,orderDetailNo);
		JDBCTemplate.close(conn);
		return od;
	}
	//환불 요청 데이터 삽입 메소드
	public int insertRefund(Refund r) {
		Connection conn = JDBCTemplate.getConnection();
		String detailOnoText = "환불접수완료";
		
		int result1 = new RefundDao().insertRefund(conn,r);
		int result2 = new OrderDetailDao().chStatus(conn,r.getOrderDetailNo(),detailOnoText);
		
		int result = result1*result2;
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
