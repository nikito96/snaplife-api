package com.nn.snaplife.beans.responses;

public interface Response {
	boolean isSuccess();
	void setSuccess(boolean success);
	String getMessage();
	void setMessage(String message);
}
