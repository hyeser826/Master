package com.semi.product.controller;

import java.io.File;
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
import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Attachment;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/update.pr")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
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
		
		//multipart/form-data로 전송이 되었는지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			//전송파일 용량 제한(int maxSize)
			int maxSize = 10 * 1024 * 1024;
			
			//전달된 파일을 저장시킬 폴더의 물리적인 경로 알아내기(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/product_upfiles/");
				
			//전달된 파일명을 수정작업 후 서버에 업로드
			//파일처리하려면 multipartRequest로 변환해야한다.
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			//multipartRequest로 변환하였으니 해당 객체로 전달받은 데이터 꺼내기
			int pno = Integer.parseInt(multiRequest.getParameter("pno"));
			int mstNo = Integer.parseInt(multiRequest.getParameter("mstno"));
			String pName = multiRequest.getParameter("name");
			String categoryNameOrigin = multiRequest.getParameter("categoryname"); 
			String categoryName = multiRequest.getParameter("category");
			int pPrice = Integer.parseInt(multiRequest.getParameter("price"));
			int deliveryFee = Integer.parseInt(multiRequest.getParameter("deliveryFee"));
			int pStock = Integer.parseInt(multiRequest.getParameter("stock"));
			String pStatus = multiRequest.getParameter("status");
			String pDescription = multiRequest.getParameter("content");
			String expPeriod = multiRequest.getParameter("expPeriod");
			
			Product p = new Product();
			p.setProNo(pno);
			p.setMstNo(mstNo);
			p.setProName(pName);
			//카테고리가 수정이 안되서 null이면 기존거로 세팅
			if(categoryName==null) {
				p.setCategoryName(categoryNameOrigin);
			}else {
				p.setCategoryName(categoryName);
			}
			p.setProPrice(pPrice);
			p.setDeliveryFee(deliveryFee);
			p.setProStock(pStock);
			p.setProStatus(pStatus);
			p.setProDescription(pDescription);
			p.setExpPeriod(expPeriod);
			
			
			//만약 전달된 새로운 첨부파일이 있다면 (첨부파일 수정)
			Attachment at = null;	//없으면 없는채로 가면 되니까 null로 초기화
			ArrayList<Attachment> list = new ArrayList<>();
			
			//새로 전달된 파일이름이 있을 때 list for문으로 돌려주기
			for(int i=1;i<=4;i++) {
				
				String key = "reUpfile"+i;
				
				if(multiRequest.getOriginalFileName(key)!=null) {
					at = new Attachment();
					at.setFileName(multiRequest.getOriginalFileName(key));
					at.setSysName(multiRequest.getFilesystemName(key));
					at.setFilePath("/resources/product_upfiles/");
					
					//만약 기존에도 첨부파일이 있는 경우 보냈던 파일번호랑 수정명을 받아준다.
					if(multiRequest.getParameter("originFileNo")!=null){
						//새로 첨부된 파일이 있고, 기존에도  파일이 있는 경우
						//update Attachment - 기존의 파일 고유번호를 이용하여 update한다.
						at.setAttachmentNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
						
						//새로운 첨부파일이 있다면 기존에 있던 첨부파일은 더이상 필요없으니 서버에서 삭제한다.
						new File(savePath+multiRequest.getParameter("originFileName")).delete();	
						
					}else {	//새로운 첨부파일은 있지마 기존엔 첨부파일이 없을 경우
						//insert Attachment - 기존에 파일이 없었으니 새로운 파일 정보를 insert한다.
						//새로 추가하려면 참조된 게시글의 번호 refBno를 가지고 가야한다.
						at.setProNo(pno);
					}
					list.add(at);
				}
			}
			System.out.println(p);
			System.out.println(list);
			//service로 요청보내기
			//새로운 첨부파일이 없는 경우 - p,(at)null == product update
			//새로운 첨부파일이 있고 기존 첨부파일이 있는 경우 - p,list(file_no) : product-update, list-update
			//새로운 첨부파일이 있고 기존 첨부파일이 없는 경우 - p,list(pno) : product-update, list-insert
			int result = new ProductService().updateProduct(p,list);
			
			System.out.println("updateController's result : "+result);
			if(result>0) {
				//성공시에 게시글 상세 페이지로 재요청
				request.getSession().setAttribute("alertMsg", "상품 수정 성공");
				response.sendRedirect(request.getContextPath()+"/detailms.pr?pno="+pno);
			}else {
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}
		}

	}
}
