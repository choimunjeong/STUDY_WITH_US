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
 * Servlet implementation class EventListServlet
 */
@WebServlet(name = "EventList", urlPatterns = { "/eventList" })
public class EventListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		if(memberNo==0) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "로그인 후 이용가능한 페이지 입니다");
			request.setAttribute("loc", "/views/login.jsp");//로그인화면으로 이동
			rd.forward(request, response);
		}else {
			int reqPage = Integer.parseInt(request.getParameter("reqPage"));
			EventBoardPage ebp = new EventBoardService().selectList(reqPage);
			//4. 결과값 처리
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/event/eventList.jsp");
			request.setAttribute("list", ebp.getList());
			request.setAttribute("pageNavi", ebp.getPageNavi());
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
