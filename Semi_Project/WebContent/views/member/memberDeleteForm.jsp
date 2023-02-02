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
    .inputs{
        border: 1px solid lightgray;
        border-radius: 3px;
        width: 300px;
        height: 40px;
    }
</style>
</head>
<body>
	<%@ include file = "/views/common/menubar.jsp" %>
	<%if(loginMem != null) {%>
	<div align="center" style="width:1900px; position:relative;">
        	<div id="head" align="center">회원탈퇴</div>
        	
        	<form action="<%=contextPath %>/delete.me" method="post" id="login-form">         
                    <table align="center" id="login1">
                        <tr align="center">
                            <td style="padding:50px"></td>
                        </tr>
                        <tr align="center">
                            <td>탈퇴를 원하시면 비밀번호를 입력해 주세요.</td>
                        </tr>
                        <tr align="center">
                            <td style="padding:10px"></td>
                        </tr>
                        <tr align="center">
                            <td><input type="password" name="memPw" class="inputs" id="memPw" placeholder="비밀번호 입력" required></td>
                        </tr>
                        <tr align="center">
                            <td style="padding:50px">
                                <button type="submit" class="btn btn-primary" id="btn-findId">탈퇴하기</button>
                            	<button type="button" class="btn btn-danger" id="btn-cancel" onclick="location.replace('<%=contextPath%>')">취소</button>
                            </td>
                        </tr>
                   </table>
            </form>
	</div>
	<%} %>
	<%if(loginMem == null) {%>
		<script type="text/javascript">
			location.replace("/smp");
		</script>
	<%} %>
	
</body>
</html>
















