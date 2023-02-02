package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberDeleteEnd
 */
@WebServlet("/deleteEnd.me")
public class MemberDeleteEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDeleteEnd() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("views/member/memberDeleteEnd.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}























