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
 * Servlet implementation class QuestionViewServlet
 */
@WebServlet(name = "QuestionView", urlPatterns = { "/questionView" })
public class QuestionViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//값 저장
		String memberId = request.getParameter("memberId");
		String writerId = request.getParameter("writerId");
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));

		if(!writerId.equals("")) {
			//비밀글이고 작성자가 아닐때
			if( !memberId.equals(writerId)) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "비밀글입니다.");
				request.setAttribute("loc", "/questionList?reqPage=1");
				rd.forward(request, response);
			//비밀글이고 작성자일때
			}else {
				QuestionBoard q = new QuestionBoardService().selectOneQuestion(questionNo);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/question/questionView.jsp");
				request.setAttribute("q", q);
				rd.forward(request, response);
			}
		}else if(memberId.equals("admin")) {
			//관리자일때
			QuestionBoard q = new QuestionBoardService().selectOneQuestion(questionNo);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/question/questionManagerView.jsp");
			request.setAttribute("q", q);
			rd.forward(request, response);
		}else {
			//일반글
			QuestionBoard q = new QuestionBoardService().selectOneQuestion(questionNo);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/question/questionView.jsp");
			request.setAttribute("q", q);
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
