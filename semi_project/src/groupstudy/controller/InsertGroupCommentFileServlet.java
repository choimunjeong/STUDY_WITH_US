package groupstudy.controller;

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

import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class InsertGroupCommentFileServlet
 */
@WebServlet(name = "InsertGroupCommentFile", urlPatterns = { "/insertGroupCommentFile" })
public class InsertGroupCommentFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGroupCommentFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//첨부파일확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "파일업로드실패 enctype");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		
		String saveDirectory = getServletContext().getRealPath("/")+"/upload/groupImg";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		int groupNo = Integer.parseInt(mRequest.getParameter("groupNo"));
		String commentWriter = mRequest.getParameter("commentWriter");
		String commentTitle = mRequest.getParameter("commentTitle");
		String category1 = mRequest.getParameter("category1");
		String category2 = mRequest.getParameter("category2");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		
		
		int result = new GroupStudyService().insertGroupCommentFile(groupNo, commentWriter, commentTitle, filename, filename);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("msg", "첨부파일 업로드 완료");
		}else {
			request.setAttribute("msg", "첨부파일 업로드 실패");
		}
		request.setAttribute("loc", "/myPlanGroupDetail?groupNo="+groupNo+"&category1="+category1+"&category2="+category2);
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
