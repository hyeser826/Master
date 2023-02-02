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
import com.semi.member.model.vo.Member;


/**
 * Servlet implementation class BoardDeleteCtlr
 */
@WebServlet("/odpmt.li")
public class OrderPaymentCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPaymentCtlr() {
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
		request.setCharacterEncoding("UTF-8"); //인코딩 먼저 해주고	
		
//		<input type="checkbox" name="cartNo" value="<%=mdlist.get(i).getCartNo() %>" checked>
//		<input type="checkbox" name="cartNo" value="<%=aclist.get(i).getCartNo() %>" checked
//		선택된 카트번호만 배열에 저장하기
		
//		String[] cartNos = {"5","6","8","10"}; //'user2';의 카트 임의로 사용
		String[] cartNos = request.getParameterValues("cartNo");
//		배열에 저장된 카트번호(getParameterValues : String으로만 옴)
		String cartNo = "";
//		<input type="hidden" name="deliveryFee<%=i %>" value="0">
//		<input type="hidden" name="deliveryFee<%=i %>" value="<%=mdlist.get(i).getDeliveryFee() %>">
//		배송비는 0으로 왔으면 5만원 넘어서 무료라서 무료된 애들만 골라서 deliveryFee 0으로 업데이트 해야
		
		
//		배열에 있는 카트번호 하나씩 for문 돌려서 Cart Arraylist로 받아오기
//		일단 빈배열 : buyList
		ArrayList<Cart> buyList = new ArrayList<>();
//		반복문으로 선택한 체크박스 카트번호만 카트리스트로 저장
		for(int i=0; i<cartNos.length; i++) {
			cartNo = cartNos[i];
			buyList.addAll(new CartService().selectbuyList(cartNo));
		}
		
//		cartNos배열에 담긴 순서 > md > ac > 장인홈별로 > 상품순으로  cartNo가 담겨와서
//		그 순서대로 카트내용 전체 담아왔음
		
//		<input type="hidden" name="finalPrice" value="">
//		<input type="hidden" name="mdAllMdPrice" value="">
//		<input type="hidden" name="acAllMdPrice" value="">
//		이중에 클릭하는 버튼에 따라 value값이 this로 넘어오게 하기로 함.
//		공에만 선택했는지, 체험만 선택했는지, 전체를 선택했는지 구분할 수 있음
//		int dfee = Integer.getInteger(request.getParameter("deliveryFee"));
		System.out.println(cartNos);
		if(buyList!=null) {
			//배송관련 회원의 기본정보는 세션에서 직접받기
			request.setAttribute("buyList", buyList); //주문할 목록에 띄워줄 cart에 담긴 정보 list
			request.getRequestDispatcher("/views/cart/readyToOrder.jsp").forward(request, response); //주문결제 페이지로 결과 보내기
		}else {
			request.setAttribute("errorMsg", "장바구니 상품 구매에 실패하였습니다.");
			request.getRequestDispatcher("/views/commom/errorPage.jsp").forward(request, response);
			
		}
		

	}

}
