<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,com.semi.product.model.vo.Product"%>
<%
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer{
		background:black;
		color:white;
		width:1000px;
		height:800px;
		margin:auto;  /*가운데 정렬*/
		margin-top:50px; /*위로부터 50px만큼 여백*/
	}
	.list-area{
		width:760px;
		margin:auto;
	}
	.thumbnail{
		border : 1px solid white;
		width : 220px;
		display : inline-block;
		margin : 15px;
	}
	.thumbnail:hover{
		cursor:pointer;
		opacity:0.7;
	}
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">전체 상품 페이지</h2>
		<br>
		<div class="list-area">
		<%if(!list.isEmpty()) {%>
			<%for(Product p : list) {%>
			<div class="thumbnail" align="center">
				<input type="hidden" value="<%=p.getpNo() %>">
				<img src="<%=contextPath %><%=p.getpName()%>" width="200px" height="150px">
				<p>
					<%=p.getProPrice()%><br>
					<%=p.getProName() %><br>
 					/*해당 상품의 장인페이지로 이동하는 부분 */
 					<a href="<%=contextPath()%>/" target="_blank">A장인홈</a>
				</p>
			</div>
			<%} %>
			<%}else{ %>
				조회된 게시글이 없습니다.
			<%} %>
		</div>
	</div>
	
	<script>
		$(function(){
			$(".thumbnail").click(function(){
				location.href="<%=contextPath%>/detail.pr?pno="+$(this).children().eq(0).val();
			})
		})
	
	</script>
	
</body>
</html>