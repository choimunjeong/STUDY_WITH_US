package eventboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventboard.model.service.EventBoardService;

/**
 * Servlet implementation class DeleteOneEventServlet
 */
@WebServlet(name = "DeleteOneEvent", urlPatterns = { "/deleteOneEvent" })
public class DeleteOneEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOneEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));
		int result = new EventBoardService().deleteOneEvent(eventNo);
		//삭제할 첨부파일이 있는지 검사
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			String filepath = new EventBoardService().deleteFilepath(eventNo);
			if(filepath != "") {
				String root = getServletContext().getRealPath("/"); //경로를 먼저 만들기
				String saveDirectory = root+"upload/event/";
				File delFile = new File(saveDirectory+filepath);
				boolean delResult = delFile.delete();
				if(delResult) {
					System.out.println("파일 삭제 성공");
				}else {
					System.out.println("파일 삭제 실패");
				}
			}
			request.setAttribute("msg", "삭제완료");
			request.setAttribute("loc", "/eventManagerList?reqPage=1");
		}
		else {
			request.setAttribute("msg", "그룹 삭제 대실패");
			request.setAttribute("loc", "/eventManagerList?reqPage=1");
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
