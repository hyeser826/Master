<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.text.*,java.util.ArrayList,com.semi.product.model.vo.*
	, com.semi.board.model.vo.Board
	, com.semi.review.model.vo.Review
    , com.semi.common.model.vo.PageInfo"%>
<%
	/*
 불러와야할 데이터 : 이미지, 체험상품정보, 리뷰, qna데이터 
*/

ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
Product p = (Product)request.getAttribute("p");

ArrayList<Board> qnalist = (ArrayList<Board>)request.getAttribute("list");
ArrayList<Review> reviewlist = (ArrayList<Review>)request.getAttribute("list");
PageInfo pi = (PageInfo)request.getAttribute("pi");

DecimalFormat df = new DecimalFormat("###,###");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>체험 상품 상세보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
/* div{
border: 1px solid black;
} */
.outer{
 background:white;
            color : black;
            width : 100%;
            margin : auto; /*가운데 정렬*/
           

}
.content-area {
	background: white;
	color: black;
	font-weight: bold;
	width: 80%;
	margin: auto; /*가운데 정렬*/
	margin-top: 50px;위로부터 /* 50px만큼 여백 */
	/* align-items: center; */
}

.inline-block {
	display: inline-block;
}

.innerline{
 position: relative;
}

.main, .info {
positon: absolute;
align-items: center;
	height: 500px;
}

.main-pic {
	float: left;
	width: 60%;
	height: 100%
}

.main-pic1 {
	height: 90%;
}

.main-pic1>img{
	width:650px; 
	height:450px;
}
.main-pic2 {
	height: 10%;
}

.info {
	float: left;
	width: 40%;
	height: 100%
}

.info1 {
	height: 90%;
}
.info1 a {
    color:black;
    text-decoration: none;
}


.info2 {
	height: 10%;
}

.info2>button {
	width: 200px;
}
.nav-tabs>li>a {
	color: gray;
}

.detail-area {
	height: 1000px;
}

.moveTopBtn {
	position: fixed;
	bottom: 1rem;
	right: 1rem;
	width: 4rem;
	height: 4rem;
	background: none;
	color: black;
	cursor: pointer;
}
</style>
</head>

