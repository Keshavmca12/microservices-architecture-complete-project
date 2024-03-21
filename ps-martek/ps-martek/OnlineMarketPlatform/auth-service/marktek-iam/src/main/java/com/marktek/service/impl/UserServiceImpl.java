package com.marktek.service.impl;

import com.marktek.dao.UserDao;
import com.marktek.entity.Customers;
import com.marktek.model.RegistrationForm;
import com.marktek.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	private static final Logger log = getLogger(lookup().lookupClass());
	@Autowired
	private UserDao userDao;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		Customers user = userDao.findByMobileNumber(mobileNumber);
		if(user == null){
			throw new UsernameNotFoundException("Invalid mobile number or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getMobileNumber(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<Customers> findAll() {
		List<Customers> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(Long.valueOf(id));
	}

	@Override
    public Customers save(Customers user) {
        return userDao.save(user);
    }

	@Override
	public Boolean sendOtp(RegistrationForm user) {

		if(user!=null){
			if(user.getMobileNo().contains("543")){
				Customers detials=new Customers();
				detials.setAge(user.getAge());
				detials.setEmail(user.getEmail());
				detials.setAddress(user.getAddress());
				detials.setLastname(user.getLastname());
				detials.setPassword(user.getPassword());
				detials.setMobileNumber(user.getMobileNo());
				save(detials);
			}
			else{
				return Boolean.FALSE;
			}
		}
//		if(user!=null){
//			try {
//				AwsBasicCredentials awsCreds = AwsBasicCredentials.create(this.accessKey, this.secretKey);
//				SnsClient snsClient = SnsClient.builder()
//						.region(Region.US_EAST_1)
//						.credentialsProvider(StaticCredentialsProvider.create(awsCreds))
//						.build();
//
//				PublishRequest request = PublishRequest.builder()
//						.message("Hello this is a message from Amay")
//						.phoneNumber(user.getMobileNo())
//						.build();
//
//				PublishResponse result = snsClient.publish(request);
//				log.info(result.messageId() + " Message sent. Status was " + result.sdkHttpResponse().statusCode());
//				return Boolean.TRUE;
//			} catch (SnsException e) {
//				log.error(e.awsErrorDetails().errorMessage());
//			}
//		}
		return Boolean.TRUE;
	}
}
