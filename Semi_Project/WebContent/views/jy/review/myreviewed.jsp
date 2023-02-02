<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.ArrayList,com.semi.member.model.vo.Member,
    com.semi.board.model.vo.Board,
    com.semi.JY.product.model.vo.Product,
   com.semi.JY.productboard.model.vo.ProductBoard"%>

    <%
	ArrayList<ProductBoard> list = (ArrayList<ProductBoard>)request.getAttribute("list");
   	Product p = (Product)request.getAttribute("p");
%>
    <%@include file="/views/common/menubar.jsp" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <title>화면 구조 잡기 기본 </title>

    <style>
   
       .outer{
        width:100%;
        margin:auto; /*가운데 정렬*/
        margin-top:50px; /*위로부터 50px만큼 여백*/
    }
    .header{
        color : black;
        width : 1000px;
        height: 150px;
        line-height: auto;
        margin : 0 auto; /*가운데 정렬*/
        margin-bottom: 30px;
        border-bottom: 2px solid lightgrey;
    }
    #left-side{
        float : left;
        color : black;
        width : 25%;
        height: 1000px;
        line-height: auto;
        margin : 0 auto; /*가운데 정렬*/
        padding-left: 100px;
        padding-top: 30px;
    }
    #left-side a{
        text-decoration: none;
        color: black;
        font-size: 18px;
        font-weight: bold;
    }
    #mypage-menu{
        margin : 0 auto; /*가운데 정렬*/
        width: 200px;
        font-size: 20px;
        padding: 0px;
     	align:"center"
    }
    #mypage-menu>li{
        margin : 0 auto; /*가운데 정렬*/
        width: 100%;
        height: 100%;
        cursor: pointer;
        padding: 15px;
        list-style : none;
    }
    #mypage-menu>li>a{
        text-decoration : none;
      	color : black;
    }
    #right-side{
        float : left;
        color : black;
        width : 60%;
        height: 1000px;
        line-height: auto;
        margin : 0 auto; /*가운데 정렬*/
        padding: 30px;
        padding-left: 0px;
    }

	#mypage-menu>ol>li{
	    margin: 0 auto;
	    font-size: 10px;
	    cursor: pointer;
	    padding: 15px;
	    list-style: none;
	}
	#mypage-menu>ol>li>a{
		text-decoration: none;
		font-size: 15px;
	}
	li:hover {
		border-bottom: 3px solid rgba(255, 221, 0, 0.343);
	}
    </style>
    
    
    </head>
    <body>
    
    <!-- 
             Member loginMem = (Member)session.getAttribute("loginMem");
    
     -->
        <%
            String userId = loginMem.getMemId();
            String userName=loginMem.getMemName();
            String email = loginMem.getMemEmail();
            String phone =(loginMem.getMemPhone()==null)?"":loginMem.getMemPhone();
            String address=loginMem.getMemAddress();
            String grade = loginMem.getGrade();
        %>
       
        <div class ="outer">
            <div id="header"><div id="header1"><img src=""></div></div>
    	
    		<div id="left-side">
    			<%@include file="/views/common/leftbar2.jsp" %>
    		</div>
    		
    		<div id="right-side">
                    <div class="container">
                        <h2>내가 작성한리뷰 </h2>
                        <p>내가 작성한 리뷰 페이지 입니다.</p>            
                        <table class="table table-hover">
                          <thead>
                            <tr>
                              <th >작성날짜</th>
                              <th >상품번호</th>
                              <th >리뷰내용</th>
                              <th>확인</th>
                            </tr>
                          </thead>
                          <tbody>
                          	<!--reviewController에서 Review  list에 담아온 -->
                            <%if(list.isEmpty()) {%>
                            	<tr>
                            		<td>
										조회된 게시글이없습니다.
                            		</td>
                            	</tr>
									
								<%}else{%>
                         		   	<%for(ProductBoard i : list) {%>
	                            			<%if(i.getStatus().equals("Y")) {%>
                             <tr>
                           	
                                <td ><%=i.getProductBoardDate() %></td>
                                <td ><%=i.getProductNo()%></td>
                                <td><%=i.getProductBoardContent() %> </td>
                             	<td>
                       			  	  <a href="<%=contextPath%>/reviewEnrollForm.me" class="btn btn-primary" method="get" >리뷰수정</a>
                            	<br><br><br>
                       	    	      <a href="<%=contextPath%>/review.del" class="btn btn-danger" >리뷰삭제</a>
								</td>
										<%}else{%>
									<%} %>
								<%} %>
							<%} %>

                            </tr>
                            
                          </tbody>
                        </table>
                      </div>
              
                    <hr>
        	

        	<div id="fffooter">
        	</div>
    	</div>
    </div>
    
</body>
</html>




