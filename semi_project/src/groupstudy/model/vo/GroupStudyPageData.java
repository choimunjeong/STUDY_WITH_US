package groupstudy.model.vo;

import java.util.ArrayList;

public class GroupStudyPageData {
	private ArrayList<GroupStudyRoom> list;
	private String pageNavi;
	public GroupStudyPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupStudyPageData(ArrayList<GroupStudyRoom> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
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
}
