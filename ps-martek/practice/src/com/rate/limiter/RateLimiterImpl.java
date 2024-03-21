package com.rate.limiter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RateLimiterImpl implements RateLimiter {

	private Map<String, RateLimiterModel> rateLimiterCache = new HashMap<>();

	@Override
	public void configure(String url, RateLimiterModel limiterModel) {
		/*
		 * if (isRateLimited(url)) { return; }
		 */
		initRateLimiter(url, limiterModel);
	}

	private void initRateLimiter(String url, RateLimiterModel limiterModel) {
		System.out.println("initRateLimiter");
		limiterModel.setStartTime(LocalDateTime.now());
		rateLimiterCache.put(url, limiterModel);
	}

	@Override
	public boolean isRateLimited(String url) {
		if (rateLimiterCache.containsKey(url)) {
			RateLimiterModel limiterModel = rateLimiterCache.get(url);
			boolean isValid = isValid(limiterModel);

			if (!isValid) {
				initRateLimiter(url, limiterModel);
			}

			if (limiterModel.getNoOfReqquest() > 0) {
				System.out.println("decresing limit");
				limiterModel.setNoOfReqquest(limiterModel.getNoOfReqquest() - 1);
				return true;
			}

		}

		System.out.println("Limit exhausted or not set");
		return false;
	}

	private boolean isValid(RateLimiterModel limiterModel) {
		long diffSecond = Duration.between(limiterModel.getStartTime(), LocalDateTime.now()).toSeconds();

		if (diffSecond > getValidity(limiterModel)) {
			// throw new ReateLimiterException(" ");
			return false;
		}
		return true;
	}

	private long getValidity(RateLimiterModel limiterModel) {
		long validityTime;

		switch (limiterModel.getTimeUnit()) {
		case "H":
			validityTime = limiterModel.getTimePeriod() * 60 * 60;
			break;
		case "M":
			validityTime = limiterModel.getTimePeriod() * 60;
			break;
		default:
			validityTime = limiterModel.getTimePeriod();
			break;
		}

		return validityTime;
	}

	public static void main(String[] args) throws InterruptedException {
		RateLimiterImpl impl = new RateLimiterImpl();

		impl.configure("abc", new RateLimiterModel(3, 1, "S"));

		System.out.println("abc1 : " + impl.isRateLimited("abc"));

		System.out.println("abc2 : " + impl.isRateLimited("abc"));

		System.out.println("abc3 : " + impl.isRateLimited("abc"));

		System.out.println("abc4 : " + impl.isRateLimited("abc"));

		System.out.println("abc5 : " + impl.isRateLimited("abc"));

		Thread.sleep(2000);

		System.out.println("abc1 : " + impl.isRateLimited("abc"));

		System.out.println("abc2 : " + impl.isRateLimited("abc"));

		System.out.println("xyz : " + impl.isRateLimited("xyz"));

	}

}
