<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.notice.model.vo.Notice" 
    import="com.semi.notice.controller.NoticeDetailCtlr"%>
<% Notice n = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>공지사항</title>
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
            width: 750px;
            border: 0px solid rgba(255, 221, 0, 0.343);
            font-size: 20px;
        }
        
        .footer{/*목록가기 수정 삭제버튼*/
            float: left;
            background-color: white;
            width: 1200px;
            height: 50px;
            font-size: 15px;
        }
        #ctlr{
            width: 300px;
            cursor: pointer;
        }
        pre{
        	font-size: 20px;
        }
    </style>
    </head>
    <body>
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
            <table id="detail-area" align="center" border="1px">
                <tbody align="left">
                    <tr>
                        <td style="width: 300px; height: 40px; padding:20px"><%=n.getNoticeTitle() %></td>
                        <td style="width: 150px; padding:20px"><%=n.getNoticeDate() %></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <pre style="height: 200px; padding:20px"><%=n.getNoticeContent() %></pre>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br><br>
        </div>
        
        <div align="center" class="footer">
            <table id="ctlr" align="center" >
                <tr>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(255, 221, 0, 0.343);"> 
                        <a href="<%=contextPath %>/list.fq" class="a_btn" id="list-btn">목록가기</a>
                    </td>
                    <% if(loginMem!=null && loginMem.getMemId().equals("admin")) {%>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(111, 174, 233, 0.343);">
                        <a href="<%=contextPath %>/updateForm.fq?nno=<%=n.getNoticeNo() %>" class="a_btn" id="rec-btn">수정하기</a>
                    </td>
                    <td align="center" style="width:100px; height: 25px; background-color: rgba(232, 154, 153, 0.602);">
                        <a href="<%=contextPath %>/deleteForm.no?nno=<%=n.getNoticeNo() %>" onclick="return chk();"class="a_btn" id="del-btn">삭제하기</a>
                    </td>
                    	<br><br><p style="font-size: 15px;">&nbsp; 조회수 : <%=n.getNoticeHits() %></p>
                </tr>
                    <%} %>
            </table>
            
        </div>
        <script>
            function chk(){
                
                var result = confirm("정말로 삭제하시겠습니까?");
                
                return result;
            }
        
        </script>

    </body>
    </html>