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
	<div align="center" style="width:1900px; position:relative;">
		<div class="outer">
			<div id="head" align="center">가입완료</div>
			
		    <button onclick="goHome();">홈으로 가기</button>
		</div>
    </div>
    <script>
	    function goHome(){
	    	location.replace("<%=contextPath%>");
	    }
    </script>

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>   
</body>
</html>


















