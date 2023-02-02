<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,com.semi.member.model.vo.Member ,com.semi.board.model.vo.Board"%>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	String memNo = request.getParameter("memNo");


%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">

    <title>리뷰등록하기</title>
    <style>
        div{
            box-sizing: border-box; /* 지금 부터 지정하는 가로세로 크기는 보더로*/
            /* border: 1px solid orangered; */
        }
    
        .outer{
            width: 1200px;
            height : 1500px;
            margin: auto;
        }
        /*크게 영역 3가지  header , content , footer*/
        .outer>div{
            width: 100%;   /* 크기를 넘어갈수 없게끔 딱 100%(전체를 감싸는 아우터의 자식요소 div들의 width 를 꽉차게 설정) */
        
        
        }
    
        #header,#footer{
            height: 20%;
        }
    
        #content{
            height: 60%;
        }
    
    
        #content>div{
            height: 100%;   /*콘첸츠 안에있는 div 자식관계를 또 content를 100%쓸수있게*/
            float : left;   /*플로트 설정으로 블록을 나눠줄수있음 ! */
        }
    
        #header{
            background-color: white;
            /* margin: auto; */
            font-size: 20px;
        }
    
        
        #content1{
            width: 25%;
            background-color: aqua;
        }
        #content2{
            width: 65%;
            background-color: white;
        }
        #content3{
            width: 10%;
            background-color: coral;
        }
        #footer{
            background-color: crimson;
        }
    
        #header>div{
            height: 100%;
            float: left;
        }
        #header1{
            width: 25%;
            background-color: pink;
    
        }
        hr {
      width : 650px;
      height : 2px;
      background-color : black;
    }
    #conct1{
        font-size: 20px;
    }
    #conct2{
        font-size: 20px;
    }
    #conct3{
        font-size: 20px;
    }
    #conct4{
        font-size: 20px;
    }
    #p11{
        font-size: 10px;
        float: right;
    }
    </style>
    
    
    </head>
    <body>
    <%@include file="/views/common/menubar.jsp" %>
    
    <!-- 
             Member loginMem = (Member)session.getAttribute("loginMem");
    
     -->
        <%
            String userId = loginMem.getMemId();
            String userName=loginMem.getMemName();
            String email = loginMem.getMemEmail();
            String phone =(loginMem.getMemPhone()==null)?"":loginMem.getMemPhone();
            String address=loginMem.getMemAddress();
            String grade = loginMem.getGrade();
        %>
       
        <div class ="outer">
            <div id="header">상품리뷰 
                <div id="header1"></div>
            </div>
            
			<div id="left-side">
    			<%@include file="/views/common/leftbar2.jsp" %>
    		</div>
    		
			<div id="right-side">

				<input type="hidden" name="userNo" value=<%=loginMem.getMemId() %>>
				
			<div id="content2">
				<form action="<%=contextPath %>/insert.re" id="mypage-form" method="post">
					<input type="hidden" name="memNo" value="<%=loginMem.getMemNo() %>" id="enroll-form"
						method="post" enctype="multipart/form-data">
				
					<hr id="hr1">
				

					<div id='v-line1' border="1">

						<br>
						<h4 id="h4">내용 수정 </h4>
						<br>
					</div>
					<p id="p3">
							<input type="text" name="pqContent" id="detailReview">
						</p>
					
						<hr id="hr2">
						<br>
						
						<button type="submit" value="cancel" id="cancel">취소</button>
						<button type="submit" value="reviewEnroll">리뷰등록</button>
											<!-- 리뷰등록 누르면 ReviewinsertController  -->
					</form>
				</div>
			<br></br><br></br><br></br><br></br>
	                         
	                    
	                <div id="content3"></div>
        </div>
        <div id="footer"></div>

    </div>
    
    <script>
					function loadImg(inputFile, num) {
						if (inputFile.file.length == 1) {
							var read = new FileReader();
							reader.readAsDataURL(inputFile.files[0]);

							read.onload = function(e) {
								console.log(e);
							}
						}
					}
				</script>
</body>
</html>