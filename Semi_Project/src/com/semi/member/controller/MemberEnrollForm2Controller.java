package com.semi.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberEnrollForm2
 */
@WebServlet("/enrollForm2.me")
public class MemberEnrollForm2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberEnrollForm2Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("views/member/memberEnrollForm2.jsp").forward(request, response);
		
	}

}















