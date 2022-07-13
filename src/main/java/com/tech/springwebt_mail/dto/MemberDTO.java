package com.tech.springwebt_mail.dto;

public class MemberDTO {
	private String id;
	private String pw;
	private String nickname;
	private String email;
	private String shpwd;
	private String bcpwd;
	private int mailcheck;
	

	
	public MemberDTO(String id, String pw, String nickname, String email, String shpwd, String bcpwd) {
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.email = email;
		this.shpwd = shpwd;
		this.bcpwd = bcpwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShpwd() {
		return shpwd;
	}
	public void setShpwd(String shpwd) {
		this.shpwd = shpwd;
	}
	public String getBcpwd() {
		return bcpwd;
	}
	public void setBcpwd(String bcpwd) {
		this.bcpwd = bcpwd;
	}
	public int getMailcheck() {
		return mailcheck;
	}
	public void setMailcheck(int mailcheck) {
		this.mailcheck = mailcheck;
	}
	
	
	
	
}
