<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="/js/finds.js" defer></script>
<link rel="stylesheet" type="text/css" href="/css/find.css">
<title>아이디 혹은 비밀번호 찾기</title>
</head>
<body>

    <div class="login_container">
        <div class="logo_container">
            <img src="/img/logo.png">
        </div>
        <ul class="selectOp">
            <li>
                <input type="radio" name="sel" id="selId" value=1>
                <label for="selId">아이디 찾기</label>
            </li>
            <li><input type="radio" name="sel" id="selPw" value=2>
                <label for="selPw">비밀번호 찾기</label></li>
        </ul>
        <div class="input_inform" id="findId">
            <div>
                <span>이름</span>
                <input type="text" name="searchName" id="searchName" required>
            </div>
            <div><span>이메일</span><input type="text" name="searchEmail" id="searchEmail" required></div>
        </div>
        <div class="input_inform" id="findPw">
            <div>
                <span>아이디</span>
                <input type="text" name="searchId" id="searchId" required>
            </div>
            <div><span>이메일</span><input type="text" name="searchMail" id="searchMail" required></div>
        </div>
        <div class="button_box">
            <button type="submit" id="searchBtn">아이디 찾기</button>
        </div>
        <div class="find_box">
            <span id="resultText">
            </span>
        </div>
    </div>
   <script>
   		$("#searchBtn").click(function(){
			console.log(selN);
   			if(selN == 1)
   				{
   	   			var searchName = $("#searchName").val();
   	   			var searchEmail = $("#searchEmail").val();
   					$.ajax({
   						url : "/find",
   						type :"post",
   						data : {selN:selN,searchName:searchName,searchEmail:searchEmail},
   						success : function(data){
   							var resultHTML = "";
   							if(data == null)
   								{
   									resultHTML = "입력하신 정보에 맞는 아이디가 없습니다.";
   									$("#resultText").css('color','red');
   								}
   							else{
   								$("#resultText").css('color','green');
   								resultHTML = searchName+" 님의 아이디는 ["+data+"] 입니다.<br><a href='/views/login.jsp' style='color: blue; text-decoration:none;'>로그인 페이지로 이동하기 ></a>";
   							}
   							$("#searchName").val("");
   							$("#searchEmail").val("");
   							$("#resultText").html(resultHTML);
   				//			data["s_id"];
   					//		data["selN"];
   						}
   						
   					});
   				}
   			else{
   				var searchId = $("#searchId").val();
   				var searchMail = $("#searchMail").val();
   					$.ajax({
   						url : "/find",
   						type : "post",
   						data : {selN:selN,searchId:searchId,searchMail:searchMail},
   						success : function(data){
   							if(data == true){
   								$("#resultText").css('color','green');
   								resultHTML = "임시비밀번호가 발급되었습니다. 이메일을 확인해주세요. <br><a href='/views/login.jsp' style='color: blue; text-decoration:none;'>로그인 페이지로 이동하기 ></a>";
   							}
   							else{
   								resultHTML = "입력하신 정보에 맞는 계정이 없습니다. 확인 후 다시 시도해주세요.";
									$("#resultText").css('color','red');
   							}
   							$("#searchId").val("");
   							$("#searchMail").val("");
   							$("#resultText").html(resultHTML);
   						}
   					});
   			} 
   		});
   </script>
</body>
</html>