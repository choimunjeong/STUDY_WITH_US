<%@page import="groupstudy.model.vo.GroupStudyMember"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="groupstudy.model.vo.Category"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
       GroupStudyRoom gsr = (GroupStudyRoom)request.getAttribute("gsr");
       Category category = (Category)request.getAttribute("category");
       //int memberCnt = (Integer)request.getAttribute("memberCnt");
       ArrayList<GroupStudyMember> gsmList = (ArrayList<GroupStudyMember>)request.getAttribute("gsmList");
       ArrayList<Integer> gaMemberNoList = (ArrayList<Integer>)request.getAttribute("gaMemberNoList");//참여요청한 memberNo전부가져옴
    %>
    <%-- <% groupstudyroom %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<title><%=gsr.getGroupTitle() %></title>
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
        height: 920px;
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
      padding-top: 30px;   
   }
   .category{
      font-size: 18px;
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
      outline: none;
      border: none;
   }
   .line{
       margin: 0;
       width: 450px;
       margin-top: 5px;
       margin-bottom: 20px;
       margin-left: 70px;
       background-color: #5f5f5f ;
   }
   /* 모달------------------------------------------------------------ */
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
   }
   
    .modal-wrap123{
      position: absolute;
      top: 0px;
      left: 0px;
      width: 100vw;
      height: 160vh;
      background-color: rgba(0,0,0,0.5);
      display: none;
      justify-content: center;
      align-items: center;
      z-index:100;
    }
    .modal123{
      background-color: #fff;
      width: 40vw;
      height: 30vh;
      max-width: 500px;
      min-width: 300px;
      min-height: 340px;
      border-radius: 10px;
      box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.5);
    }
    .modal-top123>h4{
      text-align: center;
      color: green;
    }
    .modal-content123{
       padding-left: 50px;
       padding-right: 50px;
    }
    .modal-button123>input{
       background-color: white;
       font-weight: bold;
         outline: none;
         width: 49%;
         height:30px;
         border: 0;
         box-sizing: border-box;
         padding: 0;
    }
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
                <div class="groupTitle"><%=gsr.getGroupTitle() %></div>
                    <div class="groupContent">
                       <div class="category">분류 | <%=category.getCategory1() %> > <%=category.getCategory2() %></div>
                       <div class="gcWrap">
                          <div class="gc">
                             <p class="gcTitle">스터디기간</p>
                             <hr class="line">
                             <p class="gcContent">시작일 : <%=gsr.getGroupStartDate() %> ~ 종료일 : <%=gsr.getGroupEndDate() %></p>
                          </div>
                          <div class="gc">
                             <p class="gcTitle">참여인원</p>
                             <hr class="line">
                             <p class="gcContent">
                                <%for(int i=0;i<gsr.getGroupPersonnel();i++){ %>
                                   <%if(i<gsmList.size()){ %>
                                      <img src="/img/fill_human1.png">
                                   <%}else{ %>
                                      <img src="/img/empty_human1.png">
                                   <%} %>
                                <%} %>
                                <br>
                                총 인원 <%=gsr.getGroupPersonnel() %>명 / 현재 <%=gsmList.size() %>명이 참여 중입니다.
                             </p>
                             <br><br>
                          </div>
                       </div>
                       <div class="gcWrap">
                          <div class="gc">
                             <p class="gcTitle">스터디 규칙</p>
                             <hr class="line">
                             <textarea class="gcContent" rows="7" cols="45" style="resize: none;" readonly="readonly"><%=gsr.getGroupRule() %></textarea>
                          </div>
                          <div class="gc" style="height: 200px;">
                             <p class="gcTitle"><img src="/img/Star1.png">스터디 공통계획</p>
                             <hr class="line">
                             <p class="gcContent">
                                <%StringTokenizer tokens = new StringTokenizer(gsr.getGroupExplan(),"_"); %>
                                <%while(tokens.hasMoreElements()){ %>
                                   <img src="/img/Vector33.png" style="margin-left: 10px;"> <%=tokens.nextToken() %><br>
                                <%} %>
                             </p>
                             <br><br>
                          </div>
                       </div>
                       <div class="gcWrap" style="height: 230px;">
                          <div class="gc" style="width: 90%">
                             <p class="gcTitle">스터디원에게 한마디</p>
                             <hr class="line">
                             <p class="gcContent"><%=gsr.getGroupContentBr() %></p>
                          </div>
                       </div>
                       <hr width="80%">
                       <br>
                       <div class="modalBtnDiv">
                          <button id="btn" style="border: none;">참여 요청</button>
                       </div>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
      </div>
      
      <!-- 모달---------------------------------- -->
      <div class="modal-wrap123">
          <div class="modal123">
               <div class="modal-top123">
                  <br>
                 <h4>그룹장에게 참여 요청 메시지를 보냅니다</h4>
                 <hr>
               </div>
               <form action="/insertApplyGroupMember" method="post">
               <%if(m!=null){%>
                  <input type="hidden" name="memberNo" value="<%=m.getMemberNo()%>"> <!-- 참여요청하려는 사용자의 memberNo -->
               <%}%>
               <input type="hidden" name="groupNo" value="<%=gsr.getGroupNo()%>"> <!-- 현재페이지의 groupNo -->
               
               <div class="modal-content123">
                    <p>그룹장에게 자신을 소개해보세요</p>
                  <textarea rows="5" cols="53" name="applyContent" style="resize: none" required="required"></textarea>
               </div>
               <hr width="80%;">
               <div class="modal-button123">
                  <input type="submit" value="전송">
                 <input type="button" value="취소">
               </div>
               </form>
          </div>
        </div>
    </section>
    <script>
    /* 모달----------------------------------------- */
    $(function(){
       $("[name=groupContent]").click(function(event){
          event.stopPropagation();
       });
        $("#btn").click(function(){   //참여요청 버튼 클릭시
           <%
              int check = 0;
              int applyCheck = 0;
              for(GroupStudyMember gsm : gsmList){
                  if(m!=null && m.getMemberNo()==gsm.getMemberNo()){
                     check = 1;
                  }
               }
              for(int memberNo : gaMemberNoList){
                 if(m!=null && m.getMemberNo()==memberNo){
                    applyCheck = 1;
                 }
              }
           %>
           <%if(m==null){ %>   //로그인안했을때
            alert("로그인 후 이용가능한 페이지입니다");
            location.href="/views/login.jsp";
         <%}else if(gsmList.size()==gsr.getGroupPersonnel()){ //이미 인원이 꽉찼을때 %>      
                alert("이 방은 인원이 가득찬 방으로 참여요청이 불가능합니다");
             <%}else if(check==1){ //요청하는 사용자가 이미 참여중인 스터디인경우 %>
                alert("이미 참여중인 스터디입니다.");
             <%}else if(applyCheck==1){ //참여요청한 이력이 있는경우 %>
                 alert("이미 참여요청을 보낸 그룹스터디 입니다.\n그룹장의 허가를 기다려주세요.");
             <%}else{%>
                $(".modal-wrap123").css("display","flex");
             <%}%>
        });
        
        $("input[type=button]").click(function(){
          $(".modal-wrap123").css("display","none");
        });
      })
    </script>
</body>
</html>