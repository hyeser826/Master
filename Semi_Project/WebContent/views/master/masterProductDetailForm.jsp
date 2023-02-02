<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.semi.product.model.vo.*,java.util.ArrayList, java.sql.*"%>
<%
	Product p = (Product)request.getAttribute("p");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    .outer{
        width:100%;
        margin:auto; /*가운데 정렬*/
        margin-top:50px; /*위로부터 50px만큼 여백*/
    }
    .header{
        color : black;
        width : 1200px;
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
    #mypage-menu{
        margin : 0 auto; /*가운데 정렬*/
        width: 200px;
        font-size: 20px;
        padding: 0px;
     	align:"center"
    }
    #mypage-menu>li{
        margin : 0 auto; /*가운데 정렬*/
        width: 100%;
        height: 100%;
        cursor: pointer;
        padding: 15px;
        list-style : none;
    }
    #mypage-menu>li>a{
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

	#mypage-menu>ol>li{
	    margin: 0 auto;
	    font-size: 10px;
	    cursor: pointer;
	    padding: 15px;
	    list-style: none;
	}
	#mypage-menu>ol>li>a{
		text-decoration: none;
		font-size: 15px;
	}
	li:hover {
		border-bottom: 3px solid rgba(255, 221, 0, 0.343);
	}
	
</style>
<title>Insert title here</title>
</head>
<body>
	<%@include file="/views/common/menubar.jsp"%>
	<div class="outer">
	<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">상품 상세</p>
	        <br><br>
	    </div>
		
		<%@include file="/views/common/leftbar2.jsp" %>
	
		<div id="right-side">
			<h2 align="center">상품 상세</h2>
			<table align="center" class="table table-bordered table-sm">
				<tr>
					<th width="60">상품명</th>
					<td colspan="3"><%=p.getProName()%></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td colspan="3"><%=p.getCategoryName()%></td>
				</tr>
				<tr>
					<th>가격</th>
					<td colspan="3"><%=p.getProPrice()%></td>
				</tr>
				<tr>
					<th>배송비</th>
					<td colspan="3"><%=p.getDeliveryFee()%></td>
				</tr>
				<tr>
					<th>상품 상세 내용</th>
					<td colspan="3"><textarea name="content" style="resize: none;"
							cols="30" rows="10" required><%=p.getProDescription() %></textarea></td>
				</tr>
				<tr>
					<th>상품수량</th>
					<td colspan="3"><%=p.getProStock()%></td>
				</tr>
				<tr>
					<th>체험기간</th>
					<td colspan="3"><%=p.getExpPeriod()%></td>
				</tr>
				<tr>
					<th>대표이미지</th>
					<!--미리보기-->
					<td colspan="3" align="center">
						<img src="<%=contextPath %><%=(list.get(0).getFilePath())+(list.get(0).getSysName()) %>" 
							id="titleImg" width="250" height="170">
					</td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<%for(int i=1;i<list.size();i++){%>
						<td>
						<img src="<%=contextPath%><%=(list.get(i).getFilePath())+(list.get(i).getSysName())%>" 
							id="contentImg<%=i %>" width="150" height="120">
						</td>
					<%} %>
				</tr>

			</table>

			<div align="center">
<%-- 			<button class="btn btn-info" onclick="location.href='<%=contextPath%>/mypagepr.ms?currentPage=1'">목록가기</button> --%>
				<button class="btn btn-info" onclick="location.href='<%=contextPath%>/mypagepr.ms?'">목록가기</button>
				<button class="btn btn-info" onclick="location.href='<%=contextPath%>/updateForm.pr?pno=<%=p.getProNo()%>'">수정하기</button>
				<button class="btn btn-info" onclick="location.href='<%=contextPath%>/delete.pr?pno=<%=p.getProNo()%>'">삭제하기</button>
				<button class="btn btn-info" type="button" onclick="history.back();">뒤로가기</button>
			</div>
			<br>
			<br>
		</div>
	</div>
</body>
</html>