package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emailapi.model.EmailRequest;
import com.emailapi.model.EmailResponse;
import com.emailapi.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "Hello Brijesh";
	}
	@RequestMapping("/welcome1")
	public String welcome1()
	{
		return "Hello1";
	}
	@RequestMapping(value="/sendEmail",method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest)
	{
		//
		System.out.println(emailRequest);
		boolean result = this.emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
		if(result)
		{
			return ResponseEntity.ok(new EmailResponse("Response send successfilly"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not send"));
		}
		
	}
}
