<%@page import="eventboard.model.vo.EventBoard"%>
<%@page import="noticeboard.model.vo.NoticeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    	ArrayList<EventBoard> list = (ArrayList<EventBoard>)request.getAttribute("list");
		String pageNavi = (String)request.getAttribute("pageNavi");
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>이벤트</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.js"></script>
<style>
    .wrap{
        width: 1200px;
        margin: 0 auto;
    }

    .header {
        width: 1200px;
        height: 100px;
        background: green;
    }

    .myplan{
        overflow: hidden;
    }
    
    .participatingGroup {
    	width:100%;
        height: 920px;
        margin: 0 auto;
        text-align: center;
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
        width: 100%;
        height: 867px;
    }
    
    a>div{
    	color:black;
    }


 /*이벤트 박스 관련*/
        .box {
            width: 40%;
            height: 150px;
            margin: 20px;
            display: inline-block;
            overflow: hidden;
            font-size: 20px;
            background-color: #F8FFF7;
        }

        .box>div:first-child {
            float: left;
            font-size:25px;
            text-align:left;
            margin: 50px 0px 0px 20px;
        }

        .box>div:last-child {
            float: right;
            margin:10px 10px 0px 0px;
        }

        .box>div>div:last-child {
            font-size: 13px;
        }

        .img-box {
            width: 130px;
            height: 130px;
        }

        .box left,
        .eventTitle {
            float: left;
        }

        .box right,
        .endDate {
            float: right;
        }

        .eventTitle,
        .endDate {
            margin-left: 10px;
            margin-right: 10px;
        }

        .eventTitle {
            font-size: 15px;
            color: #313131;
        }

        .endDate {
            color: #D80000;
        }

        hr {
            background-color: rgba(244, 244, 244, 0.05);
        }

        .none {
            opacity: 0%;
        }

        .pagination {
            justify-content: center;
        }
        
        .img-box, .img-box>img{
            width: 130px;
            height:130px;
            border-radius: 10px;
        }
</style>

<body>
<div><%@ include file="/WEB-INF/views/common/header.jsp" %></div>
    <section>
        <div class="wrap">
            <div class="header"></div>
            <div class="myplan">
            <div class="groupListTitle">이달의 이벤트</div>
                <div class="participatingGroup">
                    <div class="groupList">
                        <section>
                        <br>
                            <%int count = 0; %>

                            <%for(EventBoard e : list){ %>
                            <%count++; %>
                            <%if(count%2 != 0){ %>
                            <a href="/eventManagerView?eventNo=<%=e.getEventNo() %>&manager=no">
							<div class="box left">
								<div>
									<div><%=e.getEventTitle() %></div>
									<div><%=e.getEventEnrollDate() %> ~ <%=e.getEventEndDate() %></div>
								</div>
								<div>
									<div class="img-box">
									<%if(e.getFilepath()!=null){ %>
									<img src="/upload/event/<%=e.getFilepath() %>" alt="이벤트 이미지">
									<%} %>
									</div>
								</div>
							</div>
							</a>
                            <%} else{  %>
                            <a href="/eventManagerView?eventNo=<%=e.getEventNo() %>&manager=no">
							<div class="box right">
								<div>
									<div><%=e.getEventTitle() %></div>
									<div><%=e.getEventEnrollDate() %> ~ <%=e.getEventEndDate() %></div>
								</div>
								
								<div>
									<div class="img-box">
									<%if(e.getFilepath()!=null){ %>
									<img src="/upload/event/<%=e.getFilepath() %>" alt="이벤트 이미지">
									<%} %>
									</div>
								</div>
								
							</div>
							</a>
                            <hr>
                            <%} } %>

                            <!-- 여백 맞추려고 빈 박스 생성 -->
                            <%if(count%2 != 0){ %>
                            <div class="box none"></div>
                            <%} %>

                            <div>
                                <ul class="pagination text-center">
                                    <%=pageNavi %>
                                </ul>
                            </div>
                        </section>


                        <script>
                            $(document).ready(function() {
                                //d-day
                                var endDates = $(".endDate");
                                endDates.each(function(idx, item) {
                                    var date = $(item).html();
                                    var dday = dayCal(date);
                                    if (dday < 0) { //종료일지가 지난경우
                                        $(item).html("종료");
                                    } else if (dday == 0) { //D-DAY인경우
                                        $(item).html("D-" + "마지막날");
                                    } else {
                                        $(item).html("D-" + dday);
                                    }
                                });

                                var height = $(".participatingGroup").height();
                                $(".leftMenu").height(height);
                            });

                            function dayCal(date) {
                                var now = new Date();
                                var groupEndDate = date;
                                console.log(groupEndDate);
                                var endArray = groupEndDate.split('-');
                                var endDate = new Date(endArray[0], endArray[1] - 1, endArray[2]); //달은 -1해줘야함
                                var dday = endDate.getTime() - now.getTime();
                                dday = Math.floor(dday / (1000 * 60 * 60 * 24)) + 1;
                                return dday;

                            }
                            
                            //사이드메뉴바 호버기능
                            $(".leftMenuA").hover(function(){
                               $(".leftMenuA").attr("style","border-color : white");
                               $(this).attr("style","border-color : #75D701");
                            },function(){
                               $(".leftMenuA").attr("style","border-color : white");
                               $(".leftMenuA").eq(2).attr("style","border-color : #75D701");
                            });

                        </script>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>

</body>

</html>