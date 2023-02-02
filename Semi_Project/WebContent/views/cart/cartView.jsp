<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.*,com.semi.cart.model.vo.Cart, java.util.ArrayList, com.semi.cart.controller.CartPageCtlr"%>
    
<% 
	ArrayList<Cart> mdlist = (ArrayList<Cart>)request.getAttribute("mdList");
	ArrayList<Cart> aclist = (ArrayList<Cart>)request.getAttribute("acList");	
	DecimalFormat df = new DecimalFormat("###,###");
        /////////////////////////장인 홈 이름 중복없이 테이블 만들기///////////////////////////////
        int count = 0;
        int dfee = 0;
        int mdStr=0;
        int acStr=0;
        ArrayList<String> mdstrs = new ArrayList<String>();
        ArrayList<String> acstrs = new ArrayList<String>();
        
        for(int i=0; i<mdlist.size(); i++){
            for(int j=0; j<mdlist.size(); j++){
                if(!(mdlist.get(i).getStrNm().equals(mdlist.get(j).getStrNm()))){
                    mdStr=i;
                }
            }
            mdstrs.add(mdlist.get(mdStr).getStrNm());
        }
    
        for(int i=0; i<aclist.size(); i++){
            for(int j=0; j<aclist.size(); j++){
                if(!(aclist.get(i).getStrNm().equals(aclist.get(j).getStrNm()))){
               
                	acStr=i;
                }
            }
            acstrs.add(aclist.get(acStr).getStrNm());
        }
        ///////////////////////////////////////////////////////////////////////////
	int mdAllMdPrice=0;
	int acAllMdPrice=0;
	//int finalPrice=0;
%>
<%@include file ="/views/common/menubar.jsp" %>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>장바구니</title>
 	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    
    <style>
        /*********************** 상단 및 틀 ***********************/
        .outer{
            background:white;
            color : black;
            width : 100%;
            margin : auto; /*가운데 정렬*/
            margin-top : 50px; /*위로부터 50px만큼 여백*/
        }

      
        .orderLine{ /*장바구니~결제완료라인*/
            width: 1000px;
            height: auto;
            text-align: left;
            border-top: 2px solid lightgray;
            border-bottom: 2px solid lightgray;
            color: gray;
            font-size: small;
            font-weight: bold;
            padding-left: 10px;
            padding-right: 10px;
            padding-top: 5px;
            padding-bottom: 5px;
            margin : auto; 
        }

        /*********************** 장바구니 ***********************/
        
        .cartForm{/*공예상품 장바구니, 체험상품 장바구니*/
            width: 1000px;
            margin : auto; 
        }

        .carts{/*상점별 상품선택사항 : table*/
            background-color: rgba(213, 194, 100, 0.229);
            margin : auto; 
            border: 0px solid white;
            width: 950px;
            height: auto;
            text-align: left;
            font-size: small;
            padding-block: 10px;
        }
        .empty-carts{/*빈 장바구니(공예+상품 동일적용)*/
            background-color: rgba(213, 194, 100, 0.229);
            margin : auto; 
            border: 0px solid white;
            width: 950px;
            height: 100px;
            text-align: center;
            font-size: small;
            padding-block: 10px;
        }

        .mid-order{/*중간 바로구매버튼(공예+상품 동일적용)*/
            width: 220px;
            height: 40px;
            margin: 0px;
            border: 1px solid rgba(250, 192, 0, 0.836);
            background-color: rgba(250, 192, 0, 0.563);
            color: black;
            font-size: 18px;
            font-weight: bold;
            border-radius: 10px;
        }
        .mid-order-del{/*선택상품삭제버튼(공예+상품 동일적용)*/
            width: 130px;
            height: 40px;
            margin: 0px;
            border: 1px solid rgba(62, 60, 60, 0.119);
            background-color: rgba(60, 62, 62, 0.284);
            color: white;
            font-size: 15px;
            font-weight: bold;
            border-radius: 10px;
        }
        .order-final-btn{/*최종선택 전체 구매버튼*/
            width: 300px;
            height: 70px;
            margin: auto;
            border: 1px solid rgba(250, 67, 0, 0.836);
            background-color: rgba(250, 108, 0, 0.836);
            color: white;
            font-size: 30px;
            font-weight: bold;
            border-radius: 30px;
        }
        
        .select-tbl{
	        width: 950px;
        }
        
        .finalprice{
        	display : inline-block;
        	font-size: 40px;
			font-weight: 450;
        }

        .final-tbl{
        	height:120px;
        	width:950px;
        }
        .fin-order-btn{
        	width:70%;
        	height:60%;        	
        	font-size: 40px;
			font-weight: 500;
        	border: 2px solid rgba(250, 150, 30, 0.563);
            background-color:  rgba(250, 192, 0, 0.563);
            border-radius: 20px;
        }
    </style>
    </head>
