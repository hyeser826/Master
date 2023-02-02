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
 * Servlet implementation class AjaxUpdateAmountCart
 */
@WebServlet("/updateAmountCart.ca")
public class AjaxUpdateAmountCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxUpdateAmountCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");       
		String memId = loginMem.getMemId();
	    int acProNo = Integer.parseInt(request.getParameter("acmdProNo"));
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		int amount = Integer.parseInt(request.getParameter("acmdAmount"));
		
		int result = 0;
		
		System.out.println(cartNo);
		System.out.println(amount);
		result = new CartService().updateAmountCart(memId,cartNo,amount,acProNo);
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
