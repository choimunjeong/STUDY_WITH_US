package eventboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventboard.model.service.EventBoardService;
import eventboard.model.vo.EventBoardPage;

/**
 * Servlet implementation class EventManagerListServlet
 */
@WebServlet(name = "EventManagerList", urlPatterns = { "/eventManagerList" })
public class EventManagerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventManagerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 전달한 값 저장
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//3. 비즈니스 로직
		EventBoardPage ebp = new EventBoardService().selectList(reqPage);
		//4. 결과값 처리
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/event/eventManagerList.jsp");
		request.setAttribute("list", ebp.getList());
		request.setAttribute("pageNavi", ebp.getPageNavi());
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
