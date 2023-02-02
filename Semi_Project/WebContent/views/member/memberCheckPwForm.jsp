<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    .inputs{
        border: 1px solid lightgray;
        border-radius: 3px;
        width: 300px;
        height: 40px;
    }
</style>
</head>
<body>
	<div align="center" style="width:1900px; position:relative;">
        	<div id="head" align="center">비밀번호 확인</div>
        	
        	<form action="<%=contextPath %>/pwcheck.me" method="post" id="login-form">         
                    <table align="center" id="login1">
                        <tr align="center">
                            <td style="padding:50px"></td>
                        </tr>
                        <tr align="center">
                            <td>비밀번호 입력</td>
                        </tr>
                        <tr align="center">
                            <td style="padding:10px"></td>
                        </tr>
                        <tr align="center">
                            <td><input type="password" name="memPw" class="inputs" id="memPw" placeholder="비밀번호 입력" required></td>
                        </tr>
                        <tr align="center">
                            <td style="padding:50px">
                                <button type="submit" class="btn btn-primary" id="btn-findId">확인</button>
                            	<button type="button" class="btn btn-danger" id="btn-cancel" onclick="location.replace('<%=contextPath%>')">취소</button>
                            </td>
                        </tr>
                   </table>
            </form>

	</div>
</body>
</html>
















