package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class NaverLoginServlet
 */
@WebServlet(name = "NaverLogin", urlPatterns = { "/naverLogin" })
public class NaverLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NaverLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String state = request.getParameter("state");
		String code = request.getParameter("code");
		String client = "cHsZQp1qD4PC9jEE4eUk";
		String secret = "WX8FNtkno0";
		String path = "https://nid.naver.com/oauth2.0/token?client_id=" + client + "&client_secret=" + secret
				+ "&grant_type=authorization_code&state=" + state + "&code=" + code + "";
		Member m = null;

		if (state.equals(session.getAttribute("state"))) {
//			RequestDispatcher rd = request.getRequestDispatcher(path);
			HashMap<String, String> token = new MemberService().getAccessToken(path);
			if (token.get("access") != null) {
				HashMap<String, String> userInfo = new MemberService().getUserInfo(token);
				if (userInfo != null) {
					m = new Member();
					m.setFilename(userInfo.get("image"));
					m.setFilepath(userInfo.get("image"));
					m.setMemberEmail(userInfo.get("mail"));
					m.setMemberId(userInfo.get("id"));
					m.setMemberName(userInfo.get("name"));
					m.setMemberNickname(userInfo.get("nickName"));

					int result = new MemberService().naverLogin(m);

					if (result > 0) {
						Member loginMember = new MemberService().selectSnsMember(m.getMemberId());
						session.setAttribute("member", loginMember);
						RequestDispatcher rd = request.getRequestDispatcher("/");
						rd.forward(request, response);
					}
				}
			} 
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "잘못된 접근");
			request.setAttribute("loc", "/views/login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
