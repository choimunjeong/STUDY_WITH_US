   $(function(){
            $("#id_Input").focusin(function(){
                $(this).prev().animate({
                    "top":"-20px",
                    "font-size":"10px"
                }); 
            });
            
             $("#id_Input").focusout(function(){
                if($(this).val()==""){
                      $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                }
            });
            
              $("#pass_Input").focusin(function(){
                $(this).prev().animate({
                    "top":"-20px",
                    "font-size":"10px"
                }); 
            });
            
             $("#pass_Input").focusout(function(){
                if($(this).val()=="")
                    {
                        $(this).prev().animate({
                    "top":"0px",
                    "font-size":"15px"
                }); 
                    }
            });
        
        })