package questionboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import questionboard.model.service.QuestionBoardService;

/**
 * Servlet implementation class ManagerQuestionInsertCommentServlet
 */
@WebServlet(name = "ManagerQuestionInsertComment", urlPatterns = { "/managerQuestionInsertComment" })
public class ManagerQuestionInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerQuestionInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8" , new DefaultFileRenamePolicy());
		int questionNo = Integer.parseInt(mRequest.getParameter("questionNo"));
		String comment = mRequest.getParameter("comment");
		int result = new QuestionBoardService().insertManagerComment(questionNo, comment);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "등록되었습니다.");
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
