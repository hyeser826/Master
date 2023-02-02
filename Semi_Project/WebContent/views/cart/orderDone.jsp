<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>결제완료</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <style>
        /*********************** 상단 및 틀 ***********************/
        .outer{
        background:white;
        color : black;
        font-weight: bold;
        width : 1200px;
        margin : auto; /*가운데 정렬*/
        margin-top : 50px; /*위로부터 50px만큼 여백*/
        align-items: center;
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

        /*********************** 결제완료 ***********************/
        .thank-you{
            background-color: rgba(213, 194, 100, 0.229);
            margin : auto; 
            border: 0px solid white;
            width: 950px;
            height: 150px;
            text-align: center;
            padding-top: 30px;
        }
        .thank-you a{
            text-decoration: none;
        }

        .go-btn-box{
            width: 950px;
            height: 60px;
            padding: 0px;
        }


        .link-btn{
            width: 320px;
            height: 50px;
            margin: 0px;
            border: 1px solid rgb(142, 141, 141);
            background-color: rgb(117, 117, 117);
            color: white;
            font-size: 25px;
            font-weight: 400;
            border-radius: 5px;
            padding: 5px;
            margin-right: 30px;
            margin-left: 30px;
            cursor: pointer;
        }
        
        .link-btn:hover{
            background-color: rgb(71, 71, 71);
        }
    </style>
    </head>
<body>
<%@ include file="../common/menubar.jsp" %>
    <div class="outer">
        <!--================================================메뉴바 넣기================================================================-->
        <div class="orderLine">
            <table class="orderTable" align="center">
                <tr>
                    <td width="250" align="center">하나. 장바구니</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center">둘. 주문결제</td>
                    <td width="50" align="center">>></td>
                    <td width="250" align="center"style="color: black; font-size:15px; font-weight: bold;">셋. 결제완료</td>
                </tr>
            </table>
        </div>
        <br><br>
        <h3 style="padding-left:130px">결제완료</h3> 
        <!-- if문 장바구니 비었을 경우-->
        <div align="center">
            <div class="thank-you">
            
                <h1>주문해주셔서 감사합니다</h1>
                <a href="<%=contextPath %>/orderlookup.or">나의 주문내역 확인</a>
            </div>
            <hr width="950px;">
            <br>
                <button class="link-btn" id="go-ac-btn">체험상품 둘러보기</button>
                <button class="link-btn" id="go-md-btn">공예상품 둘러보기</button>
        </div>

    <script>
        $("go-ac-btn").click(function(){
            location.href="<%=contextPath %>/activity.li";
        });


        $("go-ac-btn").click(function(){
            location.href="<%=contextPath %>/houseItems.li";
        });
    </script>             
</body>
</html>