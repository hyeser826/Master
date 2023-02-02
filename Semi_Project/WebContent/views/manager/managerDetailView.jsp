<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member,
    							 com.semi.manager.master.model.vo.Master,
    							 com.semi.manager.review.model.vo.Review,
    							 com.semi.manager.orderdetail.model.vo.OrderDetail,
    							 com.semi.board.model.vo.Board,
    							 java.util.ArrayList" %>
<%
	Member m = (Member)request.getAttribute("m");
	Master mr = (Master)request.getAttribute("mr");
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	ArrayList<OrderDetail> orderList = (ArrayList<OrderDetail>)request.getAttribute("orderList");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>managerDetail</title>
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
    .titleQna:hover{
    	background-color: lightgrey;
    	cursor: pointer;
    }
    #qnaTable input{
    	width: 50%;
    	border: none;
    }
    #qnaTable textarea{
    	width: 95%;
    }
	.custom-btn {
	  width: 100px;
	  height: 30px;
	  border-radius: 5px;
	  padding: 5px 10px;
	  font-family: 'Lato', sans-serif;
	  font-weight: 500;
	  background: transparent;
	  cursor: pointer;
	  transition: all 0.3s ease;
	  position: relative;
	  display: inline-block;
	   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
	   7px 7px 20px 0px rgba(0,0,0,.1),
	   4px 4px 5px 0px rgba(0,0,0,.1);
	  outline: none;
	  margin-left:20px;
	}
	.btn-enroll {
	  width: 90px;
	  font-size: 13px;
	  border: none;
	  background: rgb(255,171,50);
	    background: linear-gradient(0deg, rgba(255,171,50,1) 0%, rgba(237,217,194,1) 100%);
	    color: grey;
	    overflow: hidden;
	}
	.btn-enroll:hover {
	    text-decoration: none;
	    color: black;
	}
	.btn-enroll:before {
	    position: absolute;
	    content: '';
	    display: inline-block;
	    top: -180px;
	    left: 0;
	    width: 30px;
	    height: 100%;
	    background-color: #fff;
	    animation: shiny-btn1 3s ease-in-out infinite;
	}
	.btn-enroll:hover{
	  opacity: .7;
	}
	.btn-enroll:active{
	  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.3),
	              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
	    inset -4px -4px 6px 0 rgba(255,255,255,.2),
	    inset 4px 4px 6px 0 rgba(0, 0, 0, .2);
	}
	@-webkit-keyframes shiny-btn1 {
	    0% { -webkit-transform: scale(0) rotate(45deg); opacity: 0; }
	    80% { -webkit-transform: scale(0) rotate(45deg); opacity: 0.5; }
	    81% { -webkit-transform: scale(4) rotate(45deg); opacity: 1; }
	    100% { -webkit-transform: scale(50) rotate(45deg); opacity: 0; }
	}
	
	.btn-mst {
	  width: 108px;
	  font-size: 13px;
	  color: #fff;
	  background: rgb(255,151,0);
	  border: none;
	  z-index: 1;
	}
	.btn-mst:after {
	  position: absolute;
	  content: "";
	  width: 100%;
	  height: 0;
	  top: 0;
	  left: 0;
	  z-index: -1;
	  border-radius: 5px;
	  background-color: #eaf818;
	  background-image: linear-gradient(315deg, #eaf818 0%, #f6fc9c 74%);
	   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
	   7px 7px 20px 0px rgba(0,0,0,.1),
	   4px 4px 5px 0px rgba(0,0,0,.1);
	  transition: all 0.3s ease;
	}
	.btn-mst:hover {
	  color: #292929;
	}
	.btn-mst:hover:after {
	  top: auto;
	  bottom: 0;
	  height: 100%;
	}
	.btn-mst:active {
	  top: 2px;
	}
	.btn-delete {
	  width: 108px;
	  font-size: 13px;
	  color: white;
	  border: none;
	  background: rgb(255,27,0);
	background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
	}
	.btn-delete2 {
	  width: 50px;
	  margin: 0px;
	  font-size: 13px;
	  color: white;
	  border: none;
	  background: rgb(255,27,0);
	background: linear-gradient(0deg, rgba(255,27,0,1) 0%, rgba(251,75,2,1) 100%);
	}
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
    <div class="outer">
    	<div class="header" align="center">
	        <p style="font-size: 35px; font-weight:bold;">회원 관리</p>
	        <br><br>
	    </div>
	    
        <div id="left-side" align="center">
            <ol id="manager-menu" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/manager.ma?currentPage=1">관리자 페이지</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath%>/manager.ma?currentPage=1">회원 관리</a></li>
                <li><a href="<%=contextPath %>/manageOrder.ma?currentPage=1">주문 관리</a></li>
            </ol>
        </div>
        
        <div id="right-side">
	    	<h2 align="center">회원 상세정보</h2>
	        <form action="<%=contextPath%>/update.ma" id="update-form" method="post">
	            <br>
	            <table class="table table-bordered" border="1">
	            	<tr>
	                	<th>회원 번호</th>
	                	<td><%=m.getMemNo()%></td>
	                	<input type="hidden" name="memNo" value="<%=m.getMemNo()%>">
	                </tr>
	                <tr>
	                	<th>이름</th>
	                	<td><%=m.getMemName()%></td>
	                </tr>
	                <tr>
	                	<th>아이디</th>
	                	<td><%=m.getMemId()%></td>
	                </tr>
	                <tr>
	                	<th>전화번호</th>
	                	<td><%=m.getMemPhone()%></td>
	                </tr>
	                <tr>
	                	<th>이메일</th>
	                	<td><%=m.getMemEmail()%></td>
	                </tr>
	                <tr>
	                	<th>주소</th>
	                	<td><%=m.getMemAddress()%></td>
	                </tr>
	                <tr>
	                	<th>회원 등급</th>
	                	<td class="apBtn"><%=m.getGrade()%>
			                <%if(mr!=null){ %>
			                	<button type="button" onclick="location.href='<%=contextPath%>/masterDetail.ma?mno=<%=m.getMemNo() %>'" class="custom-btn btn-mst">장인 정보 조회</button>
			                	<%if(m.getGrade().equals("장인")){ %>
			                	<button type="button" onclick="location.href='<%=contextPath%>/downGrade.ma?mno=<%=mr.getMemNo() %>'" class="custom-btn btn-delete">장인 등급 박탈</button>
			                	<%} %>
			                <%} %>
	                	</td>
	                </tr>
	                <tr>
	                	<th>회원 가입일</th>
	                	<td><%=m.getEnrolldate()%></td>
	                </tr>
	                <tr>
	                	<th>회원 상태</th>
	                	<td><%=m.getEnrollflag()%></td>
	                </tr>
	                <tr class="flag-area">
	                	<th>탈퇴일</th>
	                	<td><%=m.getDeletedate() %></td>
	                </tr>
	            </table>
	            <br>
	        </form>
	        
	        <script>
	        	//입점 요청 버튼 클릭 시 1:1 문의내역 중 입점문의 게시글 show
	        	function acceptMaster(){
	        		var boardCate = $(".boardCate");
	        		$.each(boardCate,function(){
	        			if(this.innerText=="입점문의"){
	        				$(".titleQna").get(0).scrollIntoView({behavior: "smooth"});
	        				$(this).parents("#qnaTable").find(".titleQna").siblings().show(500);
	        			}
	        		})
	        	}
	        	//현재 Y회원은 탈퇴일 영역 hide / 현재 N회원은 등급 변경 버튼 disabled, 회원 탈퇴 버튼 hide
	        	$(function(){
	        		var flag = "<%=m.getEnrollflag()%>";
	        		if(flag=="Y"){
	        			$(".flag-area").hide();
	        		}else{
	        			$("input[name=grade]").attr("disabled",true);
	        			$(".onlyEnroll").hide();
	        		}
	        	})
	        	//장인 등급 박탈 확인 질문
	        	function downChk(){
	        		var result = confirm("해당 회원의 장인 등급을 박탈하시겠습니까?");
	        		return result;
	        	}
	        	//회원 탈퇴 확인 질문
	        	function chk(){
	        		var result = confirm("해당 회원을 탈퇴시키시겠습니까?");
	        		return result;
	        	}
	        	//조건에 부합하면 회원 등급란에 입점요청 버튼 생성
	        	$(function(){
	        		var grade = "<%=m.getGrade()%>";
	        		if(grade=="일반" && !<%=boardList.isEmpty()%>){//현재 일반 회원이고, 1:1문의내역 있고,
	        			var boardCate = $(".boardCate");
						//입점문의 게시글이 있으면서 미답변 상태라면 입점요청 버튼 보여주기
						$.each(boardCate,function(){
							var boardStat = $(this).siblings(".boardSta").get(0).innerHTML;
							if(this.innerText=="입점문의" && boardStat=="미답변"){
								$('.apBtn').append('<button type="button" class="custom-btn btn-enroll" onclick="acceptMaster();">입점 요청<div class="dot"></div></button>');
							}
						})
	        		}
				});
	        </script>
	        
	        <hr>
	        <br>
	        <h2 align="center">1:1 문의내역</h2>
	        <a href="allBoard.ma?currentPage=1&memNo=<%=m.getMemNo()%>" style="float:right">전체보기&raquo;</a>
	        <%if(boardList.isEmpty()){ %>
	        <table id="qnaTable" class="table" border="1" cellpadding="5">
	        	<tr>
			        <th width="100px">작성일</th>
			        <th width="100px">카테고리</th>
			        <th width="100px">상태</th>
			    </tr>
		       	<tr>
		       		<td colspan="3">해당 회원의 1:1 문의내역이 없습니다.</td>
		       	</tr>
		    </table>
	        <%}else{ %>
	           	<%for(Board b : boardList){%>
	           	<form method="post">
		           	<input type="hidden" name="memNo" value="<%=m.getMemNo()%>">
		           	<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>">
			        <table id="qnaTable" class="table" border="1" cellpadding="5">
			           	<tr class="titleQna">
			           		<th width="110px">작성일</th>
			           		<td width="100px"><%=b.getBoardDate()%></td>
			           		<th width="100px">카테고리</th>
			           		<td width="100px" class="boardCate"><%=b.getBoardCategory()%></td>
			           		<th width="100px">상태</th>
			           		<td width="100px" class="boardSta"><%=b.getStatus()%></td>
			           	</tr>
			           	<tr>
			           		<th>제목</th>
			           		<td colspan="5"><%=b.getBoardTitle()%></td>
			           	</tr>
			           	<tr>
			           		<th>내용</th>
			           		<td colspan="5"><%=b.getBoardContent()%></td>
			           	</tr>
			           	<%if(b.getBoardCategory().equals("입점문의")){ %>
			           	<tr>
			           		<th>법인명</th>
			           		<td colspan="5"><input type="text" name="coName" value="<%=b.getCoName()%>"></td>
			           	</tr>
			           	<tr>
			           		<th>사업자등록번호</th>
			           		<td colspan="5"><input type="text" name="coNumber" value="<%=b.getCoNumber()%>"></td>
			           	</tr>
			           	<tr>
			           		<th>대표자</th>
			           		<td colspan="5"><input type="text" name="ceo" value="<%=b.getCeo()%>"></td>
			           	</tr>
			           	<tr>
			           		<th>업태명</th>
			           		<td colspan="5"><input type="text" name="coKind" value="<%=b.getCoKind()%>"></td>
			           	</tr>
			           	<tr>
			           		<th>장인홈</th>
			           		<td colspan="5"><input type="text" name="strName" value="<%=b.getStrName()%>"></td>
			           	</tr>
			           	<tr>
			           		<th>장인소개</th>
			           		<td colspan="5">
			           			<textarea name="masterIntro" rows="5" cols="40" style="resize:none; height:50px; border:none;"><%=b.getMasterIntro()%></textarea>
			           		</td>
			           	</tr>
			           	<%} %>
			           	<tr>
			           		<th>답변</th>
			           		<td colspan="5">
			           			<%if(b.getBoardAnswer()==null){ %>
			           			<textarea class="manAnswer" name="boardAnswer" rows="3" cols="60" style="resize:none"></textarea>
			           			<%}else{ %>
			           			<textarea name="boardAnswer" rows="3" cols="60" style="resize:none"><%=b.getBoardAnswer()%></textarea>
			           			<%} %>
			           		</td>
			           	</tr>
			           	<tr>
			           		<th>답변 등록</th>
			           		<td class="buttons" colspan="2">
			           			<%if(b.getStatus().equals("미답변")){ %>
			           			<button type="button" style="margin-left:70px;" class="btn btn-info" id="insertAnswer">답변 등록</button>
			           			<%}else if(b.getStatus().equals("답변완료")){ %>
			           			<button type="button" style="margin-left:70px;" class="btn btn-primary" id="updateAnswer">답변 수정</button>
			           			<%}else{ %>
			           			<button class="btn" style="margin-left:70px;" disabled>답변 불가</button>
			           			<%} %>
			           		</td>
			           		<th>등급 심사</th>
			           		<td colspan="2">
			           			<%if(b.getBoardCategory().equals("입점문의") && !m.getGrade().equals("장인")){ %>
			           			<button type="submit" name="accept" style="margin-left:35px;" class="btn btn-success" formaction="<%=contextPath%>/updateGrade.ma">승인</button>
			           			<button type="button" name="deny" style="margin-left:35px;" class="btn btn-danger">거절</button>
			           			<%} %>
			           		</td>
			           	</tr>
			        </table>
		        </form>
	            <%} %>
	        <%} %>
	        <br>
	        <script>
		        $(function(){
		        	$(".titleQna").siblings().hide();
	        	})
	        	$(".titleQna").on("click",function(){
	        		$(this).siblings().toggle(500);
	        	})
		        $("[name=deny]").on("click",function(){
		        	alert("장인 등급 신청이 거절되었습니다.");
	        		$(this).attr("disabled",true);
	        		$(this).siblings().attr("disabled",true);
	        	})
	        	$(".buttons").on("click","#insertAnswer",function(){
					var bno = $(this).parents("#qnaTable").siblings("[name=boardNo]").val();
					var ban = $(this).parents("#qnaTable").find(".manAnswer").val();
					var boardSta = $(this).parents("#qnaTable").find(".boardSta");
					var curBtn = $(this);
					
					$.ajax({
						url : "answer.ma",
						data : {
							boardNo : bno,
							boardAnswer : ban
						},
						type : "post",
						success : function(result){
							if(result>0){
								alert("답변이 등록되었습니다.");
								boardSta.get(0).innerHTML = "답변완료";
								curBtn.attr("id","updateAnswer");
								curBtn.get(0).innerHTML = "답변 수정";
								curBtn.removeClass("btn-info");
								curBtn.addClass("btn-primary");
							}else{
								alert("답변 등록에 실패했습니다.");
							}
		        		},
						error : function(){
							console.log("통신 실패");
						}
					})
	        	})
			    $(".buttons").on("click","#updateAnswer",function(){
					var bno = $(this).parents("#qnaTable").siblings("[name=boardNo]").val();
					var ban = $(this).parents("#qnaTable").find(".manAnswer").val();
					$.ajax({
						url : "updateAnswer.ma",
						data : {
							boardNo : bno,
							boardAnswer : ban
						},
						type : "post",
						success : function(result){
							if(result>0){
								alert("답변이 수정되었습니다.");
							}else{
								alert("답변 수정에 실패했습니다.");
							}
		        		},
						error : function(){
							console.log("통신 실패");
						}
					})
	        	})
	        </script>
	        
	        <hr>
	        <br>
	        <h2 align="center">구매내역</h2>
	        <br>
	        <table id="buyTable" class="table table-borderless" border="1" cellpadding="5">
	            <thead>
	            <%if(orderList.isEmpty()){ %>
	               	<tr style="border-bottom:1px solid lightgrey;">
		                <th colspan="2">주문번호</th>
	               		<td></td>
				<%}else{ %>
	               	<tr class="order-hover" style="border-bottom:1px solid lightgrey;">
		                <th colspan="2">주문번호</th>
	               		<td><%=orderList.get(0).getOrderNo() %></td>
				<%} %>
	                </tr>
	            </thead>
	            	<tr>
		                <th width="350px">상품명</th>
		                <th width="270px">수량</th>
		                <th>처리 상태</th>
		            </tr>
	            <tbody>
	            <%if(orderList.isEmpty()){ %>
	           	<tr>
	           		<td colspan="3">해당 회원의 구매내역이 없습니다.</td>
	           	</tr>
	           	<%}else{ %>
	               	<%for(OrderDetail o : orderList){%>
	               	<tr class="detail-area">
	               		<input type="hidden" value="<%=o.getOrderNo()%>">
	               		<td><%=o.getProductName()%></td>
	               		<td><%=o.getProductCount()%>개</td>
	               		<td><%=o.getOrderStatus()%></td>
	              	</tr>
	               	<%} %>
	            <%} %>
	            </tbody>
	        </table>
	        <br>
	        
	        <hr>
	        <br>
	        <h2 align="center">리뷰내역</h2>
	        <a href="allReview.ma?currentPage=1&memNo=<%=m.getMemNo()%>" style="float:right">전체보기&raquo;</a>
	        <table id="reviewTable" class="table" border="1" cellpadding="5">
	            <thead align="center">
	              	<tr>
		                <th width="150">작성일</th>
		                <th width="200">상품명</th>
		                <th width="280">리뷰내용</th>
		                <th width="95">상태</th>
		                <th>관리</th>
	                </tr>
	            </thead>
	            <tbody>
	            <%if(list.isEmpty()){ %>
	            	<tr>
	            		<td colspan="5">해당 회원의 리뷰 내역이 없습니다.</td>
	            	</tr>
	            <%}else{ %>
	                <%for(Review r : list){%>
	                <tr>
	                	<td align="center"><%=r.getProductBoardDate()%></td>
	                	<td align="center"><%=r.getProductName()%></td>
	                	<td><%=r.getProductBoardContent()%></td>
	                	<td align="center"><%=r.getStatus()%></td>
	                	
	                	<td align="center">
	                	<%if(r.getStatus().equals("Y")){ %>
	                		<a href="<%=contextPath%>/deleteReview.ma?pbNo=<%=r.getProductBoardNo()%>&mno=<%=m.getMemNo() %>" onclick="return reviewChk();" class="custom-btn btn-delete2">삭제</a>
	                	<%} %>
	                	</td>
	                </tr>
	                <%} %>
	            <%} %>
	            </tbody>
	        </table>
	        <br>
	        <script>
		        function reviewChk(){
		    		var result = confirm("해당 리뷰를 삭제하시겠습니까?");
		    		return result;
		    	}
	        </script>
	        <div align="center">
            <a href="<%=contextPath%>/manager.ma?currentPage=1" class="btn btn-secondary">돌아가기</a>
            <%if(!m.getGrade().equals("관리자")){ %>
	            <%if(mr!=null){ %>
	            <a href="<%=contextPath%>/delete.ma?mno=<%=m.getMemNo()%>&mst=<%=mr.getStatus()%>" onclick="return chk();" class="onlyEnroll btn btn-danger">회원 탈퇴</a>
	            <%}else{ %>
	            <a href="<%=contextPath%>/delete.ma?mno=<%=m.getMemNo() %>" onclick="return chk();" class="onlyEnroll btn btn-danger">회원 탈퇴</a>
	            <%} %>
            <%} %>
            </div>
	        <br><br><br><br><br><br><br><br><br><br>
	    </div>
    </div>
</body>
</html>