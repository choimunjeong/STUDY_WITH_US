<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="personalstudy.model.vo.PersonalStudyRoom"%>
<%@page import="java.util.Calendar"%>
<%@page import="personalstudy.model.vo.PersonalStudyTask"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	ArrayList<PersonalStudyTask> pstl = (ArrayList<PersonalStudyTask>)request.getAttribute("pstl");
    	ArrayList<String> gpstl = (ArrayList<String>)request.getAttribute("gpstl");
    	int i = 0;
    	Calendar cal = Calendar.getInstance();
    	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    	String korDay = "";
    	switch(dayOfWeek){
    	case 1:
    		korDay = "일";
    		break;
    	case 2:
    		korDay = "월";
    		break;
    	case 3:
    		korDay = "화";
    		break;
    	case 4:
    		korDay = "수";
    		break;
    	case 5:
    		korDay = "목";
    		break;
    	case 6:
    		korDay = "금";
    		break;
    	case 7:
    		korDay ="토";
    		break;
    	}
    	int Tminute;
    	int Tsec;
    	int TMilli;
    	PersonalStudyRoom NTime = (PersonalStudyRoom)request.getAttribute("NTime");
    	if(NTime == null)
    	{
    		 Tminute = 0;
       		 Tsec = 0;
       		 TMilli = 0;
    	}
    	else{
    		String str = NTime.getStudyTotalTime();
        	str = str.replaceAll(" ", "");
        	String[] strA= str.split(":");
        	 Tminute = Integer.parseInt(strA[0]);
       		 Tsec = Integer.parseInt(strA[1]);
       		 TMilli = Integer.parseInt(strA[2]);
    	}
    	

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        .wrap {
            width: 1200px;
            margin: 0 auto;
        }

        .header {
            width: 1200px;
            height: 100px;
        }

        .myplan {
            overflow: hidden;
        }

         .leftMenu {
        width: 182px;
        height: 920px;

        float: left;
    }

        .participatingGroup {
            float: left;
            height: 920px;
        }

          .groupListTitle {
        width: 100%;
        height: 53px;
        text-align: center;
        line-height: 53px;
        color: white;
        font-weight: bold;
        background: #75D701;
        padding-left: 250px;
    }

        .groupList {
            width: 1018px;
            height: 867px;
            display:flex;
        }


      .leftMenuList{
        padding: 0;
        margin: 0;
        list-style-type: none;
        text-indent: 25px;
        line-height: 58px;
    }
    .leftMenuA{
        display: block;
        width: 100%;
        height: 58px;
        color: black;
        font-size: 13px;
        font-weight: bold;
        text-decoration: none;
        border-left: 10px solid white;
    }
    .leftMenuA:hover{
        color: black;
        text-decoration: none;
        border-left: 10px solid #75D701;
    }
    /* 해당 페이지의 메뉴를 고정으로 */
    .leftMenuList>li:nth-child(2)>a{
        border-color: #75D701;
    }
   
    .leftMenuList>li:first-child{
        text-indent: 0;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }

        /* ---------------------------------------------------------- */
        .blank {
            /* 공백용... */
            height: 80px;
        }

        span {
            display: inline-block;
            text-align: center;
            font-size: 20px;
        }

        .gl1 {
            width: 25%;
            height: 70px;
            line-height: 70px;
        }

        .gl2 {
            width: 50%;
        }

        .gl3 {
            width: 20%;
        }

        .gl {
            font-family: Roboto;
        }

        .groupList {
            padding:
        }

        a {
            text-decoration: none;
            color: black !important;
        }

        a:hover {
            font-weight: bold;
        }

        .groupList {
            padding: 0;
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 800px;
            margin: 0 auto;
        }

        .group_goal {
            height: 80%;
            width: 30%;
            margin: auto;
            margin-top:50px;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            background-color: #F1F1F1;
            color:black;
        }

        .today_goal {
        color:black;
            margin: auto;
            height: 80%;
            width: 60%;
             border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            background-color: #F1F1F1;
             margin-top:50px;
            display: flex;
            flex-wrap: wrap;
            align-content: flex-start;
        }

        .goal_title {
            width: 100%;
            height: 10%;
            text-align: center;
            line-height: 70px;
            font-weight: bold;
            position: relative;
            font-size: 15px;
        }
        .goal_area{
            border: 1px solid black;
            margin: auto;
            height: 7%;
            box-shadow: 2px 2px 2px grey;
            margin-top: 15px;
            border-radius: 5px;
            text-align: center;
            line-height:50px;
            font-weight: bold;
            color:white;
        }
        .groups{
            width: 90%;

        }
        .deleteBtn{
        	position:absolute;
        	right:20px;
        	top:-1px;
        }
        .today{
            width: 40%;
            position:relative;
        }
        .groups{
        position:relative;
        }
        button>img{
            height: 100%;
            width: 100%;
        }
        .goal_title>a{
            width: 30px;
            height: 30px;
            position: absolute;
            right: 25px;
            font-size: 24px;
          	
        }

        .goal_title>img{
            width: 30px;
            height: 30px;
            right: 42%;
            top: -12px;
        }
        .goal_title>img{
            position: absolute;
        }
		.groups>a{
		width: 30px;
            height: 30px;
            position: absolute;
            left: 25px;
            font-size: 24px;
            top:-3px;
		}
		.today>a:first-child{
			width: 30px;
            height: 30px;
            position: absolute;
            left: 25px;
            font-size: 24px;
            top:-3px;
		}
		span{
			font-size:15px;
		}
		.today>a:nth-child(2){
			color:white;
		}
			.timeInputs>img {
        	margin-right:7px;
        }
        
        .timeInputs{
        	height:53px;
        	float:right;
        	line-height:3px;
        }
        #timeInput{
        	margin-top:7px;
        	width:130px;
        	text-align:center;
        	height:40px;
        	outline:none;
        	font-size:22px;
        	background-color:white;
        	color:black;
        	border: 1px solid #fff;
        	border-radius: 10px;
        }
        #timeSet{
        	width:100px;
        	height: 30px;
        	background-color: rgba(94,252,3,0.3);
        	outline:none;
        	border-radius:5px;
        	border:none;
        	margin-right: 10px;
        	color:white;
        }
        #stop{
        	display:none;
        }

    </style>
