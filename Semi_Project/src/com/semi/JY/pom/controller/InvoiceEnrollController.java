package com.semi.JY.pom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.JY.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.JY.orderdetail.model.vo.OrderDetail;
import com.semi.JY.pom.model.service.DeliveryService;
import com.semi.JY.pom.model.vo.Delivery;
import com.semi.JY.product.model.vo.Product;

/**
 * Servlet implementation class InvoiceEnrollController
 */
@WebServlet("/InvoiceEnroll.en")
public class InvoiceEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		//아래 회원번호 가져오기 . 
		Member loginMem = (Member)session.getAttribute("loginMem");	
		int memNo = loginMem.getMemNo();
		System.out.println("인보이스 엔롤컨트롤러 memNo : "+memNo);
		//주문번호,송장번호,택배사 직접 입력해서 넣을꺼임 
		Delivery dv = new Delivery();

		dv.setOrderDetailNo(Integer.parseInt(request.getParameter("OrderDetailNo")));//주문번호
		dv.setDeliveryInvoice(request.getParameter("deliveryInvoiceNo"));//송장번호
		dv.setDeliveryParcel(request.getParameter("DeliveryParcel")); //택배사
	
		System.out.println("인보이스엔롤 컨트롤러 에서 dv :"+dv);
		
		//오더디테일
		OrderDetail od =new DeliveryService().selectOrderDetailNo(memNo);
		od.setOrderDetailNo(Integer.parseInt(request.getParameter("OrderDetailNo")));
		System.out.println("InvoiceEnrollController  od :"+od);
		// 마스터 테이블에  장인번호 찾으러간다!
		Master m = new DeliveryService().selectMasterNo(memNo);
		
		System.out.println("InvoiceEnrollController m : "+m);	
		
		//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
		Product p=new DeliveryService().selectProductMasterNo(memNo);
		
				
		
		
		//조회 해서 list로 담아오기 (Delivery)  송장등록 ! 그리고 배송완료상태로 변경
		ArrayList<Delivery> list5=new DeliveryService().invoiceEnroll(memNo,dv,od,m,p);
		
		request.setAttribute("list5",list5);
		System.out.println("인보이스 엔롤 컨트롤러  list5조회 :"+list5);
			
		if(!list5.isEmpty()) {
			response.sendRedirect(request.getContextPath()+"/list.pom");
		}else {
		
		}
	}
}