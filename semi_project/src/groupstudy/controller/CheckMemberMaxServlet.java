package groupstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.dao.GroupStudyDao;
import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class CheckMemberMaxServlet
 */
@WebServlet(name = "CheckMemberMax", urlPatterns = { "/checkMemberMax" })
public class CheckMemberMaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMemberMaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		
		int memberCnt = new GroupStudyService().getMemberCnt(groupNo);
		
		PrintWriter out = response.getWriter();
		out.print(memberCnt);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
