<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            width: 120px;
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
    </style>
    </head>
<body>
<%@ include file="/views/common/menubar.jsp" %>
    <div class="outer">
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
            <form action="" class="cartForm" >
                <h3 style="padding-left:30px">공예상품 장바구니</h3> 
                    <!-- if문 장바구니 비었을 경우-->
                    <div class="empty-carts">
                        <h1>공예상품 장바구니에 담긴 상품이 없습니다</h1>
                        <h3>장인의 손길이 닿은 공예상품을 담아보세요</h3>
                    </div>
                    <hr>
                    <div align="right" style="padding-right: 30px;"><button type="button" class="mid-order" onclick="">공예상품 구경하기</button></div>
                    <br>
                    <!--else if 장바구니에 상품있을 경우-->
                    <div class="whole-carts">
                        <table align="center" class="carts" style="padding: 10px;">
                            <thead style="border-bottom: 2px solid gray;">
                                <tr>
                                    <td height="30px" width="50px" align="center">
                                        <input type="checkbox" name="" id="" checked>
                                    </td>
                                    <td colspan="2" align="left" style="font-size: medium; width: 300px;">뭐뭐 장인의 어디어디상점</td>
                                    <td width="100px">수량</td>
                                    <td width="100px" align="center">상품금액</td>
                                    <td width="100px" align="center">배송비</td>
                                </tr>
                               
                            </thead>
                            <!-- 상점별 상품 선택사항이 보여지는 공간! 장바구니 클릭한 상점 중 상점코드가 겹치지않도록 카트생성 -->
                            <tbody>
                                <tr align="center">
                                    <td>
                                        <input type="checkbox" name="" id="" checked>
                                    </td>
                                    <td><img src="https://www.chf.or.kr/jnrepo/upload/jnBrdBoard/202208/790ecc229b864e12ad0fdc6b755874af_1660802244358.jpg" style="width:100px; height:100px;"></td>
                                    <td>자개의 오색빛깔 어쩌구한 어쩌구<br>
                                        <small>이걸로 말할것같으면 뭐뭐 장인의 어디어디상점의 대표상품으로 사랑받고있다 그런것이다</small></td>
                                    <td width="100px"><input style="width:30px;" align="center" type="number" name="" id="item-count"></td>
                                    <td>금액<br>
                                        <small>쿠폰적용사항</small></td><!-- if문으로 쿠폰 적용되었으면 보이도록-->
                                    <td>3000원</td>
                                </tr>
                            </tbody>
                            <!-- 공예상품에 대한 전체 금액에 대한 항목 sum처리 -->
                            <tfoot>
                                <tr>
                                    <td colspan="6" style="background-color: rgba(252, 181, 38, 0.251); height: 50px;" align="center">
                                        <table>
                                            <tr align="center">
                                            <td width="100px">선택상품 금액<br>
                                                (상품금액 합계)</td>
                                            <td width="50px">-</td>
                                            <td width="100px">할인적용 금액 <br>
                                                (할인적용 금액)</td>
                                            <td width="50px">+</td>
                                            <td width="150px">배송비<br>
                                                (if문 있으면 1개만)</td>
                                            <td width="150px" align="right" style="font-size: medium;">총 금액</td>
                                            <td width="200px" style="font-size: medium;">선택-할인+배송비</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <br>
                    </div>
                    <hr>
                    <div align="center">
                             <!-- 중간에 결제 요청할 경우 필요한 회원정보 input hidden에 담아 보내기-->
                             <p style="font-size: 30px; margin: 3px;">선택체험상품 총 금액 800,000원 &nbsp;&nbsp;<button type="submit" class="mid-order">선택 체험상품 바로구매</button> <button type="submit" class="mid-order-del" >선택상품 삭제</button></p>
                    </div>
            </form>
            <!--==============================================================공예상품폼 FINISH============================================================-->    
            <!--==============================================================체험상품폼 START============================================================-->                      
            <form action="" class="cartForm">
                <br><br>
                <h3 style="padding-left:30px">체험상품 장바구니</h3> 
                    <!-- if문 장바구니 비었을 경우-->
                    <div class="empty-carts">
                        <h1>체험상품 장바구니에 담긴 상품이 없습니다</h1>
                        <h3>나만을 위한 단하나의 공예품. 지금바로 장인과 함께 체험해보세요.</h3>
                    </div>
                    <hr>
                    <div align="right" style="padding-right: 30px;"><button type="button" class="mid-order" onclick="">체험상품 구경하기</button></div>
                    <br>
                     <!--else if 장바구니에 상품있을 경우-->
                    <div class="whole-carts">
                    <table align="center" class="carts" style="padding: 10px;">
                        <thead style="border-bottom: 2px solid gray;">
                            <tr>
                                <td height="30px" width="50px" align="center">
                                    <input type="checkbox" name="" id="" checked>
                                </td>
                                <td colspan="3" align="left" style="font-size: medium; width: 300px;">뭐뭐 장인의 어디어디장인</td>
                                <td>체험인원</td>
                                <td width="100px" align="center">상품금액</td>
                                <td width="100px" align="center">체험일</td>
                            </tr>
                            <tr>
                                <td colspan="6" style="background-color: black;"></td>
                            </tr>
                        </thead>
                        <!-- 상점별 상품 선택사항이 보여지는 공간! 장바구니 클릭한 상점 중 상점코드가 겹치지않도록 카트생성 -->
                        <tbody>
                            <tr align="center">
                                <td width="50px">
                                    <input type="checkbox" name="" id="" checked>
                                </td>
                                <td width="100px" height="100px">상품이미지</td>
                                <td width="250px" align="left" style="padding-left: 15px; padding-right: 15px;">매듭장인 매듭만들기 체험<br>
                                    <small>매듭을 만들어 보자</small></td>
                                <td width="100px"><input style="width:30px;" align="center" type="number" name="" id="item-count" ></td>
                                <td width="100px">금액<br>
                                    <small>쿠폰적용사항</small></td><!-- if문으로 쿠폰 적용되었으면 보이도록-->
                                <td width="150px">2022-11-31</td>
                            </tr>
                        </tbody>
                         <!-- 체험상품에 대한 전체 금액에 대한 항목 sum처리 -->
                        <tfoot>
                            <tr>
                                <td colspan="6" style="background-color: rgba(252, 181, 38, 0.251); height: 50px;" align="center">
                                    <table>
                                        <tr align="center">
                                            <td width="100px">선택상품 금액<br>
                                                (상품금액 합계)</td>
                                            <td width="50px">-</td>
                                            <td width="100px">할인적용 금액 <br>
                                                (할인적용 금액)</td>
                                            
                                            <td width="150px" align="right" style="font-size: medium;">총 금액</td>
                                            <td width="200px" style="font-size: medium;">선택-할인</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <br>
                    </div>
                    <hr>
                    <div align="center">
                        <!-- 중간에 결제 요청할 경우 필요한 회원정보 input hidden에 담아 보내기-->
                        <p style="font-size: 30px; margin: 3px;">선택체험상품 총 금액 800,000원 &nbsp;&nbsp;<button type="submit" class="mid-order">선택 체험상품 바로구매</button> <button type="submit" class="mid-order-del" >선택상품 삭제</button></p>
                    </div>
            </form>
            <!--==============================================================전체결제폼 FINISH============================================================-->    
            <!--==============================================================전체결제폼 START============================================================-->    
            <form action="">
                <div id="total-order" style="height: 50px; margin: auto; padding: 40px;" align="center">
                    <!-- 최종에 결제 요청할 경우 필요한 회원정보 input hidden에 담아 보내기-->
                    <p style="font-size: 37px; margin: 3px; height: 50px;">선택체험상품 총 금액 800,000원 &nbsp;&nbsp;<button type="submit" class="order-final-btn">주문하기 >></button></p>
                </div>
            </form>
            <!--==============================================================체험상품폼 FINISH============================================================-->             
    </div>
    <br><br><br><br><br><br><br><br><br><br>
</body>
</html>