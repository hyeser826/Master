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
 * Servlet implementation class MasterUpdateController
 */
@WebServlet("/masterUpdate.ma")
public class MasterUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int masterNo = Integer.parseInt(request.getParameter("masterNo0"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String coName = request.getParameter("coName0");
		String coNumber = request.getParameter("coNumber0");
		String ceo = request.getParameter("ceo0");
		String coKind = request.getParameter("coKind0");
		String strName = request.getParameter("strName0");
		String masterIntro = request.getParameter("masterIntro0");
		String status = request.getParameter("status0");
		
		//status가 y면 update, n이면 alert
		if(status.equals("Y")) {
			Master mr = new Master(masterNo,memNo,coName,coNumber,ceo,coKind,strName,masterIntro,status);
			int result = new ManagerService().updateMaster(mr);
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "장인 정보가 수정되었습니다.");
			}else {
				request.getSession().setAttribute("alertMsg", "장인 정보 수정에 실패했습니다.");
			}
		}else {
			request.getSession().setAttribute("alertMsg", "장인 상태가 N인 경우 정보 수정이 불가합니다.");
		}
		
		response.sendRedirect(request.getContextPath()+"/masterDetail.ma?mno="+memNo);
	}

}
