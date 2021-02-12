package manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class ChangeLevelServlet
 */
@WebServlet(name = "ChangeLevel", urlPatterns = { "/changeLevel" })
public class ChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLevelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 넘어온 값 저장
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//3.비즈니스 로직
		int result = new MemberService().changeLevel(memberLevel, memberNo);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		request.setAttribute("loc", "/memberList?reqPage="+reqPage);
		if(result>0) {
			request.setAttribute("msg", "변경되었습니다.");
		}else {
			request.setAttribute("msg", "실패");
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
