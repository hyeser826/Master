package com.semi.master.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.master.model.service.MasterService;
import com.semi.master.model.vo.Master;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MasterIntroController
 */
@WebServlet("/introms.in")
public class MasterIntroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterIntroController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 시 세션에 로그인 유저 정보를 담아놨기 때문에 로그인 유저 정보가 있는지 없는지 판단하여 로그인 유무 확인
		HttpSession session = request.getSession();
		
		Member loginMem = (Member)session.getAttribute("loginMem");
		//로그인 정보에 담긴 멤버번호 가져오기
		int memNo = loginMem.getMemNo();
		
		//장인객체 받아오기
		Master m = new MasterService().selectMst(memNo);
		request.setAttribute("m", m);
		
		//장인 소개 등록/수정 form으로 포워딩
		request.getRequestDispatcher("/views/master/masterIntroUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
