package com.semi.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.order.model.service.OrderDetailService;
import com.semi.order.model.service.OrderService;
import com.semi.order.model.vo.Delivery;
import com.semi.order.model.vo.Order;
import com.semi.order.model.vo.OrderDetail;

/**
 * Servlet implementation class OrderDetailController
 */
@WebServlet("/detail.or")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기까지는 오시나요??");
		int ono = Integer.parseInt(request.getParameter("ono"));
//		System.out.println("주문디테일 ono : "+ono);
		
		int result =1;
		if(result>0) {
			//주문건 가져오기 -> [주문정보] 주문번호, 회원번호(주문자), 결제수단이름, 배송지주소,수령자 주소, 수령자번호, 주문일자, 총 배송비
			Order o = new OrderService().selectOrder(ono);
			
			//주문건에 대한 주문상세 가져오기 -> [주문상품(총 몇 개 / 총 가격 원)] 상품리스트_ 대표이미지,상품명, 수량, 상품구매금액(수량*가격),처리상태,장인번호 가져오기
			ArrayList<OrderDetail> odList = new OrderDetailService().selectOrderDetail(ono);
			
			System.out.println(o);
			System.out.println(o.getTotalDel());

			request.setAttribute("o", o);
			request.setAttribute("odList", odList);
			
			//조회해온 데이터 담아서 상세보기 페이지로 포워딩
			request.getRequestDispatcher("/views/order/orderDetail.jsp").forward(request, response);
			
		}else {
			//실패시 에러페이지
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
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
