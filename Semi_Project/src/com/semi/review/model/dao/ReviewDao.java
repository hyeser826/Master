package com.semi.review.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.review.model.vo.Review;

public class ReviewDao {
	
	private Properties prop = new Properties();
	
	public ReviewDao() {
		try {
			prop.loadFromXML(new FileInputStream(ReviewDao.class.getResource("/db/sql/review-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//리뷰 등록 메소드
	public int insertReview(Connection conn, Review r) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getProductNo());
			pstmt.setInt(2, r.getMemNo());
			pstmt.setString(3, r.getProductBoardContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//상품상세페이지 리뷰 조회해오는 에이젝스
	public ArrayList<Review> selectProductReviewList(Connection conn, int pno) {
		
		ResultSet rset = null;
		ArrayList<Review> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectProductReviewList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Review(rset.getInt("PRODUCT_BOARD_NO")
						,rset.getString("PRODUCT_BOARD_CONTENT")
						,rset.getDate("PRODUCT_BOARD_DATE")
						,rset.getString("MEM_ID")
						
						));
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

}
