package com.itzo.dto;

//결재상세
public class AppDetailDto {
	private int aseq;
	private String title;
	private String content;
	private String approval;
	private String dname;
	private String position;
	private String indate;
	public int getAseq() {
		return aseq;
	}
	public void setAseq(int aseq) {
		this.aseq = aseq;
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
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	
}
