package com.semi.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.manager.model.service.ManagerService;
import com.semi.manager.order.model.vo.Order;

/**
 * Servlet implementation class OrderDetailViewController
 */
@WebServlet("/orderDetail.ma")
public class OrderDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ono = request.getParameter("ono");
		//'주문 번호'에 대한 주문 정보 상세 조회
		//주문번호(주문일), 회원 이름, 수령인 이름, 수령인 전화번호, 배송지, 결제수단, (회원여부)
		//주문상세번호, 상품명, 수량, 가격, 처리상태, 포장서비스, 체험일
		//Order o = new ManagerService().selectOrder(ono);
		
		HttpSession session = request.getSession();
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
			request.getRequestDispatcher("/views/refund/orderDetailView.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
