<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    	String chkResult = (String)request.getAttribute("msg");
    	String id = (String)request.getAttribute("id");
    	//네이버 로그인
    	    String clientId = "cHsZQp1qD4PC9jEE4eUk";//애플리케이션 클라이언트 아이디값";
    	    String redirectURI = URLEncoder.encode("http://localhost/naverLogin", "UTF-8");
    	    SecureRandom random = new SecureRandom();
    	    String state = new BigInteger(130, random).toString();
    	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    	    apiURL += "&client_id=" + clientId;
    	    apiURL += "&redirect_uri=" + redirectURI;
    	    apiURL += "&state=" + state;
    	  session.setAttribute("state", state);

    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="933752867537-c41ib5spq1iea0nqonfrnq807nohmj67.apps.googleusercontent.com">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="/js/login.js" defer></script>
<link rel="stylesheet" type="text/css" href="/css/login.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript"
		src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
		charset="utf-8"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<title>로그인</title>
<style>
.snsLogin *{
	width:150px;
	height : 50px;
}

</style>
</head>
<body>
	<form action="/login" method="post">
		<div class="login_container">
			<div class="logo_container">
				<a href="/index.jsp"><img src="/img/logo.png"></a>
			</div>
			<div class="login_box">
				<span>아이디</span>
				<%if(id != null){ %>
				<input type="text" id="id_Input" name="loginId" value=""
					required>
				<%}else{ %>
				<input type="text" id="id_Input" name="loginId" required>
				<%} %>

			</div>
			<div class="login_box">
				<span>비밀번호</span><input type="password" id="pass_Input"
					name="loginPw" required>
			</div>
			<%if(chkResult != null){ %>
			<span id="chkResult"><%=chkResult %></span>
			<%}else{ %>
			<span id="chkResult"></span>
			<%} %>
			<div class="button_box">
				<button type="submit">로그인</button>
			</div>
			<div class="find_box">
				<span><a href="/views/find.jsp">아이디 또는 비밀번호 찾기 / </a></span> <span><a
					href="/joinFrm">회원가입</a></span>
			</div>
			<div class="snsLogin">
				<a id="kakao-login-btn"></a>
				<a id="naver_id_login" href="<%=apiURL%>"><img src="/img/naver_login.png"></a>
			</div>
			
		</div>
	</form>
</body>
<script>
Kakao.init('74493d26ed184b36cd47a8c33ae186c9');
Kakao.Auth.createLoginButton({
	container: "#kakao-login-btn",
	success: function(authObj) {
		Kakao.API.request({
			url:'/v2/user/me',
			success: function(res){
				var id = res.id;
				var name = res.properties.nickname;
				var image = res.properties.profile_image;
				Kakao.API.request({
	                   url: '/v1/user/unlink',
	                   success: function(res) {
	                
	                     alert('success: ' + JSON.stringify(res))
	                   },
	                   fail: function(err) {
	                     alert('fail: ' + JSON.stringify(err))
	                   },
	              });
				$.ajax({
					url:"/snsLogin",
					type:"post",
					data:{id:id,name:name,image:image},
					success : function(data){
						if(data){
							location.href="/";
						}
						else{
							alert("알수없는 에러");
							location.href="/views/login.jsp";
						}
					}
				});
			}
		});

		console.log(authObj);
		var token = authObj.access_token;
		console.log(token);
	},
	fail : function(err){
		alert(JSON.stringify(err));
	}
});

/*
var naver_id_login = new naver_id_login("cHsZQp1qD4PC9jEE4eUk","http://localhost/naverLogin");
var state = naver_id_login.getUniqState();
naver_id_login.setButton("white",2,40);
naver_id_login.setDomain("http://localhost:80");
naver_id_login.setState(state);
naver_id_login.setPopup();
naver_id_login.init_naver_id_login();

alert(naver_id_login.oauthParams.access_token);
naver_id_login.get_naver_userprofile("naverSignInCallback()");
// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
function naverSignInCallback() {
  alert(naver_id_login.getProfileData('email'));
  alert(naver_id_login.getProfileData('nickname'));
  alert(naver_id_login.getProfileData('age'));
}*/
</script>
</html>