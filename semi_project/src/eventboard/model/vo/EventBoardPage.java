package eventboard.model.vo;

import java.util.ArrayList;

public class EventBoardPage {
	private ArrayList<EventBoard> list;
	private String pageNavi;
	public EventBoardPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventBoardPage(ArrayList<EventBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<EventBoard> getList() {
		return list;
	}
	public void setList(ArrayList<EventBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
