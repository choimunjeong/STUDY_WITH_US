package groupstudy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class DeleteGroupCommentFileServlet
 */
@WebServlet(name = "DeleteGroupCommentFile", urlPatterns = { "/deleteGroupCommentFile" })
public class DeleteGroupCommentFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroupCommentFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String filepath = request.getParameter("filepath");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		
		int result = new GroupStudyService().deleteGroupComment(commentNo);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {//첨부파일을 삭제해줘야함
			String saveDirectory = getServletContext().getRealPath("/")+"/upload/groupImg/";
			File delFile = new File(saveDirectory+filepath);
			if(delFile.delete()) {
				System.out.println(filepath+"첨부파일삭제 성공");
			}else {
				System.out.println(filepath+"첨부파일삭제 실패");
			}
			request.setAttribute("msg", "삭제에 성공하였습니다");
		}else {
			request.setAttribute("msg", "삭제에 실패하였습니다");
		}
		request.setAttribute("loc", "/myPlanGroupDetail?groupNo="+groupNo+"&category1="+category1+"&category2="+category2);
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
