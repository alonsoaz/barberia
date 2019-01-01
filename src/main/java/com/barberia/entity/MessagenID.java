package com.barberia.entity;

public class MessagenID {
	
	private String message;
	private int ID;
	public MessagenID(String message, int iD) {
		super();
		this.message = message;
		ID = iD;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	

}
