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
 * Servlet implementation class BoardUpdateCtlr
 */
@WebServlet("/update.psn")
public class BoardUpdateCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

			String memId = request.getParameter("memId");
		
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));

			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			String coNum = request.getParameter("coNum");
			String coName = request.getParameter("coName");
			String ceo = request.getParameter("ceo");
			String coKind = request.getParameter("coKind");
			
			String dearPsn = request.getParameter("dearPsn");
			
			
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setCoNumber(coNum);
			b.setCoName(coName);
			b.setCeo(ceo);
			b.setCoKind(coKind);
			
			
			int result;
			
			if(memId.equals("admin")) {
				result = new BoardService().updateAnswer(boardNo, dearPsn);
			} else {
				result = new BoardService().updateBoard(b);
			}
			
			
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "게시글 수정성공");
				response.sendRedirect(request.getContextPath()+"/detail.psn?bno="+boardNo);
			} else {
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}

	}

}
