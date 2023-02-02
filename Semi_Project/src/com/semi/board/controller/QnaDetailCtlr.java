package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.member.model.vo.Member;


/**
 * Servlet implementation class NoticeDetailCtlr
 */
@WebServlet("/detail.qna")
public class QnaDetailCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		//조회수 증가 / 조회수 증가가 성공적으로 이루어졌다면 게시글 번호로 게시글 조회 / 게시글 번호로 첨부파일 조회
		//조회해온 데이터 담아서 상세보기 페이지로 포워딩
		//실패시 에러페이지
		
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");
		
		String chkMaster = loginMem.getMemId();
		ArrayList<Integer> pnoList = new ArrayList<Integer>();
		
		if(loginMem.getGrade().equals("장인")) {			
			pnoList = new BoardService().checkMaster(chkMaster);
		}
		
		if(pnoList!=null)
		for(int checkPno : pnoList) {
			System.out.println(checkPno);
		}

		Board b = new BoardService().selectBoard(bno);
		
		if(b!=null) {
			
			if(pnoList!=null) {
				request.setAttribute("pnoList", pnoList);
			}
				request.setAttribute("b", b);
				request.getRequestDispatcher("/views/board/qnaDetailView.jsp").forward(request, response);				

		} else {
			request.setAttribute("errorMsg", "해당 게시글을 조회할 수 없습니다.");
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
