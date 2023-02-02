package com.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;


/**
 * Servlet implementation class BoardInsertCtlr
 */
@WebServlet("/insert.qna")
public class QnaInsertCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertCtlr() {
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
		request.setCharacterEncoding("UTF-8");

		String categoryName = request.getParameter("categoryName");
	
		String memId = request.getParameter("memId");
		int pno = Integer.parseInt(request.getParameter("pno"));
		String strName = request.getParameter("strName");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
//		String categoryName = "4.악세사리";
//		String memId = "user1";
//		int pno = 1;
//		String strName = "김순덕장인홈";
//		
//		String title = "대량구매 관련";
//		String content = "저희가족 대식구인데 맞춰서 사용해보려고요! 가능한가요?";
			
		String pnoLink;	
		
		if(categoryName.equals("0.체험")) {		
			pnoLink = "http://localhost:8888/smp/detail.ac?pno="+pno;
		}else {
			pnoLink = "http://localhost:8888/smp/detail.md?pno="+pno;
		}

		String totalContent = pnoLink+" 에대한 내용입니다. ["+content+"]";

		int result=0;

		Board b = new Board();
			
		b.setMemId(memId);
		b.setProductNo(pno);
		b.setStrName(strName);
		b.setBoardTitle(title);
		b.setBoardContent(totalContent);
		

		result = new BoardService().insertQnaBoard(b);
				
			
			if(result>0) {
				//성공시 가장 최신글로 등록이 될테니, 리스트 게시글 목록 첫 페이지 띄우기
				request.getSession().setAttribute("alertMsg", "게시글 작성에 성공했습니다.");
				response.sendRedirect(request.getContextPath()+"/insert.done");
			} else {

				//실패시 에러페이지에 에러메세지와 함께 보내주기
				request.setAttribute("errorPage", "게시글 작성에 실패했습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
			
			

		
		
		
	}

}
