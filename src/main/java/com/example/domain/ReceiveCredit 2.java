package com.example.domain;

public class ReceiveCredit {
	private String status;
	private String messsage;
	private String errorCode;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMesssage() {
		return messsage;
	}
	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@Override
	public String toString() {
		return "ReceiveCredit [status=" + status + ", messsage=" + messsage + ", errorCode=" + errorCode + "]";
	}
	

}
