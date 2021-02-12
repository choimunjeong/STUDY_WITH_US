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

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		Member updateMember = new Member();
		updateMember.setMemberId(request.getParameter("memberId"));
		updateMember.setMemberNickname(request.getParameter("nickName"));
		updateMember.setMemberPw(request.getParameter("memberPw"));
		updateMember.setFilename(request.getParameter("pro_img"));
		updateMember.setFilepath("/img/ex"+request.getParameter("pro_img"));
		
		int result = new MemberService().updateMember(updateMember);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			session.invalidate();
			request.setAttribute("loc", "/views/login.jsp");
			request.setAttribute("msg", "회원정보 변경에 성공했습니다.\\n변경된 정보로 로그인해주세요.");
			
		}else {
			request.setAttribute("msg", "회원정보 변경에 실패했습니다.\\n다시 시도해주세요.");
			request.setAttribute("loc", "/myPage");
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
