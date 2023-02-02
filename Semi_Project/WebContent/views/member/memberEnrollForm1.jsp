<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div#head{
        font-size: 40px;
        width: 1600px;
        padding: 50px;
        margin-bottom: 50px;
        border-bottom: 2px solid lightgray;
    }
    .title{
    	display: table-cell;
    }
</style>
</head>
<body>
	<%@include file = "../common/menubar.jsp" %>
	<div align="center" style="width:1900px; position:relative;">
		<div class="outer">
			<div id="head" align="center">회원가입</div>
			
			<div>
				<div class="title">01/서비스 약관 동의</div>
				<div class="title">02/회원가입 정보 입력</div>
				<div class="title">03/가입완료</div>
			</div>
			
			<div>
				<button onclick="enrollPage();" id="enroll-btn">회원가입</button>
			</div>
			
		</div>
		
	</div>
	<script>
        function enrollPage(){
        	location.href = "<%=contextPath%>/enrollForm2.me";
        }
    </script>
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>