package groupstudy.model.vo;

public class Category {
	private int categoryNo;
	private String category1;
	private String category2;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int categoryNo, String category1, String category2) {
		super();
		this.categoryNo = categoryNo;
		this.category1 = category1;
		this.category2 = category2;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	
}
