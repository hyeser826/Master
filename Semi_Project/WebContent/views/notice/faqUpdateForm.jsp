<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.notice.model.vo.Notice"%>
<% Notice n = (Notice)request.getAttribute("Notice"); %>
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

        #update-form>table{
            width: 750px;
            border: 0px solid rgba(255, 221, 0, 0.343);
            font-size: 20px;
        }
        #update-form input, #update-form textarea{
        	width : 100%;/*가로길이를 부모요소의 100%*/
            height: 100%;
        	box-sizing : border-box;
        }
        
        .footer{/*목록가기 수정 삭제버튼*/
            float: left;
            background-color: white;
            width: 1050px;
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
    </style>
    </head>
    <body>
         <%@ include file="../common/menubar.jsp" %> 
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 35px; font-weight:bold;">FAQ</p>
            <br><br>
            <img src="resources/img/icon3.jpg" alt="" style="width: 27px; height:20px">

        </div>
        <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        <div id="left-side" align="center">
            <ol id="mem-center">
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
        <form action="<%=contextPath %>/update.fq" method="post" id="update-form">
        <input type="hidden" name="category" value="NOTICE">
        <input type="hidden" name="nno" value="<%=n.getNoticeNo() %>">
        
            <table align="center" border="1px">
                <tbody align="left">
                    <tr>
                        <td style="width: 300px; height: 40px;" colspan="2">
                        	<input type="text" name="title" value="<%=n.getNoticeTitle() %>">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="height: 400px;">
                                <textarea name="content" rows="10" style="resize:none;" required><%=n.getNoticeContent() %></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
            
	        <br><br>
	    <div align="right" class="footer">
	        	<input type="submit" href="<%=contextPath %>/update.fq" class="a_btn" value="제출하기">
	           	<input type="button" onclick="history.back()" class="a_btn"  value="뒤로가기">
	    </div>
			<br><br>
        </form>

    </div>


</body>
</html>