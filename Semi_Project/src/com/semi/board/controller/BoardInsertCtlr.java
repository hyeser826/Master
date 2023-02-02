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
@WebServlet("/insert.psn")
public class BoardInsertCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

	
			String category = request.getParameter("category");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String memId = request.getParameter("memId");
			
			
			String strName = request.getParameter("strName");
			String mstIntro = request.getParameter("mstIntro");
			
			String coNum = request.getParameter("coNum");
			String coName = request.getParameter("coName");
			String ceo = request.getParameter("ceo");
			String coKind = request.getParameter("coKind");
			
			
			Board b = new Board();
			int result=0;
			
			
			if(category.equals("LEVELUP")) {
				
				b.setMemId(memId);
				b.setBoardTitle(title);
				b.setBoardContent(content);
				b.setBoardCategory(category);
				
				b.setStrName(strName);
				b.setMasterIntro(mstIntro);
				
				b.setCoNumber(coNum);
				b.setCoName(coName);
				b.setCeo(ceo);
				b.setCoKind(coKind);

				
				result = new BoardService().insertLvBoard(b);
				
			}else if(category.equals("PERSONAL")){
				
				b.setMemId(memId);
				b.setBoardTitle(title);
				b.setBoardContent(content);
				b.setBoardCategory(category);

				
				result = new BoardService().insertPsnBoard(b);
				
			}

			
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/list.psn?currentPage=1");
				
			} else {

				request.setAttribute("errorPage", "게시글 작성에 실패했습니다.");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
			
			

		
		
		
	}

}
