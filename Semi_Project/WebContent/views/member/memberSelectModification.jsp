<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String strReferer = request.getHeader("referer"); //이전 URL 가져오기
 
 if(strReferer == null){
%>
 <script language="javascript">
  alert("URL을 직접 입력해서 접근하셨습니다.\n정상적인 경로를 통해 다시 접근해 주세요.\n홈으로 갑니다.");
  document.location.href="경로";
  location.replace('/smp')
 </script>
<%
  return;
 }
%>
<%@ include file = "/views/common/menubar.jsp" %>
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
<body>
	<div align="center" style="width:1900px; position:relative;">
        <div id="head" align="center">회원정보 수정</div>

        <div>
        <br>
        	<button type="button" class="btn btn-primary"  onclick="goInfoModification();">회원정보 수정</button>
        	<button type="button" class="btn btn-primary"  onclick="goPwChange();">비밀번호 변경</button>
        	<button type="button" class="btn btn-primary" id="btn-goHome" onclick="gologin();">홈으로 가기</button>
        </div>
	</div>
	
	<script>
		function goInfoModification(){
			location.replace('<%=contextPath%>/modifyForm.me');
		}
		function goPwChange(){
			location.replace('<%=contextPath%>/modifyPwForm.me');
		}
		function gologin(){
			location.replace('<%=contextPath%>');
		}
	</script>

</body>
</html>


















