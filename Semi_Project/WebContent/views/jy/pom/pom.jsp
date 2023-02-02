<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member
    						,com.semi.board.model.vo.Board
    						,com.semi.JY.order.model.vo.Order
    						,com.semi.JY.orderdetail.model.vo.OrderDetail,
    						java.util.ArrayList" %>

<%
		@SuppressWarnings("unchecked")
		ArrayList<OrderDetail> list =(ArrayList<OrderDetail>)request.getAttribute("list");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
 
	<!-- 아래는 호명가에서 새로 추가한거  -->
    <script type="text/javascript" src="/data/skin/front/story_g/js/gd_gettext.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/jquery.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/underscore/underscore-min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/validation/jquery.validate.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/validation/additional-methods.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/numeral/numeral.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/global/accounting.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/global/money.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/chosen/chosen.jquery.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/placeholder/placeholders.jquery.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/copyclipboard/clipboard.min.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery/vticker/jquery.vticker.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/gd_ui.js?ts=1525753067"></script>
<script type="text/javascript" src="/data/skin/front/story_g/js/jquery.bxslider.min.js?ts=1533805056"></script>
<script src="//ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat%7Csource-han-serif-korean%7CNoto+Sans+KR">
 
 <!--  아래는  구글링해서 찾은 파일들  -->


  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
 
 
 
 
    <title>화면 구조 잡기 기본 </title>

    <style>
    /* 바로아래는 혜진님꺼 */
    .outer{
        width:100%;
        margin:auto; /*가운데 정렬*/
        margin-top:50px; /*위로부터 50px만큼 여백*/
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
    
    
    </head>
    <body>
   <%@include file="/views/common/menubar.jsp" %>
    
    
    <!-- 
             Member loginMem = (Member)session.getAttribute("loginMem");
    
     -->
        <%
            String userId = loginMem.getMemId();
            String userName=loginMem.getMemName();
            String email = loginMem.getMemEmail();
            String phone =(loginMem.getMemPhone()==null)?"":loginMem.getMemPhone();
            String address=loginMem.getMemAddress();
            String grade = loginMem.getGrade();
        %>
       
        <div class ="outer">
    		<div id="left-side">
    			<%@include file="/views/common/leftbar2.jsp" %>
    		</div>
            <div id="right-side">
                <div id="content2">
                    <div class="container">
                        <h2>주문조회</h2>

                        <div class="container">
                            <table class="table table-bordered">
                       
        <!-- search -->
        <table class="searchBox">
            <caption>조회</caption>
            <colgroup>
                <col width="123px">
                <col width="*">
            </colgroup>
            <tbody>
                <div class="right">

				<div class="section">
					<div class="section-header">
						
					</div>
					<div class="section-body">
						<form method="get" name="frmDateSearch">
							<input type="hidden" name="memNo" value="cancel">
							<fieldset>
								

								<div class="date-check">
									<h3 class="h3">
										조회기간
									</h3>
									<div class="check-option clear">
										<div class="check-option-inner" data-target-name="wDate[]">
											<div class="btn active"><button class="inner" type="button" data-value="7">7일</button></div>
											<div class="btn"><button class="inner" type="button" data-value="30">1개월</button></div>
											<div class="btn"><button class="inner" type="button" data-value="90">3개월</button></div>
											<div class="btn"><button class="inner" type="button" data-value="365">1년</button></div>
										</div>
										<div id="layer" class="layer"></div>
									</div>
									<div class="check-cal">
										<div class="cal-from">
											<input type="date" name="wDate[]" value="2022-11-07" id="picker2" class="js-datepicker" />
										</div>
										<div class="divide">~</div>
										<div class="cal-to">
											<input type="date" name="wDate[]" value="2022-11-07" class="js-datepicker" />
										</div>
									</div>
                                    <br>
									<div class="submit-block">
											<!--아래꺼 원래 클래스 class="skinbtn point2 cl-find" --> 
										<button id="bbttnn" type="submit" class="btn btn-secondary" ><em>조회 <img alt="" src="https://cdn-pro-web-240-197-godomall.spdycdn.net/hojeontr0476_godomall_com/data/skin/front/story_g/img/etc/search.png" /></em></button>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<div class="section section2">
				
					<div class="section-body">
						<!-- 주문상품 리스트 -->
						<div class="table1 type1">
    <table class="goods-board">
        <colgroup>
            <col style="width:150px"/>
            <col/>
            <col style="width:154px"/>
            <col style="width:120px"/>
            <col style="width:137px"/>
        </colgroup>
        <thead>
      
        </thead>
        
    </table>
</div>
           
     
     
                      
        <table class="table table-hover">
             <ul class="nav nav-tabs" role="tablist">
	    <li class="nav-item">
	      <a class="nav-link active" data-toggle="tab" href="#home">입금대기</a> 	<!-- 이 안에 상품등록도 할 수 있도록 버튼만들기 -->
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu2">결제완료</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu3">상품준비중</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" data-toggle="tab" href="#menu4">배송완료</a>
	    </li>
	   
	  </ul>
	  <!-- 
			  아래는  입금대기 ,아래는  입금대기 ,아래는  입금대기
			  아래는  입금대기 ,아래는  입금대기 ,아래는  입금대기
			  아래는  입금대기 ,아래는  입금대기 ,아래는  입금대기 누르면 결제완료
			  
				   -->
            <thead>
            <tr>
              <th>주문상세번호</th>
              <th>상품번호</th>
              <th>진행상태</th>
              <th>확인</th>
            </tr>
          </thead>
   
          <tbody id="searchPom">
         	<%if(list.isEmpty()) {%>
		               <tr>
         				   <td colspan="4" class="no-data2" >
         						비어있음.!
         				   </td>
   					  </tr>
   					  
		            <%}else {%>													
	            		<%for(OrderDetail i : list) {%>
	            			 <%if(i.getOrderStatus().equals("입금대기")){ %>
	         				 <tr >
         							 <td><%=i.getOrderDetailNo() %></td>
					                 <td><%=i.getProductNo() %></td>
					                 <td id="orderApprove" >
					                  	  <input type="hidden" name="odStatus" value="<%=i.getOrderStatus() %>"><%=i.getOrderStatus() %></td>
						           
									 <td>
									    <div class="row">
									        <div class="col-lg-6">
      								    <form action="orderApprove.me" >
									        <input type="submit"  class="btn btn-primary" id="orderApprove" value="입금승인"><br><br>
											<input type="hidden" name="orderApprove" value="<%=i.getOrderDetailNo() %>">
										  </form>				
														<button type="submit" value="주문거절" class="btn btn-danger" id="refuseOrder" onclick="refuseOrder();" > 주문거절</button>
												
											</div>
									   </div>
   									</td>
							  
   					  			</tr>
	          				  <%} %>
	          			  <%} %>
	          		  <%} %>
		        
            
            </tbody>
        </table>
        <!--여기 아래는 결제완료    -->
           <!--여기 아래는 결제완료    -->
              <!--여기 아래는 결제완료    -->
                 <!--여기 아래는 결제완료    -->
    <div id="menu2" class="container tab-pane fade"><br>  
       
		<table class="table table-hover" align="center">
              
		   
          <tbody id="searchPom">
         		<%if(list.isEmpty()) {%>
		               <tr>
         				   <td colspan="4" class="no-data2" >
         						
         				   </td>
   					  </tr>
   					  
		            <%}else {%>													<!-- 여기는 결제완료 상태 -->
	            		<%for(OrderDetail i : list) {%>
	            			
	         				 <%if(i.getOrderStatus().equals("결제완료")){ %>
	         				 <tr >
         							 <td><%=i.getOrderDetailNo() %></td>
					                 <td><%=i.getProductNo() %></td>
					                 <td id="completePay" >
					                 	<input type="hidden"  name="completePay" value="<%=i.getOrderStatus() %>"><%=i.getOrderStatus() %>
					                
					                 	<!-- ajax에서 처리 -->
					                 </td>
									 <td>
									    <div class="row">
									        <div class="col-lg-6">
									      	  <form action="completePay.me" >
									    		    <input type="submit"  class="btn btn-primary" id="completePay" value="주문승인"><br><br>
													<input type="hidden" name="completePay" value="<%=i.getOrderDetailNo() %>">
											  </form>
														<button type="submit" value="주문거절" class="btn btn-danger" id="refuseOrder" onclick="refuseOrder();" > 주문거절</button>
										
											</div>
									   </div>
   									</td>
							  
   					  			</tr>
	          				  <%} %>
	          			  <%} %>
	          		  <%} %>
             
            </tbody>
        </table>      	
      </div>
      <!--  결제완료  메뉴2 끝 -->
           <!--여기 아래는 상품준비중    -->
           <!--여기 아래는 상품준비중    -->
              <!--여기 아래는 상품준비중    -->
                 <!--여기 아래는 상품준비중    -->
   <div id="menu3" class="container tab-pane fade"><br>  
       <h3>상품준비중</h3>
		<table class="table table-hover" align="center">
           <tbody id="searchPom">
         		<%if(list.isEmpty()) {%>
		               <tr>
         				   <td colspan="4" class="no-data3">
         				   
         				   </td>
   					  </tr>
		            <%}else {%>
	            		<%for(OrderDetail i : list) {%>
	         				 <%if(i.getOrderStatus().equals("상품준비중")){ %>
	         				 <tr>
         							 <td><%=i.getOrderDetailNo() %></td>
					                 <td><%=i.getProductNo() %></td>
					                 <td><input type="hidden" name="" value="<%=i.getOrderStatus() %>"><%=i.getOrderStatus() %></td>
									 <td>
									    <div class="row">
									        <div class="col-lg-6">
													<form action="<%=contextPath %>/invoiceEnrollForm.en" >
									    		 	    <input type="submit"  class="btn btn-primary" id="productReady" value="송장등록 "><br><br>
														<input type="hidden" name="productReady" value="<%=i.getOrderDetailNo() %>">
												 	 </form>
														<button type="submit" value="<%=i.getOrderDetailNo() %>" class="btn btn-danger" id="refuseOrder" onclick="refuseOrder();" > 주문거절</button>
											</div>
									   </div>
   									</td>
							  </tr>
   					  
	          				  <%} %>
	          			  <%} %>
	          		  <%} %>
             
            </tbody>
        </table>      	
      </div>
      <!--  상품준비중  메뉴2 끝 -->
            <!--여기 아래는 배송완료    -->
           <!--여기 아래는 배송완료    -->
              <!--여기 아래는 배송완료    -->
                 <!--여기 아래는 배송완료   -->
     <div id="menu4" class="container tab-pane fade"><br>  
       <h3>배송완료</h3>
		<table class="table table-hover" align="center">
            <tbody id="searchPom">
        		<%if(list.isEmpty()) {%>
		               <tr>
         				   <td colspan="4" class="no-data4">
         				   
         				   </td>
   					  </tr>
		            <%}else {%>
	            		<%for(OrderDetail i : list) {%>
	         				 <%if(i.getOrderStatus().equals("배송완료")){ %>
	         				 <tr>
         							 <td><%=i.getOrderDetailNo() %></td>
					                 <td><%=i.getProductNo() %></td>
					                 <td><input type="hidden" name="" value="<%=i.getOrderStatus() %>"><%=i.getOrderStatus() %></td>
									 <td>
									    <div class="row">
									        <div class="col-lg-6">
													
											</div>
									   </div>
   									</td>
							  </tr>
   					  
	          				  <%} %>
	          			  <%} %>
	          		  <%} %>
             
            </tbody>
        </table>      	
      </div>
      <!--  배송완료  메뉴4 끝 -->
              		  </div>
         	                </div>
			              		  <div id="content3">
			              
			                </div>
							</div>
									<div id="footer">
							</div>
					</div>
 	<script>
 		//아래는 입금대기에서 결제완료으로 바뀌는 버튼 인데 수정필요할듯
 		function orderApprove() {
 			var odStatus = $("input[name=odStatus]").val();
	
 			$.ajax({
                url : "orderApprove.me",
                data : {
                	odStatus:odStatus
                },
                success: function(list){
                	console.log("통신성공");
                	if(!list.isEmpty()){
                		$("#test1").load("${contextPath}/list #test1")
				 		$("#test1").html("결제완료");
                	console.log("결제 완료");
                	
                	}else{
                		alert("변경을 실패하였습니다.")
                	}
                },
                error : function(){
                	console.log("통신실패");
                }
            });
		}
 		
 		//아래는결제완료에서 상품준비중//
 		//아래는결제완료에서 상품준비중//
 		//아래는결제완료에서 상품준비중//
 		//아래는결제완료에서 상품준비중//
 		
 		function completePay() {
 			
 			$.ajax({
                url : "completePay.me",
             
                success: function(list){
                	console.log("통신성공");
                	if(!list.isEmpty()){
                 	
                	console.log("상품준비중으로 변경 완료");
                	}else{
                		alert("변경을 실패하였습니다.")
                	}
                
                },
                error : function(){
                	console.log("통신실패");
                }
            });
 		}
 			
 			//아래는 상품준비중에서 송장등록버튼눌렀을때 
 			//아래는 상품준비중에서 송장등록버튼눌렀을때 
 			//아래는 상품준비중에서 송장등록버튼눌렀을때 
 			function productReady() {
 	 			$.ajax({
 	                url : "invoiceList.en",
 	                success: function(list){
 	                	console.log("통신성공");
 	                	if(!list.isEmpty()){
 	                	console.log("배송완료으로 변경 완료");
 	                	}else{
 	                		alert("변경을 실패하였습니다.")
 	                	}
 	                 },
 	                error : function(){
 	                	console.log("통신실패");
 	                }
 	            });
 	 				
 			}
 			
 			
 			//입금 대기 상태에서 주문거절  
 			//입금 대기 상태에서 주문거절  
 			//입금 대기 상태에서 주문거절  
 			function refuseOrder(){
 				$.ajax({
 	                url : "refuseOrder.en",
 	                
 	                success: function(list){
 	                	console.log("통신성공");
 	                	if(!list.isEmpty()){
 	                	console.log("거절로 변경 완료");
 	                	
 	                	}else{
 	                		alert("변경을 실패하였습니다.")
 	                	}
 	                 },
 	                error : function(){
 	                	console.log("통신실패");
 	                }
 	            });
 			}
 			
 		
		
	//아래는 달력//
	//아래는 달력//
	//아래는 달력//
	//아래는 달력//
	//아래는 달력//
	//아래는 달력//
	
	if ($('.check-option-inner').length) {
        $('.check-option-inner button.inner').click(function (e) {
            $startDate = $endDate = '';
            $period = $(this).data('value');
            $elements = $('input[name="' + $(this).closest('.check-option-inner').data('target-name') + '"]');
            $format = $($elements[0]).data('DateTimePicker').format();
            if ($period >= 0) {
                $startDate = moment().hours(0).minutes(0).seconds(0).subtract($period, 'days').format($format);
                $endDate = moment().hours(0).minutes(0).seconds(0).format($format);
            }
            $($elements[0]).val($startDate);
            $($elements[1]).val($endDate);

            $('.check-option-inner .btn').removeClass('active');
            $(this).parents('.btn').addClass('active');
        });

        // 선택된 버튼에 따른 값 초기화
        $elements = $('input[name*=\'' + $('.check-option-inner').data('target-name') + '\']');
        if ($elements.length && $elements.val() != '') {
            $interval = moment($($elements[1]).val()).diff(moment($($elements[0]).val()), 'days');
            $('.check-option-inner').find('button[data-value="' + $interval + '"]').trigger('click');
        } else {
            $('.check-option-inner').find('button[data-value="-1"]').trigger('click');
        }
	 }

</script>
  </body>
    </html>