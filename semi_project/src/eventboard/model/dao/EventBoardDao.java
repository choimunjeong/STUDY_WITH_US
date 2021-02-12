package eventboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import eventboard.model.vo.EventBoard;
import noticeboard.model.vo.NoticeBoard;

public class EventBoardDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) totalCount from event_board";
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

	public ArrayList<EventBoard> selectList(int start, int end, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<EventBoard> list = new ArrayList<EventBoard>();
		String query = "select * from (select rownum as rnum, n.* from(select * from event_board order by 1 desc ) n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				EventBoard e = new EventBoard();
				e.setEventEndDate(rset.getString("EVENT_ENDDATE"));
				e.setEventEnrollDate(rset.getString("EVENT_ENROLLDATE"));
				e.setEventNo(rset.getInt("EVENT_NO"));
				e.setEventTitle(rset.getString("EVENT_TITLE"));
				e.setFilepath(rset.getString("filepath"));
				e.setRnum(rset.getInt("rnum"));
				list.add(e);
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

	//----------------------------------------------------------------------------기현아 여기 쿼리문 수정했어----------------------(아래)
	public int insertEvent(Connection conn, EventBoard eb) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into event_board values(event_BOARD_SEQ.NEXTVAL, ?,to_char(sysdate, 'yyyy-mm-dd'), ?, ?, ? , ?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, eb.getEventTitle());
			pstmt.setString(2,  eb.getEventEndDate());
			pstmt.setString(3, eb.getEventContent());
			pstmt.setString(4, eb.getFilename());
			pstmt.setString(5, eb.getFilepath());
			pstmt.setString(6, eb.getEventLink());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public EventBoard selectOneEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		EventBoard eb = null;
		String query = "select * from event_board where event_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				eb = new EventBoard();
				eb.setEventContent(rset.getString("EVENT_CONTENT"));
				eb.setEventEndDate(rset.getString("EVENT_ENDDATE"));
				eb.setEventEnrollDate(rset.getString("EVENT_ENROLLDATE"));
				eb.setEventLink(rset.getString("EVENT_LINK"));
				eb.setEventNo(rset.getInt("EVENT_NO"));
				eb.setEventTitle(rset.getString("EVENT_TITLE"));
				eb.setFilename(rset.getString("FILENAME"));
				eb.setFilepath(rset.getString("FILEPATH"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return eb;
	}

	public String deletefilepath(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String filepath = "";
		String query = "select filepath from event_board where event_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				filepath = rset.getString("filepath");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return filepath;
	}

	public int deleteEvent(Connection conn, int eventNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from event_board where event_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, eventNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}