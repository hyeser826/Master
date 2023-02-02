package com.semi.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.manager.model.service.ManagerService;

/**
 * Servlet implementation class DownGradeController
 */
@WebServlet("/downGrade.ma")
public class DownGradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownGradeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memNo = Integer.parseInt(request.getParameter("mno"));
		
		//1. member 테이블에서 회원 grade 일반으로 update
		int result = new ManagerService().downGrade(memNo);
		//2. master 테이블에 장인 상태 N으로 update
		int result2 = new ManagerService().deleteMaster(memNo);
		
		if(result*result2>0) {//성공
			request.getSession().setAttribute("alertMsg", "장인 등급이 박탈되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+memNo);
		}else {//실패
			request.getSession().setAttribute("alertMsg", "장인 등급 박탈에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+memNo);
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
