package noticeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noticeboard.model.service.NoticeBoardService;
import noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class NoticeManagerViewServlet
 */
@WebServlet(name = "NoticeManagerView", urlPatterns = { "/noticeManagerView" })
public class NoticeManagerViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeManagerViewServlet() {
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		//3.비즈니스 로직
		NoticeBoard n = new NoticeBoardService().selectNoticeView(noticeNo);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/notice/noticeManagerView.jsp");
		request.setAttribute("n", n);
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
