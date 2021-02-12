<%@page import="groupstudy.model.vo.GroupStudyRoomAddCategory"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<GroupStudyRoomAddCategory> gsrCateList = (ArrayList<GroupStudyRoomAddCategory>)request.getAttribute("gsrCateList");
    %>
    <%-- <% groupstudyroom %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		padding-left: 50px;
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
    .leftMenuList>li:last-child>a{
        border-color: #75D701;
    }
    
    .leftMenuList>li:first-child{
        text-indent: 0;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }
    
    
    
    /* ---------------------------------------------------------- */
    .blank{/* 공백용... */
    	height: 80px;
    }
    span{
   		display: inline-block;
   		font-family: Roboto;
   		font-size: 12px;
    }
    .gl1{
    	font-family: Roboto;
    	text-indent: 10px;
    	width: 20%;
    	height: 20px;
    	line-height: 20px;
    	padding-left: 20px;
    }
    .gl2{
    	font-family: Roboto;
    	text-align: center;
    	width: 51%;
    }
    .gl3{
    	font-family: Roboto;
    	text-align: center;
    	width: 10%;
    }
    .gl4{
    	font-family: Roboto;
    	text-align: center;
    	width: 10%;
    }
    .gl{
    	font-family: Roboto;
    }
    .groupList{
    	padding: 
    }
    #selectA{
    	text-decoration: none;
    	color: black;
    }
    a:hover{
    	font-weight: bold;
    }
   
</style>
</head>
<body>
 	<section>
        <div class="wrap">
            <div class="header">
            	<%@ include file="/WEB-INF/views/common/header.jsp"%>
            </div>
            <div class="myplan">
            	<div class="groupListTitle">나의 그룹스터디 리스트</div>
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
                    
                    	<%if(gsrCateList==null || gsrCateList.isEmpty()){ %>
                    		<div style="text-align: center; font-size: 25px; font-weight: bold; padding-top: 100px; opacity: 0.5;">
		                       	현재 참여중인 스터디가 없습니다.
		                    </div>
                        <%}else{ %>
                        	<div class="blank"></div>
                        	<span class="gl1" style="font-weight: bold; font-size: 20px;">분류</span><span class="gl2" style="font-weight: bold; font-size: 20px;">제목</span> <span class="gl3" style="font-weight: bold; font-size: 20px;">종료일</span> <span class="gl4" style="font-weight: bold; font-size: 20px;">구분</span>
                        	<hr style="width: 90%; margin: 10px;">
                        	<%for(GroupStudyRoomAddCategory gsrAddc : gsrCateList) {%>
                        		<div class="gl">
                        			<a id="selectA" href="/myPlanGroupDetail?groupNo=<%=gsrAddc.getGsr().getGroupNo()%>&category1=<%=gsrAddc.getCategory().getCategory1() %>&category2=<%=gsrAddc.getCategory().getCategory2() %>">
                        			
                        			
                        			<span class="gl1"><%=gsrAddc.getCategory().getCategory1() %> > <%=gsrAddc.getCategory().getCategory2() %></span>
                        			<span class="gl2"><%=gsrAddc.getGsr().getGroupTitle()%></span>
                        			<span class="gl3"><%=gsrAddc.getGsr().getGroupEndDate()%></span>
                        			<%if(m.getMemberNo()==gsrAddc.getGsr().getGroupManagerNo()){ %>
                        				<span class="gl4">그룹장</span>
                        			<%}else{ %>
                        				<span class="gl4">참여중</span>
                        			<%} %>
                        			</a>
                        			<hr style="width: 90%; margin: 10px;">
                        		</div>
                        	<%} %>
		                <%} %>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
		</div>
    </section>
    <script>
    	//사이드메뉴바 호버기능
    	$(".leftMenuA").hover(function(){
    		$(".leftMenuA").attr("style","border-color : white");
    		$(this).attr("style","border-color : #75D701");
    	},function(){
    		$(".leftMenuA").attr("style","border-color : white");
    		$(".leftMenuA").eq(2).attr("style","border-color : #75D701");
    	});
    </script>
</body>
</html>