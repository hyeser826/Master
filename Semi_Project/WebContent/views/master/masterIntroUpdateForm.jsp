<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.master.model.vo.*"%>
<% Master m = (Master)request.getAttribute("m"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery등록 링크 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<title>장인 소개 수정/등록</title>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
		<div class="container">
		  <h2>장인 소개 작성</h2>
		  <form action="updatems.in" method="post">
		    <div class="form-group">
		      <input type="hidden" name="masterNo" value="<%=m.getMasterNo()%>">
		      <textarea class="form-control" rows="5" id="comment" name="text"><%=m.getMasterIntro()%></textarea>
		    </div>
		    <button type="submit" class="btn btn-primary">수정하기</button>
		    <button type="button" class="btn btn-info" onclick="history.back();">뒤로가기</button>
		  </form>
		</div>
</body>
</html>