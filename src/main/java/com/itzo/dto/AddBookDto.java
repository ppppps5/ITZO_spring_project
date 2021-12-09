package com.itzo.dto;

public class AddBookDto {
	private int eseq;
	private String name;
	private String email;
	private String position;
	@Override
	public String toString() {
		return "AddBookDto [eseq=" + eseq + ", name=" + name + ", email=" + email + ", position=" + position + "]";
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
}
