package com.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.service.MemberService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MemberCheckMyPwAjaxForm
 */
@WebServlet("/checkMyPw.me")
public class MemberCheckMyPwAjaxForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MemberCheckMyPwAjaxForm() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMem = (Member) session.getAttribute("loginMem");
		String myPw = request.getParameter("myPw");
		PrintWriter out = response.getWriter();
		int checkMyPw = 0;
		
		if(loginMem.getMemPw().equals(myPw)){
			checkMyPw = 1;
		}
		out.write(checkMyPw + ""); // --> ajax 결과값인 result가 됨
				// --> String으로 값을 내보낼 수 있도록 + "" 를 해준다
	}

}

















