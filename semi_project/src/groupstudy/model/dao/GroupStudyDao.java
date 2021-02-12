package groupstudy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import alarm.model.vo.Alarm;
import common.JDBCTemplate;
import groupstudy.model.vo.Category;
import groupstudy.model.vo.GroupApplyData;
import groupstudy.model.vo.GroupComment;
import groupstudy.model.vo.GroupList;
import groupstudy.model.vo.GroupStudyRoom;
import member.model.vo.Member;
import sun.security.action.GetIntegerAction;
import groupstudy.model.vo.GroupStudyMember;

public class GroupStudyDao {
	
	public ArrayList<GroupStudyRoom> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<GroupStudyRoom> list = new ArrayList<GroupStudyRoom>();
		String query = "select * from (select rownum as rnum, n.* from (select * from group_studyRoom order by 1 desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroupStudyRoom gsr = new GroupStudyRoom();
				gsr.setrNum(rset.getInt("rNum"));
				gsr.setGroupNo(rset.getInt("group_no"));
				gsr.setGroupTitle(rset.getString("group_title"));
				gsr.setGroupExplan(rset.getString("group_explan"));
				gsr.setCategoryNo(rset.getInt("category_no"));
				//이 메소드는 그룹스터디 리스트를 보여주는 것이므로 보여줄 데이터만 가져온다.
				list.add(gsr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) cnt from group_studyRoom";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
				//별칭으로 해둔 cnt의 값을 넘겨준다.(총 게시물 수)
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
	