<body>
<%@ include file="/views/common/menubar.jsp"%>
	
	<div class=outer>
	<div class="content-area">
		<!-- 페이지 맨위로 올려주는 버튼 영역  -->
		<div class="moveTopBtn" align="center">
			▲<br>top
		</div>
		<script>
	const topBtn = document.querySelector(".moveTopBtn");

	// 버튼 클릭 시 맨 위로 이동
	topBtn.onclick = () => {
	  window.scrollTo({ top: 0, behavior: "smooth" });  
	}
	</script>
		<div class="innerline">
			
			<form action="<%=contextPath%>/insertCart.ca" method="post">

				<div class="main">

					<div class="main-pic" align="center">

						<div class="main-pic1">
							<img class="img-fluid" id="titleImg" height="300px" src="<%=p.getTitleImg() %>" >
						</div>
						<hr>
						<div class="main-pic2">
							
						  <%if(!list.isEmpty()){ %>	
							<%for(int i=0;i<list.size();i++){ %>
							
							<img class="content-pic" id="contentImg<%=i %>" src="<%=list.get(i).getFilePath() %>"  width="50px" height="50px">
						
						<%} %>
					<%} %>
						</div>
					</div>
					<div class="info" align="center">
						<div class="info1">
						  <div class="materStore">
						  <input type="hidden" value="<%=p.getMstNo() %>">
							<br> <a href="<%=contextPath%>/masterStore.li?mno=<%=p.getMstNo() %>" id="master-home">
							   <h4>
							   <img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">&nbsp;&nbsp;<%=p.getStrName() %>
							   <img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;"></h4></a> <br>
							
							</div>
							<hr>
							<br>
							<h3><%=p.getProName() %></h3>
							<br>
							<table class="table order-table">
								<thead>
									<tr>
										<td><b>판매가</b></td>
										<td><%=df.format(p.getProPrice())%>원</td>
									</tr>
									<tr>
										<td><b>인원수</b></td>
										<td><input type="number" id="person" min="1" max="<%=p.getProStock() %>" value="1"
											required></td>
									</tr>
									<tr>
										<td><b>체험일</b></td>
										<td><input type="date" class="booking-date" max="<%=p.getExpPeriod() %>" required></td>
									</tr>
								</thead>

								<tbody>

									<tr>
										<td><b>총 상품금액 : </b></td>
										<td><label id="amount" align="center" style="font-size:20px"><%=df.format(p.getProPrice())%>원</label></td>

									</tr>

								</tbody>

							</table>
							
							<script>
							
							//체험날짜 최소범위를 오늘로 지정하는 구문
							 
						/* 	 $(function(){
								 var now = new Date();
								   

								    var year=now.getFullYear();//연도
								   

								    var month=now.getMonth()+1;//월
								    

								    var date=now.getDate();//일
								  

								   
								    var str = '\"'+year+"-"+month+"-"+date+'\"';
								     console.log(str);
								 $(".booking-date").attr("min",str);
							 })
								 */
								
							
    

 
							
							
							//썸네일 누르면 타이틀이미지 경로가 누른 썸네일의 경로가 되는 제이쿼리문
							$(".main-pic2").on("click","img",function(){
								
								$(".main-pic1>img").attr("src",$(this).attr("src"));
							});
							
							//총 주문금액 띄워주는 함수
						
								$('#person').on('click',function(){
									var amount = (<%=p.getProPrice() %>*$('#person').val()).toLocaleString("ko-KR");
								
									$('#amount').empty();
									$('#amount').append(amount+' 원');
									console.log(amount);
									
									
								}); 
								
						
		
							
							</script>

						</div>

						<div class="info2">
							<hr>
							<!--  <button type="button" class="btn btn-success" onclick="">찜하기♡</button>-->
							<button type="button" class="btn btn-secondary btn-lg" id="cartBtn">장바구니</button>
						
							<button type="button" class="btn btn-primary btn-lg" id="orderBtn">구매하기</button>
						</div>
						
					</div>

				</div>
				
				<script>

					//장바구니 버튼 누르면 실행될 함수
					$(".info2").on("click","#cartBtn",function(){
						$.ajax({
							url: 'insertCart.ca',
							type: 'POST',
							data: {
						categoryName : "0.체험",
						pno : <%=p.getProNo()%>,
					    productPrice: <%=p.getProPrice() %>,
    				    mno : <%=p.getMstNo() %>,
					    eDate: $(".booking-date").val(),
					    quantity : $("#person").val(),
					    totalPrice: <%=p.getProPrice()%>*$('#person').val()
								
							},
							success: function(result){
								
							  console.log(result);
							  cartAlert(result);
							}
						});
						
						function cartAlert(result){
							if(result == '0'){
								alert("장바구니에 추가를 하지 못하였습니다.");
							} else if(result == '1'){
								alert("장바구니에 추가되었습니다.");
							} else if(result == '5'){
								alert("로그인이 필요합니다.");	
							}
						}
						
					});
					
					//구매하기 버튼 누르면 실행될 함수
					$(".info2").on("click","#orderBtn",function(){
						$.ajax({
							url: 'odpmt2.one',
							type: 'POST',
							data: {
						pno : <%=p.getProNo()%>,
						productName: "<%=p.getProName() %>",
						strName : "<%=p.getStrName() %>", 
						productPrice: <%=p.getProPrice() %>,
					    eDate: $(".booking-date").val(),
					    quantity : $("#person").val()
					    								
							},
							success: function(){
								
								
							 
							}
						});
						
						
					});
					
					
					</script>
			</form>
			<br clear="both"> <br>
			<hr>
			<div class="detail-area" align="center">

				<div class="container">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#home">상세정보</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#menu1">리뷰</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#menu2">상품문의</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#menu3">교환/반품/배송 정보</a></li>

					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div id="home" class="container tab-pane active">
							<br>
							<h3>상세정보</h3>
							<p><%=p.getProDescription() %></p>
						</div>
						<div id="menu1" class="container tab-pane fade review-area">
							<br>
							<h3>고객리뷰</h3>
							<table class="table table-hover review-table">
								<thead>
									<tr>
										<th>글번호</th>
										<th>리뷰내용</th>
										<th>작성자</th>
										<th>게시일</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div id="menu2" class="container tab-pane fade qna-area">
							<br>
							<h3>상품문의</h3>
							<table class="table table-hover qna-table">
								<thead>
									<tr>
										<th>글번호</th>
										<th>문의유형</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
									</tr>
								</thead>
								<tbody>
								
								
								</tbody>
							</table>
							<%
									if (loginMem != null) {
								%>
								<button class="btn btn-info" id="pQnaInsert" data-toggle="modal"
									data-target="#addQna">글 작성</button>
								<%
									}
								%>
							
							
