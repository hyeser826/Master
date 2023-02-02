package com.semi.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.manager.model.service.ManagerService;
import com.semi.manager.master.model.vo.Master;

/**
 * Servlet implementation class UpdateGradeController
 */
@WebServlet("/updateGrade.ma")
public class UpdateGradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGradeController() {
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
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		Master mr = new Master();
		mr.setMemNo(memNo);
		mr.setCoName(request.getParameter("coName"));
		mr.setCoNumber(request.getParameter("coNumber"));
		mr.setCeo(request.getParameter("ceo"));
		mr.setCoKind(request.getParameter("coKind"));
		mr.setStrName(request.getParameter("strName"));
		mr.setMasterIntro(request.getParameter("masterIntro"));
		
		//1. member 테이블에서 회원 grade 장인으로 update
		int result = new ManagerService().updateGrade(memNo);
		//2. master 테이블에 장인 정보 insert
		int result2 = new ManagerService().insertMaster(mr);
		
		if(result*result2>0) {//성공
			request.getSession().setAttribute("alertMsg", "장인 등급 신청이 승인되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+memNo);
		}else {//실패
			request.getSession().setAttribute("alertMsg", "장인 등급 변경에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+memNo);
		}
		
	}

}
