package com.semi.cart.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderPaymentCtlr3
 */
@WebServlet("/odpmt2.one")
public class OrderPaymentCtlr3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderPaymentCtlr3() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //인코딩 먼저 해주고	
		String pno = request.getParameter("pno");
		String price = request.getParameter("productPrice");
		String quantity = request.getParameter("quantity");
		String strName = request.getParameter("strName");
		String productName = request.getParameter("productName");
		String eDate = request.getParameter("eDate");
		
		request.setAttribute("pno", pno);
		request.setAttribute("quantity", quantity);
		request.setAttribute("productName", productName);
		request.setAttribute("strName", strName);
		request.setAttribute("price", price);
		request.setAttribute("eDate", eDate);
			
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
