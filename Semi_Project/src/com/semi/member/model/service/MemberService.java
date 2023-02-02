package com.semi.member.model.service;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import com.semi.common.JDBCTemplate;
import com.semi.member.model.dao.MemberDao;
import com.semi.member.model.vo.Member;
import com.semi.member.model.vo.MemberId;
import com.semi.member.model.vo.MemberPw;
import com.semi.member.model.vo.Order;

public class MemberService {
	
	//멤버 로그인 메소드
	public Member loginMember(String memId, String memPw) {
		Connection conn = JDBCTemplate.getConnection();
		Member m = new MemberDao().loginMember(conn,memId,memPw);
		
		JDBCTemplate.close(conn);
		return m;
	}

	//멤버 추가 메소드
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn,m);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//아이디 중복 체크 메소드
	public int idDuplicationCheck(String memId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().idDuplicationCheck(conn,memId);
		
		JDBCTemplate.close(conn);
		return result;
	}
	
	//핸드폰 번호 중복 체크 메소드
	public int phoneDuplicationCheck(String memPhone) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().phoneDuplicationCheck(conn,memPhone);
		
		JDBCTemplate.close(conn);
		return result;
	}
	
	//이메일 중복 체크 메소드
	public int emailDuplicationCheck(String memEmail) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().emailDuplicationCheck(conn,memEmail);
		
		JDBCTemplate.close(conn);
		return result;
	}

	//아이디 찾기 메소드
	public MemberId findId(String memName, String memPhone) {
		Connection conn = JDBCTemplate.getConnection();
		MemberId m = new MemberDao().findId(conn,memName,memPhone);
		
		JDBCTemplate.close(conn);
		return m;
	}

	//비번 찾기 메소드
	public int findPw(String memId, String memName, String memPhone) {
		Connection conn = JDBCTemplate.getConnection();
		int findPwResult = new MemberDao().findPw(conn,memId,memName,memPhone);
		
		if(findPwResult == 1) {				
			System.out.println("비밀번호 조회 성공");
			JDBCTemplate.commit(conn);
		}else {
			System.out.println("비번 조회 실패");
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return findPwResult;
	}

	//비회원 로그인 메소드
	public Order loginNoMember(String reciverName, String orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		Order o = new MemberDao().loginNoMember(conn,reciverName,orderNo);
		
		JDBCTemplate.close(conn);
		return o;
	}

	//회원 정보 수정 메소드
	public Member updateMember(Member loginMem) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,loginMem);
		Member updateMem = null;
		
		if(result>0) {
			JDBCTemplate.commit(conn);
			//update는 성공했으니 다시 dao로 가서 조회
			updateMem = new MemberDao().selectMember(conn,loginMem.getMemId());
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return updateMem;
	}

	//비밀번호 수정 메소드
	public int modifyPw(Member memIdPw) {
		Connection conn = JDBCTemplate.getConnection();
		int modifyPwResult = new MemberDao().modifyPw(conn,memIdPw);
		
		if(modifyPwResult>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return modifyPwResult;
	}

	public int deleteMember(String memId, String memPw) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().deleteMember(conn,memId,memPw);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
}






















