<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
    Member m = (Member)session.getAttribute("member");
    %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/header/style.css">
<link rel="stylesheet" href="/css/header/responsive.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<style>
   .nav{
      width: 1200px;
        margin: 0 auto;
        position:relative;
   }
    .nav-wrap {
        position: absolute;
        z-index: 10;
        width: 1200px;
        overflow: hidden;
        margin: 0 auto;
        font-family: Roboto;
        background-color: #fff;
    }
    .nav-left {
        margin-top: 20px;
        width: calc(100%/6);
        height: 100%;
        float: left;
        text-align: center;
    }
    .nav-center {
        width: calc((100%/3)*2);
        float: left;
    }
    .nav-right {
        width: calc(100%/6);
        float: left;
        overflow: hidden;
    }
    .nav-right>div {
        width: calc(100%/3);
        float: left;
        text-align: center;
        line-height: 100px;
    }
    .nav-right>div>a{
        color: black;
    }
    ul {
        list-style-type: none;
    }
    a {
        text-decoration: none;
    }
    .nav-center>ul {
        overflow: hidden;
        margin: 0;
        padding-left: 0;
    }
    .nav-center>ul>li {
        float: left;
        width: calc(100%/4);
        text-align: center;
    }
    .nav-center>ul>li>a {
        display: inline-block;
        width: 100%;
        height: 100px;
        line-height: 100px;
        font-weight: bold;
        font-size: 18px;
        color: black;
    }
    .nav-sub {
        width: 100%;
        padding: 0;
        display: none;
        position: relative;
    }
    .nav-sub>li {
        width: 100;
    }
    .nav-sub>li>a {
        display: inline-block;
        width: 100%;
        height: 40px;
        font-weight: bold;
        font-size: 15px;
        color: black;
        line-height: 30px;
    }
    .nav-center>ul>li:hover>a {
        cursor: pointer;
    }
    .alarmNote{
    	width : 300px;
    	height:500px;
    	position:absolute;
    	right:-310px;
    	top : 15px;
    	display:none;
    	background-color: rgba(63,63,63,0.3);
    	border-radius: 10px;
    }
    .alarmNote>ul{
    padding : 0px;
    margin : 10px auto;
    width : 90%;
    height : 90%;
    margin-top : 20px;
    }

    .alarmNote>ul>li{
    	display:flex;
    	width : 100%;
    	height : calc(100%/5);
    	border-bottom : 1px solid rgba(0,0,0,0.3);
    	font-size : 12px;
    }
    .alarmNote>ul>li:first-child{
    	border-top : 1px solid rgba(0,0,0,0.3);
    }
    .alarmNote>ul>li>div>a{
    	font-size : 10px;
    }
    .alarmNote>ul>li>div:first-child{
    	width:20%;
    	border-radius: 10px;
    	height:100%;
    }
    .alarmNote>ul>li>div>img{
    	width:100%;
    	height:50%;
    	border-radius: 70%;
    	margin-top: 20px;

    }
     .alarmNote>ul>li>div:nth-child(2){
    	width:60%;
    	border-radius: 10px;
    	margin: auto;
    	color:white;
    	font-weight: bold;
    }
     .alarmNote>ul>li>div:nth-child(3){
    	width:10%;
    	height:15%;
    }
    .alarmNote>ul>li>div:nth-child(3)>img{
    	width:70%;
    	height:100%;
    	margin-top:10px;
    	object-fit : contain;
    	
    }
    .deleteToggle{
		border-radius:10px;
    	width:100px;
    	display:none;
    	height:30px;
    	position: absolute;
    	background-color: rgba(0,0,0,0.7);
    	color : orange;
    	font-size: 13px;
    	text-align: center;
    	line-height: 30px;
    }
</style>

