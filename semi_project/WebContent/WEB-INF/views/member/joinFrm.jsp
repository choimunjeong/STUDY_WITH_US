<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="/css/join.css">
<title>Study with Us 회원 가입</title>
</head>


<body>

  <div class="joinContainer">
        <div class="logo_container"><img src="/img/logo.png"></div>
        <form action="/join" method="post" onsubmit="return form_check()">
            <ul class="join_info">
                <li>
                    <label for="memberId">* 아이디 </label>
                    <input type="text" id="memberId" name="memberId" placeholder="5~12자의 영문 대/소문자와 숫자로만 입력" required>
                    <span></span>
                    <button type="button" id="dupleChk">확인</button>
                    <button type="button" id="cancelUse">취소</button>
                </li>
                <li><label for="memberName">* 이름 </label><input type="text" id="memberName" name="memberName" required></li>
                <li><label for="memberPw">* 비밀번호 </label><input type="password" placeholder="5~12자의 영문 대소문자와 숫자로만 입력" id="memberPw" name="memberPw" required>
                    <span>
                    </span>
                </li>
                <li><label for="pwChk">* 비밀번호 확인 </label><input type="password" id="pwChk" name="pwChk" required><span></span></li>
                <li><label for="nickName">* 닉네임 </label><input type="text" id="nickName" name="nickName" required></li>
                <li><label for="memberMail">* 이메일 </label><input type="text" id="memberMail" name="memberMail" required></li>
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
                        <input type="checkbox" name="check_agree" id="check_agree" required>이용약관, 개인정보 수집 및 이용에 모두 동의합니다.
                    </div>
                    <div>
                        <button type="submit">회원가입</button>
                        <button type="reset">초기화</button>
                    </div>
                </div>
            </ul>
        </form>

<script>

var checkNo = 0;
var count = 0;
$(function() {
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
    $("#memberId").keyup(function() {
        var idChk = /^[a-zA-Z0-9]{5,12}$/;
        var id = $(this).val();
        if (idChk.test(id)) {
            $(this).next().css('color', 'green');
            $(this).next().html("중복체크를 진행해주세요.");
            $("#dupleChk").css('display', 'block');

        } else {
            $(this).next().css('color', 'red');
            $(this).next().html("조건에 일치하지 않습니다.");
            $(this).attr("pass", "false");
            $("#dupleChk").css('display', 'none');
        }
    });
    $("#memberPw").keyup(function() {
        var pwChk = /^[a-zA-Z0-9]{5,12}$/;
        var pw = $(this).val();
        if (pwChk.test(pw)) {
            $(this).next().css('color', 'green');
            $(this).next().html("사용가능합니다.");
            $(this).attr("pass", "true");

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

    $("#check_agree").click(function() {

        if ($(this).prop("checked")) {
            $(this).attr("pass", "true");

        } else {
            $(this).attr("pass", "false");
        }

    });
    $("#memberMail").change(function() {
        if ($(this).val() == "") {
            $(this).attr("pass", "false");
        } else {
            $(this).attr("pass", "true");
        }

    });

    $("#nickName").change(function() {
        if ($(this).val() == "") {
            $(this).attr("pass", "false");
        } else {
            $(this).attr("pass", "true");
        }

    });

    $("#memberName").change(function() {
        if ($(this).val() == "") {
            $(this).attr("pass", "false");
        } else {
            $(this).attr("pass", "true");
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
    
    $("#cancelUse").click(function(){
    	$("#memberId").attr("readonly",false);
    	$("#dupleChk").css("display","block");
        $("#cancelUse").css("display","none");
        checkNo = checkNo - 1;
    });

    $("#dupleChk").click(function(){
        var memberId = $("#memberId").val();
        var sel = false;
        $.ajax({
            url : "/dupleChk",
            type : "get",
            data : {memberId:memberId},
            success : function(data){
            	if(data=="true"){
            		sel = confirm("해당 아이디는 사용하실 수 있습니다. 사용하시겠습니까?");
            		if(sel){
            			checkNo = checkNo + 1;
            			$("#memberId").attr("readonly",true);
            			$("#dupleChk").css("display","none");
            			$("#cancelUse").css("display","block");
            		}
            		}
            	else{
            		alert("사용하실 수 없습니다.");
            	}
            }   
        });
    });
    
});

function form_check() {
    $("input").each(function(index, item) {
        if ($(item).attr("pass") == "true") {
            checkNo = checkNo + 1;
            count = count+1;
        } 

    });
    console.log(checkNo);
    if (checkNo == 8) {
        return true;
    } else {
    	alert("모든 정보를 입력해주세요");
    	checkNo = checkNo-count;
    	count = 0;
    	console.log(checkNo);
        return false;
    }
};
</script>

</body>
</html>