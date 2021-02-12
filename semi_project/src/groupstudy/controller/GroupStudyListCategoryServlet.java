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
 * Servlet implementation class GroupStudyListCategoryServlet
 */
@WebServlet(name = "GroupStudyListCategory", urlPatterns = { "/groupStudyListCategory" })
public class GroupStudyListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupStudyListCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. view에서 넘겨준 데이터 저장
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		//3. 비지니스 로직
		GroupStudyPageData gspdCategory = new GroupStudyService().selectListCategory(reqPage, category1, category2);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/groupStudy/groupStudyListCategory.jsp");
		request.setAttribute("category1", category1);
		request.setAttribute("category2", category2);
		if(gspdCategory!=null) {
			request.setAttribute("list", gspdCategory.getList());
			request.setAttribute("pageNavi", gspdCategory.getPageNavi());		
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