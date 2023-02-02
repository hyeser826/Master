package com.semi.JY.pom.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;

import com.semi.JY.master.model.vo.Master;
import com.semi.JY.orderdetail.model.vo.OrderDetail;
import com.semi.JY.pom.model.vo.Delivery;
import com.semi.JY.product.model.vo.Product;


public class DeliveryDao {
	private Properties prop = new Properties();

	
	public DeliveryDao() {

		String filePath = DeliveryDao.class.getResource("/db/sql/delivery-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("delivery-mapper경로 이상!");
		}

	}

	
	
					//pom.jsp에 장인번호가 포함된	//아래는 딜리버리 아니고 orderdetail  쫙 뿌려주기 
	public ArrayList<OrderDetail> selectDeliveryBoard(Connection conn,int memNo,OrderDetail od,Master m ) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<OrderDetail> list = new ArrayList<>();
							//아래는 딜리버리 메퍼
		String sql = prop.getProperty("selectDeliveryBoard");
		                                                                                                                             
		try {
			pstmt=conn.prepareStatement(sql);
			//아래 조회할꺼야 
		//	pstmt.setInt(1,memNo);
			pstmt.setInt(1,m.getMasterNo());
		
			
			rset=pstmt.executeQuery();
			
			//리스트에 담을꺼  // 다 담아 !  vo순서에 맞춰서 ! 
				while(rset.next()) {
					list.add(new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
											,rset.getInt("PRODUCT_NO")
											,rset.getInt("ORDER_NO")
											,rset.getInt("PRODUCT_COUNT")
											,rset.getInt("PRODUCT_PRICE")
											,rset.getString("ORDER_STATUS")
											,rset.getDate("EXPDATE")	
											,rset.getString("MEM_FLAG")
											));
										}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("selectDeliveryBoard: list"+list);
		return list;
	}
	
	
	
	
	//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
		public int changeOrderStatus(Connection conn, int memNo,Master m, OrderDetail od,Product p) {
	
		int result = 0;
		PreparedStatement pstmt=null;
		//처리상태 를 입금대기에서 결제완료  으로 update해서 변경해주려고 
		//장인번호 가져와야됨 . 
		ArrayList<OrderDetail> list= new ArrayList<>();
		
		String sql = prop.getProperty("changeOrderStatus");
		
		try {
			pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1,p.getMasterNo());
			pstmt.setInt(1,od.getOrderDetailNo());
				
			result=pstmt.executeUpdate();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		System.out.println(" 입금대기에서 결제완료 result : "+result);
		return result;
	}
		
		
		//아래는 처리상태 를결제완료에서 상품준비중으로 update해서 변경해주려고 
	public int changeOrderStatus2(Connection conn, int memNo,Master m,OrderDetail od,Product p) {
	
		ArrayList<OrderDetail>list2 = new ArrayList<>();
		int result2 = 0;
		PreparedStatement pstmt=null;
	
		String sql = prop.getProperty("changeOrderStatus2");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,od.getOrderDetailNo());
		
			
			//처리상태 를결제완료에서 상품준비중으로 update해서 변경해주려고 
			
			result2=pstmt.executeUpdate();
			System.out.println("result2 : "+result2);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("처리상태 를결제완료에서 상품준비중 업데이트성공 : "+result2);
		return result2;
	}
	
	
	
	//아래는 처리상태를 상품준비중-> 배송완료로 변경
	public int changeOrderStatus3(Connection conn,int memNo,Master m,OrderDetail od,Product p) {
		int result3 = 0;
		PreparedStatement pstmt=null;
		String sql = prop.getProperty("changeOrderStatus3");
		
		
		try {
			pstmt=conn.prepareStatement(sql);
	
		
			pstmt.setInt(1,od.getOrderDetailNo());
		
			result3=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("상품준비중에서 배송완료로 변경 됬나? : "+result3);
		return result3;
	}
	

	
	//update     주문번호 ㅡ송장번호 ㅡ 택배사 업데이트
	public int invoiceInsertEnroll(Connection conn, int memNo, Delivery dv,OrderDetail od,Master m,Product p) {
		
		int result=0; 
		PreparedStatement pstmt =null;
										//
		String sql = prop.getProperty("invoiceInsertEnroll");
	
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,dv.getOrderDetailNo()); //상세주문번호
			pstmt.setString(2,dv.getDeliveryInvoice()); //송장번호
			pstmt.setString(3,dv.getDeliveryParcel()); //택배사 
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		System.out.println("딜리버리dao 송장등록 되었나? 1이면 등록 :"+result);
		return result;
	}

		//위에 송장등록후 딜리버리 리스트조회하기 
	public ArrayList<Delivery> selectDeliveryInvoiceList(Connection conn, int memNo,OrderDetail od) {
	
		ArrayList<Delivery> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt =null;
		
		String sql = prop.getProperty("selectDeliveryInvoiceList");
		
		try {
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setInt(1,od.getOrderDetailNo());
			
			rset=pstmt.executeQuery();
			
			
			while(rset.next()) {
				list.add(new Delivery(rset.getInt("ORDER_DETAIL_NO")
									 ,rset.getString("DELIVERY_CATEGORY")
									 ,rset.getString("DELIVERY_INVOICE_NO")
									 ,rset.getString("DELIVERY_SHIPPING")
									 ,rset.getDate("DELIVERY_SHIP_DATE")
									 ,rset.getString("DELIVERY_PARCEL")
									 ,rset.getString("STATUS")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("딜리버리dao에서  송장등록리스트"+list);
		return list;
	}

	//아래는 다시 ArrayList로 목록갱신    입금대기 에서 결제완료 
	public ArrayList<OrderDetail> selectchangeOrderStatus(Connection conn, int memNo,Product p,Master m,OrderDetail od) {
	
		ArrayList<OrderDetail> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectchangeOrderStatus");
		
		try {
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setInt(1,od.getOrderDetailNo());
			
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
										,rset.getInt("PRODUCT_NO")
										,rset.getInt("ORDER_NO")
										,rset.getInt("PRODUCT_COUNT")
										,rset.getInt("PRODUCT_PRICE")
										,rset.getString("ORDER_STATUS")
										,rset.getDate("EXPDATE")	
										,rset.getString("MEM_FLAG")
										));
									}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			}
		System.out.println("입금대기 에서 결제완료 list 갱신성공"+list);
		return list;
		
	}
	
	//아래는 결제완료에서 상품준비중으로 띄운거 목록 띄우기  //사용안할수도있음
	public ArrayList<OrderDetail> selectchangeOrderStatus2(Connection conn, int memNo , Master m,OrderDetail od) {
		
		ArrayList<OrderDetail> list2 = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectchangeOrderStatus2");
	
		try {
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setInt(1,od.getOrderDetailNo());
		
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				list2.add(new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
										,rset.getInt("PRODUCT_NO")
										,rset.getInt("ORDER_NO")
										,rset.getInt("PRODUCT_COUNT")
										,rset.getInt("PRODUCT_PRICE")
										,rset.getString("ORDER_STATUS")
										,rset.getDate("EXPDATE")	
										,rset.getString("MEM_FLAG")
										));
									}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			System.out.println("list2는?"+list2);
			return list2;
	}


	//'상품준비중' select  조회
	public ArrayList<OrderDetail> selectProductReady(Connection conn,OrderDetail od) {
		ResultSet rset = null;
		PreparedStatement pstmt= null;
		ArrayList<OrderDetail> list6 = new 	ArrayList<>();
		
		String sql = prop.getProperty("selectProductReady");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,od.getOrderDetailNo());
		
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				list6.add(new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
										,rset.getInt("PRODUCT_NO")
										,rset.getInt("ORDER_NO")
										,rset.getInt("PRODUCT_COUNT")
										,rset.getInt("PRODUCT_PRICE")
										,rset.getString("ORDER_STATUS")
										,rset.getDate("EXPDATE")	
										,rset.getString("MEM_FLAG")
										));
									}
				
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("list6은 몇"+ list6);
		return list6;
	}


	//딜리버리에서 송장 벨류값줄려고 ! 
	public Delivery selectDeliveryInvoiceList2(Connection conn,OrderDetail od) {
		Delivery dv = new Delivery();
		ResultSet rset = null;
		PreparedStatement pstmt =null;
		
		String sql = prop.getProperty("selectDeliveryInvoiceList2");
		
		try {
			pstmt=conn.prepareStatement(sql);
	
		
			
			rset=pstmt.executeQuery();
			
			
			if(rset.next()) {
				dv=new Delivery(rset.getInt("ORDER_DETAIL_NO")
						 ,rset.getString("DELIVERY_CATEGORY")
						 ,rset.getString("DELIVERY_INVOICE_NO")
						 ,rset.getString("DELIVERY_SHIPPING")
						 ,rset.getDate("DELIVERY_SHIP_DATE")
						 ,rset.getString("DELIVERY_PARCEL")
						 ,rset.getString("STATUS")
			);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("딜리버리dao에서 조회"+dv);
		return dv;
	}


	//마스터 테이블에 장인번호 조회하려고 하는중
	public Master selectMasterNo(Connection conn,int memNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Master m = new Master ();
		
		
		String sql =prop.getProperty("selectMasterNo");
			
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,memNo);
	
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Master(rset.getInt("MASTER_NO")
							,rset.getInt("MEM_NO")
							,rset.getString("CO_NAME")
							,rset.getString("CO_NUMBER")
							,rset.getString("CEO")
							,rset.getString("CO_KIND")
							,rset.getString("STR_NAME")
							,rset.getString("MASTER_INTRO")
							,rset.getString("STATUS"));
						
						
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);
		}
		System.out.println("selectMasterNo m:"+m);
		return m;
	}


	
	//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
	public Product selectProductMasterNo(Connection conn,int memNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Product p = new Product();
		
		String sql = prop.getProperty("selectProductMasterNo");
		
		try {
			pstmt=conn.prepareStatement(sql);
		
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				p=new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getString("PRODUCT_DESCRIPTION")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("STATUS")
						,rset.getInt("DELIVERY_FEE")
						,rset.getString("EXP_PERIOD")
						);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
			return p;
	}

	//결제완료상태에서 주문거절 
	//결제완료상태에서 주문거절 
	//결제완료상태에서 주문거절 
	public int RefuseOrder(Connection conn, Product p, OrderDetail od, Master m) {
	
		int result = 0;
		PreparedStatement pstmt=null;
		//처리상태 를 결제완료 상태에서 주문거절  으로 update해서 변경해주려고 
		//장인번호 가져와야됨 . 
		ArrayList<OrderDetail> list= new ArrayList<>();
		
		String sql = prop.getProperty("RefuseOrder");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,p.getMasterNo());
				
			result=pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		System.out.println(" 결제완료에서  주문거절로 업데이트  : "+result);
		return result;
	}


	//주문거절 상태로 orderDetail조회해서 list로 받아오기  	
	//주문거절 상태로 orderDetail조회해서 list로 받아오기  	
	//주문거절 상태로 orderDetail조회해서 list로 받아오기  	
	public ArrayList<OrderDetail> selectRefuseOrderDetail(Connection conn, Product p, OrderDetail od, Master m) {
		
		ArrayList<OrderDetail> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRefuseOrderDetail");
		
		try {
			pstmt=conn.prepareStatement(sql);
	
			pstmt.setInt(1,m.getMasterNo());
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
										,rset.getInt("PRODUCT_NO")
										,rset.getInt("ORDER_NO")
										,rset.getInt("PRODUCT_COUNT")
										,rset.getInt("PRODUCT_PRICE")
										,rset.getString("ORDER_STATUS")
										,rset.getDate("EXPDATE")	
										,rset.getString("MEM_FLAG")
										));
									}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			}
		System.out.println(" 주문거절 list 갱신성공"+list);
		return list;
		
	}


	// 오더디테일 조회  (유저)
	public OrderDetail selectOrderDetailNo(Connection conn, int memNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		OrderDetail od = new OrderDetail ();
		
		
		String sql = prop.getProperty("selectOrderDetailNo");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,memNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				od = new OrderDetail(rset.getInt("ORDER_DETAIL_NO")
									,rset.getInt("PRODUCT_NO")
									,rset.getInt("ORDER_NO")
									,rset.getInt("PRODUCT_COUNT")
									,rset.getInt("PRODUCT_PRICE")
									,rset.getString("ORDER_STATUS")
									,rset.getDate("EXPDATE")
									,rset.getString("MEM_FLAG")
									);
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
		
	



}
