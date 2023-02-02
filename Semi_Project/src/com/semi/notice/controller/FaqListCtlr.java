package com.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.notice.model.service.NoticeService;
import com.semi.notice.model.vo.Notice;

/**
 * 메뉴바 hover시 고객센터 하위 자주묻는 질문에 대한 내용
 * 검색시 해당 문구를 포함한 내용을 보여주며
 * 카테고리를 크게 상품, 결제, 개인정보로 나눠
 * 클릭시 해당 관련 자주묻는 질문만 보이게 함
 * 해당 자주묻는 질문에 대해서는 해당 질문에 hover시 slideDown()되게 함
 */
@WebServlet("/list.fq")
public class FaqListCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqListCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		공지사항 전체 리스트를 조회해 조회 결과를 담아 응답페이지로 포워딩 하기
		ArrayList<Notice> list = new NoticeService().selectFaqList();
//		응답페이지에 리스트 담아서 포워딩
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/notice/faqListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
