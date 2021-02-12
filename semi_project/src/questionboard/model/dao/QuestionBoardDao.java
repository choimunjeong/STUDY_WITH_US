package questionboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import questionboard.model.vo.QuestionBoard;

public class QuestionBoardDao {

	//전체 글 수
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) totalCount from question_board";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return totalCount;
	}

	//페이징에 해당하는 게시글 불러오기
	public ArrayList<QuestionBoard> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QuestionBoard> list = new ArrayList<QuestionBoard>();
		String query = "select * from (select rownum as rnum, n.* from(select * from question_board order by 1 desc ) n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QuestionBoard q = new QuestionBoard();
				q.setRnum(rset.getInt("rnum"));
				q.setAnswerStatus(rset.getString("ANSWER_STATUS"));
				q.setQuestionNo(rset.getInt("QUESTION_NO"));
				q.setQuestionPw(rset.getInt("QUESTION_PW"));
				q.setQuestionTitle(rset.getString("QUESTION_TITLE"));
				q.setQuestionWriteDate(rset.getString("QUESTION_WRITEDATE"));
				q.setQuestionWriterId(rset.getString("QUESTION_WRITER_ID"));
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	//사용자페이지 - 문의사항 1개 조회
	public QuestionBoard selectOneQuestion(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QuestionBoard q = null;
		String query = "select * from question_board where question_no = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				q = new QuestionBoard();
				q.setAnswerStatus(rset.getString("ANSWER_STATUS"));
				q.setQuestionNo(rset.getInt("QUESTION_NO"));
				q.setQuestionPw(rset.getInt("QUESTION_PW"));
				q.setQuestionTitle(rset.getString("QUESTION_TITLE"));
				q.setQuestionWriteDate(rset.getString("QUESTION_WRITEDATE"));
				q.setQuestionWriterId(rset.getString("QUESTION_WRITER_ID"));
				q.setQuestionContent(rset.getString("QUESTION_CONTENT"));
				q.setAnswerContent(rset.getString("ANSWER_CONTENT"));
				q.setAnswerDate(rset.getString("ANSWER_DATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return q;
	}

	//사용자페이지 - 문의사항 작성
	public int insertQuestion(Connection conn, QuestionBoard q) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into question_board values(QUESTION_BOARD_SEQ.NEXTVAL, ?,?, ?,  to_char(sysdate, 'yyyy-mm-dd'), 'n' , null, null, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, q.getQuestionTitle() );
			pstmt.setString(2, q.getQuestionContent());
			pstmt.setInt(3, q.getQuestionPw());
			pstmt.setString(4, q.getQuestionWriterId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//사용자페이지 - 문의사항 삭제
	public int deleteQuestion(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from question_board where question_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//관리자페이지 - 문의사항에 답변 등록
	public int insertManagerComment(Connection conn, int questionNo, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update question_board set ANSWER_STATUS='y', ANSWER_CONTENT=?, ANSWER_DATE=to_char(sysdate,'yyyy-mm-dd') where QUESTION_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment);
			System.out.println("comment : "+comment);
			pstmt.setInt(2, questionNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//관리자페이지 - 문의사항에 답변만 삭제
	public int deleteManagerComment(Connection conn, int questionNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update question_board set ANSWER_STATUS='n', ANSWER_CONTENT=null, ANSWER_DATE=null where QUESTION_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, questionNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}