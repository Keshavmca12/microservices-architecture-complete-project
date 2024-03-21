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

@RestController()
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerAttributesController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(CustomerAttributesController.class);

	@Autowired
	private CustomerService customerService;



	/*@PostMapping(value = "/register")
	@Timed(value = "register", description = "Time taken for add new customer address")
	public Mono<ResponseEntity<Response<Customer>>> save(@RequestBody  CustomerRequest request) {

		return customerService.save(request).map(user -> {
			return new ResponseEntity<Response<Customer>>(
					new Response<Customer>(true, "Record Saved Successully", user), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<Customer>>(
				new Response<Customer>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));
	}*/




	@GetMapping(value = "/customeraddress/api/v1/{custId}")
	@Timed(value = "User Address ", description = "Time taken for fetch specific Address")
	public Mono<ResponseEntity<Response<Address>>> userById(@PathVariable("custId") String id) {

		log.debug(" finding the delivery address :{}",id);
		return customerService.findDeliveryAddressId(id)
				.map(address -> new ResponseEntity<Response<Address>>(
						new Response<Address>(true, "Customer Record retrieved successully", address), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<Address>>(
						new Response<Address>(false, "Customer Record not found"), HttpStatus.NOT_FOUND));

	}


	@GetMapping(value = "/customerpay/api/v1/{custId}")
	@Timed(value = "User CardDetails ", description = "Time taken for fetch specific CardDetails")
	public Mono<ResponseEntity<Response<CardDetails>>> paymentByCustId(@PathVariable("custId") String id) {

		log.debug(" finding the delivery CardDetails :{}",id);
		return customerService.findPaymentInfoById(id)
				.map(CardDetails -> new ResponseEntity<Response<CardDetails>>(
						new Response<CardDetails>(true, "Customer Record retrieved successully", CardDetails), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<CardDetails>>(
						new Response<CardDetails>(false, "Customer Record not found"), HttpStatus.NOT_FOUND));

	}



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
