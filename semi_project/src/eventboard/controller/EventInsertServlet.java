package eventboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import eventboard.model.service.EventBoardService;
import eventboard.model.vo.EventBoard;
import noticeboard.model.service.NoticeBoardService;
import noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class EventInsertServlet
 */
@WebServlet(name = "EventInsert", urlPatterns = { "/eventInsert" })
public class EventInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 넘겨준 값 저장
		//파일업로드 형식이 맞는지 검사(멀티파트 폼 데이타 형식이 맞는지 검사하는 것
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "이벤트 작성 오류[enctype]");
			request.setAttribute("loc",  "/");
			rd.forward(request, response);
			return;
		}
		//파일업로드 준비
		//1.파일업로드 경로설정
		String root = getServletContext().getRealPath("/");//webcontent 폴더까지 경로
		String saveDirectory = root+"upload/event";
		//2.파일 푀대크기 지정(cos 라이브러리 무료버전의 경우 최대 10mb 까지 가능)
		int maxSize = 10*1024*1024; //10메가바이트 10바이트,10키로바이트,10메가바이트
		//request -> MultipartRequest 객체로 변환하면서 파일이 업로드
		//매개변수가 5개 : 파일 저장 경로, 최대 사이즈, 인코딩 형식, 자동으로 파일명을 바꿔주는 객체(넘버링 됨) // 
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8" , new DefaultFileRenamePolicy());
		EventBoard e = new EventBoard();
		e.setEventContent(mRequest.getParameter("eventContent"));
		e.setEventEndDate(mRequest.getParameter("eventEndDate"));
		e.setEventTitle(mRequest.getParameter("eventTitle"));
		e.setFilename(mRequest.getOriginalFileName("filename"));
		e.setFilepath(mRequest.getFilesystemName("filename"));
		e.setEventLink(mRequest.getParameter("eventLink"));

		//3.비즈니스로직
		int result = new EventBoardService().insertEvent(e);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "이벤트 등록 성공");
		}else {
			request.setAttribute("msg", "이벤트 등록 실패");
		}
		request.setAttribute("loc", "/eventManagerList?reqPage=1");
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
