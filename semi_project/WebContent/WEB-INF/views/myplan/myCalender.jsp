<%@page import="org.json.simple.JSONArray"%>
<%@page import="personalstudy.model.vo.PersonalStudyTask"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   JSONArray calendarData = (JSONArray)request.getAttribute("calendarData");
%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script>
<head>
    <link href='../css/mycalendar.css' rel='stylesheet' />
    <script src='../js/mycalendar.js'></script>
    <meta charset="UTF-8">
    <title>나의 스케줄</title>
    <style>
  .wrap{
        width: 1200px;
        margin: 0 auto;
    }

    .header {
        width: 1200px;
        height: 100px;
    }

    .myplan{
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
    }

    .groupList {
        width: 1018px;
        height: 867px;
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
    .leftMenuList>li:nth-child(3)>a{
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
            color: black;
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
            max-width: 900px;
            margin: 0 auto;
        }

        /* 모달------------------------------------------------------------ */
      .modalBtnDiv{
         text-align: center;
      }   
      #btn{
         outline: none;
         width: 156px;
         height: 53px;
         background: #6ED078;
         border-radius: 5px;
         color: white;
         font-size: 20px;
         font-weight: bold;
      }
      
       .modal-wrap123, .modal-wrap1234{
         position: absolute;
         top: 0;
         left: 0;
         width: 100vw;
         height: 1000vh;
         background-color: rgba(0,0,0,0.3);
         justify-content: center;
         align-items: center;
         display:none;
         z-index:100;
       }
       .modal123{
         background-color: #fff;
         position: absolute;
         top:300px;
         left:34vw;
         width: 40vw;
         max-width: 500px;
         min-width: 300px;
         border-radius: 10px;
         box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5);
       }
       .modal-top123, .modal-top123>h4{
         text-align: center;
         color:green;
       }
       .modal-content123{
          padding-left: 50px;
          padding-right: 50px;
       }
       .modal-button123>input{
          background-color: white;
          font-weight: bold;
            outline: none;
            width: 49%;
            height:30px;
            border: 0;
            box-sizing: border-box;
            padding: 0;
            margin-bottom: 10px;
       }
       .modal-wrap1234>div>div>h4{
          display:inline-block;
          margin-left: 50px;
       }
       .btn{
          float:right;
          margin-right:10px;
       }
        .backColor>li{
          float:left;
          margin-left: 5px;
       }

       
       input[type=radio]{
          display:none;
       }
    </style>
</head>
<script>
   
    document.addEventListener('DOMContentLoaded', function() {
       /*서블릿에서 받아온 JSONArray*/
        var list = ${calendarData};

       var calendarEl = document.getElementById('calendar');        
        var now = new Date();
        var modal = document.getElementById("modal");
        var calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,listMonth'
            },
            initialDate: now,
            selectable: true,
            businessHours: true,
            dayMaxEvents: true,
            //달력 날짜 누르면 모달창 뜨게
            select: function(arg) {
                $(".modal-wrap123").show();
                $("#startDate").val(arg.start);
                $("input[type=submit]").click(function(){
                  });
            },
            //달력에 저장된 일정 누르면
            eventClick: function(arg) {
                  var taskNo = arg.event.id;
                  $("#taskNo").val(taskNo);
              $.ajax({
                 url : "/calendarSelectOneDate",
                 type : "get",
                 data : {taskNo:taskNo},
                 success : function(data){
                    //data는 JSON형태 즉, 객체 형태
                    var title = decodeURIComponent(data.title);
                    var start = decodeURIComponent(data.start);
                    var end = decodeURIComponent(data.end);
                    var color = decodeURIComponent(data.color);
                    console.log(color);
                    $(".modal-wrap1234").show();
                        $("#startDateUpdate").val(start);
                        $("#endDateUpdate").val(end);
                        $("#contentUpdate").val(title);
                        $("#colorUpdate").val(color);
                        $("#taskNo").val(arg.event.id);
                 }
              });
                /*삭제버튼*/
                $("#delete").click(function(){             
                   var idx = thisClick(taskNo);
                   if(idx != -1){
                      $.ajax({
                          url : "/calendarDelete",
                          type : "get",
                          data : {taskNo:taskNo},
                          success : function(data){
                             alert(data);
                             $(".modal-wrap1234").hide();
                             arg.event.remove();
                          
                          },
                         error : function(){
                            console.log("실패");
                         }
                       });
                   }
                  });
             },
             events: list
         });
         calendar.render();
         
         $(function(){
            //모달 취소버튼
             $("input[type=button]").click(function(){
                $(".modal-wrap123").hide();
                $(".modal-wrap1234").hide();
               });
         });
         
         //json에서도 삭제
         function thisClick(taskNo){
            var idx = list.findIndex(function(item){return item.id === taskNo});
            if(idx != -1){
                list.splice(idx,1);
            }
            return idx;
         }
        
        //사이드메뉴바 호버기능
        $(".leftMenuA").hover(function(){
           $(".leftMenuA").eq(1).attr("style","border-color : white");
           $(this).attr("style","border-color : #75D701");
        },function(){
           $(".leftMenuA").attr("style","border-color : white");
           $(".leftMenuA").eq(1).attr("style","border-color : #75D701");
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
        
    });
    
    

