<%@page import="alarm.model.vo.Alarm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="groupstudy.model.vo.GroupList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    	Member myInfo = (Member) request.getAttribute("myInfo");
    	ArrayList<GroupList> gl = (ArrayList<GroupList>) request.getAttribute("group_list");
    	ArrayList<Alarm> al = (ArrayList<Alarm>) request.getAttribute("myAlarm");
    	String alNavi = (String)request.getAttribute("alNavi");
    	String glNavi = (String)request.getAttribute("glNavi");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<style>
.full_container {
	width: 1200px;
	height: 1300px;
	margin: auto;
	display: flex;
}

.content_container {
	width: 100%;
	height: 100%;
	margin: auto;
}

.banner {
	width: 100%;
	height: 45px;
	line-height: 45px;
	text-align: center;
	font-weight: bold;
	background-color: #75D701;
	color: white;
	margin-bottom: 100px;
}

.content {
	width: 100%;
	height: 867px;
}

.content_box {
	width: 76%;
	border: 1px solid #B6B6B6;
	margin: auto;
	height: 35%;
	margin-top: 60px;
	border-top-right-radius: 10px;
	border-top-left-radius: 10px;
	background-color: rgba(222,255,214,0.2);

}

.content>div:first-child {
	margin-top: 70px;
}
.content_box:last-child{
	margin-bottom: 70px;
}
.my_info {
	border: 1px solid transparent;
	width: 100%;
	height: 35%;
	position: relative;
	text-align: center;
}

.my_info>img {
	width: 80px;
	height: 80px;
	border-radius: 70%;
	position: absolute;
	top: -50px;
	right: 45%;
}

.my_info>div {
	margin-top: 45px;
	font-weight: bold;
}

.detail_info {
	display: flex;
	padding: 0;
	margin: auto;
	width: 70%;
	height: 50%;
	flex-wrap: wrap;
	justify-content: space-around;
}

.detail_info>li {
	margin-top: 10px;
	width: 40%;
	height: 20%;
	border-bottom: 1px solid black;
	color: grey;
	font-weight: bold;
	font-size: 15px;
}

li {
	list-style: none;
}

.content>div:first-child>div:nth-child(3) {
	text-align: right;
	padding-right: 40px;
}

a {
	text-decoration: none;
	color: cornflowerblue;
	font-size: 12px;
}

.group_list,.work_list{
	margin-top:15px;
}
.content_title {
	width: 100%;
	height: 15%;
	color: black;
	text-align: center;
	line-height: 45px;
	font-size: 13px;
	font-weight: bold;
	border-top-left-radius:10px;
	border-top-right-radius: 10px; 
}

.g_list>td:nth-child(2), .g_list>th:nth-child(2) {
	width: 200px;
}

.w_list>td:nth-child(2), .w_list>th:nth-child(2) {
	width: 300px;
}

.w_list>td:nth-child(3), .w_list>th:nth-child(3) {
	width: 50px;
}

.w_list>td:nth-child(4), .w_list>th:nth-child(4) {
	width: 50px;
}

table {
	width: 80%;
	margin: auto;
}

th {
	padding-bottom: 5px;
	padding-top:10px;
}

th,td{
	width: 100px;
	text-align: center !important;
	border-bottom: 1px solid #ddd;
	
}

td, th {
	font-size: 15px;
}

td img{
	width:20px;
	height:20px;
	margin-bottom: 3px;
}
#deleteMember{
cursor: pointer;
color: cornflowerblue;
}
#glNavi{
	text-align: center;
	margin-top:20px;
}
#alNavi{
text-align: center;
margin-top:20px;
}
.work_list,.group_list{
height:60%;
}
.modal_back{
	position:absolute;
	width: 100vw;
	height : 150vh;
	background-color:rgba(0, 0,0, 0.2);
	z-index: 1;
	display:none;
}
.modal_content{
position:absolute;
width: 500px;
height : 430px;
border-radius:10px;
background-color: #fff;
z-index:2;
margin: 200px 400px 200px 400px;
display:none;
box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5);
}
.modal_content>div:first-child{
	text-align: center;
	color : green;
	line-height: 70px;
	font-weight: bold;
	font-size : 20px;
	width:100%;
	height:70px;
	border-bottom: 1px solid rgba(0,0,0,0.3);
}
.modal_content>div:nth-child(2){
	width:100%;
	height:65%;
}
#modal_text{
	width:100%;
	height:25%;
	padding-left:50px;
	padding-top:20px;
}
#modal_area>div{
	padding-left:50px;
}
#modal_area{
	width:100%;
	height:70%;
}
#modal_area>textarea{
	width:80%;
	height:80%;
	margin-left:10%;
	margin-top:2.5%;
	border-bottom: 1px solid rgba(0,0,0,0.3);
	resize: none;
}
#applyBtn,#rejectBtn{
	width:40%;
	height:60%;
	outline: none;
	border:none;
	background-color: #fff;
	margin: auto;
	padding-top: 20px;
}

