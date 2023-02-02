<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.order.model.vo.*"%>
    
<% 
int productNo = Integer.parseInt(request.getParameter("productNo"));
int detailOno = Integer.parseInt(request.getParameter("detailOno"));
String detailOnoText = request.getParameter("detailOnoText");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery등록 링크 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>리뷰 등록</title>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
		<div class="container">
		  <h2>리뷰 작성</h2>
		  <form action="write.re" method="post">
		    <div class="form-group">
		      <input type="hidden" name="productNo" value=<%=productNo %>>
		      <input type="hidden" name="detailOno" value=<%=detailOno %>>
		      <input type="hidden" name="detailOnoText" value=<%=detailOnoText %>>
		      <textarea class="form-control" rows="5" id="comment" name="productBoardContent"></textarea>
		    </div>
		    <button type="submit" class="btn btn-primary">등록하기</button>
		    <button type="button" class="btn btn-info" onclick="history.back();">뒤로가기</button>
		  </form>
		</div>
</body>
</html>
