package noticeboard.controller;

import java.io.File;
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

import noticeboard.model.service.NoticeBoardService;
import noticeboard.model.vo.NoticeBoard;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2. view로 넘어온 데이터 저장
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "공지사항 오류[enctype]");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		//파일저장경로
		String root = getServletContext().getRealPath("/");
		String saveDirectory =root+"upload/notice";
		//파일 최대 크기 지정
		
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		NoticeBoard n = new NoticeBoard();
		n.setNoticeNo(Integer.parseInt(mRequest.getParameter("noticeNo")));
		n.setNoticeTitle(mRequest.getParameter("noticeTitle"));
		n.setNoticeContent(mRequest.getParameter("noticeContent"));
		n.setFilename(mRequest.getOriginalFileName("filename")); //새로 파일이 들어왔을때 자동으로 들어갈 것
		n.setFilepath(mRequest.getFilesystemName("filename"));
		//기본파일 이름 및 경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//파일 삭제 확인용
		String status = mRequest.getParameter("status");
		//현재 첨부파일 확인
		File f = mRequest.getFile("filename");
		if(f!=null && f.length()>0) {  //새로운 첨부파일이 있는 경우
			if(status.equals("delete")) {  //기본첨부파일을 삭제한 경우->우리도 파일을 하나 삭제해줄것임
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = delFile.delete();
				System.out.println(bool?"삭제성공" : "삭제실패");
			}
		}else {//새로운 첨부파일이 없는 경우
			if(status.equals("delete")) { //기존첨부파일을 삭제한 경우
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = delFile.delete();
				System.out.println(bool?"삭제성공" : "삭제실패");
			}else if(status.equals("stay")) {
				n.setFilename(oldFilename);
				n.setFilepath(oldFilepath);
			}
		}
		//3.비즈니스 로직
		int result = new NoticeBoardService().updateNotice(n);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "공지사항 수정 완료");
		}else {
			request.setAttribute("msg", "공지사항 수정 실패");
		}
		request.setAttribute("loc", "/noticeManagerView?noticeNo="+n.getNoticeNo());
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
