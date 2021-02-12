package questionboard.model.vo;

import java.util.ArrayList;

public class QuestionBoardPage {
	private ArrayList<QuestionBoard> list;
	private String pageNavi;
	public QuestionBoardPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionBoardPage(ArrayList<QuestionBoard> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<QuestionBoard> getList() {
		return list;
	}
	public void setList(ArrayList<QuestionBoard> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}