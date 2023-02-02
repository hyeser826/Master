<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,com.semi.product.model.vo.*"%>
<%
	Product p = (Product)request.getAttribute("p");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/*     .info{ */
/* 		background:gray; */
/* 		color:black; */
/* 		width:1000px; */
/* 		height: 400px; */
/* 		margin:auto;  /*가운데 정렬*/ */
/* 		margin-top:50px; /*위로부터 50px만큼 여백*/ */
/* 	} */
	table{
		width: 300px;
		height: 200px;
		border : 2px solid white;
	}
	.info{
/* 		border: 1px solid; */
/* 		width: 450px; */
/* 		height: 390px; */
	}
/* 	.info_div pic{ */
/* 		width:45%;  */
/* 		height:150px;  */
/* 		float:left;  */
/* 	} */
/* 	.info_div txt{ */
/* 		width:45%;  */
/* 		height:150px;  */
/* 		border:1px solid;  */
/* 		float:left; */
/* 	} */
	.grid_item.first {background-color: orange;}
	
	.count-wrap {position: relative;padding: 0 38px;border: 1px solid #ddd;overflow: hidden;width: 60px;}
	.count-wrap > button {border: 0;background: #ddd;color: #000;width: 38px;height: 38px;position: absolute;top: 0;font-size: 12px;}
	.count-wrap > button.minus {left: 0;}
	.count-wrap > button.plus {right: 0;}
	.count-wrap .inp {border: 0;height: 38px;text-align: center;display: block;width: 100%;}
</style>
</head>
<!--
	왼쪽 공간 : 사진 (메인1 서브3) 
	오른쪽 공간 : 
	맨 위에 해당 상품의 장인메인홈으로 링크연결
	- 상품정보
	1. 상품명
	----------------------------------------
	talbe 안에
	2. 판매가 : 25000원
	3. 배송비 : 3000원(5만원 이상 구매시 무료배송)
	4. 수량 :	- 2 + (-,+ 버튼)
	5. 포장서비스  : 셀렉트-옵션으로 선택/선택안함(디폴트)
	----------------------------------------
	6. 총 상품 금액 : 25000원
	7. 찜하기♡ 버튼   8. 장바구니 버튼
	9. 구매하기 버튼

-->
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	<div class="info">
<!-- 	<div class="info_div pic"> -->
		<div  style="width: 45%; height:400px; border:1px solid; float: left;">
			<img src="http://gdimg.gmarket.co.kr/2094578242/still/280?ver=1618974081">
		</div>
<!-- 	<div class="info_div txt"> -->
		<div  style="width: 45%; height:400px; border:1px solid; float: left;">
			<h4>장인홈 링크 설정해주기</h4>
			<hr>
			<h2>핸드메이드 주걱</h2>
			<table border="1">
				<tr>
					<td>판매가</td>
					<td>25000원</td>
				</tr>
				<tr>
					<td>배송비</td>
					<td>3000원</td>
				</tr>
				<tr>
					<td>수량</td>
					<td>
						<div class="count-wrap _count">
					    	<button type="button" class="minus"> - </button>
					    	<input type="text" class="inp" value="1"/>
					    	<button type="button" class="plus"> + </button>
						</div>
					</td>
				</tr>
				<tr>
					<td>포장서비스</td>
					<td>
						<select>
							<option value="wrap">선택안함</option>
							<option value="wrap">선택</option>
						</select>
					</td>
				</tr>
			</table>
			<div>
				총 상품 금액<br>
				총금액<br>
				<hr>
				<button>찜하기♡</button>
				<button>장바구니</button><br>
				<button>구매하기</button>
			</div>
		</div>
	</div>
	<script>
		//수량 옵션
		$('._count :button').on({
		    'click' : function(e){
		        e.preventDefault();
		        var $count = $(this).parent('._count').find('.inp');
		        var now = parseInt($count.val());
		        var min = 1;
		        var max = 999;
		        var num = now;
		        if($(this).hasClass('minus')){
		            var type = 'm';
		        }else{
		            var type = 'p';
		        }
		        if(type=='m'){
		            if(now>min){
		                num = now - 1;
		            }
		        }else{
		            if(now<max){
		                num = now + 1;
		            }
		        }
		        if(num != now){
		            $count.val(num);
		        }
		    }
		});
	</script>
</body>
</html>