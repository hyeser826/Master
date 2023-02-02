package com.semi.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.notice.model.dao.NoticeDao;
import com.semi.notice.model.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeList() {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<Notice> selectFaqList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectFAQList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//공지사항 등록메소드
	public int insertNotice(String memId, String title, String content, String category) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn, memId, title, content, category);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

//	조회수 증가용 메소드 + 공지글 상세조회
	public Notice increaseCount(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().increaseCount(conn,noticeNo);
		Notice n = null;
		
		if(result>0) {
			JDBCTemplate.commit(conn);
			n = new NoticeDao().selectNotice(conn,noticeNo);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return n;
	}

	public Notice selectNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Notice n = new NoticeDao().selectNotice(conn,noticeNo);
		
		JDBCTemplate.close(conn);
		
		return n;
	}

	public int updateNotice(int nno, String title, String content) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().updateNotice(conn,nno,title,content);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int deleteNotice(int nno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, nno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}



}
