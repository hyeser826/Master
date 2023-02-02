package com.semi.manager.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.board.model.vo.Board;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;
import com.semi.manager.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.manager.orderdetail.model.vo.OrderDetail;
import com.semi.manager.review.model.vo.Review;

public class ManagerDao {
	
	private Properties prop = new Properties();
	
	public ManagerDao() {//기본 생성자
		
		try {
			prop.loadFromXML(new FileInputStream(ManagerDao.class.getResource("/db/sql/manager-mapper.xml").getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//총 회원수 조회 메소드
	public int selectMemberListCount(Connection conn) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMemberListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	//총 회원 목록 조회 메소드
	public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi) {
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMemberList");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemPhone(rset.getString("MEM_PHONE"));
				m.setGrade(rset.getString("GRADE"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				m.setEnrollflag(rset.getString("ENROLLFLAG"));
				m.setOrderDate(rset.getDate("ORDER_DATE"));
				list.add(m);
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
	//총 회원 목록 조회 메소드(정렬)
	public ArrayList<Member> selectOrderedMemberList(Connection conn, PageInfo pi, int num) {
		ArrayList<Member> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String key = "";
		switch(num) {
		case 1 : key="selectMemberList"; break;
		case 2 : key="selectMemberListDesc"; break;
		case 3 : key="selectMemberListRecent"; break;
		}
		String sql = prop.getProperty(key);
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemPhone(rset.getString("MEM_PHONE"));
				m.setGrade(rset.getString("GRADE"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				m.setEnrollflag(rset.getString("ENROLLFLAG"));
				m.setOrderDate(rset.getDate("ORDER_DATE"));
				list.add(m);
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
	//회원 조건 검색 메소드
	public Member selectOneMember(Connection conn, String memName, String memPhone) {
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectOneMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memPhone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
				m.setMemNo(rset.getInt("MEM_NO"));
				m.setMemName(rset.getString("MEM_NAME"));
				m.setMemPhone(rset.getString("MEM_PHONE"));
				m.setGrade(rset.getString("GRADE"));
				m.setEnrolldate(rset.getDate("ENROLLDATE"));
				m.setEnrollflag(rset.getString("ENROLLFLAG"));
				m.setOrderDate(rset.getDate("ORDER_DATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	//회원 정보 상세보기 메소드
	public Member selectMemberDetail(Connection conn, int mno) {
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMemberDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("MEM_NO")
							  ,rset.getString("MEM_ID")
							  ,rset.getString("MEM_PW")
							  ,rset.getString("MEM_NAME")
							  ,rset.getString("MEM_PHONE")
							  ,rset.getString("MEM_EMAIL")
							  ,rset.getString("MEM_ADDRESS")
							  ,rset.getString("GRADE")
							  ,rset.getDate("ENROLLDATE")
							  ,rset.getString("ENROLLFLAG")
							  ,rset.getDate("DELETEDATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	//장인 회원 정보 상세보기 메소드
	public Master selectMasterDetail(Connection conn, int mno) {
		Master mr = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMasterDetail");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mr = new Master(rset.getInt("MASTER_NO")
							   ,rset.getInt("MEM_NO")
							   ,rset.getString("CO_NAME")
							   ,rset.getString("CO_NUMBER")
							   ,rset.getString("CEO")
							   ,rset.getString("CO_KIND")
							   ,rset.getString("STR_NAME")
							   ,rset.getString("MASTER_INTRO")
							   ,rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mr;
	}
	//일반회원을 장인회원으로 update
	public int updateGrade(Connection conn, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateGrade");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//장인 정보 insert
	public int insertMaster(Connection conn, Master mr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMaster");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mr.getMemNo());
			pstmt.setString(2, mr.getCoName());
			pstmt.setString(3, mr.getCoNumber());
			pstmt.setString(4, mr.getCeo());
			pstmt.setString(5, mr.getCoKind());
			pstmt.setString(6, mr.getStrName());
			pstmt.setString(7, mr.getMasterIntro());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//장인회원을 일반회원으로 update
	public int downGrade(Connection conn, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("downGrade");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//장인 정보 N으로 update
	public int deleteMaster(Connection conn, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMaster");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//장인 회원 정보 리스트 조회 메소드
	public ArrayList<Master> selectMasterList(Connection conn, int memNo) {
		ArrayList<Master> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMasterList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Master(rset.getInt("MASTER_NO")
								   ,rset.getInt("MEM_NO")
								   ,rset.getString("CO_NAME")
								   ,rset.getString("CO_NUMBER")
								   ,rset.getString("CEO")
								   ,rset.getString("CO_KIND")
								   ,rset.getString("STR_NAME")
								   ,rset.getString("MASTER_INTRO")
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
	//회원 탈퇴 메소드
	public int deleteMember(Connection conn, int memNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//장인->장인 : 장인 정보 update
	public int updateMaster(Connection conn, Master mr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMaster");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mr.getCoName());
			pstmt.setString(2, mr.getCoNumber());
			pstmt.setString(3, mr.getCeo());
			pstmt.setString(4, mr.getCoKind());
			pstmt.setString(5, mr.getStrName());
			pstmt.setString(6, mr.getMasterIntro());
			pstmt.setInt(7, mr.getMasterNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//리뷰 조회 메소드(최신 3개)
	public ArrayList<Review> selectReview(Connection conn, int mno) {
		ArrayList<Review> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Review r = new Review();
				r.setProductBoardNo(rset.getInt("PRODUCT_BOARD_NO"));
				r.setProductName(rset.getString("PRODUCT_NAME"));
				r.setProductBoardContent(rset.getString("PRODUCT_BOARD_CONTENT"));
				r.setProductBoardDate(rset.getDate("PRODUCT_BOARD_DATE"));
				r.setStatus(rset.getString("STATUS"));
				
				list.add(r);
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
	//리뷰 삭제 메소드
	public int deleteReview(Connection conn, int productBoardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productBoardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//구매내역 조회 메소드(최신 1묶음)
	public ArrayList<OrderDetail> selectOrderList(Connection conn, int mno) {
		ArrayList<OrderDetail> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				OrderDetail o = new OrderDetail();
				o.setOrderNo(rset.getInt("ORDER_NO"));
				o.setProductName(rset.getString("PRODUCT_NAME"));
				o.setProductCount(rset.getInt("PRODUCT_COUNT"));
				o.setOrderStatus(rset.getString("ORDER_STATUS"));
				
				list.add(o);
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
	//1:1 문의내역 조회(최신 3개)
	public ArrayList<Board> selectThreeBoardList(Connection conn, int mno) {
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectThreeBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO")
								  ,rset.getInt("MEM_NO")
								  ,rset.getString("BOARD_TITLE")
								  ,rset.getString("BOARD_CONTENT")
								  ,rset.getString("BOARD_ANSWER")
								  ,rset.getDate("BOARD_DATE")
								  ,rset.getDate("ANSWER_DATE")
								  ,rset.getString("BOARD_CATEGORY")
								  ,rset.getString("CO_NAME")
								  ,rset.getString("CO_NUMBER")
								  ,rset.getString("CEO")
								  ,rset.getString("CO_KIND")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("MASTER_INTRO")
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
	//1:1 문의글 답변 등록 메소드
	public int insertAnswer(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardAnswer());
			pstmt.setInt(2, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//1:1 문의글 답변 등록 메소드
	public int updateAnswer(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardAnswer());
			pstmt.setInt(2, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//주문 관리 메뉴-총 주문수 조회 메소드
	public int selectOrderListCount(Connection conn) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectOrderListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	//주문 관리 메뉴-총 주문 목록 조회 메소드
	public ArrayList<OrderDetail> selectAllOrderList(Connection conn, PageInfo pi) {
		ArrayList<OrderDetail> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectAllOrderList");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				OrderDetail od = new OrderDetail();
				od.setOrderDetailNo(rset.getInt("ORDER_DETAIL_NO"));
				od.setProductName(rset.getString("PRODUCT_NAME"));
				od.setProductCount(rset.getInt("PRODUCT_COUNT"));
				od.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				od.setOrderStatus(rset.getString("ORDER_STATUS"));
				od.setMemFlag(rset.getString("MEM_FLAG"));
				od.setReciverName(rset.getString("RECIVER_NAME"));
				od.setReciverPhone(rset.getString("RECIVER_PHONE"));
				list.add(od);
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
	//특정 회원의 총 1:1 게시글 수 조회 메소드
	public int selectBoardListCount(Connection conn,int memNo) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	//특정 회원의  1:1 문의내역 전체 조회
	public ArrayList<Board> selectBoardList(Connection conn, PageInfo pi, int memNo) {
		ArrayList<Board> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoardList");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO")
								  ,rset.getInt("MEM_NO")
								  ,rset.getString("BOARD_TITLE")
								  ,rset.getString("BOARD_CONTENT")
								  ,rset.getString("BOARD_ANSWER")
								  ,rset.getDate("BOARD_DATE")
								  ,rset.getDate("ANSWER_DATE")
								  ,rset.getString("BOARD_CATEGORY")
								  ,rset.getString("CO_NAME")
								  ,rset.getString("CO_NUMBER")
								  ,rset.getString("CEO")
								  ,rset.getString("CO_KIND")
								  ,rset.getString("STR_NAME")
								  ,rset.getString("MASTER_INTRO")
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
	//특정 회원의 총 리뷰 수 조회 메소드
	public int selectReviewListCount(Connection conn, int memNo) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectReviewListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return listCount;
	}
	//특정 회원의 리뷰 전체 조회
	public ArrayList<Review> selectReviewList(Connection conn, PageInfo pi, int memNo) {
		Review r = null;
		ArrayList<Review> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectReviewList");
		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new Review();
				r.setProductBoardNo(rset.getInt("PRODUCT_BOARD_NO"));
				r.setProductName(rset.getString("PRODUCT_NAME"));
				r.setProductBoardContent(rset.getString("PRODUCT_BOARD_CONTENT"));
				r.setProductBoardDate(rset.getDate("PRODUCT_BOARD_DATE"));
				r.setStatus(rset.getString("STATUS"));
				
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
