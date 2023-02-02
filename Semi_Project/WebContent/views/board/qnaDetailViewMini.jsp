<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.board.model.vo.Board, java.util.ArrayList
    ,com.semi.board.controller.QnaDetailCtlr, com.semi.member.model.vo.Member"%>
<% 
	Board b = (Board)request.getAttribute("b");
%>
<%@include file ="/views/common/menubar.jsp" %>
<%	
int pno = (Integer)request.getAttribute("pno");
String strName = (String)request.getAttribute("strName");
String categoryName = (String)request.getAttribute("categoryName");
ArrayList<Integer> pnoList = (ArrayList<Integer>)request.getAttribute("pnoList");

%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <style>        
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

        #qna-enroll-form input, #qna-enroll-form textarea{
        	width : 100%;/*가로길이를 부모요소의 100%*/
            height: 100%;
        	box-sizing : border-box;
        }
        
        .footer{/*목록가기 수정 삭제버튼*/
            float: left;
            background-color: white;
            width: 1600px;
            height: 50px;
            font-size: 15px;
            padding-right: 150px;
        }
        
        .a_btn{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
            border: 0px;
            background-color: rgba(255, 221, 0, 0.343);
            width: 150px;
        }
        
        #psn-insert-table{
       		width: 100%;
       		border : 1px solid lightgray;
        }
        
        .psn-tables{
        	background-color: white;
        	width: 100%;
        	border : 1px solid lightgray;
        }
        
        .psn-tables th, .psn-tables td{
        	background-color: lightgray;
        	font-size : 17px;
        	font-weight : lighter;
        	border-bottom : 1px solid gray;
        	height : 80px;
        }
        
        .psn-tables td{
        	background-color: white;
        	padding : 0px;
        }
        
        .psn-inputs{
        	margin : 0px;
        	background-color: white;
        	border : 1px solid lightgray;
        	height : 80px;
        }
        
    </style>
    </head>
    <body>
    <div id="co-tr" class="right-side">	
    <%if(loginMem.getMemId().equals(b.getMemId())||loginMem.getGrade().equals("장인")){ %> 
		<table align="center" id="qna-enroll-form">
			<tbody>
				<tr>
        			<td style="width:15%; height:50px;">
	        			상품문의
					</td>
        			<td colspan="3" style="width:55%"><%=b.getBoardTitle() %></td>
        			<td colspan="2"  style="width:30%, height:50px;">질문작성일  <%=b.getBoardDate() %></td>
        		</tr>
        		<tr>
        			<td colspan="6"  align="left">
        				<pre style="height:100%; width:100%; padding:10px;"><%=b.getBoardContent() %></pre>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="4"></td>
        			<td colspan="2" style="width:30%; height:50px;">질문자 질문내용</td>
        		</tr>
        		<% if(b.getStatus().equals("W")){ %>
        		<tr>
        			<td colspan="6" align="left"> 
        				<pre style="height:100%; width:100%; padding:10px;">관리자가 질문을 확인중에 있습니다. 감사합니다.</pre>
        			</td>
        		</tr>
        		<tr align="center">
        			<td colspan="4"></td>
        			<td colspan="2" style="width:30%; height:50px;">관리자 질문 확인 중</td>
        		</tr>
				<%} else { %>
        		<tr>
        			<td colspan="6" align="left"> 
        				<pre style="height:100%; width:100%; padding:10px;"><%=b.getBoardAnswer() %></pre>
        			</td>
        		</tr>
        		<tr>
        			<td colspan="3"></td>
        			<td colspan="2" style="height:50px;">관리자답변  <%=b.getAnswerDate() %></td>
        		</tr>
	        	<%}%>
			</tbody>
		</table> 
 		<%}else{ %>
        <div align="center">
        <h3>1:1문의사항은 글을 작성한 회원만 확인할 수 있습니다.</h3>
        </div>
        <%} %>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
       <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>