<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.product.model.vo.*,java.util.ArrayList"%>
<%
	Product p = (Product)request.getAttribute("p");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
<style>
    .outer{
		background:pink;
		color:white;
		width:1000px;
		margin:auto;  /*가운데 정렬*/
		margin-top:50px; /*위로부터 50px만큼 여백*/
	}
	
	#enroll-form>table{
		border:1px solid black
	}
	#enroll-form input,#enroll-form textarea{
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
	<div class="outer" align="center">
		<br>
		<h2 align="center">상품 수정</h2>
		
		<form action="<%=contextPath %>/update.pr" id="update-form" method="post" enctype="multipart/form-data">
		<!-- 카테고리,제목,내용,첨부파일 -->	
		<!-- 어떤 상품에 대해 수정처리 할 것인지 알아야 하기 때문에 pNo를 넘긴다.  -->
		<input type="hidden" name="pno" value="<%=p.getProNo()%>">
		<input type="hidden" name="mstno" value="<%=p.getMstNo()%>" >
		<input type="hidden" name="categoryname" value="<%=p.getCategoryName()%>" >
			<table align="center" class="table table-bordered table-sm">
				<tr>
					<th width="60">상품명</th>
					<td colspan="3"><input type="text" name="name" value="<%=p.getProName() %>" required></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><%=p.getCategoryName() %></td>
					<td>
					<select id="selectbox" name="category">
						<option value="" selected disabled>카테고리 수정</option>
						<option value="0.체험">0.체험</option>
						<option value="1.식기">1.식기</option>
						<option value="2.생활용품">2.생활용품</option>
						<option value="3.문구">3.문구</option>
						<option value="4.악세사리">4.악세사리</option>
						<option value="5.의류">5.의류</option>
						<option value="6.잡화">6.잡화</option>
						<option value="7.인테리어소품">7.인테리어소품</option>
					</select>
		
					</td>
				</tr>
				<tr>
					<th>가격</th>
					<td colspan="3"><input type="text" name="price" value="<%=p.getProPrice() %>" required></td>
				</tr>
				<tr>
					<th>배송비</th>
					<td colspan="3"><input type="text" name="deliveryFee" value="<%=p.getDeliveryFee() %>" ></td>
				</tr>
				<tr>
					<th>상품수량</th>
					<td colspan="3"><input type="text" name="stock" value="<%=p.getProStock() %>" required></td>
				</tr>
				<tr>
					<th>상품 상세 내용</th>
					<td colspan="3">
						<textarea name="content" style="resize: none;" cols="30" rows="10"><%=p.getProDescription() %></textarea>
					</td>
				</tr>
				<tr>
					<th>체험기간</th>
					<td colspan="3"><input type="text" name="expPeriod" value="<%=p.getExpPeriod() %>" size=30 maxlength=23 placeholder="ex) 2022/11/01 - 2022/11/17" ></td>
				</tr>
				<tr>
					<th>상품판매여부</th>
					<td colspan="3"><input type="text" name="status" value="<%=p.getProStatus()%>" placeholder="Y/N" required></td>
				</tr>
				<tr>
					<th>대표이미지</th> <!--미리보기-->
					<td colspan="3" align="center"><img id="titleImg" src="<%=contextPath%><%=(list.get(0).getFilePath())+(list.get(0).getSysName())%>" width="250" height="170"></td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<%for(int i=1;i<list.size();i++){%>
						<td>
						<img src="<%=contextPath%><%=(list.get(i).getFilePath())+(list.get(i).getSysName())%>" id="contentImg<%=i %>" width="150" height="120">
						</td>
					<%} %>
					
				</tr>

			</table>
			
			<!-- 파일첨부 영역 -->
			<div id="file-area" align="center">
				<%for(int i=0;i<list.size();i++) {%>
					<%if(list != null) {%>
						<input type="hidden" name="originFileNo" value="<%=list.get(i).getAttachmentNo()%>">
						<input type="hidden" name="originFileName" value="<%=list.get(i).getSysName()%>">
					<%} %>
				<%} %>
				<input type="file" id="file1" name="reUpfile1" onchange="loadImg(this,1);"> <!--대표이미지라서 필수-->
				<input type="file" id="file2" name="reUpfile2" onchange="loadImg(this,2);">
				<input type="file" id="file3" name="reUpfile3" onchange="loadImg(this,3);">
				<input type="file" id="file4" name="reUpfile4" onchange="loadImg(this,4);">
			</div>
			<br>
			<div align="center">
				<button type="submit" >수정 등록하기</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
			<br>
		</form>
		<script>

			$(function(){
				$("#file-area").hide();

				$("#titleImg").click(function(){
					$("#file1").click();
				});
				$("#contentImg1").click(function(){
					$("#file2").click();
				});
				$("#contentImg2").click(function(){
					$("#file3").click();
				});
				$("#contentImg3").click(function(){
					$("#file4").click();
				});
			})



			function loadImg(inputFile,num){

				//inputFile : 현재 변화가 생긴 input type="file" 요소 객체
				//num : 몇번째 input 요소인지 확인 후 해당 영역에 미리보기를 위한 매개변수

				//files속성은 업로드된 파일의 정보를 배열의 현태로 묶어서 반환하는 속성
				//파일 선택시 length가 1을 반환/ 취소하면 0을 반환 
				//console.log(inputFile.files.length);

				if(inputFile.files.length == 1){
					//선택된 파일이 존재하면 
					//선택된 파일을 읽어서 해당 영역에 미리보기 띄워주기

					//파일을 읽어주기 위한 객체 FileReader
					var reader = new FileReader();

					//파일을 읽어들이는 메소드 - 어떤 파일을 읽을 것인지 매개변수로 제시
					//readAsDataURL(파일);
					//파일을 읽어들이는 순간 해당 파일만의 고유 url을 부여한다.
					//부여된 url을 src 속성에 추가하면 된다.
					reader.readAsDataURL(inputFile.files[0]);

					//파일 읽기가 완료된 시점에 src에 url을 부여하는 함수 만들기
					reader.onload = function(e){
						//e는 이벤트 객체
						//부여된 url은 e객체의 target에 result에 들어있음
						//각 영역에 맞춰서 이미지 미리보기
						switch(num){
							case 1 : $("#titleImg").attr("src",e.target.result); break;
							case 2 : $("#contentImg1").attr("src",e.target.result); break;
							case 3 : $("#contentImg2").attr("src",e.target.result); break;
							case 4 : $("#contentImg3").attr("src",e.target.result); break;
						}
					}

				}else{
					switch(num){
							case 1 : $("#titleImg").attr("src",null); break;
							case 2 : $("#contentImg1").attr("src",null); break;
							case 3 : $("#contentImg2").attr("src",null); break;
							case 4 : $("#contentImg3").attr("src",null); break;
						}
				}
			
			}
		</script>
	</div>
	<br><br>
</body>
</html>