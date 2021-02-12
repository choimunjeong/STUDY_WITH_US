<%@page import="java.util.ArrayList"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="groupstudy.model.vo.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   	ArrayList<GroupStudyRoom> list = (ArrayList<GroupStudyRoom>)request.getAttribute("list");
	Category category = (Category)request.getAttribute("category");
   	String pageNavi = (String)request.getAttribute("pageNavi");
   	String category1 = (String)request.getAttribute("category1");
   	String category2 = (String)request.getAttribute("category2");
   %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>스터디 찾기</title>
</head>
<style>
.wrap {
	margin: 0 auto;
}
.heaer {
	width: 1200px;
	height: 100px;
}
.content-wrap {
	margin: 0 auto;
	margin-top: 100px;
	width: 1200px;
	height: 1274px;
}
.content-right {
	margin: 0 auto;
	width: 963px;
	height: 100%;
	text-align: center;
}
.content-title {
	margin: 0;
	display:inline-block;
	width: 100%;
	height: 53px;
	color: #fff;
	font-weight: bold;
	line-height: 53px;
	text-align: center;
	background-color: #75D701;
}
.search-box {
	margin: 0 auto;
	margin-top: 40px;
	width: 827px;
	background-color: #DCDEDD;
	overflow: hidden;
	box-sizing: none;
}
.label {
	float: left;
	width: calc(100%/ 4);
	height: 48px;
	line-height: 48px;
	text-align: center;
	display: inline-block;
	color: #fff;
	font-weight: bold;
	background-color: #75D701;
}
.label:hover {
	cursor: pointer;
}
.search-name {
	float: left;
	width: 100%;
height:150px;
line-height:150px;
}
.search-name>select {
	width: 100px;
	height: 30px;
}
.search-name>input {
	width: 435px;
	height: 30px;
	outline: none;
	box-sizing: none;
}
.search-category {
	float: left;
	width: 100%;
	height:150px;
line-height:150px;
	text-align: center;
}
#search-btn {
	width: 100px;
	height: 30px;
	border: none;
	border-radius: 3px;
	background-color: #3D7CB6;
	color: #fff;
	line-height: 0px;
}
.add-function {
	margin: 0 auto;
	margin-top: 30px;
	width: 827px;
	height: 40px;
}
.add-function>a {
	float: left;
	width: 113px;
	height: 33px;
	color: #fff;
	font-size: 13px;
	font-weight: bold;
	border-radius: 3px;
	background-color: #167E14;
	text-decoration: none;
	line-height: 33px;
}
.add-function>select {
	width: 106px;
	height: 25px;
	float: right;
}
hr {
	width: 827px;
	margin: 0 auto;
	margin-bottom: 51px;
}
.content-list {
	width: 827px;
	height: 750px;
	margin: 0 auto;
	overflow: hidden;
	text-align: center;
}
.study-list {
	width: 152px;
	height: 202px;
	float: left;
	margin-left: 45px;
	margin-bottom: 40px;
	border: 1px solid #DCDEDD;
}
.study-list:hover{
	box-shadow: 5px 5px 5px 3px #cccccc;
	transition: 0.7s;
}
.list-img{
	width: 150px;
	height: 150px;
}
.list-img>img{
	width: 100%;
	height: 100%;
}
.list-content {
	width: 150px;
	height: 50px;
	color: #fff;
	background-color: #fff;
	overflow: hidden;
	box-sizing:none;
	border: 1px solid #DCDEDD;
}
.list-content>a {
	display: inline-block;
	margin-top: 5px;
	text-decoration: none;
	font-weight: bold;
	font-size: 15px;
	color:black;
}
.list-content>p {
	margin: 0;
	margin-top: 0px;
	font-size: 10px;
	color:black;
}
.selectPage {
	font-size: 18px;
	color: blue;
}
select {
	height: 30px;
	outline: none;
}
</style>
<body>
	<section>
		<div class="wrap">
			<div class="header">
				<%@ include file="/WEB-INF/views/common/header.jsp"%>
			</div>
			<div class="content-wrap">
				<a href="groupStudyList?reqPage=1" class="content-title">스터디 찾기</a>
				<div class="content-right">
					<div class="search-box">
						<div class="label">검색어로 찾기</div>
						<div class="label">카테고리로 찾기</div>
						<div class="label"></div>
						<div class="label"></div>
						<form action="/groupStudyListWord?reqPage=1" method="post">
							<div class="search-name">
								<select name="search-word">
									<option value="group_title" selected>제목</option>
									<option value="group_content">내용</option>
								</select> 
								<input type="text" id="inputs" name="inputs" placeholder="검색어를 입력해주세요.">
								<button id="search-btn" type="submit">검 색</button>
							</div>
						</form>
						<form action="/groupStudyListCategory?reqPage=1" method="post">
							<div class="search-category">
								분류 | 1차 > <select id="sel1" name="category1"
									style="margin-right: 10px;">
									<option value="취업" selected>취업</option>
									<option value="공무원">공무원</option>
									<option value="자격증">자격증</option>
									<option value="어학">어학</option>
								</select> 2차 > <select id="sel2" name="category2">
								</select>
								<button id="search-btn" type="submit">검 색</button>
							</div>
						</form>
					</div>
					<div class="add-function">
						<%if(m!=null && m.getMemberGrade() != 0) {%>
						<a
							href="/createRoomCntCheck?memberNo=<%=m.getMemberNo()%>&memberGrade=<%=m.getMemberGrade()%>">그룹스터디
							생성</a>
						<%} %>
						<select id="float" name="float-category"
	onChange="window.open(value,'_self');">
	<option>선택하세요</option>
	<option value="/groupStudyList?reqPage=1">최근등록순</option>
