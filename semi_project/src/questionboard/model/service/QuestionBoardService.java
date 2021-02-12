package questionboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import noticeboard.model.dao.NoticeBoardDao;
import noticeboard.model.vo.NoticeBoardPage;
import questionboard.model.dao.QuestionBoardDao;
import questionboard.model.vo.QuestionBoard;
import questionboard.model.vo.QuestionBoardPage;

public class QuestionBoardService {

	//사용자페이지 - 문의사항 전체 리스트
	public QuestionBoardPage selectList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//전체 글 수 검색
		int totalCount = new QuestionBoardDao().totalCount(conn);
		
		//총 페이지 수
		int numPerPage = 10;
		int totalPage = 0;
		if(totalCount%numPerPage != 0) {
			totalPage = totalCount/numPerPage + 1;
		}else {
			totalPage = totalCount/numPerPage;
		}
		
		//게시글 시작, 끝번호
		int start = (reqPage-1)*numPerPage + 1;
		int end = reqPage*numPerPage;
		
		//페이지 범위에 있는 게시글 불러옴
		ArrayList<QuestionBoard> list = new QuestionBoardDao().selectList(conn, start, end);
		
		//페이지 네비 생성
		int pageNaviSize = 5;
		String pageNavi = "";
		
		//페이지 번호
		int pageNo = reqPage-1;
		if(reqPage <= 3) {
			pageNo = 1;
		}else if(pageNo > totalPage-4) {
			pageNo = totalPage-1;
		}

		//7. 이전버튼
		if(reqPage > 3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/questionList?reqPage="+(pageNo-1)+"'><<</a></li>";
		}
		
		//8. 네이게이션 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link' href='#' style='background-color:#6ED078'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/questionList?reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}

		//9. 다음버튼
		if(reqPage <= totalPage-3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/questionList?reqPage="+pageNo+"'>>></a></li>";
		}
		
		if(pageNo==2) {
			pageNavi="";
		}

		//10. 리스트+태그텍스트+멤버 카운트를 객체에 넣어줌
		QuestionBoardPage qbp = new QuestionBoardPage(list, pageNavi);
		JDBCTemplate.close(conn);
		return qbp;
	}

	//사용자페이지 - 문의사항 1개 조회
	public QuestionBoard selectOneQuestion(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		QuestionBoard q = new QuestionBoardDao().selectOneQuestion(conn, questionNo);
		JDBCTemplate.close(conn);
		return q;
	}

	//사용자페이지 - 문의사항 작성
	public int insertQuestion(QuestionBoard q) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QuestionBoardDao().insertQuestion(conn, q);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//사용자페이지 - 문의사항 삭제
	public int deleteOneQuestion(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QuestionBoardDao().deleteQuestion(conn, questionNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//관리자페이지 - 문의사항에 답변 등록
	public int insertManagerComment(int questionNo, String comment) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QuestionBoardDao().insertManagerComment(conn, questionNo, comment);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	//관리자페이지 - 문의사항에 답변만 삭제
	public int deleteComment(int questionNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QuestionBoardDao().deleteManagerComment(conn, questionNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	//관리자페이지 - 리스트에서 선택한 문희사항 삭제
	public boolean deleteAllQuestion(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer sT1 = new StringTokenizer(num, "/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int questionNo = Integer.parseInt(sT1.nextToken());
			int result1 = new QuestionBoardDao().deleteQuestion(conn, questionNo);
			if(result1==0) {
				result = false;
				break;
			}
			if(result) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		}
		JDBCTemplate.close(conn);
		return result;
	}

}