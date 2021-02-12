package groupstudy.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import groupstudy.model.dao.GroupStudyDao;
import groupstudy.model.vo.Category;
import groupstudy.model.vo.GroupComment;
import groupstudy.model.vo.GroupCommentMemberFilePath;
import groupstudy.model.vo.GroupManagePage;
import groupstudy.model.vo.GroupStudyMember;
import groupstudy.model.vo.GroupStudyPageData;
import groupstudy.model.vo.GroupStudyRoom;
import groupstudy.model.vo.GroupStudyRoomAddCategory;
import member.model.vo.Member;

public class GroupStudyService {
	
	public GroupStudyPageData selectList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		GroupStudyDao dao = new GroupStudyDao();	//총 게시물 수를 구하는 DAO
		int totalCount = dao.totalCount(conn);		//한 페이지당 게시물 수
		int numPerPage = 12;					//한 페이지당 게시물 수
		int totalPage = 0;						//전체 페이지 수
		if(totalCount%numPerPage ==0) {			//나머지가 0인경우
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage+1;
		}
		System.out.println("totalcount : "+totalCount);
		//reqPage=1 -> start : 1, end = 10
		//reqPage=2 -> start : 11, end = 20
		//reqPage=3 -> start : 21, end = 30
		int start = ((reqPage-1)*numPerPage)+1; //해당 페이지 게시물의 시작번호
		int end = reqPage*numPerPage;
		System.out.println("시작번호 : "+start+" / 끝번호 : "+end);
		ArrayList<GroupStudyRoom> list = dao.selectList(conn,start,end);
		//페이지 네비게이션 작성
		int pageNaviSize = 5;					//페이지 네비게이션 길이 지정
		String pageNavi = "";					//페이지 네비 태그 작성용
		//페이지 시작번호 구하기 
		int pageNo = reqPage-2;
		//이전버튼 : 현재 페이지가 1일때만 이전버튼 X
		if(reqPage > 1) {
			pageNavi += "<a class='btn' href='/groupStudyList?reqPage="+(reqPage-1)+"'>이전</a>";
		}
		if(reqPage <= 3) {
			pageNo = 1;
		}
		//페이지 네비 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(reqPage == pageNo) { //페이지 네비가 현재 요청페이지인 경우(a태그가 필요없다. 새로고침을 원하는게 아니라면)
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}else {	//클릭한 페이지가 현재페이지와 다를 경우 페이지 이동시켜줘야하니까 span 말고 a태그를 써서 클릭한 숫자 페이지로 이동시킴
				if(pageNo == 0) {
					pageNavi += "<a class='btn' href='/groupStudyList?reqPage="+pageNo+"'>"+pageNo+"</a>";
				}else {					
					pageNavi += "<a class='btn' href='/groupStudyList?reqPage="+pageNo+"'>"+pageNo+"</a>";
				}
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음 버튼
		if(reqPage != totalPage) {
			pageNavi += "<a class'btn' href='/groupStudyList?reqPage="+(reqPage+1)+"'>다음</a>";
		}
		GroupStudyPageData gspd = new GroupStudyPageData(list, pageNavi);
		
		JDBCTemplate.close(conn);
		return gspd;
	}

	public ArrayList<Integer> selectGroupNo(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Integer> groupNoList = new GroupStudyDao().selectGroupNo(conn, memberNo);
		JDBCTemplate.close(conn);
		return groupNoList;
	}
	//groupList.jsp에 출력용 group이랑 category랑 짝이 맞아야해서 객체 새로 생성함
	public ArrayList<GroupStudyRoomAddCategory> selectGroupStudyOne(ArrayList<Integer> groupNoList) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<GroupStudyRoomAddCategory> gsrCateList = new ArrayList<GroupStudyRoomAddCategory>();
		//반복문으로 groupNoList의 각각의 값으로 GroupStudyRoom과 Category를 가져와서 한쌍으로 List에 저장
		for(Integer groupNo : groupNoList) {
			GroupStudyRoomAddCategory gsrAddc = new GroupStudyRoomAddCategory();
			gsrAddc.setGsr(new GroupStudyDao().selectGroupStudyOne(conn, groupNo));
			gsrAddc.setCategory(new GroupStudyDao().selectCategory(conn, gsrAddc.getGsr().getCategoryNo()));//categoryNo을 넘겨줌
			gsrCateList.add(gsrAddc);
		}
		JDBCTemplate.close(conn);
		return gsrCateList;
	}

	public GroupStudyRoom selectGroupStudyOne(int groupNo) {
		Connection conn = JDBCTemplate.getConnection();
		GroupStudyRoom gsr = new GroupStudyDao().selectGroupStudyOne(conn, groupNo);
		JDBCTemplate.close(conn);
		return gsr;
	}

	public Category selectCategory(int categoryNo) {
		Connection conn = JDBCTemplate.getConnection();
		Category category = new GroupStudyDao().selectCategory(conn, categoryNo);
		JDBCTemplate.close(conn);
		return category;
	}
	
	//groupNo으로 선택한그룹스터디에 참여중인 인원수 가져오기
	public int selectMemberNo(int groupNo) {
		Connection conn = JDBCTemplate.getConnection();
		int memberCnt = new GroupStudyDao().selectMemberNo(conn, groupNo);
		JDBCTemplate.close(conn);
		return memberCnt;
	}

	public int insertApplyGroupMember(int memberNo, int groupNo, String applyContent) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new GroupStudyDao().insertApplyGroupMember(conn,memberNo,groupNo,applyContent);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<String> categorySelAjax(String sel1) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> categoryList = new GroupStudyDao().categorySelAjax(conn,sel1);
		JDBCTemplate.close(conn);
		return categoryList;
	}

	public int createRoomCntCheck(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int roomCnt = new GroupStudyDao().createRoomCntCheck(conn,memberNo);
		JDBCTemplate.close(conn);
		return roomCnt;
	}

	public int selectCategoryNo(String category1, String category2) {
		Connection conn = JDBCTemplate.getConnection();
		int categoryNo = new GroupStudyDao().selectCategoryNo(conn,category1,category2);
		JDBCTemplate.close(conn);
		return categoryNo;
	}
	
	//groupStudyRoom, groupStudyMember(방장) 2개 한번에 insert
	public int insertGroupStudyRoom(GroupStudyRoom gsr) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new GroupStudyDao().insertGroupStudyRoom(conn,gsr);
		int result2 = 0;
		if(result>0) {
			//groupStudyMember 테이블에도 방장을 insert함(groupMangerNo을 가지고 제일 최근에 만들어진 groupNo으로 insert)
			int lastGroupNo = new GroupStudyDao().selectManagerGroupNo(conn, gsr.getGroupManagerNo());//최근 groupNo구하기
			result2 = new GroupStudyDao().insertGroupStudyMember(conn, gsr.getGroupManagerNo(), lastGroupNo);//방장도 groupStudyMember에 저장
			if(result2>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result2;
	}
	//(진선)스터디룸삭제
		public int deleteGroupStudyRoom(int groupNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().deleteGroupStudy(conn, groupNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//(진선)그룹스터디멤버 삭제
		public int deleteGroupStudyMember(int memberNo, int groupNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().deleteGroupStudyMember(conn, memberNo, groupNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//(진선)그룹스터디 수정
		public int updateGroupStudyRoom(GroupStudyRoom gsr) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().updateGroupStudyRoom(conn, gsr);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		//(진선)스터디 참여요청시 체크용(인원수 및 중복참여)
		public ArrayList<GroupStudyMember> selectGroupStudyMemberAll(int groupNo) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<GroupStudyMember> gsmList = new GroupStudyDao().selectGroupStudyMemberAll(conn, groupNo);
			JDBCTemplate.close(conn);
			return gsmList;
		}
		
	public int getMemberCnt(int groupNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int memberCnt = new GroupStudyDao().getMemberCnt(conn,groupNo);
		
		JDBCTemplate.close(conn);
		
		return memberCnt;
	}

	public int updateAndInsert(int groupNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		GroupStudyDao dao = new GroupStudyDao();
		
		int applyNo = dao.getApplyNo(conn,groupNo,memberNo);
		
		int result = dao.updateApplyStatus(conn,applyNo);
		
		if(result>0) {
			int result2 = dao.insertGroupStudyMember(conn, memberNo, groupNo);
			if(result2>0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
				result = 0;
			}
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	//관리자 페이지 - 그룹스터디 no으로 참여중인 멤버 수 찾기
		public HashMap<Integer, Integer> seleteAllGroupMemberCount() {
			Connection conn = JDBCTemplate.getConnection();
			HashMap<Integer, Integer> memberCount = new GroupStudyDao().seleteAllGroupMemberCount(conn);
			JDBCTemplate.close(conn);
			return memberCount;
		}

		//관리자 페이지 - 그룹스터디 삭제(이미지 있는 경우 이미지 경로만 받아온다)
		public ArrayList<String> deleteAllMember(String num) {
			Connection conn = JDBCTemplate.getConnection();
			System.out.println(num);
			StringTokenizer sT1 = new StringTokenizer(num, "/");
			ArrayList<String> imgList = new ArrayList<String>();
			boolean result = true;
			while(sT1.hasMoreTokens()) {
				int groupNo = Integer.parseInt(sT1.nextToken());
				String filePath = new GroupStudyDao().deleteFilepath(conn, groupNo);
				if(filePath != null) {
					imgList.add(filePath);
				}
				int result1 = new GroupStudyDao().deleteGroupStudy(conn, groupNo);
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
			if(result) {
				return imgList;
			}
			JDBCTemplate.close(conn);
			return null;
		}

		//관리자페이지 - 그룹스터디 페이징 조회
		public GroupManagePage seleteList(int reqPage) {
			Connection conn = JDBCTemplate.getConnection();
			
			//1. 전체 글 수 검색
			int totalCount = new GroupStudyDao().totalCount2(conn);

			//2. 총 페이지 수
			int numPerPage = 10;
			int totalPage = 0;
			if(totalCount/numPerPage == 0) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage + 1;
			}
			
			//3. 게시글 시작번호와 끝번호
			int start = (reqPage-1)*numPerPage + 1;
			int end = reqPage*numPerPage;
			
			//4. 페이지 범위에 있는 게시글 리스트를 불러옴 + 그룹스터디 각 인원수도 계산
			ArrayList<GroupStudyRoom> list = new GroupStudyDao().selectList2(conn, start, end);
			HashMap<Integer, Integer> memberCount = new GroupStudyDao().seleteAllGroupMemberCount(conn);
			
			//5. 페이지 네비게이션 생성
			int pageNaviSize = 5;
			String pageNavi = "";
			
			//6. 페이지 번호
			int pageNo = reqPage-2;
			if(reqPage <= 3) {
				pageNo = 1;
			}else if(pageNo > totalPage-4) {
				pageNo = totalPage-4;
			}
			
			//7. 이전버튼
			if(reqPage > 3) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/groupStudyListManager?reqPage="+(pageNo-1)+"'><<</a></li>";
			}
			
			//8. 네비게이션 숫자
			for(int i=0; i<pageNaviSize; i++) {
				if(reqPage==pageNo) {
					pageNavi += "<li class='page-item'><a class='page-link' href='#' style='background-color:#6ED078'>"+pageNo+"</a></li>";
					pageNavi += "<li class='reqPage' style='display:none'>"+reqPage+"</li>";
				}else {
					pageNavi += "<li class='page-item'><a class='page-link' href='/groupStudyListManager?reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
				}
				pageNo++;
				
				if(pageNo > totalPage) {
					break;
				}
			}
			
			//9. 다음버튼
			System.out.println(pageNo +": pageNo");
			System.out.println(reqPage +": reqPage");
			System.out.println(totalPage + ": totalPage");
			if(reqPage <= totalPage-3) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/groupStudyListManager?reqPage="+pageNo+"'>>></a></li>";
			}

			if(pageNo==2) {
				pageNavi="";
			}
			
			//10. 리스트+태그텍스트+멤버 카운트를 객체에 넣어줌
			GroupManagePage gmp = new GroupManagePage(list, pageNavi, memberCount);
			JDBCTemplate.close(conn);
			return gmp;
		}
		// 검색어로 검색하였을때!!!!!!!!!!!!	12/01
		public GroupStudyPageData selectListInputs(int reqPage, String word, String inputs) {
			Connection conn = JDBCTemplate.getConnection();
			GroupStudyDao dao = new GroupStudyDao(); // 총 게시물 수를 구하는 DAO
			int totalCount = dao.totalCount(conn, word, inputs); // 한 페이지당 게시물 수
			if(totalCount!=0) {
				int numPerPage = 12; // 한 페이지당 게시물 수
				int totalPage = 0; // 전체 페이지 수
				if (totalCount % numPerPage == 0) { // 나머지가 0인경우
					totalPage = totalCount / numPerPage;
				} else {
					totalPage = totalCount / numPerPage + 1;
				}
				System.out.println("검색어로검색totalcount : " + totalCount);
				// reqPage=1 -> start : 1, end = 10
				// reqPage=2 -> start : 11, end = 20
				// reqPage=3 -> start : 21, end = 30
				int start = ((reqPage - 1) * numPerPage) + 1; // 해당 페이지 게시물의 시작번호
				int end = reqPage * numPerPage;
				System.out.println("-----검색어로 검색-----\n"+"시작번호 : " + start + " / 끝번호 : " + end);
				ArrayList<GroupStudyRoom> list = dao.selectListInputs(conn, start, end, word, inputs);
				System.out.println(list.size());
				// 페이지 네비게이션 작성 시작
				int pageNaviSize = 5; // 페이지 네비게이션 길이 지정
				if(totalPage<5) {
					pageNaviSize = totalPage; // 페이지 네비게이션 길이 지정					
				}
				String pageNavi = ""; // 페이지 네비 태그 작성용(HTML코드)
				// 페이지 시작번호 구하기
				// reqPage : 1 ~ 5 -> 1, reqPage : 6 ~ 10 -> 6, reqPage : 11 ~ 15 -> 11
				int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
				// 이전버튼 : 페이지 시작번호가 1이 아닌경우에만 이전버튼 생성
				if (pageNo != 1) {
					pageNavi += "<a class='btn' href='/groupStudyListWord?search-word="+word+"&inputs="+inputs+"&reqPage=" + (pageNo - 1) + "'>이전</a>";
				}
				// 페이지 네비 숫자
				for (int i = 0; i < pageNaviSize; i++) {
					if (reqPage == pageNo) { // 페이지 네비가 현재 요청페이지인 경우(a태그 필요없음)
						pageNavi += "<span class='selectPage'>" + pageNo + "</span>";
					} else {
						pageNavi += "<a class='btn' href='/groupStudyListWord?search-word="+word+"&inputs="+inputs+"&reqPage=" + pageNo + "'>" + pageNo + "</a>";
					}
					pageNo++;
					if (pageNo > totalPage) {
						break;
					}
				}
				// 다음 버튼
				if (pageNo <= totalPage) {
					pageNavi += "<a class'btn' href='/groupStudyListWord?search-word="+word+"&inputs="+inputs+"&reqPage=" + pageNo + "'>다음</a>";
				}
				GroupStudyPageData gspdWord = new GroupStudyPageData(list, pageNavi);

				JDBCTemplate.close(conn);
				return gspdWord;
			}else {
				return null;
			}
		}
		//카테고리별 검색 기능 메소드	12/1
		public GroupStudyPageData selectListCategory(int reqPage, String category1, String category2) {
			Connection conn = JDBCTemplate.getConnection();
			GroupStudyDao dao = new GroupStudyDao(); // 총 게시물 수를 구하는 DAO
			int totalCount = dao.totalCountCategory(conn, category1, category2); // 한 페이지당 게시물 수
			if(totalCount!=0) {
				int numPerPage = 12; // 한 페이지당 게시물 수
				int totalPage = 0; // 전체 페이지 수
				if (totalCount % numPerPage == 0) { // 나머지가 0인경우
					totalPage = totalCount / numPerPage;
				} else {
					totalPage = totalCount / numPerPage + 1;
				}
				System.out.println("카테고리별totalcount : " + totalCount);
				// reqPage=1 -> start : 1, end = 10
				// reqPage=2 -> start : 11, end = 20
				// reqPage=3 -> start : 21, end = 30
				int start = ((reqPage - 1) * numPerPage) + 1; // 해당 페이지 게시물의 시작번호
				int end = reqPage * numPerPage;
				System.out.println("-----카테고리 검색-----\n"+"시작번호 : " + start + " / 끝번호 : " + end);
				ArrayList<GroupStudyRoom> list = dao.selectListCategory(conn, start, end, category1, category2);
				System.out.println(list.size());
				// 페이지 네비게이션 작성 시작
				int pageNaviSize = 5; // 페이지 네비게이션 길이 지정
				if(totalPage<5) {
					pageNaviSize = totalPage; // 페이지 네비게이션 길이 지정					
				}
				String pageNavi = ""; // 페이지 네비 태그 작성용(HTML코드)
				// 페이지 시작번호 구하기
				// reqPage : 1 ~ 5 -> 1, reqPage : 6 ~ 10 -> 6, reqPage : 11 ~ 15 -> 11
				int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
				// 이전버튼 : 페이지 시작번호가 1이 아닌경우에만 이전버튼 생성
				if (pageNo != 1) {
					pageNavi += "<a class='btn' href='/groupStudyListCategory?category1="+category1+"&category2="+category2+"&reqPage=" + (pageNo - 1) + "'>이전</a>";
				}
				// 페이지 네비 숫자
				for (int i = 0; i < pageNaviSize; i++) {
					if (reqPage == pageNo) { // 페이지 네비가 현재 요청페이지인 경우(a태그 필요없음)
						pageNavi += "<span class='selectPage'>" + pageNo + "</span>";
					} else {
						pageNavi += "<a class='btn' href='/groupStudyListCategory?category1="+category1+"&category2="+category2+"&reqPage=" + pageNo + "'>" + pageNo + "</a>";
					}
					pageNo++;
					if (pageNo > totalPage) {
						break;
					}
				}
				// 다음 버튼
				if (pageNo <= totalPage) {
					pageNavi += "<a class'btn' href='/groupStudyListCategory?category1="+category1+"&category2="+category2+"&reqPage=" + pageNo + "'>다음</a>";
				}
				GroupStudyPageData gspdCategory = new GroupStudyPageData(list, pageNavi);

				JDBCTemplate.close(conn);
				return gspdCategory;
			}else {	//DB에 정보가 존재하지 않을때
				return null;
			}
			
		}
		
		//(진선)참여중인스터디 > 상세보기 > 댓글전체 불러오기
		public GroupCommentMemberFilePath selectGroupCommentAll(int groupNo) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<GroupComment> gcList = new GroupStudyDao().selectGroupCommentAll(conn, groupNo);
			ArrayList<String> memberIdList = new GroupStudyDao().selectMemberIdDistinct(conn,groupNo);
			HashMap<String, String> memberIdFileMap = new HashMap<String, String>();
			for(String memberId : memberIdList) {//memberId와 filepath를 한번에 저장
				Member memberIdFile = new GroupStudyDao().selectMemberIdFilepath(conn,memberId);
				memberIdFileMap.put(memberIdFile.getMemberId(), memberIdFile.getFilepath());//memberId를 키로 filepath를 값으로 가져옴
			}
			GroupCommentMemberFilePath gcmf = new GroupCommentMemberFilePath(gcList, memberIdFileMap);
			JDBCTemplate.close(conn);
			return gcmf;
		}
		
		//(진선)댓글 수정하기
		public int updateGroupComment(int commentNo, String commentContent) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().updateGroupComment(conn, commentNo, commentContent);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//(진선)댓글 삭제하기
		public int deleteGroupComment(int commentNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().deleteGroupComment(conn, commentNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//(진선)댓글 작성하기(insert)
		public int insertGroupComment(int groupNo, String commentWriter, String commentContent) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().insertGroupComment(conn, groupNo, commentWriter, commentContent);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		//(진선)댓글 첨부파일 업로드(insert)
		public int insertGroupCommentFile(int groupNo, String commentWriter, String commentTitle, String filename, String filepath) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new GroupStudyDao().insertGroupCommentFile(conn, groupNo, commentWriter, commentTitle, filename, filepath);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}
		
		public ArrayList<Integer> selectApplyMemberAll(int groupNo) {
	         Connection conn = JDBCTemplate.getConnection();
	         ArrayList<Integer> gaMemberNoList = new GroupStudyDao().selectApplyMemberAll(conn,groupNo);
	         JDBCTemplate.close(conn);
	         return gaMemberNoList;
	      }
}
