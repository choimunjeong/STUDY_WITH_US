package questionboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import questionboard.model.service.QuestionBoardService;
import questionboard.model.vo.QuestionBoardPage;

/**
 * Servlet implementation class QuestionListServlet
 */
@WebServlet(name = "QuestionList", urlPatterns = { "/questionList" })
public class QuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//비즈니스 로직
		QuestionBoardPage qbp = new QuestionBoardService().selectList(reqPage);
		//4.결과값 처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/question/questionList.jsp");
		request.setAttribute("list", qbp.getList());
		request.setAttribute("pageNavi", qbp.getPageNavi());
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
