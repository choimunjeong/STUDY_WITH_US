package member.model.vo;

import java.util.ArrayList;

import groupstudy.model.vo.GroupList;


public class GroupPageData {
	private ArrayList<GroupList> list;
	private String pageNavi;
	public ArrayList<GroupList> getList() {
		return list;
	}
	public void setList(ArrayList<GroupList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	public GroupPageData(ArrayList<GroupList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public GroupPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
