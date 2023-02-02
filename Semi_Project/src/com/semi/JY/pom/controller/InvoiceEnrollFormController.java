package com.semi.JY.pom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.JY.orderdetail.model.vo.OrderDetail;
import com.semi.JY.pom.model.service.DeliveryService;
import com.semi.JY.pom.model.vo.Delivery;

/**
 * Servlet implementation class InvoiceEnrollFormController
 */
@WebServlet("/invoiceEnrollForm.en")
public class InvoiceEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMem = (Member)session.getAttribute("loginMem");	
		//회원번호 담아오기.
		int memNo = loginMem.getMemNo();
	
		OrderDetail od =new DeliveryService().selectOrderDetailNo(memNo);
			
		od.setOrderDetailNo(Integer.parseInt(request.getParameter("productReady")));
		System.out.println("여기 인보이스엔롤폼컨트롤러 오디 왜 안나와?"+od);
		
	
		//딜리버리에서 송장 벨류값줄려고 ! 
		Delivery dv=new DeliveryService().selectinvoiceEnroll(memNo,od);
		request.setAttribute("dv",dv); 
		
		
		ArrayList<OrderDetail>list6=new DeliveryService().selectProductReady(od);
		
		request.setAttribute("list6",list6);
		
		if(session.getAttribute("loginMem")==null) {
			session.setAttribute("alert","로그인후 가능한 서비스입니다.");
			 
			response.sendRedirect(request.getContextPath());
		}else {
			request.getRequestDispatcher("views/jy/pom/invoiceEnrollForm.jsp").forward(request, response);
			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
	}
}