</script>

<body>
    <section>
        <div class="wrap">
            <div class="header">
                <%@ include file="/WEB-INF/views/common/header.jsp"%>
            </div>
            <div class="myplan">
                    <div class="groupListTitle">나의 스케줄</div>
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
                        <br><br>
                        <!-- 달력 -->
                        <div id='calendar'></div>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
        </div>
      
      <!-- 일정 추가 모달 -->
        <div class="modal-wrap123">
            <div class="modal123">
                <div class="modal-top123">
                    <br>
                    <h4>스케줄 추가</h4>
                    <hr>
                </div>
                <form action="/calendarInsert?memberNo=<%=m.getMemberNo() %>" method="post">
                    <div class="modal-content123">
                       <p>시작날짜 : <input id="startDate" name="startDate" type="text" value="" readonly style="border:0px;"></p>
                        <p>종료날짜 : <input id="startDate" name="endDate" type="date" required="required"></p>
                        <hr>
                        
                         <ul class="backColor">
                         <li><p style="display:inline-block">스케줄 색상 : </p></li>
                        <li>
                            <input type="radio" id="ex1" name="color" value="#D979BF">

                            <label for="ex1"><div style="background-color:#D979BF; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="ex2" name="color" value="#934ED9">

                            <label for="ex2"><div style="background-color:#934ED9; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                       
                        <li>
                            <input type="radio" id="ex4" name="color" value="#AEACF2">

                            <label for="ex4"><div style="background-color:#AEACF2; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="ex6" name="color" value="#7EBF60">

                            <label for="ex6"><div style="background-color:#7EBF60; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="ex7" name="color" value="#BAF2A0">

                            <label for="ex7"><div style="background-color:#BAF2A0; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="ex8" name="color" value="#f2f263">

                            <label for="ex8"><div style="background-color:#f2f263; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                    </ul>
                        <hr style="margin-top:55px;">
                        <p style="display:block;">스케줄에 추가할 내용을 입력하세요</p>
                        <textarea rows="2" cols="50"  maxlength="33"name="content" style="resize: none" required="required"></textarea>
                    <hr>
                    <div class="modal-button123">
                        <input type="submit" value="등록">
                        <input type="button" value="취소">
                    </div>
                    </div>

                </form>
            </div>
         </div>
         
        <!-- 일정 선택시 모달(수정, 삭제) -->
        <div class="modal-wrap1234">
            <div class="modal123">
                <div class="modal-top123">
                    <br>
                    <h4>스케줄 확인</h4><button class="btn btn-danger btn-sm" id="delete">삭제</button>
                    <hr>
                </div>
                <form action="/calendarUpdate?memberNo=<%=m.getMemberNo() %>" method="post">
                    <div class="modal-content123">
                       <input type="text" name="taskNo" style="display:none;" id="taskNo">
                       <p>시작날짜 : <input id="startDateUpdate" name="startDate" type="text" value="" readonly style="border:0px;"></p>
                        <p>종료날짜 : <input id="endDateUpdate" name="endDate" type="date" required="required"></p>
                        <hr>
                       
                         <ul class="backColor">
                         <li><p style="display:inline-block">스케줄 색상 : </p></li>
                        <li>
                            <input type="radio" id="e1" name="color" value="#D979BF">
                            <label for="e1"><div style="background-color:#D979BF; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="e2" name="color" value="#934ED9">
                            <label for="e2"><div style="background-color:#934ED9; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                       
                        <li>
                            <input type="radio" id="e4" name="color" value="#AEACF2">
                            <label for="e4"><div style="background-color:#AEACF2; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="e6" name="color" value="#7EBF60">
                            <label for="e6"><div style="background-color:#7EBF60; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="e7" name="color" value="#BAF2A0">
                            <label for="e7"><div style="background-color:#BAF2A0; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                        <li>
                            <input type="radio" id="e8" name="color" value="#f2f263">
                            <label for="e8"><div style="background-color:#f2f263; width:30px; height:30px; border-radius:10px;"></div></label>
                        </li>
                    </ul>
                        <hr style="margin-top:55px;">
                        <p style="display:block;">수정 할 내용을 입력하세요</p>
                        <textarea id="contentUpdate" rows="2" maxlength="33" cols="50" name="content" style="resize: none" required="required"></textarea>
                    <hr>
                    <div class="modal-button123">
                        <input type="submit" value="수정하기">
                        <input type="button" value="취소">
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>

</html>