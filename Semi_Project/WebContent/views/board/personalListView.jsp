<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.semi.board.model.vo.Board
    												, com.semi.common.model.vo.PageInfo"%>
<!-- ArrayList, Notice 둘다 import 필요 -->
<% 
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>1:1 문의</title>
    <%@ include file="../common/menubar.jsp" %>
    <style>
        .outer{
            background:white;
            color : black;
            width : 100%;
            margin : auto; /*가운데 정렬*/
            margin-top : 50px; /*위로부터 50px만큼 여백*/
        }

        .header{/*자주묻는질문*/
            color : black;
            width : 1000px;
            height: 150px;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            margin-bottom: 30px;
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


        #a_btn{/*글작성 버튼*/
            width: 100px;
            height: 40px;
        }

        .faq-btn:active{
            background-color: rgba(255, 221, 0, 0.343);

        }
        
        .psnSearch{/*1:1 문의 검색*/
            background-color: white;
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }

        .psnSearch:hover{/*1:1 문의 검색*/
            background-color: white;
            width: 230px;
            height: 50px;
            text-align: center;
            border: 3px solid rgba(255, 221, 0, 0.343);
            cursor : pointer;
            font-size: 20px;
            font-weight: bold;
        }


        .personal-area{/*질문리스트가 있는 table*/
            width: 800px;
            font-size: 25px;
        }
        
        .pesonal-list{/*질문영역*/
            background-color: white;
            border-bottom: 3px solid rgba(255, 221, 0, 0.343);
            width: 600px;
            height: 50px;
            text-align: left;
            cursor : pointer;
            font-size: 15px;
            padding-left: 30px;
            padding-top: 10px;

        }
        .paging-area{
        	float : left;
        	width: 100%;
        }
	
    </style>
    </head>
    <body>
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 35px; font-weight:bold;">FAQ</p>
            <br><br>
            <!-- <img src="resources/img/icon3.jpg" alt="" style="width: 27px; height:20px"> -->

        </div>
        <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        
        <div id="left-side" align="center">
            <ol id="mem-center" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/list.no">고객센터</a></li>
                <li><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li><a href="<%=contextPath %>/list.fq">자주묻는질문</a></li>
                <li><a href="<%=contextPath %>/list.psn?currentPage=1">1:1문의</a></li>
            </ol>
        </div>       
        
		<% if(loginMem.getMemId() != null){ %>
        <div id="right-side" align="center">
	        <table class="personal-area" align="center">
	            <thead  align="center">
	             	<tr style="height: 100px;">
	                    <td colspan="5">
	                    	<div>
		                    <input type="text" name="find" id="psnSearch" class="psnSearch" value="" placeholder="검색어를 입력하세요" style="width:400px">
		                    	<!-- 해당 검색바에 원하는 검색어 입력시 자동으로 필터링 -->
		                           <script>
										$(document).ready(function(){
										  $("#psnSearch").on("keyup", function() {
										    var value = $(this).val();
										    $(".pesonal-list").filter(function() {
										      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
										    });
										  });
										});
									</script>                    
		                    	
		                    	
		                    	<button type="submit" class="faq-btn" id="write-faq">
		                    		<a href="<%=contextPath %>/enrollForm.psn" style="text-decoration : none;">글작성</a>
		                    	</button>
		                    	
	                    	</div>
	                   	</td>
	                </tr>
	            </thead>
	            <tbody align="center" class="pesonal-list">  
		     		<div class="psn-question" style=" font-size: 10px;" id="answer-info">
	            	<% if(!list.isEmpty()&&loginMem.getMemId()!=null){%>
	            	<!-- 리스트가 비어있지 않을 경우 직접작성한 회원은 작성한 글, 관리자는 전체조회 가능-->
		     			<% for(Board b : list){ %>
		     				<% if(loginMem.getMemId().equals(b.getMemId())||loginMem.getMemId().equals("admin")){ %>
								<tr>
									<td align="center" style="width:50px; height:40px;"><%=b.getBoardNo() %></td>
				                    	<%if(b.getBoardCategory().equals("PERSONAL")){ %>
				                 		<td>일반문의</td>
				                 		<%}else if(b.getBoardCategory().equals("LEVELUP")){ %>
				                 		<td>입점문의</td>
				                 		<%} %>
				                    <td>
				                    	<div class="psn-div"><%=b.getBoardTitle() %>
				                    		<input type="hidden" id="psn-bno" value="<%=b.getBoardNo() %>">					                    	
				                    	</div>				                    	
				                    </td>
				                    <td><%=b.getMemId() %></td>
				                    <td><%=b.getBoardDate() %></td>
				                </tr>
	     						<%if(b.getAnswerDate()!=null&&b.getBoardAnswer()!=null){%>
			     				<tr>
			     					<td></td>
			     					<td>질문답변</div></td>
				                    <td>
				                    	<div class="psn-div"> >re : <%=b.getBoardTitle() %>에대해 답변 작성되었습니다.
											<input type="hidden" id="psn-bno" value="<%=b.getBoardNo() %>">					                    	
				                    	</div>				                    	
				                    </td>
				                    <td>관리자</td>
				                    <td><%=b.getAnswerDate() %></td>
			     				</tr>
			     				<%} %>
						   <%} %>
				       <%} %>
         			<%} else { %>
		            	<!-- 리스트가 비어있을 경우 -->	            		
		            	<tr>
		                    <td colspan="5">
		                        <p>1:1문의내역이  없습니다.</p>
		                    </td>
		                </tr>
				  	<%} %>
            	</tbody>
       		</table>
        </div>
		


        <div align="center" class="paging-area">
	       	<%if(pi.getCurrentPage()!=1){ %>
	       	<button onclick="location.href='<%=contextPath %>/list.psn?currentPage=<%=pi.getCurrentPage()-1 %>'">&lt;</button>
	       	<%} else { %>
	       	<button disabled>&lt;</button>
	       	<%} %>
		       	<%for(int i=pi.getStartPage();i<=pi.getEndPage(); i++){ %>
		       		<%if(i==pi.getCurrentPage()){ %>
		       		<button disabled style="background : rgba(255, 221, 0, 0.343);"><%=i %></button>
		       		<%} else { %>
		       		<button onclick="location.href='<%=contextPath %>/list.psn?currentPage=<%=i %>'"><%=i %></button>
		       		<%} %>
		       	<%} %>
	        <%if(pi.getMaxPage()!=pi.getCurrentPage()){ %>
	        <button onclick="location.href='<%=contextPath %>/list.psn?currentPage=<%=pi.getCurrentPage()+1 %>'">&gt;</button>
	       	<%} else { %>
	       	<button disabled>&gt;</button>
	       	<%} %>  	
       </div>
	   <br>   
        <br><br>
  	</div>
    <%} else { %>
        <div id="right-side" align="center">
			<h3>1:1 문의내역은 회원만 확인할 수 있습니다.</h3>
		</div>
    <%}%>
	<br><br>
	<script>
    	$(function(){
    		$(".psn-div").click(function(){	
    			var bno = $(this).children().eq(0).val();				
    				
    			location.href="<%=contextPath %>/detail.psn?bno="+bno;
    		})
    	});
    </script>
    </body>
    </html>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    