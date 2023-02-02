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
 * Servlet implementation class MemberDeleteFormController
 */
@WebServlet("/delete.me")
public class MemberDeleteFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteFormController() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMem = (Member) session.getAttribute("loginMem");
		String memPw = request.getParameter("memPw");
		
		if(loginMem.getMemPw().equals(memPw)){
			int deleteUser = new MemberService().deleteMember(loginMem.getMemId(),memPw);
			if(deleteUser > 0) {				
				session.removeAttribute("loginMem");
				response.sendRedirect(request.getContextPath()+"/deleteEnd.me");
			}
			if(deleteUser <= 0) {
				session.setAttribute("alertMsg", "수정에 실패했습니다.");
				response.sendRedirect(request.getContextPath());				
			}
		}
		if(!loginMem.getMemPw().equals(memPw)){
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");	
			response.sendRedirect(request.getContextPath());
		}
	}

}


























