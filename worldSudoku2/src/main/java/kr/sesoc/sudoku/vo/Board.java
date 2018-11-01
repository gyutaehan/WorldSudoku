package kr.sesoc.sudoku.vo;

public class Board {
	private String boardSeq;
	private String title;
	private String content;
	private String userId;
	private String hitcount;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(String boardSeq, String title, String content, String userId, String hitcount) {
		super();
		this.boardSeq = boardSeq;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.hitcount = hitcount;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHitcount() {
		return hitcount;
	}
	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}
	@Override
	public String toString() {
		return "Board [boardSeq=" + boardSeq + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", hitcount=" + hitcount + "]";
	}
	

	
}
