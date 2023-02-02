package com.semi.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.vo.Board;
import com.semi.manager.model.service.ManagerService;
import com.semi.manager.master.model.vo.Master;
import com.semi.member.model.vo.Member;
import com.semi.manager.orderdetail.model.vo.OrderDetail;
import com.semi.manager.review.model.vo.Review;

/**
 * Servlet implementation class ManagerDetailViewController
 */
@WebServlet("/detail.ma")
public class ManagerDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerDetailViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		//회원 정보 조회
		Member m = new ManagerService().selectMemberDetail(mno);
		Master mr = new ManagerService().selectMasterDetail(mno);
		
		//리뷰 조회
		ArrayList<Review> list = new ManagerService().selectReview(mno);
		
		//구매내역 조회
		ArrayList<OrderDetail> orderList = new ManagerService().selectOrderList(mno);
		
		//1:1 문의내역 조회
		ArrayList<Board> boardList = new ManagerService().selectThreeBoardList(mno);
		if(!boardList.isEmpty()) {
			for(Board b : boardList) {
				switch(b.getStatus()) {
				case "W" : b.setStatus("미답변"); break;
				case "Y" : b.setStatus("답변완료"); break;
				case "N" : b.setStatus("삭제"); break;
				}
				switch(b.getBoardCategory()) {
				case "PERSONAL" : b.setBoardCategory("일반문의"); break;
				case "LEVELUP" : b.setBoardCategory("입점문의"); break;
				}
			}
		}
		
		HttpSession session = request.getSession();
		if(m!=null) {//회원 정보 상세보기
			request.setAttribute("m", m);
			if(mr!=null) {//장인(상태 Y+N) 회원 정보 조회
				request.setAttribute("mr", mr);
			}
			
			request.setAttribute("list", list);//리뷰 조회
			request.setAttribute("orderList", orderList);//구매내역 조회
			request.setAttribute("boardList", boardList);//1:1 문의내역 조회
			
			try {
				if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("/views/manager/managerDetailView.jsp").forward(request, response);
				}
			} catch(NullPointerException e){
				e.printStackTrace();
				session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
				response.sendRedirect(request.getContextPath());
			}
		
		}else {//회원 정보 상세보기 실패
			session.setAttribute("alertsMsg", "회원 정보 상세보기 실패");
			response.sendRedirect(request.getContextPath());
			
		}
	}

}
