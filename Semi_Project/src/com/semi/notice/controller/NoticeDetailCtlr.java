package com.semi.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.notice.model.service.NoticeService;
import com.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailCtlr
 */
@WebServlet("/detail.no")
public class NoticeDetailCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클릭시 해당 공지사항 번호
		int noticeNo = Integer.parseInt(request.getParameter("nno"));

		//해당 글을 클릭했을 때 상세조회가 되어야되기 때문에 조회수도 같이 증가되어야 한다.
		
		//조회수 증가용 서비스
		Notice n = new NoticeService().increaseCount(noticeNo);      
		
		if(n!=null) {
			request.setAttribute("notice", n);
			request.getRequestDispatcher("/views/notice/noticeDetail.jsp").forward(request, response);
			
		} else {
			request.setAttribute("errorMsg", "해당 페이지를 조회할 수 없습니다.");
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
