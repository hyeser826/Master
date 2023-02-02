<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,com.semi.order.model.vo.*"%>
<%
	Order o = (Order)request.getAttribute("o");
	ArrayList<OrderDetail> odList = (ArrayList<OrderDetail>)request.getAttribute("odList");
	
	int totalPrice = 0;
	int productCount = 0;
	int expCount = 0;
	int productTotalPrice = 0;
	int expTotalPrice = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 상세</title>
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
/* 	.static>td{ */
/* 		vertical-align:middle; */
/* 	} */
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
    <div class="outer">
    <div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">주문 상세</p>
	        <br><br>
	    </div>
    <%@include file="/views/common/leftbar2.jsp" %>
		
       <div id="right-side">
        		<div class="container">
        			<!-- 주문 정보 -->
        			<table class="table table-bordered verticle">
        				<thead style="background-color: lightgray;">
       					  	<tr style="font-size: 25px; height: 80px;"  class="product-detail">
                               	<td colspan="4" width="250px">주문정보</td>
                            </tr>
        				</thead>
                        <tbody align="center">
                            <tr>
								<th>주문일자</th>
								<td><%=o.getOrderDate() %></td>
                            </tr>
							<tr>
								<th>주문번호</th>
								<td><%=o.getOrderNo() %></td>
							</tr> 
							<tr>
								<th>주문자</th>
								<td><%=o.getOrderDate() %></td>
							</tr>
                        </tbody>  
        			</table>
        			
        			<!-- 주문 상품 목록 -->
        			<!-- 장인별 주문 상세 테이블 만들기 -->
	                 	
		        			<table class="table table-bordered">
		        				<thead style="background-color: lightgray;">
		       					  	<tr style="font-size: 25px; height: 80px;"  class="product-detail">
		                               	<td colspan="4" width="250px" id="productInfo"></td>
		                            </tr>
		        				</thead>
								
		                        <tbody>
		                    	<%for(int i = 0;i<odList.size();i++) {%>
			                    	<%if(odList.get(i).getExpDate()==null) {%>  
			                    		<%productCount++; %>
			                            <tr>
											<td rowspan="5" style="vertical-align:middle;">
												<img src="<%=contextPath %><%=odList.get(i).getTitleImg()%>" 
													id="titleImg" width="250" height="170">
											</td>
			                            </tr>
			                            <tr>
											<td>상품명</td>
											<td><%=odList.get(i).getProductName() %></td>
			                            </tr>
										<tr>	
											<td>수량</td>
											<td><%=odList.get(i).getProductCount() %></td>
										</tr>
										<tr>	
											<td>상품 구매 금액</td>
											<td><input type="hidden" value="<%=odList.get(i).getProductCount()* odList.get(i).getProductPrice()%>" class="cp+<%=i%>"><%=odList.get(i).getProductCount()* odList.get(i).getProductPrice()%></td>
			                           		<%productTotalPrice += odList.get(i).getProductCount()* odList.get(i).getProductPrice(); %>
			                           		<%totalPrice += odList.get(i).getProductCount()* odList.get(i).getProductPrice(); %>
			                            </tr>
			                            <tr>	
											<td>발송일</td>
											<td><%=odList.get(i).getDeliveryShipDate() %></td>
			                            </tr>
			                            <tr class="status">
			                            	<td>처리상태</td>
			                            	<td><%=odList.get(i).getOrderStatus() %></td>
			                            	<input type="hidden" id="detailOno" name="detailOno" value="<%=odList.get(i).getOrderDetailNo()%>">
			                            	<%if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("입금대기")) {%>
			                            		<td><button class="btn btn-info" value="주문취소">주문취소</button></td>
			                            	<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("주문취소")) {%>
			                            		<td>
			                            			<p>주문취소되었습니다. 3일 이내로 등록해주신 환불계좌로 환불될 예정입니다. 감사합니다.</p>
			                            		</td>
			                            	<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("결제완료")){%>
		                            		<td>
			                    				<p></p>
			                    			</td>
		                            		<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("상품준비중")){%>
			                            		<td>
				                    				<p>상품이 준비중입니다.</p>
				                    			</td>
			                            	<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("배송완료")) {%>
				                        		<td>
				                    				<button class="btn btn-info" value="구매확정">구매확정</button>
				                    				<p>택배사 : <%=odList.get(i).getDeliveryParcel() %></p>
				                    				<p>송장번호 : <%=odList.get(i).getDeliveryInvoiceNo()%></p>
				                    			</td>
			                    			<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("구매확정")) {%>
			                            		<td>
			                            			<input type="hidden" name="review" value="<%=odList.get(i).getProductNo() %>">
			                            			<button class="btn btn-info" value="리뷰작성">리뷰작성</button>
			                            			<button class="btn btn-info" value="환불">환불</button>
			                            		</td>
			                            	<%}else if(odList.get(i).getOrderStatus()!=null && odList.get(i).getOrderStatus().equals("리뷰작성")) {%>
			                            		<td>
				                    				<p>리뷰작성이 완료되었습니다.</p>
				                    			</td>
			                            	<%} %>
			                            </tr>
			                            <%} %>
		                        <%} %>
		                       </tbody>
		                   	</table>
                       
							<!-- 체험 리스트 -->
                            <table class="table table-bordered">
		        				<thead style="background-color: lightgray;">
		       					  	<tr style="font-size: 25px; height: 80px;"  class="product-detail">
		                               	<td colspan="4" width="250px" id="expInfo"></td>
		                            </tr>
		        				</thead>
								
		                        <tbody>
		                        <%for(OrderDetail od : odList) {%>
		                        <%if(od.getExpDate()!=null) {%>
		                        	<%expCount++; %>
	                            	<tr>
									<td rowspan="6">
										<img src="<%=contextPath %><%=od.getTitleImg()%>" 
											id="titleImg" width="250" height="170">
									</td>
		                            </tr>
		                            <tr>
										<td style="vertical-align:middle;">체험명</td>
										<td style="vertical-align:middle;"><%=od.getProductName() %></td>
		                            </tr>
		                            <tr>
										<td style="vertical-align:middle;">체험일</td>
										<td style="vertical-align:middle;"><%=od.getExpDate() %></td>
		                            </tr>
									<tr>	
										<td style="vertical-align:middle;">인원</td>
										<td style="vertical-align:middle;"><%=od.getProductCount() %></td>
									</tr>
									<tr>	
										<td style="vertical-align:middle;">상품 구매 금액</td>
										<td style="vertical-align:middle;"><%=od.getProductCount()* od.getProductPrice()%></td>
		                            	<%expTotalPrice += od.getProductCount()* od.getProductPrice(); %>
		                            	<%totalPrice += od.getProductCount()* od.getProductPrice(); %>
		                            </tr>
		                            <tr class="status">
		                            	<td>처리상태</td>
		                            	<td><%=od.getOrderStatus() %></td>
		                            	
		                            	<input type="hidden" id="detailOno" name="detailOno" value="<%=od.getOrderDetailNo()%>">
		                            	<%if(od.getOrderStatus()!=null && od.getOrderStatus().equals("입금대기")){%>
		                            		<td><button class="btn btn-info" value="주문취소">주문취소</button></td>
		                            		
		                            	<%}else if(od.getOrderStatus()!=null && od.getOrderStatus().equals("주문취소승인")) {%>
		                            		<td>
		                            			<p>주문취소승인되었습니다. 3일 이내로 등록해주신 환불계좌로 환불될 예정입니다. 감사합니다.</p>
		                            		</td>
		                            	<%}else if(od.getOrderStatus()!=null && od.getOrderStatus().equals("결제완료")) {%>
		                            		<td>
		                            			<p>결제 완료! 즐거운 체험되시길 바래요:)</p>
		                            		</td>
		                            	<%}else if(od.getOrderStatus()!=null && od.getOrderStatus().equals("구매확정")) {%>
		                            		<td>
		                            			<input type="hidden" name="review" value="<%=od.getProductNo() %>">
			                            		<button class="btn btn-info rv" value="리뷰작성">리뷰작성</button>
		                            		</td>
		                            	<%}else if(od.getOrderStatus()!=null && od.getOrderStatus().equals("리뷰작성")) {%>
			                            		<td>
				                    				<p>리뷰작성이 완료되었습니다.</p>
				                    			</td>
			                            <%} %>
		                           </tr>
		                       <%} %>
	                         <%} %>
                        	</tbody>  
        				</table>
        				
        			<!-- 결제 정보 -->
        			<table class="table table-bordered verticle">
        				<thead style="background-color: lightgray;">
       					  	<tr style="font-size: 25px; height: 80px;"  class="product-detail">
                               	<td colspan="4" width="250px">결제 정보</td>
                            </tr>
        				</thead>

                        <tbody>
                            <tr>
								<th>결제수단</th>
								<td><%=o.getPayName() %></td>
                            </tr>
							<tr>
								<th>총 결제 금액</th>	
								<!-- 모든 장인 홈들의 상품,체험,배송비 다 포함된 금액 -->
								<td class="areaTotal"><%=totalPrice+o.getTotalDel()%></td>
							</tr> 
							<tr>
								<th>배송비</th>
								<!-- 배송비만 알려주고 몇개의 홈에서 구매한 것인지 -->
								<td><%=o.getTotalDel() %></td>
							</tr>
                        </tbody>  
        			</table>
					<!-- 배송 정보 -->
        			<table class="table table-bordered verticle">
        				<thead style="background-color: lightgray;">
       					  	<tr style="font-size: 25px; height: 80px;"  class="product-detail">
                               	<td colspan="4" width="250px">배송 정보</td>
                            </tr>
        				</thead>

                        <tbody>
                            <tr>
								<th>수령자</th>
								<td><%=o.getReciverName() %></td>
                            </tr>
							<tr>
								<th>수령 주소</th>	
								<td><%=o.getOrderAddress() %></td>
							</tr> 
							<tr>
								<th>휴대전화</th>
								<td><%=o.getReciverPhone() %></td>
							</tr>
                        </tbody>  
        			</table>
        		</div>
         </div>
      </div>
      
      <script>
	     $(function(){
	   		$(document).on("click", ".status .btn", function () {
	              var detailOno = $(this).parent().siblings("input[type=hidden]").val();
	              //$(this) -> .btn이 있는 button 태그
	              //parent() -> a태그 바로 위에 있는 것 = <td>
	              //siblings("input[type=hidden]") -> <input>과 <td>는 지금 동일선상 그래서 sliblings
	              //val() -> 이거로 값 뽑아내기
	              
	              //input태그는 form태그 안이 아니더라도 스크립트에서 처리해주면 hidden으로 사용 가능하다.
	              //동적 이벤트에서 그 위치 찾아주면 된다.
	              
	              //console.log("detailOno : "+detailOno);
 	              //event.preventDefault();
	              
	              var detailOnoText = $(this).val();
	             
				  var productNo = $(this).siblings("input[type=hidden]").val();
				  //console.log("productNo :"+productNo);

				  var ono = <%=o.getOrderNo()%>;
	              
	              if(detailOnoText=="환불") {
	        		  location.href="<%=contextPath%>/refund.or?detailOno="+detailOno;
	        	  }else if(detailOnoText=="리뷰작성"){
		        		  location.href="<%=contextPath%>/review.or?productNo="+productNo+"&detailOnoText="+detailOnoText+"&detailOno="+detailOno;
				  }else if(detailOnoText=="입금대기"){
		              location.href="<%=contextPath%>/chstatus.or?detailOno="+detailOno+"&detailOnoText="+'결제완료'+"&ono="+ono;
	        	  }else{
		              location.href="<%=contextPath%>/chstatus.or?detailOno="+detailOno+"&detailOnoText="+detailOnoText+"&ono="+ono;
	        	  }
	   		});
    	});	
	     
	     /*
	     var i = 1;
	     while (i < 10) { // 변수 i가 10보다 작을 때만 while 문을 반복함.
	       document.write(i + "<br>");
	       i++; // 반복할 때마다 변수 i를 1씩 증가시켜 변수 i가 10보다 커지면 반복문을 종료함.
	     }
	     */
	 </script>
	 <script>
		 $(function(){
			 var pc = <%=productCount%>;
			 var ptp = <%=productTotalPrice%>;
			
			 var ec = <%=expCount%>;
			 var etp = <%=expTotalPrice%>;
			 var pstr = "상품체험 (총 "+pc+"개 / "+ptp+"원)";
			 var estr = "주문체험 (총 "+ec+"개 / "+etp+"원)";
			 
			 var productInfo = document.querySelector("#productInfo");
			 productInfo.innerHTML = pstr;
			 
			 var expInfo = document.querySelector("#expInfo");
			 expInfo.innerHTML = estr;
		 })
	
		 
     </script>
     
</body>
</html>