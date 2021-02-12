package groupstudy.model.vo;

import java.util.ArrayList;
import java.util.HashMap;

import groupstudy.model.vo.GroupStudyRoom;

public class GroupManagePage {
	private ArrayList<GroupStudyRoom> list;
	private String pageNavi;
	private HashMap<Integer, Integer> memberCount;
	public GroupManagePage(ArrayList<GroupStudyRoom> list, String pageNavi, HashMap<Integer, Integer> memberCount) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
		this.memberCount = memberCount;
	}
	public GroupManagePage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<GroupStudyRoom> getList() {
		return list;
	}
	public void setList(ArrayList<GroupStudyRoom> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public HashMap<Integer, Integer> getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(HashMap<Integer, Integer> memberCount) {
		this.memberCount = memberCount;
	}
	
	
	
}
