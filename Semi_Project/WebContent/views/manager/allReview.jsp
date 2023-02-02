<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,com.semi.common.model.vo.PageInfo
    							,com.semi.manager.review.model.vo.Review
    							,com.semi.member.model.vo.Member"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Member m = (Member)request.getAttribute("m");
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("reviewList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderListView</title>
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
    #manager-menu{
        margin : 0 auto; /*가운데 정렬*/
        width: 200px;
        height: 60px;
        font-size: 20px;
        padding: 0px;
     	align:"center"
    }
    #manager-menu>li{
        margin : 0 auto; /*가운데 정렬*/
        width: 100%;
        height: 100%;
        font-size: 20px;
        cursor: pointer;
        padding: 15px;
        list-style : none;
    }
    #manager-menu>li:hover{
        border-bottom: 3px solid rgba(255, 221, 0, 0.343);
    }
    #manager-menu>li>a{
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
    .order-hover:hover{
    	background-color: lightgrey;
    	cursor: pointer;
    }
    .titleQna:hover{
    	background-color: lightgrey;
    	cursor: pointer;
    }
    #qnaTable input{
    	width: 50%;
    	border: none;
    }
    #qnaTable textarea{
    	width: 95%;
    }
	.custom-btn {
	  width: 100px;
	  height: 30px;
	  border-radius: 5px;
	  padding: 5px 10px;
	  font-family: 'Lato', sans-serif;
	  font-weight: 500;
	  background: transparent;
	  cursor: pointer;
	  transition: all 0.3s ease;
	  position: relative;
	  display: inline-block;
	   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
	   7px 7px 20px 0px rgba(0,0,0,.1),
	   4px 4px 5px 0px rgba(0,0,0,.1);
	  outline: none;
	  margin-left:20px;
	}
	.paging-btn{
    	background-color: rgba(255, 221, 0, 0.126);
    	border: 3px solid rgba(255, 221, 0, 0.343);
    }
    .btn-delete2 {
	  width: 50px;
	  margin: 0px;
	  font-size: 13px;
	  color: white;
	  border: none;
	  background: rgb(255,27,0);
	background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
	}
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
	<div class="outer">
    	<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">회원 관리</p>
	        <br><br>
	    </div>
	    
        <div id="left-side" align="center">
            <ol id="manager-menu" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/manager.ma?currentPage=1">관리자 페이지</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath%>/manager.ma?currentPage=1">회원 관리</a></li>
                <li><a href="<%=contextPath %>/manageOrder.ma?currentPage=1">주문 관리</a></li>
            </ol>
        </div>
        
        <div id="right-side">
        	<h2 align="center"><%=m.getMemName()%> 회원의 리뷰내역</h2>
        	<br>
	        <table id="reviewTable" class="table" border="1" cellpadding="5">
	            <thead align="center">
	              	<tr>
		                <th width="150">작성일</th>
		                <th width="200">상품명</th>
		                <th width="280">리뷰내용</th>
		                <th width="95">상태</th>
		                <th>관리</th>
	                </tr>
	            </thead>
	            <tbody>
	            <%if(list.isEmpty()){ %>
	            	<tr>
	            		<td colspan="5">해당 회원의 리뷰 내역이 없습니다.</td>
	            	</tr>
	            <%}else{ %>
	                <%for(Review r : list){%>
	                <tr>
	                	<td align="center"><%=r.getProductBoardDate()%></td>
	                	<td align="center"><%=r.getProductName()%></td>
	                	<td><%=r.getProductBoardContent()%></td>
	                	<td align="center"><%=r.getStatus()%></td>
	                	
	                	<td align="center">
	                	<%if(r.getStatus().equals("Y")){ %>
	                		<a href="<%=contextPath%>/deleteReview.ma?pbNo=<%=r.getProductBoardNo()%>&mno=<%=m.getMemNo() %>" onclick="return reviewChk();" class="custom-btn btn-delete2">삭제</a>
	                	<%} %>
	                	</td>
	                </tr>
	                <%} %>
	            <%} %>
	            </tbody>
	        </table>
	        <br>
	        <script>
		        function reviewChk(){
		    		var result = confirm("해당 리뷰를 삭제하시겠습니까?");
		    		return result;
		    	}
	        </script>
	        
	        <!-- 페이징바 -->
	        <div align="center" class="paging-area">
	        	<%if(pi!=null){ %>
	        	<%if(pi.getCurrentPage() != 1){ %>
		        	<button class="paging-btn" onclick="location.href='<%=contextPath%>/allReview.ma?currentPage=<%=pi.getCurrentPage()-1%>'">&lt;</button>
		        <%}else{ %>
		        	<button class="paging-btn" disabled>&lt;</button>
		        <%} %>
		        
		        <%for(int i=pi.getStartPage(); i<=pi.getEndPage(); i++){ %>
	        		<!-- 내가 요청한 페이지 버튼 비활성화 -->
		        	<%if(i==pi.getCurrentPage()){ %>
		        		<button class="paging-btn" disabled><%=i %></button>
		        	<%}else{ %>
		        		<button class="paging-btn" onclick="location.href='<%=contextPath%>/allReview.ma?currentPage=<%=i%>'"><%=i%></button>
		        	 <%} %>
		        <%} %>
		        
		        <%if(pi.getCurrentPage() != pi.getMaxPage()){ %>
		        <button class="paging-btn" onclick="location.href='<%=contextPath%>/allReview.ma?currentPage=<%=pi.getCurrentPage()+1%>'">&gt;</button>
	        	<%}else{ %>
	        		<button class="paging-btn" disabled>&gt;</button>
		        <%} %>
		        <%} %>
	        </div>
	        <div align="right">
	        <a href="<%=contextPath%>/detail.ma?mno=<%=m.getMemNo()%>" class="btn btn-warning" style="color:grey;">돌아가기</a>
	        </div>
	        <br><br><br><br><br><br><br><br><br><br>
        </div>
</body>
</html>