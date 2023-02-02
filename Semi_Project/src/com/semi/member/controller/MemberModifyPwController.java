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

/**
 * Servlet implementation class MemberModifyPwController
 */
@WebServlet("/modifyPw.me")
public class MemberModifyPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberModifyPwController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Member loginMem = (Member) session.getAttribute("loginMem");
		String memId = loginMem.getMemId();
		String memPw = request.getParameter("memPw");
		Member memIdPw = new Member(memId,memPw);
		
		int modifyPwResult = new MemberService().modifyPw(memIdPw);

		if(modifyPwResult > 0) {
			
			try {
				if(!session.getAttribute("loginMem").equals(null)) {
					request.getRequestDispatcher("views/member/memberFindPwEnd.jsp").forward(request, response);
				}
			} catch(NullPointerException e){
				e.printStackTrace();
				session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
				response.sendRedirect(request.getContextPath());
			}
		}else {
			session.setAttribute("alertMsg", "수정에 실패했습니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}


















