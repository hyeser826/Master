package com.semi.refund.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.refund.model.service.RefundService;
import com.semi.refund.model.vo.Refund;

/**
 * Servlet implementation class AskRefundController
 */
@WebServlet("/insertRefund.or")
public class InsertRefundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRefundController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int orderDetailNo = Integer.parseInt(request.getParameter("orderDetailNo"));
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		String refundReason = request.getParameter("refundReason");
		
		Refund r = new Refund();
		r.setOrderDetailNo(orderDetailNo);
		r.setRefundReason(refundReason);
		
		int result = new RefundService().insertRefund(r);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "환불 요청이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.or?ono="+orderNo);
			
		}else {
			request.getSession().setAttribute("alertMsg", "환불 요청에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.or?ono="+orderNo);
		}
	}

}
