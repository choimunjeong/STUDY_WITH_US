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
      font-weight:bold;
	  text-align: center;
	}
	
	.bordertable td{
	  padding-left:20px !important;
	}  
	
	.bordertable th, .bordertable td{
	  font-size: 12px;         
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
	
	strong{
		font-size: 15px;
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
                        <br>
                            <table class="table bordertable th120">
                                <tr>
                                    <th  style="border-top:2px solid #6ECF4C;">
                                     <%if(q.getAnswerStatus().charAt(0)=='y'){ %>
                                    <div class="statusY"> 완료 </div>
                                    <%}else{ %>
                                    <div class="statusN"> 미답변 </div>
                                    <%} %>
                                    </th>
                                    <td  style="border-top:2px solid #6ECF4C;"><strong><%=q.getQuestionTitle() %></strong></td>
                                </tr>
                                <tr>
                                    <th>등록자</th>
                                    <td><%=q.getQuestionWriterId() %></td>
                                </tr>
                                <tr>
                                    <th>등록일</th>
                                    <td><%=q.getQuestionWriteDate() %></td>
                                </tr>
                                <tr >
                                    <th>내용</th>
                                    <td><%=q.getQuestionContentBr() %></td>
                                </tr>
                                <%if(q.getAnswerStatus().charAt(0)=='y'){ %>
                                <tr>
                                	<th>운영자</th>
                                	<td><strong>답변일 | </strong><%=q.getAnswerDate() %></td>
                                </tr>
                                <tr>
                                	<th>답변내용</th>
                                	<td><%=q.getAnswerContentBr() %></td>
                                </tr>
                                <%}else{ %>
                                	<tr>
                                		<th>운영자</th>
                                		<td colspan="2"> 답변 준비 중입니다.</td>
                                	</tr>
                                <%} %>
                                <tr style="text-align:center">
                                
                                    <th colspan="2">
                                    <br>
                                    <%if(m!=null && m.getMemberId().equals(q.getQuestionWriterId()) ){ %>
                                    	<%if(q.getAnswerStatus().charAt(0)=='y'){ %>
                                    	<a href="javascript:onclick()" class="btn btn-primary btn-sm">삭제하기</a>
                                    	<%}else{ %>
                                    	<a href="/questionOneDelete?questionNo=<%=q.getQuestionNo() %>" class="btn btn-primary btn-sm" style="background-color:#56A902;border:0px;">삭제하기</a>
                                    	<%} %>
                                    <%} %>
                                      <a href="javascript:history.go(-1)" class="btn btn-primary btn-sm" style="background-color:#56A902;border:0px;">목록으로</a>
                                    </th>
                                </tr>
                            </table>
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
    function onclick() {
        alert('답변이 등록된 글은 삭제하실 수 없습니다.');
    }
	</script>
</body>

</html>