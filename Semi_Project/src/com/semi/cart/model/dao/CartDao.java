package com.semi.cart.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.board.model.vo.Board;
import com.semi.cart.model.vo.Cart;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;

public class CartDao {
	
	private Properties prop = new Properties();

	public CartDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(CartDao.class.getResource("/db/sql/cart-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//장바구니 리스트 페이지 메소드 : ArrayList<Cart> cart반환 ssnId로 조회 상태Y
	public ArrayList<Cart> cartMdList(Connection conn, String memId) {
		
		//select 문 
		ResultSet rset = null;
		ArrayList<Cart> mdList = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("cartMdList");

		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
			   mdList.add(new Cart(rset.getInt("CART_NO")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("CATEGORY_NAME")
								  ,rset.getInt("PRODUCT_NO")
								  ,rset.getString("PRODUCT_NAME")
								  ,rset.getInt("PRODUCT_PRICE")
								  ,rset.getInt("AMOUNT")
								  ,rset.getInt("TOTAL_PRICE")
								  ,rset.getInt("DELIVERY_FEE")
								  ,rset.getDate("E_DATE")
//								  ,rset.getString("SSN_ID")
								  ,rset.getString("MEM_ID")
								  ,rset.getString("MEM_FLAG")
								  ,rset.getDate("CART_DATE")
								  ,rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		
		return mdList;
	}
	
	//장바구니 리스트 페이지 메소드 : ArrayList<Cart> cart반환 ssnId로 조회 상태Y
	public ArrayList<Cart> cartAcList(Connection conn, String memId) {
		
		//select 문 
		ResultSet rset = null;
		ArrayList<Cart> acList = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("cartAcList");

		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
			   acList.add(new Cart(rset.getInt("CART_NO")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("CATEGORY_NAME")
								  ,rset.getInt("PRODUCT_NO")
								  ,rset.getString("PRODUCT_NAME")
								  ,rset.getInt("PRODUCT_PRICE")
								  ,rset.getInt("AMOUNT")
								  ,rset.getInt("TOTAL_PRICE")
								  ,rset.getInt("DELIVERY_FEE")
								  ,rset.getDate("E_DATE")
//								  ,rset.getString("SSN_ID")
								  ,rset.getString("MEM_ID")
								  ,rset.getString("MEM_FLAG")
								  ,rset.getDate("CART_DATE")
								  ,rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		
		return acList;
	}
	
	
	// 장바구니 추가 : int procductNo 조회해오기 Cart객체로 조회
	public ArrayList<Cart> selectCart(Connection conn, Cart c) {
		
		//select 문 
		ResultSet rset = null;
		ArrayList<Cart> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectCart");

		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, c.getMemId());
			pstmt.setInt(2, c.getCartNo());

			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Cart(rset.getInt("CART_NO")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("CATEGORY_NAME")
								  ,rset.getInt("PRODUCT_NO")
								  ,rset.getString("PRODUCT_NAME")
								  ,rset.getInt("PRODUCT_PRICE")
								  ,rset.getInt("AMOUNT")
								  ,rset.getInt("TOTAL_PRICE")
								  ,rset.getInt("DELIVERY_FEE")
								  ,rset.getDate("E_DATE")
//								  ,rset.getString("SSN_ID")
								  ,rset.getString("MEM_ID")
								  ,rset.getString("MEM_FLAG")
								  ,rset.getDate("CART_DATE")
								  ,rset.getString("STATUS")));
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
	

	public ArrayList<Cart> selectbuyList(Connection conn, String cartNo) {
		//select 문 
		ResultSet rset = null;
		ArrayList<Cart> buylist = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectbuyList");
////////////////////////////////////////////////////////////////////////////수정
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, cartNo);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
			  buylist.add(new Cart(rset.getInt("CART_NO")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("CATEGORY_NAME")
								  ,rset.getInt("PRODUCT_NO")
								  ,rset.getString("PRODUCT_NAME")
								  ,rset.getInt("PRODUCT_PRICE")
								  ,rset.getInt("AMOUNT")
								  ,rset.getInt("TOTAL_PRICE")
								  ,rset.getInt("DELIVERY_FEE")
								  ,rset.getDate("E_DATE")
//								  ,rset.getString("SSN_ID")
								  ,rset.getString("MEM_ID")
								  ,rset.getString("MEM_FLAG")
								  ,rset.getDate("CART_DATE")
								  ,rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		
		return buylist;
	}
	
	
		public int insertMemCart(Connection conn, Cart c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMemCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getProductNo());
			pstmt.setInt(2, c.getProductNo());
			pstmt.setInt(3, c.getAmount());
			pstmt.setInt(4, c.getTotalPrice());
			pstmt.setString(5, c.getMemId());
			pstmt.setDate(6, c.getEventDate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	

	
	public int updateAmount(Connection conn, Cart c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAmount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getAmount());
			pstmt.setInt(2, c.getTotalPrice());
			pstmt.setInt(3, c.getProductNo());
			pstmt.setString(4, c.getMemId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	
	//선택상품 삭제 productNo, ssnId로 조회해서 amount 0, status N으로 수정
	public int deleteCart(Connection conn, String cartNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, cartNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int insertMdCart(Connection conn, Cart c) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMdCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getMstNo());
			pstmt.setInt(2, c.getProductNo());
			pstmt.setInt(3, c.getAmount());
			pstmt.setInt(4, c.getTotalPrice());
			pstmt.setString(5, c.getMemId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public int insertActivityCart(Connection conn, Cart c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertActivityCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getMstNo());
			pstmt.setInt(2, c.getProductNo());
			pstmt.setInt(3, c.getAmount());
			pstmt.setInt(4, c.getTotalPrice());
			pstmt.setDate(5, c.getEventDate());
			pstmt.setString(6, c.getMemId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public int updateAmountCart(Connection conn, String memId, int cartNo, int amount,int acProNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAmountCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, amount);
			pstmt.setInt(2, amount);
			pstmt.setInt(3, acProNo);
			pstmt.setInt(4, cartNo);
			pstmt.setString(5, memId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateAcAmount(Connection conn, Cart c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAcAmount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getAmount());
			pstmt.setInt(2, c.getTotalPrice());
			pstmt.setInt(3, c.getProductNo());
			pstmt.setString(4, c.getMemId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteOneCart(Connection conn, String memId, int cartNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteOneCart");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, cartNo);
			pstmt.setString(2, memId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
//	public int selectBeforeAcCart(Connection conn, Cart c) {
//		ResultSet rset = null;
//		int cartNo = 0;
//		PreparedStatement pstmt = null;
//		
//		String sql = prop.getProperty("selectCart");
//
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//		
//			pstmt.setString(1, c.getMemId());
//			pstmt.setDate(2, c.getEventDate());
//			pstmt.setInt(3, c.getProductNo());
//
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {				
//				cartNo = rset.getInt("CART_NO");								  
//			}else {
//				cartNo = 0;
//			}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//
//		
//		return cartNo;
//	}
//
//	
//	public int selectBeforeMdCart(Connection conn, Cart c) {
//		ResultSet rset = null;
//		int check = 0;
//		PreparedStatement pstmt = null;
//		
//		String sql = prop.getProperty("selectCart");
//
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//		
//			pstmt.setString(1, c.getMemId());
//			pstmt.setInt(2, c.getProductNo());
//
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				
//				check = rset.getInt("CART_NO");
//								  
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//
//		
//		return check;
//	}

	public int afterOrderCart(Connection conn, ArrayList<Cart> buyList) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteCart");
		
		try {
			
			for(Cart c : buyList) {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getCartNo());
			
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
}
