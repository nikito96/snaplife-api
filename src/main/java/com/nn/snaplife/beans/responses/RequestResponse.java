package com.nn.snaplife.beans.responses;

public class RequestResponse implements Response {
	
	private boolean success;
	private String message;

	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return this.success;
	}

	@Override
	public void setSuccess(boolean success) {
		// TODO Auto-generated method stub
		this.success = success;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}

}
