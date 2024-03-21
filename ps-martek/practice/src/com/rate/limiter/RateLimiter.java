package com.rate.limiter;

public interface RateLimiter {
	/**Implement a rate limiter class 
Library class
only 2 public methods
configure(string url, ….): void // once per app run
isRateLimited(string url): booelan // per request 
All api handlers will first call this library class and method
Depending on the response from the method api handler will either continue processing OR reject the request
All api handlers can configure their own rate limit configuration

controller
if (Ratelimiter.isRateLimited(Request.url) ) { throw error} process;
Config
n no. of requests per m units of timeunit
10 req /1m
20req/5m
timeunit can be m, s, h


requirements
Need to implement a rate limiter library
Need to process in memory - [no db or cache]
*/

	public void configure(String url, RateLimiterModel limiterModel);
	
	public boolean isRateLimited(String url);
}
