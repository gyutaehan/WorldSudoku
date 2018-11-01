package kr.sesoc.sudoku.vo;

public class Record {
	private String recodeSeq;
	private String userRecord;
	private String difficulty;
	private String userId;
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Record(String recodeSeq, String userRecord, String difficulty, String userId) {
		super();
		this.recodeSeq = recodeSeq;
		this.userRecord = userRecord;
		this.difficulty = difficulty;
		this.userId = userId;
	}

	public String getRecodeSeq() {
		return recodeSeq;
	}

	public void setRecodeSeq(String recodeSeq) {
		this.recodeSeq = recodeSeq;
	}

	public String getUserRecord() {
		return userRecord;
	}

	public void setUserRecord(String userRecord) {
		this.userRecord = userRecord;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Record [recodeSeq=" + recodeSeq + ", userRecord=" + userRecord + ", difficulty=" + difficulty
				+ ", userId=" + userId + "]";
	}
	
	

}
