package groupstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;
import groupstudy.model.vo.GroupStudyPageData;

/**
 * Servlet implementation class GroupStudyListWordServlet
 */
@WebServlet(name = "GroupStudyListWord", urlPatterns = { "/groupStudyListWord" })
public class GroupStudyListWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupStudyListWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. view에서 넘겨준 값
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String word = request.getParameter("search-word");
		String inputs = request.getParameter("inputs");
		System.out.println("select값 : "+word);
		System.out.println("입력값 : "+inputs);
		//3. 비지니스 로직
		GroupStudyPageData gspdWord = new GroupStudyService().selectListInputs(reqPage, word, inputs);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/groupStudy/groupStudyListWord.jsp");
		if(gspdWord!=null) {
			request.setAttribute("list", gspdWord.getList());
			request.setAttribute("pageNavi", gspdWord.getPageNavi());
		}else {
			request.setAttribute("list", null);
			request.setAttribute("pageNavi", "");
		}
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