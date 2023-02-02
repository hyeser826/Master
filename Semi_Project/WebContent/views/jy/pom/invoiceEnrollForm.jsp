<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.ArrayList
    								,com.semi.JY.pom.model.vo.Delivery
    								,com.semi.JY.orderdetail.model.vo.OrderDetail"%>
    
    <%
		
		Delivery dv =(Delivery)request.getAttribute("dv");
    ArrayList<OrderDetail> list6 =(ArrayList<OrderDetail>)request.getAttribute("list6");
	
	%>
   <%@include file="/views/common/menubar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
<body>
<div class="outer">
<div id="left-side">
    			<%@include file="/views/common/leftbar2.jsp" %>
    		</div>
    		<div id="right-side">
		<section class="section">
			<!--  바로아래는 '상품준비중'인 상태일때만 표시될수있도록 -->
     			<tr>
     				<td>
     					<%if(!list6.isEmpty()) {%>
							<%for(OrderDetail i :list6) {%>
								  <!-- 상태가 '상품준비중만' -->
					</td>
     			</tr>
              <h5 class="card-title">송장번호 등록</h5>
             <!-- Browser Default Validation, 아래 form은 인보이스엔롤컨트롤러  -->
              <form action="<%=contextPath %>/InvoiceEnroll.en" method="post" > 
                <div class="col-md-4">
          		       주문번호
   					   <input type="text"  class="form-control"name="OrderDetailNo"id="validationDefault01" value="" >
              			
  				</div>
           <br>
                <div class="col-md-4">
                 			송장번호
           		        <input type="text" class="form-control" name="deliveryInvoiceNo"  id="validationDefault02" value=""   required>
         			   
                </div>
                
           <br>
                <div class="col-md-3">
                	
                  				택배사
                  <select class="form-select" name="DeliveryParcel"  value="" id="validationDefault04" required>
                    <option selected  value="노룩패스택배">노룩패스택배</option>
                    <option selected  value="대한통운">대한통운</option>
                    <option selected disabled value="느린택배">느린택배</option>
                    <option selected value="로젠택배">로젠택배</option>
                    <option selected  value="우체국택배">우체국택배</option>
                  </select>
                 
            
                </div>
               
          <br>
                <div class="col-12">
         	         <button class="btn btn-primary" type="submit">제출하기</button>
                </div>
              </form>
				
				<%} %>
			<%} %>		
         </section>
    		</div>
</div>
</body>
</html>