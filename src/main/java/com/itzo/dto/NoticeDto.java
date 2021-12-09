package com.itzo.dto;

import java.util.Date;

public class NoticeDto {
	private int nseq;
	private int eseq;
	private String name;
	private String nWriter;
	private String nTitle;
	private String nContent;
	private String nFile;
	private Date indate;
	private int nGroup;
	private int nStep;
	private int nIndent;

	@Override
	public String toString() {
		return "NoticeDto [nseq=" + nseq + ", eseq=" + eseq + ", name=" + name + ", nWriter=" + nWriter + ", nTitle="
				+ nTitle + ", nContent=" + nContent + ", nFile=" + nFile + ", indate=" + indate + ", nGroup=" + nGroup
				+ ", nStep=" + nStep + ", nIndent=" + nIndent + "]";
	}

	public int getNseq() {
		return nseq;
	}

	public void setNseq(int nseq) {
		this.nseq = nseq;
	}

	public int getEseq() {
		return eseq;
	}

	public void setEseq(int eseq) {
		this.eseq = eseq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nWriter) {
		this.nWriter = nWriter;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnFile() {
		return nFile;
	}

	public void setnFile(String nFile) {
		this.nFile = nFile;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public int getnGroup() {
		return nGroup;
	}

	public void setnGroup(int nGroup) {
		this.nGroup = nGroup;
	}

	public int getnStep() {
		return nStep;
	}

	public void setnStep(int nStep) {
		this.nStep = nStep;
	}

	public int getnIndent() {
		return nIndent;
	}

	public void setnIndent(int nIndent) {
		this.nIndent = nIndent;
	}

}