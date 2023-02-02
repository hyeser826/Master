<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.util.ArrayList, com.semi.notice.model.vo.Notice"%>
<!-- ArrayList, Notice 둘다 import 필요 -->
<% 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <style>
        .outer{
            background:white;
            color : black;
            width : 100%;
            margin : auto; /*가운데 정렬*/
        }

        .header{/*자주묻는질문*/
            color : black;
            width : 1000px;
            height: 150px;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            margin-top: 70px;
            border-bottom: 2px solid lightgrey;
        }

        #left-side{
            float : left;
            color : black;
            width : 28%;
            height: auto;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            padding-left: 20px;
            padding-top: 30px;
        }
        #left-side a{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
        }
        #mem-center{
            margin : 0 auto; /*가운데 정렬*/
            width: 200px;
            height: 60px;
            font-size: 20px;
            padding: 0px;
        	align:"center"
        }

        #mem-center>li{
            margin : 0 auto; /*가운데 정렬*/
            width: 100%;
            height: 100%;
            font-size: 20px;
            cursor: pointer;
            padding: 15px;
            list-style : none;
        }

        #mem-center>li:hover{
            border-bottom: 3px solid rgba(255, 221, 0, 0.343);
        }
        
        #mem-center>li>a{
            text-decoration : none;
        	color : black;
        }
        
        #right-side{
            float : left;
            color : black;
            width : 850px;
            height: auto;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            padding: 30px;
            padding-left: 0px;
        }

        .faq-btn{/*검색하는 버튼*/
            background-color: rgba(255, 221, 0, 0.126); 
            border: 3px solid rgba(255, 221, 0, 0.343);
            width: 100px;
            height: 40px;
            font-size: 20px;
            font-weight: bold;
            color: rgba(76, 66, 2, 0.95);
            text-decoration: none;
        }

        #a_btn{/*글작성 버튼*/
            width: 100px;
            height: 40px;
        }

        .faq-btn:active{
            background-color: rgba(255, 221, 0, 0.343);

        }
        .categorySearch{/*상품 결제 개인정보 버튼*/
            background-color: rgba(255, 221, 0, 0.126);
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .categorySearch:hover{/*상품 결제 개인정보 버튼 효과*/
            background-color: rgba(255, 221, 0, 0.343);
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .list-area{/*질문리스트가 있는 table*/
            width: 850px;
            font-size: 25px;
        }
        
        .question{/*질문영역*/
            background-color: white;
            border-bottom: 3px solid rgba(255, 221, 0, 0.343);
            width: 600px;
            height: 50px;
            text-align: left;
            cursor : pointer;
            font-size: 20px;
            padding-left: 30px;
            padding-top: 10px;
        }
        
        .faqSearch{/*상품 결제 개인정보 버튼*/
            background-color: white;
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }
    </style>
    </head>
    
    <body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 40px;">공지사항</p>
            <br>
            <img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">

        </div>
       <!-- 
       <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        --> 
        <div id="left-side" align="center">
            <ol id="mem-center">
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/list.no">고객센터</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li><a href="<%=contextPath %>/list.fq">자주묻는질문</a></li>
                <%if(loginMem != null) {%>
                <li><a href="<%=contextPath %>/list.psn?currentPage=1">1:1문의</a></li>
                <%} %>
                <%if(loginMem == null) {%>
                <li><a href="" onclick="return alert('회원만 이용이 가능합니다.');">1:1문의</a></li>
                <%} %>
            </ol>
        </div>    
        
        <div id="right-side"> 
        <table class="list-area" align="center">
            <thead  align="center">
                <tr align="center" style="height: 100px;">
                    <td>
                    	<input type="text" name="find" id="categorySearch" class="faqSearch" value="" placeholder="검색어를 입력하세요" style="width:400px">
                    	<!-- 해당 검색바에 원하는 검색어 입력시 자동으로 필터링 -->
                        <script>
								$(document).ready(function(){
								  $("#categorySearch").on("keyup", function() {
								    var value = $(this).val();
								    $(".question").filter(function() {
								      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
								    });
								  });
								});
						</script>   
                    <!-- 관리자 작성 가능 조건 -->
                    <% if(loginMem!=null && loginMem.getMemId().equals("admin")) {%>
                    	<button type="submit" class="faq-bt"?n" id="write-faq">
                    		<a href="<%=contextPath %>/enrollForm.no" style="text-decoration : none;">글작성</a>
                    	</button>
                    </td>
                    <%}%>
                </tr>
            </thead>
            <tbody align="center">  
            	<% if(list.isEmpty()){%>
            	<!-- 리스트가 비어있을 경우 -->	            		
            	<tr>
                    <td colspan="3">
                        <p>공지사항이 없습니다.</p>
                    </td><!-- 1:1문의 링크 -->
                </tr>
            	<%} else { %>
	     		<!-- 리스트가 비어있지 않을 경우 -->
	     			<% for(Notice n : list){  %>
                            <tr>
			                    <td>
			                    	<div class="question"><%=n.getNoticeTitle() %>
					            		<input type="hidden" name="nno" id="selectNno" value="<%=n.getNoticeNo() %>">
			                    	</div>
		                    	</td>
			                </tr>
            	    <%} %>
                <%} %>
            </tbody>
       </table>
        </div>
        <br><br>
    </div>
    <script>
    	$(function(){
    		$(".question").click(function(){
    			var nno = $(this).children().eq(0).val();
    			location.href="<%=contextPath %>/detail.no?nno="+nno;
    		})
    	});
    </script>
    
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>