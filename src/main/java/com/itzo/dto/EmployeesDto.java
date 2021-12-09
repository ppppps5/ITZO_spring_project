package com.itzo.dto;

import java.util.Date;

public class EmployeesDto {
	private int eseq;
	private String name;
	private String pwd;
	private String position;
	private String email;
	private String phone;
	private Date joinday;
	private String resinum;
	private String address;
	private String salary;
	private int dseq;
	private int enabled;
	private String authority;

	
	@Override
	public String toString() {
		return "EmployeesDto [eseq=" + eseq + ", name=" + name + ", pwd=" + pwd + ", position=" + position + ", email="
				+ email + ", phone=" + phone + ", joinday=" + joinday + ", resinum=" + resinum + ", address=" + address
				+ ", salary=" + salary + ", dseq=" + dseq + ", enabled=" + enabled + ", authority=" + authority + "]";
	}
	
	
//	public EmployeesDto() {
//		this("user", "1111", 1);
//	}
//	
//	public EmployeesDto(String username, String password) {
//		this(username, password, 1);
//	}
	

	public EmployeesDto () {}
	public EmployeesDto(int eseq, String name, String pwd, String position, String email, String phone, Date joinday,
			String resinum, String address, String salary, int dseq, int enabled, String authority) {
		super();
		this.eseq = eseq;
		this.name = name;
		this.pwd = pwd;
		this.position = position;
		this.email = email;
		this.phone = phone;
		this.joinday = joinday;
		this.resinum = resinum;
		this.address = address;
		this.salary = salary;
		this.dseq = dseq;
		this.enabled = enabled;
		this.authority = authority;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoinday() {
		return joinday;
	}

	public void setJoinday(Date joinday) {
		this.joinday = joinday;
	}

	public String getResinum() {
		return resinum;
	}

	public void setResinum(String resinum) {
		this.resinum = resinum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public int getDseq() {
		return dseq;
	}

	public void setDseq(int dseq) {
		this.dseq = dseq;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	

}
