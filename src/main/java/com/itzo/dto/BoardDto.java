package com.itzo.dto;

import java.util.Date;

public class BoardDto {

	private int bseq;
	private int eseq;
	private String name;
	private String bWriter;
	private String bTitle;
	private String bContent;
	private String bFile;
	private Date indate;
	private int bGroup;
	private int bStep;
	private int bIndent;
	private int dseq;

	@Override
	public String toString() {
		return "BoardDto [bseq=" + bseq + ", eseq=" + eseq + ", name=" + name + ", bWriter=" + bWriter + ", bTitle="
				+ bTitle + ", bContent=" + bContent + ", bFile=" + bFile + ", indate=" + indate + ", bGroup=" + bGroup
				+ ", bStep=" + bStep + ", bIndent=" + bIndent + ", dseq=" + dseq + "]";
	}

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
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

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbFile() {
		return bFile;
	}

	public void setbFile(String bFile) {
		this.bFile = bFile;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	public int getDseq() {
		return dseq;
	}

	public void setDseq(int dseq) {
		this.dseq = dseq;
	}

}