<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Member m = (Member)session.getAttribute("member");
    %>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<!DOCTYPE html>
<html>
<head>
<style>
   body{
      background-color: #fff;
   }
    .pop{
        width: 400px;
        height: 285px;
        margin: auto;
    }
    div{
        text-align: center;
    }
    .pop>div:first-child{
        width: 100%;
        height: 15%;
        line-height: 50px;
        font-weight: bold;
        font-size: 18px;
        border-bottom: 1px solid black;
        color:green;
    }
      .pop>div:nth-child(2){
      font-size: 12px;
      font-weight: bold;
    }
    #listContent{
        width: 300px;
        height: 100px;
        margin-top: 15px;
    }
    button{
        width: 40%;
        margin-top: 10px;
        height: 30px;
        outline: none;
        background-color: transparent;
    }
    button:first-child{
        color: green;
    }
    button{
       border:none;
       outline:none;
       cursor:pointer;
    }
    .pop>div:nth-child(3){
        border-top: 1px solid black;
        margin-top: 50px;
        padding-bottom : 20px;
        border-bottom: 1px solid rgba(0,0,0,0.2);
    }


     .backColor>li{
       float:left;
       margin-left: 5px;
       list-style: none;
    }
    
    
    input[type=radio]{
       display:none;
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form action="/insertPersonalList?memberNo=<%=m.getMemberNo()%>" method="post">
     <div class="pop">
        <div>
         Enter your plan for today
        </div>
         
          <ul class="backColor">
          <li><div>스케줄 색상 </div></li>
         <li>
             <input type="radio" id="ex1" name="color" value="#D979BF">

             <label for="ex1"><div style="background-color:#D979BF; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
         <li>
             <input type="radio" id="ex2" name="color" value="#934ED9">

             <label for="ex2"><div style="background-color:#934ED9; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
        
         <li>
             <input type="radio" id="ex4" name="color" value="#AEACF2">

             <label for="ex4"><div style="background-color:#AEACF2; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
         <li>
             <input type="radio" id="ex6" name="color" value="#7EBF60">

             <label for="ex6"><div style="background-color:#7EBF60; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
         <li>
             <input type="radio" id="ex7" name="color" value="#BAF2A0">

             <label for="ex7"><div style="background-color:#BAF2A0; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
         <li>
             <input type="radio" id="ex8" name="color" value="#f2f263">

             <label for="ex8"><div style="background-color:#f2f263; width:25px; height:25px; border-radius:10px;"></div></label>
         </li>
     </ul>
        <div><input type="text" id="listContent" name="list_title"></div>
        <div><button type="submit" id="createBtn">생성</button>
        <button id="rejectBtn">취소</button></div>
    </div>
   </form>
  <script>
     $(function(){
        $("#rejectBtn").click(function(){
           window.close();
        });
        
        /*색상 선택되면 테두리*/
        $(".backColor>li>label").click(function() {
            $(".backColor>li>label").each(function(index, item) {
                $(item).prev().attr("pass", "false");
            });
            $(this).prev().attr("pass", "true");
              $(".backColor>li>label").each(function(index, item) {
                if($(item).prev().attr("pass")=="true"){
                    $(item).children().css("border","2px solid black");
                }
                else{
                    $(item).children().css("border","none");
                }
            });
            
        });
     })
  </script>
</body>
</html>