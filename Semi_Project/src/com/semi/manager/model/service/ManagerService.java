package com.semi.manager.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.board.model.vo.Board;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;
import com.semi.manager.model.dao.ManagerDao;
import com.semi.manager.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.manager.orderdetail.model.vo.OrderDetail;
import com.semi.manager.review.model.vo.Review;

public class ManagerService {
	//총 회원수 조회 메소드
	public int selectMemberListCount() {
		Connection conn = JDBCTemplate.getConnection();
		int count = new ManagerDao().selectMemberListCount(conn);
		JDBCTemplate.close(conn);
		return count;
	}
	//총 회원 목록 조회 메소드
	public ArrayList<Member> selectMemberList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new ManagerDao().selectMemberList(conn,pi);
		JDBCTemplate.close(conn);
		return list;
	}
	//총 회원 목록 조회 메소드(정렬)
	public ArrayList<Member> selectOrderedMemberList(PageInfo pi, int num) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new ManagerDao().selectOrderedMemberList(conn,pi,num);
		JDBCTemplate.close(conn);
		return list;
	}
	//회원 조건 검색 메소드
	public Member selectOneMember(String memName, String memPhone) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = new ManagerDao().selectOneMember(conn,memName,memPhone);
		JDBCTemplate.close(conn);
		return m;
	}
	//회원 정보 상세보기 메소드
	public Member selectMemberDetail(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = new ManagerDao().selectMemberDetail(conn,mno);
		JDBCTemplate.close(conn);
		return m;
	}
	//장인 회원 정보 상세보기 메소드
	public Master selectMasterDetail(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		Master mr = new ManagerDao().selectMasterDetail(conn,mno);
		JDBCTemplate.close(conn);
		return mr;
	}
	//일반회원을 장인회원으로 update
	public int updateGrade(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().updateGrade(conn,memNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//장인 정보 insert
	public int insertMaster(Master mr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().insertMaster(conn,mr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//장인회원을 일반회원으로 update
	public int downGrade(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().downGrade(conn,memNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//장인 정보 N으로 update
	public int deleteMaster(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().deleteMaster(conn,memNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//장인 회원 정보 리스트 조회 메소드
	public ArrayList<Master> selectMasterList(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Master> list = new ManagerDao().selectMasterList(conn,memNo);
		JDBCTemplate.close(conn);
		return list;
	}
	//회원 탈퇴 메소드
	public int deleteMember(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().deleteMember(conn,memNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//장인->장인 : 장인 정보 update
	public int updateMaster(Master mr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().updateMaster(conn,mr);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//리뷰 조회 메소드(최신 3개)
	public ArrayList<Review> selectReview(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Review> list = new ManagerDao().selectReview(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	//리뷰 삭제 메소드
	public int deleteReview(int pqNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().deleteReview(conn,pqNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//구매내역 조회 메소드(최신 1묶음)
	public ArrayList<OrderDetail> selectOrderList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderDetail> list = new ManagerDao().selectOrderList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}
	//1:1 문의내역 조회(최신 3개)
	public ArrayList<Board> selectThreeBoardList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = new ManagerDao().selectThreeBoardList(conn,mno);
		JDBCTemplate.close(conn);
		return list;
	}
	//1:1 문의글 답변 등록 메소드
	public int insertAnswer(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().insertAnswer(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//1:1 문의글 답변 등록 메소드
	public int updateAnswer(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ManagerDao().updateAnswer(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	//주문 관리 메뉴-총 주문수 조회 메소드
	public int selectOrderListCount() {
		Connection conn = JDBCTemplate.getConnection();
		int count = new ManagerDao().selectOrderListCount(conn);
		JDBCTemplate.close(conn);
		return count;
	}
	//주문 관리 메뉴-총 주문 목록 조회 메소드
	public ArrayList<OrderDetail> selectAllOrderList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrderDetail> list = new ManagerDao().selectAllOrderList(conn,pi);
		JDBCTemplate.close(conn);
		return list;
	}
	//특정 회원의  총 1:1 게시글 수 조회 메소드
	public int selectBoardListCount(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int count = new ManagerDao().selectBoardListCount(conn,memNo);
		JDBCTemplate.close(conn);
		return count;
	}
	//특정 회원의  1:1 문의내역 전체 조회
	public ArrayList<Board> selectBoardList(PageInfo pi,int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = new ManagerDao().selectBoardList(conn,pi,memNo);
		JDBCTemplate.close(conn);
		return list;
	}
	//특정 회원의 총 리뷰 수 조회 메소드
	public int selectReviewListCount(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int count = new ManagerDao().selectReviewListCount(conn,memNo);
		JDBCTemplate.close(conn);
		return count;
	}
	//특정 회원의 리뷰 전체 조회
	public ArrayList<Review> selectReviewList(PageInfo pi, int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Review> list = new ManagerDao().selectReviewList(conn,pi,memNo);
		JDBCTemplate.close(conn);
		return list;
	}
	
}
