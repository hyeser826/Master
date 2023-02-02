package com.semi.JY.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.vo.Board;
import com.semi.member.model.vo.Member;
import com.semi.JY.productboard.model.service.ProductBoardService;
import com.semi.JY.productboard.model.vo.ProductBoard;
import com.semi.JY.review.model.service.ReviewService;
import com.semi.JY.review.model.vo.Review;







/**
 * Servlet implementation class reviewController
 */
@WebServlet("/list.rv")  //마이페이지에서 내작리 누르면 나오는 페이지  
public class reviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reviewController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		//리뷰에서 (작성날짜,상품명,리뷰내용)가져오기 (Date,string,string)-->이거아님 수정해야됨
		
		//아래 회원번호 가져오기 . 
		Member loginMem = (Member)session.getAttribute("loginMem");	
		int memNo = loginMem.getMemNo();
		System.out.println("memNo : "+memNo);
		
		
		ProductBoard pb =new ProductBoardService().selectProductBoard(memNo);
		
		//조회 해서 list로 담아오기 (ProductBoard)
		ArrayList<ProductBoard> list =new ReviewService().selectReviewList(memNo,pb);
		
		request.setAttribute("list",list);
			
		if(session.getAttribute("loginMem")==null) {
			session.setAttribute("alert","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}else {
			request.getRequestDispatcher("views/jy/review/myreviewed.jsp").forward(request, response);
		}
		
		}


}
