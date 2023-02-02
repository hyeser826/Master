package com.semi.JY.review.model.service;

import java.util.ArrayList;
import java.sql.Connection;

import com.semi.common.JDBCTemplate;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.vo.ProductBoard;
import com.semi.JY.review.model.dao.ReviewDao;



public class ReviewService {
	
	//리뷰리스트 가지러가기  //reviewController에서 list에 담으려고 ! 
	public ArrayList<ProductBoard> selectReviewList(int memNo,ProductBoard pb) {
		Connection conn = JDBCTemplate.getConnection();
		//JDBCTemplate연결하기 
		ArrayList <ProductBoard> list = new ReviewDao().selectReviewList(conn,memNo,pb);
		
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	

	//게시글 상태만 N으로 바꿔줄려고 UPDATE 써서 ProductBoard테이블에서! 내가쓴리뷰니까 MemNo 가져가기!
	public int ReviewDelete(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new ReviewDao().ReviewDelete(conn,memNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	//리뷰 내용만수정하기 
	public int reviewModifyContent(ProductBoard pb) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new ReviewDao().reviewModifyContent(conn,pb);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}




	





}
