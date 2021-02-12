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
import questionboard.model.vo.QuestionBoard;

/**
 * Servlet implementation class QuestionInsertServlet
 */
@WebServlet(name = "QuestionInsert", urlPatterns = { "/questionInsert" })
public class QuestionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//폼 태그로 값을 전달하면 multipartrequest를 써줘야한다
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8" , new DefaultFileRenamePolicy());
		QuestionBoard q = new QuestionBoard();
		q.setQuestionContent(mRequest.getParameter("questionContent"));
		q.setQuestionWriterId(mRequest.getParameter("memberId"));
		q.setQuestionPw(Integer.parseInt(mRequest.getParameter("pwd")));
		q.setQuestionTitle(mRequest.getParameter("questionTitle"));
		
		//비즈니스 로직
		int result = new QuestionBoardService().insertQuestion(q);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "문의글 등록 성공");
		}else {
			request.setAttribute("msg", "실패");
		}
		request.setAttribute("loc", "/questionList?reqPage=1");
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
