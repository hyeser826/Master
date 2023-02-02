package com.semi.JY.review.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.member.model.dao.MemberDao;
import com.semi.board.model.vo.Board;
import com.semi.member.model.vo.Member;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.vo.ProductBoard;

public class ReviewDao {
	private Properties prop = new Properties();

	public ReviewDao() {

		String filePath = ReviewDao.class.getResource("/db/sql/review-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("review-mapper경로 이상!");
		}

	}

	//아래는 리뷰게시판 게시글 추가메소드
//	public int insertBoardReview(Connection conn,ProductBoard pb) {
//		int result=0;
//		PreparedStatement pstmt = null;
//		
//								//PRODUCT_BOARD(문의번호,상품번호(뺏음),문의내용,작성일자,게시글상태 )
//								//작성날짜 , 상품번호 , 리뷰내용 , 안보이는거 회원번호 ,문의번호?,게시글상태
//		String sql = prop.getProperty("insertBoardReview");
//		
//		try {
//			pstmt=conn.prepareStatement(sql);
//		
//			pstmt.setInt(1,pb.getMemNo()); //회원번호
//			pstmt.setString(2,pb.getProductBoardTitle());   //제목
//			pstmt.setInt(3,pb.getProductBoardPwd()); 		//작성글비밀번호
//			pstmt.setString(4,pb.getProductBoardContent()); //내용
//			pstmt.setInt(5,pb.getProductBoardHits()); //조회수
//			
//			result = pstmt.executeUpdate();  //업데이트 
//				
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			JDBCTemplate.close(pstmt);
//		}
//		System.out.println("리뷰dao 인데 1 나와?"+result);
//		return result;
//		
//	}

	
	
public ArrayList<ProductBoard> selectReviewList(Connection conn,int memNo,ProductBoard pb) {
		
		ArrayList<ProductBoard> list = new ArrayList<>();
		ResultSet rset = null; 
		PreparedStatement pstmt = null;
		
			//ProductBoard조회 (문의번호,상품번호,회원번호,문의내용,작성일자,게시글상태)
		String sql = prop.getProperty("selectReviewList");
		
		
		try {
			pstmt=conn.prepareStatement(sql);
			//Member회원번호와 일치시키는거 
			pstmt.setInt(1,memNo);
			pstmt.setInt(2,pb.getProductBoardNo());
			pstmt.setInt(3,pb.getProductNo());
	
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
			list.add(new ProductBoard(rset.getInt("PRODUCT_BOARD_NO") //문의번호
										,rset.getInt("PRODUCT_NO")//상품번호
										,rset.getInt("MEM_NO") //회원번호
										,rset.getString("PRODUCT_BOARD_CONTENT") //문의내용
										,rset.getDate("PRODUCT_BOARD_DATE") //작성날짜
										,rset.getString("STATUS") //상태
							
					));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		System.out.println("리뷰 DAO list : " +list);
		return list;
	}


//게시글 상태만 N으로 바꿔줄려고 UPDATE 써서 ProductBoard테이블에서! 내가쓴리뷰니까 MemNo 가져가기!
public int ReviewDelete(Connection conn, int memNo) {
	int result = 0;
	PreparedStatement pstmt =null;
	
	String sql = prop.getProperty("ReviewDelete");
	
	try {
		pstmt=conn.prepareStatement(sql);
	
		pstmt.setInt(1,memNo);
		
		result=pstmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(pstmt);
	
	}
	System.out.println("리뷰 지우는거 성공? "+ result);
	return result;
}

//아래는 리뷰 내용만 수정하기 
public int reviewModifyContent(Connection conn,ProductBoard pb) {
	int result = 0;
	PreparedStatement pstmt =null;
	
	String sql = prop.getProperty("reviewModifyContent");
	
	try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,pb.getProductNo());
		pstmt.setInt(2,pb.getProductBoardNo());
		pstmt.setString(3,pb.getProductBoardContent());
		
		result=pstmt.executeUpdate();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(pstmt);
	}
	System.out.println("리뷰내용수정 성공했냐"+result);
	return result;
}

}