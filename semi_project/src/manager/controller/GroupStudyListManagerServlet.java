package manager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;
import groupstudy.model.vo.GroupManagePage;

/**
 * Servlet implementation class GroupStudyListManagerServlet
 */
@WebServlet(name = "GroupStudyListManager", urlPatterns = { "/groupStudyListManager" })
public class GroupStudyListManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupStudyListManagerServlet() {
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
		GroupManagePage gmp = new GroupStudyService().seleteList(reqPage);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/managerPage/groupStudyListManager.jsp");
		request.setAttribute("list", gmp.getList());
		request.setAttribute("pageNavi", gmp.getPageNavi());
		request.setAttribute("memberCount", gmp.getMemberCount());
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