</head>
<body>
<div style="height: 100px;">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="modal">
		
	</div>
 <section>
        <div class="wrap">
            <div class="myplan">
                    <div class="groupListTitle"><%=cal.get(Calendar.YEAR)%>년 <%=cal.get(Calendar.MONTH)+1%>월 <%=cal.get(Calendar.DAY_OF_MONTH)%>일 (<%=korDay%>) 오늘 할 일
                    	<div class="timeInputs">
                        <img src="/img/today_time_icon_black.png" style="width:33px; height:33px;">
                        <img id="start" src="/img/today_time_start.png" style="width:25px; height:26px;" onclick="func6();">
                        <img id="stop" src="/img/today_time_stop.png" style="width:25px; height:25px;" onclick="func7();">
                        <img id="pause" src="/img/today_time_pause.png" style="width:25px; height:25px;" onclick="func8();">

                        <form action="/personalStudyRoomInsert?memberNo=<%=m.getMemberNo()%>" method="post" style="display:inline;">                        
                        <input type="text" id="timeInput" name="time" placeholder="<%=Tminute %> : <%=Tsec%> : <%=TMilli%>">
                        <input type="submit" id="timeSet" value="시간 저장"> 
                        </form>
                    </div>
                    </div>
                <div class="leftMenu">
                    <ul class="leftMenuList">
                        <li>My Plan</li>
 						<li><a class="leftMenuA" href="/todayPlan?memberNo=<%=m.getMemberNo()%>"><img src="/img/calender2.png" style="margin-right: 10px;">일일 계획</a></li>
                        <li><a class="leftMenuA" href="/myStudyCalender?memberNo=<%=m.getMemberNo()%>"><img src="/img/day2.png" style="margin-right: 10px;">나의 스케줄</a></li>
                        <li><a class="leftMenuA" href="/myGroupStudyList?memberNo=<%=m.getMemberNo()%>"><img src="/img/group_icon2.png" style="margin-right: 10px;">참여중인 스터디</a></li>
                    </ul>
                </div>
                <div class="participatingGroup">
                    <div class="groupList">
                        <div class="group_goal" ondrop="drop(this,event);" ondragenter="return false;" ondragover="return false;">
                            <div class="goal_title">
                                그룹 스터디 목표
                                <img src="/img/fin1.png">
                            </div>
                            <%for(String gl : gpstl){ i++;%>
                             <div class="goal_area groups" draggable="true" ondragstart="drag(this, event)" style="background-color:black" id="groups<%=i%>">
                       		<a href="#"><img src="/img/Ellipse.png"></a>
                        	  <span><%=gl %></span>
                            </div>
                            <%} %>
                           
                        </div>
                        
                        <div class="today_goal" ondrop="drop(this,event);" ondragenter="return false;" ondragover="return false;">
                            <div class="goal_title">
                                오늘의 목표
                                <a href="#" id="addList" onclick="openPop();">+</a>
                            </div>

                            <%for(PersonalStudyTask pst : pstl) {%>
                            <%if(pst.getTaskOrder() == 1) {%>
                             <div class="goal_area today" style="background-color:<%=pst.getTaskColor()%>" id="today">
                             <a></a>
                                <%=pst.getTaskContent() %>
                                <a href="#" class="deleteBtn"><img src="/img/deleteBtn.png"></a>
                            	</div>
                            <%} else{%>
                            <div class="goal_area today" style="background-color:<%=pst.getTaskColor()%>" id="today">
                                <a href="#"><img src="/img/Ellipse.png"></a>
                                <%=pst.getTaskContent() %>
                                <a href="#" class="deleteBtn"><img src="/img/deleteBtn.png"></a>
                            	</div>
                            <%}} %>

                        </div>
                        </div>
                    </div>

                    </div>
                </div>
    </section>
    <div>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
