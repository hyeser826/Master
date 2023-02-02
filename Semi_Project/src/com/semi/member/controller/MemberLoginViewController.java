package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginViewController
 */
@WebServlet("/loginview.me")
public class MemberLoginViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLoginViewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member referer = null;
		if(request.getHeader("referer") == null) {
			referer = new Member((String)request.getContextPath());
		} else {
			referer = new Member((String)request.getHeader("referer"));
		}
		request.setAttribute("referer", referer);
		
		HttpSession session = request.getSession();
		

		request.getRequestDispatcher("views/member/memberLoginView.jsp").forward(request, response);

	}
	
}















