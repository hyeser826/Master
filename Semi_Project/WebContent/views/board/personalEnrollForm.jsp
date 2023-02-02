<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>1:1문의 작성</title>
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

        #enroll-form input, #enroll-form textarea{
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

		#co-tr{
			display : none;
		}
        
        input, textarea{
        	padding : 10px;
        }
        
        .ct-btn{
        	width:30px;
        	height:30px;
        }
    </style>
    </head>
    <body>
         <%@ include file="../common/menubar.jsp" %> 
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 35px; font-weight:bold;">1:1문의 작성</p>
            <br><br>
            <img src="resources/img/icon3.jpg" alt="" style="width: 27px; height:20px">

        </div>
        <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        <div id="left-side" align="center">
            <ol id="mem-center">
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li><a href="<%=contextPath %>/list.no">공지사항</a></li>
                <li><a href="<%=contextPath %>/list.fq">자주묻는질문</a></li>
                <li><a href="<%=contextPath %>/list.psn?currentPage=1">1:1문의</a></li>
            </ol>
        </div>    
        <div id="right-side" class="right-side">
        <form action="<%=contextPath %>/insert.psn" method="post" id="enroll-form">
	        <input type="hidden" name ="memId" value="<%=loginMem.getMemId() %>">
            <table id="psn-insert-table" class="psn-tables" align="center" border="1px">
	            <tr>
	            	<td style="width: 500px; height: 60px;">
	            	<input style="width: 100%; height: 60px; border:0px;" type="text" name="title" placeholder="제목을 입력하세요" required>
	            	</td>
	                <td style="width:50px;">
	                	<input id="personal-category" style="width:30px;" type="radio" name="category" value="PERSONAL" checked>
	            	</td>
	            	<td style="width:100px;">일반문의</td>
	            	<td style="width:50px;">
	                	<input id="master-category" style="width:30px;" type="radio" name="category" value="LEVELUP">
	            	</td>
	            	<td style="width:100px;">입점문의</td>        
            	</tr>

             </table>
                    <script>
	                    $("#personal-category").click(function(){
	                			$("#co-tr").css("display", "none");
	                	});

                    	$("#master-category").click(function(){
                    			$("#co-tr").css("display", "block");
                    	});

                    </script>
                    
                    <div id="co-tr" class="right-side">	
	                    <table id="co-table" align="center" class="psn-tables">
	                    	<tr align="center">
	                    		<td colspan="3" style="height:50px; font-size : 20px; font-weight : bold;">사업자등록증 입력폼</th>
	                    	</tr>
	                    	<tr align="center">
	                    		<td style="width:300px;" rowspan="7"><img style="width:270px; height:350px;"  alt="사업자등록증예시" src="resources/img/icon/사업자등록증.jpg"></td>
	                    	</tr>
	                    	<tr align="center" style="width:200px;">
	                    		<th>장인홈<br>(상점명)</th>
	                    		<td><input class="strName" type="text" name="coName"></td>
	                    	</tr>
	                    	<tr align="center">
	                    		<th>장인소개</th>
	                    		<td><textarea name="mstIntro" rows="10" style="width : 100%; height: 100%; resize:none;"></textarea></td>
	                    	</tr>
	                    	<tr align="center">
	                    		
	                    		<th>사업자등록번호</th>
	                    		<td><input class="psn-inputs" type="text" name="coNum"></td>
	                    	</tr>
	                    	<tr align="center">
	                    		<th>법인명<br>(개인사업자명/단체명)</th>
	                    		<td><input class="psn-inputs" type="text" name="coName"></td>
	                    	</tr>
	                    	<tr align="center">
	                    		<th>대표자</th>
	                    		<td><input class="psn-inputs" type="text" name="ceo"></td>
	                    	</tr>
	                    	<tr align="center">
	                    		<th>업태명 및 종목</th>
	                    		<td><input class="psn-inputs" type="text" name="coKind"></td>
	                    	</tr>
	                    	<tr align="left">
	                    		<td style="height:50px;" colspan="3">&nbsp;&nbsp;입력예시 사진 확인 후 항목입력. 상점명 및 상점소개 미입력시 상점명은 법인명으로 입력됩니다.</td>
	                    	</tr>
	                    </table>
	                    </div>
	                    
                    <div id="psn-tr" class="right-side">
		                <table class="psn-tables">
		                	<tr>
								<td colspan="2">
					                <textarea name="content" rows="10" style="width : 100%; height:auto; resize:none;" required></textarea>						
								</td>
							</tr>
		                </table> 
                    </div>
                </tbody>
            </table>
        </div>
        
        <div align="right" class="footer">
            <input type="submit" value="등록하기" class="a_btn" id="enroll-btn">
            <input type="reset" value="초기화" class="a_btn" id="reset-btn">
        </div>
            
        </form>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>