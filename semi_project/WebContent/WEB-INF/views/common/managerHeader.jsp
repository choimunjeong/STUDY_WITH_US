<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
    Member m = (Member)session.getAttribute("member");
    %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/header/style.css">
<link rel="stylesheet" href="/css/header/responsive.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<style>
	.nav{
		width: 1200px;
        margin: 0 auto;
	}
    .nav-wrap {
        position: absolute;
        z-index: 10;
        width: 1200px;
        height: 100px;
        overflow: hidden;
        margin: 0 auto;
        font-family: Roboto;
        background-color: #fff;
    }

    .nav-left {
        margin-top: 20px;
        width: calc(100%/6);
        height: 100%;
        float: left;
        text-align: center;
    }

    .nav-center {
        width: calc((100%/3)*2);
        float: left;
    }

    .nav-right {
        float: right;
        overflow: hidden;
    }

    .nav-right>div {
        width: 100%;
        text-align: center;
        line-height: 100px;
    }
    .nav-right>div>a{
        color: black;
    }

    ul {
        list-style-type: none;
    }

    a {
        text-decoration: none;
    }

    .nav-center>ul {
        overflow: hidden;
        margin: 0;
        padding-left: 0;
    }

    .nav-center>ul>li {
        float: left;
        width: 100%;
        height: 100px;
        
    }
    .nav-center>ul>li>h3{
    	line-height: 100px;
    }


    .nav-sub {
        width: 100%;
        padding: 0;
        display: none;
        position: relative;
    }

    .nav-sub>li {
        width: 100;
    }


</style>

<body>
   <div class="nav">
      <div class="nav-wrap">
          <div class="nav-left">
              <a href="index.jsp"><img src="/img/navi_logo.png" width="140px" height="70px"></a>
          </div>
          <div class="nav-center">
              <ul>
                  <li><h3>관리자 페이지</h3></li>
              </ul>
          </div>
          <div class="nav-right">
              <div>
                  <a href="/logout">관리자 페이지 나가기</a>
               </div>
          </div>
      </div>
   </div>
   <script>
   </script>	
		      		
</body>