</select>
					</div>
					<hr>
					<div class="content-list">
						<%if(list == null) {%>
							<h2>일치하는 정보가 없습니다.</h2>
						<%}else {%>
							<%for(GroupStudyRoom gsr : list) {%>
							<div class="study-list">
								<div class="list-img">
									<%if(gsr.getFilename()==null) {%>
										<img src="/img/basic.png">
									<%}else {%>								
										<img src="/upload/groupImg/<%=gsr.getFilepath() %>">
									<%} %>
								</div>
								<div class="list-content">
									<a href="/groupStudyDetail?groupNo=<%=gsr.getGroupNo()%>"><%=gsr.getGroupTitle() %></a><br>
									<p><%=gsr.getGroupExplan() %></p>
								</div>
							</div>
							<%} %>
						<%} %>
					</div>
					<div id="pageNavi"><%=pageNavi %></div>
				</div>
			</div>
			<div>
				<%@ include file="/WEB-INF/views/common/footer.jsp"%>
			</div>
		</div>
	</section>
	<script>
    	//최초에 검색어입력박스------------------------------------------
        $(function(){        	
            $(".label").eq(1).click();
        });
        $(".label").click(function(){
            var label = $(this).index();
            if(label == 0){	//검색어 입력으로 검색------------------------
                $(".search-name").css("display","block");
                $(".search-category").css("display","none");
                $(".label").eq(0).css({'color':'#1F6A27','background-color':'#DCDEDD'});
                $(".label").eq(0).nextAll().css({'color':'','background-color':''});
            }
            if(label == 1){	//카테고리별 검색----------------------------------------
                $(".search-name").css("display","none");
                $(".search-category").css("display","block");
                $(".label").eq(0).nextAll().css({'color':'#1F6A27','background-color':'#DCDEDD'});
                $(".label").eq(0).css({'color':'','background-color':''});
                changeSel();	//처음 화면에 보여줘야함
            	$("#sel1").change(function(){
        	        changeSel();
        	     });
        	    function changeSel(){
        	        var sel1 = $("#sel1").val();
        	        $.ajax({
        	           url : "/categorySelAjax",
        	           type : "post",
        	           data : {sel1:sel1},
        	           success : function(data){
        	              var sel2 = $("#sel2");
        	              sel2.empty();
        	              for(var idx in data){
        	                 var option = $("<option></option>");
        	                 option.val(data[idx]);
        	                 option.html(data[idx]);
        	                 sel2.append(option);
        	              }

        	           }
        	        });
            	}
            }	
        });
    </script>
</body>
</html>