<%@page import="java.util.HashMap"%>
<%@page import="groupstudy.model.vo.GroupComment"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="groupstudy.model.vo.GroupStudyRoomAddCategory"%>
<%@page import="groupstudy.model.vo.GroupStudyRoom"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
       GroupStudyRoom gsr = (GroupStudyRoom)request.getAttribute("gsr");
       ArrayList<GroupComment> gcList = (ArrayList<GroupComment>)request.getAttribute("gcList"); 
       String category1 = (String)request.getAttribute("category1");
       String category2 = (String)request.getAttribute("category2");
       int memberCnt = (Integer)request.getAttribute("memberCnt");
       //ArrayList<Member> memberFilepath = (ArrayList<Member>)request.getAttribute("memberFilepath");
       HashMap<String, String> memberIdFileMap = (HashMap<String, String>)request.getAttribute("memberIdFileMap");
       String groupEndDate = gsr.getGroupEndDate();
    %>
    
    <%-- <% groupstudyroom %> --%>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
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
        font-family: Roboto;
    }
    .leftMenu {
        width: 182px;
        height: 1300px;
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
    .leftMenuList>li:last-child>a{
        border-color: #75D701;
    }
    
    .leftMenuList>li:first-child{
        text-indent: 0;
        text-align: center;
        font-weight: bold;
        font-size: 20px;
    }
    
    .inputComment>form{
       display: flex;
    }
    
    
    
    /* ---------------------------------------------------------- */
    .gl{
       overflow: hidden;
       padding-left: 100px;
    }
    .gl1-1{
       float: left;
    }
    .greenSquare{
       width: 78px;
       height: 36px;
       border-radius: 5px;
       background-color: #3B4E32;
       text-align: center;
       line-height: 36px;
       font-weight: bold;
       font-size: 20px;
       color: white;
    }
    .dDayDiv{
       overflow: hidden;
       width: 600px;
    }
    .gl1-2{
       float: left;
    }
    
    .gl1-1:first-child{ /* 제목이랑 사진들어갈곳 크기지정함 */
       margin-right: 30px;
       width: 200px;
    }
    .gl1-2:first-child {
      width: 25%;
   }
   .gl1-2:nth-child(2) {
      width: 40%;
      font-size: 18px;
   }
   .gl1-2:last-child {
      width: 35%;
      text-align: center;
   }
    /* 목록/수정/삭제/스터디나기기버튼---------------------------------------------------------- */
   .btnDiv{
      text-align: right;
      height: 50px;
   }
   .btnAll{
      text-align: center;
      display: inline-block;
      margin: 5px;
      color: black;
      border: 1px solid #B4B4B4;
      height: 35px;
      line-height: 35px;
      width: 70px;
      font-family: Roboto;
   }
   .glTitle{
      color: black;
      font-weight: bold;
      text-indent: 100px;
      font-size: 17px;
      height: 30px;
      line-height: 30px;
      font-family: Roboto;
   }
   .gl{
      margin-top: 20px;
      margin-bottom: 50px;
   }
   th{
      height: 20px;
   }
   .commentWrap{
      overflow: hidden;
   }
   .commentList{
      float: left;
   }
   td{
      text-align: center;
   }
   
   .line{
      width: 85%;
      margin: 0;
      margin-left: 75px;
   }
   
   .{
      overflow: scroll;
      height: 200px;
      overflow-x: hidden;
   }
   .attachmentScroll{
      overflow: scroll;
      height: 200px;
      overflow-x: hidden;
   }
   .commentScroll{
      overflow: scroll;
      height: 400px;
      overflow-x: hidden;
   }
   
}
    