<body>
    <div class="outer">
<% if(loginMem.getMemId()!= null){ %>
        <!--================================================메뉴바 넣기================================================================-->
        <div class="orderLine">
            <table class="orderTable" align="center">
                <tr>
                    <td width="250" align="center" style="color: black; font-size:15px; font-weight: bold;">하나. 장바구니</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center">둘. 주문결제</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center">셋. 결제완료</td>
                </tr>
            </table>
        </div>
        <br><br>
        <div>
            <!--==============================================================공예상품폼 START============================================================-->
            <form method="post" class="cartForm" action="<%=contextPath %>/odpmt.li">
                <h4 style="padding-left:30px">공예상품 장바구니</h4> 
                <%if(!mdlist.isEmpty()){ %>
                <%for(int j = 0; j<mdstrs.size(); j++) {%><!--장인홈별로 테이블생성-->
                <div class="whole-carts">
                        <table align="center" class="carts" style="padding: 10px;">
                            <thead style="border-bottom: 2px solid gray;">
                                <tr>
                                    
                                    <td colspan="2" align="left" style="font-size: medium; width:150px;">
                                    	<div class="mdStrNm-div">
	                                    	<%=mdstrs.get(j) %>
	                                    </div>
                                    </td>
                                    <td width="100px" align="center">수량</td>
                                    <td width="100px" align="center">상품금액</td>
                                    <td width="100px" align="center">배송비</td>
                                    <td height="50px" width="50px" align="center">
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                       		<%for(int i = 0; i<mdlist.size(); i++) {%>
                       		<%if(mdstrs.get(j).equals(mdlist.get(i).getStrNm())) {%>
                                <tr align="center"  class="mdStrNm-div">
                                    <!-- <td width="100px" height="100px">상품이미지</td> -->
                                    
                                    <td width="250px" align="left" style="padding-left: 15px; padding-right: 15px;">
	                                    <div class="mdStrNm-div">
		                                    <input type="hidden" name="mdpno<%=i %>" value="<%=mdlist.get(i).getProductNo() %>">
		                                   <a id="md-name"> 
		                                   
		                                   <%=mdlist.get(i).getProductNm() %></a>
	                                    </div>
                                    <td>
                                   
                                    <td width="100px">
                                     <div class="acmd-area">
                                    <input type="hidden" id="acmdProNo" value="<%=mdlist.get(i).getProductNo() %>">
                                     <input type="hidden" id="acmdCartNo" value="<%=mdlist.get(i).getCartNo() %>">
                                    <input style="width:30px;" align="center" type="number" id="amount" name="amount" value="<%=mdlist.get(i).getAmount()%>">
                                    </div>
                                    </td>
                                    
                                    <td width="100px"><%=df.format(mdlist.get(i).getAmount()*mdlist.get(i).getProductPrice()) %></td>
                                   
                                    <% 
                                    
                                    if(mdAllMdPrice > 50000){ %>                              
                                    <td width="150px">0원</td>
                                    <input type="hidden" name="deliveryFee<%=i %>" value="0">
                                    <%} else if(mdAllMdPrice<50000){ %> 
                                    <td width="150px"><%=df.format(mdlist.get(i).getDeliveryFee())%>원</td>
                                   	<% for(int k=1; k<mdlist.size(); k++){%>
               							<% if(!(mdlist.get(j).getStrNm().equals(mdlist.get(k).getStrNm()))){ %>
                  						 	<% count++; %>
                						<%}%>
           							<%}%>
           							<%dfee = (3000*count); %>
                                    <%} %> 
                                    
                                    <td height="70px;">
                                    	<div class="cartNo-div" style="display:none;">
                                    		<input type="checkbox" name="cartNo" class="cartNo" value="<%=mdlist.get(i).getCartNo() %>" checked>
                                    	</div>
                                    	<div class="deleteOneCart">
                                    	     <input type="hidden" id="acCartNo" value="<%=mdlist.get(i).getCartNo() %>">
                                    		<button type="button" id="del-btn">삭제</button>
                                    	</div>	
                                    </td>
                                </tr>
                                 <script>
                                    <!-- checkbox가 체크되어있으면  -->
                                    
                                    if($(".cbx_chkAll").is(":checked")) {
                                        <% mdAllMdPrice += (mdlist.get(i).getAmount()*mdlist.get(i).getProductPrice()); %>                                     	
                                        }
                                    
                               
                                    
                                    
                                 
                                    
                                    </script>   
                                
                                
                                
                             <%} %>
                             <%} %>
                            </tbody>
                            <!-- 공예상품에 대한 전체 금액에 대한 항목 sum처리 -->
                            <tfoot>
                                <tr>
                                    <td colspan="6" style="background-color: rgba(252, 181, 38, 0.251); height: 50px;" align="center">
                                        <table>
                                            <tr align="center">
	                                            <td width="100px">상품금액
	                                            
	                                            <br><%=df.format(mdAllMdPrice)%>원
	                                            </td>
	                                            <td width="50px">+</td>
	                                            <td width="150px">배송비<br>
	                                            <% if(mdAllMdPrice > 50000){ %>                              
				                                   	무료
				                                <%} else if(mdAllMdPrice<50000){ %> 
				                                	3,000원
				                                <%} %> 
	                                            </td>
	                                            <td width="200px" align="center" style="font-size: medium;">
			                                       	상품금액  + 배송비
			                                    </td>
			                                   
	                                            <td width="200px" style="font-size: medium;">
	                                            <% if(mdAllMdPrice > 50000){ %>                              
				                                    0원
				                                <%} else if(mdAllMdPrice<50000){ mdAllMdPrice+=3000;%> 
				                                    <%=df.format(mdAllMdPrice) %>원
				                                <%} %> 
	                                            </td>
                                            </tr>
                                        </table>
                                        
                                       
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <br>
                	<%} %> 
                <%} else { %>   
                        <div class="empty-carts">
                            <h5>공예상품 장바구니에 담긴 상품이 없습니다</h5>
                            <h6>장인의 깊은 정성이 담긴 상품을 담아보세요</h6>
                        </div>
                        <hr>
                        <div align="right" style="padding-right: 30px;"><a href="<%=contextPath %>/houseItems.li" class="mid-order">공예상품 구경하기</a></div>
                        <br>
                <%} %>  

                    
                
                    <hr>
            						
            <!--<input type="hidden" name="deliveryFee" value="<%=dfee %>">-->
            <!--==============================================================공예상품폼 FINISH============================================================-->    
            <!--==============================================================체험상품폼 START============================================================-->                      
                <br><br><br>
                <h4 style="padding-left:30px">체험상품 장바구니</h4> 
                <%if(!aclist.isEmpty()){ %>
                <%for(int j=0; j<acstrs.size(); j++) {%><!--장인홈별로 테이블생성-->
                <div class="whole-carts">
                        <table align="center" class="carts" style="padding: 10px;">
                            <thead style="border-bottom: 2px solid gray;">
                                <tr>
                                    
                                    <td colspan="2" align="left" style="font-size: medium; width:150px;">
                                    	<div class="mdStrNm-div">
	                                    	<%=acstrs.get(j) %>
	                                    </div>
                                    </td>
                                    <td width="100px" align="center">수량</td>
                                    <td width="100px" align="center">상품금액</td>
                                    <td width="100px" align="center">예약일</td>
                                    <td height="50px" width="50px" align="center">
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                       		<%for(int i = 0; i<aclist.size(); i++) {%>
                       		<%if(acstrs.get(j).equals(aclist.get(i).getStrNm())) {%>
                                <tr align="center"  class="mdStrNm-div">
                                    <!-- <td width="100px" height="100px">상품이미지</td> -->
                                    
                                    <td width="250px" align="left" style="padding-left: 15px; padding-right: 15px;">
	                                    <div class="mdStrNm-div">
		                                    <input type="hidden" name="acpno<%=i %>" value="<%=aclist.get(i).getProductNo() %>">
		                                   <a id="ac-name"> <%=aclist.get(i).getProductNm() %></a>
	                                    </div>
                                    <td>
                                   
                                    <td width="100px">
                                    
                                    <div class="acmd-area">
                                    <input type="hidden" id="acmdProNo" value="<%=aclist.get(i).getProductNo() %>">
                                     <input type="hidden" id="acmdCartNo" value="<%=aclist.get(i).getCartNo() %>">
                                    <input style="width:30px;" align="center" type="number" id="acAmountId" name="acAmountId" value="<%=aclist.get(i).getAmount()%>">
                                    </div>
                                    </td>
                                    
                                    <td width="100px"><%=df.format(aclist.get(i).getTotalPrice()) %></td>
                                   
                                   
                                    <script>
                                    <!-- checkbox가 체크되어있으면  -->
                                    if($(".cbx_chkAll").is(":checked")) {
                                 <% acAllMdPrice += (aclist.get(i).getAmount()*aclist.get(i).getProductPrice()); %>                                    	
                                    }
                                    </script>                            
                                    <td width="150px"><%=aclist.get(i).getEventDate() %></td>
                                    <td height="70px;">
                                    	<div class="cartNo-div" style="display:none;">
                                    		<input type="checkbox" name="cartNo" class="cartNo" value="<%=aclist.get(i).getCartNo() %>" checked>
                                 
                                    	</div>
                                    	<div class="deleteOneCart">
                                    	     <input type="hidden" id="acCartNo" value="<%=aclist.get(i).getCartNo() %>">
                                    		<button type="button" id="del-btn">삭제</button>
                                    	</div>	
                                    </td>
                                </tr> 
                                
                                
                              
                             <%} %>
                             <%} %>
                            </tbody>
                            <!-- 공예상품에 대한 전체 금액에 대한 항목 sum처리 -->
                            <tfoot>
                                <tr>
                                    <td colspan="6" style="background-color: rgba(252, 181, 38, 0.251); height: 50px;" align="center">
                                        <table>
                                            <tr align="center">
	                                            <td width="100px">상품금액
	                                            </td>
	                                            <td class="acTotal-price" width="150px"><%=aclist.get(j).getTotalPrice()%>원</td>
	                                            <td width="50px"></td>
	                                            <td colspan="2" width="400px" align="center" style="font-size: 12px;">
			                                       	선택한 예약일을 확인해주세요
			                                    </td>
                                            </tr>
                                        </table>
                                        <script>
                                        
                                        
                        
                                    /*     $(".outer").on("click",".ac-amountBtn",function(){
                                        	var yn=confirm("선택한 상품을 정말 삭제하시겠습니까?");
                            				
                           				 $("form").attr("action", "/updateAmountCart.ca");
                           				if(yn){
                           				  $(this).attr("type","submit");
                           				}
                                        	
                                        }); */
                                        
                                     
                                         
                                        </script>
                                        
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                    <br>
                	<%} %> 
                <%} else { %>   
                        <div class="empty-carts">
                            <h5>체험상품 장바구니에 담긴 상품이 없습니다</h5>
                            <h6>장인과 함께 깊은 정성을 담아 나만의 공예품품을 만들어보세요</h6>
                        </div>
                        <hr>
                        <div align="right" style="padding-right: 30px;"><a href="<%=contextPath %>/activity.li" class="mid-order">체험상품 구경하기</a></div>
                        <br>
                <%} %>  

                    
                
                    <hr>
               <%--  <%if(!aclist.isEmpty()){ %>
                    <table align="center" class="select-tbl">
                             <input type="hidden" name="acAllMdPrice" value="<%=acAllMdPrice %>">
                    	<tr align="center">
                    		<td style="width:55%">
                    			<p class="totalprice"
                    			 style="font-size: 30px; margin: 3px;">선택상품 총 금액 <%=df.format(acAllMdPrice)%>원</p>
                   			</td>
                    		<td style="width:25%">
                    			<button type="submit" class="mid-order">선택상품 바로구매</button>
                    		</td>
                    		<td style="width:20%">
           						<input type="hidden" name="cartNo">
	                            <button type="submit" class="mid-order-del" >선택상품 삭제</button>
                    		</td>
                    	</tr>
                    </table>
                <%} %>   --%>
    			</div>
      	<!--==============================================================체험상품폼 FINISH============================================================-->             
		<br><br>
		<%if(!aclist.isEmpty()||!mdlist.isEmpty()){ %>
		    <div>
			    <table align="center" class="final-tbl">
			    
			    <%int finalAllPrice =  mdAllMdPrice+acAllMdPrice;%>
					<input type="hidden" name="finalPrice" value="<%=finalAllPrice%>">
					<tr>
						<td style="width:50%; padding-top:15px;"  align="right">
							<p class="finalprice">총 금액&nbsp;</p><p class="finalprice"><%=df.format(finalAllPrice)%></p><p class="finalprice">&nbsp;원</p>
						</td>
						<td style="width:50%;" align="center">
							<button type="submit" class="fin-order-btn" >주문하기</button>
						</td>
					</tr>
				</table>
		    </div>
		<%} %>
</form>      
    <br><br><br><br><br><br><br><br><br><br>

	<%}else{ %>
	<h1>잘못된 접근입니다.</h1>
	<h3><a href="<%=contextPath %>">홈페이지로 돌아가기</a></h3>
	<%} %>

	<script>	
	
	function refreshCartList(){
		location.reload();
	}
	
	//수량변경 버튼 누르면 변경된 수량을 가지고 업데이트문 실행
    $(".outer").on("click",".acmd-area",function(){
    /* 	var cartAcMd = $("#acAmountId").attr("value"); */
    	
    	
    	$.ajax({
			url: 'updateAmountCart.ca',
			type: 'get',
			
			data: {

		acmdProNo: $(this).children().eq(0).val(),
	    cartNo : $(this).children().eq(1).val(),
		acmdAmount: $(this).children().eq(2).val()
				
			},
			success: function(result){
				
				console.log("통신성공");
			  cartAlert(result);
			  refreshCartList();
			},
			
			error: function(){
				console.log("통신실패");
				
			}
    	});
			
			function cartAlert(result){
				if(result == '0'){
					alert("수량을 수정 하지 못하였습니다.");
				} else if(result == '1'){
					alert("수량이 수정 되었습니다.");
				} else if(result == '5'){
					alert("로그인이 필요합니다.");	
				}
			}; 
    	
    });
	
	//삭제 버튼 누르면 장바구니 한 행 삭제
    $(".outer").on("click",".deleteOneCart",function(){
    	var cartAcMd = $("#acAmountId").attr("value");
    	
    	
    	$.ajax({
			url: 'deleteOneCart.ca',
			type: 'get',
			
			data: {

		//acmdProNo: $(this).children().eq(0).val(),
	    cartNo : $(this).children().eq(0).val(),
		
				
			},
			success: function(result){
				
				console.log("통신성공");
			  cartAlert(result);
			  refreshCartList();
			},
			
			error: function(){
				console.log("통신실패");
				
			}
    	});
			
			function cartAlert(result){
				if(result == '0'){
					alert("삭제 하지 못하였습니다.");
				} else if(result == '1'){
					alert("상품이 삭제 되었습니다.");
				} else if(result == '5'){
					alert("로그인이 필요합니다.");	
				}
			}; 
    	
    });
	
    </script>
</body>
</html>