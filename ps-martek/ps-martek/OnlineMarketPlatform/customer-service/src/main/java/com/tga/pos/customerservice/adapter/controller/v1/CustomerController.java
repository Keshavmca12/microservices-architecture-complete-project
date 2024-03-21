package com.tga.pos.customerservice.adapter.controller.v1;

import com.tga.pos.customerservice.*;
import com.tga.pos.customerservice.adapter.dto.*;
import com.tga.customer.model.*;
import com.tga.pos.customerservice.domainlayer.service.*;
import io.micrometer.core.annotation.Timed;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.util.*;

@RestController()
@RequestMapping("/customer"+APPConstant.V1)
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	


	@PostMapping(value = "/register")
	@Timed(value = "register", description = "Time taken for register new customer")
	public Mono<ResponseEntity<Response<Customer>>> save(@RequestBody  CustomerRequest request) {

		return customerService.save(request).map(user -> {
			return new ResponseEntity<Response<Customer>>(
					new Response<Customer>(true, "Record Saved Successully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<Customer>>(
				new Response<Customer>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}



	@GetMapping(value = "/{id}")
	@Timed(value = "userById", description = "Time taken for fetch specific user")
	public Mono<ResponseEntity<Response<Customer>>> customerById(@PathVariable("id") String id) {

		return customerService.findById(id)
				.map(user -> new ResponseEntity<Response<Customer>>(
						new Response<Customer>(true, "Customer Record retrieved successully", user), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<Customer>>(
						new Response<Customer>(false, "Customer Record not found"), HttpStatus.NOT_FOUND));

	}

	@GetMapping(value = "/all")
	@Timed(value = "allUsers", description = "Time taken for fetch all user")
	public Mono<ResponseEntity<Response<List<Customer>>>> allUsers() {

		return customerService.getAll().collectList()
				.map(list -> new ResponseEntity<Response<List<Customer>>>(
						new Response<List<Customer>>(true, "Record retrieved successully", list), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<List<Customer>>>(
						new Response<List<Customer>>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}


/*	@Timed(value = "address", description = "Time taken for add new customer address")
	@PutMapping(value = "/address")
	public Mono<ResponseEntity<Response<String>>> updateAddress(@RequestBody  AddressRequest request) {
		System.out.println("Checking if Debugger hits here");
		return customerService.updateAddress(request).map(customer -> {
			return new ResponseEntity<Response<String>>(
					new Response<String>(true, "Record Saved Successully", customer), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<String>>(
				new Response<String>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}*/



		/*@PostMapping(value = "/login")
	@Timed(value = "login", description = "Time taken for authenticating wser")
	public Mono<ResponseEntity<Response<AuthResponse>>> login(@RequestBody AuthRequest authReq) {

		return customerService.login(authReq).map(user -> {
			return new ResponseEntity<Response<AuthResponse>>(
					new Response<AuthResponse>(true, "User Authenticated Successfully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<AuthResponse>>(
				new Response<AuthResponse>(false, "Invalid User Details"), HttpStatus.UNAUTHORIZED));

	}*/
	
/*	@GetMapping(value = "/login/userinfo")
	@Timed(value = "loginUserInfo", description = "Time taken for fetch login user info")
	public Mono<ResponseEntity<Response<Customer>>> loginUserInfo() {

		
		return customerService.loginUserInfo()
				.map(user -> new ResponseEntity<Response<Customer>>(
						new Response<Customer>(true, "Record retrieved successully", user), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<Customer>>(
						new Response<Customer>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}



	@PutMapping(value = "/update/{id}")
	@Timed(value = "update", description = "Time taken for update user")
	public Mono<ResponseEntity<Response<Customer>>> update(@PathVariable("id") String id,
															  @RequestBody Customer _user) {

		return customerService.update(id, _user).map(user -> {
			return new ResponseEntity<Response<Customer>>(
					new Response<Customer>(true, "Record Saved Successully", user), HttpStatus.CREATED);
		}).defaultIfEmpty(new ResponseEntity<Response<Customer>>(
				new Response<Customer>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}*/

}
