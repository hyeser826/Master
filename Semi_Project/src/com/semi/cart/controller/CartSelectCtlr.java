package com.semi.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.cart.model.service.CartService;
import com.semi.cart.model.vo.Cart;
//선택한 상품만 구매하기 창


/**
 * Servlet implementation class BoardDeleteCtlr
 */
@WebServlet("/select.buylist")
public class CartSelectCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartSelectCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] cartNos = request.getParameterValues("cartNo");
		String cartNo = "";
		ArrayList<Cart> buyList = new ArrayList<>();
		
		for(int i=0; i<cartNos.length; i++) {
			cartNo = cartNos[i];
			buyList.addAll(new CartService().selectbuyList(cartNo));
		}

		
		if(buyList!=null) {
			response.sendRedirect(request.getContextPath()+"/list.ct");
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
