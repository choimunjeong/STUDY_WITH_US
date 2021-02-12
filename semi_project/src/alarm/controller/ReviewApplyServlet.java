package alarm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import alarm.model.service.AlarmService;
import groupstudy.model.vo.GroupApplyData;

/**
 * Servlet implementation class ReviewApplyServlet
 */
@WebServlet(name = "ReviewApply", urlPatterns = { "/reviewApply" })
public class ReviewApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewApplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int alNo = Integer.parseInt(request.getParameter("alarmNum"));
		
		GroupApplyData gad = new AlarmService().getApplyInform(alNo); 
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		new Gson().toJson(gad,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