</style>
</head>
<body>
    <section>
        <div class="wrap">
            <div class="header">
               <%@ include file="/WEB-INF/views/common/header.jsp"%>
            </div>
            <div class="myplan">
               <div class="groupListTitle" style="font-weight: bold">[ <%=gsr.getGroupTitle() %> ]</div>
                <div class="leftMenu">
                    <ul class="leftMenuList">
                       <li>개인스터디</li>
                        <li><a class="leftMenuA" href="/todayPlan?memberNo=<%=m.getMemberNo()%>"><img src="/img/calender2.png" style="margin-right: 10px;">일일 계획</a></li>
                        <li><a class="leftMenuA" href="/myStudyCalender?memberNo=<%=m.getMemberNo()%>"><img src="/img/day2.png" style="margin-right: 10px;">나의 스케줄</a></li>
                        <li><a class="leftMenuA" href="/myGroupStudyList?memberNo=<%=m.getMemberNo()%>"><img src="/img/group_icon2.png" style="margin-right: 10px;">참여중인 스터디</a></li>
                    </ul>
                </div>
                <div class="participatingGroup">
                    
                    <div class="groupList">
                       <div class="btnDiv">
                          <a href="/myGroupStudyList?memberNo=<%=m.getMemberNo()%>" class="btnAll">목록으로</a>
                          <%if(gsr.getGroupManagerNo()==m.getMemberNo()){//방장인 경우 %>
                             <a href="/updateGroupStudyRoomFrm?groupNo=<%=gsr.getGroupNo() %>&category1=<%=category1 %>&category2=<%=category2 %>" class="btnAll">수정</a>
                             <a href="" class="btnAll" id="studyDelete">삭제</a><!-- script로처리 -->
                          <%}else{ //단순한 참여자인경우%>
                             <a href="" class="btnAll" id="studyExit" style="width: 100px;">스터디 나가기</a><!-- script로처리 -->
                          <%} %>
                       </div>
                       <div class="gl"><!-- 메인이미지넣는곳 -->
                          <div class="gl1-1">
                             <%if(gsr.getFilepath()==null){ %>
                                <img src='/img/basic.png' style="width: 100%; height: 200px; object-fit: contain; border: none;">
                             <%}else{ %>
                                <img src='/upload/groupImg/<%=gsr.getFilepath() %>' style="width: 100%; height: 200px; object-fit: contain; border: none;">
                             <%} %>
                          </div>
                          <div class="gl1-1">
                             <div class="dDayDiv">
                                <div class="gl1-2">
                                   <div class="greenSquare"></div>
                                   <div id="dDayStatus" style="text-indent: 8px;"></div>
                                </div>
                                <div class="gl1-2">
                                   시작 날짜 : <%=gsr.getGroupStartDate() %><br>
                                   종료 날짜 : <%=gsr.getGroupEndDate() %>
                                </div>
                                <div class="gl1-2">
                                   <img src="/img/group_icon3.png"><br>
                                   참여인원 : <%=memberCnt %> / <%=gsr.getGroupPersonnel() %>
                                </div>
                             </div>
                             <div>
                                <p style="font-weight: bold; color: black;">스터디 상세내용</p>
                                <p class="gcContent" style="border: 1px solid gray; padding-left: 10px; width: 550px;"><%=gsr.getGroupContentBr() %></p>
                             </div>
                          </div>
                       </div>
                       <div class="glTitle">
                          우리 스터디 규칙
                          <hr class="line">
                       </div>
                       <div class="gl">
                          <div>
                             <p class="gcContent"><%=gsr.getGroupRuleBr() %></p>
                          </div>
                       </div>
                       <div class="glTitle">
                          우리 스터디 목표
                          <hr class="line">
                       </div>
                       <div class="gl">
                          <div>
                             <%StringTokenizer tokens = new StringTokenizer(gsr.getGroupExplan(),"_"); %>
                             <%for(int i = 0;tokens.hasMoreElements();i++){ %>
                                   <img src="/img/Vector33.png" style="margin-left: 10px;"> <%=tokens.nextToken() %><br>
                             <%} %>
                          </div>
                       </div>
                       <div class="glTitle" style="overflow: hidden;">
                          <div style="float: left; width: 70%;">자료실</div>
