<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,com.semi.product.model.vo.*,com.semi.master.model.vo.*"%> 
<%
	//list와 m 꺼내기
	ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
	Master m = (Master)request.getAttribute("m");
%>
    
<!DOCTYPE html>
<html>
<head>
 <title>상품등록,관리 페이지</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<style>
    .outer{
        width:100%;
        margin:auto; /*가운데 정렬*/
        margin-top:50px; /*위로부터 50px만큼 여백*/
        background:bisque;
		color:white;
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
	nav-tabs>li>a{
	 color: gray;
	}
	#jbtr{
		background-color: bisque;
	}
	thead{
		background-color:bisque;
	}
</style>
<title>Insert title here</title>
</head>
<body>
    <%@include file="/views/common/menubar.jsp" %>
    
	<%@include file="/views/common/leftbar2.jsp" %>
	
		
	<div id="right-side">
	<div class="container">
    <form>
	  <div class="jumbotron" id="jbtr">
	    <h1><%=m.getStrName() %></h1>      
	    <p><%=m.getMasterIntro() %></p>
	  	<br>
	  	<a href="<%=contextPath%>/introms.in" class="btn btn-info">소개 수정하기</a>
	  </div>
	 </form>    
	</div>
		
	  <h2 align="center">장인_상품관리</h2>
	<div class="container">
	  <a href="<%=contextPath%>/enrollForm.pr" class="btn btn-info" style="float:right;">상품 등록하기</a>
	  <br>
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link active" data-toggle="tab" href="#home">전체</a> 	<!-- 이 안에 상품등록도 할 수 있도록 버튼만들기 -->
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu1">상품</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu2">체험</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu3">판매중</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu4">미판매</a>
	    </li>
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div id="home" class="container tab-pane active"><br>
	      <h3>전체 상품 조회</h3>
	      <table class="table table-hover" align="center">
            <thead>
                <tr>
                    <th>상품번호</th>
                    <th>카테고리명</th>	<!-- 상품/체험 섞였고 1-7은 상품 8-23은 지역 -->
                    <th>상품명</th>
                    <th>가격</th>
                    <th>재고수량</th>
                    <th>체험기간</th>
                    <th>판매가능여부</th>		
                </tr>
            </thead>
            <tbody>
          		<%if(list.isEmpty()) {%>
          			<tr>
          				<td colspan="6">조회된 게시글이 없습니다.</td>
          			</tr>
          		<%}else {%>
          			<%for(Product p:list) {%>
          				<tr>
          					<td><%=p.getProNo() %></td>
          					<td><%=p.getCategoryName() %></td>
          					<td><%=p.getProName() %></td>
          					<td><%=p.getProPrice() %></td>
          					<td><%=p.getProStock() %></td>
          					<td><%=p.getExpPeriod() %></td>
          					<td><%=p.getProStatus() %></td>
          				</tr>
          			<%} %>
          		<%} %>
            </tbody>
        </table>
        
	    </div>
	    <div id="menu1" class="container tab-pane fade"><br>
	      <h3>상품 조회</h3>
			<table class="table table-hover">
            <thead>
                <tr>
                    <th>상품번호</th>
                    <th>카테고리명</th>	<!-- 상품/체험 섞였고 1-7은 상품 8-23은 지역 -->
                    <th>상품명</th>
                    <th>가격</th>
                    <th>재고수량</th>
                    <th>판매가능여부</th>
                </tr>
            </thead>
            <tbody>
          		<%if(list.isEmpty()) {%>
          			<tr>
          				<td colspan="6">조회된 게시글이 없습니다.</td>
          			</tr>
          		<%}else {%>
          			
          			<%for(Product p:list) {%>
          				<%if(!p.getCategoryName().equals("0.체험")){ %>
	          				<tr>
	          					<td><%=p.getProNo() %></td>
	          					<td><%=p.getCategoryName() %></td>
	          					<td><%=p.getProName() %></td>
	          					<td><%=p.getProPrice() %></td>
	          					<td><%=p.getProStock() %></td>
	          					<td><%=p.getProStatus() %></td>
	          				</tr>
	          			<%}else { %>
	          				<tr>
	          					<td colspan="6">등록된 상품이 없습니다:)</td>
	          				</tr>
	          			<%} %>
          			<%} %>
          		<%} %>	
            </tbody>
        </table>
	    </div>
	    <div id="menu2" class="container tab-pane fade"><br>
	      <h3>체험 조회</h3>
			<table class="table table-hover" align="center">
            <thead>
                	<th>상품번호</th>
                    <th>카테고리명</th>	<!-- 상품/체험 섞였고 1-7은 상품 8-23은 지역 -->
                    <th>상품명</th>
                    <th>가격</th>
                    <th>가능한 인원</th>
                    <th>체험기간</th>
                    <th>판매가능여부</th>
            </thead>
            <tbody>
            	<%if(list.isEmpty()) {%>
          			<tr>
          				<td colspan="7">조회된 게시글이 없습니다.</td>
          			</tr>
          		<%}else {%>
          			<%for(Product p:list) {%>
          				<%if(p.getCategoryName().equals("0.체험")){ %>
	          				<tr>
	          					<td><%=p.getProNo() %></td>
	          					<td><%=p.getCategoryName() %></td>
	          					<td><%=p.getProName() %></td>
	          					<td><%=p.getProPrice() %></td>
	          					<td><%=p.getProStock() %></td>
	          					<td><%=p.getExpPeriod() %></td>
	          					<td><%=p.getProStatus() %></td>
	          				</tr>
          				<%}%>
          			<%} %>
          		<%} %>	
            </tbody>
        </table>
	    </div>
	    <div id="menu3" class="container tab-pane fade"><br>
	      <h3>판매중</h3>
			<table class="table table-hover" align="center">
            <thead>
                	<th>상품번호</th>
                    <th>카테고리명</th>	<!-- 상품/체험 섞였고 1-7은 상품 8-23은 지역 -->
                    <th>상품명</th>
                    <th>가격</th>
                    <th>상품재고 / 가능한 인원</th>
                    <th>체험기간</th>
                    <th>판매가능여부</th>
            </thead>
            <tbody>
            	<%if(list.isEmpty()) {%>
          			<tr>
          				<td colspan="7">조회된 게시글이 없습니다.</td>
          			</tr>
          		<%}else {%>
          			<%for(Product p:list) {%>
          				<%if(p.getProStatus().equals("Y")){ %>
	          				<tr>
	          					<td><%=p.getProNo() %></td>
	          					<td><%=p.getCategoryName() %></td>
	          					<td><%=p.getProName() %></td>
	          					<td><%=p.getProPrice() %></td>
	          					<td><%=p.getProStock() %></td>
	          					<td><%=p.getExpPeriod() %></td>
	          					<td><%=p.getProStatus() %></td>
	          				</tr>
          				<%}%>
          			<%} %>
          		<%} %>	
            </tbody>
        </table>
	    </div>
	    <div id="menu4" class="container tab-pane fade"><br>
	      <h3>미판매</h3>
			<table class="table table-hover" align="center">
            <thead>
                	<th>상품번호</th>
                    <th>카테고리명</th>	<!-- 상품/체험 섞였고 1-7은 상품 8-23은 지역 -->
                    <th>상품명</th>
                    <th>가격</th>
                    <th>상품재고 / 가능한 인원</th>
                    <th>체험기간</th>
                    <th>판매가능여부</th>
            </thead>
            <tbody>
            	<%if(list.isEmpty()) {%>
          			<tr>
          				<td colspan="7">조회된 게시글이 없습니다.</td>
          			</tr>
          		<%}else {%>
          			<%for(Product p:list) {%>
          				<%if(!p.getProStatus().equals("Y")){ %>
	          				<tr>
	          					<td><%=p.getProNo() %></td>
	          					<td><%=p.getCategoryName() %></td>
	          					<td><%=p.getProName() %></td>
	          					<td><%=p.getProPrice() %></td>
	          					<td><%=p.getProStock() %></td>
	          					<td><%=p.getExpPeriod() %></td>
	          					<td><%=p.getProStatus() %></td>
	          				</tr>
          				<%}%>
          			<%} %>
          		<%} %>	
            </tbody>
        </table>
	    </div>
        <script>

	        $(function(){
		   		$(document).on("click", ".jumbotron .btn", function () {
	   			  	var masterNo = $(this).siblings("#mstno").val();
	           		console.log(masterNo);
	           		event.preventEvent();
	           		
		          	location.href="<%=contextPath%>/updatems.in?masterNo="+masterNo;
		   		});
	    	});	

        	
        	$(function(){
	        	$(".table>tbody>tr").click(function(){
	        		location.href="<%=contextPath%>/detailms.pr?pno="+$(this).children().eq(0).text();
	        	})
	        })
	        
        </script>
	  </div>
	</div>  	
	</div>
	<br><br><br><br><br><br>
</body>
</html>