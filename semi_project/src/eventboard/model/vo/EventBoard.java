package eventboard.model.vo;

public class EventBoard {
	private int eventNo;
	private String eventTitle;
	private String eventEnrollDate;
	private String eventEndDate;
	private String eventContent;
	private String filename;
	private String filepath;
	private String eventLink;
	private String eventWinnerStatus;   //<-----추가
	private int rnum;
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public EventBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventBoard(int eventNo, String eventTitle, String eventEnrollDate, String eventEndDate, String eventContent,
			String filename, String filepath, String eventLink, String eventWinnerStatus) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.eventEnrollDate = eventEnrollDate;
		this.eventEndDate = eventEndDate;
		this.eventContent = eventContent;
		this.filename = filename;
		this.filepath = filepath;
		this.eventLink = eventLink;
		this.eventWinnerStatus = eventWinnerStatus;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventEnrollDate() {
		return eventEnrollDate;
	}
	public void setEventEnrollDate(String eventEnrollDate) {
		this.eventEnrollDate = eventEnrollDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
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
	public String getEventLink() {
		return eventLink;
	}
	public void setEventLink(String eventLink) {
		this.eventLink = eventLink;
	}
	public String getEventWinnerStatus() {
		return eventWinnerStatus;
	}
	public void setEventWinnerStatus(String eventWinnerStatus) {
		this.eventWinnerStatus = eventWinnerStatus;
	}
	
	
}