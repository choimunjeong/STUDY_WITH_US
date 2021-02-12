package groupstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class DeleteGroupStudyMemberServlet
 */
@WebServlet(name = "DeleteGroupStudyMember", urlPatterns = { "/deleteGroupStudyMember" })
public class DeleteGroupStudyMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroupStudyMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String groupTitle = request.getParameter("groupTitle");
		
		int result = new GroupStudyService().deleteGroupStudyMember(memberNo, groupNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("msg", "["+groupTitle+"] 스터디를 나갔습니다.");
		}else {
			request.setAttribute("msg", "["+groupTitle+"] 스터디 나가기에 실패했습니다.");
		}
		request.setAttribute("loc", "/myGroupStudyList?memberNo="+memberNo);
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
