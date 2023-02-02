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
	#id,#name,#phone,#email,#address,#address_detail{
		width: 500px;
	}
</style>
</head>
<body>
<%@ include file = "/views/common/menubar.jsp" %>
<%	
		String memId = loginMem.getMemId();
		String memName = loginMem.getMemName();
		String memPhone;
		String memEmail;
		String[] memAddress;
		
		if(loginMem.getMemPhone() == null){
			memPhone = "";
		}else{
			memPhone = loginMem.getMemPhone();
		}
		if(loginMem.getMemEmail() == null){
			memEmail = "";
		}else{
			memEmail = loginMem.getMemEmail();
		}
		if(loginMem.getMemAddress() == null){
			memAddress = null;
		}else{
			memAddress = loginMem.getMemAddress().split(",");
		}
%>
	<div align="center" style="width:1900px; position:relative;">
		<div class="outer">
			<div id="head" align="center">회원정보 수정</div>
			
		<section class="bg-light">
        <div class="container py-4">
            <div class="row align-items-center justify-content-between">
                <a class="navbar-brand h1 text-center" href="index.do">
                    <span class="text-dark h4"></span> <span class="text-primary h4"></span>                 
                </a>
       		</div>
            <form action="<%=contextPath%>/modify.me" method="post">
                <div class="form-group">
               		<label for="exampleInputEmail1" class="form-label mt-4">아이디</label>
                    <input type="text" class="form-control" name="memId" id="id" value="<%=memId%>" placeholder="4~15자의 영문/숫자를 조합하여 입력" readonly>
                	<div class="invalid-feedback" id="cid">아이디를 입력하세요</div>
                </div>
                <div class="form-group">
               		<label for="exampleInputEmail1" class="form-label mt-4">이름</label>
                    <input type="text" class="form-control" name="memName" id="name" value="<%=memName%>" placeholder="이름 입력">
                	<div class="invalid-feedback" id="cn">이름을 입력하세요</div>
                </div>
                <div class="form-group">
               		<label for="exampleInputEmail1" class="form-label mt-4">핸드폰번호</label>
                    <input type="text" class="form-control" name="memPhone" id="phone" value="<%=memPhone%>" placeholder="핸드폰번호 입력">
                	<div class="invalid-feedback" id="cpn">핸드폰번호를 입력하세요</div>
                </div>
                <div class="form-group">
               		<label for="exampleInputEmail1" class="form-label mt-4">이메일</label>
                    <input type="email" class="form-control" name="memEmail" id="email" value="<%=memEmail%>" placeholder="이메일 입력">
                	<div class="invalid-feedback" id="ce">이메일을 입력하세요</div>
                </div>
                <div class="form-group">
               		<label for="exampleInputEmail1" class="form-label mt-4">주소</label>
                    <input type="text" class="form-control" name="memAddress" id="address" value="<%=memAddress[0]%>" placeholder="주소 입력" readonly>
                	<div class="invalid-feedback" id="ca">주소를 입력하세요</div>
                </div>
                <div class="form-group">
                	<label for="exampleInputEmail1" class="form-label mt-4">상세 주소</label>
                    <input type="text" class="form-control" name="memAddressDetail" id="address_detail" value="<%=memAddress[1]%>" placeholder="상세 주소 입력">
                </div>
				<div class="d-grid gap-2">
                    <button class="btn btn-primary btn-lg" type="submit" onclick="return modifyMember();">수정하기</button>
                	<button type="button" class="btn btn-danger" id="btn-cancel" onclick="location.replace('<%=contextPath%>')">취소</button>
                </div>
            </form>
        </div>
    	</section>
			
		</div>
	</div>
	
	<script> 
        function modifyMember(){
            var name = document.getElementById("name").value;
            var name_rule = /^[가-힣]{2,}$/;
            var email = document.getElementById("email").value;
            var email_rule =  /^[0-9a-zA-Z]{1,}@[0-9a-zA-Z]{1,}.[a-zA-Z]{2,3}$/;
            
            if(name == ""){
                alert("이름을 입력해주세요.")
                return false;
            } else if(!name_rule.test(name)){
                alert("이름은 2글자 이상 한글만 가능합니다.")
                return false;
            } else if(email == ""){
                alert("이메일을 입력해주세요.")
                return false;
            } else if(!email_rule.test(email)){
                alert("이메일 형식에 맞춰서 입력하세요.")
                return false;
            }
        }
        
        $("#name").focusout(function(){
        	let name = $("#name").val();
            let name_rule = /^[가-힣]{2,}$/;
            
            if(name == ""){
        		$("#cn").html('이름을 입력하세요');
        		$("#name").attr("class","form-control is-invalid");
        	}else if(!name_rule.test(name)){
        		$("#cn").html('2글자 이상 한글만 가능합니다');
        		$("#name").attr("class","form-control is-invalid");
        	}else{   		
        		$("#cn").html('');
        		$("#name").attr("class","form-control is-valid");
        	}
        })
        
        $("#phone").focusout(function(){
        	let phone = $("#phone").val();
            let phone_rule = /^[0-9]{1,}$/;
            
            if(phone == ""){
        		$("#cpn").html('핸드폰번호를 입력하세요');
        		$("#phone").attr("class","form-control is-invalid");
        	}else if(!phone_rule.test(phone)){
        		$("#cpn").html('숫자만 가능합니다');
        		$("#phone").attr("class","form-control is-invalid");
        	}else{   		
        		$("#cpn").html('');
        		$("#phone").attr("class","form-control is-valid");
        	}
        })
        
        $("#email").focusout(function(){
        	let email = $("#email").val();
            let email_rule =  /^[0-9a-zA-Z]{1,}@[0-9a-zA-Z]{1,}.[a-zA-Z]{2,3}$/;
            
            if(email == ""){
        		$("#ce").html('이메일을 입력하세요');
        		$("#email").attr("class","form-control is-invalid");
        	}else if(!email_rule.test(email)){
        		$("#ce").html('이메일 형식에 맞춰서 입력하세요');
        		$("#email").attr("class","form-control is-invalid");
        	}else{   		
        		$("#ce").html('');
        		$("#email").attr("class","form-control is-valid");
        	}
        })
        
        $("#address").focus(function(){
        	let address = $("#address").val();
            
            if(address == ""){
        		$("#ca").html('주소를 입력하세요');
        		$("#address").attr("class","form-control is-invalid");
        	}else{   		
        		$("#ca").html('');
        		$("#address").attr("class","form-control is-valid");
        	}
        })
        
        $("#address").focusout(function(){
        	let address = $("#address").val();
            
            if(address == ""){
        		$("#ca").html('주소를 입력하세요');
        		$("#address").attr("class","form-control is-invalid");
        	}else{   		
        		$("#ca").html('');
        		$("#address").attr("class","form-control is-valid");
        	}
        })
    </script>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		window.onload = function(){
		    document.getElementById("address").addEventListener("click", function(){ //주소검색을 클릭하면
		        //카카오 지도 발생
		        new daum.Postcode({
		           	oncomplete: function(data) { //선택시 입력값 세팅
		                document.getElementById("address").value = data.address; // 주소 넣기                
		            }
		        }).open();
		    });
		}
	</script>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>



















