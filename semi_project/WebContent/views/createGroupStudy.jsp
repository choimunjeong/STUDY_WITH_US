<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="groupstudy.model.vo.Category"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Date now = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
    	String today = sdf.format(now);
    %>
    
    <%-- <% groupstudyroom %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<title>그룹스터디 만들기</title>
<style>
    .wrap{
        margin: 0 auto;
    }
    
    .header {
        width: 1200px;
        height: 100px;
        background: green;
    }
    .myplan{
        overflow: hidden;
        width: 1200px;
        margin: 0 auto;
        margin-bottom: 100px;
    }
    .leftMenu {
        width: 182px;
       /*  height: 953px; */
       height: 100%;
        background: #E1E1E1;
        float: left;
    }
    
    .groupDetail {
        float: left;
    }
    .groupTitle {
        width: 100%;
        height: 53px;
        text-align: center;
        line-height: 53px;
        color: white;
        font-weight: bold;
        background: #75D701;
        margin: 0;
        font-size: 20px;
    }
    .groupContent {
        width: 1200px;
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
    }
    .leftMenuA:hover{
        color: black;
        text-decoration: none;
    }
    .leftMenuList>li:first-child{
        text-indent: 0;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }
	/* -------------------------------------------------------------- */
	.groupContent{
		padding-top: 50px;	
		/* height: 900px; */
		height: 100%;
	}
	.category{
		font-size: 15px;
		font-family: Roboto;
		margin-left:30px;
		margin-bottom: 50px;
	}
	.gcWrap{
		overflow: hidden;
	}
	.gc{
		float: left;
		width: 50%;
		margin-bottom: 70px;
	}
	.gcTitle{
		display: inline-block;
		font-family: Roboto;
		font-weight: bold;
		font-size: 24px;
		color: #464646;
		margin: 0;
		margin-left: 70px;
	}
	.gcContent{
		font-family: Roboto;
		font-size: 18px;
		color: #464646;
		margin: 0;
		margin-left: 70px;
	}
	.line{
		 margin: 0;
		 width: 450px;
		 margin-top: 5px;
		 margin-bottom: 20px;
		 margin-left: 70px;
		 background-color: #5f5f5f ;
	}
	/* createStudy-------------------------------------------------------------- */
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
		border: none;
	}
	.groupTitle>input{
		width: 40%;
		height: 30px;
		outline: none;
	}
	.category{
		font-family: Roboto;
		font-size: 24px;
		font-weight: bold;
	}
	
	.explan{
		width: 300px;
		height: 30px;
	}
	
	select{
      width: auto; 
      height: 30px;
      border-color: #DBDBDB;
   }
	input{
		outline: none;
		border-color: #DBDBDB;
		box-shadow: none;
	}
	textarea{
		outline: none;
		border-color: #DBDBDB;
	}
	img{
		border-color: #DBDBDB;
	}
	
	/* .explan:first-child{
		display: inline-block;
	} 
	.plus{
		display: none;
	}
	.p1{
		display: inline-block;
	}*/
