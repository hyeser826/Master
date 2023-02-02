package com.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.notice.model.service.NoticeService;
import com.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertCtlr
 */
@WebServlet("/updateForm.no")
public class NoticeUpdateFormCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateFormCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		noticeDetailView에서 수정하기 버튼을 누르면 글번호가 넘어오며 요청된다.
//		해당하는 글번호로 notice정보 조회해와서
//		noticeUpdateForm.jsp로 해당 notice 객체를 포워딩해 
//		noticeUpdateForm에서 해당 정보들을 수정할 수 있께 데이터를 뽑아준다.
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		Notice n = new NoticeService().selectNotice(noticeNo);
//		가지고온 notice 객체를 noticeUpdateForm.jsp로 포워딩해
		request.setAttribute("Notice", n);
		HttpSession session = request.getSession();
		
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("/views/notice/noticeUpdateForm.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}

//		해당 Form에 value에 각각 데이터를 뿌려준 뒤 수정까지 작성하기
		
//		수정에 성공하면 상세정보 페이지로 재요청
//		실패하면 에러페이지에 공지사항 수정 실패 메세지 띄우기
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
