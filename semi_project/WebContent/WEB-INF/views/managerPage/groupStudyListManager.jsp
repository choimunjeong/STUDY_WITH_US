<%@page import="java.util.HashMap"%>
<%@page import="groupstudy.model.vo.GroupStudyMember"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
    	ArrayList<GroupStudyRoom> list = (ArrayList<GroupStudyRoom>)request.getAttribute("list");
		String pageNavi = (String)request.getAttribute("pageNavi");
		HashMap<Integer, Integer> memberCount = (HashMap<Integer, Integer>)request.getAttribute("memberCount");
	%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>관리자|그룹스터디 관리</title>
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

</style>

<body>
	 <section>
         <div class="wrap">
            <div class="header">
                <%@ include file="/WEB-INF/views/common/managerHeader.jsp"%>
            </div>
            <div class="myplan">
                    <div class="groupListTitle">그룹스터디 관리</div>
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
                            <table class="table" style="width:95%;  margin:0 auto; text-align:center; "">
                                <tr>
                                    <th>선택</th>
                                    <th>그룹번호</th>
                                    <th>그룹장</th>
                                    <th>제목</th>
                                    <th>기간</th>
                                    <th>인원수</th>

                                </tr>
                                <%for(GroupStudyRoom gsr : list){ %>
                                <tr>
                                    <td><input type="checkbox" class="chk"></td>
                                    <td style="display:none"><%=gsr.getGroupNo() %></td>
                                    <td><%=gsr.getGroupNo() %></td>
                                    <td><%=gsr.getGroupManagerNo() %></td>
                                    <td><%=gsr.getGroupTitle() %></td>
                                    <td><%=gsr.getGroupStartDate() %>~<%=gsr.getGroupEndDate() %></td>
                                    <%if(memberCount.containsKey(gsr.getGroupNo())){ %>
                                    	<td><%=memberCount.get(gsr.getGroupNo()) %>/<%=gsr.getGroupPersonnel() %></td>
                                    <%}else{ %>
                                    	<td>1/<%=gsr.getGroupPersonnel() %></td>
                                    <%} %>
                                </tr>
                                <%}  %>
                                <tr>
                                    <th>
                                        <button class="btn btn-primary btn-sm deleteAllBtn">삭제하기</button>
                                    </th>
                                    <td colspan="8"></td>
                                </tr>
                            </table>
                            <br>
                            <div class="text-center" style="width:100%; margin:0 auto;">
								<ul class="pagination" >
									<%=pageNavi %>
								</ul>
                            </div>
                        </section>


                        <script>
                            $(".deleteAllBtn").click(function() {
                                var inputs = $("[type=checkbox]:checked");
                                var num = new Array();
                                inputs.each(function(idx, item) {
                                    num.push($(item).parent().next().html());
                                });
                                if(num.length==0){
                                	alert('삭제할 그룹을 선택해주세요.');
                                }else{
                                	location.href = "/deleteAllGroup?num=" + num.join("/");
                                }
                            });
                            
                            //사이드메뉴바 호버기능
                            $(".leftMenuA").hover(function(){
                               $(".leftMenuA").eq(1).attr("style","border-color : white");
                               $(this).attr("style","border-color : #75D701");
                            },function(){
                               $(".leftMenuA").attr("style","border-color : white");
                               $(".leftMenuA").eq(1).attr("style","border-color : #75D701");
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