package kr.sesoc.sudoku.vo;

public class Member {
	private String userId;
	private String password;
	private String point;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String userId, String password, String point) {
		super();
		this.userId = userId;
		this.password = password;
		this.point = point;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", point=" + point + "]";
	}
	
	

}
