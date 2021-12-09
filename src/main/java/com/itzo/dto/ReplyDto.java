package com.itzo.dto;

import java.util.Date;

public class ReplyDto {
	private Integer rseq;
	private Integer bseq;
	private String replytext;
	private String replyer;
	private Date indate;
	private Date updatedate;

	@Override
	public String toString() {
		return "ReplyDto [rseq=" + rseq + ", bseq=" + bseq + ", replytext=" + replytext + ", replyer=" + replyer
				+ ", indate=" + indate + ", updatedate=" + updatedate + "]";
	}

	public Integer getRseq() {
		return rseq;
	}

	public void setRseq(Integer rseq) {
		this.rseq = rseq;
	}

	public Integer getBseq() {
		return bseq;
	}

	public void setBseq(Integer bseq) {
		this.bseq = bseq;
	}

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

}
