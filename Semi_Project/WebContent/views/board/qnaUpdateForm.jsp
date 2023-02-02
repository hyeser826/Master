<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.board.model.vo.Board, java.util.ArrayList"%>
<% 
	Board b = (Board)request.getAttribute("b");
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
            margin-top:70px;
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

     	#psn-detail-tbl{
     		padding: 20px;
     		width : 100%;
            height: auto;
            border : 1px solid black;
     	
     	}
     	
     	#psn-detail-tbl td{
     		padding: 20px;
            border : 1px solid black;
     	
     	}
     	
     	#psn-detail-tbl>tbody th{
			background-color: rgba(255, 221, 0, 0.343);
     	}
     	
     	#psn-detail-tbl>tbody td{
			padding:2%;
			height:30px;
     	}
        
        #psn-detail-tbl input{
        	width : 100%;
        	padding:10%;
        }
        
        .footer{/*목록가기 수정 삭제버튼*/
            float: left;
            background-color: white;
            width: 100%;
            height: 50px;
            font-size: 15px;
            padding : 30px;
        }

        
        a{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
        }
        
        pre{
	        width : 100%;
	        font-size: 20px;
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
    </style>
    </head>
    <body>
        <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 35px; font-weight:bold;">1:1문의</p>
            <br><br>
            <img src="resources/img/icon3.jpg" alt="" style="width: 27px; height:20px">

        </div>
        <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        <div id="left-side" align="center">
            <ol id="mem-center">
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/list.no">고객센터</a></li>
                <li><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li><a href="<%=contextPath %>/list.fq">자주묻는질문</a></li>
                <%if(loginMem != null) {%>
                <li><a href="<%=contextPath %>/list.psn?currentPage=1">1:1문의</a></li>
                <%} %>
                <%if(loginMem == null) {%>
                <li><a href="" onclick="return alert('회원만 이용이 가능합니다.');">1:1문의</a></li>
                <%} %>
            </ol>
        </div>
        <form action="<%=contextPath %>/update.qna" method="post" id="update-form">
        <input type="hidden" value="<%=b.getBoardNo() %>" name="boardNo">   
        <input type="hidden" value="<%=b.getMemId() %>" name="memId">  
        <div id="right-side">
        <%if(loginMem.getMemId().equals(b.getMemId())||loginMem.getMemId().equals("admin")){ %> 
        	<table id="psn-detail-tbl">
        		<thead align="center">
	        		<tr>
	        			<td style="width:15%; height:50px;">
		        			<% if(loginMem.getMemId().equals("admin")){ %>
								질문답변
		        			<%}else if(b.getBoardCategory().equals("LEVELUP")){ %>
								입점문의
							<%}else if(b.getBoardCategory().equals("PERSONAL")){ %> 
								일반문의
							<%} %>
						</td>
	        			<td colspan="3" style="width:65%"><%=b.getBoardTitle() %></td>
	        			<td style="width:20%">질문작성일  <%=b.getBoardDate() %></td>
	        		</tr>
	        		<tr>
	        			<td colspan="5"  align="left">
	        				<textarea name="content" rows="10" style="width : 100%; height:auto; resize:none;" required><%=b.getBoardContent() %></textarea>
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="3"></td>
	        			<td colspan="2" style="height:50px;">질문자 질문내용</td>
	        		</tr>
	        	</thead>
	        	<tbody  align="center">
	        	<% if(b.getBoardCategory().equals("LEVELUP")){ %>
	        		<tr>
	        			<th colspan="5" style="height:10px;"></th>
	        		</tr>
	        		<tr>
	        			<td colspan="5">입점요청 기재사항</td>
	        		</tr>
	        		<tr>
	        			<td  colspan="2" style="width:25%">장인홈(상점명)</td>
	        			<td colspan="3">
	        				<input class="strName" type="text" name="coNum" value="<%=b.getStrName() %>">
        				</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" style="width:25%">장인소개</td>
	        			<td colspan="3">
	        				<input class="mstInfo" type="text" name="coName" value="<%=b.getMasterIntro() %>">
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" style="width:25%">사업자등록번호</td>
	        			<td colspan="3">
	        				<input class="psn-inputs" type="text" name="coNum" value="<%=b.getCoNumber() %>">
        				</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" style="width:25%">법인명<br>(개인사업자명/단체명)</td>
	        			<td colspan="3">
	        				<input class="psn-inputs" type="text" name="coName" value="<%=b.getCoName() %>">
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" style="width:25%">대표자명</td>
	        			<td colspan="3">
	        				<input class="psn-inputs" type="text" name="ceo"  value="<%=b.getCeo() %>">
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" style="width:25%">업태명 및 종목명</td>
	        			<td colspan="3">
	        				<input class="psn-inputs" type="text" name="coKind" value="<%=b.getCoKind() %>">
	        			</td>
	        		</tr>
	        	<%}%>
	        	</tbody>
        	</table>
        </div>    

       <div align="right" class="footer">
           <input type="submit" value="수정하기" class="a_btn" id="update-btn">
           <input type="button" onclick="history.back()" value="뒤로가기" class="a_btn" id="back-btn">
       </div>

        </form>
      <%}else{ %>
        <div align="center">
        <h3>1:1문의사항은 글을 작성한 회원만 확인할 수 있습니다.</h3>
        <a href="<%=contextPath %>/list.psn?currentPage=1" >나의 1:1문의 목록가기</a>
      <%} %>
      </div>
       <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
       <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>