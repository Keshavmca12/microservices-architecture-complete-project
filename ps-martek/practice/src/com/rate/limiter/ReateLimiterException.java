package com.rate.limiter;

public class ReateLimiterException  extends RuntimeException {
	
	private String msg;
	
	private int code;

	public ReateLimiterException(String msg, int code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	
	public ReateLimiterException(String msg) {
		super();
		this.msg = msg;
	}
	
	
	
	
	

}
