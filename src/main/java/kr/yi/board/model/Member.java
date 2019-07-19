package kr.yi.board.model;

import java.util.Date;

public class Member {
	private String memberid;
	private String name;
	private String password;
	private Date regdate;
	
	public Member() {
		super();
	}
	public Member(String memeberid, String name, String password, Date regdate) {
		super();
		this.memberid = memeberid;
		this.name = name;
		this.password = password;
		this.regdate = regdate;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memeberid) {
		this.memberid = memeberid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
