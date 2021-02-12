<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Member m = (Member)session.getAttribute("member");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>Study with Us 회원 가입</title>
<style>
 .joinContainer {
            width: 628px;
            height: 533px;
            margin: 0 auto;
            margin-top: 100px;
        }

        .logo_container {
            text-align: center;
        }

        .logo_container>img {
            width: 140px;
             height: 70px;
        }

        .join_info {
            width: 628px;
            height: 420px;
            margin-top: 38px;
            padding-left: 0px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        .join_info>li {
            list-style: none;
            width: 285px;
            font-weight: bold;
            border-bottom: 1px solid black;
            height: 26px;
            position: relative;
            font-size: 12px;
        }

        label {
            position: absolute;
        }

        .join_info>li>input {
            right: 7px;
            font-size: 10px;
            position: absolute;
            width: 189px;
            height: 13px;
            border: none;
        }

        .join_info>li:nth-child(even) {
            margin-left: 58px;
        }

        .profile_img {
            width: 511px;
            height: 75px;
        }

        h5 {
            margin: 0px;
        }


        .profile {
            display: flex;
            padding-left: 0px;
            margin-top: 10px;
            justify-content: space-between;
        }

        .profile>li {
            list-style: none;
            width: 50px;
            height: 50px;
        }

        .profile>li>label {
            width: 50px;
            height: 50px;
        }

        .profile>li>label>img {
            width: 100%;
            height: 100%;
            border-radius: 70%;
        }

        .join_agree {
            display: block;
            font-size: 12px;
        }

        button {
            width: 308px;
            height: 35px;
            margin-top: 15px;
            color: white;
            background-color: #C54840;
            border: none;
        }

        span {
            position: absolute;
            top: 30px;
            color: red;
            font-size: 10px;
        }

        input[type=radio] {
            display: none;
        }

        #dupleChk {
            width: 40px;
            height: 25px;
            line-height: 25px;
            position: absolute;
            top: -20px;
            right: 0px;
            border-radius: 5px;
            display: none;
        }
        
        #cancelUse{
         width: 40px;
            height: 25px;
            line-height: 25px;
            position: absolute;
            top: -20px;
            right: 0px;
            border-radius: 5px;
            display: none;
        }
        
        .join_info>li:first-child>label,
        .join_info>li:nth-child(2)>label,
        .join_info>li:nth-child(6)>label
        {
        	top : -20px;
        }
        #memberId,#memberName,#memberMail{
          right : 90px;
                }
</style>
</head>


<body>

  <div class="joinContainer">
        <div class="logo_container"><img src="/img/logo.png"></div>
        <form action="/updateMember" method="post" onsubmit="return form_check()">
            <ul class="join_info">
                <li>
                    <label for="memberId">* 아이디 </label>
                    <input type="text" id="memberId" name="memberId" value="<%=m.getMemberId()%>" readonly>
                </li>
                <li><label for="memberName">* 이름 </label><input type="text" id="memberName" name="memberName" value="<%=m.getMemberName()%>" readonly></li>
                <li><label for="memberPw">* 비밀번호 </label><input type="password" placeholder="5~12자의 영문 대소문자와 숫자로만 입력" id="memberPw" name="memberPw" required>
                    <span>
                    </span>
                </li>
                <li><label for="pwChk">* 비밀번호 확인 </label><input type="password" id="pwChk" name="pwChk" required><span></span></li>
                <li><label for="nickName">* 닉네임 </label><input type="text" id="nickName" name="nickName" required></li>
                <li><label for="memberMail">* 이메일 </label><input type="text" id="memberMail" name="memberMail" value="<%=m.getMemberEmail()%>" readonly></li>
                <div class="profile_img">
                    <h5>프로필 이미지</h5>
                    <ul class="profile">
                        <li>
                            <input type="radio" id="ex1" name="pro_img" value="1.jpg">

                            <label for="ex1"><img src="img/ex1.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex2" name="pro_img" value="2.jpg">

                            <label for="ex2"><img src="img/ex2.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex3" name="pro_img" value="3.jpg">

                            <label for="ex3"> <img src="img/ex3.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex4" name="pro_img" value="4.jpg">

                            <label for="ex4"><img src="img/ex4.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex5" name="pro_img" value="5.jpg">

                            <label for="ex5"><img src="img/ex5.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex6" name="pro_img" value="6.jpg">

                            <label for="ex6"> <img src="img/ex6.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex7" name="pro_img" value="7.jpg">

                            <label for="ex7"> <img src="img/ex7.jpg"></label>
                        </li>
                        <li>
                            <input type="radio" id="ex8" name="pro_img" value="8.jpg">

                            <label for="ex8"> <img src="img/ex8.jpg"></label>
                        </li>
                    </ul>
                </div>
                <div class="join_agree">
                    <div>
                        <button type="submit">변경하기</button>
                        <button type="button" id="back">뒤로가기</button>
                    </div>
                </div>
            </ul>
        </form>

