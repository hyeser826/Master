package com.semi.cart.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.cart.model.service.CartService;
import com.semi.cart.model.vo.Cart;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class ProductInsertCart
 */
@WebServlet("/insertCart.ca")
public class ProductInsertCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductInsertCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 상품 상세페이지에서 받은 정보로 장바구니 디비에 넣어주는 작업 하기
		// 같은 상품번호가 리스트에 또 들어오면 제품 수량에서 count + 되게 해주는 작업해야함
		request.setCharacterEncoding("UTF-8");
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");       
		String categoryName = request.getParameter("categoryName");
		//String memId = loginMem.getMemId();
		int pno = Integer.parseInt(request.getParameter("pno"));
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		

		
		Cart c = new Cart();
		int result = 0;
		

		  System.out.println("상품번호"+pno);
		  System.out.println(categoryName);

		  
		//로그인 확인 후 로그인 되어있지 않으면 로그인 하라는 alert창 띄우는 구문	 
			if(!(loginMem==null)) {
			  String memId = loginMem.getMemId();
			  
				if (categoryName.equals("0.체험")) {
					Date eDate = Date.valueOf(request.getParameter("eDate"));

					c.setMemId(memId);
					c.setCategoryNm(categoryName);
					c.setProductNo(pno);
					c.setProductPrice(productPrice);
					c.setAmount(quantity);
					c.setTotalPrice(totalPrice);
					c.setMstNo(mno);
					c.setEventDate(eDate);
					result = new CartService().selectCart(c);
					
					
					
				} else {

					c.setMemId(memId);
					c.setCategoryNm(categoryName);
					c.setProductNo(pno);
					c.setProductPrice(productPrice);
					c.setAmount(quantity);
					c.setTotalPrice(totalPrice);
					c.setMstNo(mno);
					result = new CartService().selectCart(c);
					
					
					
				}
				
			 }else {//비회원일 경우
				 
				 result = 5;
				 
			 }
	
		response.getWriter().print(result);
		System.out.println(result);

		System.out.println(c);

	}
	

}
