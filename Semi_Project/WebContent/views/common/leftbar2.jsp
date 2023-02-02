<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div id="left-side">
		<%if(loginMem != null) {%>
		<div>
          <ol id="mypage-menu" class="main-menu">
          
	          <li style="background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/orderlookup.or">주문내역</a></li>
	          <ol style="text-align:left;">
	          <li style="line-height:15px;"><a href="<%=contextPath%>/orderlookup.or">주문 조회</a></li>
	          </ol>
	             
	          <li style="background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/pwcheckform.me">내 정보</a></li>
	          <ol style="text-align:left;">
	          <li style="line-height:15px"><a href="<%=contextPath%>/pwcheckform.me">개인정보 수정</a></li>
	          <li style="line-height:15px"><a href="<%=contextPath%>/list.rv">내가 쓴 리뷰</a></li>
	          <li style="line-height:15px;"><a href="<%=contextPath%>/deleteForm.me">회원탈퇴</a></li>
	          </ol>
	      </ol>
	 	</div>         
			<%if(loginMem.getGrade().equals("장인")) {%>
		 	<div class="mst">     
		 	 	<ol id="mypage-menu" class="main-menu">    
		          <li style="background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/mypagepr.ms">내 상점 관리</a></li>
		          <ol style="text-align:left;">
		          <li style="line-height:15px"><a href="<%=contextPath %>/mypagepr.ms">상품 관리</a></li>
		          <li style="line-height:15px"><a href="<%=contextPath%>/list.pom">주문 관리</a></li>
		          </ol>
	        
	         	</ol>
	     	</div>
	 		<%} %>
	 	<%} %>
	</div>