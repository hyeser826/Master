package com.semi.JY.pom.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.JY.master.model.vo.Master;
import com.semi.JY.orderdetail.model.vo.OrderDetail;

import com.semi.JY.pom.model.dao.DeliveryDao;
import com.semi.JY.pom.model.vo.Delivery;
import com.semi.JY.product.model.vo.Product;


public class DeliveryService {
	
		//오더디테일 주문상세조회 (장인번호가져와서)
	public ArrayList<OrderDetail> selectBoard(int memNo,OrderDetail od,Master m ) {
		Connection conn= JDBCTemplate.getConnection();
				
		ArrayList<OrderDetail> list =new DeliveryDao().selectDeliveryBoard(conn,memNo,od,m);
		
		JDBCTemplate.close(conn); 
		
		return list;
	}
	
	
	//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
	//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
	//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
		public ArrayList<OrderDetail> changeOrderStatus(int memNo,Master m, OrderDetail od,Product p) {
	
		Connection conn= JDBCTemplate.getConnection();
		//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
		int result =new DeliveryDao().changeOrderStatus(conn,memNo,m,od,p);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}	
				//아래는 다시 ArrayList로 목록갱신
		ArrayList<OrderDetail> list2 =new DeliveryDao().selectchangeOrderStatus(conn,memNo,p,m,od);
		JDBCTemplate.close(conn); 
		return list2;
	}
		
		
		
	//아래는결제완료에서 상품준비중//
	//아래는결제완료에서 상품준비중//
	public int changeOrderStatus2(int memNo,Master m,OrderDetail od,Product p) {
		Connection conn= JDBCTemplate.getConnection();
		//처리상태 를결제완료에서 상품준비중으로 update해서 변경해주려고 
		int result2 =new DeliveryDao().changeOrderStatus2(conn,memNo,m,od,p);
		if(result2>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
//		ArrayList<OrderDetail>list3 = new DeliveryDao().selectchangeOrderStatus2(conn,memNo,m,od);
		JDBCTemplate.close(conn); 
		return result2;
	}
	
	
	//송장등록
	public ArrayList<Delivery> invoiceEnroll(int memNo,Delivery dv,OrderDetail od,Master m,Product p) {
		Connection conn= JDBCTemplate.getConnection();
		// 필요한건 주문번호랑  송장번호 택배사  //insert
		int result=new DeliveryDao().invoiceInsertEnroll(conn,memNo,dv,od,m,p);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
				}else {
			JDBCTemplate.rollback(conn);
				}
		//주문번호,송장등록,택배사등록 이후  처리상태를  '배송완료로'변경
		//주문번호,송장등록,택배사등록 이후  처리상태를  '배송완료로'변경
		//주문번호,송장등록,택배사등록 이후  처리상태를  '배송완료로'변경
		int result3=new DeliveryDao().changeOrderStatus3(conn,memNo,m,od,p);
		
		if(result3>0) {
			JDBCTemplate.commit(conn);
			}else {
			JDBCTemplate.rollback(conn);
			}
		//커밋후 select 조회 - 갱신
		ArrayList<Delivery> list=new DeliveryDao().selectDeliveryInvoiceList(conn,memNo,od);
	
		JDBCTemplate.close(conn);
		return list;
	}
	
	


	//'상품준비중' select 
	public ArrayList<OrderDetail> selectProductReady(OrderDetail od) {
		Connection conn= JDBCTemplate.getConnection();
		
		ArrayList<OrderDetail> list6=new DeliveryDao().selectProductReady(conn,od);
		
		JDBCTemplate.close(conn);
		return list6;
		
	}
	
	
	//마스터 테이블에 장인번호찾으려고  전체조회하려고 하는중
	public Master selectMasterNo(int memNo) {
			Connection conn= JDBCTemplate.getConnection();
		
			Master m=new DeliveryDao().selectMasterNo(conn,memNo);
			JDBCTemplate.close(conn);
			
			return m;
			
			
	}


	

	//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
	public Product selectProductMasterNo(int memNo) {
		Connection conn= JDBCTemplate.getConnection();
		
		Product p=new DeliveryDao().selectProductMasterNo(conn,memNo);
	
		JDBCTemplate.close(conn);
		
		return p;
		
	}

	//결제완료상태에서 주문거절 
	//결제완료상태에서 주문거절 
	//결제완료상태에서 주문거절 
	
	public ArrayList<OrderDetail>  RefuseOrder(Product p, OrderDetail od, Master m) {
		Connection conn= JDBCTemplate.getConnection();
		
		int result =new DeliveryDao().RefuseOrder(conn,p,od,m);
	
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//주문거절 상태로 orderDetail조회해서 list로 받아오기  	
			ArrayList<OrderDetail> list5=new DeliveryDao().selectRefuseOrderDetail(conn,p,od,m);
			JDBCTemplate.close(conn);
			
			return list5;
	}

	// 오더디테일 조회  (유저)
	public OrderDetail selectOrderDetailNo(int memNo) {
		Connection conn= JDBCTemplate.getConnection();
		
		OrderDetail od=new DeliveryDao().selectOrderDetailNo(conn,memNo);
		return od;
	}

	//딜리버리에서 송장 벨류값줄려고 ! 
	public Delivery selectinvoiceEnroll(int memNo, OrderDetail od) {
	Connection conn= JDBCTemplate.getConnection();
		
	Delivery dv=new DeliveryDao().selectDeliveryInvoiceList2(conn,od);
	
		JDBCTemplate.close(conn);
		return dv;
	}



}

