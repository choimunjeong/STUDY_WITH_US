package questionboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questionboard.model.service.QuestionBoardService;
import questionboard.model.vo.QuestionBoard;

/**
 * Servlet implementation class ManagerCommentDeleteServlet
 */
@WebServlet(name = "ManagerCommentDelete", urlPatterns = { "/managerCommentDelete" })
public class ManagerCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		int result = new QuestionBoardService().deleteComment(questionNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "답변이 삭제되었습니다.");
		}else {
			request.setAttribute("msg", "실패");
		}
		request.setAttribute("loc", "/questionView?questionNo="+questionNo+"&writerId=&memberId=admin");
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
