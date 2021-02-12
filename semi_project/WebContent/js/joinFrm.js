/**
 * 
 */
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
                    $(this).next().html("사용가능합니다.");
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
                    console.log("닉네임 트루");
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
            	console.log(checkNo);
                return false;
            }
        };/**
 * 
 */