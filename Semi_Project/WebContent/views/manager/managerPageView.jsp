<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,com.semi.member.model.vo.Member
    							,com.semi.common.model.vo.PageInfo"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Member> list = null;
	list = (ArrayList<Member>)request.getAttribute("list");
	Member oneMem = null;
	oneMem = (Member)request.getAttribute("m");
%>
 <%@include file="/views/common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>managerPage</title>
<style>
    .outer{
        background:white;
        color : black;
        width : 100%;
        margin : auto; /*가운데 정렬*/
        margin-top : 50px; /*위로부터 50px만큼 여백*/
    }
    .header{
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
    #manager-menu{
        margin : 0 auto; /*가운데 정렬*/
        width: 200px;
        height: 60px;
        font-size: 20px;
        padding: 0px;
     	align:"center"
    }
    #manager-menu>li{
        margin : 0 auto; /*가운데 정렬*/
        width: 100%;
        height: 100%;
        font-size: 20px;
        cursor: pointer;
        padding: 15px;
        list-style : none;
    }
    #manager-menu>li:hover{
        border-bottom: 3px solid rgba(255, 221, 0, 0.343);
    }
    #manager-menu>li>a{
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
    .search-btn{
        background-color: rgba(255, 221, 0, 0.126);
        border: 3px solid rgba(255, 221, 0, 0.343);
        width: 100px;
        height: 40px;
        font-size: 20px;
        font-weight: bold;
        color: rgba(76, 66, 2, 0.95);
    }
    .search-form{
        width: 810px;
        height: 250px;
        text-align: center;
        border: 3px solid lightgrey;
        font-size: 20px;
        font-weight: bold;
    }
    .searchTable{
    	background-color: rgba(255, 221, 0, 0.126);
    	margin: auto;
        width: 500px;
        border: 3px solid rgba(255, 221, 0, 0.343);
        font-size: 20px;
    }
    .searchTable tr{
        padding: 20px;
    }
    .a-btn{
    	background-color: white;
    	border: none;
    }
    .a-btn:hover{
    	background-color: rgba(255, 221, 0, 0.126);
    }
    .paging-btn{
    	background-color: rgba(255, 221, 0, 0.126);
    	border: 3px solid rgba(255, 221, 0, 0.343);
    }
    #name,#phone{
        width: 95%;
    }
    #resultTable{
        margin:auto;
        text-align: center;
    }
    #resultTable:hover{cursor:pointer;}
