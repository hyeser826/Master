<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.cart.order.model.vo.Order, java.sql.Date"%>
    
<%
	Date eventDate = (Date)request.getAttribute("eDate");
	int pno = (Integer)(request.getAttribute("pno"));
	int price = (Integer)(request.getAttribute("price"));
	int amount = (Integer)(request.getAttribute("quantity"));
	String strName = (String)request.getAttribute("strName");
	String productName = (String)request.getAttribute("productName");
	
	int delFee = 3000;
	int count = 0;
	int totalDel = 0;
	int totalPrice = 0;
	int storeTotal = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문결제</title>
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
	
	/*********************** 상품리스트 ***********************/
	
	.rt-odrpay-form{/*전체 상품리스트*/
	    width: 1000px;
	    margin : auto; 
	}
	
	.odrpay-tbl{/*상점별 상품선택사항 : table*/
	    margin : auto ; 
	    width: 1000px;
	    height: auto;
	    text-align: left;
	    font-size: 12px;
	    border-collapse: collapse;
	
	}
	
	.odrpay-tbl tr, .odrpay-tbl td, .odrpay-tbl th{
	    background-color : white;
	    padding: 0px;
	    border: 1px solid rgb(222, 222, 222);
	}
	
	#odrpay-tbl-top td{
	    background-color: rgba(250, 192, 0, 0.563);
	}
	
	.odrpay-tbl th{
	    border-right: 0px;
	}
	
	.pmt-order-btn{/*최종선택 전체 구매버튼*/
	    width: 250px;
	    height: 70px;
	    margin: auto;
	    border: 1px solid rgba(250, 67, 0, 0.536);
	    background-color: rgba(250, 108, 0, 0.536);
	    color: white;
	    font-size: 30px;
	    font-weight: bold;
	    border-radius: 30px;
	}
	
	.bck-to-cart{/*최종선택 전체 구매버튼*/
	    width: 100px;
	    height: 70px;
	    margin: auto;
	    border: 1px solid rgba(207, 206, 206, 0.236);
	    background-color: rgba(225, 224, 223, 0.236);
	    color: rgb(150, 150, 150);
	    font-size: 13px;
	    font-weight: bold;
	    border-radius: 30px;
	} 
	.delinfo, #payinfo{
	    width: 1000px;
	    font-size: 13px;
	    border-collapse: collapse;
	}
	
	.delinfo th, .delinfo td,#payinfo th, #payinfo td{
	    border-bottom: 1px solid rgb(222, 222, 222);
	    border-top: 1px solid rgb(222, 222, 222);
	    background-color: rgba(250, 192, 0, 0.236);
	    height: 20px;
	    width : 250px; 
	    font-size: 13px; 
	    height: 80px;
	}
	
	.delinfo th, #payinfo th{
		text-align:center;
		width:30%; 
		height:50px;
	}
	
	.delinfo td, #payinfo td{
	    background-color: white;
	    padding-left: 10px;            
	}
	
	.delinfo input, #payinfo td{
		
	    height: 100%;
	    border: 0px;
	    width:100%;
	    text-align:left;
	}
	
	#let-mem-chk p{
	 	display : inline-block;
	 	font-size: 15px;
		font-weight: 500;
	}

    .final-tbl{
    	height:100px;
    	width:950px;
    }
    
    .final-tbl td{
    	height:100%;
    	paddig : 0px;
    }
	
	.finalprice{
	    height: 80px; 
	    margin: auto; 
	    display : inline-block;
       	font-size: 40px;
		font-weight: 450;
	}
	
	#let-odr-pay{
        width: 950px;
    }
    
    
	
	
	#bck-to-cart{
       	width:70%;
       	height:80px;        	
       	font-size: 20px;
		font-weight: 500;
       	border: 2px solid lightgray;
        background-color: rgb(221, 220, 220);
        border-radius: 40px;
        color: rgb(98, 98, 98);
   }
   
   #pmt-order-btn{
     	width:70%;
     	height:80px;        	
     	font-size: 40px;
		font-weight: 500;
     	border: 2px solid rgba(250, 150, 30, 0.563);
        background-color:  rgba(250, 192, 0, 0.563);
        border-radius: 20px;
	}	
	
