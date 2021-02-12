package eventboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventboard.model.service.EventBoardService;
import eventboard.model.vo.EventBoard;

/**
 * Servlet implementation class EventManagerViewServlet
 */
@WebServlet(name = "EventManagerView", urlPatterns = { "/eventManagerView" })
public class EventManagerViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventManagerViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.뷰에서 넘어온 값 저장
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		String manager = request.getParameter("manager");
		//3.비즈니스 로직
		EventBoard e = new EventBoardService().selectEventView(eventNo);
		if(manager.equals("yes")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/event/eventManagerView.jsp");
			request.setAttribute("e", e);
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/event/eventView.jsp");
			request.setAttribute("e", e);
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
