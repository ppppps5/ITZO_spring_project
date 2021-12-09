package com.itzo.dto;

import java.util.Date;

public class VacationDto {
	private int vseq;
	private int eseq;
	private int vkseq;
	private String title;
	private Date start1;
	private Date end1;
	private int maxdate;
	private Date cdate;
	private String vFile;
	private String result;
	private String writer;
	private String approval;
	private String content;
	
	@Override
	public String toString() {
		return "VacationDto [vseq=" + vseq + ", eseq=" + eseq + ", vkseq=" + vkseq + ", title=" + title + ", start1="
				+ start1 + ", end1=" + end1 + ", cdate=" + cdate + ", maxdate=" + maxdate + ", vFile=" + vFile
				+ ", result=" + result + ", writer=" + writer + ", approval=" + approval + ", content=" + content + "]";
	}

	public int getVseq() {
		return vseq;
	}

	public void setVseq(int vseq) {
		this.vseq = vseq;
	}

	public int getEseq() {
		return eseq;
	}

	public void setEseq(int eseq) {
		this.eseq = eseq;
	}

	public int getVkseq() {
		return vkseq;
	}

	public void setVkseq(int vkseq) {
		this.vkseq = vkseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart1() {
		return start1;
	}

	public void setStart1(Date start1) {
		this.start1 = start1;
	}

	public Date getEnd1() {
		return end1;
	}

	public void setEnd1(Date end1) {
		this.end1 = end1;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getMaxdate() {
		return maxdate;
	}

	public void setMaxdate(int maxdate) {
		this.maxdate = maxdate;
	}

	public String getvFile() {
		return vFile;
	}

	public void setvFile(String vFile) {
		this.vFile = vFile;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	
}