</style>
</head>
<body>
 	<section>
        <div class="wrap">
            <div class="header" style="margin: 0 auto;">
            	<%@ include file="/WEB-INF/views/common/header.jsp"%>
            </div>
            
            <div class="myplan">
                <div class="groupDetail">
                	<form action="/insertGroupStudyRoom" method="post" enctype="multipart/form-data">
                	<div class="groupTitle">그룹스터디 생성</div>
                    <div class="groupContent">
                    	<input type="hidden" name="groupManagerNo" value="<%=m.getMemberNo()%>"><!-- 생성하려는 사용자의id넘기기 -->
	                    	<div class="category">
	                    		분류 | 1차 > 
	                    		<select id="sel1" name="category1" style="margin-right: 10px;">
	                    			<option value="취업" selected>취업</option>
	                    			<option value="공무원">공무원</option>
	                    			<option value="자격증">자격증</option>
	                    			<option value="어학">어학</option>
	                    		</select>
	                    		2차 > 
	                    		<select id="sel2" name="category2">
	                    		</select>
	                    	</div>
	                    	<div style="margin-bottom: 50px;"><p class="gcTitle"><label for="groutTitleInput">스터디이름 : </label> <input type="text" id="groutTitleInput" maxlength="15" name="groupTitle" placeholder="생성하려는 스터디이름을 정해주세요" style="outline: none; width:800px; font-size: 20px;" required="required"></p></div>
	                    	<div class="gcWrap">
	                    		<div class="gc">
	                    			<p class="gcTitle">스터디기간</p>
	                    			<hr class="line">
	                    			<p class="gcContent">
	                    				스터디 시작 날짜 : <input type="hidden" name="groupStartDate" value="<%=today %>"><%=today %><br>
	                    				스터디 종료 날짜 : <input type="date" name="groupEndDate" required="required">
	                    			</p>
	                    			<br><br>
	                    		</div>
	                    		<div class="gc">
	                    			<p class="gcTitle">참여인원 <sub style="font-size: 13px; color: gray;">최대인원은 12명입니다</sub> </p>
	                    			<hr class="line">
	                    			<p class="gcContent">
	                    				희망 인원 : 
		                    			<select id="selpersonnel" name="groupPersonnel">
			                    			<%for(int i=2;i<13;i++){ %>
			                    				<option value="<%=i%>"><%=i%></option>
			                    			<%} %>
		                    			</select>
	                    			</p>
	                    			<br><br>
	                    		</div>
	                    	</div>
	                    	<div class="gcWrap">
	                    		<div class="gc">
	                    			<p class="gcTitle">스터디 규칙</p>
	                    			<hr class="line">
	                    			<p class="gcContent">
	                    				<textarea class="rowCheck" rows="5" cols="40" name="groupRule" maxlength="30" style="resize: none; font-size: 20px;" required="required"></textarea>
	                    			</p>
	                    			<br><br>
	                    		</div>
	                    		<div class="gc" style="height: 200px;">
	                    			<p class="gcTitle"><img src="/img/Star1.png">스터디 공통계획</p>
	                    			<hr class="line">
	                    			<p class="gcContent planWrap">
	                    				<input type="text" class="explan" maxlength="20" required="required"> <img class="plus p1" src="/img/Group62.png">
	                    			</p>
	                    			<input type="hidden" name="groupExplan"> <!-- 여기에 explan을 String으로 한번에 묶어서 보냄 -->
	                    			<br><br>
	                    		</div>
	                    	</div>
	                    	<div class="gcWrap">
	                    		<div class="gc">
	                    			<p class="gcTitle">스터디원에게 한마디</p>
	                    			<hr class="line">
	                    			<p class="gcContent">
	                    				<textarea class="rowCheck" rows="6" cols="40" name="groupContent" maxlength="90" style="resize: none; font-size: 20px;" required="required"></textarea>
	                    			</p>
	                    		</div>
	                    		<div class="gc">
	                    			<p class="gcTitle">스터디 프로필사진<sub style="font-size: 13px; color: gray;">파일없이 등록 시 기본 이미지</sub></p>
	                    			<hr class="line">
	                    			<p class="gcContent" style="font-size: 15px;">
	                    				프로필 사진 : <input type="file" name="filename" onchange="loadImg(this)">
	                    				<img id="img-view" width="80%" height="250px;" style="object-fit: contain; border: none;">
	                    			</p>
	                    			<br>
	                    		</div>
	                    	</div>
	                    	<hr width="80%">
	                    	<br>
	                    	<div class="modalBtnDiv">
	              				<button id="btn" type="submit">생성 하기</button>
	                    	</div>
                    	</div>
                    </form>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
		</div>
    </section>
    
    <script>
    //카테고리--------------------------------------------------
    $(function(){
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
	    
	    //이렇게 걸면 동적으로 이벤트를 걸 수 있음 / 이안에서 생성한 태그같은 애들에게도 이벤트가 적용됨 !!!!
	    $(document).on("click",".plus",function(){
			var planInput = "<input type='text' class='explan' maxlength='20' required='required'> ";
			var plusImg = "<img class='plus' src='/img/Group62.png'>";
			var minusImg = "<img class='plus' src='/img/Group58.png'>";
			
			if($(this).attr("src")=="/img/Group62.png"){//plus아이콘을 선택한경우
				if($(".explan").length==5){
					alert("계획은 최대 5개까지 등록이 가능합니다");
					return;
				}
				for(var i = 0;i<=$(this).index(".plus");i++){
					$(this).attr("src","/img/Group58.png");
				} 
				$(".planWrap").append(planInput);
				$(".planWrap").append(plusImg);
			}else{//minus아이콘을 선택한경우
				$(this).prev().remove();
				$(this).remove();
			}
			
	    });
	    //이거는 처음 한번만 페이지를 불러오고 그상태의 애들만 이벤트를 걸수있음 / 여기서 생성된 애들한테는 이벤트가 적요안됨!!!!
		/* $(".plus").click(function(){
			
		});  */  
		
		//생성하기 버튼 클릭하면 groupExplan내용 _로 합쳐서 전달하기
		$(document).on("click","#btn",function(){
			var groupExplan = "";
			for(var i = 0;i<$(".explan").length;i++){
				if(i==$(".explan").length-1){
					groupExplan += $(".explan").eq(i).val(); 
					break;
				}
				groupExplan += $(".explan").eq(i).val()+"_"; 
			}
			$("input[name=groupExplan]").val(groupExplan);
		});
		
		//날짜 비교 후 오늘보다 이전날짜 선택 불가능하게
		$("input[type=date]").change(function(){
			var groupStartDate = $("input[name=groupStartDate]").val();
			var groupEndDate = $(this).val();
			console.log(groupEndDate);
			var startArray = groupStartDate.split('-');
	        var endArray = groupEndDate.split('-');
	        var startDate = new Date(startArray[0], startArray[1], startArray[2]);
	        var endDate = new Date(endArray[0], endArray[1], endArray[2]);
			if(endDate.getTime()<startDate.getTime()){
				alert("스터디 종료 날짜를 오늘날짜 이후로 선택해주세요");
				$(this).val(groupStartDate);
			}
		});
    });
  	//배열의 길이가 0인지(첨부파일개수가 0인지)
	//배열에 담겨있는 파일의 크기가 0인지 확인 -> 파일업로드를 1개만 하기 때문에 0인덱스만 검사
	function loadImg(f){
		if(f.files.length != 0 && f.files[0]!=0){
			var reader = new FileReader();	//JS파일리더객체 -> 파일정보 확인가능
			//현재 사용자가 선택한 파일의 경로를 일어옴
			reader.readAsDataURL(f.files[0]);
			//파일의 경로를 읽어오는 작업이 완료되면 밑의 함수를 동작시키겠다.
			reader.onload=function(e){
				$("#img-view").attr('src',e.target.result);//파일경로를 src속성에 설정
			}
		}else{
			$("#img-view").attr("src","");
		}
	}
	
	
	
	//스터디 규칙 / 스터디원에게 할말(groupRule,groupContent)
	//입력 줄수 제한 (7졸까지가능)
	$(".rowCheck").keyup(function(){
		var str = $(this).val();
		var str_arr = str.split("\n");  // 줄바꿈 기준으로 나눔 
		var row = str_arr.length;  // row = 줄 수 
		if(row >7){//마지막 입력문자 삭제
			alert("7줄 이상 입력할 수 없습니다");
			var lastChar = str.slice(0,-1); //열 
			$(this).val(lastChar);
		}
	});
    </script>
</body>
</html>