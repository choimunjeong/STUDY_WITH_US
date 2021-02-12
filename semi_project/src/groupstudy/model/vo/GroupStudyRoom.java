package groupstudy.model.vo;

public class GroupStudyRoom {
	private int rNum;
	private int groupNo;
	private String groupExplan;
	private String groupRule;
	private int groupPersonnel;
	private String groupContent;
	private String groupStartDate;
	private String groupEndDate;
	private int groupManagerNo;
	private String groupTitle;
	private int categoryNo;
	private String filename;
	private String filepath;
	
	public GroupStudyRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GroupStudyRoom(int rNum, int groupNo, String groupExplan, String groupRule, int groupPersonnel,
			String groupContent, String groupStartDate, String groupEndDate, int groupManagerNo, String groupTitle,
			int categoryNo, String filename, String filepath) {
		super();
		this.rNum = rNum;
		this.groupNo = groupNo;
		this.groupExplan = groupExplan;
		this.groupRule = groupRule;
		this.groupPersonnel = groupPersonnel;
		this.groupContent = groupContent;
		this.groupStartDate = groupStartDate;
		this.groupEndDate = groupEndDate;
		this.groupManagerNo = groupManagerNo;
		this.groupTitle = groupTitle;
		this.categoryNo = categoryNo;
		this.filename = filename;
		this.filepath = filepath;
	}

	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupExplan() {
		return groupExplan;
	}
	public void setGroupExplan(String groupExplan) {
		this.groupExplan = groupExplan;
	}
	public String getGroupRule() {
		return groupRule;
	}
	public void setGroupRule(String groupRule) {
		this.groupRule = groupRule;
	}
	public int getGroupPersonnel() {
		return groupPersonnel;
	}
	public void setGroupPersonnel(int groupPersonnel) {
		this.groupPersonnel = groupPersonnel;
	}
	public String getGroupContent() {
		return groupContent;
	}
	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}
	public String getGroupStartDate() {
		return groupStartDate;
	}
	public void setGroupStartDate(String groupStartDate) {
		this.groupStartDate = groupStartDate;
	}
	public String getGroupEndDate() {
		return groupEndDate;
	}
	public void setGroupEndDate(String groupEndDate) {
		this.groupEndDate = groupEndDate;
	}
	public int getGroupManagerNo() {
		return groupManagerNo;
	}
	public void setGroupManagerNo(int groupManagerNo) {
		this.groupManagerNo = groupManagerNo;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	   public String getGroupContentBr() {
		      return groupContent.replaceAll("\r\n", "<br>");
		   }
		   public String getGroupRuleBr() {
		      return groupRule.replaceAll("\r\n", "<br>");
		   }
		   public String getFilename() {
				return filename;
			}

			public void setFilename(String filename) {
				this.filename = filename;
			}

			public String getFilepath() {
				return filepath;
			}

			public void setFilepath(String filepath) {
				this.filepath = filepath;
			}
}
