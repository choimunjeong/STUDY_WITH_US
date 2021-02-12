package noticeboard.model.vo;

import java.util.ArrayList;

public class NoticeBoardPage {
	private ArrayList<NoticeBoard> list;
	private String pageNavi;
	public NoticeBoardPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeBoardPage(ArrayList<NoticeBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<NoticeBoard> getList() {
		return list;
	}
	public void setList(ArrayList<NoticeBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
