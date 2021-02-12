<%@page import="noticeboard.model.vo.NoticeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    	NoticeBoard n = (NoticeBoard)request.getAttribute("n");
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>관리자|공지사항 관리</title>
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
    .leftMenuList>li:nth-child(4)>a{
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

    .pagination {
        justify-content: center;
    }

    .pagination>li>a {
        color: black;
    }

    .deleteAllBtn {
        border-color: #38AF52;
        background-color: #38AF52;
    }
    
    .imgWrap{
    	text-align: center;
    }
</style>

<body>
 	 <section>
         <div class="wrap">
            <div class="header">
                <%@ include file="/WEB-INF/views/common/managerHeader.jsp"%>
            </div>
            <div class="myplan">
                    <div class="groupListTitle">공지사항 관리</div>
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
                        <br><br>
                        <div class="table-wrapper" style="width:95%; margin:0 auto;">
                            <table class="table table-borader tableEdit">
                                <tr>
                                    <th colspan="2" style="background-color:#F1F1F1"><%=n.getNoticeTitle() %></th>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td>관리자</td>
                                </tr>
                                <tr>
                                    <th>첨부파일</th>
                                    <td>
                                        <%if(n.getFilename() != null){ %>
                                        <img src="/img/file.png" width="16px">
                                        <a href="javascript:fileDownload('<%=n.getFilename() %>', '<%=n.getFilepath() %>')"><%=n.getFilename() %></a>
                                        <%} else{%>
                                        없음
                                        <%} %>
                                    </td>
                                </tr>
                                <tr >
                                    <th>내용</th>
                                    <td><%=n.getNoticeContent() %></td>
                                </tr>
                                <%if(n.getFilename() != null){ %>
                                <tr>
                                	<td colspan="2" class="imgWrap"><img src="/upload/notice/<%=n.getFilepath() %>">	<br></td>
                                </tr>
                                <%} %>
                                <tr style="text-align:center">
                                    <th colspan="2">
                                        <a href="/noticeUpdateFrm?noticeNo=<%=n.getNoticeNo() %>" class="btn btn-primary btn-sm">수정하기</a>
                                        <a href="/noticeOneDelete?noticeNo=<%=n.getNoticeNo() %>" class="btn btn-primary btn-sm">삭제하기</a>
                                        <a href="javascript:history.go(-1)" class="btn btn-primary btn-sm">목록으로</a>
                                    </th>
                                </tr>
                            </table>
                        </div>

                        <script>
                            function fileDownload(filename, filepath) {
                                var url = "/noticeFileDownload";
                                var enFilename = encodeURIComponent(filename);
                                var enFilepath = encodeURIComponent(filepath);
                                location.href = url + "?filename=" + enFilename + "&filepath=" + enFilepath;
                            }
                            
                            //사이드메뉴바 호버기능
                            $(".leftMenuA").hover(function(){
                               $(".leftMenuA").eq(2).attr("style","border-color : white");
                               $(this).attr("style","border-color : #75D701");
                            },function(){
                               $(".leftMenuA").attr("style","border-color : white");
                               $(".leftMenuA").eq(2).attr("style","border-color : #75D701");
                            });
                            
                            $(document).ready(function(){
                            	var height = $(".tableEdit").height();
                            	$(".table-wrapper").height(height+50);
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