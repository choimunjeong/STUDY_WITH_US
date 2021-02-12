<%@page import="questionboard.model.vo.QuestionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    	QuestionBoard q = (QuestionBoard)request.getAttribute("q");
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>고객문의</title>
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

    .title {
        margin-left: 20px;
    }

    .text {
        width: 94%;
        height: 750px;
        overflow: auto;
        line-height: 20px;
        font-family: Roboto;
        font-style: normal;
        padding: 3%;
        margin: 0 auto;
    }

    strong {
        margin-left: 10px;
    }

    select.form-control,
    input.form-control {
        display: inline-block;
        width: 200px;
        height: 30px;
        font-size: 0.8em;
    }
    
    .pagination{
    	justify-content: center;
    }
    
    .pagination > li > a{
    	color : black;
    }
    
    .deleteAllBtn{
    	border-color : #38AF52;
    	background-color : #38AF52;
    }
    
    .notice-content{
    	color:black;
    }
    
    .bordertable th{
	  background-color: #f7f8fa;    
	  text-align: center;
	}
	
	.bordertable td{
	  padding-left:20px !important;
	}  
	
	.bordertable th, .bordertable td{
	  font-family: '나눔고딕',NanumGothic,'맑은고딕',MalgunGothic,'돋움',Dotum,Helvetica,sans-serif;
	  font-size: 12px;    
	  border:1px solid #ededed !important;
	  font-weight: normal;      
	  line-height: 19px;
	  color:#20232;
	  padding-top: 9px !important;
	  padding-bottom: 7px !important;
	}
	
	.th120 th{
	  width:120px;
	}

	.statusY{
		color : #004106;
		border: 1px solid #64CC6F;
	}
	
	.statusN{
		color : #630000;
		border: 1px solid #CD5F5F;
	}
</style>

<body>
	 <section>
         <div class="wrap">
            <div class="header">
                <%@ include file="/WEB-INF/views/common/header.jsp"%>
            </div>
            <div class="myplan">
                    <div class="groupListTitle">고객센터</div>
                <div class="leftMenu">
                    <ul class="leftMenuList">
                        <li>고객센터</li>
                        <li><a class="leftMenuA" href="/noticeList?reqPage=1">공지사항</a></li>
                        <li><a class="leftMenuA" href="/questionList?reqPage=1">고객문의</a></li>
                    </ul>
                </div>
                <div class="participatingGroup">
                    <div class="groupList">
                        <div class="table-wrapper" style="width:95%; margin:0 auto;">
                           <form action="questionInsert" method="post" enctype="multipart/form-data">
                           <br>
                                <table class="table table-borader">
                                    <tr>
                                        <th colspan="2" style="border-top:2px solid #6ECF4C;">문의사항 작성<input type="text" value="<%=m.getMemberId() %>" style="display:none;" name="memberId" ></th>
                                    </tr>
                                    <tr>
                                        <th>제목</th>
                                        <td><input type="text" class="form-control"  maxlength="16" name="questionTitle"  required="required"></td>
                                    </tr>
                                    <tr>
                                        <th>비밀글 설정</th>
                                        <td><input type="radio" name="pwd" value="0" id="pwd1"  required="required"> <label for="pwd1"> 일반</label> &nbsp;&nbsp;<input type="radio" name="pwd" value="1" id="pwd2"  required="required"> <label for="pwd2">비밀</label> </td>
                                    </tr>
                                    <tr >
                                        <th >내용</th>
                                        <td ><textarea name="questionContent" class="form-control" maxlength="200" rows="10" cols="40"  required="required"></textarea></td>
                                    </tr >
                                </table>
                                <hr>
                                <div style="text-align:center;"><button type="submit" class="btn btn-primary"  style="background-color:#56A902;border:0px;">등록하기</button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
<script>
	//사이드메뉴바 호버기능
	$(".leftMenuA").hover(function(){
	   $(".leftMenuA").eq2).attr("style","border-color : white");
	   $(this).attr("style","border-color : #75D701");
	},function(){
	   $(".leftMenuA").attr("style","border-color : white");
	   $(".leftMenuA").eq(2).attr("style","border-color : #75D701");
	});
</script>
</body>

</html>