<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.text.*,java.util.ArrayList,com.semi.product.model.vo.Product"%>
    
    <%
     ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
    DecimalFormat df = new DecimalFormat("###,###");
    %>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>인테리어 소품</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <style>
        .outer{
            background:white;
            color : black;
            width : 100%;
            margin : auto; /*가운데 정렬*/
           
        }
        
        .inline-block {
	display: inline-block;
}
        

        .header{/*자주묻는질문*/
            color : black;
            width : 1600px;
            height: 150px;
            line-height: auto;
            margin : 0 auto; /*가운데 정렬*/
            margin-top: 70px;
            border-bottom: 2px solid lightgrey;
            padding-left: 20px;
        }
        
         .content-area{
        position: relative;
        }

        #left-side{
			
            float : left;
            color : black;
            width : 25%;
            height: 1200px;
            line-height: auto;
            margin : auto; /*가운데 정렬*/
            padding-left: 200px;
            padding-top: 30px;
            positon: absolute;
            align-items: center;
        }
        #left-side a{
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
            
        }
        #mem-center{
            margin : 0 auto; /*가운데 정렬*/
            width: 200px;
            height: 60px;
            font-size: 20px;
            padding: 0px;
        	align:"center"
        }

        #mem-center>li{
            margin : 0 auto; /*가운데 정렬*/
            width: 100%;
            height: 100%;
            font-size: 20px;
            cursor: pointer;
            padding: 15px;
            list-style : none;
        }

        #mem-center>li:hover{
            border-bottom: 3px solid rgba(255, 221, 0, 0.343);
        }
        
        #mem-center>li>a{
            text-decoration : none;
        	color : black;
        }
        
        #right-side{
            float : left;
            color : black;
            width : 1200px;
            height: auto;
            line-height: auto;
            margin : 30px; /*가운데 정렬*/
            margin-left:50px;
            padding: 30px;
             position: absolute;
             align-items: center;
           /*  padding-left: 100px; */
            
        }
        
       

       
	.list-area{
		width:100%;
		margin:auto;
	}
	.thumbnail{
		border : 1px solid white;
		/*width: 350px;*/
		height: 320px;
		display : inline-block;
		margin : 15px;
	}

	.thumbnail>img{
		
		width : 100%;
		height: 70%;
		
	}
	.thumbnail:hover{
		cursor:pointer;
		opacity:0.7;
	}
	
	
	.filter-area {
	width: 100%;
	margin: auto;
}

.filter-area>p {
	color: gray;
	font-size: 15px;
	font-weight: nomal;
}

.filter-area>p>a {
	style: none;
	
}
.filter-area>p>a:hover {
	style: none;
	font-weight: bold;
	cursor: pointer;
}

     
        
    </style>
    </head>
    
    <body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <div class="header" align="center">
            <p style="font-size: 40px;">인테리어 소품</p>
            <br>
            <img src="resources/img/icon/icon3.jpg" style="width: 27px; height:20px;">
           
        </div>
       <!-- 
       <hr style="border: 3px solid rgba(255, 221, 0, 0.126);;">
        --> 
        
        <div class="content-area">
        <div id="left-side" class="inline-block" align="center">
            <ol id="mem-center">
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/tablewear.li">식기</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/houseItems.li">생활용품</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/stationery.li">문구</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/acc.li">악세사리</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/cloth.li">의류</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/etc.li">잡화</a></li>
                <li style="font-weight:bold; background-color: rgba(255, 221, 0, 0.343);"><a href="<%=contextPath %>/interior.li">인테리어 소품</a></li>
            </ol>
        </div>    
        
        <div id="right-side" class="inline-block"> 
		<div>
			<div class="filter-area">
				<p class="filter">
					<a id="bestProduct">인기순</a> | <a id="recent">최신순</a>
					| <a id="lowPrice">낮은가격순</a> | <a id="highPrice">높은가격순</a>
				</p>
				<hr>
			</div>
			<div class="list-area card-columns">
		<%if(!list.isEmpty()){ %>
   <%for(Product p : list){ %>
  <div class="card thumbnail">
					<input type="hidden" value="<%=p.getProNo()%>"> 
					<img class="card-img-top" src="<%=p.getTitleImg()%>">
					<div class="card-body text-center">
						
						<p class="card-text">
							<%=p.getProName()%><br>
							<%=df.format(p.getProPrice())%>원<br>


						</p>


					</div>
				</div>
  <%} %>
  <%}else{ %>
      조회된 상품이 없습니다.
  <%} %>
		
		</div>
		</div>