.btnWrap{
	text-align: center;
	margin-top : 20px;
	border-top: 1px solid rgba(0,0,0,0.3);
}
#redFont{
	color: red;
}
#blueFont{
	color: blue;
}
.detail_info>li>a{
	color: black !important;
	font-size: 15px;
}
</style>
</head>
<body>
	<div style="height: 100px;">
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
	</div>
	<div class="modal_back"></div>
	<div class="full_container">
	<div class="modal_content">
		<div>
				Study 참여요청이 도착했습니다
		</div>
		<div>
			<div id="modal_text">
			</div>
			<div id="modal_area">
			<div>참여요청 메시지</div>
			<textarea></textarea>
			</div>
		</div>
		<div class="btnWrap">
			<button id="applyBtn">수락</button>
			<button id="rejectBtn">거절</button>
		</div>
	</div>

		<div class="content_container">
			<div class="banner">마이 페이지</div>
			<div class="content">
				<div class="content_box">
					<div class="my_info">
						<img src="<%=myInfo.getFilepath()%>">
						<div><%=myInfo.getMemberName()%> 님
						</div>
					</div>
					<ul class="detail_info">
						<li>아이디 : <a><%=myInfo.getMemberId()%></a></li>
						<li>닉네임 : <a><%=myInfo.getMemberNickname()%></a></li>
						<li>이메일 : <a><%=myInfo.getMemberEmail()%></a></li>
						<li>등급 : <a><%if(myInfo.getMemberGrade() == 0){%> 관리자 <%}else if(myInfo.getMemberGrade() == 1){ %>
							정회원 <%}else{ %> 준회원 <%} %></a>
						</li>
					</ul>
					<div>
						<a href="/updateForm">개인정보 수정</a>
						<a> / </a>
						<a id="deleteMember">회원 탈퇴</a>
					</div>
				</div>
				<div class="content_box">
					<div class="content_title">참여 중인 그룹스터디 리스트</div>
					<div class="group_list">
						<table>
							<tr class="g_list">
								<th>그룹 스터디명</th>
								<th>기간</th>
								<th>인원 수</th>
								<th>이동</th>
							</tr>

							<%if(gl == null) {%>
							<tr class="g_list">
								<td colspan="4">없음</td>
							</tr>
							<%}else{ %>
							<%for (GroupList a : gl) {%>
							<tr class="g_list">
								<td><%=a.getGroupTitle()%></td>
								<td><%=a.getGroupStartDate()%> ~ <%=a.getGroupEndDate()%></td>
								<td><%=a.getMemberCnt()%>/<%=a.getGroupMax() %>명</td>
								<td><a href="/groupStudyDetail?groupNo=<%=a.getGroupNo()%>"><img src="/img/move.png"></a></td>
							</tr>
							<%} } %>

						</table>
					</div>
					<div id="glNavi"><%=glNavi%></div>
				</div>
				<div class="content_box">
					<div class="content_title">알림 리스트</div>
					<div class="work_list">
						<table>
							<tr class="w_list">
								<th>알림 분류</th>
								<th>내용</th>
								<th>상태</th>
								<th>삭제</th>
							</tr>
							<%if(al == null) {%>
								<tr class="w_list">
									<td colspan="3">없음</td>
								</tr>
							<%}else{ %>
							<%for (Alarm l : al) {%>
							<tr class="w_list">
								<%if(l.getAlarmSubject()==1||l.getAlarmSubject()==3){%>
								<td>참여요청</td>
								<%}else if(l.getAlarmSubject() ==2|| l.getAlarmSubject()==4){ %>
								<td>요청결과</td>
								<%} %>
								
								<td><%=l.getAlarmContent()%></td>
								<td>
									<%if(l.getAlarmStatus().charAt(0) == 'x'){%>
									<a class="readVal" href="#" alarmNum="<%=l.getAlarmNo()%>" id="noRead">안읽음</a>
									<%}else if(l.getAlarmStatus().charAt(0) == 'r'){%>
									<a class="readVal" id="redFont">거부</a>
									<%}else if(l.getAlarmStatus().charAt(0) == 'a') {%>
									<a class="readVal" id="blueFont">승인</a>
									<% }else{%>
									<a class="readVal">읽음</a>
									<%} %>
								</td>
								<td><input type="checkbox" gn="<%=l.getAlarmNo()%>"></td>
							</tr>
							<%}} %>
						</table>
					
					</div>
						<div id="alNavi"><%=alNavi%></div>
				</div>
			</div>
		</div>
	</div>
	<div>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<script>
		$(function(){
			var groupMax;
			var groupNo;
			var readVal;
			var applyMemberNo;
			var alarmNum;
			var stat;
			/*
			$("#noRead").click(function(){
				alarmNum = $(this).attr("alarmNum");
				$.ajax({
					url : "/readMyPageAlarm",
					type : "post",
					data : {alarmNum:alarmNum},
					success : function(data){
						if(data){
							location.reload();	
						}
						
					}
				});
			});*/

			$("#applyBtn").click(function(){
				$.ajax({
					url : "/checkMemberMax",
					type : "post",
					data : {groupNo : groupNo},
					success : function(data){
						if(data>=groupMax){
							stat = "r";
							$.ajax({
								url : "/updateAlarm",
								type : "post",
								data : {alarmNum:alarmNum,stat:stat},
								success : function(data){
									readVal.html("실패");
									readVal.css('color','red');
									alert("해당 그룹의 인원 수가 초과되어 멤버를 추가할 수 없습니다.");
								}
							});
						}
						else{
							$.ajax({
								url : "/updateAndInsertMember",
								type : "post",
								data : {applyMemberNo:applyMemberNo,groupNo:groupNo},
								success : function(data){
									if(data){
										stat = "a";
										$.ajax({
											url : "/updateAlarm",
											type : "post",
											data : {alarmNum:alarmNum,stat:stat},
											success : function(data){
												readVal.html("승인");
												readVal.css('color','blue');
												alert("그룹에 멤버를 성공적으로 추가했습니다.");
											}
										});
									}
									else{
										stat = "r";
										$.ajax({
											url : "/updateAlarm",
											type : "post",
											data : {alarmNum:alarmNum,stat:stat},
											success : function(data){
												readVal.html("실패");
												readVal.css('color','red');
												alert("Insert Error.");
											}
										});
									}
								}
							});
						}
					},
					complete : function(){
						$(".modal_back").css('display','none');
						$(".modal_content").css('display','none');
					}
				});
			});
			$("#rejectBtn").click(function(){
				$(".modal_back").css('display','none');
				$(".modal_content").css('display','none');
				stat = "r";
				$.ajax({
					url : "/updateAlarm",
					type : "post",
					data : {alarmNum:alarmNum,stat:stat},
					success : function(data){
						readVal.html("거부");
						readVal.css('color','red');
						alert("거부완료");
					}
				});
			});
			$(".readVal").click(function(){
				var status = $(this).html();
				readVal = $(this);
				alarmNum = $(this).attr("alarmNum");
				if(status == '안읽음'){
					$(this).html("읽음");
					$(this).css('color','grey');
					/*
					$.ajax({
						url:"/updateRead",
						type:"post",
						data:{alarmNum:alarmNum},
						success : function(data){
							if(data){
								console.log("성공");
							}
							else{
								console.log("실패");
							}
						}
						
					});*/
					
					//안읽은 요소중에 참여요청이면
					console.log($(this).parent().prev().prev().html());
					if($(this).parent().prev().prev().html() == '참여요청')
						{
							$.ajax({
								url : "/reviewApply",
								type : "post",
								data:{alarmNum:alarmNum},
								success : function(data){
									$("#modal_area>textarea").html(data.applyContent);
									$("#modal_text").html("그룹스터디 ["+data.groupTitle+"]에 참여 요청이 왔습니다.<br>참여 요청 닉네임 : "+data.memberNickname);
									groupMax = data.groupMax;
									groupNo = data.groupNo;	
									applyMemberNo = data.applyMemberNo;
								}
							});
							$(".modal_back").css('display','block');
							$(".modal_content").css('display','block');

						}
					else{
						stat = "k";
						$.ajax({
							url : "/updateAlarm",
							type : "post",
							data : {alarmNum:alarmNum,stat:stat},
							success : function(data){
								location.reload();
							}
						});
					}
				}
			});
			$("#deleteMember").click(function(){
				var con = confirm("정말로 회원탈퇴를 진행하시겠습니까?");
				if(con){
					var input = prompt("회원탈퇴를 진행하시려면\n'회원탈퇴'를 입력해주세요.");
					if(input == '회원탈퇴')
						{
							location.href="/deleteMember";
						}
					else{
						alert("올바르지 않은 입력값입니다.\n이전 화면으로 돌아갑니다.");
					}
				}
				else{
					alert("회원탈퇴가 취소되었습니다.");
				}
			});
			$("input[type=checkbox]").click(function(){
				if($(this).prop("checked")){
					var a = confirm("알림을 삭제하시겠습니까?");
					if(a){
						var a_no = $(this).attr("gn");
						
						$.ajax({
							url : "/deleteAlarm",
							type : "get",
							data : {a_no:a_no},
							success : function(data){
								if(data>0){
									alert("성공하였습니다.");
									location.reload();
								}
								else{
									alert("실패!");
									$(this).prop("checked",false);
								}
							}
							
						});
					}
					else{
						$(this).prop("checked",false);
					}
				}
			});
		});
	</script>
</body>
</html>