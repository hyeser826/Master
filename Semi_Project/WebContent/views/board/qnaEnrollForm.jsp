<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member"%>
    
<%	
Member loginMem = (Member)session.getAttribute("loginMem");	

String contextPath = request.getContextPath();
int pno = 1;
String strName = "김순덕장인홈";
String categoryName = "4.악세사리";

//String contextPath = request.getAttribute("contextPath");
//int pno = (Integer)request.getAttribute("pno");
//String strName = request.getAttribute("strName");
//String categoryName = request.getAttribute("categoryName");

%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>1:1문의 작성</title>
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
        <form action="<%=contextPath %>/insert.qna" method="post" id="enroll-form">
        <input type="hidden" name ="memId" value="<%=loginMem.getMemId() %>">
        <input type="hidden" name ="pno" value="<%=pno %>">
        <input type="hidden" name ="categoryName" value="<%=categoryName %>">
        <input type="hidden" name ="strName" value="<%=strName %>"> 
			<div id="co-tr" class="right-side">	
				<table align="center" id="qna-enroll-form">
					<thead>
						<tr>
							<td colspan="2" style="height:50px; font-size : 20px; font-weight : bold;"><%=strName %>에 문의하기</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
						<tr>
							<td colspan="2">
								<input type="text" name="title" placeholder="제목을 입력하세요" required>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea name="content" rows="10" style="width : 100%; height: 100%; resize:none;" placeholder="내용을 입력하세요" required></textarea>
							</td>
						</tr>
					<tfoot>
						<tr>
							<td colspan="2">문의한 내용은 1:1문의하기내 상품문의에서 확인 가능합니다.</td>
						</tr>
						<tr>
							<td>
								<input type="submit" value="등록하기" class="a_btn" id="enroll-btn">
							</td>
							<td>
								<input type="reset" value="초기화" class="a_btn" id="reset-btn">
							</td>
						</tr>
					</tfoot>
				</table>
			</div>            
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
       <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>