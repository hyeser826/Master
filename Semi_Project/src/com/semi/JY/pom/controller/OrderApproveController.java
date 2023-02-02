package com.semi.JY.pom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.semi.JY.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.JY.orderdetail.model.vo.OrderDetail;
import com.semi.JY.pom.model.service.DeliveryService;
import com.semi.JY.product.model.vo.Product;

/**
 * Servlet implementation class OrderApprove
 */
@WebServlet("/orderApprove.me")
public class OrderApproveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderApproveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginMem = (Member)session.getAttribute("loginMem");	
		//회원번호 담아오기. 이거 보류 
		int memNo = loginMem.getMemNo();
			
		//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
		Product p=new DeliveryService().selectProductMasterNo(memNo);
		
		
		
		//오더디테일 테이블에 주문상세번호 찾으러 가자 !(회원과 조회)
		OrderDetail od =new DeliveryService().selectOrderDetailNo(memNo);
		
		od.setOrderDetailNo(Integer.parseInt(request.getParameter("orderApprove")));
		System.out.println("orderApprove컨트롤러  od :"+od);

		// 마스터 테이블에  장인번호 찾으러간다!
		Master m = new DeliveryService().selectMasterNo(memNo);
		System.out.println("orderApprove컨트롤러 m : "+m);	
		//입금대기에서 결제완료로
		//ArrayList<OrderDetail> list2
		
		ArrayList<OrderDetail> list2=new DeliveryService().changeOrderStatus(memNo,m,od,p);
		
		if(!list2.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/list.pom");
		}else {
		
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
	}
}