<!-- qna 게시판 상세보기 모달  -->
   <div class="modal fade" id="readQna">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">상품 문의</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        <br>
        <table class="qna-detail" border="1">   
          <tbody>
         
          </tbody>
        </table>
        
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
         <!-- <button type="button" class="btn btn-secondary">수정</button>
          <button type="button" class="btn btn-info">삭제</button> -->
          <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
        </div>
        
      </div>
    </div>
  </div>
					 
  
						</div>
						
						<script>

						$(function(){
				        	  selectProductQna();
				        	  selectProductReview();
				        	  /*  setInterval(selectProductQna,1000);*/
				          })
				          
				          //동적으로 생성된 요소에 이벤트를 적용하고싶다면 상위요소선택자, 하위요소 선택자를 구별하는 이벤트 구문으 사용
         
				   
		//리뷰 리스트 조회해오는 에이젝스
		function selectProductReview(){
      	  $.ajax({
      		
      		  url: "productReviewList.re",
      		  data:{
      			  
      			  pno : <%=p.getProNo()%>
      		  },
      		  success: function(list){
      			  console.log(list);
      			 var str = "";
  					  for(var i in list){
  						 str += "<tr>"
  						 +"<td>"+list[i].productBoardNo+"</td>"
  						 +"<td>"+list[i].productBoardContent+"</td>"
  						 +"<td>"+list[i].memId+"</td>"
  						 +"<td>"+list[i].productBoardDate+"</td>"
  						 +"</tr>"
  					  }
  					  $(".review-table tbody").html(str);
      			  
      		  },
      		  error: function(){
      			  console.log("통신실패");
      		  }
      		  
      	  })
        };
				          
				          

        //상품 qna 리스트 불러오는 에이젝스
        function selectProductQna(){
      	  $.ajax({
      		
      		  url: "productQnAList.qna",
      		  data:{
      			  
      			  pno : <%=p.getProNo()%>
      		  },
      		  success: function(list){
      			  console.log(list);
      			 var str = "";
  					  for(var i in list){
  						 str += "<tr data-toggle=\"modal\" data-target=\"#readQna\">"
  						 +"<td>"+list[i].boardNo+"</td>"
  						 +"<td>"+list[i].boardCategory+"</td>"
  						 +"<td>"+list[i].boardTitle+"</td>"
  						 +"<td>"+list[i].memId+"</td>"
  						 +"<td>"+list[i].boardDate+"</td>"
  						 +"</tr>"
  					  }
  					  $(".qna-table tbody").html(str);
      			  
      		  },
      		  error: function(){
      			  console.log("통신실패");
      		  }
      		  
      	  })
        };
        
        
        
        //게시글 상세보기 모달창 띄우기
        $(".qna-table").on("click","tbody>tr",function(){
   
  
$.ajax({

  url: "readQna.qna",
  data:{
	  
	  bno : $(this).children().eq(0).text()
	
  },
  success: function(b){
	  console.log(b);
	  var str = "<tr data-toggle=\"modal\" data-target=\"#readQna\">"+
     "<td width=\"100px\">문의유형</td>"+
     "<td width=\"100px\" id=\"qnaType\">"+b.boardCategory+"</td>"+
     "<td width=\"300px\">작성자</td>"+
     "<td width=\"200px\" id=\"qnaWriter\">"+b.memId+"</td>"+
     "<td width=\"200px\">작성일</td>"+
     "<td width=\"400px\" id=\"qnaDate\">"+b.boardDate+"</td>"+
     
   "</tr>"+
   "<tr>"+
    "<td colspan=\"6\"><p width=\"500px\">제목 : "+b.boardTitle+"</p></td>"+
   "</tr>"+
   "<tr>"+
     "<td id=\"qnaContent\" colspan=\"6\" style=\"width:400px; height:300px;\"><p>"+b.boardContent+"</p></td>"+
   "</tr>";
		  
    $(".qna-detail tbody").empty();
		  $(".qna-detail tbody").html(str);
		  console.log(str);
	  
  },
  error: function(){
	  console.log("통신실패");
  }
  
});





}); 
       
      
 
   
						
						</script>
						
						
						<div id="menu3" class="container tab-pane fade">
							<br><br><br>
							<img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">
							<br><br>
							<h3>교환 / 반품 정보</h3>
							<br>
							<table class="table">
								
									<tr>
										<th>반품/교환 배송비</th>
										<td>(구매자귀책) 3,000원/6,000원 초기배송비 무료시 반품배송비 부과방법 : 왕복(편도x2)</td>
									</tr>
									<tr>	
										<th>반품/교환지 주소</th>
										<td>장인 스토어별 상이(판매자에게 문의하세요.)</td>
									</tr>	
									<tr>
										<th>반품/교환 안내</th>
										<td>장인 고객센터 1588-4989 </td>
									</tr>
								
							</table>
							<br><br><br>
							<img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">
							<br><br>
							<h3>반품/교환 기준</h3>
							<br>
							<hr>
							
