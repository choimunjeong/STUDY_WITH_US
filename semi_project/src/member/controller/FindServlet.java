package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.service.MemberService;

/**
 * Servlet implementation class FindServlet
 */
@WebServlet(name = "Find", urlPatterns = { "/find" })
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberService service = new MemberService();
		String s_id;
		
		int selN = Integer.parseInt(request.getParameter("selN"));
		if(selN == 1) { //아이디 찾기 
			String searchName = request.getParameter("searchName");
			String searchEmail = request.getParameter("searchEmail");
			s_id = service.findMemberId(searchName,searchEmail);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			new Gson().toJson(s_id,response.getWriter());
			// ↓ 한번에 ajax로 여러개의 데이터를 반환해야할때 사용 
			// 받을땐 data["s_id"]; data["selN"]; 이런식으로 JSP에서 
//			String s = Integer.toString(selN);
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("selN", s);
//			map.put("s_id", s_id);
//			new Gson().toJson(map,response.getWriter());
			
		}
		else {
			int result = 0;
			String searchId = request.getParameter("searchId");
			String searchMail = request.getParameter("searchMail");
			result = service.findMemberPw(searchId,searchMail);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			if(result>0) {
				String b = "true";
				PrintWriter out = response.getWriter();
				out.print(b);
			}
			else {
				String b = "false";
				PrintWriter out = response.getWriter();
				out.print(b);
			}
		
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
