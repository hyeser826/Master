package com.semi.refund.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.order.model.vo.OrderDetail;
import com.semi.refund.model.service.RefundService;

/**
 * Servlet implementation class RefundFormController
 */
@WebServlet("/refund.or")
public class RefundFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefundFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int detailOno = Integer.parseInt(request.getParameter("detailOno"));
		
		System.out.println("refund's detailOno ; "+detailOno);
		
		OrderDetail od = new RefundService().selectRefundProduct(detailOno);
		System.out.println("od : "+od);
		if(od!=null) {
			request.setAttribute("od", od);
			request.getRequestDispatcher("/views/refund/refundForm.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("alertMsg", "환불 상품 조회에 실패했습니다.");
			response.sendRedirect(request.getHeader("referer"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
