package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberModifyPwController
 */
@WebServlet("/findPwForm.me")
public class MemberFindPwFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindPwFormController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memId = request.getParameter("memId");
		String memName = request.getParameter("memName");
		String memPhone = request.getParameter("memPhone");
		int findPwResult = new MemberService().findPw(memId, memName, memPhone);
		
		if(findPwResult == 1) {
			request.setAttribute("memId", memId);
			request.getRequestDispatcher("views/member/memberFindPwForm2.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "없는 회원입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}


















