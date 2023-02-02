package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class AjaxProductQnADetail
 */
@WebServlet("/readQna.qna")
public class AjaxProductQnADetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductQnADetail() {
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
		
		Board b = new BoardService().selectBoard(bno);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(b,response.getWriter());
		
		System.out.println(bno);
		System.out.println(b);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
