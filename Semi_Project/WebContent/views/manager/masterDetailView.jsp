<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,com.semi.manager.master.model.vo.Master"%>
<%
	ArrayList<Master> list = (ArrayList<Master>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>masterDetailView</title>
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
    #masterUpdate-form input{
    	width: 50%;
    }
    #masterUpdate-form textarea{
    	width: 95%;
    	height:50px;
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
	    
	    <hr style="border: 3px solid rgba(255, 221, 0, 0.126);">
	    
        <div id="left-side" align="center">
            <ol id="manager-menu" >
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath%>/manager.ma?currentPage=1">관리자 페이지</a></li>
                <li style="background-color: rgba(255, 221, 0, 0.126);"><a href="<%=contextPath%>/manager.ma?currentPage=1">회원 관리</a></li>
                <li><a href="<%=contextPath %>/manageOrder.ma?currentPage=1">주문 관리</a></li>
            </ol>
        </div>
        
        <div id="right-side">
			<h2 align="center">장인 상세정보</h2>
	        <form action="<%=contextPath%>/masterUpdate.ma" id="masterUpdate-form" method="post">
	        	<br>
	        	<%if(list.isEmpty()){ %>
		            <table class="table table-bordered" border="1">
		            	<tr>
		            		<td>조회 실패</td>
		            	</tr>
		            </table>
	            <%}else{ %>
	            	<!-- < %for(Master m : list){ %>-->
	            	<input type="hidden" name="memNo" value="<%=list.get(0).getMemNo()%>">
	            	<%for(int i=0; i<list.size(); i++){ %>
	            	<table class="table table-bordered mst-table" border="1">
		            	<tr class="mst-area">
		                	<th>장인번호</th>
		                	<td><input type="text" name="masterNo<%=i%>" style="border:none;" class="mst-input" value="<%=list.get(i).getMasterNo()%>" readonly></td>
		                </tr>
		                <tr class="mst-area">
		                	<th>법인명</th>
		                	<td>
		                		<input type="text" name="coName<%=i%>" class="mst-input" required value="<%=list.get(i).getCoName()%>">
		                	</td>
	                	</tr>
		                <tr class="mst-area">
		                	<th>사업자등록번호</th>
		                	<td>
		                		<input type="text" name="coNumber<%=i%>" class="mst-input" required value="<%=list.get(i).getCoNumber()%>">
		                	</td>
		                </tr>
		                <tr class="mst-area">
		                	<th>대표자</th>
		                	<td>
		                		<input type="text" name="ceo<%=i%>" class="mst-input" required value="<%=list.get(i).getCeo()%>">
		                	</td>
		                </tr>
		                <tr class="mst-area">
		                	<th>업태명</th>
		                	<td>
		                		<input type="text" name="coKind<%=i%>" class="mst-input" required value="<%=list.get(i).getCoKind()%>">
		                	</td>
		                </tr>
		                <tr class="mst-area">
		                	<th>상점명</th>
		                	<td>
		                		<input type="text" name="strName<%=i%>" class="mst-input" required value="<%=list.get(i).getStrName()%>">
		                	</td>
		                </tr>
		                <tr class="mst-area">
		                	<th>상점소개</th>
		                	<td>
		                		<textarea name="masterIntro<%=i%>" class="mst-input" rows="5" cols="40" style="resize:none" required><%=list.get(i).getMasterIntro()%></textarea>
		                	</td>
		                </tr>
		                <tr class="mst-area">
		                	<th>장인 상태</th>
		                	<td><input type="text" class="status mst-input" name="status<%=i%>" style="border:none;" value="<%=list.get(i).getStatus()%>" readonly></td>
		                </tr>
	                </table>
	            <div id="buttons" align="center">
	            	<%if(list.get(i).getStatus().equals("Y")){ %>
	                <button type="submit" id="change" class="btn btn-primary">정보 변경</button>
	                <%} %>
	                <a href="<%=contextPath%>/detail.ma?mno=<%=list.get(0).getMemNo()%>" class="btn btn-info">돌아가기</a>
	            </div>
	                <div><br></div>
	                <%} %>
	            <%} %>
			</form>
			<script>
			$(function(){
				//name이 status로 시작하는 요소의 부모 요소를 선택하고,
				//그 부모의 하위에 있는 input 요소들 전체에 disabled 속성 부여하기
				var status = $("input[name^=status]");
				$.each(status,function(){
					
					if(this.value=="N"){
						$(this).parents("table").find("input").prop("disabled",true);
						$(this).parents("table").find("textarea").prop("disabled",true);
					}
				})
	    	})
			</script>
		</div>
	</div>
</body>
</html>