package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.MemberManagePage;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		
		Member m = new Member();
		m.setMemberId(loginId);
		m.setMemberPw(loginPw);
		
		Member loginMember = new MemberService().selectOneMember(m);
		
		if(loginMember == null)
		{
			RequestDispatcher rdd = request.getRequestDispatcher("/views/login.jsp");
			request.setAttribute("msg", "아이디 혹은 비밀번호를 확인해주세요.");
			request.setAttribute("id", loginId);
			rdd.forward(request, response);
		}
		else if(loginMember.getMemberName().equals("관리자")) {
			MemberManagePage mmp = new MemberService().selectList(1);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/memberList.jsp");
			request.setAttribute("list", mmp.getList());
			request.setAttribute("pageNavi", mmp.getPageNavi());
			rd.forward(request, response);
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("member", loginMember);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "[ "+loginMember.getMemberNickname()+" ]님 환영합니다.");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
