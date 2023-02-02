<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.notice.model.vo.Notice" 
    import="com.semi.notice.controller.NoticeDetailCtlr"%>
<% Notice n = (Notice)request.getAttribute("notice"); %>
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
        }
        #mem-center{
            margin : 0 auto; /*가운데 정렬*/
            padding-top: 50px;
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
            color : black;
            width : 55%;
            height: auto;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            padding-right: 30px;
            padding-left: 0px;
            padding-bottom: 50px;
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
        #detail-area{/*질문리스트가 있는 table*/
            width: 300px;
            border: 0px solid rgba(255, 221, 0, 0.343);
            font-size: 20px;
        }
        .footer{/*목록가기 수정 삭제버튼*/
            float: left;
            background-color: white;
            width: 1900px;
            height: 50px;
            font-size: 15px;
        }
        .footer a{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
        }
        #ctlr{
            width: 300px;
            cursor: pointer;
        }
        #left-side a{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
        }
        pre{
        	font-size: 20px;
        }
        
    </style>
    </head>
    
    <body>
    <%@ include file="../common/menubar.jsp" %>
   	<div class="outer">
	    <div class="header" align="center">
	        <p style="font-size: 40px;">공지사항</p>
	        <br>
	        <img src="resources/img/icon/icon3.jpg" alt="" style="width: 27px; height:20px">
	
	    </div>
	        
	    <div>
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
            <table id="detail-area" align="left" border="1px">
                <tbody align="left">
                    <tr>
                        <td style="width: 100px; height: 40px; padding:20px"><%=n.getNoticeTitle() %></td>
                        <td style="width: 100px; padding:20px"><%=n.getNoticeDate() %></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <pre style="padding:20px"><%=n.getNoticeContent() %></pre>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
	    </div>
        
        <div align="center" class="footer">
            <table id="ctlr">
                <tr>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(255, 221, 0, 0.343);"> 
                        <a href="<%=contextPath %>/list.no" class="a_btn" id="list-btn">목록가기</a>
                    </td>
                    <% if(loginMem!=null && loginMem.getMemId().equals("admin")) {%>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(111, 174, 233, 0.343);">
                        <a href="<%=contextPath %>/updateForm.no?nno=<%=n.getNoticeNo() %>" class="a_btn" id="rec-btn">수정하기</a>
                    </td>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(232, 154, 153, 0.602);">
                        <a href="<%=contextPath %>/deleteForm.no?nno=<%=n.getNoticeNo() %>" onclick="return chk();"class="a_btn" id="del-btn">삭제하기</a>
                    </td>
                    	<p style="font-size: 15px;">&nbsp; 조회수 : <%=n.getNoticeHits() %></p>
                </tr>
                    <%} %>
            </table>
        </div>
        
    </div>
        
        <script>
            function chk(){
                
                var result = confirm("정말로 삭제하시겠습니까?");
                
                return result;
            }
        </script>
        
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
</body>
</html>














