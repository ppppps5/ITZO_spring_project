package com.itzo.dto;

import java.util.Date;

public class SalaryDto {
	private int sseq;
	private int eseq;
	private String name;
	private String stitle;
	private Date indate;
	private String bsalary;
	private String annual;
	private String position;
	private String holiday;
	private String over;
	private String welfare;
	private String bonus;
	private String pension;
	private String emp;
	private String health;
	private String longcare;
	private String income;
	private String resident;
	private String labor;

	@Override
	public String toString() {
		return "SalaryDto [sseq=" + sseq + ", eseq=" + eseq + ", name=" + name + ", stitle=" + stitle + ", indate="
				+ indate + ", bsalary=" + bsalary + ", annual=" + annual + ", position=" + position + ", holiday="
				+ holiday + ", over=" + over + ", welfare=" + welfare + ", bonus=" + bonus + ", pension=" + pension
				+ ", emp=" + emp + ", health=" + health + ", longcare=" + longcare + ", income=" + income
				+ ", resident=" + resident + ", labor=" + labor + "]";
	}

	public int getSseq() {
		return sseq;
	}

	public void setSseq(int sseq) {
		this.sseq = sseq;
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

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getBsalary() {
		return bsalary;
	}

	public void setBsalary(String bsalary) {
		this.bsalary = bsalary;
	}

	public String getAnnual() {
		return annual;
	}

	public void setAnnual(String annual) {
		this.annual = annual;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

	public String getWelfare() {
		return welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getLongcare() {
		return longcare;
	}

	public void setLongcare(String longcare) {
		this.longcare = longcare;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getLabor() {
		return labor;
	}

	public void setLabor(String labor) {
		this.labor = labor;
	}

}