package com.itzo.dto;

import java.util.Date;

public class MailDto {
	private int mseq;
	private int eseq ;
	private String name;
	private String email;
	private String sname; 
	private String sender;
	private String rname;
	private String receiver;
	private String title;
	private String content;
	private String mFile;
	private Date indate;
	private String remove;
	private String trans;
	private String imp;
	@Override
	public String toString() {
		return "MailDto [mseq=" + mseq + ", eseq=" + eseq + ", name=" + name + ", email=" + email + ", sname=" + sname
				+ ", sender=" + sender + ", rname=" + rname + ", receiver=" + receiver + ", title=" + title
				+ ", content=" + content + ", mFile=" + mFile + ", indate=" + indate + ", remove=" + remove + ", trans="
				+ trans + ", imp=" + imp + "]";
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	public String getmFile() {
		return mFile;
	}
	public void setmFile(String mFile) {
		this.mFile = mFile;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public String getRemove() {
		return remove;
	}
	public void setRemove(String remove) {
		this.remove = remove;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getImp() {
		return imp;
	}
	public void setImp(String imp) {
		this.imp = imp;
	}
	
	
	
	
	
}
