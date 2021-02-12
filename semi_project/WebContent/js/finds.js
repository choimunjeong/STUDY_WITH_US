/**
 * 
 */
 		 var selN = 1;
       $(function(){
            $("#selId").parent().css('border-right','none');
            $("label").click(function(){
            	$("#resultText").html("");
               $(this).prev().prop("checked",true); 
                if($(this).prev().prop("checked") == true)
                    {
                        if($(this).prev().attr("id") == "selId")
                            {
                            	selN = 1;
                                $("#findPw").css('display','none');
                                $("#findId").css('display','block');
                                $(this).parent().css("border-bottom","none");
                                $(this).parent().next().css({
                                    'border-top':'none',
                                    'border-right' : 'none'
                                });
                                $("#selPw").next().css('color','grey');
                                 $(this).css('color','#3D9D63');
                                $(this).parent().css({
                                    'border-top':'1px solid #3D9D63',
                                    'border-right' : '1px solid #3D9D63'
                                });
                                $("#selPw").parent().css('border-bottom','1px solid #3D9D63');
                                $("button").html("아이디 찾기");
                                $("#findPw input").attr("required",false);
                                console.log("지움");
                            }
                        else{
                        	selN = 2;
                           $("#findId").css('display','none');
                                $("#findPw").css('display','block');
                                $(this).parent().css("border-bottom","none");
                                $(this).parent().prev().css({
                                    'border-top':'none',
                                    'border-left' : 'none'
                                });
                             $(this).parent().css({
                                    'border-top':'1px solid #3D9D63',
                                    'border-right' : '1px solid #3D9D63'
                                });
                             $("#selId").parent().css('border-bottom','1px solid #3D9D63');
                            $(this).css('color','#3D9D63');
                                $("#selId").next().css('color','grey');
                            $("button").html("비밀번호 찾기");
                             $("#findId input").attr("required",false);
                            console.log("지움2");
                        }
                         
                    }
              
            });
            
            $("#searchId").focusin(function(){
                $(this).prev().animate({
                    "top":"-12px",
                    "font-size":"10px"
                }); 
            });
            
             $("#searchId").focusout(function(){
                if($(this).val()==""){
                      $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                }
            });
            
                         $("#searchName").focusin(function(){
                $(this).prev().animate({
                    "top":"-12px",
                    "font-size":"10px"
                }); 
            });
            
             $("#searchName").focusout(function(){
                if($(this).val()==""){
                      $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                }
            });
            
              $("#searchEmail").focusin(function(){
                $(this).prev().animate({
                    "top":"-12px",
                    "font-size":"10px"
                }); 
            });
            
             $("#searchEmail").focusout(function(){
                if($(this).val()=="")
                    {
                        $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                    }
            });
            
               $("#searchMail").focusin(function(){
                $(this).prev().animate({
                    "top":"-12px",
                    "font-size":"10px"
                }); 
            });
            
             $("#searchMail").focusout(function(){
                if($(this).val()=="")
                    {
                        $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                    }
            });
      
        })
