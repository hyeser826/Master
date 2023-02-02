package com.semi.cart.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.vo.Board;
import com.semi.cart.model.dao.CartDao;
import com.semi.cart.model.vo.Cart;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;

public class CartService {


	//장바구니 리스트 페이지 메소드 : ArrayList<Cart> cart반환 ssnId로 조회 상태Y
	public ArrayList<Cart> cartMdList(String memId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Cart> mdList = new CartDao().cartMdList(conn, memId);

		JDBCTemplate.close(conn);

		
		return mdList;
	}
	
	public ArrayList<Cart> cartAcList(String memId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Cart> acList = new CartDao().cartAcList(conn, memId);

		JDBCTemplate.close(conn);

		
		return acList;
	}
	// 장바구니 추가 : int procductNo 조회해오기 Cart객체로 조회
//	public int selectBeforeCart(Cart c) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		int cartNo = 0;
//		if(c.getCategoryNm().equals("0.체험")) {			
//			cartNo = new CartDao().selectBeforeAcCart(conn, c);
//		}else {			
//			cartNo = new CartDao().selectBeforeMdCart(conn, c);
//		}
//		
//		int result = 0;
//
//		if(cartNo>0) {	

//		상품갯수 변경(1개이상&&재고이하)
//		int result update된 행수 반환 장바구니 동일상품 있으면 실행
//			result= new CartDao().updateAmount(conn, c, cartNo);
//		} else {	
//			int result insert된 행수 반환 장바구니 동일상품 없을시 실행
//			장바구니 상품행 추가
//			result= new CartDao().insertMemCart(conn, c);
//		}
//		
//
//		if(result>0) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
//		
//		JDBCTemplate.close(conn);
//
//		
//		return result;
//	}
	
	//장바구니에서 사려고 고른애들
	public ArrayList<Cart> selectbuyList(String cartNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Cart> buylist = new CartDao().selectbuyList(conn, cartNo);
	
		JDBCTemplate.close(conn);

		return buylist;
	}

	
	
	//선택상품 삭제 productNo, ssnId로 조회해서 amount 0, status N으로 수정
//	public int deleteCart(String cartNo) {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		int result = new CartDao().deleteCart(conn, cartNo);
//		
//		if(result>0) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
//		
//		JDBCTemplate.close(conn);
//	
//		
//		return result;
//		
//	}
	
	//카트 수량0, STATUS = n처리
	public int afterOrderCart(ArrayList<Cart> buyList) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new CartDao().afterOrderCart(conn, buyList);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	
		
		return result;
	}


	//일반상품 장바구니 인서트
		public int insertMdCart(Cart c) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new CartDao().insertMdCart(conn,c);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
		
			
			return result;
		}
		//체험 상품 인서트
		public int insertActivityCart(Cart c) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new CartDao().insertActivityCart(conn,c);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
		
			
			return result;
		}
		
		
	    //장바구니에서 수량변경시 실행될 업데이트문
		public int updateAmountCart(String memId, int cartNo, int amount, int acProNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new CartDao().updateAmountCart(conn,memId,cartNo,amount,acProNo);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
		
			
			return result;
		}
		//장바구니 한행 지우는 구문
		public int deleteOneCart(String memId, int cartNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new CartDao().deleteOneCart(conn,memId,cartNo);
			
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
		
			
			return result;
		}
	
	
		// 장바구니 추가 : int procductNo 조회해오기 Cart객체로 조회
		public int selectCart(Cart c) {
			
		Connection conn = JDBCTemplate.getConnection();
		
		//장바구니에 담은 상품이 있는지 확인
		ArrayList<Cart> list = new CartDao().selectCart(conn, c);		
		int result = 0;
		if(list.isEmpty() || list==null) {//리스트가 비어있으면 인서트해주기
			if(c.getEventDate()!=null) {
				
				result = new CartDao().insertActivityCart(conn,c);
			}else {
				result = new CartDao().insertMdCart(conn,c);
				
			}
		
		} else {//리스트에 담긴 상품이 있을때는 업데이트문으로 수량 변경해주기
//			상품갯수 변경(1개이상&&재고이하)
//			int result update된 행수 반환 장바구니 동일상품 있으면 실행
		
	     if(c.getCategoryNm().equals("0.체험")) {
	    	int count =0;
		for(int i=0;list.size()>i;i++) {
			if(list.get(i).getEventDate().equals(c.getEventDate())) {
				count++;
			}
				
		}
			if(count>0) {
				result = new CartDao().updateAcAmount(conn,c);
			}else {
				result = new CartDao().insertActivityCart(conn,c);
			}
	     }else {
	    	
	    	 result = new CartDao().updateAmount(conn,c);
	     }
		
				
		}
		
		return result;
	}
	
	
}
