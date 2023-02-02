package com.semi.order.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.JDBCTemplate;
import com.semi.order.model.service.OrderDetailService;
import com.semi.order.model.service.OrderService;
import com.semi.order.model.vo.Order;
import com.semi.order.model.vo.OrderDetail;

/**
 * Servlet implementation class OrderStatusCancelController
 */
@WebServlet("/chstatus.or")
public class OrderStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderStatusController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int detailOno = Integer.parseInt(request.getParameter("detailOno"));
		int ono = Integer.parseInt(request.getParameter("ono"));
		String detailOnoText = request.getParameter("detailOnoText");

		System.out.println(detailOno);		//주문상세번호
		System.out.println(ono);			//주문번호
		System.out.println(detailOnoText);	//주문상세번호텍스트

		int result = new OrderDetailService().chStatus(detailOno, detailOnoText);

		System.out.println("result" + result);
		System.out.println("ono " + ono);
		if (result > 0) {
			// ono가지고 다시 컨트롤러..??
			response.sendRedirect(request.getContextPath()+"/detail.or?ono="+ono);
		} else {
			request.setAttribute("errorMsg", "상품등록 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
