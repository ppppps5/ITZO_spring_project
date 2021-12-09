package com.itzo.dto;

public class AttConDto {
	private int eseq;	//사원번호
	private int abs;		//결근
	private int ldate;	//지각일
	private int sick;	//병가
	private int vadate;	//월차
	private int reva;	//잔여월차
	public int getEseq() {
		return eseq;
	}
	public void setEseq(int eseq) {
		this.eseq = eseq;
	}
	public int getAbs() {
		return abs;
	}
	public void setAbs(int abs) {
		this.abs = abs;
	}
	public int getLdate() {
		return ldate;
	}
	public void setLdate(int ldate) {
		this.ldate = ldate;
	}
	public int getSick() {
		return sick;
	}
	public void setSick(int sick) {
		this.sick = sick;
	}
	public int getVadate() {
		return vadate;
	}
	public void setVadate(int vadate) {
		this.vadate = vadate;
	}
	public int getReva() {
		return reva;
	}
	public void setReva(int reva) {
		this.reva = reva;
	}
	
}
