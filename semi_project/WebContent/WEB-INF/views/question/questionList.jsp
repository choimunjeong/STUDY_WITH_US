<%@page import="questionboard.model.vo.QuestionBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    	ArrayList<QuestionBoard> list = (ArrayList<QuestionBoard>)request.getAttribute("list");
		String pageNavi = (String)request.getAttribute("pageNavi");
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
    
    a{
    	color:black;
    }
    
    .table th, .table td{
	  font-size: 12px;    
	  line-height: 19px;
	  color:#20232;
	  padding-top: 9px !important;
	  padding-bottom: 7px !important;
	  
	}
	
	.table th{
		font-weight: bold;  
	}
	
	.statusY{
		color : #004106;
		border: 1px solid #64CC6F;
	}
	
	.statusN{
		color : #630000;
		border: 1px solid #CD5F5F;
	}
	
	.tableTitle{
		width:450px;
		text-align: left;
	}
	
	.tableStatus{
		width:100px;
	}
	
	.table-wrap{
		min-height: 500px;
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
                        <section>
                        <br>
                        <%if(m!=null){ %>
                       		<div style="text-align:right; margin-bottom:5px;">
								<a href="/questionWriteFrm" class="btn btn-primary btn-sm" style="margin-right:30px;background-color:#fff;border:1px solid #DEDEDE;color:#525252">문의글 등록</a>
							</div>
							<%} %>
							<div class="table-wrap">
                            <table class="table" style="width:95%; border-top:2px solid #6ECF4C; margin:0 auto; text-align:center;">
                                <tr>
                                    <th>순서</th>
                                    <th></th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                </tr>
                                <%for(QuestionBoard q : list){ %>
                                <tr>
                                    <td><%=q.getRnum() %></td>
                                   <td style="display:none;"><%=q.getQuestionNo() %></td>
                                   <td class="tableStatus">
	                                    <%if(q.getAnswerStatus().charAt(0)=='y'){ %>
	                                    <div class="statusY"> 완료 </div>
	                                    <%} else{%>
	                                    <div class="statusN"> 미답변 </div>
	                                    <%} %>
                                    </td>
                                    <td  class="tableTitle" >
                                    
                                    <!-- 비밀글일때 -->
                                    <%if(q.getQuestionPw()==1){ %>
	                                    <!-- 로그인 안 했을 때 -->
	                                    <%if(m ==null){ %>
	                                    <a href="javascript:onclick()"><%=q.getQuestionTitle() %></a>
	                                    
	                                    <!-- 로그인 했을 때 -->
	                                    <%} else{%>
	                                    <a href="/questionView?questionNo=<%=q.getQuestionNo() %>&memberId=<%=m.getMemberId()%>&writerId=<%=q.getQuestionWriterId()%>"><%=q.getQuestionTitle() %></a>
	                                    <%}%>
	                                    <img src="/img/lock.png" style="width:10px;">
                                    
                                    <!-- 비밀글 아닐때 -->
                                    <%} else{%>
	                                    <a href="/questionView?questionNo=<%=q.getQuestionNo() %>&memberId=&writerId="><%=q.getQuestionTitle() %></a>
                                    <%}%>
                                    </td>
                                    <td><%=q.getQuestionWriterId() %></td>
                                    <td><%=q.getQuestionWriteDate() %></td>
                                </tr>
                                <%} %>
                            </table>
                            </div>
                            <br><br>
                            <div class="text-center" style="width:100%; margin:0 auto;">
								<ul class="pagination" >
									<%=pageNavi %>
								</ul>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
	<script>
    function onclick() {
        alert('비밀글 입니다.');
    }
    
    //사이드메뉴바 호버기능
    $(".leftMenuA").hover(function(){
       $(".leftMenuA").eq(1).attr("style","border-color : white");
       $(this).attr("style","border-color : #75D701");
    },function(){
       $(".leftMenuA").attr("style","border-color : white");
       $(".leftMenuA").eq(1).attr("style","border-color : #75D701");
    });
	</script>
</body>

</html>