<script>
var minute = "<%=Tminute%>";
var time = 0;
var sec = "<%=Tsec%>";
var mill = "<%=TMilli%>";
var time2;
var pause = false;
var timeInput = document.getElementById("timeInput");
var memberNo = "<%=m.getMemberNo()%>";
function drag(target, food) {		//드래그 시작시 호출 할 함수
	food.dataTransfer.setData('Text', target.id);
};
function drop(target, food) {		//드롭시 호출 할 함수
	var id = food.dataTransfer.getData('Text');
	var gList = document.getElementById(id);
	var gId = gList.getAttribute("id");
	var gElement = $('#'+gId);
	var gContent = $('#'+gId).children().next().html();
	gElement.css('width','40%');
	$.ajax({
		url : "/insertGroupTask",
		type:"post",
		data:{gContent:gContent,memberNo:memberNo},
		success : function(data){
			if(data){
				location.reload();
			}
			else{
				alert("이동 실패");
			}
		}
	});
	target.appendChild(document.getElementById(id));
	food.preventDefault();
};

		function openPop(){
			var popup = window.open('/views/popUp.jsp','리스트 추가','width=330px,height=310px,scrollbars=no');
		};
		
		$(function(){
			$(".deleteBtn").click(function(){
				var deleteIdx = $(this).parent().index();
				var memberNo = "<%=m.getMemberNo()%>";
				console.log(deleteIdx);
				$.ajax({
					url : "/deleteTask",
					type : "post",
					data : {deleteIdx:deleteIdx,memberNo:memberNo},
					success : function(data){
						if(data){
							alert("삭제에 성공했습니다.");
							location.reload();
						}
						else{
							alert("삭제 실패");
						}
					}
				});
			});
			
		});
		function func6(){
			$("#stop").css('display','inline-block');
			$("#pause").css('display','inline-block');
			$("#start").css('display','none');
			//var timeInput = document.getElementById("timeInput");
            //if(pause){
            time2 = window.setInterval(function(){	
            		//일시정지일때 시작누르면
            		var date = new Date();
                    var mill = date.getMilliseconds();
                    //p2.innerHTML = count + " : "+mill;
                    timeInput.value = minute + " : " + sec + " : "+mill;
                    if(mill >= 990){
                        sec++;
                    }
                    if(sec >= 59 && mill >= 990){
                    	minute++;
                    	sec = 0;
                    }
                    if(minute >= 59 && sec >= 59 && mill >= 990){
                    	window.clearInterval(time2);
                    }
                },10)
               // }
/*
            	else{
            		// 중지하고 시작누르면
            		 time2 = window.setInterval(function(){	
            		var date = new Date();
                    var mill = date.getMilliseconds();
                    //p2.innerHTML = count + " : "+mill;
                    minute = 0;
                    time = 0;
                    sec = 0;
                    timeInput.value = minute + " : " + sec + " : "+mill;
                    if(mill >= 990){
                        sec++;
                    }
                    if(sec >= 59 && mill >= 990){
                    	minute++;
                    	sec = 0;
                    }
                    if(minute >= 59 && sec >= 59 && mill >= 990){
                    	window.clearInterval(time2);
                    }
                },10);
            	}
                */
        }
        function func7(){
        	pause = false;
        	$("#stop").css('display','none');
        	$("#start").css('display','inline-block');
			$("#pause").css('display','inline-block');
            window.clearInterval(time2);
           minute = "<%=Tminute%>";
           sec = "<%=Tsec%>";
           mill = "<%=TMilli%>";
           timeInput.value = minute + " : " + sec + " : "+mill;
        }
        function func8(){
        	pause = true;
        	$("#pause").css('display','none');
        	$("#start").css('display','inline-block');
			$("#stop").css('display','inline-block');
			window.clearInterval(time2);
        }

	        //사이드메뉴바 호버기능
	        $(".leftMenuA").hover(function(){
	           $(".leftMenuA").attr("style","border-color : white");
	           $(this).attr("style","border-color : #75D701");
	        },function(){
	           $(".leftMenuA").attr("style","border-color : white");
	           $(".leftMenuA").eq(0).attr("style","border-color : #75D701");
	        });
</script>
</body>
</html>