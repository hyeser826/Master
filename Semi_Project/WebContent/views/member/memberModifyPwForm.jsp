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
    
    <!-- 섹션 크기 -->
	.bg-light{
		height: 1053px;
		padding-top:55px;
		padding-bottom:75px;
	}
	.flex-fill.mx-xl-5.mb-2{
		margin: 0 auto;
		width : 700px;
		padding-right: 7rem;
		padding-left: 7rem;
	}
	
    <!-- 입력창 -->
	.container.py-4{
		margin: 0 auto;
		width : 503px;
	}
	
    <!-- 가입하기 -->
	.d-grid.gap-2{
		padding-top: 30px;
	}
	#pw,#myPw,#pwc{
		width: 500px;
	}
</style>
</head>
<body>
<%@ include file = "/views/common/menubar.jsp" %>
	<div align="center" style="width:1900px; position:relative;">
        <div class="outer">
			<div id="head" align="center">비밀번호 수정</div>
			
		<section class="bg-light">
        <div class="container py-4">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center" href="index.do">
                    <span class="text-dark h4"></span> <span class="text-primary h4"></span>                 
                </a>
       		</div>
				<div class="form-group has-success">
					<label class="form-label mt-4" for="inputValid">현재 비밀번호</label>
					<input type="password" class="form-control" name="myPw" id="myPw" placeholder="현재 비밀번호 입력">
					<div class="invalid-feedback" id="checkMyPw"></div>
				</div>
            <form action="<%=contextPath %>/modifyPw.me" method="post">
				<div class="form-group has-success">
					<label class="form-label mt-4" for="inputValid">새 비밀번호</label>
					<input type="password" class="form-control" name="memPw" id="pw" placeholder="8~16자의 영문/숫자/특수문자를 조합하여 입력">
				</div>
				<div class="form-group has-danger">
					<label class="form-label mt-4" for="inputInvalid">비밀번호 재확인</label>
					<input type="password" class="form-control" id="pwc" placeholder="비밀번호 다시 입력">
					<div class="invalid-feedback" id="cp"></div>
				</div>
				<div class="d-grid gap-2">
                    <button class="btn btn-primary btn-lg" type="submit" onclick="return modify();">수정하기</button>
                	<button type="button" class="btn btn-danger" id="btn-cancel" onclick="location.replace('<%=contextPath%>')">취소</button>
                </div>
            </form>
        </div>
    	</section>
		</div>
    </div>
<script>
	function modify(){
	    var pw = document.getElementById("pw").value;
	    var pw_rule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
	    var pwc = document.getElementById("pwc").value;
	    
	    if(pw == ""){
	        alert("비밀번호를 입력해주세요.")
	        return false;
	    } else if(!pw_rule.test(pw)){
	        alert("비밀번호는 영문자, 숫자, 특수문자(!@#$%^&*)를 포함해 8~16자만 가능합니다.")
	        return false;
	    } else if(pwc == ""){
	    	alert("비밀번호 확인을 입력해주세요.")
	        return false;
	    }
	    return true;
	}
	
	$('#myPw').focusout(function(){
		let myPw = $('#myPw').val(); 
		
		$.ajax({
			url : "checkMyPw.me",
			type : "post",
			data : {myPw: myPw},
			dataType : 'json',
			success : function(result){
				if(myPw == ""){
					$("#checkMyPw").html('비밀번호를 입력하세요');
					$("#myPw").attr("class","form-control is-invalid");
				}else if(result == 0){
					$("#checkMyPw").html('비밀번호가 일치하지 않습니다');
					$("#myPw").attr("class","form-control is-invalid");
				}else{
					$("#myPw").attr("class","form-control is-valid");
				} 
			},
			error : function(){
				alert("서버요청실패");
			}
		})
	})

	$("#pw").focusout(function(){
		let myPw = $('#myPw').val();
		let pw = $('#pw').val();
		let pw_rule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
		let pwc = $('#pwc').val();
		
		if(pw == ""){
			$("#cp").html('비밀번호를 입력하세요');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(pw == myPw){
			$("#cp").html('새 비밀번호를 입력하세요');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(!pw_rule.test(pw)){
			$("#cp").html('영문자, 숫자, 특수문자(!@#$%^&*)를 포함해 8~16자만 가능합니다');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(pw != pwc){
			$("#cp").html('비밀번호가 일치하지 않습니다');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else{        		
			$("#cp").html('');
			$("#pw").attr("class","form-control is-valid");
			$("#pwc").attr("class","form-control is-valid");
		}
	})
	
	$("#pwc").focusout(function(){
		let myPw = $('#myPw').val();
		let pw = $('#pw').val();
		let pw_rule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
		let pwc = $('#pwc').val();
		
		if(pw == ""){
			$("#cp").html('비밀번호를 입력하세요');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(pw == myPw){
			$("#cp").html('새 비밀번호를 입력하세요');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(!pw_rule.test(pw)){
			$("#cp").html('영문자, 숫자, 특수문자(!@#$%^&*)를 포함해 8~16자만 가능합니다');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else if(pwc == "" || pw != pwc){
			$("#cp").html('비밀번호가 일치하지 않습니다');
			$("#pw").attr("class","form-control is-invalid");
			$("#pwc").attr("class","form-control is-invalid");
		}else{        		
			$("#cp").html('');
			$("#pw").attr("class","form-control is-valid");
			$("#pwc").attr("class","form-control is-valid");
		}
	})
</script>

<br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>

















