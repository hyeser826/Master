package com.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.MemberId;

/**
 * Servlet implementation class MemberFindIdController
 */
@WebServlet("/findIdEnd.me")
public class MemberFindIdEndController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindIdEndController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String memName = request.getParameter("memName");
		String memPhone = request.getParameter("memPhone");
		MemberId mid = new MemberService().findId(memName, memPhone);
		
		try {
			if(!mid.equals(null)) {				
				System.out.println("아이디 조회 성공");
				request.setAttribute("mid", mid);
				request.getRequestDispatcher("views/member/memberFindIdEnd.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg", "없는 회원입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
