package noticeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;
import noticeboard.model.service.NoticeBoardService;
import noticeboard.model.vo.NoticeBoardPage;

/**
 * Servlet implementation class NoticeMangerListServlet
 */
@WebServlet(name = "NoticeMangerList", urlPatterns = { "/noticeManagerList" })
public class NoticeMangerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeMangerListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 객체 가져오기
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		//3. 비즈니스 로직
		NoticeBoardPage nbp = new NoticeBoardService().seleteList(reqPage);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/notice/noticeManagerList.jsp");
		request.setAttribute("list", nbp.getList());
		request.setAttribute("pageNavi", nbp.getPageNavi());
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
