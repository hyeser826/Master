package com.semi.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.vo.Board;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;

public class BoardService {

	//1:1문의글 총 갯수 조회해오기
	public int selectListCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new BoardDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return count;
	}
	
	//QNA 총 갯수 조회해오기
		public int selectQnaCount(String memId) {
			Connection conn = JDBCTemplate.getConnection();
			
			int count = new BoardDao().selectQnaCount(conn,memId);
			
			JDBCTemplate.close(conn);
			
			return count;
		}

	//페이징바
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn,pi);
		
		JDBCTemplate.close(conn);
		
		return list;
				
	}
	
	//페이징바
		public ArrayList<Board> selectQnaList(PageInfo pi, String memId) {
			Connection conn = JDBCTemplate.getConnection();
			
			ArrayList<Board> list = new BoardDao().selectQnaList(conn,pi,memId);
			
			JDBCTemplate.close(conn);
			
			return list;
					
		}


	//입점문의 추가
	public int insertLvBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 먼저 board 테이블에 insert 하기 
		int result = new BoardDao().insertLvBoard(conn,b);
	
		if(result>0) {

			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result; 
	}
	
	//일반문의 추가
	public int insertPsnBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 먼저 board 테이블에 insert 하기 
		int result = new BoardDao().insertPsnBoard(conn,b);
		
		
		if(result>0) {

			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result; 
	}

	//QNA문의 추가
	public int insertQnaBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 먼저 board 테이블에 insert 하기 
		int result = new BoardDao().insertQnaBoard(conn,b);
		
		
		if(result>0) {

			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result; 
	}
	
	//1:1 & QNA 선택 조회 메소드
	public Board selectBoard(int bno) {
		Connection conn = JDBCTemplate.getConnection();
		
		Board b = new BoardDao().selectBoard(conn,bno);
		
		JDBCTemplate.close(conn);
		
		
		return b;
	}

	
	//1:1문의 QNA 수정 메소드
	public int updateBoard(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 수정 
		int result = new BoardDao().updateBoard(conn,b);

		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//관리자 답변 및 답변 수정
	public int updateAnswer(int bno, String dearPsn) {
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 정보 수정 
		int result = new BoardDao().updateAnswer(conn, bno, dearPsn);

		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	//게시글 삭제 메소드
	public int deleteBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result =new BoardDao().deleteBoard(conn,boardNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Integer> checkMaster(String chkMaster) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Integer> pnoList = new BoardDao().checkMaster(conn, chkMaster);
		
		JDBCTemplate.close(conn);
		
		return pnoList;
	}
	
	//에이젝스 상품문의글 리스트 조회 메소드
	public ArrayList<Board> selectProductQnAList(int pno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = new BoardDao().selectProductQnAList(conn,pno);
		
		JDBCTemplate.close(conn);
		return list;
	}

	
	
	
}