<!-- 첨부파일 업로드!!!! -->       <div style="float: left; width: 20%; margin-right: 30px;">
                             <form action="/insertGroupCommentFile" id="uploadFrm" method="post" enctype="multipart/form-data"> 
                                <label for="upBtn" id="uploadLabel" class="btnAll" style="text-indent: 0; font-weight: 100; font-size: 12px;  height: 25px; line-height: 25px;">업로드<input id="upBtn" type="file"  name="filename" onchange="uploadFile(this)" style="display: none;"></label>
                                <input type="hidden" name="commentTitle">
                                <input type="hidden" name="commentWriter" value="<%=m.getMemberId()%>">
                                <input type="hidden" name="groupNo" value="<%=gsr.getGroupNo()%>">
                                <input type="hidden" name="category1" value="<%=category1%>">
                                <input type="hidden" name="category2" value="<%=category2%>">
                             </form>
                          </div>
                       </div>
                       <div class="gl" id="attachment" style="padding-left: 80px;padding-right: 20px; width: 93%; ">
                          <table class="table table-sm">
                        <tr>
                           <th width="10%" style="text-align: center;">작성자</th>
                           <th width="45%" style="text-align: center;">내용</th>
                           <th width="20%" style="text-align: center;">첨부파일</th>
                           <th width="10%" style="text-align: center;">삭제</th>
                        </tr>
                        <%for(GroupComment gc : gcList){ %>
                           <%if(gc.getFilename()!=null){ //자료실에는 첨부파일이 있는 값만 출력%>
                              <tr>
                                 <td><%=gc.getCommentWriter() %></td>
                                 <td><%=gc.getCommentTitle() %></td>
                                 <td><a href="javascript:fileDownload('<%=gc.getFilename()%>','<%=gc.getFilepath()%>')"><%=gc.getFilename() %></a></td>
                                 <td>
                                    <!-- 파일을 올린사용자는 본인것만 삭제가능 / 방장은 모든 자료삭제가능 -->
                                    <%if(m.getMemberId().equals(gc.getCommentWriter()) || m.getMemberNo()==gsr.getGroupManagerNo()){%>
                                       <a href="javascript:void(0)" style="color: black;" onclick="deleteFile(this,'<%=gc.getCommentNo()%>','<%=gc.getFilepath()%>','<%=gc.getGroupNo()%>','<%=category1%>','<%=category2%>')">삭제</a>
                                    <%} %>
                                 </td>
                              </tr>
                           <%} %>
                        <%} %>
                     </table>
                       </div>
                       <div class="glTitle">
                          댓글
                          <hr class="line">
                       </div>
                       <div class="gl" style="padding-left: 70px;">
                          <!-- 댓글 입력하는 창 -->
                          <div class="inputComment">
                        <form action="/insertGroupComment" method="post">
                           <input type="hidden" name="groupNo" value="<%=gsr.getGroupNo()%>"> <!-- 스룹스터디no -->
                           <input type="hidden" name="commentWriter" value="<%=m.getMemberId()%>"> <!-- 작성자 -->
                           <input type='hidden' name='category1' value='<%=category1%>'>
                           <input type='hidden' name='category2' value='<%=category2%>'>
                           <textarea class="form-control rowCheck" name="commentContent" style="resize: none; width: 85%; display: inline-block; outline: none;" maxlength="65" required="required"></textarea> 
                           <button type="submit" class="btn btn-success btn-lg endDayCheck" style="background-color: #3B4E32">등록</button>
                        </form>
                     </div>
                     <!-- 전체 댓글 출력 및 본인 댓글 수정 / 삭제 -->
                     <div id="commentScrollDiv" style="width: 93%; margin-top: 20px;">
                          <%for(GroupComment gc : gcList){ %>
                          <%if(gc.getCommentContent()!=null){ %>
                             <div class="commentListWrap" style="clear:left;">
                           <div class="commentList" style="width: 10%;">
                              <img src="<%=memberIdFileMap.get(gc.getCommentWriter())%>" style="border-radius: 50%; width: 60px; height: 60px;" ><!-- 댓글을 쓴 사용자들의 프로필사진 -->
                           </div>
                           <div class="commentList" style="width: 77%;">
                              <p id="commentWriterP" style="margin: 0;"><%=gc.getCommentWriter() %></p>
                              <p class="oldContent"><%=gc.getCommentContentBr() %></p>
                              <textarea name="commentContent" class="form-control changeComment" style="display: none; resize: none;" required="required"><%=gc.getCommentContent() %></textarea>
                           </div>
                           <%if(m.getMemberId().equals(gc.getCommentWriter()) || m.getMemberNo()==gsr.getGroupManagerNo()){ //작성자랑 현재 접속자랑 같은 경우 수정/삭제 가능하게 || 그룹장인경우 삭제만가능하게%>
                              <div class="commentList" style="width: 13%;">
                                 <%if(m.getMemberId().equals(gc.getCommentWriter())){ %>
                                    <a href="javascript:void(0)" onclick="modifyComment(this,'<%=gc.getCommentNo()%>','<%=gsr.getGroupNo()%>')">수정</a>
                                 <%} %>
                                 <a href="javascript:void(0)" onclick="deleteComment(this,'<%=gc.getCommentNo()%>','<%=gsr.getGroupNo()%>','<%=category1%>','<%=category2%>')">삭제</a>
                              </div>
                           <%} %>
                        </div>
                        <%} %>
                     <%} //댓글 for문 종료 지점%>
                     </div>
                       </div>
                    </div>
                </div>
            </div>
            
      </div>
      <%@ include file="/WEB-INF/views/common/footer.jsp"%>
    </section>
    <script>
       $(function(){
          var countFile = 0;
          var countContent = 0;
          <%for(GroupComment gc : gcList){ %>
             <%if(gc.getFilename()!=null){%>//같은 테이블이라 파일이름이 있고없고와 댓글내용이 있고없고로 비교룰 한번 더 해줘야함
                countFile++;
                if(countFile>5){//첨부자료가 5개초과일 경우 스크롤생기게
                   $("#attachment").addClass("attachmentScroll");
                }
            <%}else if(gc.getCommentContent()!=null){%>//같은 테이블이라 파일이름이 있고없고와 댓글내용이 있고없고로 비교룰 한번 더 해줘야함
               countContent++;
               if(countContent>5){//댓글이 5개초과일 경우 스크롤 생기게
                   $("#commentScrollDiv").addClass("commentScroll");
                }
            <%}%>
          <%}%>
          
          //자료실에 자료가 5개 이상일 경우에만 스크롤바가 생기도록 수정
          <%if(gcList.size()>5){%>
             $("#attachment").addClass("overAuto");
          <%}%>
          
          //D-Day구하기
          var now = new Date();
          var groupEndDate = '<%=groupEndDate%>';
          console.log(groupEndDate);
           var endArray = groupEndDate.split('-');
           var endDate = new Date(endArray[0], endArray[1]-1, endArray[2]);//달은 -1해줘야함
           console.log(endDate);
          console.log(now);
           var dday = endDate.getTime() - now.getTime();
           dday = Math.floor(dday / (1000 * 60 * 60 * 24))+1;
           console.log(dday);
           if(dday<0){//종료일지가 지난경우
              $(".greenSquare").html("종료");
              $("#dDayStatus").html("종료됨");
           }else if(dday==0){//D-DAY인경우
              $(".greenSquare").html("D-"+dday);
              $("#dDayStatus").html("D-DAY!");
           }else{
              $(".greenSquare").html("D-"+dday);
              $("#dDayStatus").html("진행중");
           }
           
           //스터디 삭제하기 confirm
           $("#studyDelete").click(function(){
              if(!confirm("[<%=gsr.getGroupTitle()%>] 스터디를 삭제하시겠습니까?")){
                 $("#studyDelete").attr("href","javascript:void(0);");
              }else{
                 $("#studyDelete").attr("href","/deleteGroupStudyRoom?groupNo=<%=gsr.getGroupNo() %>&memberNo=<%=m.getMemberNo() %>");
              }
           });
           //스터디 나가기 confirm
           $("#studyExit").click(function(){
              if(!confirm("[<%=gsr.getGroupTitle()%>] 스터디를 나가시겠습니까?")){
                 $("#studyExit").attr("href","javascript:void(0);");
              }else{
                 $("#studyExit").attr("href","/deleteGroupStudyMember?memberNo=<%=m.getMemberNo()%>&groupNo=<%=gsr.getGroupNo() %>&groupTitle=<%=gsr.getGroupTitle()%>");
              }
           });
       });
       
       //종료된 스터디 일경우 댓글 입력 불가
       $(function(){
          $(".endDayCheck").click(function(e){
             if($("#dDayStatus").html()=="종료됨"){
                  alert("이미 종료된 스터디로 댓글등록이 불가능합니다");
                  e.preventDefault();
              }
          });
       });
       
       
       //자료실
        //첨부파일 업로드(제목먼저 입력받기)
        $(function(){
           $("#uploadLabel").click(function(e){
              if($("#dDayStatus").html()=="종료됨"){   //종료된 스터디일 경우 업로드 불가
                   alert("이미 종료된 스터디로 파일 업로드가 불가능합니다");
                   e.preventDefault();
                   return;
               }
              var contentTitle = prompt('파일과 함께 업로드할 내용을 입력해주세요', '');
              if(contentTitle==null){
                 e.preventDefault();
                 return;
              }
              $("input[name=commentTitle]").val(contentTitle);   
           });
           $("#upBtn").click(function(e){
              e.stopPropagation();
           });
        });
       
       
       //filename의 값이 바뀌면 바로 form태그 submit
       function uploadFile(filename){
          console.log("test");
          $("#uploadFrm").submit(); 
       }
       
       //첨부파일 다운로드
       function fileDownload(filename,filepath){//인코딩작업해주려고 자바스크립트로 함
         var url = "/groupCommentFileDownload";
         var encFilename = encodeURIComponent(filename);
         var encFilepath = encodeURIComponent(filepath);
         location.href=url+"?filename="+encFilename+"&filepath="+encFilepath;
      }
       
       //첨부파일 삭제
       function deleteFile(obj, commentNo, filepath, groupNo, category1, category2){
         if(confirm("파일을 삭제하시겠습니까?")){
            location.href="/deleteGroupCommentFile?commentNo="+commentNo+"&filepath="+filepath+"&groupNo="+groupNo+"&category1="+category1+"&category2="+category2;
         }
      }
       
       
       //-----------------------------------------------------------------------
       
       
       //댓글달기
      //수정버튼 클릭
      function modifyComment(obj, commentNo, groupNo,e){
         if($("#dDayStatus").html()=="종료됨"){   //종료된 스터디일 경우 댓글수정 불가
              alert("이미 종료된 스터디로 댓글 수정이 불가능합니다");
              e.preventDefault();
              return;
          }
         $(obj).parent().prev().children("textarea").show();   //textarea를 보여주는 코드
         $(obj).parent().prev().children("p").hide();   //p태그를 숨겨주는 코드
         //수정버튼 -> 수정완료버튼
         $(obj).html('수정완료');
         $(obj).attr('onclick','modifyComplete(this,"'+commentNo+'","'+groupNo+'")');
         //삭제버튼 -> 수정취소
         $(obj).next().html('취소');
         $(obj).next().attr('onclick','modifyCancel(this,"'+commentNo+'","'+groupNo+'")');
         $(obj).next().next().hide();
      }
      //수정취소 버튼
      function modifyCancel(obj,commentNo,groupNo){
         $(obj).parent().prev().children("textarea").hide();         //textarea를 숨기는 코드
         $(obj).parent().prev().children("p").show();   //p태그를 보여주는 코드
         //수정완료 -> 수정
         $(obj).prev().html('수정');
         $(obj).prev().attr('onclick','modifyComment(this,"'+commentNo+'","'+groupNo+'")');
         //취소 -> 삭제
         $(obj).html("삭제");
         $(obj).attr('onclick','deleteComment(this,"'+commentNo+'","'+groupNo+'")');
         $(obj).next().show();
      }
      //수정완료 버튼
      function modifyComplete(obj,commentNo,groupNo){
         var form = $("<form action='/groupCommentUpdate' method='post'></form>");
         form.append($("<input type='text' name='commentNo' value='"+commentNo+"'>"));
         form.append($("<input type='text' name='groupNo' value='"+groupNo+"'>"));
         form.append($("<input type='text' name='category1' value='<%=category1%>'>"));
         form.append($("<input type='text' name='category2' value='<%=category2%>'>"));
         form.append($(obj).parent().prev().children("textarea"));
         $("body").append(form);
         form.submit();
      }
      //댓글삭제하기
      function deleteComment(obj, commentNo, groupNo, category1, category2){
         if(confirm("댓글을 삭제하시겠습니까?")){
            location.href="/groupCommentDelete?commentNo="+commentNo+"&groupNo="+groupNo+"&category1="+category1+"&category2="+category2;
         }
      }
      
      
      //사이드메뉴바 호버기능
       $(".leftMenuA").hover(function(){
          $(".leftMenuA").attr("style","border-color : white");
          $(this).attr("style","border-color : #75D701");
       },function(){
          $(".leftMenuA").attr("style","border-color : white");
          $(".leftMenuA").eq(2).attr("style","border-color : #75D701");
       });
       
       
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