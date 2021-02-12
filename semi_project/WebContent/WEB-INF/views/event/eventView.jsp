<%@page import="eventboard.model.vo.EventBoard"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	EventBoard e = (EventBoard)request.getAttribute("e");
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
    
    .tableEdit th, .tableEdit td{
	  font-size: 12px;    
	  line-height: 19px;
	  color:#20232;
	  padding-top: 9px !important;
	  padding-bottom: 7px !important;
	}
	
	.tableEdit th{
		width : 200px;
		font-weight: bold;  
	}
	
	.tableEdit td{
		text-align: left;
	}
	.img-wrap{
		text-align: center;
	}
	
	.img-wrap>img{
		width:60%;
	}
	.table-wrapper{
		min-height: 500px;
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
                        <div class="table-wrapper" style="width:85%; margin:0 auto;">
                        <br>
                            <table class="table table-borader tableEdit">
                                <tr>
                                    <th colspan="4" style="border-top:2px solid #6ECF4C;"><%=e.getEventTitle() %></th>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td>관리자</td>
                                    <th>마감일</th>
                                    <td><%=e.getEventEndDate() %></td>
                                </tr>
                                <tr>
                                    <th>첨부파일</th>
                                    <td colspan="3">
                                        <%if(e.getFilename() != null){ %>
                                        <img src="/img/file.png" width="16px">
                                        <a href="javascript:fileDownload('<%=e.getFilename() %>', '<%=e.getFilepath() %>')"><%=e.getFilename() %></a>
                                        <%} else{%>
                                        없음
                                        <%} %>
                                    </td>
                                </tr>
                                <tr>
                                	<th>첨부링크</th>
                                	<td colspan="3">
                                		<%if(e.getEventLink().equals("null")){ %>
                                		없음
                                		<%}else{ %>
                                			<a href=<%=e.getEventLink() %>>이벤트 링크 바로가기[이동]</a>
                                		<%} %>
                                	</td>
                                </tr>
                                
                                <tr >
                                    <th>내용</th>
                                    <td colspan="3">
                                    <%=e.getEventContent() %><br>
									</td>
                                </tr>
                                <%if(e.getFilepath()!= null){ %>
                                <tr>
                                	<th colspan="4" class="img-wrap">
                 	                 
                                    <img src="/upload/event/<%=e.getFilepath() %>">	<br>
                                    
                                	</th>
                                </tr>
                                <%} %>
                                <tr style="text-align:center">
                                    <th colspan="4">
                                        <a href="javascript:history.go(-1)" class="btn btn-primary btn-sm">목록으로</a>
                                    </th>
                                </tr>
                            </table>
                            <br>
                        </div>

                        <script>
                            function fileDownload(filename, filepath) {
                                var url = "/eventFileDownload";
                                var enFilename = encodeURIComponent(filename);
                                var enFilepath = encodeURIComponent(filepath);
                                location.href = url + "?filename=" + enFilename + "&filepath=" + enFilepath;
                            }
                            
                            $(document).ready(function(){
                            	var height = $(".tableEdit").height();
                            	$(".table-wrapper").height(height+70);
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