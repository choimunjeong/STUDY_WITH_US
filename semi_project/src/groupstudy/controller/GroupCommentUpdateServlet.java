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
 * Servlet implementation class GroupCommentUpdateServlet
 */
@WebServlet(name = "GroupCommentUpdate", urlPatterns = { "/groupCommentUpdate" })
public class GroupCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		String commentContent = request.getParameter("commentContent");
		
		int result = new GroupStudyService().updateGroupComment(commentNo, commentContent);
		
		
		
		
		if(result>0) {
			RequestDispatcher rd = request.getRequestDispatcher("/myPlanGroupDetail?groupNo="+groupNo+"&category1="+category1+"&category2="+category2);
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "댓글 수정 실패");
			request.setAttribute("loc", "/myPlanGroupDetail?groupNo="+groupNo+"&category1="+category1+"&category2="+category2);
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