package noticeboard.model.vo;

public class NoticeBoard {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String filename;
	private String filepath;
	private int noticeStatus;
	private String noticeEnrollDate;
	private int rnum;
	public NoticeBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeBoard(int noticeNo, String noticeTitle, String noticeContent, String filename, String filepath,
			int noticeStatus, String noticeEnrollDate, int rnum) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.filename = filename;
		this.filepath = filepath;
		this.noticeStatus = noticeStatus;
		this.noticeEnrollDate = noticeEnrollDate;
		this.rnum = rnum;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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
	public int getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(int noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getNoticeEnrollDate() {
		return noticeEnrollDate;
	}
	public void setNoticeEnrollDate(String noticeEnrollDate) {
		this.noticeEnrollDate = noticeEnrollDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	
	
	
	
	
}
