package com.semi.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.notice.model.vo.Notice;


public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String filePath = NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	//공지사항 조회용
	public ArrayList<Notice> selectNoticeList(Connection conn) {
		ArrayList<Notice> list = new ArrayList<>(); 
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNoticeList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO")
								  , rset.getString("NOTICE_TITLE")
								  , rset.getString("NOTICE_CONTENT")
								  , rset.getInt("MEM_NO")
								  , rset.getInt("NOTICE_HITS")
								  , rset.getDate("NOTICE_DATE"))
								 );
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		
		return list;
		
	}
	
	//FAQ 리스트 조회용
	public ArrayList<Notice> selectFAQList(Connection conn) {
		ArrayList<Notice> list = new ArrayList<>(); 
		Statement stmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFAQList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO")
								  , rset.getString("NOTICE_TITLE")
								  , rset.getString("NOTICE_CONTENT")
								  , rset.getInt("MEM_NO")
								  , rset.getInt("NOTICE_HITS")
								  , rset.getDate("NOTICE_DATE"))
								 );
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		
		return list;
		
	}

	//공지사항 게시용
	public int insertNotice(Connection conn, String memId, String title, String content, String category) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, memId);
			pstmt.setString(4, category);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	

	//공지사항 조회수 상승
	public int increaseCount(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
	
		return result;
	}

	//게시글 선택
	public Notice selectNotice(Connection conn, int noticeNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectNotice");
		Notice n = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("NOTICE_NO")
							 , rset.getString("NOTICE_TITLE")
							 , rset.getString("NOTICE_CONTENT")
							 , rset.getInt("NOTICE_HITS")
							 , rset.getDate("NOTICE_DATE")
							 , rset.getString("STATUS"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return n;
		
	}

	//게시글 수정
	public int updateNotice(Connection conn, int nno, String title, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	
	//게시글 삭제
	public int deleteNotice(Connection conn, int nno) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	
	

}
