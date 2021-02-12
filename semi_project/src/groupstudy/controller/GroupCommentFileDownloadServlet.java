package groupstudy.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GroupCommentFileDownloadServlet
 */
@WebServlet(name = "GroupCommentFileDownload", urlPatterns = { "/groupCommentFileDownload" })
public class GroupCommentFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCommentFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String filename = request.getParameter("filename");
		String filepath = request.getParameter("filepath");
		
		String saveDirectory = getServletContext().getRealPath("/")+"/upload/groupImg/";
		
		FileInputStream fis = new FileInputStream(saveDirectory+filepath);//서블릿으로 가져옴
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		String resFilename = "";
		
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1;
		System.out.println(bool);
		if(bool) {//사용자의 브라우저가 IE인 경우
			resFilename = URLEncoder.encode(filename,"UTF-8");
			resFilename = resFilename.replace("\\\\", "%20");
		}else {// 그 외 브라우저인 경우
			resFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		response.setContentType("application/octet-stream");//파일받으면된다는 응답이 온것
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);//resFilename : 파일 다운로드받을때의 파일명
		//파일 전송
		int read = -1;
		while((read=bis.read())!=-1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}