	public ArrayList<Integer> selectGroupNo(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select group_no from GROUP_STUDYMEMBER where member_no=?";
		ArrayList<Integer> groupNoList = new ArrayList<Integer>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				groupNoList.add(rset.getInt("group_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return groupNoList;
	}

	public GroupStudyRoom selectGroupStudyOne(Connection conn, int groupNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from group_studyRoom where group_no=?";
		GroupStudyRoom gsr = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				gsr = new GroupStudyRoom();
				gsr.setGroupNo(rset.getInt("group_no"));
				gsr.setGroupTitle(rset.getString("group_title"));
				gsr.setGroupManagerNo(rset.getInt("group_manager_no"));
				gsr.setGroupRule(rset.getString("group_rule"));
				gsr.setGroupPersonnel(rset.getInt("group_personnel"));
				gsr.setGroupExplan(rset.getString("group_explan"));
				gsr.setGroupContent(rset.getString("group_content"));
				gsr.setGroupStartDate(rset.getString("group_startdate"));
				gsr.setGroupEndDate(rset.getString("group_enddate"));
				gsr.setCategoryNo(rset.getInt("category_no"));
				gsr.setFilename(rset.getString("filename"));
				gsr.setFilepath(rset.getString("filepath"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gsr;
	}

	public Category selectCategory(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from category where category_no=?";
		Category category = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				category = new Category();
				category.setCategoryNo(rset.getInt("category_no"));
				category.setCategory1(rset.getString("category1"));
				category.setCategory2(rset.getString("category2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return category;
	}

	//인원수 가져오는 메소드
	public int selectMemberNo(Connection conn, int groupNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select count(*) cnt from GROUP_STUDYMEMBER where group_no=?";
		int memberCnt = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				memberCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return memberCnt;
	}

	public int insertApplyGroupMember(Connection conn, int memberNo, int groupNo, String applyContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into group_apply values(group_apply_seq.nextval,?,?,?,'미승인')";//처음 요청한거니까 미승인으로 들어감
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, applyContent);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<String> categorySelAjax(Connection conn, String sel1) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> categoryList = new ArrayList<String>();
		String query = "select category2 from category where category1=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sel1);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				categoryList.add(rset.getString("category2"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return categoryList;
	}

	public int createRoomCntCheck(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int roomCnt = 0;
		String query = "select count(*) cnt from GROUP_STUDYROOM where group_manager_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				roomCnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return roomCnt;
	}

	public int selectCategoryNo(Connection conn, String category1, String category2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int categoryNo = 0;
		String query = "select category_no from category where category1=? and category2=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category1);
			pstmt.setString(2, category2);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				categoryNo = rset.getInt("category_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return categoryNo;
	}
	//그룹스터디 생성 insert
	public int insertGroupStudyRoom(Connection conn, GroupStudyRoom gsr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into GROUP_STUDYROOM values(GROUP_STUDYROOM_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gsr.getGroupTitle());
			pstmt.setInt(2, gsr.getGroupManagerNo());
			pstmt.setString(3, gsr.getGroupRule());
			pstmt.setInt(4, gsr.getGroupPersonnel());
			pstmt.setString(5, gsr.getGroupExplan());
			pstmt.setString(6, gsr.getGroupContent());
			pstmt.setString(7, gsr.getGroupStartDate());
			pstmt.setString(8, gsr.getGroupEndDate());
			pstmt.setInt(9, gsr.getCategoryNo());
			pstmt.setString(10, gsr.getFilename());
			pstmt.setString(11, gsr.getFilepath());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//방생성 시 방장의 가장최근 groupNo을 구함 이후 바로 groupStudyMember에 방장도 insert하기 위함
	public int selectManagerGroupNo(Connection conn, int groupManagerNo) {
		PreparedStatement pstmt = null;
		int lastGroupNo = 0;
		ResultSet rset = null;
		String query = "select max(group_no) as group_no from group_studyroom where group_manager_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupManagerNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				lastGroupNo = rset.getInt("group_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return lastGroupNo;
	}

	//최근 groupNo과 방장의No으로 groupStudyMember에 insert
	public int insertGroupStudyMember(Connection conn, int groupManagerNo, int lastGroupNo) {
		PreparedStatement pstmt = null;
		int result2 = 0;
		String query = "insert into GROUP_STUDYMEMBER values(GROUP_STUDYMEMBER_SEQ.NEXTVAL,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupManagerNo);
			pstmt.setInt(2, lastGroupNo);
			result2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result2;
	}


	// 기현 마이페이지 -> 그룹리스트 -> 페이징
	public int totalCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) cnt from GROUP_STUDYMEMBER g1,GROUP_STUDYROOM g2 where member_no =? and g1.GROUP_NO = g2.GROUP_NO";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				result = rset.getInt("cnt");
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

	public ArrayList<GroupList> selectList(Connection conn, int memberNo, int start, int end) {
		ArrayList<GroupList> list = new ArrayList<GroupList>();
		int i = 0;
		ArrayList<Integer> gn = new ArrayList<Integer>();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		GroupList g = null;
		String query = "select * from (select rownum as rnum, n.* from(select group_studymember.group_no,group_title,group_startdate,group_enddate,group_personnel,member_no from group_studyroom,group_studymember where group_studyroom.group_no = group_studymember.group_no and group_studymember.member_no=? order by 1 desc)n) where rnum between ? and ?";
		String query2 = "select * from (select rownum as rnum, n.* from(select group_no, count(*) membercount from group_studymember where group_no in (select group_no from group_studymember where member_no = ?) group by group_no order by group_no desc)n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt2 = conn.prepareStatement(query2);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			pstmt2.setInt(1, memberNo);
			pstmt2.setInt(2, start);
			pstmt2.setInt(3, end);
			rset = pstmt.executeQuery();
			rset2 = pstmt2.executeQuery();
			while(rset2.next()) {
				gn.add(rset2.getInt("membercount"));
			}
			while(rset.next())
			{
				g = new GroupList();
				g.setrNum(rset.getInt("rnum"));
				g.setGroupNo(rset.getInt("group_no"));
				g.setGroupTitle(rset.getString("group_title"));
				g.setGroupStartDate(rset.getString("group_startdate"));
				g.setGroupEndDate(rset.getString("group_enddate"));
				g.setGroupMax(rset.getInt("group_personnel"));
				g.setMemberNo(rset.getInt("member_no"));
				g.setMemberCnt(gn.get(i));
				list.add(g);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}

	public GroupApplyData getApplyInfo(Connection conn, int groupNum, int applyMemberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		GroupApplyData gad = new GroupApplyData();
		String query = "select * from group_studyroom g1,(select g1.apply_content,g1.group_no,m1.member_nickname from group_apply g1 join member m1 using(member_no) where member_no= ? ) g2 where g1.group_no = g2.group_no and g1.group_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, applyMemberNo);
			pstmt.setInt(2, groupNum);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				gad.setApplyContent(rset.getString("apply_content"));
				gad.setGroupMax(rset.getInt("group_personnel"));
				gad.setGroupNo(rset.getInt("group_no"));
				gad.setGroupTitle(rset.getString("group_title"));
				gad.setMemberNickname(rset.getString("member_nickname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gad;
		
	}

	public int getMemberCnt(Connection conn, int groupNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select count(*) cnt from group_studymember where group_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("cnt");
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

	public int getApplyNo(Connection conn, int groupNo, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "select apply_no from group_apply where group_no = ? and member_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, memberNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("apply_no");
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

	public int updateApplyStatus(Connection conn, int applyNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update group_apply set apply_status=? where apply_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "승인");
			pstmt.setInt(2, applyNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	//그룹스터디 삭제
	public int deleteGroupStudy(Connection conn, int groupNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from GROUP_STUDYROOM where GROUP_NO = ?";
		try {
		    pstmt = conn.prepareStatement(query);
		    pstmt.setInt(1, groupNo);
		    result = pstmt.executeUpdate();
		} catch (SQLException e) {
		    e.printStackTrace();
		}finally {
		    JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteGroupStudyMember(Connection conn, int memberNo, int groupNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from GROUP_STUDYMEMBER where MEMBER_NO = ? and GROUP_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, groupNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    JDBCTemplate.close(pstmt);
		}
		return result;
	}
	//updateGroupStudyRoom 파일패스도 수정해야해서 다시작업필요
	public int updateGroupStudyRoom(Connection conn, GroupStudyRoom gsr) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update GROUP_STUDYROOM set group_content=?, group_enddate=?, group_explan=?, group_personnel=?, group_rule=?, group_title=?, filename=?, filepath=? where group_no=? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gsr.getGroupContent());
			pstmt.setString(2, gsr.getGroupEndDate());
			pstmt.setString(3, gsr.getGroupExplan());
			pstmt.setInt(4, gsr.getGroupPersonnel());
			pstmt.setString(5, gsr.getGroupRule());
			pstmt.setString(6, gsr.getGroupTitle());
			pstmt.setString(7, gsr.getFilename());
			pstmt.setString(8, gsr.getFilepath());
			pstmt.setInt(9, gsr.getGroupNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//(진선)스터디 참여요청시 체크용(인원수 및 중복참여)
	//groupStudymember전체 조회
	public ArrayList<GroupStudyMember> selectGroupStudyMemberAll(Connection conn, int groupNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<GroupStudyMember> gsmList = new ArrayList<GroupStudyMember>();
		String query = "select * from GROUP_STUDYMEMBER where group_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				GroupStudyMember gsm = new GroupStudyMember();
				gsm.setGroupMemberNo(rset.getInt("group_member_no"));
				gsm.setGroupNo(rset.getInt("group_no"));
				gsm.setMemberNo(rset.getInt("member_no"));
				gsmList.add(gsm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    JDBCTemplate.close(pstmt);
		    JDBCTemplate.close(rset);
		}
		return gsmList;
	}
	//관리자 페이지 - 각 그룹스터디 안에 몇 명있는지 계산
		public HashMap<Integer, Integer> seleteAllGroupMemberCount(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			HashMap<Integer, Integer> memberCount = new HashMap<Integer, Integer>();
			String query = "select GROUP_NO, count(*)+1 memberCount from GROUP_STUDYMEMBER group by GROUP_NO";
			try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					memberCount.put(rset.getInt("GROUP_NO"), rset.getInt("memberCount"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return memberCount;
		}


		//관리자 페이지 - 그룹스터디 총 수
		public int totalCount2(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int totalCount = 0;
			String query = "select count(*) totalCount from GROUP_STUDYROOM";
			try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					totalCount = rset.getInt("totalCount");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			return totalCount;
		}

		//관리자 페이지 - 그룹스터디 페이징 
		public ArrayList<GroupStudyRoom> selectList2(Connection conn, int start, int end) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<GroupStudyRoom> list = new ArrayList<GroupStudyRoom>();
			String query = "select * from (select rownum as rnum, n.* from(select * from group_studyroom order by 1 desc) n) where rnum between ? and ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,  start);
				pstmt.setInt(2, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroupStudyRoom gsr = new GroupStudyRoom();
					gsr.setGroupNo(rset.getInt("group_no"));
					gsr.setGroupManagerNo(rset.getInt("GROUP_MANAGER_NO"));
					gsr.setGroupTitle(rset.getString("GROUP_TITle"));
					gsr.setGroupStartDate(rset.getString("GROUP_STARTDATE"));
					gsr.setGroupEndDate(rset.getString("GROUP_ENDDATE"));
					gsr.setGroupPersonnel(rset.getInt("GROUP_PERSONNEL"));
					list.add(gsr);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return list;
		}

		//관리자페이지 - 그룹스터디 삭제 할 때 그룹스터디 이미지 한번에 받기위함
		public String deleteFilepath(Connection conn, int groupNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String filepath = "";
			String query = "select filepath from group_studyroom where group_no = ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
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

		public int getManagerNo(Connection conn, int groupNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = "select group_manager_no from group_studyroom where group_no = ?";
			int result = 0;
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt("group_manager_no");
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
		
		//제목 내용으로 검색한 수 구하는 메소드 --------11/30일
		public int totalCount(Connection conn, String word, String inputs) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			String query = null;
			if(word.equals("group_title")) {
				query = "select count(*) cnt from group_studyRoom where group_title LIKE ?";
			}else {
				query = "select count(*) cnt from group_studyRoom where group_content LIKE ?";
			}
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+inputs+"%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt("cnt");
					//별칭으로 해둔 cnt의 값을 넘겨준다.(총 게시물 수)
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
		public int totalCountCategory(Connection conn, String category1, String category2) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int result = 0;
			String query = "select count(*) cnt from (select * from group_studyRoom join category using (category_no)) where category1 = ? and category2 = ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category1);
				pstmt.setString(2, category2);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getInt("cnt");
					//별칭으로 해둔 cnt의 값을 넘겨준다.(총 게시물 수)
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
		//제목, 내용으로 검색해서 해당 그룹스터디 목록 가져오기	12/1
		public ArrayList<GroupStudyRoom> selectListInputs(Connection conn, int start, int end, String word, String inputs) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = null;
			ArrayList<GroupStudyRoom> list = new ArrayList<GroupStudyRoom>();
			if(word.equals("group_title")) {
				query = "select * from (select rownum as rnum, n.* from (select * from group_studyRoom where group_title LIKE ? order by 1 desc)n) where rnum between ? and ?";			
			}else {
				query = "select * from (select rownum as rnum, n.* from (select * from group_studyRoom where group_content LIKE ? order by 1 desc)n) where rnum between ? and ?";
			}
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+inputs+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroupStudyRoom gsr = new GroupStudyRoom();
					gsr.setrNum(rset.getInt("rNum"));
					gsr.setGroupNo(rset.getInt("group_no"));
					gsr.setGroupTitle(rset.getString("group_title"));
					gsr.setGroupExplan(rset.getString("group_explan"));
					gsr.setCategoryNo(rset.getInt("category_no"));
					gsr.setFilename(rset.getString("filename"));
					gsr.setFilepath(rset.getString("filepath"));
					//이 메소드는 그룹스터디 리스트를 보여주는 것이므로 보여줄 데이터만 가져온다.
					list.add(gsr);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return list;
		}
		//카테고리별로 검색해서 해당 그룹스터디 목록 가져오기	12/1
		public ArrayList<GroupStudyRoom> selectListCategory(Connection conn, int start, int end, String category1, String category2) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<GroupStudyRoom> list = new ArrayList<GroupStudyRoom>();
			String query = "select * from (select rownum as rnum, n.* from (select * from group_studyRoom join category using (category_no) where category1 = ? and category2 = ? order by group_startdate desc)n) where rnum between ? and ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category1);
				pstmt.setString(2, category2);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroupStudyRoom gsr = new GroupStudyRoom();
					gsr.setrNum(rset.getInt("rNum"));
					gsr.setGroupNo(rset.getInt("group_no"));
					gsr.setGroupTitle(rset.getString("group_title"));
					gsr.setGroupExplan(rset.getString("group_explan"));
					gsr.setCategoryNo(rset.getInt("category_no"));
					gsr.setFilename(rset.getString("filename"));
					gsr.setFilepath(rset.getString("filepath"));
					//이 메소드는 그룹스터디 리스트를 보여주는 것이므로 보여줄 데이터만 가져온다.
					list.add(gsr);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return list;
		}
		//(진선)참여중인스터디 > 상세보기 > 댓글전체 불러오기
		public ArrayList<GroupComment> selectGroupCommentAll(Connection conn, int groupNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<GroupComment> gcList = new ArrayList<GroupComment>();
			String query = "select rownum as rnum , g.* from (select * from GROUP_COMMENT where group_no=? order by comment_no desc) g";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					GroupComment gc = new GroupComment();
					gc.setrNum(rset.getInt("rnum"));
					gc.setCommentNo(rset.getInt("comment_no"));
					gc.setGroupNo(rset.getInt("group_no"));
					gc.setCommentContent(rset.getString("comment_content"));
					gc.setFilename(rset.getString("filename"));
					gc.setFilepath(rset.getString("filepath"));
					gc.setCommentWriter(rset.getString("comment_writer"));
					gc.setCommentTitle(rset.getString("comment_title"));
					gcList.add(gc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    JDBCTemplate.close(pstmt);
			    JDBCTemplate.close(rset);
			}
			return gcList;
		}
		
		//(진선)댓글 수정하기
		public int updateGroupComment(Connection conn, int commentNo, String commentContent) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "update GROUP_COMMENT set comment_content=? where comment_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, commentContent);
				pstmt.setInt(2, commentNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//(진선)댓글 삭제하기
		public int deleteGroupComment(Connection conn, int commentNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "delete from GROUP_COMMENT where comment_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, commentNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    JDBCTemplate.close(pstmt);
			}
			return result;
		}
		
		//(진선)댓글 작성하기(insert)
		public int insertGroupComment(Connection conn, int groupNo, String commentWriter, String commentContent) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into GROUP_COMMENT values(GROUP_COMMENT_SEQ.NEXTVAL,?,?,null,null,?,null)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
				pstmt.setString(2, commentContent);
				pstmt.setString(3, commentWriter);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    JDBCTemplate.close(pstmt);
			}
			return result;
		}

		//(진선)댓글 첨부파일 업로드(insert)
		public int insertGroupCommentFile(Connection conn, int groupNo, String commentWriter, String commentTitle, String filename, String filepath) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into GROUP_COMMENT values(GROUP_COMMENT_SEQ.NEXTVAL,?,null,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
				pstmt.setString(2, filename);
				pstmt.setString(3, filepath);
				pstmt.setString(4, commentWriter);
				pstmt.setString(5, commentTitle);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    JDBCTemplate.close(pstmt);
			}
			return result;
		}
		//(진선)그룹스터디별로 코멘트 작성한 사용자의 id구하기(중복없이)
		public ArrayList<String> selectMemberIdDistinct(Connection conn, int groupNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<String> memberIdList = new ArrayList<String>();
			String query = "select distinct comment_writer from group_comment where group_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, groupNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					String memberId = null;
					memberId = rset.getString("comment_writer");
					memberIdList.add(memberId);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
			    JDBCTemplate.close(pstmt);
			}
			return memberIdList;
		}

		//(진선)selectMemberIdDistinct에서 구한 memberId로 한쌍으로 filepath을 구함
		public Member selectMemberIdFilepath(Connection conn, String memberId) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Member memberIdFile = new Member();
			String query = "select member_id, filepath from member where member_id=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					memberIdFile.setMemberId(rset.getString("member_id"));
					memberIdFile.setFilepath(rset.getString("filepath"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
			    JDBCTemplate.close(pstmt);
			}
			return memberIdFile;
		}
		//(진선)참여요청이력이 있는지 확인하기 위해 groupApply 테이블 조회
	      public ArrayList<Integer> selectApplyMemberAll(Connection conn, int groupNo) {
	         PreparedStatement pstmt = null;
	         ResultSet rset = null;
	         ArrayList<Integer> gaMemberNoList = new ArrayList<Integer>();
	         String query = "select member_no from group_apply where group_no=?";//참여요청한 memberNo만 가져옴
	         try {
	            pstmt = conn.prepareStatement(query);
	            pstmt.setInt(1, groupNo);
	            rset = pstmt.executeQuery();
	            while(rset.next()) {
	               int ga;
	               ga = (rset.getInt("member_no"));
	               gaMemberNoList.add(ga);
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }finally {
	            JDBCTemplate.close(rset);
	             JDBCTemplate.close(pstmt);
	         }
	         return gaMemberNoList;
	      }

}
