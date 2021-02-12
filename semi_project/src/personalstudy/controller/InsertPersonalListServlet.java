package personalstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.dao.PersonalStudyDao;
import personalstudy.model.service.PersonalStudyService;

/**
 * Servlet implementation class InsertPersonalListServlet
 */
@WebServlet(name = "InsertPersonalList", urlPatterns = { "/insertPersonalList" })
public class InsertPersonalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPersonalListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String color = request.getParameter("color");
		String listTitle = request.getParameter("list_title");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int result = new PersonalStudyService().insertList(color,listTitle,memberNo);
		response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out1 = response.getWriter();
		String str="";
		
		if(result>0) {
			str = "<script>";
			str += "opener.location.reload();";
			str += "self.close();";
			str += "</script>";		
			System.out.println(str);
		}
		else {
			str = "<script>";
			str += "opener.location.reload();";
			str += "self.close();";
			str += "</script>";
		}
		
		 out1.println(str);
		 out1.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