<script>
var checkNo = 0;
$(function() {
	 var prevPw = '<%=m.getMemberPw()%>';

	$("#back").click(function(){
		window.history.back();
	});
    $(".join_info>li>input").focusin(function() {
        //              $(this).css('right','90px');
        $(this).css('outline', 'none');
        $(this).animate({
            'right': '90px'
        }, 500);

        $(this).prev().animate({
            'top': '-20px'
        }, 500);
    });
    $(".join_info>li>input").focusout(function() {

        if ($(this).val() == "") {
            $(this).animate({
                'right': '7px'
            }, 500);
            $(this).prev().animate({
                'top': '0px'
            }, 500);
            $(this).next().html("");
        }

    });

    $("#memberPw").keyup(function() {
        var pwChk = /^[a-zA-Z0-9]{5,12}$/;
        var pw = $(this).val();
        if (pwChk.test(pw)) {
        	if(pw != prevPw){
        		  $(this).next().css('color', 'green');
                  $(this).next().html("사용가능합니다.");
                  $(this).attr("pass", "true");
        	}
        	else{
        		 $(this).next().css('color', 'red');
                 $(this).next().html("이전 비밀번호와 일치합니다.");
                 $(this).attr("pass", "false");
        	}

        } else {
            $(this).next().css('color', 'red');
            $(this).next().html("조건에 일치하지 않습니다.");
            $(this).attr("pass", "false");
        }

    });

    $("#pwChk").keyup(function() {
        if ($(this).val() == $("#memberPw").val()) {
            $(this).next().css('color', 'green');
            $(this).next().html("비밀번호가 일치합니다.");
            $(this).attr("pass", "true");
        } else {
            $(this).next().css('color', 'red');
            $(this).next().html("비밀번호가 일치하지 않습니다.");
            $(this).attr("pass", "false");
        }

    });

    $("button[type=reset]").click(function() {
        $("span").html("");
        checkNo = 0;
    });

    $("#nickName").change(function() {
        if ($(this).val() == "") {
            $(this).attr("pass", "false");
        } else {
            $(this).attr("pass", "true");
            console.log("닉네임 트루");
        }
    });

    $(".profile>li>label").click(function() {

        $(".profile>li>label").each(function(index, item) {
            $(item).prev().attr("pass", "false");
        });

        $(this).prev().attr("pass", "true");
        console.log($(this).prev().attr("pass"));
        
          $(".profile>li>label").each(function(index, item) {
            if($(item).prev().attr("pass")=="true"){
                $(item).children().css("border","5px solid black");
            }
            else{
                $(item).children().css("border","none");
            }
        });
        
    });
      
});

function form_check() {
    $("input").each(function(index, item) {
        if ($(item).attr("pass") == "true") {
            checkNo = checkNo + 1;
        } else {
            console.log("false");
        }
    });
    console.log(checkNo);
    if (checkNo == 4) {
        return true;
    } else {
    	alert("모든 정보를 확인해주세요");
        return false;
    }
};
</script>

</body>
</html>