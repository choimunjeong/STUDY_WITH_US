package groupstudy.model.vo;

public class GroupComment {
	private int rNum;
	private int commentNo;
	private int groupNo;
	private String commentContent;
	private String commentTitle;
	private String filename;
	private String filepath;
	private String commentWriter;
	public GroupComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public GroupComment(int rNum, int commentNo, int groupNo, String commentContent, String commentTitle,
			String filename, String filepath, String commentWriter) {
		super();
		this.rNum = rNum;
		this.commentNo = commentNo;
		this.groupNo = groupNo;
		this.commentContent = commentContent;
		this.commentTitle = commentTitle;
		this.filename = filename;
		this.filepath = filepath;
		this.commentWriter = commentWriter;
	}

	public String getCommentContentBr() {
		return commentContent.replaceAll("\r\n", "<br>");
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
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
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
}