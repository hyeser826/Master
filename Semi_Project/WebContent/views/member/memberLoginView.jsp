<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.semi.member.model.vo.Member"%>
<%
	String referer = request.getHeader("referer");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    
    div#head{
        font-size: 40px;
        width: 1600px;
        padding: 50px;
        margin-bottom: 50px;
        border-bottom: 2px solid lightgray;
    }

    div#body{
        margin-right:100px;
        margin-left:100px;
        margin-bottom: 50px;
    }
    #body>div{
        float: left;
    }
    #body1{
        width: 50%;
        padding-bottom:8px;
    }
    #bb{
    	margin-top:50px;
    	width: 1px;
    	height:250px;
    	border: 1px solid lightgray;
    }
    #body2{
        width: 48%;
    }
    
    #login1{
    	margin-top:50px;
        margin-right: 50px;
        height: 200px;
        float: right;
    }
    #login2{
    	margin-top:50px;
        margin-left:50px;
        height: 200px;
        float: left;
    }

    #bottom{
        background-color:whitesmoke;
        padding-top: 330px;
        margin-top: 10px;
        margin-left: 400px;
        margin-right: 400px;
        padding-bottom: 30px;
    }

    .inputs{
        border: 1px solid lightgray;
        border-radius: 3px;
        width: 300px;
        height: 40px;
    }

    #login-btn{
        width: 300px;
        height: 40px;
        border: 0px;
        border-radius: 3px;
        background-color: rgb(69, 69, 73);
        color: white;
    }
    #order-btn{
        width: 300px;
        height: 40px;
        border: 0px;
        border-radius: 3px;
        background-color: lightgray;
        color: black;
    }
    #enroll-btn{
        width: 300px;
        height: 40px;
        border: 0px;
        margin-top:10px;
        border-radius: 3px;
        background-color: dodgerblue;
        color: white;
    }

</style>



</head>

<body>
<%@ include file = "/views/common/menubar.jsp" %>
	<%if(loginMem == null) {%>
	<div align="center" style="width:1900px; position:relative;">
        <div id="head" align="center">?????????</div>

        <div id="body" align="center">

            <div id="body1">
                <form action="<%=contextPath %>/login.me" method="post" id="login-form" onsubmit="return frm_check();">
                    <input type="hidden" name="referer" value="<%=referer%>">
                    <table align="center" id="login1">
                        <tr align="left">
                            <td style="font-size: 20px; color:rgb(69, 69, 73);">| ?????? ?????????</td>
                        </tr>
                        <tr align="left">
                            <td></td>
                        </tr>
                        <tr>
                       		<td><input type="text" name="memId" class="inputs" id="logId" placeholder="?????????" required></td>
                        </tr>
                        <tr align="left">
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="password" name="memPw" class="inputs" id="memPw" placeholder="????????????" required></td>
                        </tr>
                        <tr align="left">
                            <td colspan="2" style="font-size: 15px; margin:10px;">
                                <input type="checkbox" name="saveId" id="saveId">&nbsp; <label for="saveId">????????? ??????</label>
                            </td>
                        </tr>
                        <tr align="left">
                            <th>
                                <button type="submit" id="lotgin-btn">?????????</button>
                            </th>
                        </tr>
                        <tr align="right">
                            <td>
                                <a href="<%=contextPath%>/findId.me">????????? ??????</a> |
                                <a href="<%=contextPath%>/findPw.me">???????????? ??????</a>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: 17px;">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            
            <div id="bb">
            	
            </div>
            
            <div id="body2">
                <form action="<%=contextPath %>/nologin.me" method="post" id="login-form">
                    <table align="center" id="login2">
                        <tr align="left">
                            <td style="font-size: 20px; color:rgb(69, 69, 73)">| ????????? ????????????</td>
                        </tr>
                        <tr align="left">
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="reciverName" class="inputs" id="orderName" placeholder="????????????" required></td>
                        </tr>
                        <tr align="left">
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="text" name="orderNo" class="inputs" id="orderNum" placeholder="????????????" required></td>
                        </tr>
                        <tr align="left">
                            <td colspan="2" style="font-size: 15px; margin:10px;">
                                <input type="checkbox" name="saveName" id="saveName">&nbsp; <label for="saveId">???????????? ??????</label>
                            </td>
                        </tr>
                        <tr align="left">
                            <th>
                                <button type="submit" id="order-btn">?????? ?????? ??????</button>
                            </th>
                        </tr>
                        <tr>    
                            <td style="color:gray;">
                               	?????????????????? ????????? ??????, <br> 
                               	??????????????? ??????????????????.
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

        </div>

        <div id="bottom" align="center">
        	<table>
        		<tr align="center" >
        			<td>
        				?????? ????????? ????????? ????????????????
        			</td>
        		</tr>
        		<tr align="center">
        			<td>
        			 <button onclick="enrollPage();" id="enroll-btn">????????????</button>
        			</td>
        		</tr>
        	</table>
        </div>
	</div>
	<%} %>
	<%if(loginMem != null) {%>
		<script type="text/javascript">
			location.replace("/smp");
		</script>
	<%} %>
	<script>
		$(function() {
        
        fnInit();
      
	  	});
	  
		function frm_check(){
		      saveid();
		}

		 function fnInit(){
		     var cookieid = getCookie("saveid");
		     var cookieName = getCookie("saveName");
		     console.log(cookieid);
		     console.log(cookieName);
		     if(cookieid != ""){
		         $("input:checkbox[id='saveId']").prop("checked", true);
		         $('#logId').val(cookieid);
		     }
		     if(cookieName != ""){
		         $("input:checkbox[id='saveName']").prop("checked", true);
		         $('#reciverName').val(cookieName);
		     }    
		 }

		 function setCookie(name, value, expiredays) {
		     var todayDate = new Date();
		     todayDate.setTime(todayDate.getTime() + 0);
		     if(todayDate > expiredays){
		         document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expiredays + ";";
		     }else if(todayDate < expiredays){
		         todayDate.setDate(todayDate.getDate() + expiredays);
		         document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
		     }
		     
		     console.log(document.cookie);
		 }

		 function getCookie(Name) {
		     var search = Name + "=";
		     console.log("search : " + search);
		     
		     if (document.cookie.length > 0) { // ????????? ???????????? ????????? 
		         offset = document.cookie.indexOf(search);
		         console.log("offset : " + offset);
		         if (offset != -1) { // ????????? ???????????? 
		             offset += search.length;
		             // set index of beginning of value
		             end = document.cookie.indexOf(";", offset);
		             console.log("end : " + end);
		             // ?????? ?????? ????????? ?????? ????????? ?????? ?????? 
		             if (end == -1)
		                 end = document.cookie.length;
		             console.log("end??????  : " + end);
		             
		             return unescape(document.cookie.substring(offset, end));
		         }
		     }
		     return "";
		 }

		 function saveid() {
		     var expdate = new Date();
		     if ($("#saveId").is(":checked") || $("#saveName").is(":checked")){
		         expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30);
		         setCookie("saveid", $("#logId").val(), expdate);
		         setCookie("saveName", $("#reciverName").val(), expdate);
		         }else{
		        expdate.setTime(expdate.getTime() - 1000 * 3600 * 24 * 30);
		         setCookie("saveid", $("#logId").val(), expdate);
		         setCookie("saveName", $("#reciverName").val(), expdate);
		     }
		 }
		 
		//???????????? ????????? ?????????
        function enrollPage(){
        	location.href = "<%=contextPath%>/enrollForm1.me";
        }

    </script>
		

	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>

























