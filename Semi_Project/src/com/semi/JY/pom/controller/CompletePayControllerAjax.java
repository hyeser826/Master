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
 * Servlet implementation class CompletePayControllerAjax
 */
@WebServlet("/completePay.me")
public class CompletePayControllerAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletePayControllerAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    ////아래는결제완료에서 상품준비중//
    ////아래는결제완료에서 상품준비중//
    ////아래는결제완료에서 상품준비중//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMem = (Member)session.getAttribute("loginMem");	
		//회원번호 담아오기.
		int memNo = loginMem.getMemNo();
	
		//아래는 오더디테일(회원포함)
		OrderDetail od =new DeliveryService().selectOrderDetailNo(memNo);
		od.setOrderDetailNo(Integer.parseInt(request.getParameter("completePay")));
		System.out.println("CompletePayControllerAjax  od :"+od);
	
		// 마스터 테이블에  장인번호 찾으러간다!
		Master m = new DeliveryService().selectMasterNo(memNo);
		System.out.println("CompletePayControllerAjax m : "+m);	
	
		//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
		Product p=new DeliveryService().selectProductMasterNo(memNo);
				
		
		
		
		//결제완료에서 상품준비중으로
		//결제완료에서 상품준비중으로
		//결제완료에서 상품준비중으로
		int result=new DeliveryService().changeOrderStatus2(memNo,m,od,p);
		System.out.println("//결제완료에서 상품준비중으로 컨트롤러 0보다커?"+result);
		if(result==1) {
			response.sendRedirect(request.getContextPath()+"/list.pom");
		}else {
			
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
