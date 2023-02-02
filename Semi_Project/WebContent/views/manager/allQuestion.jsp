<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList
    							,com.semi.common.model.vo.PageInfo
    							,com.semi.board.model.vo.Board
    							,com.semi.member.model.vo.Member"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Member m = (Member)request.getAttribute("m");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");
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
    .order-hover:hover{
    	background-color: lightgrey;
    	cursor: pointer;
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
        	<h2 align="center"><%=m.getMemName()%> 회원의 1:1 문의내역</h2>
	        <br>
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
	        
	        <!-- 페이징바 -->
	        <div align="center" class="paging-area">
	        	<%if(pi!=null){ %>
	        	<%if(pi.getCurrentPage() != 1){ %>
		        	<button class="paging-btn" onclick="location.href='<%=contextPath%>/allBoard.ma?currentPage=<%=pi.getCurrentPage()-1%>'">&lt;</button>
		        <%}else{ %>
		        	<button class="paging-btn" disabled>&lt;</button>
		        <%} %>
		        
		        <%for(int i=pi.getStartPage(); i<=pi.getEndPage(); i++){ %>
	        		<!-- 내가 요청한 페이지 버튼 비활성화 -->
		        	<%if(i==pi.getCurrentPage()){ %>
		        		<button class="paging-btn" disabled><%=i %></button>
		        	<%}else{ %>
		        		<button class="paging-btn" onclick="location.href='<%=contextPath%>/allBoard.ma?currentPage=<%=i%>'"><%=i%></button>
		        	 <%} %>
		        <%} %>
		        
		        <%if(pi.getCurrentPage() != pi.getMaxPage()){ %>
		        <button class="paging-btn" onclick="location.href='<%=contextPath%>/allBoard.ma?currentPage=<%=pi.getCurrentPage()+1%>'">&gt;</button>
	        	<%}else{ %>
	        		<button class="paging-btn" disabled>&gt;</button>
		        <%} %>
		        <%} %>
	        </div>
	        <div align="right">
	        <a href="<%=contextPath%>/detail.ma?mno=<%=m.getMemNo()%>" class="btn btn-warning" style="color:grey;">돌아가기</a>
	        </div>
	        <br><br><br><br><br><br><br><br><br><br>
        </div>
</body>
</html>