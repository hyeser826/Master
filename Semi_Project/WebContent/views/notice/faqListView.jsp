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
    <title>자주 묻는 질문</title>
    
    
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

        #ctgrSearchbar{/*검색하는 창*/
            border: 3px solid rgba(255, 221, 0, 0.343);
            width: 350px;
            height: 40px;
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
        .faqSearch{/*상품 결제 개인정보 버튼*/
            background-color: rgba(255, 221, 0, 0.126);
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .faqSearch:hover{/*상품 결제 개인정보 버튼 효과*/
            background-color: rgba(255, 221, 0, 0.343);
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }
        
        #categorySearch{
        	background-color: white;
        }

        .list-group{/*질문리스트가 있는 table*/
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

        .answer{/*답변영역*/
            border: 0px solid white;
            width: 600px;
            height: auto;
            text-align: left;
            display: none;
            margin-top: 0px;
            margin-bottom: 0px;
            font-size: 15px;
            background-color: rgba(255, 221, 0, 0.126);
            padding-left: 30px;
            padding-top: 10px;
            padding-bottom: 10px;
        }

		.mini-btn{
			border : 0px;
			background-color: rgba(255, 221, 0, 0.126);
			font-size: 10px;
		}
		.close-btn{
			border : 0px;
			background-color: rgba(255, 221, 0, 0.126);
			font-size: 10px;
		}
    </style>
    </head>
    <body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 40px;">FAQ</p>
            <br>
            <img src="resources/img/icon/icon3.jpg" alt="" style="width: 27px; height:20px">

        </div>
        
        <div id="left-side" align="center">
            <ol id="mem-center" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/list.no">고객센터</a></li>
                <li><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath %>/list.fq">자주묻는질문</a></li>
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
                        <button type="submit" class="faqSearch" value="상품">상품관련</button>
                        <button type="submit" class="faqSearch" value="결제">결제관련</button>
                        <button type="submit" class="faqSearch" value="회원">회원관련</button>
                            <script>
								$(document).ready(function(){
								  $(".faqSearch").click(function() {
								    var value = $(this).val();
								    $(".question").filter(function() {
								    $(".answer").hide();
								      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
								      
								    });
								  });
								});
							</script>
                    </td>
                </tr>
                <tr>
                    <td><input type="text" name="find" id="categorySearch" class="faqSearch" value="" placeholder="검색어를 입력하세요" style="width:400px">
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
                    	<%if(loginMem!=null && loginMem.getMemId().equals("admin")){ %>
                    	<button type="submit" class="faq-btn" id="write-faq">
                    		<a href="<%=contextPath %>/enrollForm.fq" style="text-decoration : none;">글작성</a>
                    	</button>
                    </td>
                    <%}%>
                </tr>
            </thead>
            <tbody align="center">
                <tr align="center">
                    <td colspan="3" style="height: 100px;">
                    </td>
                </tr>

            	<% if(list.isEmpty()){%>
            	<!-- 리스트가 비어있을 경우 -->	            		
            	<tr>
                    <td colspan="3">
                        <p>1:1문의를 통해 문의하세요</p>
                    </td><!-- 1:1문의 링크 -->
                </tr>
            		
            	<%} else { %>
            	<!-- 리스트가 비어있지 않을 경우 -->
	     			<% for(Notice n : list){  %>
	            		<% if(n.getNoticeContent().contains(" ")){%><!-- 상품관련문의 -->
                            <tr>
			            		<input type="hidden" name="nno" value="<%=n.getNoticeNo() %>">
			                    <td>
									    <div class="question" style="list-style:none;">
									    	<%=n.getNoticeTitle() %>
									    </div>
									    <div class="answer">
									    	<%=n.getNoticeContent() %><br><br><br>
												<%if(loginMem!=null && loginMem.getMemId().equals("admin")){ %>
		                    					<a href="<%=contextPath %>/deleteForm.no?nno=<%=n.getNoticeNo() %>" onclick="return del();"class="a_btn" id="del-btn">
			                    					<button class="mini-btn">삭제</button>
			                    				</a>
		                    					<a href="<%=contextPath %>/updateForm.fq?nno=<%=n.getNoticeNo() %>" class="a_btn" id="rec-btn">
				                    				<button class="mini-btn">수정</button>
		                    					</a>
			                    				<%}%>
									    </div>
									<script>
										$(function(){
											$(".question").click(function() {
												$(this).next().show();
										  });
										});
									</script>  
									     <!-- 질문클릭시 하위 내용 보임, div내 닫기버튼 클릭시 내용 숨기기  -->
                    			</td>
			                </tr>
	            		<%} %>	
	            	<%} %>
	            <%} %>
            </tbody>
            <tfoot>
                <tr>
                    <td>
                        <div style="padding: 50px; font-size: small;" align="center">
                        <%if(loginMem != null) {%>
	                   		<a href="<%=contextPath %>/list.psn?currentPage=1" style="text-decoration: none;">원하는 답변이 없으신가요? <b>1:1문의하기</b></a>
		        		<%} %>
		        		<%if(loginMem == null) {%>
	                   		<a href="" style="text-decoration: none;" onclick="return alert('회원만 이용이 가능합니다.');">원하는 답변이 없으신가요? <b>1:1문의하기</b></a>
		        		<%} %>
                        </div>
                    </td>
                </tr>
            </tfoot>
       </table>
        </div>
        <br><br>
    </div>

    <script>
            function del(){
                  var result = confirm("정말로 삭제하시겠습니까?");                   
                  return result;
        	}

    </script>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>