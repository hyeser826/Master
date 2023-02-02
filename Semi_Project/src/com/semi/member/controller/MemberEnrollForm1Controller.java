package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberEnrollForm
 */
@WebServlet("/enrollForm1.me")
public class MemberEnrollForm1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberEnrollForm1Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("views/member/memberEnrollForm1.jsp").forward(request, response);
	}

}

















