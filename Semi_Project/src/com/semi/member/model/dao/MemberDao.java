package com.semi.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.member.model.vo.Member;
import com.semi.member.model.vo.MemberId;
import com.semi.member.model.vo.MemberPw;
import com.semi.member.model.vo.Order;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	
	public MemberDao() {		
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
			
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("member-mapper경로 이상!");
		}
	}
		
	//로그인 메소드
	public Member loginMember(Connection conn, String memId, String memPw) {		
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
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
							  ,rset.getDate("DELETEDATE")
							  );
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 이상!");
		}finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return m;
	}
	
	//회원가입 메소드
	public int insertMember(Connection conn,Member m) {	
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemId());
			pstmt.setString(2, m.getMemPw());
			pstmt.setString(3, m.getMemName());
			pstmt.setString(4, m.getMemPhone());
			pstmt.setString(5, m.getMemEmail());
			pstmt.setString(6, m.getMemAddress());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		}finally {
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return result;
	}
	
	//아이디 중복 체크 메소드
	public int idDuplicationCheck(Connection conn, String memId) {		
		int idDuplicationCheck = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idDuplicationCheck");
		
	    try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rset = pstmt.executeQuery();
			if(rset.next() || memId.equals("")) {
				idDuplicationCheck = 0;  // 이미 존재하는 경우, 생성 불가능
			} else {
				idDuplicationCheck = 1;  // 존재하지 않는 경우, 생성 가능
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		} finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return idDuplicationCheck;
	}
	
	//핸드폰 번호 중복 체크 메소드
	public int phoneDuplicationCheck(Connection conn, String memPhone) {
		int phoneDuplicationCheck = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("phoneDuplicationCheck");
		
	    try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memPhone);
			rset = pstmt.executeQuery();
			if(rset.next() || memPhone.equals("")) {
				phoneDuplicationCheck = 0;  // 이미 존재하는 경우, 생성 불가능
			} else {
				phoneDuplicationCheck = 1;  // 존재하지 않는 경우, 생성 가능
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		} finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return phoneDuplicationCheck;
	}
	
	//이메일 중복 체크 메소드
	public int emailDuplicationCheck(Connection conn, String memEmail) {
		int emailDuplicationCheck = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("emailDuplicationCheck");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memEmail);
			rset = pstmt.executeQuery();
			if(rset.next() || memEmail.equals("")) {
				emailDuplicationCheck = 0;  // 이미 존재하는 경우, 생성 불가능
			} else {
				emailDuplicationCheck = 1;  // 존재하지 않는 경우, 생성 가능
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류");
		} finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return emailDuplicationCheck;
	}

	//아이디 찾기 메소드
	public MemberId findId(Connection conn, String memName, String memPhone) {	
		MemberId mid = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memPhone);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				mid = new MemberId(rset.getString("MEM_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 이상!");
		} finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return mid;
	}

	//비밀번호 찾기 메소드
	public int findPw(Connection conn, String memId, String memName, String memPhone) {
		int findPwResult = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("findPw");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memPhone);
			
			rset = pstmt.executeQuery();
			if(rset.next() == true) {
				findPwResult = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 이상!");
		}finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return findPwResult;
	}

	//비회원 로그인 메소드
	public Order loginNoMember(Connection conn, String reciverName, String orderNo) {
		Order o = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("loginNoMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reciverName);
			pstmt.setString(2, orderNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				o = new Order(rset.getInt("ORDER_NO")
							 ,rset.getString("RECEIVER_NAME")
							  );
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 이상!");
		}finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return o;
	}

	//회원 정보 수정 메소드
	public int updateMember(Connection conn, Member loginMem) {
		int updateResult = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, loginMem.getMemName());
			pstmt.setString(2, loginMem.getMemPhone());
			pstmt.setString(3, loginMem.getMemEmail());
			pstmt.setString(4, loginMem.getMemAddress());
			pstmt.setString(5, loginMem.getMemId());
			updateResult=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return updateResult;
	}

	//회원 조회 메소드
	public Member selectMember(Connection conn, String memId) {
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
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
						  ,rset.getDate("DELETEDATE")
						  );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return m;
	}

	//비밀번호 수정 메소드
	public int modifyPw(Connection conn, Member memIdPw) {
		int modifyPwResult = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("modifyPw");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memIdPw.getMemPw());
			pstmt.setString(2, memIdPw.getMemId());
			modifyPwResult=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {				
				JDBCTemplate.close(pstmt);
			}
		}
		return modifyPwResult;
	}

	public int deleteMember(Connection conn, String memId, String memPw) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}




















