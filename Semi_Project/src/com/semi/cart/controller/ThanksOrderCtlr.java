package com.semi.cart.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.cart.model.service.CartService;
import com.semi.cart.model.vo.Cart;
import com.semi.cart.order.model.service.OrderDetailService;
import com.semi.cart.order.model.service.OrderService;

/**
 * Servlet implementation class ThanksOrderCtlr
 */
@WebServlet("/insertnew.odr")
public class ThanksOrderCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanksOrderCtlr() {
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
		String[] cartNos = request.getParameterValues("cartNo");
//		배열에 저장된 카트번호(getParameterValues : String으로만 옴)
		String cartNo = "";
		ArrayList<Cart> buyList = new ArrayList<>();
//		반복문으로 선택한 체크박스 카트번호만 카트리스트로 저장
		for(int i=0; i<cartNos.length; i++) {
			cartNo = cartNos[i];
			buyList.addAll(new CartService().selectbuyList(cartNo));
		}
//		MEMORDER에 들어갈 정보
		String memId = request.getParameter("memId");
		String reciverName = request.getParameter("reciverName");
		String reciverPhone = request.getParameter("reciverPhone");
		String orderAddress = request.getParameter("orderAddress");
		int totalDel = 100000;
		System.out.println(memId+reciverName+reciverPhone+orderAddress+totalDel);
//		int totalDel = Integer.getInteger(request.getParameter("totalDel"));
		int orderNo = 0;
		int result1 = 0;
		int result2 = 0;
		for(int i=0; i<cartNos.length; i++) {
			if(i==0) {
				orderNo = new OrderService().insertNewOrder(memId,reciverName,reciverPhone,orderAddress,totalDel);
			}
		}
			//order_detail에 넘길 카트번호로 배열의 길이만큼 담겼는지 확인위해 result1에 담아오기
			result1 = new OrderDetailService().insertOrderDetail(orderNo,buyList);
		System.out.println(result1);
		
		if(result1 == cartNos.length) {
			
			//장바구니에 담긴상품 상태 N 수량도 0바꾸고 카트번호로 배열의 길이만큼 담겼는지 확인위해 result2에 담아오기
			result2 = new CartService().afterOrderCart(buyList);
			System.out.println(result2);
			
			if(result2 == cartNos.length) {
				request.setAttribute("alertMsg", "구매가 완료되었습니다. 구매해주셔서 감사합니다");
				response.sendRedirect(request.getContextPath()+"/soldout.xo");
			}
			
		}else {
			request.setAttribute("errorMsg", "구매가 되지 않았습니다. 다시 시도해주세요");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
