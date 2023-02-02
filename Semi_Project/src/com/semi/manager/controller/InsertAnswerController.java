package com.semi.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.vo.Board;
import com.semi.manager.model.service.ManagerService;

/**
 * Servlet implementation class InsertAnswerController
 */
@WebServlet("/answer.ma")
public class InsertAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAnswerController() {
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
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardAnswer = request.getParameter("boardAnswer");
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardAnswer(boardAnswer);
		
		int result = new ManagerService().insertAnswer(b);
		
		response.getWriter().print(result);
	}

}
