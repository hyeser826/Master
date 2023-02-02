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
 * Servlet implementation class MemberModifyController
 */
@WebServlet("/modify.me")
public class MemberModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberModifyController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Member loginMem = (Member) session.getAttribute("loginMem");
		String memName = request.getParameter("memName");
		String memPhone = request.getParameter("memPhone");
		String memEmail = request.getParameter("memEmail");
		String memAddress = request.getParameter("memAddress")+","+request.getParameter("memAddressDetail");
		
		loginMem.setMemName(memName);
		loginMem.setMemPhone(memPhone);
		loginMem.setMemEmail(memEmail);
		loginMem.setMemAddress(memAddress);
		Member updateMem = new MemberService().updateMember(loginMem);
		
		try {
			if(!updateMem.equals(null)) {
				session.setAttribute("loginMem", updateMem);
				response.sendRedirect(request.getHeader("referer"));			
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg", "정보수정에 실패했습니다.");			
			response.sendRedirect(request.getContextPath());
		}
	}
	
}




















