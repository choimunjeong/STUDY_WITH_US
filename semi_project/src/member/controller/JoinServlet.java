package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet(name = "Join", urlPatterns = { "/join" })
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String nickName = request.getParameter("nickName");
		String memberEmail = request.getParameter("memberMail");
		String fileName = request.getParameter("pro_img");
		String filePath = "/img/ex"+fileName;
		
		Member m = new Member();
		
		m.setFilename(fileName);
		m.setFilepath(filePath);
		m.setMemberEmail(memberEmail);
		m.setMemberId(memberId);
		m.setMemberName(memberName);
		m.setMemberNickname(nickName);
		m.setMemberPw(memberPw);
		
		int result = new MemberService().insertMember(m);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		request.setAttribute("loc", "/");
		
		if(result>0) {
			System.out.println("성공");
			request.setAttribute("msg","회원가입에 성공했습니다." );
			
		}
		else {
			System.out.println("실패");
			request.setAttribute("msg", "회원가입에 실패했습니다.");
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
