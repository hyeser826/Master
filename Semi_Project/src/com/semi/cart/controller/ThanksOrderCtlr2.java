package com.semi.cart.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.cart.model.service.CartService;
import com.semi.cart.model.vo.Cart;
import com.semi.member.model.vo.Member;
import com.semi.order.model.service.OrderDetailService;
import com.semi.order.model.service.OrderService;

/**
 * Servlet implementation class ThanksOrderCtlr
 */
@WebServlet("/insertnew.strg")
public class ThanksOrderCtlr2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanksOrderCtlr2() {
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
		request.setCharacterEncoding("UTF-8"); //인코딩 먼저 해주고	
		
//		MEMORDER에 들어갈 정보
		Date eventDate = Date.valueOf(request.getParameter("eventDate")); //예약일
		int pno = Integer.getInteger(request.getParameter("pno"));		  //상품번호
		int price = Integer.getInteger(request.getParameter("price"));	  //가격
		int amount = Integer.getInteger(request.getParameter("amount"));  //수량
		String strName = request.getParameter("strName");				  //장인홈이름
		
		String reciverName = request.getParameter("reciverName");         //수령자
		String reciverPhone = request.getParameter("reciverPhone");		  //수령자번호
		String orderAddress = request.getParameter("orderAddress");		  //수령자주소
		
		int delFee = Integer.getInteger(request.getParameter("delFee"));  //최종 배송비

		String memId = request.getParameter("memId");					  //회원이면 ID
		
		
		int bmemNo = 0;
		
		//주문서 만들고 주문번호 받아오기
		int orderNo = 0;
		if(memId==null) {
			//비회원 주문시 주문번호 받기
			orderNo = new OrderService().insertNewBOrder(bmemNo,reciverName,reciverPhone,orderAddress,delFee);	
			System.out.println(orderNo);
		}else if(memId!=null){
			//회원 주문시 주문번호 받기
			orderNo = new OrderService().insertNewOrder(memId,reciverName,reciverPhone,orderAddress,delFee);
			System.out.println(orderNo);
		}
		
		//받아온 주문번호로 상세 주문서 만들기
		int result = 0;
		if(orderNo>0) {//주문번호를 받아왔다면
			if(memId!=null) {
			//회원 주문번호로 주문상세했는지 결과값 받기
			result = new OrderDetailService().insertMOrderDetail(orderNo,eventDate,pno,price,amount,strName);
			System.out.println(result);
			} else {
			//비회원 주문번호로 주문상세했는지 결과값 받기
			result = new OrderDetailService().insertBOrderDetail(orderNo,eventDate,pno,price,amount,strName);
			System.out.println(result);
			}
		}

		if(orderNo==0||result==0) {//주문번호 0 : 주문서 작성안됨, 결과값 0 : 주문디테일 작성안됨
			request.setAttribute("errorMsg", "구매가 되지 않았습니다. 다시 시도해주세요");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
		} else {
			request.setAttribute("alertMsg", "구매해주셔서 감사합니다. 주문번호는 이메일을 확인해주세요");
			response.sendRedirect(request.getContextPath()+"/soldout.xoxo");
		}
	}

}
