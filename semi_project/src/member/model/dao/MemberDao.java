package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import groupstudy.model.vo.GroupList;
import member.model.vo.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberEmail());
			pstmt.setString(5, m.getMemberNickname());
			pstmt.setString(6, m.getFilename());
			pstmt.setString(7, m.getFilepath());
			pstmt.setString(8, null);
			pstmt.setInt(9,2);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Boolean selectOneMember(Connection conn, String memberId) {
		// TODO Auto-generated method stub
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String query = "select * from member where member_id = ?";
		Boolean b = true;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				b = false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public Member selectOneMember(Connection conn, Member m) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ? and member_pw = ?";
		Member loginMember = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginMember = new Member();
				loginMember.setMemberId(rset.getString("member_id"));
				loginMember.setMemberPw(rset.getString("member_pw"));
				loginMember.setMemberNo(rset.getInt("member_no"));
				loginMember.setMemberName(rset.getString("member_name"));
				loginMember.setMemberEmail(rset.getString("member_email"));
				loginMember.setMemberNickname(rset.getString("member_nickname"));
				loginMember.setFilename(rset.getString("filename"));
				loginMember.setFilepath(rset.getString("filepath"));
				loginMember.setMemberEnrollSNS(rset.getString("member_enrollsns"));
				loginMember.setMemberGrade(rset.getInt("member_grade"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return loginMember;
	}

	public String findMemberId(Connection conn, String searchName, String searchEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String s_id = null;
		String query = "select member_id from member where member_name = ? and member_email = ?";
		Member loginMember = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchName);
			pstmt.setString(2, searchEmail);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				s_id = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return s_id;
	}

	public int findMemberPw(Connection conn, String searchId, String searchMail, String n_pw) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw = ? where member_id = ? and member_email = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n_pw);
			pstmt.setString(2, searchId);
			pstmt.setString(3, searchMail);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member selectOneMember(Connection conn, int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_no = ?";
		Member m = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setFilename(rset.getString("filename"));
				m.setFilepath(rset.getString("filepath"));
				m.setMemberEnrollSNS(rset.getString("member_enrollsns"));
				m.setMemberGrade(rset.getInt("member_grade"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public ArrayList<GroupList> searchMyStudy(Connection conn, int memberNo) {
		
		PreparedStatement pstmt = null;
		PreparedStatement pst = null;
		ResultSet rset2 = null;
		ResultSet rset = null;
		String query2 = "select group_no, count(*) membercount from group_studymember where group_no in (select group_no from group_studymember where member_no = ?) group by group_no order by group_no asc";
		String query = "select group_studymember.group_no,group_title,group_startdate,group_enddate,group_personnel,member_no from group_studyroom,group_studymember where group_studyroom.group_no = group_studymember.group_no and group_studymember.member_no=?";
		GroupList g = null;
		int i = 0;
		ArrayList<Integer> gn = new ArrayList<Integer>();
		ArrayList<GroupList> gl = new ArrayList<GroupList>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			pst = conn.prepareStatement(query2);
			pst.setInt(1, memberNo);
			rset2 = pst.executeQuery();
			while(rset2.next())
			{
				gn.add(rset2.getInt("membercount"));
			}
			
			while(rset.next()) {
				g = new GroupList();
				g.setGroupNo(rset.getInt("group_no"));
				g.setGroupTitle(rset.getString("group_title"));
				g.setGroupStartDate(rset.getString("group_startdate"));
				g.setGroupEndDate(rset.getString("group_enddate"));
				g.setGroupMax(rset.getInt("group_personnel"));
				g.setMemberNo(rset.getInt("member_no"));
				g.setMemberCnt(gn.get(i));
				gl.add(g);
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gl;
	}

	public int updateMember(Connection conn, Member updateMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_pw = ?, member_nickname = ?, filename = ?, filepath = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, updateMember.getMemberPw());
			pstmt.setString(2, updateMember.getMemberNickname());
			pstmt.setString(3, updateMember.getFilename());
			pstmt.setString(4, updateMember.getFilepath());
			pstmt.setString(5, updateMember.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int selectOneMember(Connection conn, int groupNum, int alNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select g1.member_no send_member_no from alarm a1 join group_apply g1 using(group_no) where g1.member_no = a1.send_Member_No and group_no = ? and alarm_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNum);
			pstmt.setInt(2, alNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("send_member_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member searchSendMember(Connection conn, int sendMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = new Member();
		String query = "select member_name, FILEPATH from member where member_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sendMemberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m.setFilepath(rset.getString("filepath"));
				m.setMemberName(rset.getString("member_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	//관리자 페이지 - 사용자 삭제
	public int deleteUser(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member where member_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//관리자 페이지 - 사용자 레벨 변경
	public int changeLevel(Connection conn, int memberLevel, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set member_grade=? where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  memberLevel);
			pstmt.setInt(2, memberNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//관리자 페이지 - 총 사용자 수
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rset = null;
		String query = "select count(*) as total from member order by 1 desc";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	//관리자 페이지 - 사용자 페이징 조회
	public ArrayList<Member> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from (select rownum as rnum, n.* from(select * from member order by 1 desc) n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getNString("member_pw"));
				m.setMemberName(rset.getNString("member_name"));
				m.setMemberEmail(rset.getNString("member_email"));
				m.setMemberNickname(rset.getNString("member_nickname"));
				m.setMemberGrade(rset.getInt("member_grade"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int searchSnsMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = -1;
		String query="select member_no from member where member_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = 1;
			}
			else {
				result = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertSnsMember(Connection conn, String id, String name, String image) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,'Cvas2354Dgf','snsUser','snsUser@kakao.com',?,?,?,o,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2,name);
			pstmt.setString(3,image);
			pstmt.setString(4, image);
			pstmt.setInt(5, 2);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member selectSnsMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_id = ?";
		Member m = new Member();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m.setFilename(rset.getString("filename"));
				m.setFilepath(rset.getString("filepath"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberGrade(rset.getInt("member_grade"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setMemberNo(rset.getInt("member_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int naverLogin(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,'o',?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, "NAVERLOGIN154322");
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberEmail());
			pstmt.setString(5, m.getMemberNickname());
			pstmt.setString(6, m.getFilename());
			pstmt.setString(7, m.getFilepath());
			pstmt.setInt(8, 2);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}

