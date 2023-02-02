<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,java.util.LinkedHashMap
    							,com.semi.order.model.vo.*"%> 
<%
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("list");
	LinkedHashMap<Integer,ArrayList<OrderDetail>> map = (LinkedHashMap<Integer,ArrayList<OrderDetail>>)request.getAttribute("map");
%>
<!DOCTYPE html>
 <html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	table> thead{
	cursor:pointer;
	}
	
</style>
<title>주문 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	
</style>
</head>
<body>      
   	<!-- ================================== 주문목록 ================================== -->
  	<%@include file="/views/common/menubar.jsp" %>
  	
    <div class="outer">
	     <div class="header" align="center">
		        <p style="font-size: 35px; font-weight:bold;">주문 내역</p>
		        <br><br>
		 </div>
	<%@include file="/views/common/leftbar2.jsp" %>
        
     <div id="right-side">
        	<form id="orderList">
        		<div class="container">
        		<%if(!list.isEmpty()) {%>
                    <%for(Order o:list) {%>
                    <table class="table table-bordered" id="order-table">
                        <thead style="background-color: lightgray;" class="product-detail">
                              <tr style="font-size: 25px; height: 80px;" >
                              	<input type="hidden" id="ono" name="ono" value=<%=o.getOrderNo()%>>
                                <td colspan="4" width="250px"><%=o.getOrderDate()%> (<%=o.getOrderNo()%>)</td>
                              </tr>
                        </thead>
                        <!-- 주문 한 건에 대한 전체 상품리스트가 보여지는 공간 -->
                        <tbody align="center">
                                <%ArrayList<OrderDetail> odList = map.get(o.getOrderNo());//키값으로 뽑은 리스트%>
                                <%for(OrderDetail od : odList){ //리스트 전체 순환반복%>
                                    <tr>
                                        <td width="100px" height="100px"><img src="<%=contextPath%><%=od.getTitleImg()%>"></td>
                                        <td><%=od.getProductName()%></td><!-- 순차적으로 접근된 객체에서 필드값 추출 -->
                                        <td><%=od.getProductPrice() * od.getProductCount()%>원</td>
                                        <td><%=od.getProductCount()%>개</td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" width="250px" align="left"><%=od.getOrderStatus()%></td>
                                    </tr>
                                <%} %>
                       </tbody>
        			</table>
        			<%} %>
        		<%} %>
        		</div>
        	</form>
        	<br><br><br><br><br><br><br><br>
        </div>
         <script>
         //$(".product-detail")클릭 시 그 부분이 가지고 있는 oderNo를 controller에 넘겨준다.
         
         $(function(){
     		$(".product-detail").on("click", "tr", function () {
                var ono = $(this).children().val();
                location.href="<%=contextPath%>/detail.or?ono="+ono;
            });
      	});
         
         </script>
      </div>
    <br><br><br><br><br><br><br><br><br><br>
</body>
</html>