<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.MemberId"%>
<%
	MemberId mid = (MemberId)request.getAttribute("mid");
%>

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
    
</style>
</head>
<%@ include file = "/views/common/menubar.jsp" %>
<script type="text/javascript">
	history.pushState(null, null, location.href); 
	window.onpopstate = function(event) { 
		history.go(1); 
	};
	function NotReload(){
	    if( (event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82)) || (event.keyCode == 116) ) {
	        event.keyCode = 0;
	        event.cancelBubble = true;
	        event.returnValue = false;
	    } 
	}
	document.onkeydown = NotReload;
</script>
<body oncontextmenu="return false" onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
    <%if(mid.getMemId() != null){ %>
	<div align="center" style="width:1900px; position:relative;">
        	<div id="head" align="center">아이디 찾기</div>
        		<div>
        			<h2><%=mid.getMemId() %></h2>
        		</div>
        	<div>
        	<br>
        		<button type="button" class="btn btn-info" id="btn-findPw" onclick="location.replace('<%=contextPath%>/findPw.me')">비밀번호 찾기</button>
        		<button type="button" class="btn btn-primary" id="btn-goHome" onclick="location.replace('<%=contextPath%>')">홈으로 가기</button>
        	</div>
    <%} %>
    <%if(mid.getMemId() == null) {%>
    	<script type="text/javascript">
			location.replace(<%=contextPath%>);
		</script>
    <%} %>
	</div>
</body>
</html>














