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
import com.semi.member.model.service.MemberService;
import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Attachment;
import com.semi.board.model.vo.Board;
import com.semi.master.model.service.MasterService;
import com.semi.member.model.vo.Member;
import com.semi.JY.pom.model.service.DeliveryService;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.order.model.vo.Order;
import com.semi.JY.orderdetail.model.vo.OrderDetail;


/**
 * Servlet implementation class pomController
 */
@WebServlet("/list.pom")
public class PomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PomController() {
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
		System.out.println("memNo: "+memNo);

		//필요한 테이블 : 장인,상품,주문디테일
		
		//여기서 장인테이블이랑 상품이랑 오더디테일 연결해와서 
		//리스트로 받아서 한방에 뿌려주기
		
		//가져온 멤버번호로 장인번호로 조회해서 그 장인에 대한 주문리스트 가져오고 넣고 뷰단에 뿌려주기
		//ArrayList<orderDetail> list = new ProductService().masterCheck(memNo);
		//Master m = new MasterService().selectMst(memNo);
		
		//아래는 장인번호 
		Master m = new DeliveryService().selectMasterNo(memNo);
		System.out.println("m : "+m);	
		
		//오더디테일 (유저)
		OrderDetail od =new DeliveryService().selectOrderDetailNo(memNo);
		
		//PRODUCT 테이블에서 장인넘버 가지러가자 ! 
		Product p=new DeliveryService().selectProductMasterNo(memNo);
		
		
		//리스트 조회 (OrderDetail)
		ArrayList<OrderDetail> list=new DeliveryService().selectBoard(memNo,od,m);
		request.setAttribute("list",list);
		System.out.println("pom 컨트롤러 list  : "+list);
		
		
		response.setContentType("application/json; charset=UTF-8"); 
		new Gson().toJson(list,response.getWriter());
		
	
	if(session.getAttribute("loginMem")==null) {
		session.setAttribute("alert","로그인후 가능한 서비스입니다.");
		response.sendRedirect(request.getContextPath());
	}else {
	
		request.getRequestDispatcher("views/jy/pom/pom.jsp").forward(request, response);
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
