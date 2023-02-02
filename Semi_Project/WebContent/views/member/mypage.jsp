<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>화면 구조 잡기 기본 </title>


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
</style>


</head>
<body>
<%@include file="/views/common/menubar.jsp" %>

    <div class ="outer">
        <div id="header">상품리뷰 
            <div id="header1"></div>

        </div>

		<div id="content">
			<div id="content1">
				<form action="" id="mypage-form" method="post"></form>
             		<pre>  <b><div id="conct1"> <<내 정보>></div></b></pre>
                    <pre>
                        <div id="conct1_1">   -내 정보 수정</div>
                        <div id="conct1_2">   -내 1:1 문의내역</div>      
                        <div id="conct1_3"><a href="<%=contextPath%>/list.rv?currentPage=1">   -내가작성한리뷰</a></div>             
                    </pre>


                    <!--&&&&&&&&&&&&&&&&&&&&&&&&&&구분선&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&-->


                   <pre> <b><div id="conct2"> <<주문정보>>  </div>  </b></pre>
                        <pre>
                            <div id="conct2_1">  -주문/예매조회</div>
                            <div id="conct2_2">  -취소/반품/교환 내역</div>                    
                        </pre>
                        

                       <!--&&&&&&&&&&&&&&&&&&&&&&&&&&구분선&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&-->

                    <pre><div id="conct3"> <b><<내 상점 관리>></b>  </div></pre>  
                        
                    <div>
                           <pre><a href="<%=contextPath%>/mypagepr.ms?currentPage=1">  -상품관리</a></div></pre>
              				
                           <div><pre>  -교환/반품관리</div></pre>
				
				           <div><pre>  -문의내역</pre></div> 
				<br>

                   <!--&&&&&&&&&&&&&&&&&&&&&&&&&&구분선&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&-->
				<div id="conct4"><a href="<%=contextPath%>/list.cu?currentPage=1"><b><<쿠폰>></b></a></div>
				<br>
                <div><pre><b>장인으로 등업 신청</b></pre></div>
                    <br> <br>
				<div><pre>회원탈퇴</pre></div>
                <br>
                   <!--&&&&&&&&&&&&&&&&&&&&&&&&&&구분선&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&-->
			
				<br>
                
			</div>
                    <!--&&&&&&&&&&&&&&&&&&&&&&&&&&구분선&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&-->
			<div id="content2">
				<p>상품리뷰</p>
				<br>
				<br>
				<br>
				<hr>


			</div>
			<div id="content3"></div>
		</div>
		<div id="footer"></div>
        

    </div>
</body>
</html>