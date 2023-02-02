<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>상품문의 작성 성공</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <style>        
		#popupClose{
			width: 100px;
			height: 40px;
			border: 0px;
			background-color: rgba(132, 198, 255, 0.508);
			font-size: 15px;
			font-weight: bold;
			cursor: pointer;
		}

		#popupClose:hover{
			background-color: rgba(9, 125, 227, 0.848);
		}
    </style>
    </head>
    <body align="center">
        <h2>상품 문의가 등록되었습니다.</h2>
        <h4>등록한 상품문의 확인 및 수정은</h4>
        <h4> <고객센터>내 <상품문의>에서 확인 가능합니다. </h4>
        <br><br>
        
		<button id="popupClose" onclick="close();">CLOSE</button>

		<script>
			function close(){
				e.preventDefault();
				
				window.close();
				
			}
		</script>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
       <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </body>
    </html>