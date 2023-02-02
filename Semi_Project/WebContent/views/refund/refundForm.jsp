<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.order.model.vo.OrderDetail" %>
<%
	OrderDetail od = (OrderDetail)request.getAttribute("od");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취소/환불요청</title>
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
	.btn-back{
    	background-color: rgba(255, 221, 0, 0.126);
    	border: 3px solid rgba(255, 221, 0, 0.343);
    }
    .btn-ask{
    	background-color: rgba(255, 174, 13, 100);
    	border: 3px solid rgba(255, 221, 0, 0.343);
    	color: rgb(64, 60, 0);
    }
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
	<div class="outer">
		<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">취소/환불요청</p>
	        <br><br>
	    </div>
		
		<div id="left-side">
            <ol id="mypage-menu" class="main-menu">
                <li style="background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/xxxxxx.xx">주문내역</a></li>
	            <ol style="text-align:left;">
	            <li style="line-height:15px;"><a href="<%=contextPath%>/xxxxxx.xx">주문 조회</a></li>
	            <li style="line-height:15px; border-bottom: 3px solid rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/xxxxxx.xx">취소/환불요청</a></li>
	            </ol>
                
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath %>/xxxxxx.xx">내 정보</a></li>
                <ol style="text-align:left;">
	            <li style="line-height:15px"><a href="<%=contextPath %>/xxxxxx.xx">개인정보 수정</a></li>
	            <li style="line-height:15px"><a href="<%=contextPath %>/xxxxxx.xx">내가 쓴 리뷰</a></li>
	            </ol>
	                
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath %>/xxxxxx.xx">내 상점 관리</a></li>
                <ol style="text-align:left;">
	            <li style="line-height:15px"><a href="<%=contextPath %>/xxxxxx.xx">상품 관리</a></li>
	            <li style="line-height:15px"><a href="<%=contextPath %>/xxxxxx.xx">주문 관리</a></li>
	            </ol>
            </ol>
        </div>
        
        <div id="right-side">
			<h2 align="center">환불 요청</h2>
	        <form action="" id="masterUpdate-form" method="post">
			<input type="hidden" name="orderDetailNo" value="<%=od.getOrderDetailNo()%>">
			<input type="hidden" name="orderNo" value="<%=od.getOrderNo()%>">
	        	
	        	<br>
	            <table class="table table-borderless" border="1">
		           	<tr class="order-area" style="border-bottom:1px solid lightgrey;">
		               	<th width="140">주문상세번호</th>
		               	<td width="130">상품명</td>
		               	<td width="200"></td>
		               	<td width="180" style="text-align:center;">주문금액(수량)</td>
		               	<td style="text-align:center;">장인홈</td>
		            </tr>
		            <tr class="order-area" style="border-bottom:1px solid lightgrey;">
		               	<td height="100" style="vertical-align:middle;" id="orderDetailNo"><%=od.getOrderDetailNo()%></td>
		               	<td style="vertical-align:middle;"><img alt="상품" src="<%=contextPath %><%=od.getTitleImg()%>" width="120px"></td>
		               	<td style="vertical-align:middle;"><%=od.getProductName()%></td>
		               	<td style="vertical-align:middle; text-align:center;"><%=od.getProductPrice()*od.getProductCount()%>원<br>(<%=od.getProductCount()%>개)</td>
		               	<td style="vertical-align:middle; text-align:center;"><%=od.getStrName()%></td>
		            </tr>
		            <tr class="order-area" style="border-bottom:1px solid lightgrey;">
		            	<th>환불 예정 금액</th>
		            	<td><%=od.getProductPrice()*od.getProductCount()%>원</td>
		            </tr>
		            <tr class="order-area" style="border-bottom:1px solid lightgrey;">
		               	<th style="vertical-align:middle;">환불 사유</th>
		               	<td colspan="5"><textarea rows="2" cols="85" style="resize:none" name="refundReason" required></textarea></td>
		            </tr>
		            <tr>
	        			<th rowspan="2" style="vertical-align:middle;">환불 요청 동의</th>
	        			<td colspan="5">
	        				<label for="agree">
	        					<input type="checkbox" id="agree" required>
	        					환불 요청 상품의 상품명, 상품가격, 배송정보를 확인하였으며, 환불에 동의하시겠습니까? (전자상거래법 제8조 2항)
	        				</label>
	        				<div style="font-size:small;">
	                    	맞춤 상품 특성상 환불 요청이 거절될 수 있습니다. 배송비가 있는 상품의 경우 결제된 배송비는 환불되지 않으며, 주문금액에서 반송 택배비를 제외한 금액이 환불됩니다. 자세한 사항은 1:1문의에서 확인하실 수 있습니다. 
	        				</div>
	        			</td>
	        		</tr>
	            </table>
	            <div style="text-align:center;">
	            	<button type="button" onclick="history.back();" class="btn-back">돌아가기</button>
	                <button type="submit" class="btn-ask" formaction="<%=contextPath%>/insertRefund.or">환불 요청</button>
	            </div>
			</form>
			<br><br><br><br><br><br><br><br><br><br>
		</div>
	</div>
</body>
</html>