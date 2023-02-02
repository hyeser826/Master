package com.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.semi.common.MyFileRenamePolicy;
import com.semi.member.model.vo.Member;
import com.semi.master.model.service.MasterService;
import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Attachment;
import com.semi.master.model.vo.Master;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class productInsertController
 */
@WebServlet("/insert.pr")
public class productInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productInsertController() {
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
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//용량제한
			int maxSize = 10*1024*1024;
			
			String savePath = request.getServletContext().getRealPath("/resources/product_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			//int memNo = Integer.parseInt(multiRequest.getParameter("memNo"));
			
			int masterNo = new MasterService().masterNumber(Integer.parseInt(multiRequest.getParameter("memNo")));
			
			Product p = new Product();
			
			p.setProName(multiRequest.getParameter("pname"));
			p.setCategoryName(multiRequest.getParameter("categoryName"));
			//request.getParameter는 jsp에서 받아온 name값으로 꺼내오는 거고 변수처리 해준 상황이면 그냥 변수명 넣어준다.
			p.setMstNo(masterNo); 		
			p.setProPrice(Integer.parseInt(multiRequest.getParameter("price")));
			p.setDeliveryFee(Integer.parseInt(multiRequest.getParameter("deliveryFee")));
			p.setProStock(Integer.parseInt(multiRequest.getParameter("stock")));
			p.setProDescription(multiRequest.getParameter("content"));
			p.setExpPeriod(multiRequest.getParameter("expPeriod"));
			p.setProStatus(multiRequest.getParameter("status"));
			
			//Attachment에 넣을 데이터
			//첨부파일이 여러개 넘어올 수 있기 때문에 ArrayList에 담아주기
			ArrayList<Attachment> list = new ArrayList<>();
			
			for(int i=1;i<=4;i++) {	//name값이 file1,file2,file3,...이기 때문에 인덱스를 활용해서 키값 활용

				//키값
				String key = "file"+i;
				
				//키값에 해당하는 input file요소에서 넘어온 파일 이름이 있는지 (파일첨부가 되었는지)
				if(multiRequest.getOriginalFileName(key) != null) {
					//첨부파일이 있으면
					//Attachment 객체를 생성해서 데이터 담고 list에 추가하기
					//원본명, 수정명, 폴더 경로, 파일레벨
					
					Attachment at = new Attachment();
					at.setFileName(multiRequest.getOriginalFileName(key));
					at.setSysName(multiRequest.getFilesystemName(key));
					at.setFilePath("/resources/product_upfiles/");
					
					if(i==1) {//대표이미지
						at.setFileLevel(1);
					}else {//서브이미지
						at.setFileLevel(2);
					}
					
					//at객체 데이터 세팅이 끝났으니 list에 추가해주기
					list.add(at);
					
				}
				
			}
			
			int result = new ProductService().insertProduct(p,list);
		
			if(result>0) {
				//성공시 수정된 물건의 상세페이지 띄워주기
				request.getSession().setAttribute("alertMsg","상품등록 성공");
				response.sendRedirect(request.getContextPath()+"/mypagepr.ms");
			}else {
				//실패시 에러페이지
				request.setAttribute("errorMsg", "상품등록 실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
		}

		
	}

}
