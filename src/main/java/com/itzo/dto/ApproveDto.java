package com.itzo.dto;

import java.util.Date;

public class ApproveDto {
	private int aseq;
	private int eseq;
	private String title;
	private String content;
	private String approval;
	private int dseq;
	private String position;
	private Date indate;
	private String aFile;
	private String myapprove;
	private String department;
	private String writer;
	private String state;
	
	@Override
	public String toString() {
		return "ApproveDto [aseq=" + aseq + ", eseq=" + eseq + ", title=" + title + ", content=" + content
				+ ", approval=" + approval + ", dseq=" + dseq + ", position=" + position + ", indate=" + indate
				+ ", aFile=" + aFile + ", myapprove=" + myapprove + ", department=" + department + ", writer=" + writer
				+ ", state=" + state + "]";
	}

	public int getAseq() {
		return aseq;
	}

	public void setAseq(int aseq) {
		this.aseq = aseq;
	}

	public int getEseq() {
		return eseq;
	}

	public void setEseq(int eseq) {
		this.eseq = eseq;
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

	public int getDseq() {
		return dseq;
	}

	public void setDseq(int dseq) {
		this.dseq = dseq;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getaFile() {
		return aFile;
	}

	public void setaFile(String aFile) {
		this.aFile = aFile;
	}

	public String getMyapprove() {
		return myapprove;
	}

	public void setMyapprove(String myapprove) {
		this.myapprove = myapprove;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}