</style>
</head>
<body>
    <div class="outer">
		<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">회원 관리</p>
	        <br><br>
	    </div>
	    
        <div id="left-side" align="center">
            <ol id="manager-menu">
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/manager.ma?currentPage=1">관리자 페이지</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath%>/manager.ma?currentPage=1">회원 관리</a></li>
                <li><a href="<%=contextPath %>/manageOrder.ma?currentPage=1">주문 관리</a></li>
            </ol>
        </div>
        
        <div id="right-side">
        	<h2 align="center">회원 관리</h2>
	        <form action="<%=contextPath%>/search.ma" class="search-form">
	            <br>
	            <table border="1" cellpadding="10" class="searchTable">
	                <tr>
	                    <th>이름</th>
	                    <td><input type="text" name="memName" id="name" required></td>
	                </tr>
	                <tr>
	                    <th>전화번호</th>
	                    <td><input type="text" name="memPhone" id="phone" required placeholder="- 제외하고 숫자만 입력"></td>
	                </tr>
	            </table>
	            <br>
	            <div class="btns">
	                <button type="reset" class="button search-btn">초기화</button>
	                <button type="submit" class="button search-btn">검색</button>
	            </div>
	            <br>
	        </form>
	        <br><br>
	        
			<div id="list-area">
	        <button type="button" class="a-btn" onclick="order(1);">가입일순</button> |
	        <button type="button" class="a-btn" onclick="order(2);">가입일 역순</button> |
	        <button type="button" class="a-btn" onclick="order(3);">최근 구매일순</button>
	        <br><br>
	            <table id="resultTable" class="table table-hover" border="1" cellpadding="5">
	                <thead>
	                	<tr>
		                    <th>회원 번호</th>
		                    <th>회원 등급</th>
		                    <th>이름</th>
		                    <th>전화번호</th>
		                    <th>최근 구매일</th>
		                    <th>회원 가입일</th>
		                    <th>회원 상태</th>
	                    </tr>
	                </thead>
	                <tbody>
		            <%if(list==null && oneMem==null){ %>
		            	<tr>
		            		<td colspan="8">조회된 회원이 없습니다.</td>
		            	</tr>
		            <%}else if(oneMem==null){ %>
		            	<%for(Member m : list){ %>
			                <tr>
			                    <td><%=m.getMemNo() %></td>
			                    <td><%=m.getGrade() %></td>
			                    <td><%=m.getMemName() %></td>
			                    <td><%=m.getMemPhone() %></td>
			                    <%if(m.getOrderDate()==null){ %>
			                    	<td>없음</td>
			                    <%}else{ %>
			                    	<td><%=m.getOrderDate() %></td>
			                    <%} %>
			                    <td><%=m.getEnrolldate() %></td>
			                    <td><%=m.getEnrollflag() %></td>
			                </tr>
		            	<%} %>
		            <%}else{ %>
		            	<tr>
			                <td><%=oneMem.getMemNo() %></td>
			                <td><%=oneMem.getGrade() %></td>
			                <td><%=oneMem.getMemName() %></td>
			                <td><%=oneMem.getMemPhone() %></td>
			                <%if(oneMem.getOrderDate()==null){ %>
			                	<td>없음</td>
			                <%}else{ %>
			                	<td><%=oneMem.getOrderDate() %></td>
			                <%} %>
			                <td><%=oneMem.getEnrolldate() %></td>
			                <td><%=oneMem.getEnrollflag() %></td>
			            </tr>
		            <%} %>
	                </tbody>
	            </table>
	            <br>
	        </div>
	        
	        <!-- 페이징바 -->
	        <div align="center" class="paging-area">
	        	<%if(pi!=null){ %>
	        	<%if(pi.getCurrentPage() != 1){ %>
		        	<button class="paging-btn" onclick="location.href='<%=contextPath%>/manager.ma?currentPage=<%=pi.getCurrentPage()-1%>'">&lt;</button>
		        <%}else{ %>
		        	<button class="paging-btn" disabled>&lt;</button>
		        <%} %>
		        
		        <%for(int i=pi.getStartPage(); i<=pi.getEndPage(); i++){ %>
	        		<!-- 내가 요청한 페이지 버튼 비활성화 -->
		        	<%if(i==pi.getCurrentPage()){ %>
		        		<button class="paging-btn" disabled><%=i %></button>
		        	<%}else{ %>
		        		<button class="paging-btn" onclick="location.href='<%=contextPath%>/manager.ma?currentPage=<%=i%>'"><%=i%></button>
		        	 <%} %>
		        <%} %>
		        
		        <%if(pi.getCurrentPage() != pi.getMaxPage()){ %>
		        <button class="paging-btn" onclick="location.href='<%=contextPath%>/manager.ma?currentPage=<%=pi.getCurrentPage()+1%>'">&gt;</button>
	        	<%}else{ %>
	        		<button class="paging-btn" disabled>&gt;</button>
		        <%} %>
		        <%} %>
	        </div>
	        <br><br>
        </div>
    </div>
    
    <script>
		$(function(){
			$("#list-area tbody>tr").click(function(){
				var mno = $(this).children().eq(0).text();
				location.href="<%=contextPath%>/detail.ma?mno="+mno;
			});
		});
		function order(num){
			switch(num){
			case 1 : location.href="<%=contextPath%>/managerOrder.ma?currentPage=1&num="+num; break;
			case 2 : location.href="<%=contextPath%>/managerOrder.ma?currentPage=1&num="+num; break;
			case 3 : location.href="<%=contextPath%>/managerOrder.ma?currentPage=1&num="+num; break;
			}
		}
	</script>
</body>
</html>