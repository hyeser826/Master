package com.semi.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.cart.model.service.CartService;
import com.semi.cart.model.vo.Cart;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class BoardListCtlr
 */
@WebServlet("/list.ct")
public class CartPageCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartPageCtlr() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		장바구니에 담긴 내 상품 리스트 띄워보기
		
		request.setCharacterEncoding("UTF-8");
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");
		String memId = loginMem.getMemId(); 
		ArrayList<Cart> mdList = new CartService().cartMdList(memId);
		ArrayList<Cart> acList = new CartService().cartAcList(memId);
		
		request.setAttribute("mdList", mdList);
		request.setAttribute("acList", acList);
		request.getRequestDispatcher("/views/cart/cartView.jsp").forward(request, response);
		
	}

}
