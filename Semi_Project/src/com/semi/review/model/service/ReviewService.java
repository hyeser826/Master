package com.semi.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.review.model.dao.ReviewDao;
import com.semi.review.model.vo.Review;

public class ReviewService {
	//리뷰 등록 메소드
	public int insertReview(Review r) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ReviewDao().insertReview(conn,r);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//상품상세페이지 리뷰 조회해오는 에이젝스
	public ArrayList<Review> selectProductReviewList(int pno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> list = new ReviewDao().selectProductReviewList(conn,pno);
		
		JDBCTemplate.close(conn);
		return list;
	}

}
