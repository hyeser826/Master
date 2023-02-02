package com.semi.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.cart.model.service.CartService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class AjaxDeleteOneCart
 */
@WebServlet("/deleteOneCart.ca")
public class AjaxDeleteOneCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxDeleteOneCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");       
		String memId = loginMem.getMemId();
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		
		int result = 0;
		
		System.out.println(cartNo);
		result = new CartService().deleteOneCart(memId,cartNo);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
