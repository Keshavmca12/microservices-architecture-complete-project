package com.rate.limiter;

import java.time.LocalDateTime;

public class RateLimiterModel {

	private int noOfReqquest;
	private int timePeriod;
	// We can make this Enum;
	private String timeUnit;
	
	private int fixedValue;

	private  LocalDateTime startTime;
	
	
	
	public RateLimiterModel(int noOfReqquest, int timePeriod, String timeUnit) {
		super();
		this.noOfReqquest = noOfReqquest;
		this.timePeriod = timePeriod;
		this.timeUnit = timeUnit;
		this.fixedValue = noOfReqquest;
	}
	public int getNoOfReqquest() {
		return noOfReqquest;
	}
	public void setNoOfReqquest(int noOfReqquest) {
		this.noOfReqquest = noOfReqquest;
	}
	public int getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;
	}
	public String getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public int getFixedValue() {
		return fixedValue;
	}
	public void setFixedValue(int fixedValue) {
		this.fixedValue = fixedValue;
	}
	
	
	
	
}


