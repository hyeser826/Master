<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member,com.semi.product.model.vo.Product
    							,java.util.ArrayList
    							,com.semi.JY.productboard.model.vo.ProductBoard    							" %>
    <%
    
    ArrayList<ProductBoard> list =(ArrayList<ProductBoard>)request.getAttribute("list");
 
    %>
    <%@include file="/views/common/menubar.jsp" %>
    
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script> 

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- 아래는 내가 다시  -->



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
        width : 28%;
        height: auto;
        line-height: auto;
        margin : 0 auto; /*가운데 정렬*/
        padding-left: 20px;
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
        width : 850px;
        height: auto;
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
            <div id="header">
                <div id="header1"></div>
            </div>
            <div id="left-side">
    			<%@include file="/views/common/leftbar2.jsp" %>
    		</div>
    		
            <div id="right-side">
               
                <div id="content2">
                    <div class="container">
                        <h2>1:1문의</h2>
                        <div class="container">
                            <table class="table table-bordered">
                          
                        <table class="table table-hover">
                          <thead>
                            <tr>
                              <th>문의번호</th>
                              <th>작성자</th>
                              <th>내용</th>
                              <th>작성일</th>
                              <th>상태</th>
                            </tr>
                          </thead>
                          <tbody>
                            <%if(list.isEmpty()){%>
                            	<tr>
                            		<td colspan="6">
                            			문의내역없음.
                            		</td>
                            	</tr>
                            <%}else{ %>
                            	<%for(ProductBoard i : list) {%>
                       				
                            <tr>
                              <td><%=i.getProductBoardNo() %></td>
                               <td><%=i.getMemNo() %></td>
                               <td><%=i.getProductBoardContent() %></td>
                              <td><%=i.getProductBoardDate()%></td>
                           	  <td><%=i.getStatus() %></td>
                            </tr>
                            	<%} %>
                            <%} %>
                            
                    
                          </tbody>
                        </table>
                      </div>
                    <hr>
                </div>
                <div id="content3">
                </div>
        </div>
        <div id="footer"></div>
    </div>
    <script>

        //아래는 조회버튼누르면 조회되게끔

        
   
        	$(function(){
        		$("#btnselect").click(function(){
        			
        			//location.href ='MasterRe.me'
        			$.ajax({
        				url : "MasterRe.me",
        				data : {input : $("#Calendar").val()},
        				dataType: 'json',
        				type :"get",
        				success : function(){
        					console.log("통신성공")
        				},
        				error : function(){
        					console.log("통신실패")
        				},
        				complete : function(){
        					console.log("통신여부와 상관없이 실행")
        				}
        			
        			})
        			
        		})
        	}
        	
        	
        	);

        </script>
    </body>
    </html>