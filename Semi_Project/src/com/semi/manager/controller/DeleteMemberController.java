package com.semi.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.manager.model.service.ManagerService;

/**
 * Servlet implementation class ManagerMemberDeleteController
 */
@WebServlet("/delete.ma")
public class DeleteMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memNo = Integer.parseInt(request.getParameter("mno"));
		String mstStatus = request.getParameter("mst");//Y, N, null
		
		int result = 1;
		if(mstStatus!=null) {//장인 정보가 있다면 장인 상태도 N으로 UPDATE
			result = new ManagerService().deleteMaster(memNo);
		}
		int result2 = new ManagerService().deleteMember(memNo);//회원 상태 N으로 UPDATE
		
		if(result*result2>0) {//성공
			request.getSession().setAttribute("alertMsg", "회원 탈퇴 완료");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+memNo);
		}else {//실패
			request.getSession().setAttribute("alertMsg", "회원 탈퇴 실패");
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
