package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberFindIdController
 */
@WebServlet("/findId.me")
public class MemberFindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindIdController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("views/member/memberFindIdForm.jsp").forward(request, response);
		
	}

}
