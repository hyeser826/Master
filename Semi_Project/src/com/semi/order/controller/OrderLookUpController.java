package com.semi.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.order.model.service.OrderDetailService;
import com.semi.order.model.service.OrderService;
import com.semi.order.model.vo.Order;
import com.semi.order.model.vo.OrderDetail;

/**
 * Servlet implementation class OrderLookUpController
 */
@WebServlet("/orderlookup.or")
public class OrderLookUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderLookUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Member loginMem = (Member)session.getAttribute("loginMem");
		//로그인 정보에 담긴 멤버번호 가져오기
		int memNo = loginMem.getMemNo();
		
		ArrayList<Order> list = new OrderService().selectOrderList(memNo);
		LinkedHashMap<Integer,ArrayList<OrderDetail>> map = new LinkedHashMap<>();
		
		for(Order o : list){
			System.out.println("o.getOrderNo : "+o.getOrderNo());
		    map.put(o.getOrderNo(),new OrderDetailService().selectOrderDetailList(o.getOrderNo()));
		}
		System.out.println("orderController's list : "+list);
		System.out.println("orderController's map : "+map);
		
		//가져온 상품리스트 jsp로 setAttribute해서 넘기기
		request.setAttribute("list", list);
		request.setAttribute("map", map);
		
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("/views/order/orderListView.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
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