</style>
</head>
<body>
<%@ include file="/views/common/menubar.jsp"%>
    <div class="outer">
        <!--================================================메뉴바 넣기================================================================-->
        <div class="orderLine">
            <table class="orderTable" align="center">
                <tr>
                    <td width="250" align="center" >하나. 장바구니</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center" style="color: black; font-size:15px; font-weight: bold;">둘. 주문결제</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center">셋. 결제완료</td>
                </tr>
            </table>
        </div>
        <br><br>
        <div id="item-list">
            <!--============================================================== 주문예정상품 리스트 START============================================================-->
            <form action="<%=contextPath %>/insertnew.odr" class="rt-odrpay-form" method="post">            
           	<input type="hidden" name="eventDate" value="<%=eventDate %>">
           	<input type="hidden" name="pno" value="<%=pno %>">
           	<input type="hidden" name="price" value="<%=price %>">
           	<input type="hidden" name="amount" value="<%=amount %>">
           	<input type="hidden" name="strName" value="<%=strName %>">
                    <div class="rt-odrpay-li">
                        <p>주문 결제할 상품목록</p>
                        <table class="odrpay-tbl">
                            <thead align="center" id="odrpay-tbl-top">
                                <tr style="font-size: 13px; height: 80px;">
                                    <td width="50%">상품정보</td>
                                    <td width="13%">판매자</td>
                                    <td width="7%">수량</td>
                                    <td width="15%">상품금액</td>
                                    <td width="15%">배송비/<br>예약일</td>
                                </tr>
                            </thead>
                            <!-- 전체 상품리스트가 보여지는 공간 -->
                            <tbody align="center"  style="background-color: white;">
                                <tr style="font-size: 13px; height: 80px;">
                                    <td><%=productName %></td>
                                    <td><%=strName %></td>
                                    <td><%=amount %></td>
                                    <td><%=price %></td>
                                    <% totalPrice += (amount*price); %>
                                    <% if(eventDate!=null){%>
                                    <td><%=eventDate %></td>
                                    <% } else { %>
                                  		<%if(storeTotal>50000){ %>
                                	<td>무료</td>
                                 		<% } else { %>
                                 		<%	count++; %>
                                 		<td><%=delFee %></td>
                                 		<%} %>
                                    <%} %>
                                </tr>
                            </tbody>
                          
                        </table>
                        <br><br>
                        <div align="right" id="let-mem-chk">
                            <p>총 주문금액&nbsp;</p><p><%=(totalPrice+(delFee*count)) %></p><p>&nbsp;원&nbsp;</p>
                        </div>
                        <br>
                    </div>
                    <!--============================================================== 주문예정상품 리스트 FINISH============================================================-->  
                    <hr>
                    <br>
                    <!--============================================================== 배송지 리스트 start============================================================-->      
                    <div class="delivery">
                        <p>주문자</p>
                        <table class="delinfo">
                        <%if(loginMem != null){ %>
                        <input type="hidden" name="memId" id="" value="<%=loginMem.getMemId() %>">
                        	<tr>
                                <th>주문자 이름</th>
                                <td>
                                <input type="text" name="memName" id="memName" value="<%=loginMem.getMemName() %>" required>
                                </td>
                            </tr>
                            <tr>
                                <th>주문자 전화번호</th>
                                <td>
                                <input type="text" name="memPhone" id="memPhone" value="<%=loginMem.getMemPhone() %>" required>
                                </td>
                               
                            </tr>
                            <tr align="center">
                                <th>주문자 이메일주소</th>
                                <td>
                                <input type="text" name="memEmail" id="memEmail" value="<%=loginMem.getMemEmail() %>" required>
                                </td>
                                
                            </tr>
                            <tr align="center">
                                <th>주문자 배송주소</th>
                                <td align="center"><input type="text" name="memAddress" id="memAddress" value="<%=loginMem.getMemAddress() %>" required></td>
                            </tr>
                        <%}else{ %>

                            <tr>
                                <th>주문자 이름</th>
                                <td>
                                <input type="text" name="bmemName" id="memName" value="" required>
                                </td>
                            </tr>
                            <tr>
                                <th>주문자 전화번호</th>
                                <td>
                                <input type="text" name="bmemPhone" id="memPhone" value="" required>
                                </td>
                               
                            </tr>
                            <tr align="center">
                                <th>주문자 이메일주소</th>
                                <td>
                                <input type="text" name="bmemEmail" id="memEmail" value="" required>
                                </td>
                                
                            </tr>
                            <tr align="center">
                                <th>주문자 배송주소</th>
                                <td>
                                <input type="text" name="bmemAddress" id="bmemAddress" value="" required>
                                </td>
                            </tr>
                        <%} %>
                        </table>
                        <br><br>
                        <p>배송지
                            <input type="checkbox" name="samedel" id="samedel" onclick="check(this)">&nbsp;주문자 정보 입력받기</td>
                        </p>
                        <table class="delinfo">
                            <tr> 
                                <th>배송받는분</th>
                                <td><input type="text" maxlength="5" name="reciverName" id="reciverName" value="" required> </td>
                            </tr>
                            <tr> 
                                <th>배송지 전화번호</th>
                                <td><input type="text" maxlength="11"  name="reciverPhone" id="reciverPhone" value="" required></td>
                            </tr>
                            <tr>
                                <th>배송지 주소</th>
                                <td><input type="text" maxlength="50"  name="orderAddress" id="orderAddress" value="" required></td>
                            </tr>
                        </table>
                    </div>
                    <br><br>
                    <hr>
                    <br>
                    <div id="paymentsInfo">
                        <p>결제수단</p>
                        <table id="payinfo">
                            <tr align="center">
                                <th style="width:30%; height:50px">주문방법</th>
                                <td>계좌 입금</td>
                            </tr>
                            <tr align="center">
                                <th>계좌번호</th>
                                <td>하나은행 123-123098-09876 장인몰</td>
                            </tr>
                        </table>
                    </div>
                    <br><br>
            <hr>
            <div id="total-order" align="center">
			    <table align="center" id="let-odr-pay">
					<tr>
						<td style="width:20%;" align="center">
							<button type="button" id="bck-to-cart" onclick="goBack()">뒤로가기</button>
						</td>
						<td style="width:45%; padding-botoom: 10px;"  align="center">
							<input type="hidden" name="delFee" value="<%=(delFee*count) %>">
							<p class="finalprice">총 금액&nbsp;</p><p class="finalprice"><%=(totalPrice+(delFee*count)) %></p><p class="finalprice"></p><p class="finalprice">&nbsp;원</p>
						</td>
						<td style="width:35%;" align="center">
							<button type="submit" id="pmt-order-btn" onclick="return validate();">결제하기</button>
						</td>
					</tr>
				</table>
		    </div>
            </form>
            <!--==============================================================체험상품폼 FINISH============================================================-->   
                      
    </div>
    <script type="text/javascript">
    function check(n) {
        if (n.checked) {
            document.getElementById("reciverName").value =
                document.getElementById('memName').value;
            document.getElementById("reciverPhone").value =
                document.getElementById('memPhone').value;
            document.getElementById("orderAddress").value =
                document.getElementById('memAddress').value;
        } else {
        	document.getElementById("reciverName").value = "";
            document.getElementById("reciverPhone").value = "";
            document.getElementById("orderAddress").value = "";
        }
    }
    
    function goBack(){	
		var stayOnPage = confirm("입력사항은 저장되지 않습니다.").value;
		if (stayOnPage) {
			history.back()
		}
	}
    
    function validate(){
    	var rcvnm = document.getElementById("reciverName").value;
    	var nmrl = /^[가-힣]{2,}$/;
        var rcvpn = document.getElementById("reciverPhone").value;
        var phrl = /^[0-9]{,11}$/;
        var rcvadrs = document.getElementById("orderAddress").value;
        
        if(rcvnm == ""){
            alert("배송받으시는 분의 이름을 입력해주세요.")
            return false;
        } else if(!nmrl.test(rcvnm)){
            alert("이름은 2글자 이상 한글만 가능합니다.")
            return false;
        } else if(rcvpn == ""){
            alert("배송받으시는 분의 연락처를 입력해주세요")
            return false;
        } else if(rcvadrs == ""){
        	alert("배송받으시는 분의 주소를 입력해주세요")
            return false;
        }
        
    }
</script>
    <br><br><br><br><br><br><br><br><br><br>
</body>
</html>