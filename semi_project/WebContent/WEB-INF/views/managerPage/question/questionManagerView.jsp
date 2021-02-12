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
    <title>관리자|고객문의 관리</title>
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
    .leftMenuList>li:nth-child(5)>a{
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

	.statusY{
		color : #004106;
		border: 1px solid #64CC6F;
		text-align:center;
	}
	
	.statusN{
		color : #630000;
		border: 1px solid #CD5F5F;
		text-align:center;
	}
	
</style>

<body>
 	 <section>
         <div class="wrap">
            <div class="header">
                <%@ include file="/WEB-INF/views/common/managerHeader.jsp"%>
            </div>
            <div class="myplan">
                    <div class="groupListTitle">고객문의 관리</div>
                <div class="leftMenu">
                    <ul class="leftMenuList">
                        <li>관리자</li>
                        <li><a class="leftMenuA" href="/memberList?reqPage=1" >사용자 관리</a></li>
                        <li><a class="leftMenuA" href="/groupStudyListManager?reqPage=1">그룹스터디 관리</a></li>
                        <li><a class="leftMenuA" href="/noticeManagerList?reqPage=1">공지사항 관리</a></li>
                        <li><a class="leftMenuA" href="/managerQuestionList?reqPage=1">고객문의 관리</a></li>
                        <li><a class="leftMenuA" href="/eventManagerList?reqPage=1">이벤트 관리</a></li>
                    </ul>
                </div>
                <div class="participatingGroup">
                    <div class="groupList">
                        <section>
                        <br>
                        <div class="table-wrapper" style="width:95%; margin:0 auto;">
                        <form action="managerQuestionInsertComment?questionNo=<%=q.getQuestionNo()%>" method="post" enctype="multipart/form-data">
                            <table class="table bordertable th120">
                                <tr>
                                    <th  style="border-top:2px solid #6ECF4C; width:150px;">
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
                                 <th colspan="2" style="background-color:#F1F1F1">운영자 답변</th>
                                 </tr>
                                <tr>
                                	<th>답변일</th>
                                	<td><%=q.getAnswerDate() %></td>
                                </tr>
                                <tr>
                                	<th>답변내용</th>
                                	<td><p><%=q.getAnswerContentBr() %></p></td>
                                </tr>
                                <% }else{ %>
                                <tr>
                                	<th colspan="2">답변 작성하기</th>
                                </tr>
                                
                               	<tr>
                               		<th>운영자<input type="text" value="<%=q.getQuestionNo()%>" style="display:none;" name="questionNo"></th>
                               		<td colspan="2"><textarea rows="10" name="comment" cols="100" maxlength="200" required="required"></textarea></td>
                               	</tr>
                               	
                               	<tr>
                               		<td colspan="2"><div style="float:right;"><button type="submit" class="btn btn-primary">답변 등록</button></div></td>
                               	</tr>
                               	
                                <%} %>
                                <tr style="text-align:center">
                                    <th colspan="2">
                                    <%if(q.getAnswerStatus().charAt(0)=='y'){ %>
                                    	 <a href="/managerCommentDelete?questionNo=<%=q.getQuestionNo() %>" class="btn btn-primary btn-sm">답변 삭제하기</a>
                                    <%} %>
                                      <a href="/questionOneDelete?questionNo=<%=q.getQuestionNo() %>" class="btn btn-danger btn-sm">삭제하기</a>
                                      <a href="/managerQuestionList?reqPage=1" class="btn btn-primary btn-sm">목록으로</a>
                                    </th>
                                </tr>
                            </table>
                            </form>
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
        function editComment() {
            var url = "/noticeFileDownload";
            var enFilename = encodeURIComponent(filename);
            var enFilepath = encodeURIComponent(filepath);
            location.href = url + "?filename=" + enFilename + "&filepath=" + enFilepath;
        }
        
        //사이드메뉴바 호버기능
        $(".leftMenuA").hover(function(){
           $(".leftMenuA").eq(3).attr("style","border-color : white");
           $(this).attr("style","border-color : #75D701");
        },function(){
           $(".leftMenuA").attr("style","border-color : white");
           $(".leftMenuA").eq(3).attr("style","border-color : #75D701");
        });
    </script>
</body>

</html>