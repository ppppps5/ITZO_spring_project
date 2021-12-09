package com.itzo.dto;

import java.util.Date;

//근태관리
public class AttDto {
	private int atseq; //근태관리번호
	private int eseq;	//사원번호
	private String comdate;	//출근일
	private Date come;	//출근시간
	private Date leave;	//퇴근시간
	private String late;	//지각시간
	private int add1;	//추가근무
	private int total1;	//총업무시간
	private String state;	//상태
	public int getAtseq() {
		return atseq;
	}
	public void setAtseq(int atseq) {
		this.atseq = atseq;
	}
	public int getEseq() {
		return eseq;
	}
	public void setEseq(int eseq) {
		this.eseq = eseq;
	}
	public String getComdate() {
		return comdate;
	}
	public void setComdate(String comdate) {
		this.comdate = comdate;
	}
	public Date getCome() {
		return come;
	}
	public void setCome(Date come) {
		this.come = come;
	}
	public Date getLeave() {
		return leave;
	}
	public void setLeave(Date leave) {
		this.leave = leave;
	}
	public String getLate() {
		return late;
	}
	public void setLate(String late) {
		this.late = late;
	}
	public int getAdd1() {
		return add1;
	}
	public void setAdd1(int add1) {
		this.add1 = add1;
	}
	public int getTotal1() {
		return total1;
	}
	public void setTotal1(int total1) {
		this.total1 = total1;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
