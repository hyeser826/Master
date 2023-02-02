package com.semi.cart.controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BoardDeleteCtlr
 */
@WebServlet("/odpmt.one")
public class OrderPaymentCtlr2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderPaymentCtlr2() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //인코딩 먼저 해주고	
		String pno = request.getParameter("pno");
		String price = request.getParameter("productPrice");
		String quantity = request.getParameter("quantity");
		String strName = request.getParameter("strName");
		String productName = request.getParameter("productName");

		request.setAttribute("pno", pno);
		request.setAttribute("price",price);
		request.setAttribute("quantity",quantity);
		request.setAttribute("strName",strName);
		request.setAttribute("productName",productName);
	
		request.getRequestDispatcher("/views/cart/beMemOrder.jsp").forward(request, response); //주문결제 페이지로 결과 보내기
			
			/*
			 * }else { request.setAttribute("errorMsg", "상품 구매에 실패하였습니다.");
			 * request.getRequestDispatcher("/views/commom/errorPage.jsp").forward(request,
			 * response);
			 * 
			 * }
			 */
		

	}

}
