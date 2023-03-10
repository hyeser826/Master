package com.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.service.MemberService;

@WebServlet("/IdCheck.me")
public class MemberCheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberCheckIdController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
					
		String memId = request.getParameter("memId");
		// join.jsp에서 받아온 key값이 userId이고
		// value값은 유저가 실제로 적은 값, String userId에는 value값이 들어간다.
		PrintWriter out = response.getWriter();
		int idDuplicationCheck = new MemberService().idDuplicationCheck(memId);

		// 성공여부 확인 : 개발자용
		if (idDuplicationCheck == 0) {
			System.out.println("이미 존재하는 아이디입니다.");
		} else if (idDuplicationCheck == 1) {
			System.out.println("사용 가능한 아이디입니다.");
		}
		out.write(idDuplicationCheck + ""); // --> ajax 결과값인 result가 됨
				// --> String으로 값을 내보낼 수 있도록 + "" 를 해준다
	}
		
}

