<p>상품 수령 후 7일 이내에 신청하실 수 있습니다. 단, 제품이 표시·광고 내용과 다르거나, 계약과 다르게 이행된 경우는 제품 수령일부터 3개월 이내, 
그 사실을 안 날 또는 알 수 있었던 날부터 30일 이내에 교환/반품이 가능합니다.<br>
<br>
추가적으로 다음의 경우 해당하는 반품/교환은 신청이 불가능할 수 있습니다.<br>
소비자의 책임 있는 사유로 상품 등이 멸실 또는 훼손된 경우 (단지, 상품 확인을 위한 포장 훼손 제외)<br>
소비자의 사용 또는 소비에 의해 상품 등의 가치가 현저히 감소한 경우<br>
시간의 경과에 의해 재판매가 곤란할 정도로 상품 등의 가치가 현저히 감소한 경우<br>
복제가 가능한 상품 등의 포장을 훼손한 경우<br>
소비자의 주문에 따라 개별적으로 생산되는 상품이 제작에 들어간 경우</p>
<br><br><br>
<img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">
							<br><br>
							<h3>구매시 주의사항</h3>
							<br>
							<hr>
							<p>
							
「전자상거래 등에서의 소비자보호에 관한 법률」에 의한 반품규정이 판매자가 지정한 반품조건보다 우선합니다.<br>
미성년자가 물품을 구매하는 경우, 법정대리인이 동의하지 않으면 미성년자 본인 또는 법정대리인이 구매를 취소할 수 있습니다.<br>
공산품, 전기용품 등 인증대상 상품을 구매하실 경우 '전기용품 및 생활용품 안전관리법' 등 관련 법률에 따라 허가 받은 상품인지 확인하시기 바랍니다.<br>
장인의 결제시스템을 이용하지 않고 판매자와 직접거래 하실 경우 상품을 받지 못하거나. 구매한 상품과 상이한 상품을 받는 등 피해가 발생 할 수 있으니 유의하시기 바랍니다.<br>
등록된 판매물품과 내용은 판매자가 등록한 것으로 장인(주)가 운영하는 장인에 등록된 내용에 대하여 일체의 책임을 지지 않습니다.<br>
							</p>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

						</div>

					</div>
				</div>

			</div>

		</div>

	</div>
	</div>
	<script>
		
	</script>
</body>
</html>