<body>
	
   <div class="nav">
   <div class="alarmNote">
		<ul id="alarmHTML">
		</ul>
	</div>
      <div class="nav-wrap">
          <div class="nav-left">
              <a href="index.jsp"><img src="/img/navi_logo.png" width="140px" height="70px"></a>
          </div>
          <div class="nav-center">
              <ul>
                  <li>
                  <% if(m!=null){ %>
                   <a href="/todayPlan?memberNo=<%=m.getMemberNo()%>">My Plan</a>
                    <ul class="nav-sub">
                          <li><a href="/todayPlan?memberNo=<%=m.getMemberNo()%>">개인스터디</a></li>
                          <li><a href="/myGroupStudyList?memberNo=<%=m.getMemberNo()%>">그룹스터디</a><br>
                  <%}else {%>
                    <a href="/todayPlan?memberNo=0">My Plan</a>
                   <ul class="nav-sub">
                          <li><a href="/todayPlan?memberNo=0">개인스터디</a></li>
                          <li><a href="/myGroupStudyList?memberNo=0">그룹스터디</a><br>
                  <%} %>
                          </li>
                      </ul>
                  </li>
                  <li>
                      <a href="/groupStudyList?reqPage=1">스터디 찾기</a>
                  </li>
                  <li>
                  <%if(m!=null) {%>
                  <a href="/eventList?memberNo=<%=m.getMemberNo()%>&reqPage=1">이벤트</a>
                   <ul class="nav-sub">
                    		<li><a href="/eventList?memberNo=<%=m.getMemberNo()%>&reqPage=1">이달의 이벤트</a></li>
                    </ul>
                  <%}else{ %>
                  <a href="/eventList?memberNo=0&reqPage=1">이벤트</a>
                      <ul class="nav-sub">
                          <li><a href="/eventList?memberNo=0&reqPage=1">이달의 이벤트</a></li>
                      </ul>
                  <%} %>
                      
                  </li>
                  <li>
                      <a href="/noticeList?reqPage=1">고객센터</a>
                  </li>
              </ul>
          </div>
          <div class="nav-right">
             <%if(m == null) {%>
              <div>
                  <a href="/views/login.jsp">로그인</a>
               </div>
               <div>
                   <a href="/joinFrm"> 회원가입</a>
               </div>
              <%}else {%>
              <div>
                 <a href="/logout"><img src="/img/logout_icon.png"></a>
              </div>
              <div>
                 <a href="/myPage?memberNo=<%=m.getMemberNo()%>&alPage=1&glPage=1"><img src="/img/login_logo1.png"></a>
              </div>
              <div>
              	<a href="#" id="alarmOpen" status="close" value=<%=m.getMemberNo() %>><img src="/img/login_logo2.png"></a>
              </div>
              <%} %>
          </div>
      </div>
   </div>
   <script>
 
      $(document).on("click",".deletes",function(){
    	 var deleteElement = $(this).children().next();
    	 var alvalues = $(this).attr("value");
    	 	deleteElement.show();
    	 	deleteElement.click(function(){
    	 		$.ajax({
    	 			url : "/updatePopAlarm",
    	 			type : "post",
    	 			data : {alvalues:alvalues},
    	 			success : function(){
    	 			}
    	 		});
    	 	});
   		});
      
   $(".nav-center").bind({
       mouseenter:function() {
           var $btn = $('.nav-sub');
           if(!$btn.is(':animated')) {
               $btn.slideDown();
           }
       },
       mouseleave: function() {
           $(".nav-sub").slideUp();    
       }
   });
       $(".nav-right>div>img").hover().css("cursor","pointer");
   
       
       $("#alarmOpen").click(function(){
    	   if($(this).attr("status")=="close"){
    		   $(".alarmNote").fadeIn();
   	   		$(this).attr("status","open");
    	   }
    	   else{
    			  $(".alarmNote").fadeOut(); 
    			  $(this).attr("status","close");
    	   }
    
       });
       
       // 웹 소켓
       // 웹 소켓 객체 생성 -> 자동 연결 실행
      		var mem = "<%=m%>";
      		if(mem != "null"){
      			var memberNo = $("#alarmOpen").attr("value");
      			 var webSocket = new WebSocket("ws://localhost/webSocket");
                 var interval = setInterval(function() {
            			webSocket.send(memberNo);
               	}, 2000);
      		}
      		webSocket.onmessage = function(message) {
      		// 콘솔 텍스트에 메시지를 출력한다.
      		$("#alarmHTML").html(message.data);
      		};
      
		
   </script>   

</body>