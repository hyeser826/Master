<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,com.semi.manager.orderdetail.model.vo.OrderDetail
    							,com.semi.common.model.vo.PageInfo"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<OrderDetail> list = (ArrayList<OrderDetail>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderListView</title>
<style>
    .outer{
        width:100%;
        margin:auto; /*가운데 정렬*/
        margin-top:50px; /*위로부터 50px만큼 여백*/
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
    .table{
    	text-align:center;
    }
    .table td{
    	cursor:pointer;
    }
    .paging-btn{
    	background-color: rgba(255, 221, 0, 0.126);
    	border: 3px solid rgba(255, 221, 0, 0.343);
    }
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
	<div class="outer">
    	<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">주문 관리</p>
	        <br><br>
	    </div>
	    
        <div id="left-side" align="center">
            <ol id="manager-menu" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/manager.ma?currentPage=1">관리자 페이지</a></li>
                <li><a href="<%=contextPath%>/manager.ma?currentPage=1">회원 관리</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath %>/manageOrder.ma?currentPage=1">주문 관리</a></li>
            </ol>
        </div>
        
        <div id="right-side">
        	<h2 align="center">주문 관리</h2>
	        <br>
	        <table class="table table-hover">
	        	<thead>
	            	<tr>
	            		<th width="150">주문번호</th>
	            		<th width="140">상품명</th>
	            		<th width="60">수량</th>
	            		<th width="100">주문금액</th>
	            		<th width="100">처리상태</th>
	            		<th width="60">회원</th>
	            		<th width="100">수령인</th>
	            		<th>수령인 번호</th>
	            	</tr>
	            </thead>
	            <tbody>
	            	<%if(list.isEmpty()){ %>
	            		<tr>
	            			<td colspan="8">조회된 주문내역이 없습니다.</td>
	            		</tr>
	            	<%}else{ %>
	            		<%for(OrderDetail od : list){ %>
	            			<tr class="selectOrder">
	            				<td style="vertical-align:middle;"><%=od.getOrderDetailNo()%></td>
			            		<td style="vertical-align:middle;"><%=od.getProductName()%></td>
			            		<td style="vertical-align:middle;"><%=od.getProductCount()%></td>
			            		<td style="vertical-align:middle;"><%=od.getProductPrice()*od.getProductCount()%>원</td>
			            		<td style="vertical-align:middle;"><%=od.getOrderStatus()%></td>
			            		<td style="vertical-align:middle;"><%=od.getMemFlag()%></td>
			            		<td style="vertical-align:middle;"><%=od.getReciverName()%></td>
			            		<td style="vertical-align:middle;"><%=od.getReciverPhone()%></td>
		            		</tr>
	            		<%} %>
	            	<%} %>
	            </tbody>
	        </table>
	        <br>
	        <!-- 페이징바 -->
	        <div align="center" class="paging-area">
	        	<%if(pi!=null){ %>
	        	<%if(pi.getCurrentPage() != 1){ %>
		        	<button class="paging-btn" onclick="location.href='<%=contextPath%>/manageOrder.ma?currentPage=<%=pi.getCurrentPage()-1%>'">&lt;</button>
		        <%}else{ %>
		        	<button class="paging-btn" disabled>&lt;</button>
		        <%} %>
		        
		        <%for(int i=pi.getStartPage(); i<=pi.getEndPage(); i++){ %>
	        		<!-- 내가 요청한 페이지 버튼 비활성화 -->
		        	<%if(i==pi.getCurrentPage()){ %>
		        		<button class="paging-btn" disabled><%=i %></button>
		        	<%}else{ %>
		        		<button class="paging-btn" onclick="location.href='<%=contextPath%>/manageOrder.ma?currentPage=<%=i%>'"><%=i%></button>
		        	 <%} %>
		        <%} %>
		        
		        <%if(pi.getCurrentPage() != pi.getMaxPage()){ %>
		        <button class="paging-btn" onclick="location.href='<%=contextPath%>/manageOrder.ma?currentPage=<%=pi.getCurrentPage()+1%>'">&gt;</button>
	        	<%}else{ %>
	        		<button class="paging-btn" disabled>&gt;</button>
		        <%} %>
		        <%} %>
	        </div>
	        <br><br>
        </div>
</body>
</html>