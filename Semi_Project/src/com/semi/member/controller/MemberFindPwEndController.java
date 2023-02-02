package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;
import com.semi.member.model.vo.MemberId;
import com.semi.member.model.vo.MemberPw;

/**
 * Servlet implementation class MemberFindPwEndController
 */
@WebServlet("/findPwEnd.me")
public class MemberFindPwEndController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindPwEndController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		Member memIdPw = new Member(memId,memPw);
		
		int modifyPwResult = new MemberService().modifyPw(memIdPw);
		
		if(modifyPwResult > 0) {
			request.getRequestDispatcher("views/member/memberFindPwEnd.jsp").forward(request, response);
		}else {
			session.setAttribute("alertMsg", "수정에 실패했습니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}


















