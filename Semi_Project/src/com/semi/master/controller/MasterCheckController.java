package com.semi.master.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.master.model.service.MasterService;
import com.semi.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class MasterCheckController
 */
@WebServlet("/mypagepr.ms")
public class MasterCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 시 세션에 로그인 유저 정보를 담아놨기 때문에 로그인 유저 정보가 있는지 없는지 판단하여 로그인 유무 확인
		HttpSession session = request.getSession();
		
		Member loginMem = (Member)session.getAttribute("loginMem");
		//로그인 정보에 담긴 멤버번호 가져오기
		int memNo = loginMem.getMemNo();
		
		//가져온 멤버번호로 장인번호로 조회해서 그 장인에 대한 상품리스트 가져오고 
		ArrayList<Product> list = new ProductService().masterCheck(memNo);
		Master m = new MasterService().selectMst(memNo);
		
		System.out.println("masterCheckController's list : "+list);
		System.out.println("masterCheckController's m : "+m);
		
		//가져온 상품리스트 jsp로 setAttribute해서 넘기기
		request.setAttribute("list", list);
		request.setAttribute("m", m);
		
		request.getRequestDispatcher("/views/master/masterProductList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