<br><br><br><br><br><br>
       
        </div>
        </div>
        <br><br>
    </div>

    <script>
    	//filter-area 인기순 함수
		$(".filter").on("click","#bestProduct",function(){
			$.ajax({

				type: 'get',
				url : "best.li",
				data : {
					categoryName: '7.인테리어소품'
			
				},
				
				success : function(bestProductList) {
					var str = "";
					console.log(bestProductList);
					
					for (var i in bestProductList) {
					
						str+="<div class=\"card thumbnail\">"
						+"<input type=\"hidden\" value=\""+bestProductList[i].proNo+"\">"
						+"<img class=\"card-img-top\" src=\""+bestProductList[i].titleImg
						+"\" width=\"200px\" height=\"150px\">"
						+"<div class=\"card-body text-center\">"
						+"<p class=\"card-text\">"
						+bestProductList[i].proName+"<br>"
						+bestProductList[i].proPrice.toLocaleString("ko-KR")+"원<br>"		
						+"</p>"+"</div>"+"</div>"
		
					}
					console.log(str);
					$(".list-area").empty();
					$(".list-area").html(str);
					
					
				},
				error : function() {
					console.log("통신실패");
				}

			});
			
		})

		//filter-area 높은가격순 함수
		$(".filter").on("click","#highPrice",function(){
			$.ajax({

				type: 'get',
				url : "highPrice.li",
				data : {
					categoryName: '7.인테리어소품'
			
				},
				
				success : function(highPriceList) {
					var str = "";
					console.log(highPriceList);
					
					for (var i in highPriceList) {
					
						str+="<div class=\"card thumbnail\">"
						+"<input type=\"hidden\" value=\""+highPriceList[i].proNo+"\">"
						+"<img class=\"card-img-top\" src=\""+highPriceList[i].titleImg
						+"\" width=\"200px\" height=\"150px\">"
						+"<div class=\"card-body text-center\">"
						+"<p class=\"card-text\">"
						+highPriceList[i].proName+"<br>"
						+highPriceList[i].proPrice.toLocaleString("ko-KR")+"원<br>"		
						+"</p>"+"</div>"+"</div>"
		
					}
					console.log(str);
					$(".list-area").empty();
					$(".list-area").html(str);
					
					
				},
				error : function() {
					console.log("통신실패");
				}

			});
			
		})
	
			
			//filter-area 낮은가격순 함수
		$(".filter").on("click","#lowPrice",function(){
			$.ajax({

				type: 'get',
				url : "lowPrice.li",
				data : {
					categoryName: '7.인테리어소품'
			
				},
				
				success : function(lowPriceList) {
					var str = "";
					console.log(lowPriceList);
					
					for (var i in lowPriceList) {
					
						str+="<div class=\"card thumbnail\">"
						+"<input type=\"hidden\" value=\""+lowPriceList[i].proNo+"\">"
						+"<img class=\"card-img-top\" src=\""+lowPriceList[i].titleImg
						+"\" width=\"200px\" height=\"150px\">"
						+"<div class=\"card-body text-center\">"
						+"<p class=\"card-text\">"
						+lowPriceList[i].proName+"<br>"
						+lowPriceList[i].proPrice.toLocaleString("ko-KR")+"원<br>"		
						+"</p>"+"</div>"+"</div>"
		
					}
					console.log(str);
					$(".list-area").empty();
					$(".list-area").html(str);
					
					
				},
				error : function() {
					console.log("통신실패");
				}

			});
			
 });
	

		//filter-area 최신상품순 함수
		$(".filter").on("click","#recent",function(){
			$.ajax({

				type: 'get',
				url : "recent.li",
				data : {
					categoryName: '7.인테리어소품'
			
				},
				
				success : function(recentProductList) {
					var str = "";
					console.log(recentProductList);
					
					for (var i in recentProductList) {
					
						str+="<div class=\"card thumbnail\">"
						+"<input type=\"hidden\" value=\""+recentProductList[i].proNo+"\">"
						+"<img class=\"card-img-top\" src=\""+recentProductList[i].titleImg
						+"\" width=\"200px\" height=\"150px\">"
						+"<div class=\"card-body text-center\">"
						+"<p class=\"card-text\">"
						+recentProductList[i].proName+"<br>"
						+recentProductList[i].proPrice.toLocaleString("ko-KR")+"원<br>"		
						+"</p>"+"</div>"+"</div>"
		
					}
					console.log(str);
					$(".list-area").empty();
					$(".list-area").html(str);
					
					
				},
				error : function() {
					console.log("통신실패");
				}

			});
			
 });
		
		//게시물 클릭시 상품 상세페이지로 이동
		$(".list-area").on("click",".thumbnail",function(){
			 location.href="<%=contextPath%>/detail.md?pno="+ $(this).children().eq(0).val();
			
		});
			  
		
    	
    	
    </script>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>